import React, { useState } from 'react';
import ApiBackendService from '../apiBackend/ApiBackendService';

const SignupForm = ({ onSwitchForm }) => {
    const [username, setUsername] = useState('');
    const [name, setName] = useState('');
    const [surname, setSurname] = useState('');
    const [email, setEmail] = useState('');
    const [password, setPassword] = useState('');
    const [confirmPassword, setConfirmPassword] = useState('');
    const [registrationCode, setRegistrationCode] = useState('');
    const [isCodeSent, setIsCodeSent] = useState(false);
    const [signupError, setSignUpError] = useState('');

    const validateEmail = (email) => {
        const re = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;
        return re.test(email);
    };

    const validatePassword = (password) => {
        const re = /^(?=.*[a-z])(?=.*[A-Z])(?=.*\d)(?=.*[@$!%*?&])[A-Za-z\d@$!%*?&]{8,}$/;
        return re.test(password);
    };

    const handleSignup = async (e) => {
        e.preventDefault();
        if (!username || !name || !surname || !email || !password || !confirmPassword) {
            setSignUpError('All fields are required.');
            return;
        }
        if (!validateEmail(email)) {
            setSignUpError('Invalid email address.');
            return;
        }
        if (!validatePassword(password)) {
            setSignUpError('Password must be at least 8 characters long and include at least one uppercase letter, one lowercase letter, one number, and one special character.');
            return;
        }
        if (password !== confirmPassword) {
            setSignUpError('Passwords do not match.');
            return;
        }
        const data = {
            username, name, surname, email, password, userImage: null, securityCode: null, processPhase: 'VERIFY_DATA'
        };
        if (!isCodeSent) {
            try {
                setSignUpError('');
                await ApiBackendService.register({}, data);
                setIsCodeSent(true);
                data.processPhase = 'SEND_SECURITY_CODE';
                await ApiBackendService.register({}, data);
            } catch (error) {
                console.error('Failed to send security code', error.message);
                setSignUpError(error.message);
            }
        } else {
            try {
                data.userImage = process.env.REACT_APP_DEFAULT_PROFILE_IMAGE;
                data.securityCode = registrationCode;
                data.processPhase = 'FINALIZE';
                const registerResponse = await ApiBackendService.register({}, data);
                localStorage.setItem(process.env.REACT_APP_JWT_TOKEN, registerResponse.jwtToken);
                window.location.href = '/user/authHandler';
            } catch (error) {
                console.error('Registration failed', error.message);
                setSignUpError(error.message);
            }
        }
    };

    return (
        <div className="form">
            <header>Signup</header>
            <form onSubmit={handleSignup}>
                {!isCodeSent ? (
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
                                <span>Already have an account? <a href="#" className="link login-link" onClick={() => onSwitchForm('login')}>Login</a></span>
                            </div>
                        </div>
                    </>
                ) : (
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
    );
};

export default SignupForm;
