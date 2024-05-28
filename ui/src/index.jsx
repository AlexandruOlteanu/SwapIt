import React from 'react';
import ReactDOM from 'react-dom/client';
import SwapIt from './SwapIt';
import reportWebVitals from './reportWebVitals';

const SwapItApp = ReactDOM.createRoot(document.getElementById('SwapIt'));
SwapItApp.render(
    <React.StrictMode>
        <SwapIt />
    </React.StrictMode>
);

reportWebVitals();
