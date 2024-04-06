import React, { lazy } from 'react'

import './css/Contact.css'

import email_animation from './animations/EmailAnimation.json'

import Preloader from './js/Preloader'
const TopbarSection = lazy(() => import('./sections/TopbarSection'));
const NavbarSection = lazy(() => import('./sections/NavbarSection'));
const Animation = lazy(() => import('./js/Animation'));
const ContactSection = lazy(() => import('./sections/ContactSection'));
const FooterSection = lazy(() => import('./sections/FooterSection'));
const BackToTopButton = lazy(() => import('./js/BackToTopButton'));

function Contact() {
    return (
        <React.Fragment>

            <Preloader />

            <TopbarSection />

            <NavbarSection />

            <div className="container-fluid py-5 mainBanner">
                <div className="container-xl pt-5 pb-3">
                    <h1 className="display-4 text-center mb-5" style={{ color: 'var(--light)' }}> Contact </h1>
                    <div className="row justify-content-center">
                        <div className="col-lg-5 d-flex justify-content-center align-items-center">
                            <Animation
                                classes={"w-75 mb-4"}
                                animationData={email_animation}
                            />
                        </div>
                        <div className="col-lg-5 d-flex justify-content-center align-items-center">
                            <p id="headlineDesktop" className='w-95' style={{ fontSize: '1.5rem', color: 'var(--light)' }}>
                                Thank you for your interest in SwapIt! Fill out the form below with your name, email, and a brief message about your project.
                                Our team will reach out to you within 48 business hours. We take your privacy seriously and will only use your information to help
                                you achieve your business goals. Plus, we offer training and onboarding support to help you get the most out of our software solutions.
                                Don't let anything hold your business back. Contact us today and let SwapIt be your partner in success.
                            </p>
                            <p id="headlineMobile" className='w-95 justify-text web-text-size' style={{ color: 'var(--light)' }}>
                                Thank you for your interest in SwapIt! Fill out the form below with your name, email, and a brief message about your project.
                                Our team will reach out to you within 48 business hours. We take your privacy seriously and will only use your information to help
                                you achieve your business goals. Plus, we offer training and onboarding support to help you get the most out of our software solutions.
                                Don't let anything hold your business back. Contact us today and let SwapIt be your partner in success.
                            </p>
                        </div>

                    </div>
                </div>
            </div>

            <ContactSection title="" />

            <FooterSection />

            <BackToTopButton />

        </React.Fragment>
    )
}

export default Contact