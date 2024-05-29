import React, { useEffect, useState, lazy } from 'react';
import { useParams } from 'react-router-dom';
import Preloader from '../js/Preloader';
import ApiBackendService from '../apiBackend/ApiBackendService';
import '../scss/UserProfile.scss';
import '../css/UserProfile.css';
import Common from '../Common';
import BanUserDialog from './BanUserDialog';
import RemoveUserBanDialog from './RemoveUserBanDialog';
import UserProducts from '../search/UserProducts';
import UserFavoriteProducts from '../search/UserFavoriteProducts';
import ChangeEmail from './ChangeEmail';
import ChangePassword from './ChangePassword.jsx';
import ChangeUsername from './ChangeUsername';
import UpdateProfile from './UpdateProfile';
import UserImage from './UserImage';
import UserDetails from './UserDetails';
import UserProfileButtons from './UserProfileButtons';
import WebsiteActionsLog from './WebsiteActionsLog.jsx';

const TopbarSection = lazy(() => import('../sections/TopbarSection'));
const NavbarSection = lazy(() => import('../sections/NavbarSection'));
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
        userRole: '',
        address: '',
        phoneNumber: '',
        country: '',
        stateRegion: ''
    });
    const [isSidebarMinimized, setIsSidebarMinimized] = useState(false);
    const [isUserProfileAuth, setIsUserProfileAuth] = useState(false);
    const [isOauth2User, setIsOauth2User] = useState(false);
    const [isAdmin, setIsAdmin] = useState(false);
    const [isTemporaryBanned, setIsTemporaryBanned] = useState(false);
    const [isPermanentBanned, setIsPermanentBanned] = useState(false);
    const [banExpiryDate, setBanExpiryDate] = useState('');
    const [activeSection, setActiveSection] = useState("updateProfile");
    const [isRightFavoriteProducts, setIsRightFavoriteProducts] = useState(false);
    const [isBanDialogVisible, setIsBanDialogVisible] = useState(false);
    const [isRemoveBanDialogVisible, setIsRemoveBanDialogVisible] = useState(false);

    useEffect(() => {
        const fetchData = async () => {
            let userId = -1, authUserId = -2;
            let authUserRole = '';
            if (username) {
                try {
                    const userDataResponse = await ApiBackendService.getUserDetailsByUsername({ username });
                    setUserData(userDataResponse);
                    userId = userDataResponse.userId;
                } catch (error) {
                    console.error('Failed to fetch user details:', error);
                }
            }
            const isLoggedIn = Common.isLoggedIn();
            if (isLoggedIn) {
                try {
                    const authUserData = await ApiBackendService.getAuthenticatedUserDetails({});
                    authUserId = authUserData.userId;
                    authUserRole = authUserData.userRole;
                } catch (error) {
                    console.error('Failed to fetch auth user details:', error);
                }
            }
            const accountStatusResponse = await ApiBackendService.getUserAccountStatus({ userId: userId });
            setIsTemporaryBanned(accountStatusResponse.userStatus === "TEMPORARY_BANNED");
            setIsPermanentBanned(accountStatusResponse.userStatus === "PERMANENT_BANNED");
            if (accountStatusResponse.userStatus === "TEMPORARY_BANNED") {
                setBanExpiryDate(Common.formatDate(accountStatusResponse.banExpiryTime));
            }
            setIsUserProfileAuth(userId === authUserId);
            setIsOauth2User(authUserRole === "OAUTH2_USER");
            setIsAdmin(authUserRole === "ADMINISTRATOR");
        }
        fetchData();
    }, [username, isTemporaryBanned, isPermanentBanned]);

    const handleLogout = () => {
        ApiBackendService.logout({});
        localStorage.clear();
        window.location.href = "/";
    };

    const toggleSidebar = () => {
        setIsSidebarMinimized(!isSidebarMinimized);
    };

    const confirmBanUser = async (banDays) => {
        const payload = {
            userId: userData.userId,
            ...(banDays !== -1 && { banDaysDuration: banDays })
        };
        await ApiBackendService.banUser(payload);
        if (banDays === -1) setIsPermanentBanned(true);
        else setIsTemporaryBanned(true);
        setIsBanDialogVisible(false);
    };

    const confirmRemoveBanUser = async () => {
        await ApiBackendService.removeUserBan({ userId: userData.userId });
        setIsPermanentBanned(false);
        setIsTemporaryBanned(false);
        setIsRemoveBanDialogVisible(false);
    };

    return (
        <React.Fragment>
            <Preloader />
            <TopbarSection />
            <NavbarSection />
            <div className="main-container">
                <div className={isSidebarMinimized ? "left-sidebar minimize" : "left-sidebar"}>
                    <div className="inner">
                        <div className="user-profile">
                            <div className="user-background" style={{ backgroundImage: `url(${userData.userImage})` }}></div>
                            <UserImage userData={userData} setUserData={setUserData} isUserProfileAuth={isUserProfileAuth} />
                            <div className="user-info">
                                <p className="user-name">@{userData.username}</p>
                                <p className="user-title">Member Since: {new Date(userData.joinDate).toLocaleDateString()}</p>
                            </div>
                        </div>
                        <UserDetails userData={userData} isTemporaryBanned={isTemporaryBanned} isPermanentBanned={isPermanentBanned} banExpiryDate={banExpiryDate} />
                        <UserProfileButtons
                            userData={userData}
                            isUserProfileAuth={isUserProfileAuth}
                            isAdmin={isAdmin}
                            isTemporaryBanned={isTemporaryBanned}
                            isPermanentBanned={isPermanentBanned}
                            setActiveSection={setActiveSection}
                            setIsRightFavoriteProducts={setIsRightFavoriteProducts}
                            handleLogout={handleLogout}
                            setIsBanDialogVisible={setIsBanDialogVisible}
                            setIsRemoveBanDialogVisible={setIsRemoveBanDialogVisible}
                            isOauth2User={isOauth2User}
                        />
                    </div>
                    <div className="toggle-button" onClick={toggleSidebar}>
                        {!isSidebarMinimized && (<i className="fa-solid fa-arrow-left"></i>)}
                        {isSidebarMinimized && (<i className="fa-solid fa-arrow-right"></i>)}
                    </div>
                </div>
                <div className="right-sidebar" style={{ display: 'flex', width: isSidebarMinimized ? '88%' : '75%' }}>
                    {activeSection === "updateProfile" && isUserProfileAuth && (
                        <UpdateProfile userData={userData} setUserData={setUserData} />
                    )}
                    {activeSection === "changeEmail" && !isOauth2User && isUserProfileAuth && (
                        <ChangeEmail
                            userData={userData}
                            setUserData={setUserData}
                        />
                    )}
                    {activeSection === "changeUsername" && isUserProfileAuth && (
                        <ChangeUsername
                            userData={userData}
                            setUserData={setUserData}
                        />
                    )}
                    {activeSection === "changePassword" && isUserProfileAuth && (
                        <ChangePassword />
                    )}
                    {(!isAdmin || !isUserProfileAuth) && (
                        <>
                            <div className='ml-5' style={{ flex: 1, overflow: 'auto', width: '100%' }}>
                                {!isRightFavoriteProducts && (
                                    <>
                                        {isUserProfileAuth && (
                                            <h2 className="text-light" style={{ paddingTop: '20px', paddingLeft: '32px', paddingBottom: '10px' }}> My Products</h2>
                                        )}
                                        {!isUserProfileAuth && (
                                            <h2 className="text-light" style={{ paddingTop: '20px', paddingLeft: '32px', paddingBottom: '10px' }}> {userData.surname}'s Products</h2>
                                        )}
                                        {((isUserProfileAuth && isSidebarMinimized) || !isUserProfileAuth) && (
                                            <UserProducts userId={userData.userId} surname={userData.surname} columnItems={4} totalItems={9} />
                                        )}
                                        {(isUserProfileAuth && !isSidebarMinimized) && (
                                            <UserProducts userId={userData.userId} surname={userData.surname} columnItems={6} totalItems={6} isUserProfileAuth={isUserProfileAuth} />
                                        )}
                                    </>
                                )}
                                {isRightFavoriteProducts && (
                                    <>
                                        <h2 className="text-light" style={{ paddingTop: '20px', paddingLeft: '32px', paddingBottom: '10px' }}> Favourite Products</h2>
                                        {((isUserProfileAuth && isSidebarMinimized) || !isUserProfileAuth) && (
                                            <UserFavoriteProducts userId={userData.userId} columnItems={4} totalItems={9} />
                                        )}
                                        {(isUserProfileAuth && !isSidebarMinimized) && (
                                            <UserFavoriteProducts userId={userData.userId} columnItems={6} totalItems={6} />
                                        )}
                                    </>
                                )}
                            </div>
                        </>
                    )}
                    {(isAdmin && isUserProfileAuth) && (
                        
                        <div className='ml-5' style={{ flex: 1, overflow: 'auto', width: '100%' }}>
                            <h2 className="text-light" style={{ paddingTop: '20px', paddingLeft: '32px', paddingBottom: '10px' }}> Website Actions Log </h2>
                            <WebsiteActionsLog columnItems={12} totalItems={4} />
                        </div>
                    )}

                </div>
            </div>
            <BanUserDialog
                isVisible={isBanDialogVisible}
                onCancel={() => setIsBanDialogVisible(false)}
                onConfirm={confirmBanUser}
            />
            <RemoveUserBanDialog
                isVisible={isRemoveBanDialogVisible}
                onCancel={() => setIsRemoveBanDialogVisible(false)}
                onConfirm={confirmRemoveBanUser}
            />
            <FooterSection />
            <BackToTopButton />
        </React.Fragment>
    );
};

export default UserProfile;
