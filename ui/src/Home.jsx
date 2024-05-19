import React, { lazy } from 'react';

import Preloader from './js/Preloader'
import CategoriesMenu from './sections/CategoriesMenu';
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
            <CategoriesMenu />

            <ContactSection title="Contact Us" />

            <FooterSection />

            <BackToTopButton />

        </React.Fragment>
    );
};

export default Home;