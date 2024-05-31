import React from 'react';

const SuccessPasswordReset = ({ onSwitchForm }) => (
    <div className="form">
        <div className="form-content">
            <header>Successfully Reset Password</header>
            <div className="form-link">
                <span>You can now go ahead and <a href="#" className="link login-link" onClick={() => onSwitchForm('login')}>Login</a></span>
            </div>
        </div>
    </div>
);

export default SuccessPasswordReset;
