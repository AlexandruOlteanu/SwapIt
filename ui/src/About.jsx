import React, { lazy } from 'react'

import online_reach_icon from './img/online-reach-icon.webp'
import revenue_and_sales_icon from './img/revenue-and-sales-icon.webp'
import memorable_brand_identity_icon from './img/memorable-brand-identity-icon.webp'
import business_strategies_icon from './img/business-strategies-icon.webp'
import software_dev_icon from './img/software-dev-icon.webp'

import AboutAnimation from './animations/AboutAnimation.json'

import Preloader from './js/Preloader'
const TopbarSection = lazy(() => import('./sections/TopbarSection'));
const NavbarSection = lazy(() => import('./sections/NavbarSection'));
const VendorsSection = lazy(() => import('./sections/VendorsSection'));
const FooterSection = lazy(() => import('./sections/FooterSection'));
const BackToTopButton = lazy(() => import('./js/BackToTopButton'));
const Animation = lazy(() => import('./js/Animation'));
const Button = lazy(() => import('./js/Button'));
const Counter = lazy(() => import('./js/Counter'));
const LocationSection = lazy(() => import('./sections/LocationSection'));
const Services = lazy(() => import('./js/Services'));
const WhatDefinesUsSection = lazy(() => import('./sections/WhatDefinesUsSection'));

