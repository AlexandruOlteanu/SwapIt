import React from 'react';

const UserDetails = ({ userData, isTemporaryBanned, isPermanentBanned, banExpiryDate, isUserProfileAuth }) => {
    return (
        <div className="user-details">
            {(isTemporaryBanned || isPermanentBanned) && (
                <div className="d-flex align-items-center br-10 pl-4 pt-4" style={{ color: 'red' }}>
                    <div className="d-flex br-10 align-items-center justify-content-center flex-shrink-0 ml-n4" style={{ width: '50px', height: '50px' }}>
                        <i className="fa-solid fa-lg fa-triangle-exclamation"></i>
                    </div>
                    {isTemporaryBanned && (
                        <div className="m-0"> User is banned till {banExpiryDate} </div>
                    )}
                    {isPermanentBanned && (
                        <div className="m-0"> User is permanently banned </div>
                    )}
                </div>
            )}
            <div className="d-flex align-items-center br-10 pl-4 pt-4">
                <div className="d-flex br-10 align-items-center justify-content-center flex-shrink-0 ml-n4" style={{ width: '50px', height: '50px' }}>
                    <i className="fa fa-lg fa-user"></i>
                </div>
                <div className="text-light m-0">{userData.name} {userData.surname}</div>
            </div>
            {(!isUserProfileAuth && userData.phoneNumber) && (
                <div className="d-flex align-items-center br-10 pl-4">
                    <div className="d-flex br-10 align-items-center justify-content-center flex-shrink-0 ml-n4" style={{ width: '50px', height: '50px' }}>
                        <i className="fa-solid fa-lg fa-mobile-screen-button"></i>
                    </div>
                    <div className="text-light m-0">{userData.phoneNumber}</div>
                </div>
            )}
            <div className="d-flex align-items-center br-10 pl-4">
                <div className="d-flex br-10 align-items-center justify-content-center flex-shrink-0 ml-n4" style={{ width: '50px', height: '50px' }}>
                    <i className="fa fa-lg fa-envelope"></i>
                </div>
                <div className="text-light m-0">{userData.email}</div>
            </div>
        </div>
    );
};

export default UserDetails;
