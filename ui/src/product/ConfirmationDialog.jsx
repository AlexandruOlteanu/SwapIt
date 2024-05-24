import React from 'react';
import '../css/ConfirmationDialog.css';

const ConfirmationDialog = ({ isVisible, onCancel, onConfirm }) => {
    if (!isVisible) return null;

    return (
        <div className="confirmation-dialog-overlay">
            <div className="confirmation-dialog">
                <h2>Confirm Deletion</h2>
                <p>This action is irreversible. Are you sure you want to delete this product?</p>
                <div className="dialog-buttons">
                    <button className="cancel-button" onClick={onCancel}>Cancel</button>
                    <button className="confirm-button" onClick={onConfirm}>Yes, I want to delete this product</button>
                </div>
            </div>
        </div>
    );
};

export default ConfirmationDialog;
