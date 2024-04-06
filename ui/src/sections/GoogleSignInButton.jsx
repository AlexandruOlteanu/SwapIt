import React, { useEffect } from 'react';

const GoogleSignInButton = () => {
    useEffect(() => {
        // Check if there are error parameters in the URL
        const urlParams = new URLSearchParams(window.location.search);
        const error = urlParams.get('error');
        const errorDescription = urlParams.get('error_description');

        if (error) {
            console.error('OAuth2 Error:', error, errorDescription);
            // Here you can handle the error, e.g., display an error message to the user
        }
    }, []);

    const handleSignIn = () => {
        // Redirect the user to initiate the OAuth2 flow
        window.location.href = 'http://localhost:8001/oauth2/authorization/google';
    };

    return (
        <button onClick={handleSignIn}>Sign In with Google</button>
    );
};

export default GoogleSignInButton;
