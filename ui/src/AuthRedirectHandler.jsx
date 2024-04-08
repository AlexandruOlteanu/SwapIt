import React, { useEffect, useState } from 'react';
import ApiBackendService from './apiBackend/ApiBackendService';


const AuthRedirectHandler = () => {

    useEffect(() => {
        const fetchAndStoreUserRole = async () => {
            try {
                const response = await ApiBackendService.getAuthenticatedUserDetails({}, {});
                localStorage.setItem('isLoggedIn', 'true');
                localStorage.setItem('userRole', response.userRole);
                console.log(response);
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
