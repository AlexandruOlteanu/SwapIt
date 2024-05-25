import React from 'react';
import '../css/ConfirmationDialog.css';

const ConfirmationDialog = ({ isVisible, onCancel, onConfirm }) => {
    if (!isVisible) return null;

    return (
        <div className="modern-dialog-overlay">
            <div className="modern-dialog">
                <h2>Confirm Deletion</h2>
                <p>This action is irreversible. Are you sure you want to delete this product?</p>
                <div className="dialog-buttons">
                    <button className="cancel-button" onClick={onCancel}>Cancel</button>
                    <button className="confirm-button" onClick={onConfirm}>Yes, Delete It</button>
                </div>
            </div>
        </div>
    );
};

export default ConfirmationDialog;
