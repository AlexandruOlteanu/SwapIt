import React, { useState } from 'react';
import ApiBackendService from '../apiBackend/ApiBackendService';

const ChangeUsername = ({ userData }) => {
    const [newUsername, setNewUsername] = useState('');
    const [password, setPassword] = useState('');
    const [error, setError] = useState('');
    const [success, setSuccess] = useState('');

    const handleChangeUsername = async (e) => {
        e.preventDefault();
        const data = {
            newUsername: newUsername,
            password: password
        };
        try {
            setError('');
            await ApiBackendService.usernameReset({}, data);
            setSuccess('Successfully Changed Your Username!');
            window.location.href = `/users/${newUsername}`;
        } catch (error) {
            console.error('Failed change username:', error.message);
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
                            <h4 className="text-light">Change Username</h4>
                        </div>
                        <div className="row mt-3">
                            <div className="col-md-12 mb-2">
                                <label className="labels">Current Username</label>
                                <input type="text" className="form-control" value={userData.username} disabled />
                            </div>
                            <div className="col-md-12 mb-2">
                                <label className="labels">New Username</label>
                                <input type="text" className="form-control" onChange={(e) => setNewUsername(e.target.value)} />
                            </div>
                            <div className="col-md-12">
                                <label className="labels">Password</label>
                                <input type="password" className="form-control" onChange={(e) => setPassword(e.target.value)} />
                            </div>
                        </div>
                        <div className="error-message">{error}</div>
                        <div className="success-message">{success}</div>
                        <div className="mt-4">
                            <button className="btn btn-primary profile-button" type="button" onClick={handleChangeUsername}>
                                Change Username
                            </button>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    );
};

export default ChangeUsername;