function About() {

    const yearsOfExperience = 4;
    const happyClients = 300;
    const endToEndCompletedProjects = 7;

    return (
        <React.Fragment>

            <Preloader />

            <TopbarSection />

            <NavbarSection />

            {/* <!-- About Start --> */}
            <div className="container-fluid py-5 mainBanner">
                <div className="container-xl pt-5 pb-3">
                    <h1 className="display-4 text-center mb-5" style={{ color: 'var(--light)' }}> About <span className="text-primary"> SwapIt </span></h1>
                    <div className="row justify-content-center">
                        <div className="col-lg-5 d-flex justify-content-center align-items-center">
                            <Animation
                                classes={"w-85 mb-4"}
                                animationData={AboutAnimation}
                            />
                        </div>
                        <div className="col-lg-6 d-flex justify-content-center align-items-center">
                            <p id="headlineDesktop" className='w-100' style={{ fontSize: '1.4rem', color: 'var(--light)' }}>
                                SwapIt is a dedicated provider of cloud-based virtual office and shared workspace solutions that empower small businesses
                                to streamline operations and drive growth. Our robust business management software simplifies financial, payroll, and inventory
                                management, seamlessly integrating with other popular tools for a comprehensive, user-friendly experience. In addition to our
                                software solutions, we offer a suite of professional services that includes name generation, copywriting, logo design, SEO and
                                online advertising, and marketing. Our commitment to affordability and accessibility means businesses of all sizes can access
                                the tools and resources they need to succeed. Join SwapIt today and start realizing your business's full potential.
                            </p>
                            <p id="headlineMobile" className='w-100 justify-text web-text-size' style={{ color: 'var(--light)' }}>
                                SwapIt is a dedicated provider of cloud-based virtual office and shared workspace solutions that empower small businesses
                                to streamline operations and drive growth. Our robust business management software simplifies financial, payroll, and inventory
                                management, seamlessly integrating with other popular tools for a comprehensive, user-friendly experience. In addition to our
                                software solutions, we offer a suite of professional services that includes name generation, copywriting, logo design, SEO and
                                online advertising, and marketing. Our commitment to affordability and accessibility means businesses of all sizes can access
                                the tools and resources they need to succeed. Join SwapIt today and start realizing your business's full potential.
                            </p>
                        </div>
                    </div>
                </div>
            </div>
            <div className="container-fluid py-5">
                <h1 className="display-4 text-center mb-4">Our results</h1>
                <div className="container-1300 pt-5 pb-3">
                    <div className="row mt-3">
                        <div className="col-lg-4 mb-2">
                            <div className="d-flex align-items-center br-10 bg-light p-4 mb-4" style={{ height: '150px' }}>
                                <div className="d-flex br-10 align-items-center justify-content-center flex-shrink-0 bg-primary ml-n4 mr-4" style={{ width: '100px', height: '100px' }}>
                                    <Counter countTo={yearsOfExperience} plus={true} />
                                </div>
                                <h4 className="m-0">Years of experience</h4>
                            </div>
                        </div>
                        <div className="col-lg-4 mb-2">
                            <div className="d-flex align-items-center br-10 bg-secondary p-4 mb-4" style={{ height: '150px' }}>
                                <div className="d-flex br-10 align-items-center justify-content-center flex-shrink-0 bg-primary ml-n4 mr-4" style={{ width: '100px', height: '100px' }}>
                                    <Counter countTo={happyClients} plus={true} />
                                </div>
                                <h4 className="text-light m-0">Happy Clients</h4>
                            </div>
                        </div>
                        <div className="col-lg-4 mb-2">
                            <div className="d-flex align-items-center br-10 bg-light p-4 mb-4" style={{ height: '150px' }}>
                                <div className="d-flex br-10 align-items-center justify-content-center flex-shrink-0 bg-primary ml-n4 mr-4" style={{ width: '100px', height: '100px' }}>
                                    <Counter countTo={endToEndCompletedProjects} plus={false} />
                                </div>
                                <h4 className="m-0">End To End Completed Projects</h4>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            {/* <!-- About End --> */}
            <div id="our-services" className="our-services section mb-5">
                <div className="container">
                    <div className="row">
                        <div className="col-lg-12">
                            <div className="section-heading  wow fadeInDown" data-wow-duration="1s" data-wow-delay="0.5s">
                                <h5>Our Services</h5>
                                <h3>What Can Be Achieved <span className="text-primary"> Together </span></h3>
                            </div>
                        </div>
                        <div className="col-lg-12">
                            <div className="naccs">
                                <div className="grid">
                                    <div className="row">
                                        <div className="col-lg-12">
                                            <div className="menu">
                                                <div className="first-thumb">
                                                    <div className="thumb">
                                                        <span className="icon"><img src={online_reach_icon} alt="" /></span>
                                                        Online Reach & Visibility
                                                    </div>
                                                </div>
                                                <div>
                                                    <div className="thumb">
                                                        <span className="icon"><img src={revenue_and_sales_icon} alt="" /></span>
                                                        Revenue and Sales
                                                    </div>
                                                </div>
                                                <div>
                                                    <div className="thumb">
                                                        <span className="icon"><img src={memorable_brand_identity_icon} alt="" /></span>
                                                        Memorable Brand Identity
                                                    </div>
                                                </div>
                                                <div>
                                                    <div className="thumb">
                                                        <span className="icon"><img src={business_strategies_icon} alt="" /></span>
                                                        Business Strategies
                                                    </div>
                                                </div>
                                                <div className="last-thumb">
                                                    <div className="thumb">
                                                        <span className="icon"><img src={software_dev_icon} alt="" /></span>
                                                        Software Solutions
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                        <div className="col-lg-12">
                                            <Services />
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>

            <WhatDefinesUsSection />

            <VendorsSection />

            <div className="container-fluid py-5">
                <div className="container-lg pt-5 pb-3">

                    <h1 className="display-4 text-center mb-5">How Can We Get In Touch?</h1>

                    <div className="row justify-content-center">
                        <div className="col-lg-5 d-flex justify-content-center align-items-center">
                            <LocationSection />
                        </div>
                        <div className="col-lg-6-modified d-flex align-items-start" style={{ flexDirection: "column" }}>

                            <p className='mb-4 justify-text web-text-size'>
                                At SwapIt, we understand that every client has unique needs and preferences when it comes to communication. That's why we offer a variety of ways to get in touch with us, including email, phone, live chat, and social media.
                                Once you've decided on the service you're interested in, we can discuss the details further through email or over the phone, depending on what works best for you. Our customer service team is always ready and available to answer any questions or concerns you may have, and we strive to provide timely and helpful responses to all inquiries.                            </p>
                            <Button
                                classes="btn btn-primary py-md-3 px-md-5"
                                href="/contact"
                                type="button"
                                placeholder="Get In Touch"
                            />

                        </div>

                    </div>
                </div>
            </div>

            <FooterSection />

            <BackToTopButton />

        </React.Fragment>
    )
}

export default About
