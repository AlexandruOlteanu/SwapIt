import React, { lazy } from 'react';

import Preloader from './js/Preloader'
import RecommendedProducts from './search/RecommendedProducts';
const TopbarSection = lazy(() => import('./sections/TopbarSection'));
const NavbarSection = lazy(() => import('./sections/NavbarSection'));
const ContactSection = lazy(() => import('./sections/ContactSection'));
const FooterSection = lazy(() => import('./sections/FooterSection'));
const BackToTopButton = lazy(() => import('./js/BackToTopButton'));

const Home = () => {
    return (
        <React.Fragment>
            <Preloader />
            <TopbarSection />
            <NavbarSection />
            <RecommendedProducts />

            <ContactSection title="Contact Us" />

            <FooterSection />

            <BackToTopButton />

        </React.Fragment>
    );
};

export default Home;