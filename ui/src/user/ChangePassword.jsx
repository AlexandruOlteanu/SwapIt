import React, { useState } from 'react';
import ApiBackendService from '../apiBackend/ApiBackendService';

const ChangePassword = () => {
    const [password, setPassword] = useState('');
    const [newPassword, setNewPassword] = useState('');
    const [repeatNewPassword, setRepeatNewPassword] = useState('');
    const [error, setError] = useState('');
    const [success, setSuccess] = useState('');

    const handleChangePassword = async (e) => {
        e.preventDefault();
        if (newPassword !== repeatNewPassword) {
            setError('Passwords don\'t match');
            return;
        }
        const data = {
            password: password,
            newPassword: newPassword
        };
        try {
            setError('');
            await ApiBackendService.passwordReset({}, data);
            setSuccess('Successfully Changed Your Password!');
        } catch (error) {
            console.error('Failed change password:', error.message);
            setError(error.message);
            setSuccess('');
        }
    };

    return (
        <div className="container rounded mt-5 ml-5 mb-5" style={{ flex: '1 -1 0%', overflow: 'auto', width: '30%' }}>
            <div className="row" style={{ height: '100%' }}>
                <div className="border-right">
                    <div className="p-3 py-5">
                        <div className="d-flex justify-content-between align-items-center mb-3">
                            <h4 className="text-light">Reset Password</h4>
                        </div>
                        <div className="row mt-3">
                            <div className="col-md-12 mb-2">
                                <label className="labels">Old Password</label>
                                <input type="password" className="form-control" onChange={(e) => setPassword(e.target.value)} />
                            </div>
                            <div className="col-md-12 mb-2">
                                <label className="labels">New Password</label>
                                <input type="password" className="form-control" onChange={(e) => setNewPassword(e.target.value)} />
                            </div>
                            <div className="col-md-12">
                                <label className="labels">Repeat New Password</label>
                                <input type="password" className="form-control" onChange={(e) => setRepeatNewPassword(e.target.value)} />
                            </div>
                        </div>
                        <div className="error-message">{error}</div>
                        <div className="success-message">{success}</div>
                        <div className="mt-4">
                            <button className="btn btn-primary profile-button" type="button" onClick={handleChangePassword}>
                                Reset Password
                            </button>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    );
};

export default ChangePassword;
