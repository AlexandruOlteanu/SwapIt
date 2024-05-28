import React from 'react';

const UserProfileButtons = ({
    userData, isUserProfileAuth, isAdmin, isTemporaryBanned, isPermanentBanned, setActiveSection, setIsRightFavoriteProducts, 
    handleLogout, setIsBanDialogVisible, setIsRemoveBanDialogVisible, isOauth2User
}) => {
    return (
        <div className="user-data">
            <hr className="pl-4" style={{ border: '1px solid white', margin: '20px 8px' }} />
            {((isUserProfileAuth && !isAdmin) || !isUserProfileAuth) && (
                <div className="d-flex user-profile-button ml-2 mr-2 p-2 align-items-center br-10" onClick={() => setIsRightFavoriteProducts(false)}>
                    <div className="d-flex br-10 align-items-center justify-content-center flex-shrink-0 ml-n3" style={{ width: '50px', height: '25px' }}>
                        <i className="fa-solid fa-bag-shopping"></i>
                    </div>
                    {isUserProfileAuth && (
                        <div className="text-light m-0 ml-n2">My Products</div>
                    )}
                    {!isUserProfileAuth && (
                        <div className="text-light m-0 ml-n2">{userData.surname}'s Products</div>
                    )}
                </div>
            )}
            {(isAdmin && !isTemporaryBanned && !isPermanentBanned && !isUserProfileAuth) && (
                <div className="d-flex ban-button mt-2 ml-2 mr-2 p-2 align-items-center br-10" style={{ color: 'red' }} onClick={() => setIsBanDialogVisible(true)}>
                    <div className="d-flex br-10 align-items-center justify-content-center flex-shrink-0 ml-n3" style={{ width: '50px', height: '25px' }}>
                        <i className="fa-solid fa-ban"></i>
                    </div>
                    <div className="m-0 ml-n2">Ban This User</div>
                </div>
            )}
            {(isAdmin && (isTemporaryBanned || isPermanentBanned)) && (
                <div className="d-flex ban-button mt-2 ml-2 mr-2 p-2 align-items-center br-10" style={{ color: 'var(--success)' }} onClick={() => setIsRemoveBanDialogVisible(true)}>
                    <div className="d-flex br-10 align-items-center justify-content-center flex-shrink-0 ml-n3" style={{ width: '50px', height: '25px' }}>
                        <i className="fa-solid fa-ban"></i>
                    </div>
                    <div className="m-0 ml-n2">Remove User Ban</div>
                </div>
            )}
            {isUserProfileAuth && (
                <>
                    {!isAdmin && (
                        <div className="d-flex user-profile-button mt-2 ml-2 mr-2 p-2 align-items-center br-10" onClick={() => setIsRightFavoriteProducts(true)}>
                            <div className="d-flex br-10 align-items-center justify-content-center flex-shrink-0 ml-n3" style={{ width: '50px', height: '25px' }}>
                                <i className="fa-solid fa-heart"></i>
                            </div>
                            <div className="text-light m-0 ml-n2">Favourite Products</div>
                        </div>
                    )}
                    <div className="d-flex user-profile-button mt-2 ml-2 mr-2 p-2 align-items-center br-10" onClick={() => setActiveSection("updateProfile")}>
                        <div className="d-flex br-10 align-items-center justify-content-center flex-shrink-0 ml-n3" style={{ width: '50px', height: '25px' }}>
                            <i className="fa-solid fa-pen-to-square"></i>
                        </div>
                        <div className="text-light m-0 ml-n2">Update Profile</div>
                    </div>
                    {!isOauth2User && (
                        <div className="d-flex user-profile-button mt-2 ml-2 mr-2 p-2 align-items-center br-10" onClick={() => setActiveSection("changeEmail")}>
                            <div className="d-flex br-10 align-items-center justify-content-center flex-shrink-0 ml-n3" style={{ width: '50px', height: '25px' }}>
                                <i className="fa-solid fa-envelope-circle-check"></i>
                            </div>
                            <div className="text-light m-0 ml-n2">Change Email</div>
                        </div>
                    )}
                    <div className="d-flex user-profile-button mt-2 ml-2 mr-2 p-2 align-items-center br-10" onClick={() => setActiveSection("changeUsername")}>
                        <div className="d-flex br-10 align-items-center justify-content-center flex-shrink-0 ml-n3" style={{ width: '50px', height: '25px' }}>
                            <i className="fa-solid fa-file-signature"></i>
                        </div>
                        <div className="text-light m-0 ml-n2">Change Username</div>
                    </div>
                    <div className="d-flex user-profile-button mt-2 ml-2 mr-2 p-2 align-items-center br-10" onClick={() => setActiveSection("changePassword")}>
                        <div className="d-flex br-10 align-items-center justify-content-center flex-shrink-0 ml-n3" style={{ width: '50px', height: '25px' }}>
                            <i className="fa-solid fa-shield-halved"></i>
                        </div>
                        <div className="text-light m-0 ml-n2">Reset Password</div>
                    </div>
                    <div className="d-flex user-profile-button mt-2 ml-2 mr-2 p-2 align-items-center br-10" onClick={handleLogout}>
                        <div className="d-flex br-10 align-items-center justify-content-center flex-shrink-0 ml-n3" style={{ width: '50px', height: '25px' }}>
                            <i className="fa-solid fa-right-from-bracket"></i>
                        </div>
                        <div className="text-light m-0 ml-n2">Logout</div>
                    </div>
                </>
            )}
        </div>
    );
};

export default UserProfileButtons;
