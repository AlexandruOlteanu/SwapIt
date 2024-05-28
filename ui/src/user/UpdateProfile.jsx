import React, { useState } from 'react';
import ApiBackendService from '../apiBackend/ApiBackendService';

const UpdateProfile = ({ userData, setUserData}) => {
    const [modifiedUserData, setModifiedUserData] = useState({ ...userData });
    const [error, setError] = useState('');
    const [success, setSuccess] = useState('');

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
            setError('');
            await ApiBackendService.updateBasicUserDetails({}, data);
            setSuccess('Successfully Updated Profile!');
            setUserData(modifiedUserData);
        } catch (error) {
            console.error('Failed to update profile: ', error.message);
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
                            <h4 className="text-light">Profile Settings</h4>
                        </div>
                        <div className="row mt-2">
                            <div className="col-md-6">
                                <label className="labels">Name</label>
                                <input type="text" className="form-control" value={modifiedUserData.name} name="name" onChange={handleChange} />
                            </div>
                            <div className="col-md-6 mb-2">
                                <label className="labels">Surname</label>
                                <input type="text" className="form-control" value={modifiedUserData.surname} name="surname" onChange={handleChange} />
                            </div>
                        </div>
                        <div className="row mt-2">
                            <div className="col-md-12 mb-2">
                                <label className="labels">Phone Number</label>
                                <input type="text" className="form-control" value={modifiedUserData.phoneNumber} name="phoneNumber" onChange={handleChange} />
                            </div>
                            <div className="col-md-12 mb-2">
                                <label className="labels">Country</label>
                                <input type="text" className="form-control" value={modifiedUserData.country} name="country" onChange={handleChange} />
                            </div>
                            <div className="col-md-12 mb-2">
                                <label className="labels">State / Region</label>
                                <input type="text" className="form-control" value={modifiedUserData.stateRegion} name="stateRegion" onChange={handleChange} />
                            </div>
                            <div className="col-md-12">
                                <label className="labels">Address</label>
                                <input type="text" className="form-control" value={modifiedUserData.address} name="address" onChange={handleChange} />
                            </div>
                        </div>
                        <div className="error-message">{error}</div>
                        <div className="success-message">{success}</div>
                        <div className="mt-4">
                            <button className="btn btn-primary profile-button" type="button" onClick={handleUserProfileUpdateSubmit}>
                                Save Profile
                            </button>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    );
};

export default UpdateProfile;
