import React, { useEffect, useState, useRef, lazy } from 'react';
import { useParams } from 'react-router-dom';
import { storage } from '../firebase-config';
import { ref, uploadBytes, getDownloadURL } from 'firebase/storage';
import { v4 as uuidv4 } from 'uuid';
import Cookies from 'js-cookie';

import Preloader from '../js/Preloader';
import ApiBackendService from '../apiBackend/ApiBackendService';
import '../scss/UserProfile.scss';
import '../css/UserProfile.css'
import CategoriesMenu from '../sections/CategoriesMenu';
import Common from '../Common';
import BanUserDialog from './BanUserDialog';
import RemoveUserBanDialog from './RemoveUserBanDialog';

const TopbarSection = lazy(() => import('../sections/TopbarSection'));
const NavbarSection = lazy(() => import('../sections/NavbarSection'));
const FooterSection = lazy(() => import('../sections/FooterSection'));
const BackToTopButton = lazy(() => import('../js/BackToTopButton'));


const UserProfile = () => {
    const fileInputRef = useRef(null);
    const [password, setPassword] = useState('');
    const [isSidebarMinimized, setIsSidebarMinimized] = useState(false);
    const [isUserProfileAuth, setIsUserProfileAuth] = useState(false);
    const [isOauth2User, setIsOauth2User] = useState(false);
    const [isAdmin, setIsAdmin] = useState(false);
    const [isTemporaryBanned, setIsTemporaryBanned] = useState(false);
    const [isPermanentBanned, setIsPermanentBanned] = useState(false);
    const [banExpiryDate, setBanExpiryDate] = useState('');
    const [activeSection, setActiveSection] = useState("updateProfile");

    const [isBanDialogVisible, setIsBanDialogVisible] = useState(false);
    const [isRemoveBanDialogVisible, setIsRemoveBanDialogVisible] = useState(false);

    //Errors
    const [updateProfileError, setUpdateProfileError] = useState('');
    const [changeEmailError, setChangeEmailError] = useState('');
    const [changePasswordError, setChangePasswordError] = useState('');
    const [changeUsernameError, setChangeUsernameError] = useState('');

    //Success message
    const [updateProfileSuccess, setUpdateProfileSuccess] = useState('');
    const [changeEmailSuccess, setChangeEmailSuccess] = useState('');
    const [changePasswordSuccess, setChangePasswordSuccess] = useState('');
    const [changeUsernameSuccess, setChangeUsernameSuccess] = useState('');

    // Change Email Section
    const [newEmail, setNewEmail] = useState('');
    const [repeatNewEmail, setRepeatNewEmail] = useState('');
    const [emailSecurityCode, setEmailSecurityCode] = useState('');
    const [isEmailSecurityCodeSent, setIsEmailSecurityCodeSent] = useState(false);

    // Change Password Section
    const [newPassword, setNewPassword] = useState('');
    const [repeatNewPassword, setRepeatNewPassword] = useState('');

    // Change Username Section
    const [newUsername, setNewUsername] = useState('');


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
        userRole: '',
        address: '',
        phoneNumber: '',
        country: '',
        stateRegion: ''
    });
    const [modifiedUserData, setModifiedUserData] = useState({
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
    const [authUserData, setAuthUserData] = useState({
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

    useEffect(() => {
        const fetchData = async () => {
            // Fetch user Data
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

            // Fetch Auth user data
            try {
                const authUserData = await ApiBackendService.getAuthenticatedUserDetails({});
                setAuthUserData(authUserData);
                setModifiedUserData(authUserData);
                authUserId = authUserData.userId;
                authUserRole = authUserData.userRole;
            } catch (error) {
                console.error('Failed to fetch auth user details:', error);
            }

            // Fetch user Account Status
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
        sessionStorage.clear();
        Cookies.remove('JSESSIONID');
        window.location.href = "/";
    };

    const changeProfilePicture = async (e) => {
        const file = e.target.files[0];
        const imageRef = ref(storage, `images/${uuidv4()}`);
        uploadBytes(imageRef, file).then((snapshot) => {
            getDownloadURL(snapshot.ref).then((url) => {
                console.log('Image URL:', url);
                const data = {
                    userDetails: {
                        IMAGE: url
                    }
                };
                // Update user image in the backend
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

    const handleChange = (event) => {
        const { name, value } = event.target;
        setModifiedUserData(prevState => ({
            ...prevState,
            [name]: value
        }));
    };

    const handleUserProfileUpdateSubmit = async (e) => {
        e.preventDefault();
        const data = {
            userDetails: {
                NAME: modifiedUserData.name,
                SURNAME: modifiedUserData.surname,
                PHONE_NUMBER: modifiedUserData.phoneNumber,
                COUNTRY: modifiedUserData.country,
                STATE_REGION: modifiedUserData.stateRegion,
                ADDRESS: modifiedUserData.address
            }
        };
        try {
            setUpdateProfileError('');
            await ApiBackendService.updateBasicUserDetails({}, data).then(() => {
                setUserData(prevUserData => ({
                    ...prevUserData,
                    name: modifiedUserData.name,
                    surname: modifiedUserData.surname,
                    phoneNumber: modifiedUserData.phoneNumber,
                    country: modifiedUserData.country,
                    stateRegion: modifiedUserData.stateRegion,
                    address: modifiedUserData.address
                }));
            });
            setUpdateProfileSuccess('Succesfully Updated Profile!')
        } catch (error) {
            console.error('Failed to update profile: ', error.message);
            setUpdateProfileError(error.message);
            setUpdateProfileSuccess('');
        }
        console.log(modifiedUserData);
    };

    const handleChangeEmail = async (e) => {
        e.preventDefault();
        const data = {
            newEmail: newEmail,
            password: password,
            securityCode: null,
            processPhase: "VERIFY_DATA"
        };
        if (!isEmailSecurityCodeSent) {
            try {
                setChangeEmailError('');
                await ApiBackendService.emailReset({}, data);
                setChangeEmailSuccess('Succesfully sent reset code to the new email!');
                setIsEmailSecurityCodeSent(true);
                data.processPhase = "SEND_SECURITY_CODE";
                await ApiBackendService.emailReset({}, data);
            } catch (error) {
                console.error('Failed to send security code: ', error.message);
                setChangeEmailError(error.message);
                setChangeEmailSuccess('');
            }
        } else {
            try {
                setChangeEmailError('');
                data.securityCode = emailSecurityCode;
                data.processPhase = "FINALIZE"
                await ApiBackendService.emailReset({}, data)
                setChangeEmailSuccess('Succesfully Changed Your Email!');
                userData.email = newEmail;
            } catch (error) {
                console.error('Failed change email:', error.message);
                setChangeEmailError(error.message);
                setChangeEmailSuccess('');
            }
        }
    };

    const handleChangePassword = async (e) => {
        e.preventDefault();
        if (newPassword !== repeatNewPassword) {
            setChangePasswordError('Passwords don\'t match');
            return;
        }
        const data = {
            password: password,
            newPassword: newPassword
        };
        try {
            setChangePasswordError('');
            await ApiBackendService.passwordReset({}, data)
            setChangePasswordSuccess('Succesfully Changed Your Password!');
        } catch (error) {
            console.error('Failed change password:', error.message);
            setChangePasswordError(error.message);
            setChangePasswordSuccess('');
        }
    };

    const handleChangeUsername = async (e) => {
        e.preventDefault();
        const data = {
            newUsername: newUsername,
            password: password
        };
        try {
            setChangeUsernameError('');
            await ApiBackendService.usernameReset({}, data)
            setChangeUsernameSuccess('Succesfully Changed Your Username!');
            userData.username = newUsername;
        } catch (error) {
            console.error('Failed change password:', error.message);
            setChangeUsernameError(error.message);
            setChangeUsernameSuccess('');
        }
    };

    const handleUserImageClick = () => {
        if (isUserProfileAuth) {
            fileInputRef.current.click();
        }
    }

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
            <CategoriesMenu />
            <div className="main-container">
                <div className={isSidebarMinimized ? "left-sidebar minimize" : "left-sidebar"}>
                    <div className="inner">
                        <div className="user-profile">
                            <div className="user-background" style={{ backgroundImage: `url(${userData.userImage})` }}></div>
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
                            <div className="user-info">
                                <p className="user-name"> @{userData.username} </p>
                                <p className="user-title">Member Since: {new Date(userData.joinDate).toLocaleDateString()}</p>
                            </div>
                        </div>
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
                                <div className="text-light m-0">{userData.name} {userData.surname} </div>
                            </div>
                            {(!isUserProfileAuth && userData.phoneNumber) && (
                                <div className="d-flex align-items-center br-10 pl-4">
                                    <div className="d-flex br-10 align-items-center justify-content-center flex-shrink-0 ml-n4" style={{ width: '50px', height: '50px' }}>
                                        <i class="fa-solid fa-lg fa-mobile-screen-button"></i>
                                    </div>
                                    <div className="text-light m-0">{userData.phoneNumber} </div>
                                </div>
                            )}
                            <div className="d-flex align-items-center br-10 pl-4">
                                <div className="d-flex br-10 align-items-center justify-content-center flex-shrink-0 ml-n4" style={{ width: '50px', height: '50px' }}>
                                    <i className="fa fa-lg fa-envelope"></i>
                                </div>
                                <div className="text-light m-0">{userData.email} </div>
                            </div>
                        </div>

                        <div className="user-data">
                            <hr className="pl-4" style={{ border: '1px solid white', margin: '20px 8px' }} />
                            {((isUserProfileAuth && !isAdmin) || !isUserProfileAuth) && (
                                <div className="d-flex user-profile-button ml-2 mr-2 p-2 align-items-center br-10">
                                    <div className="d-flex br-10 align-items-center justify-content-center flex-shrink-0 ml-n3" style={{ width: '50px', height: '25px' }}>
                                        <i className="fa-solid fa-bag-shopping"></i>
                                    </div>
                                    {isUserProfileAuth && (
                                        <div className="text-light m-0 ml-n2"> My Products </div>
                                    )}
                                    {!isUserProfileAuth && (
                                        <div className="text-light m-0 ml-n2"> {userData.surname}'s Products </div>
                                    )}
                                </div>
                            )}
                            {(isAdmin && !isTemporaryBanned && !isPermanentBanned && !isUserProfileAuth) && (
                                <div className="d-flex ban-button mt-2 ml-2 mr-2 p-2 align-items-center br-10" style={{ color: 'red' }} onClick={() => setIsBanDialogVisible(true)}>
                                    <div className="d-flex br-10 align-items-center justify-content-center flex-shrink-0 ml-n3" style={{ width: '50px', height: '25px' }}>
                                        <i className="fa-solid fa-ban"></i>
                                    </div>
                                    <div className="m-0 ml-n2"> Ban This User </div>
                                </div>
                            )}
                            {(isAdmin && (isTemporaryBanned || isPermanentBanned)) && (
                                <div className="d-flex ban-button mt-2 ml-2 mr-2 p-2 align-items-center br-10" style={{ color: 'var(--success)' }} onClick={() => setIsRemoveBanDialogVisible(true)}>
                                    <div className="d-flex br-10 align-items-center justify-content-center flex-shrink-0 ml-n3" style={{ width: '50px', height: '25px' }}>
                                        <i className="fa-solid fa-ban"></i>
                                    </div>
                                    <div className="m-0 ml-n2"> Remove User Ban </div>
                                </div>
                            )}

                            {isUserProfileAuth && (
                                <>
                                    {!isAdmin && (
                                        <div className="d-flex user-profile-button mt-2 ml-2 mr-2 p-2 align-items-center br-10" onClick={() => setActiveSection("favouriteProducts")}>
                                            <div className="d-flex br-10 align-items-center justify-content-center flex-shrink-0 ml-n3" style={{ width: '50px', height: '25px' }}>
                                                <i className="fa-solid fa-heart"></i>
                                            </div>
                                            <div className="text-light m-0 ml-n2"> Favourite Products </div>
                                        </div>
                                    )}

                                    <div className="d-flex user-profile-button mt-2 ml-2 mr-2 p-2 align-items-center br-10" onClick={() => setActiveSection("updateProfile")}>
                                        <div className="d-flex br-10 align-items-center justify-content-center flex-shrink-0 ml-n3" style={{ width: '50px', height: '25px' }}>
                                            <i className="fa-solid fa-pen-to-square"></i>
                                        </div>
                                        <div className="text-light m-0 ml-n2"> Update Profile </div>
                                    </div>
                                    {!isOauth2User && (
                                        <div className="d-flex user-profile-button mt-2 ml-2 mr-2 p-2 align-items-center br-10" onClick={() => { setActiveSection("changeEmail"); setIsEmailSecurityCodeSent(false) }}>
                                            <div className="d-flex br-10 align-items-center justify-content-center flex-shrink-0 ml-n3" style={{ width: '50px', height: '25px' }}>
                                                <i className="fa-solid fa-envelope-circle-check"></i>
                                            </div>
                                            <div className="text-light m-0 ml-n2"> Change Email </div>
                                        </div>
                                    )}
                                    <div className="d-flex user-profile-button mt-2 ml-2 mr-2 p-2 align-items-center br-10" onClick={() => setActiveSection("changeUsername")}>
                                        <div className="d-flex br-10 align-items-center justify-content-center flex-shrink-0 ml-n3" style={{ width: '50px', height: '25px' }}>
                                            <i className="fa-solid fa-file-signature"></i>
                                        </div>
                                        <div className="text-light m-0 ml-n2"> Change Username </div>
                                    </div>
                                    <div className="d-flex user-profile-button mt-2 ml-2 mr-2 p-2 align-items-center br-10" onClick={() => setActiveSection("changePassword")}>
                                        <div className="d-flex br-10 align-items-center justify-content-center flex-shrink-0 ml-n3" style={{ width: '50px', height: '25px' }}>
                                            <i className="fa-solid fa-shield-halved"></i>
                                        </div>
                                        <div className="text-light m-0 ml-n2"> Reset Password </div>
                                    </div>
                                    <div className="d-flex user-profile-button mt-2 ml-2 mr-2 p-2 align-items-center br-10" onClick={handleLogout}>
                                        <div className="d-flex br-10 align-items-center justify-content-center flex-shrink-0 ml-n3" style={{ width: '50px', height: '25px' }}>
                                            <i className="fa-solid fa-right-from-bracket"></i>
                                        </div>
                                        <div className="text-light m-0 ml-n2"> Logout </div>
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
                <div className="right-sidebar" style={{ display: 'flex', width: '70%' }}>
                    {activeSection === "updateProfile" && isUserProfileAuth && (
                        <div className="container rounded mt-5 ml-5 mb-5" style={{ flex: '1 -1 0%', overflow: 'auto', width: '30%' }}>
                            <div className="row" style={{ height: '100%' }}>
                                {/* Profile Form */}
                                <div className="border-right">
                                    <div className="p-3 py-5">
                                        <div className="d-flex justify-content-between align-items-center mb-3">
                                            <h4 className="text-light">Profile Settings</h4>
                                        </div>
                                        {/* Form Fields */}
                                        <div className="row mt-2">
                                            <div className="col-md-6">
                                                <label className="labels">Name</label>
                                                <input type="text" className="form-control" placeholder=""
                                                    value={modifiedUserData.name} name="name" onChange={handleChange}
                                                />
                                            </div>
                                            <div className="col-md-6 mb-2">
                                                <label className="labels">Surname</label>
                                                <input type="text" className="form-control" value={modifiedUserData.surname}
                                                    placeholder="" name="surname" onChange={handleChange}
                                                />
                                            </div>
                                        </div>
                                        <div className="row mt-2">
                                            <div className="col-md-12 mb-2">
                                                <label className="labels">Phone Number</label>
                                                <input type="text" className="form-control" placeholder=""
                                                    value={modifiedUserData.phoneNumber} name="phoneNumber" onChange={handleChange}
                                                />
                                            </div>
                                            <div className="col-md-12 mb-2">
                                                <label className="labels">Country</label>
                                                <input type="text" className="form-control" placeholder=""
                                                    value={modifiedUserData.country} name="country" onChange={handleChange}
                                                />
                                            </div>
                                            <div className="col-md-12 mb-2">
                                                <label className="labels">State / Region</label>
                                                <input type="text" className="form-control" placeholder=""
                                                    value={modifiedUserData.stateRegion} name="stateRegion" onChange={handleChange}
                                                />
                                            </div>
                                            <div className="col-md-12">
                                                <label className="labels">Address</label>
                                                <input type="text" className="form-control" placeholder=""
                                                    value={modifiedUserData.address} name="address" onChange={handleChange}
                                                />
                                            </div>
                                        </div>
                                        {updateProfileError && <div className="error-message">{updateProfileError}</div>}
                                        {updateProfileSuccess && <div className="success-message">{updateProfileSuccess}</div>}
                                        <div className="mt-4">
                                            <button className="btn btn-primary profile-button" type="button" onClick={handleUserProfileUpdateSubmit}>
                                                Save Profile
                                            </button>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    )}

                    {activeSection === "changeEmail" && !isOauth2User && isUserProfileAuth && (
                        <div className="container rounded mt-5 ml-5 mb-5" style={{ flex: '1 -1 0%', overflow: 'auto', width: '30%' }}>
                            <div className="row" style={{ height: '100%' }}>
                                {/* Profile Form */}
                                <div className="border-right">
                                    <div className="p-3 py-5">
                                        <div className="d-flex justify-content-between align-items-center mb-3">
                                            <h4 className="text-light">Change Email</h4>
                                        </div>
                                        {/* Form Fields */}
                                        <div className="row mt-3">
                                            <div className="col-md-12 mb-2">
                                                <label className="labels">Current Email</label>
                                                <input type="text" className="form-control" placeholder=""
                                                    value={userData.email} name="name"
                                                    disabled="true"
                                                />
                                            </div>
                                            <div className="col-md-12 mb-2">
                                                <label className="labels">New Email</label>
                                                <input type="email" className="form-control" placeholder="" name="newEmail" onChange={(e) => setNewEmail(e.target.value)}
                                                />
                                            </div>
                                            <div className="col-md-12 mb-2">
                                                <label className="labels">Repeat New Email</label>
                                                <input type="email" className="form-control" placeholder="" name="repeatNewEmail" onChange={(e) => setRepeatNewEmail(e.target.value)} />
                                            </div>
                                            <div className="col-md-12">
                                                <label className="labels">Password</label>
                                                <input type="password" className="form-control" placeholder="" name="password" onChange={(e) => setPassword(e.target.value)}
                                                />
                                            </div>
                                            {isEmailSecurityCodeSent && (
                                                <div className="col-md-12">
                                                    <label className="labels">Security Code</label>
                                                    <input type="text" className="form-control" placeholder="" name="password" onChange={(e) => setEmailSecurityCode(e.target.value)}
                                                    />
                                                </div>
                                            )}
                                        </div>
                                        {changeEmailError && <div className="error-message">{changeEmailError}</div>}
                                        {changeEmailSuccess && <div className="success-message">{changeEmailSuccess}</div>}
                                        <div className="mt-4">
                                            <button className="btn btn-primary profile-button" type="button" onClick={handleChangeEmail}>
                                                {!isEmailSecurityCodeSent ? "Change Email" : "Verify Email"}
                                            </button>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    )}

                    {activeSection === "changeUsername" && isUserProfileAuth && (
                        <div className="container rounded mt-5 ml-5 mb-5" style={{ flex: '1 -1 0%', overflow: 'auto', width: '30%' }}>
                            <div className="row" style={{ height: '100%' }}>
                                {/* Profile Form */}
                                <div className="border-right">
                                    <div className="p-3 py-5">
                                        <div className="d-flex justify-content-between align-items-center mb-3">
                                            <h4 className="text-light">Change Username</h4>
                                        </div>
                                        {/* Form Fields */}
                                        <div className="row mt-3">
                                            <div className="col-md-12 mb-2">
                                                <label className="labels">Current Username</label>
                                                <input type="text" className="form-control" placeholder=""
                                                    value={userData.username} name="username"
                                                    disabled="true"
                                                />
                                            </div>
                                            <div className="col-md-12 mb-2">
                                                <label className="labels">New Username</label>
                                                <input type="email" className="form-control" placeholder="" name="newEmail" onChange={(e) => setNewUsername(e.target.value)}
                                                />
                                            </div>
                                            <div className="col-md-12">
                                                <label className="labels">Password</label>
                                                <input type="password" className="form-control" placeholder="" name="password" onChange={(e) => setPassword(e.target.value)}
                                                />
                                            </div>
                                        </div>
                                        {changeUsernameError && <div className="error-message">{changeUsernameError}</div>}
                                        {changeUsernameSuccess && <div className="success-message">{changeUsernameSuccess}</div>}
                                        <div className="mt-4">
                                            <button className="btn btn-primary profile-button" type="button" onClick={handleChangeUsername}>
                                                Change Username
                                            </button>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    )}

                    {activeSection === "changePassword" && isUserProfileAuth && (
                        <div className="container rounded mt-5 ml-5 mb-5" style={{ flex: '1 -1 0%', overflow: 'auto', width: '30%' }}>
                            <div className="row" style={{ height: '100%' }}>
                                {/* Profile Form */}
                                <div className="border-right">
                                    <div className="p-3 py-5">
                                        <div className="d-flex justify-content-between align-items-center mb-3">
                                            <h4 className="text-light">Reset Password</h4>
                                        </div>
                                        {/* Form Fields */}
                                        <div className="row mt-3">
                                            <div className="col-md-12 mb-2">
                                                <label className="labels">Old Password</label>
                                                <input type="password" className="form-control" placeholder="" name="oldPassword" onChange={(e) => setPassword(e.target.value)} />
                                            </div>
                                            <div className="col-md-12 mb-2">
                                                <label className="labels">New Password</label>
                                                <input type="password" className="form-control" placeholder="" name="newPassword" onChange={(e) => setNewPassword(e.target.value)} />
                                            </div>
                                            <div className="col-md-12">
                                                <label className="labels">Repeat New Password</label>
                                                <input type="password" className="form-control" placeholder="" name="repeatNewPassword" onChange={(e) => setRepeatNewPassword(e.target.value)}
                                                />
                                            </div>
                                        </div>
                                        {changePasswordError && <div className="error-message">{changePasswordError}</div>}
                                        {changePasswordSuccess && <div className="success-message">{changePasswordSuccess}</div>}
                                        <div className="mt-4">
                                            <button className="btn btn-primary profile-button" type="button" onClick={handleChangePassword}>
                                                Reset Password
                                            </button>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    )}
                    <div className='ml-5' style={{ flex: 1, overflow: 'auto', width: '100%' }}>
                        {/* Content of the new column goes here */}
                        <h4>New Column Content</h4>
                        <p>Details and additional settings or information can be displayed here.</p>
                    </div>
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
