import React, { useEffect, useState, lazy } from 'react';
import { useParams } from 'react-router-dom';

import Preloader from '../js/Preloader';
import ApiBackendService from '../apiBackend/ApiBackendService';
import '../scss/UserProfile.scss';

const TopbarSection = lazy(() => import('../sections/TopbarSection'));
const NavbarSection = lazy(() => import('../sections/NavbarSection'));
const SearchSection = lazy(() => import('../sections/SearchSection'));
const FooterSection = lazy(() => import('../sections/FooterSection'));
const BackToTopButton = lazy(() => import('../js/BackToTopButton'));


const UserProfile = () => {
    const [isSidebarMinimized, setIsSidebarMinimized] = useState(false);

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
    const [sidebarMinimized, setSidebarMinimized] = useState(false);

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
                    </div>
                    <div className="toggle-button" onClick={toggleSidebar}>
                        {!isSidebarMinimized && (<i className="fa-solid fa-arrow-left"> </i>)}
                        {isSidebarMinimized && (<i className="fa-solid fa-arrow-right"> </i>)}
                    </div>
                </div>
            </div>
            <FooterSection />
            <BackToTopButton />
        </React.Fragment>
    );
};

export default UserProfile;
