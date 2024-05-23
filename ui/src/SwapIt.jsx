import React, { lazy } from 'react';
import { BrowserRouter as Router, Route, Routes } from 'react-router-dom';
import AccessOnlyIfNotLoggedIn from './auth/AccessOnlyIfNotLoggedIn';
import AuthRedirectHandler from './auth/AuthRedirectHandler';

import './css/SwapIt.css';
import ProductPage from './product/ProductPage';

const Home = lazy(() => import('./Home'));
const Authentication = lazy(() => import('./auth/Authentication'));
const UserProfile = lazy(() => import('./user/UserProfile'));
const AddProduct = lazy(() => import('./product/AddProduct'));
const Contact = lazy(() => import('./Contact'));
const PrivacyPolicy = lazy(() => import('./info/PrivacyPolicy'));
const TermsConditions = lazy(() => import('./info/TermsConditions'));

function SwapIt() {
    return (
        <Router>
            <div className="SwapIt">
                <Routes>
                    <Route path="/" element={<Home />} />
                    <Route path="/user/authHandler" element={<AuthRedirectHandler />} />
                    <Route path="/user/auth" element={<AccessOnlyIfNotLoggedIn element={Authentication} />} />
                    <Route path="/users/:username" element={<UserProfile />} />
                    <Route path="/product/addNewProduct" element={<AddProduct />} />
                    <Route path='/product/:title/:productId' element={<ProductPage />} />
                    <Route path="/contact" element={<Contact />} />
                    <Route path="/info/privacy-policy" element={<PrivacyPolicy />} />
                    <Route path="/info/terms-and-conditions" element={<TermsConditions />} />
                </Routes>
            </div>
        </Router>
    );
}

export default SwapIt;
