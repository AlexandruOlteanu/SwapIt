import React, { useEffect, useState } from 'react';
import '../css/BanUserDialog.css';

const BanUserDialog = ({ isVisible, onCancel, onConfirm }) => {
    const [banDays, setBanDays] = useState(1);
    const [customBanDays, setCustomBanDays] = useState('');

    const handleConfirm = () => {
        const days = banDays === '' ? parseInt(customBanDays, 10) : (banDays === 'Permanent Ban' ? -1 : banDays);
        onConfirm(days);
    };

    useEffect(() => {
        setBanDays(1);
    }, [isVisible]);

    if (!isVisible) return null;

    return (
        <div className="modern-dialog-overlay">
            <div className="modern-dialog">
                <h2>Ban User</h2>
                <div className="ban-days-select">
                    <label>
                        Select number of ban days:
                        {banDays !== '' && (
                            <select
                                value={banDays}
                                onChange={(e) => {
                                    setBanDays(e.target.value === 'custom' ? '' : (e.target.value === 'Permanent Ban' ? e.target.value : parseInt(e.target.value, 10)));
                                    setCustomBanDays('');
                                }}
                            >
                                <option value={1}>1</option>
                                <option value={2}>2</option>
                                <option value={5}>5</option>
                                <option value={10}>10</option>
                                <option value={30}>30</option>
                                <option value={90}>90</option>
                                <option value={'Permanent Ban'}>Permanent Ban</option>
                                <option value="custom">Custom</option>
                            </select>
                        )}
                    </label>
                    {banDays === '' && (
                        <input
                            type="number"
                            placeholder="Enter number of days"
                            value={customBanDays}
                            onChange={(e) => setCustomBanDays(e.target.value)}
                            min="1"
                            className="custom-days-input"
                        />
                    )}
                </div>
                <div className="dialog-buttons">
                    <button className="cancel-button" onClick={onCancel}>Cancel</button>
                    <button className="confirm-button" onClick={handleConfirm}>Confirm Ban</button>
                </div>
            </div>
        </div>
    );
};

export default BanUserDialog;
