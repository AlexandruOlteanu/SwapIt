import React, { lazy } from 'react';
import { BrowserRouter as Router, Route, Routes } from 'react-router-dom';
import AccessOnlyIfNotLoggedIn from './AccessOnlyIfNotLoggedIn';
import AuthRedirectHandler from './AuthRedirectHandler';

import './css/SwapIt.css';

const Home = lazy(() => import('./Home'));
const Authentication = lazy(() => import('./Authentication'));
const Contact = lazy(() => import('./Contact'));
const SoftwareDevelopment = lazy(() => import('./services/SoftwareDevelopment'));
const BusinessNamesSlogans = lazy(() => import('./services/BusinessNamesSlogans'));
const FacebookAds = lazy(() => import('./services/FacebookAds'));
const Copywriting = lazy(() => import('./services/Copywriting'));
const SeoEcommerce = lazy(() => import('./services/SeoEcommerce'));
const GraphicDesign = lazy(() => import('./services/GraphicDesign'));
const InstagramGrowth = lazy(() => import('./services/InstagramGrowth'));
const BusinessPlans = lazy(() => import('./services/BusinessPlans'));
const GoogleAds = lazy(() => import('./services/GoogleAds'));
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
                    <Route path="/contact" element={<Contact />} />
                    <Route path="/services/software-development" element={<SoftwareDevelopment />} />
                    <Route path="/services/business-slogans" element={<BusinessNamesSlogans />} />
                    <Route path="/services/facebook-ads" element={<FacebookAds />} />
                    <Route path="/services/copywriting" element={<Copywriting />} />
                    <Route path="/services/seo-ecommerce" element={<SeoEcommerce />} />
                    <Route path="/services/graphic-design" element={<GraphicDesign />} />
                    <Route path="/services/instagram-growth" element={<InstagramGrowth />} />
                    <Route path="/services/business-plans" element={<BusinessPlans />} />
                    <Route path="/services/google-ads" element={<GoogleAds />} />
                    <Route path="/info/privacy-policy" element={<PrivacyPolicy />} />
                    <Route path="/info/terms-and-conditions" element={<TermsConditions />} />
                </Routes>
            </div>
        </Router>
    );
}

export default SwapIt;
