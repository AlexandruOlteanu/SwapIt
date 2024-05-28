import React, { useState } from 'react';
import ApiBackendService from '../apiBackend/ApiBackendService';

const ChangeEmail = ({ userData, setUserData }) => {
    const [newEmail, setNewEmail] = useState('');
    const [repeatNewEmail, setRepeatNewEmail] = useState('');
    const [emailSecurityCode, setEmailSecurityCode] = useState('');
    const [isEmailSecurityCodeSent, setIsEmailSecurityCodeSent] = useState(false);
    const [password, setPassword] = useState('');
    const [error, setError] = useState('');
    const [success, setSuccess] = useState('');

    const handleChangeEmail = async (e) => {
        e.preventDefault();
        if (newEmail !== repeatNewEmail) {
            setError('Emails don\'t match');
            return;
        }
        const data = {
            newEmail: newEmail,
            password: password,
            securityCode: null,
            processPhase: "VERIFY_DATA"
        };
        if (!isEmailSecurityCodeSent) {
            try {
                setError('');
                await ApiBackendService.emailReset({}, data);
                setSuccess('Successfully sent reset code to the new email!');
                setIsEmailSecurityCodeSent(true);
                data.processPhase = "SEND_SECURITY_CODE";
                await ApiBackendService.emailReset({}, data);
            } catch (error) {
                console.error('Failed to send security code: ', error.message);
                setError(error.message);
                setSuccess('');
            }
        } else {
            try {
                setError('');
                data.securityCode = emailSecurityCode;
                data.processPhase = "FINALIZE";
                console.log(data);
                await ApiBackendService.emailReset({}, data);
                setSuccess('Successfully Changed Your Email!');
                setIsEmailSecurityCodeSent(false);
                setUserData(prevUserData => ({
                    ...prevUserData,
                    email: newEmail
                }));
            } catch (error) {
                console.error('Failed to change email:', error.message);
                setError(error.message);
                setSuccess('');
            }
        }
    };

    return (
        <div className="container rounded mt-5 ml-5 mb-5" style={{ flex: '1 -1 0%', overflow: 'auto', width: '30%' }}>
            <div className="row" style={{ height: '100%' }}>
                <div className="border-right">
                    <div className="p-3 py-5">
                        <div className="d-flex justify-content-between align-items-center mb-3">
                            <h4 className="text-light">Change Email</h4>
                        </div>
                        <div className="row mt-3">
                            <div className="col-md-12 mb-2">
                                <label className="labels">Current Email</label>
                                <input type="text" className="form-control" value={userData.email} disabled />
                            </div>
                            <div className="col-md-12 mb-2">
                                <label className="labels">New Email</label>
                                <input type="email" className="form-control" onChange={(e) => setNewEmail(e.target.value)} />
                            </div>
                            <div className="col-md-12 mb-2">
                                <label className="labels">Repeat New Email</label>
                                <input type="email" className="form-control" onChange={(e) => setRepeatNewEmail(e.target.value)} />
                            </div>
                            <div className="col-md-12">
                                <label className="labels">Password</label>
                                <input type="password" className="form-control" onChange={(e) => setPassword(e.target.value)} />
                            </div>
                            {isEmailSecurityCodeSent && (
                                <div className="col-md-12">
                                    <label className="labels">Security Code</label>
                                    <input type="text" className="form-control" onChange={(e) => setEmailSecurityCode(e.target.value)} />
                                </div>
                            )}
                        </div>
                        <div className="error-message">{error}</div>
                        <div className="success-message">{success}</div>
                        <div className="mt-4">
                            <button className="btn btn-primary profile-button" type="button" onClick={handleChangeEmail}>
                                {!isEmailSecurityCodeSent ? "Change Email" : "Verify Email"}
                            </button>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    );
};

export default ChangeEmail;
