import React, { useEffect, useState, lazy } from 'react';
import { useParams } from 'react-router-dom';

import Preloader from '../js/Preloader';
import ApiBackendService from '../apiBackend/ApiBackendService';

const TopbarSection = lazy(() => import('../sections/TopbarSection'));
const NavbarSection = lazy(() => import('../sections/NavbarSection'));
const SearchSection = lazy(() => import('../sections/SearchSection'));
const FooterSection = lazy(() => import('../sections/FooterSection'));
const BackToTopButton = lazy(() => import('../js/BackToTopButton'));



const UserProfile = () => {
    const { username } = useParams();
    const [userData, setUserData] = useState({
        userId: -1,
        username: '',
        name: '',
        surname: '',
        email: '',
        userImage: '',
        joinDate: '',
        userRole: ''
    });

    useEffect(() => {
        const fetchUserData = async () => {
            if (username) {
                try {
                    const response = await ApiBackendService.getUserDetailsByUsername({ username });
                    setUserData(response);
                    console.log(response);
                } catch (error) {
                    console.error('Failed to fetch user details:', error);
                }
            }
        };

        fetchUserData();
    }, [username]);

    return (
        <React.Fragment>
            <Preloader />
            <TopbarSection />
            <NavbarSection />
            <SearchSection />
            <h1>User Profile</h1>
            <p>Viewing profile for: {userData.username} and id {userData.userId}</p>
            <div>
                <p>Name: {userData.name} {userData.surname}</p>
                <p>Email: {userData.email}</p>
                <p>Joined: {new Date(userData.joinDate).toLocaleDateString()}</p>
                <p>Role: {userData.userRole}</p>
                {userData.userImage && <img src={userData.userImage} style={{ maxWidth: '100px', maxHeight: '100px' }} alt="User" />}
            </div>
            <FooterSection />
            <BackToTopButton />
        </React.Fragment>
    );
};

export default UserProfile;
