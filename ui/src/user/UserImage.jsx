import React, { useRef } from 'react';
import { storage } from '../firebase-config';
import { ref, uploadBytes, getDownloadURL } from 'firebase/storage';
import { v4 as uuidv4 } from 'uuid';
import ApiBackendService from '../apiBackend/ApiBackendService';

const UserImage = ({ userData, setUserData, isUserProfileAuth }) => {
    const fileInputRef = useRef(null);

    const changeProfilePicture = async (e) => {
        const file = e.target.files[0];
        const imageRef = ref(storage, `images/${uuidv4()}`);
        uploadBytes(imageRef, file).then((snapshot) => {
            getDownloadURL(snapshot.ref).then((url) => {
                const data = {
                    userDetails: {
                        IMAGE: url
                    }
                };
                ApiBackendService.updateBasicUserDetails({}, data).then(() => {
                    setUserData(prevUserData => ({
                        ...prevUserData,
                        userImage: url
                    }));
                }).catch(error => {
                    console.error('Failed to update user image:', error);
                });
            });
        });
    };

    const handleUserImageClick = () => {
        if (isUserProfileAuth) {
            fileInputRef.current.click();
        }
    };

    return (
        <div className="user-image" onClick={handleUserImageClick}>
            <img src={userData.userImage} alt="User" />
            {isUserProfileAuth && (
                <input
                    type="file"
                    style={{ display: 'none' }}
                    onChange={changeProfilePicture}
                    ref={fileInputRef}
                />
            )}
        </div>
    );
};

export default UserImage;
