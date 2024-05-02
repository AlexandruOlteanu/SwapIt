import React, { useEffect, useState, lazy } from 'react';
import { useParams } from 'react-router-dom';

import Preloader from '../js/Preloader';
import ApiBackendService from '../apiBackend/ApiBackendService';
import '../scss/UserProfile.scss';
import '../css/UserProfile.css'

const TopbarSection = lazy(() => import('../sections/TopbarSection'));
const NavbarSection = lazy(() => import('../sections/NavbarSection'));
const SearchSection = lazy(() => import('../sections/SearchSection'));
const FooterSection = lazy(() => import('../sections/FooterSection'));
const BackToTopButton = lazy(() => import('../js/BackToTopButton'));


const UserProfile = () => {
    const [isSidebarMinimized, setIsSidebarMinimized] = useState(false);
    const [isUserProfileAuth, setIsUserProfileAuth] = useState(false);
    const [isOauth2User, setIsOauth2User] = useState(false);
    const [activeSection, setActiveSection] = useState("");


    const toggleSidebar = () => {
        setIsSidebarMinimized(!isSidebarMinimized);
    };

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
    const [authUserData, setAuthUserData] = useState({
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

        const fetchAuthUserData = async () => {
            try {
                const response = await ApiBackendService.getAuthenticatedUserDetails({});
                setAuthUserData(response);
                console.log(response);
            } catch (error) {
                console.error('Failed to fetch auth user details:', error);
            }
        };

        fetchUserData();
        fetchAuthUserData();
    }, [username]); // Depends only on username for re-fetching

    useEffect(() => {
        if (userData && authUserData) {
            if (userData.userId === authUserData.userId) {
                setIsUserProfileAuth(true);
            } else {
                setIsUserProfileAuth(false);
            }

            if (authUserData.userRole === "OAUTH2_USER") {
                setIsOauth2User(true);
            } else {
                setIsOauth2User(false);
            }
        }
    }, [userData, authUserData]);

    return (
        <React.Fragment>
            <Preloader />
            <TopbarSection />
            <NavbarSection />
            <SearchSection />
            <div className="main-container">
                <div className={isSidebarMinimized ? "left-sidebar minimize" : "left-sidebar"}>
                    <div className="inner">
                        <div className="user-profile" onClick={toggleSidebar}>
                            <div className="user-background" style={{ backgroundImage: `url(${userData.userImage})` }}></div>
                            <div className="user-image">
                                <img src={userData.userImage} alt="User" />
                            </div>
                            <div className="user-info">
                                <p className="user-name"> @{userData.username} </p>
                                <p className="user-title">Member Since: {new Date(userData.joinDate).toLocaleDateString()}</p>
                            </div>
                        </div>
                        <div className="user-details">
                            <div className="d-flex align-items-center br-10 pl-4 pt-4">
                                <div className="d-flex br-10 align-items-center justify-content-center flex-shrink-0 ml-n4" style={{ width: '50px', height: '50px' }}>
                                    <i className="fa fa-lg fa-user"></i>
                                </div>
                                <div className="text-light m-0">{userData.name} {userData.surname} </div>
                            </div>
                            <div className="d-flex align-items-center br-10 pl-4">
                                <div className="d-flex br-10 align-items-center justify-content-center flex-shrink-0 ml-n4" style={{ width: '50px', height: '50px' }}>
                                    <i className="fa fa-lg fa-envelope"></i>
                                </div>
                                <div className="text-light m-0">{userData.email} </div>
                            </div>
                        </div>

                        <div className="user-data">
                            <hr className="pl-4" style={{ border: '1px solid white', margin: '20px 8px' }} />
                            <div className="d-flex user-profile-button ml-2 mr-2 p-2 align-items-center br-10">
                                <div className="d-flex br-10 align-items-center justify-content-center flex-shrink-0 ml-n3" style={{ width: '50px', height: '25px' }}>
                                    <i class="fa-solid fa-bag-shopping"></i>
                                </div>
                                {isUserProfileAuth && (
                                    <div className="text-light m-0 ml-n2"> My Products </div>
                                )}
                                {!isUserProfileAuth && (
                                    <div className="text-light m-0 ml-n2"> {userData.surname}'s Products </div>
                                )}
                            </div>
                            {isUserProfileAuth && (
                                <>
                                    <div className="d-flex user-profile-button mt-2 ml-2 mr-2 p-2 align-items-center br-10" onClick={() => setActiveSection("updateProfile")}>
                                        <div className="d-flex br-10 align-items-center justify-content-center flex-shrink-0 ml-n3" style={{ width: '50px', height: '25px' }}>
                                            <i class="fa-solid fa-pen-to-square"></i>
                                        </div>
                                        <div className="text-light m-0 ml-n2"> Update Profile </div>
                                    </div>
                                    <div className="d-flex user-profile-button mt-2 ml-2 mr-2 p-2 align-items-center br-10">
                                        <div className="d-flex br-10 align-items-center justify-content-center flex-shrink-0 ml-n3" style={{ width: '50px', height: '25px' }}>
                                            <i class="fa-solid fa-envelope-circle-check"></i>
                                        </div>
                                        <div className="text-light m-0 ml-n2"> Change Email </div>
                                    </div>
                                    <div className="d-flex user-profile-button mt-2 ml-2 mr-2 p-2 align-items-center br-10">
                                        <div className="d-flex br-10 align-items-center justify-content-center flex-shrink-0 ml-n3" style={{ width: '50px', height: '25px' }}>
                                            <i class="fa-solid fa-shield-halved"></i>
                                        </div>
                                        <div className="text-light m-0 ml-n2"> Reset Password </div>
                                    </div>
                                </>

                            )}

                        </div>
                    </div>
                    <div className="toggle-button" onClick={toggleSidebar}>
                        {!isSidebarMinimized && (<i className="fa-solid fa-arrow-left"> </i>)}
                        {isSidebarMinimized && (<i className="fa-solid fa-arrow-right"> </i>)}
                    </div>
                </div>
                <div className="right-sidebar">
                    {activeSection === "updateProfile" && (
                        <div className="update-profile-section">
                            {/* Add the form or component you want to display here */}
                            <h1>Update Profile</h1>
                            {/* More update profile content goes here */}
                        </div>
                    )}
                    {/* Similarly, add conditions for other sections if needed */}
                </div>
            </div>
            <FooterSection />
            <BackToTopButton />
        </React.Fragment>
    );
};

export default UserProfile;
