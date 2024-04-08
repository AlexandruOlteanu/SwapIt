import React, { useState, lazy } from 'react';
import  { Navigate } from 'react-router-dom'
import './css/Authentication.css';
import Preloader from './js/Preloader'
import Logo from './logo/Logo.webp'
import GoogleLogo from './img/google.png'
import ApiBackendService from './apiBackend/ApiBackendService';


const FooterSection = lazy(() => import('./sections/FooterSection'));
const BackToTopButton = lazy(() => import('./js/BackToTopButton'));

const Authentication = () => {
    const [username, setUsername] = useState('');
    const [email, setEmail] = useState('');
    const [password, setPassword] = useState('');
    const [confirmPassword, setConfirmPassword] = useState('');
    const [showSignup, setShowSignup] = useState(false);
    const [isLoggedIn, setIsLoggedIn] = useState(false);
    const [loginError, setLoginError] = useState('');

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

    const handleSignup = (e) => {
        e.preventDefault();
        // Handle signup logic here
    };

    const toggleForm = (e) => {
        e.preventDefault();
        setShowSignup(!showSignup);
    };

    return (
        <React.Fragment>
            {isLoggedIn && <Navigate to="/" />}
            <Preloader />
            <a href="/" className="navbar-brand pl-3 pt-3" aria-label="Logo">
                <img src={Logo} alt="SwapIt Logo" className="swapit-logo" />
            </a>
            <div className={`auth-container forms ${showSignup ? 'show-signup' : ''}`}>
                {/* Login Form */}
                <div className="form login">
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
                            <div className="form-link">
                                <a href="#" className="forgot-pass">Forgot password?</a>
                            </div>

                            <div className="field button-field">
                                <button>Login</button>
                            </div>
                        </form>

                        <div className="form-link">
                            <span>Don't have an account? <a href="#" className="link signup-link" onClick={toggleForm}>Signup</a></span>
                        </div>
                    </div>

                    <div className="media-options" onClick={handleOauth2Login}>
                        <a href="#" className="field google">
                            <img src={GoogleLogo} alt="" className="google-img" />
                            <span>Login with Google</span>
                        </a>
                    </div>
                </div>

                {/* Signup Form */}
                <div className="form signup">
                    <div className="form-content">
                        <header>Signup</header>
                        <form onSubmit={handleSignup}>
                            <div className="field input-field">
                                <input type="email" placeholder="Email" className="input" value={email} onChange={(e) => setEmail(e.target.value)} />
                            </div>

                            <div className="field input-field">
                                <input type="password" placeholder="Create password" className="input" value={password} onChange={(e) => setPassword(e.target.value)} />
                            </div>

                            <div className="field input-field">
                                <input type="password" placeholder="Confirm password" className="input" value={confirmPassword} onChange={(e) => setConfirmPassword(e.target.value)} />
                                <i className='bx bx-hide eye-icon'></i>
                            </div>

                            <div className="field button-field">
                                <button>Signup</button>
                            </div>
                        </form>

                        <div className="form-link">
                            <span>Already have an account? <a href="#" className="link login-link" onClick={toggleForm}>Login</a></span>
                        </div>
                    </div>

                    <div className="media-options">
                        <a href="#" className="field google">
                            <img src={GoogleLogo} alt="" className="google-img" />
                            <span>Signup with Google</span>
                        </a>
                    </div>
                </div>
            </div>
            <FooterSection />

            <BackToTopButton />
        </React.Fragment>
    );
};

export default Authentication;
