import React, { useEffect, useState } from 'react';
import { useNavigate } from 'react-router-dom';
import ApiBackendService from '../apiBackend/ApiBackendService';


const AuthRedirectHandler = () => {

    useEffect(() => {
        const fetchAndStoreUserRole = async () => {
            try {
                await ApiBackendService.getAuthenticatedUserDetails({});
                localStorage.setItem('isLoggedIn', 'true');
                window.location.href = '/';
            } catch (error) {
                console.error('Error fetching user role:', error);
            }
        };

        fetchAndStoreUserRole();
    }, []);

    return (
        <div></div> // Show a loading message or spinner
    );
};

export default AuthRedirectHandler;
