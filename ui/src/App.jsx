import React, { lazy, Suspense } from 'react';
import { BrowserRouter as Router, Route, Routes } from 'react-router-dom';
import './css/App.css';

const Home = lazy(() => import('./Home'));
const About = lazy(() => import('./About'));
const WorflowSystem = lazy(() => import('./WorkflowSystem'));
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

function App() {
  return (
    <Router>
      <div className="App">
        <Routes>
          <Route path="/" element={<Suspense fallback={<div />}><Home /></Suspense>} />
          <Route path="/about" element={<Suspense fallback={<div />}><About /></Suspense>} />
          <Route path="/workflow-system" element={<Suspense fallback={<div />}><WorflowSystem /></Suspense>} />
          <Route path="/contact" element={<Suspense fallback={<div />}><Contact /></Suspense>} />
          <Route path="/services/software-development" element={<Suspense fallback={<div />}><SoftwareDevelopment /></Suspense>} />
          <Route path="/services/business-slogans" element={<Suspense fallback={<div />}><BusinessNamesSlogans /></Suspense>} />
          <Route path="/services/facebook-ads" element={<Suspense fallback={<div />}><FacebookAds /></Suspense>} />
          <Route path="/services/copywriting" element={<Suspense fallback={<div />}><Copywriting /></Suspense>} />
          <Route path="/services/seo-ecommerce" element={<Suspense fallback={<div />}><SeoEcommerce /></Suspense>} />
          <Route path="/services/graphic-design" element={<Suspense fallback={<div />}><GraphicDesign /></Suspense>} />
          <Route path="/services/instagram-growth" element={<Suspense fallback={<div />}><InstagramGrowth /></Suspense>} />
          <Route path="/services/business-plans" element={<Suspense fallback={<div />}><BusinessPlans /></Suspense>} />
          <Route path="/services/google-ads" element={<Suspense fallback={<div />}><GoogleAds /></Suspense>} />
          <Route path="/info/privacy-policy" element={<Suspense fallback={<div />}><PrivacyPolicy /></Suspense>} />
          <Route path="/info/terms-and-conditions" element={<Suspense fallback={<div />}><TermsConditions /></Suspense>} />
        </Routes>
      </div>
    </Router>
);
}

export default App;
