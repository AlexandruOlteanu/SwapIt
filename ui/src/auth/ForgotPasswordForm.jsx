import React, { useState } from 'react';
import ApiBackendService from '../apiBackend/ApiBackendService';

const ForgotPasswordForm = ({ onSwitchForm }) => {
    const [forgotPasswordEmail, setForgotPasswordEmail] = useState('');
    const [isResetCodeSent, setIsResetCodeSent] = useState(false);
    const [resetCode, setResetCode] = useState('');
    const [newPassword, setNewPassword] = useState('');
    const [confirmNewPassword, setConfirmNewPassword] = useState('');
    const [passwordResetError, setPasswordResetError] = useState('');

    const validateEmail = (email) => {
        const re = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;
        return re.test(email);
    };

    const validatePassword = (password) => {
        const re = /^(?=.*[a-z])(?=.*[A-Z])(?=.*\d)(?=.*[@$!%*?&])[A-Za-z\d@$!%*?&]{8,}$/;
        return re.test(password);
    };

    const handleForgottenPasswordResetCode = async (e) => {
        e.preventDefault();
        if (!forgotPasswordEmail) {
            setPasswordResetError('Email is required.');
            return;
        }
        if (!validateEmail(forgotPasswordEmail)) {
            setPasswordResetError('Invalid email address.');
            return;
        }
        const data = { email: forgotPasswordEmail, newPassword: null, securityCode: null, processPhase: 'VERIFY_DATA' };
        if (!isResetCodeSent) {
            try {
                setPasswordResetError('');
                await ApiBackendService.forgottenPasswordReset({}, data);
                setIsResetCodeSent(true);
                data.processPhase = 'SEND_SECURITY_CODE';
                await ApiBackendService.forgottenPasswordReset({}, data);
            } catch (error) {
                console.error('Failed to send reset code', error.message);
                setPasswordResetError(error.message);
            }
        } else {
            if (!resetCode || !newPassword || !confirmNewPassword) {
                setPasswordResetError('All fields are required.');
                return;
            }
            if (!validatePassword(newPassword)) {
                setPasswordResetError('Password must be at least 8 characters long and include at least one uppercase letter, one lowercase letter, one number, and one special character.');
                return;
            }
            if (newPassword !== confirmNewPassword) {
                setPasswordResetError('Passwords do not match.');
                return;
            }
            try {
                setPasswordResetError('');
                data.securityCode = resetCode;
                data.newPassword = newPassword;
                data.processPhase = 'FINALIZE';
                await ApiBackendService.forgottenPasswordReset({}, data);
                setIsResetCodeSent(false);
                onSwitchForm('successPasswordReset');
            } catch (error) {
                console.error('Failed to reset password', error.message);
                setPasswordResetError(error.message);
            }
        }
    };

    return (
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
                            <span>Already have an account? <a href="#" className="link login-link" onClick={() => onSwitchForm('login')}>Login</a></span>
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
                            <span>Already have an account? <a href="#" className="link login-link" onClick={() => onSwitchForm('login')}>Login</a></span>
                        </div>
                    </form>
                )}
            </div>
        </div>
    );
};

export default ForgotPasswordForm;
