import React from 'react';
import ReactDOM from 'react-dom/client';
import SwapIt from './SwapIt';
import { Provider } from 'react-redux';
import Store from './redux/Store';
import reportWebVitals from './reportWebVitals';

const SwapItApp = ReactDOM.createRoot(document.getElementById('SwapIt'));
SwapItApp.render(
    <React.StrictMode>
        <Provider store={Store}>
            <SwapIt />
        </Provider>,
    </React.StrictMode>
);

// If you want to start measuring performance in your app, pass a function
// to log results (for example: reportWebVitals(console.log))
// or send to an analytics endpoint. Learn more: https://bit.ly/CRA-vitals
reportWebVitals();
