import React, { useState } from 'react';
import ApiBackendService from '../apiBackend/ApiBackendService';
import GoogleLogo from '../img/google.png';


const LoginForm = ({ onSwitchForm, onOauth2Login }) => {
    const [username, setUsername] = useState('');
    const [password, setPassword] = useState('');
    const [loginError, setLoginError] = useState('');

    const handleLogin = async (e) => {
        e.preventDefault();
        if (!username || !password) {
            setLoginError('All fields are required.');
            return;
        }
        try {
            const response = await ApiBackendService.login({}, { username, password });
            localStorage.setItem(process.env.REACT_APP_JWT_TOKEN, response.jwtToken);
            window.location.href = "/user/authHandler";
        } catch (error) {
            console.error('Login failed', error.message);
            setLoginError(error.message);
        }
    };

    return (
        <div className="form">
            <div className="form-content">
                <header>Login</header>
                <form onSubmit={handleLogin}>
                    <div className="field input-field">
                        <input placeholder="Username" className="input" value={username} onChange={(e) => setUsername(e.target.value)} />
                    </div>
                    <div className="field input-field">
                        <input type="password" placeholder="Password" className="input" value={password} onChange={(e) => setPassword(e.target.value)} />
                    </div>
                    {loginError && <div className="error-message">{loginError}</div>}
                    <div className="field button-field">
                        <button>Login</button>
                    </div>
                    <div className="form-link">
                        <a href="#" onClick={() => onSwitchForm('forgotPassword')} className="forgot-pass">Forgot password?</a>
                    </div>
                </form>
                <div className="form-link">
                    <span>Don't have an account? <a href="#" className="link signup-link" onClick={() => onSwitchForm('signUp')}>Signup</a></span>
                </div>
            </div>
            <div className="media-options" onClick={onOauth2Login}>
                <a href="#" className="field google">
                    <img src={GoogleLogo} alt="Google Logo" className="google-img" />
                    <span>Login with Google</span>
                </a>
            </div>
        </div>
    );
};

export default LoginForm;
