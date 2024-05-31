import React, { useState, useEffect, lazy, Suspense } from 'react';
import '../css/Authentication.css';
import Preloader from '../js/Preloader';
import Logo from '../logo/Logo.webp';
import Common from '../Common';
import LoginForm from './LoginForm';
import SignupForm from './SignupForm';
import ForgotPasswordForm from './ForgotPasswordForm';
import SuccessPasswordReset from './SuccessPasswordReset';

const FooterSection = lazy(() => import('../sections/FooterSection'));
const BackToTopButton = lazy(() => import('../js/BackToTopButton'));

const Authentication = () => {
    const [activeForm, setActiveForm] = useState('login');

    useEffect(() => {
        if (Common.isLoggedIn()) {
            window.location.href = "/";
        }
    }, []);

    const handleOauth2Login = (e) => {
        e.preventDefault();
        window.location.href = process.env.REACT_APP_OAUTH2_URI;
    };

    const renderForm = () => {
        switch (activeForm) {
            case 'login':
                return <LoginForm onSwitchForm={setActiveForm} onOauth2Login={handleOauth2Login} />;
            case 'signUp':
                return <SignupForm onSwitchForm={setActiveForm} />;
            case 'forgotPassword':
                return <ForgotPasswordForm onSwitchForm={setActiveForm} />;
            case 'successPasswordReset':
                return <SuccessPasswordReset onSwitchForm={setActiveForm} />;
            default:
                return <LoginForm onSwitchForm={setActiveForm} onOauth2Login={handleOauth2Login} />;
        }
    };

    return (
        <React.Fragment>
            <Preloader />
            <a href="/" className="navbar-brand pl-3 pt-3" aria-label="Logo">
                <img src={Logo} alt="SwapIt Logo" className="swapit-logo" />
            </a>
            <div className="auth-container forms">
                {renderForm()}
            </div>
            <Suspense fallback={<div>Loading...</div>}>
                <FooterSection />
                <BackToTopButton />
            </Suspense>
        </React.Fragment>
    );
};

export default Authentication;
