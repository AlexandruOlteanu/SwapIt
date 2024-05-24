
import React, { useState, useEffect, lazy } from 'react';
import '../css/Authentication.css';
import Preloader from '../js/Preloader';
import Logo from '../logo/Logo.webp';
import GoogleLogo from '../img/google.png';
import ApiBackendService from '../apiBackend/ApiBackendService';
import Common from '../Common';

const FooterSection = lazy(() => import('../sections/FooterSection'));
const BackToTopButton = lazy(() => import('../js/BackToTopButton'));

const Authentication = () => {

    const [activeForm, setActiveForm] = useState('login');  // 'login', 'signUp', 'forgotPassword' or 'succesPasswordReset'


    const [username, setUsername] = useState('');
    const [name, setName] = useState('');
    const [surname, setSurname] = useState('');
    const [userImage, setUserImage] = useState('');
    const [email, setEmail] = useState('');
    const [password, setPassword] = useState('');
    const [confirmPassword, setConfirmPassword] = useState('');
    const [registrationCode, setRegistrationCode] = useState('');
    const [isCodeSent, setIsCodeSent] = useState(false);
    const [loginError, setLoginError] = useState('');
    const [signupError, setSignUpError] = useState('');
    const [forgotPasswordEmail, setForgotPasswordEmail] = useState('');
    const [isResetCodeSent, setIsResetCodeSent] = useState(false);
    const [resetCode, setResetCode] = useState('');
    const [newPassword, setNewPassword] = useState('');
    const [confirmNewPassword, setConfirmNewPassword] = useState('');
    const [passwordResetError, setPasswordResetError] = useState('');

    useEffect(() => {
        if (Common.isLoggedIn()) {
            console.log("Alex");
            window.location.href = "/";
        }
    }, []);

    const handleLogin = async (e) => {
        e.preventDefault();
        const loginData = {
            username: username,
            password: password,
        };
        try {
            const response = await ApiBackendService.login({}, loginData);

            console.log('Login successful', response);
            localStorage.setItem(process.env.REACT_APP_JWT_TOKEN, response.jwtToken);
            window.location.href = "/user/authHandler";
        } catch (error) {
            console.error('Login failed', error.message);
            setLoginError(error.message);
        }
    };

    const handleOauth2Login = async (e) => {
        e.preventDefault();
        window.location.href = process.env.REACT_APP_OAUTH2_URI;
    };

    const handleSignup = async (e) => {
        e.preventDefault();
        if (password !== confirmPassword) {
            setSignUpError("Passwords do not match.");
            return;
        }
        const data = {
            username: username,
            name: name,
            surname: surname,
            email: email,
            password: password,
            userImage: null,
            securityCode: null,
            processPhase: "VERIFY_DATA"
        }
        if (!isCodeSent) {
            try {
                setSignUpError('');
                await ApiBackendService.register({}, data);
                setIsCodeSent(true);
                data.processPhase = "SEND_SECURITY_CODE";
                await ApiBackendService.register({}, data);
                console.log('Security code sent');
            } catch (error) {
                console.error('Failed to send security code', error.message);
                setSignUpError(error.message);
            }
        } else {
            try {
                setUserImage(process.env.REACT_APP_DEFAULT_PROFILE_IMAGE);
                data.userImage = process.env.REACT_APP_DEFAULT_PROFILE_IMAGE;
                data.securityCode = registrationCode;
                data.processPhase = "FINALIZE";
                const registerResponse = await ApiBackendService.register({}, data);
                console.log('Signup verified', registerResponse);
                localStorage.setItem(process.env.REACT_APP_JWT_TOKEN, registerResponse.jwtToken);
                window.location.href = "/user/authHandler";
            } catch (error) {
                console.error('Registration failed', error.message);
                setSignUpError(error.message);
            }
        }
    };

    const handleForgottenPasswordResetCode = async (e) => {
        e.preventDefault();
        const data = {
            email: forgotPasswordEmail,
            newPassword: null,
            securityCode: null,
            processPhase: "VERIFY_DATA"
        }
        if (!isResetCodeSent) {
            try {
                setPasswordResetError('');
                await ApiBackendService.forgottenPasswordReset({}, data);
                setIsResetCodeSent(true);
                data.processPhase = "SEND_SECURITY_CODE";
                await ApiBackendService.forgottenPasswordReset({}, data);
                console.log('Reset code sent');
            } catch (error) {
                console.error('Failed to send reset code', error.message);
                setPasswordResetError(error.message);
            }
        } else {
            if (newPassword !== confirmNewPassword) {
                setPasswordResetError("Passwords do not match.");
                return;
            }
            try {
                setPasswordResetError('');
                data.securityCode = resetCode;
                data.newPassword = newPassword;
                data.processPhase = "FINALIZE";

                await ApiBackendService.forgottenPasswordReset({}, data);
                console.log('Password successfully reset');
                setIsResetCodeSent(false);
                setActiveForm('succesPasswordReset');
            } catch (error) {
                console.error('Failed to reset password', error.message);
                setPasswordResetError(error.message);
            }
        }
        
    };

    const clearForms = (e) => {
        e.preventDefault();
        setUsername('');
        setName('');
        setSurname('');
        setEmail('');
        setPassword('');
        setConfirmPassword('');
        setIsCodeSent('');
        setIsResetCodeSent('')
    };

    const showForgotPasswordForm = (e) => {
        setActiveForm('forgotPassword');
        clearForms(e);
    };

    const showLoginForm = (e) => {
        setActiveForm('login');
        clearForms(e);
    };

    const showSignUpForm = (e) => {
        setActiveForm('signUp');
        clearForms(e);
    };

    return (
        <React.Fragment>
            <Preloader />
            <a href="/" className="navbar-brand pl-3 pt-3" aria-label="Logo">
                <img src={Logo} alt="SwapIt Logo" className="swapit-logo" />
            </a>
            <div className={`auth-container forms`}>
                {activeForm === 'login' && (
                    // Login Form
                    <div className="form">
                        <div className="form-content">
                            <header>Login</header>
                            <form onSubmit={handleLogin}>
                                <div className="field input-field">
                                    <input placeholder="Username" className="input" value={username} onChange={(e) => setUsername(e.target.value)} />
                                </div>

                                <div className="field input-field">
                                    <input type="password" placeholder="Password" className="input" value={password} onChange={(e) => setPassword(e.target.value)} />
                                    <i className='bx bx-hide eye-icon'></i>
                                </div>
                                {loginError && <div className="error-message">{loginError}</div>}
                                <div className="field button-field">
                                    <button>Login</button>
                                </div>
                                <div className="form-link">
                                    <a href="#" onClick={showForgotPasswordForm} className="forgot-pass">Forgot password?</a>
                                </div>
                            </form>

                            <div className="form-link">
                                <span>Don't have an account? <a href="#" className="link signup-link" onClick={showSignUpForm}>Signup</a></span>
                            </div>
                        </div>

                        <div className="media-options" onClick={handleOauth2Login}>
                            <a href="#" className="field google">
                                <img src={GoogleLogo} alt="" className="google-img" />
                                <span>Login with Google</span>
                            </a>
                        </div>
                    </div>
                )}

                {activeForm === 'signUp' && (
                    // SignUp Form
                    <div className="form">
                        <header>Signup</header>
                        <form onSubmit={handleSignup}>
                            {!isCodeSent ? (
                                // Include all initial signup fields
                                <>
                                    <div className="form-content">
                                        <div className="field input-field">
                                            <input placeholder="Username" className="input" value={username} onChange={(e) => setUsername(e.target.value)} />
                                        </div>
                                        <div className="field input-field">
                                            <input placeholder="Name" className="input" value={name} onChange={(e) => setName(e.target.value)} />
                                        </div>
                                        <div className="field input-field">
                                            <input placeholder="Surname" className="input" value={surname} onChange={(e) => setSurname(e.target.value)} />
                                        </div>
                                        <div className="field input-field">
                                            <input type="email" placeholder="Email" className="input" value={email} onChange={(e) => setEmail(e.target.value)} />
                                        </div>
                                        <div className="field input-field">
                                            <input type="password" placeholder="Create password" className="input" value={password} onChange={(e) => setPassword(e.target.value)} />
                                        </div>
                                        <div className="field input-field">
                                            <input type="password" placeholder="Confirm password" className="input" value={confirmPassword} onChange={(e) => setConfirmPassword(e.target.value)} />
                                        </div>
                                        {signupError && <div className="error-message">{signupError}</div>}
                                        <div className="field button-field">
                                            <button>Sign Up</button>
                                        </div>
                                        <div className="form-link">
                                            <span>Already have an account? <a href="#" className="link login-link" onClick={showLoginForm}>Login</a></span>
                                        </div>
                                    </div>
                                    <div className="media-options" onClick={handleOauth2Login}>
                                        <a href="#" className="field google">
                                            <img src={GoogleLogo} alt="" className="google-img" />
                                            <span>Sign Up with Google</span>
                                        </a>
                                    </div>
                                </>
                            ) : (
                                // Verification code input field
                                <>
                                    <div className="field input-field">
                                        <input type="text" placeholder="Enter verification code" className="input" value={registrationCode} onChange={(e) => setRegistrationCode(e.target.value)} />
                                    </div>
                                    {signupError && <div className="error-message">{signupError}</div>}
                                    <div className="field button-field">
                                        <button>Verify and Sign Up</button>
                                    </div>
                                </>
                            )}
                        </form>
                    </div>
                )}


                {activeForm === 'forgotPassword' && (
                    <div className="form">
                        <div className="form-content">
                            <header>Reset Password</header>
                            {!isResetCodeSent ? (
                                <form onSubmit={handleForgottenPasswordResetCode}>
                                    <div className="field input-field">
                                        <input type="email" placeholder="Your email" className="input" value={forgotPasswordEmail} onChange={(e) => setForgotPasswordEmail(e.target.value)} />
                                    </div>
                                    {passwordResetError && <div className="error-message">{passwordResetError}</div>}
                                    <div className="field button-field">
                                        <button type="submit">Send Reset Code</button>
                                    </div>
                                    <div className="form-link">
                                        <span>Already have an account? <a href="#" className="link login-link" onClick={showLoginForm}>Login</a></span>
                                    </div>
                                </form>
                            ) : (
                                <form onSubmit={handleForgottenPasswordResetCode}>
                                    <div className="field input-field">
                                        <input type="text" placeholder="Reset Code" className="input" value={resetCode} onChange={(e) => setResetCode(e.target.value)} />
                                    </div>
                                    <div className="field input-field">
                                        <input type="password" placeholder="New Password" className="input" value={newPassword} onChange={(e) => setNewPassword(e.target.value)} />
                                    </div>
                                    <div className="field input-field">
                                        <input type="password" placeholder="Confirm New Password" className="input" value={confirmNewPassword} onChange={(e) => setConfirmNewPassword(e.target.value)} />
                                    </div>
                                    {passwordResetError && <div className="error-message">{passwordResetError}</div>}
                                    <div className="field button-field">
                                        <button type="submit">Reset Password</button>
                                    </div>
                                    <div className="form-link">
                                        <span>Already have an account? <a href="#" className="link login-link" onClick={showLoginForm}>Login</a></span>
                                    </div>
                                </form>
                            )}
                        </div>
                    </div>
                )}

                {activeForm === 'succesPasswordReset' && (
                    <div className="form">
                        <div className="form-content">
                            <header>Succesfully Reseted Password</header>
                            <div className="form-link">
                                <span>You can now go ahead and  <a href="#" className="link login-link" onClick={showLoginForm}>Login</a></span>
                            </div>
                        </div>
                    </div>
                )}

            </div>
            <FooterSection />
            <BackToTopButton />
        </React.Fragment>
    );
};

export default Authentication;
