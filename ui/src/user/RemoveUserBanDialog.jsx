import React, { useEffect, useState } from 'react';
import '../css/RemoveUserBanDialog.css';

const RemoveUserBanDialog = ({ isVisible, onCancel, onConfirm }) => {
    if (!isVisible) return null;

    return (
        <div className="modern-dialog-overlay">
            <div className="modern-dialog">
                <h2>Remove User Ban</h2>
                <p>Are you sure you want to remove this user's ban?</p>
                <div className="dialog-buttons">
                    <button className="cancel-button" onClick={onCancel}>Cancel</button>
                    <button className="confirm-button" onClick={onConfirm}>Yes</button>
                </div>
            </div>
        </div>
    );
};

export default RemoveUserBanDialog;
