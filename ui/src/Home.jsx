import React, { lazy } from 'react';

import HomeWelcomeAnimation from './animations/HomeWelcomeAnimation.json'
import software_img from './img/software-img.webp'
import marketing_img from './img/marketing-img.webp'
import design_img from './img/design-img.webp'
import product_maintenance_img from './img/product_maintenance_img.webp'

import Preloader from './js/Preloader'
import FilebaseUploadComponent from './sections/ImageUploadAndDisplay ';
import FileUpload from './sections/ImageUploadAndDisplay ';
import ImageUploadAndDisplay from './sections/ImageUploadAndDisplay ';
import GoogleSignInButton from './sections/GoogleSignInButton';
const TopbarSection = lazy(() => import('./sections/TopbarSection'));
const NavbarSection = lazy(() => import('./sections/NavbarSection'));
const SearchSection = lazy(() => import('./sections/SearchSection'));
const HorizontalTimeline = lazy(() => import('./sections/HorizontalTimeline'));
const MoveToButton = lazy(() => import('./js/MoveToButton'));
const Animation = lazy(() => import('./js/Animation'));
const Timeline = lazy(() => import('./sections/Timeline'));
const TestimonialSection = lazy(() => import('./sections/TestimonialSection'));
const ContactSection = lazy(() => import('./sections/ContactSection'));
const VendorsSection = lazy(() => import('./sections/VendorsSection'));
const FooterSection = lazy(() => import('./sections/FooterSection'));
const BackToTopButton = lazy(() => import('./js/BackToTopButton'));
const TeamCarousel = lazy(() => import('./js/TeamCarousel'));
const SpecialOffer = lazy(() => import('./js/SpecialOffer'));

const Home = () => {
    return (
        <React.Fragment>
            <Preloader />
            <TopbarSection />
            <NavbarSection />
            {/* <GoogleSignInButton />
            <ImageUploadAndDisplay /> */}
            <SearchSection />

            <HorizontalTimeline
                items={[
                    <>
                        <div className="tl-bg" style={{ backgroundImage: `url(${software_img})` }}></div>
                        <div className="tl-title">
                            <p className="f2">Software Solutions</p>
                        </div>
                        <div className="tl-content">
                            <h1>Innovative Software for Better Business Performance!</h1>
                            <p> We provide customized software solutions to help you achieve your business goals. Our expert team develops smart and efficient software that streamlines your operations, boosts productivity, and aligns with your unique business needs. Let us empower your business to succeed with our cutting-edge software solutions.</p>
                            <MoveToButton
                                classes="btn btn-primary py-md-3 px-md-5 mt-2 move-to-contact"
                                placeholder="Get in Touch"
                                targetId="contact-id"
                            />
                        </div>
                    </>,

                    <>
                        <div className="tl-bg" style={{ backgroundImage: `url(${marketing_img})` }}></div>
                        <div className="tl-title">
                            <p className="f2 heading--sanSerif">Marketing Strategies</p>
                        </div>
                        <div className="tl-content">
                            <h1 className="f3 text--accent ttu">Build a durable and strong brand!</h1>
                            <p>We create marketing strategies that work. Our expert team develops customized plans that build your brand, engage your audience, and drive results. Whether you need help with branding, advertising, content creation, or social media management, we provide the expertise to help you succeed. Let us help you take your marketing to the next level with our innovative strategies.</p>
                            <MoveToButton
                                classes="btn btn-primary py-md-3 px-md-5 mt-2 move-to-contact"
                                placeholder="Get in Touch"
                                targetId="contact-id"
                            />
                        </div>
                    </>,

                    <>
                        <div className="tl-bg" style={{ backgroundImage: `url(${product_maintenance_img})` }}></div>
                        <div className="tl-title">
                            <p className="f2 heading--sanSerif">Product Maintenance</p>
                        </div>
                        <div className="tl-content">
                            <h1 className="f3 text--accent ttu">Maximizing Productivity with Reliable Software Maintenance!</h1>
                            <p>We provide expert software maintenance services to keep your software products running smoothly. Our proactive approach minimizes downtime and reduces the risk of issues, ensuring your business operates at peak performance. With comprehensive solutions, from preventive maintenance to reactive support, we keep your software products optimized and up-to-date. Trust us to be your partner in software maintenance and help your business succeed.</p>
                            <MoveToButton
                                classes="btn btn-primary py-md-3 px-md-5 mt-2 move-to-contact"
                                placeholder="Get in Touch"
                                targetId="contact-id"
                            />
                        </div>
                    </>,

                    <>
                        <div className="tl-bg" style={{ backgroundImage: `url(${design_img})` }}></div>
                        <div className="tl-title">
                            <p className="f2 heading--sanSerif">Professional Design</p>
                        </div>
                        <div className="tl-content">
                            <h1 className="f3 text--accent ttu">Tailored Design for Your Unique Business Vision!</h1>
                            <p>We provide expert professional design services, including graphic designing, to help your business stand out. Our creative and strategic team develops custom solutions that elevate your brand and engage your audience. From branding to website design, we ensure a professional look across all platforms. Let us help you transform your brand with innovative design solutions that drive success.</p>
                            <MoveToButton
                                classes="btn btn-primary py-md-3 px-md-5 mt-2 move-to-contact"
                                placeholder="Get in Touch"
                                targetId="contact-id"
                            />
                        </div>
                    </>
                ]}
            />

            {/* About section */}
            <div className="container-fluid py-5">
                <div className="container-1300 pt-5 pb-3">
                    <h1 className="display-4 text-center mb-5">Welcome To <span className="text-primary">SwapIt</span></h1>
                    <div className="row justify-content-center">
                        <div className="col-lg-10 d-flex justify-content-center">
                            <Animation
                                classes={"w-85 mb-4"}
                                animationData={HomeWelcomeAnimation}
                            />
                        </div>
                        <p className="web-text-size justify-text w-95">
                            SwapIt offers a one-stop-shop for all small business needs, including a cloud-based virtual office and shared workspace
                            with professional services to streamline operations and help businesses grow. Our powerful business management software
                            allows for easy ads management, business plans, software and more, and integrates with other popular tools. We also
                            offer expert help with design, marketing and advertising. Affordable and accessible to all business sizes, Sign up
                            with SwapIt to achieve your business goals!
                        </p>
                    </div>
                    <div className="row mt-3">
                        <div className="col-lg-4 mb-2">
                            <div className="d-flex align-items-center br-10 bg-light p-4 mb-4" style={{ height: '150px' }}>
                                <div className="d-flex br-10 align-items-center justify-content-center flex-shrink-0 bg-primary ml-n4 mr-4" style={{ width: '100px', height: '100px' }}>
                                    <i className="fa fa-2x fa-headset text-secondary"></i>
                                </div>
                                <h4 className="m-0">24/7 Professional Support</h4>
                            </div>
                        </div>
                        <div className="col-lg-4 mb-2">
                            <div className="d-flex align-items-center br-10 bg-secondary p-4 mb-4" style={{ height: '150px' }}>
                                <div className="d-flex br-10 align-items-center justify-content-center flex-shrink-0 bg-primary ml-n4 mr-4" style={{ width: '100px', height: '100px' }}>
                                    <i className="fa fa-2x fa-chart-simple text-secondary"></i>
                                </div>
                                <h4 className="text-light m-0">Business Solutions of Any Level</h4>
                            </div>
                        </div>
                        <div className="col-lg-4 mb-2">
                            <div className="d-flex align-items-center br-10 bg-light p-4 mb-4" style={{ height: '150px' }}>
                                <div className="d-flex br-10 align-items-center justify-content-center flex-shrink-0 bg-primary ml-n4 mr-4" style={{ width: '100px', height: '100px' }}>
                                    <i className="fa fa-2x fa-signs-post text-secondary"></i>
                                </div>
                                <h4 className=" m-0">Directives and Results</h4>
                            </div>
                        </div>
                    </div>
                </div>
            </div>

            {/* Services section */}
            <div className="container-fluid py-5">
                <div className="container-1300 pt-5 pb-3">
                    <h1 className="display-4  text-center mb-5">Our Services</h1>
                    <div className="row">
                        <div className="col-lg-4 col-md-6 mb-2">
                            <div className="service-item d-flex flex-column justify-content-center px-4 mb-4">
                                <div className="d-flex align-items-center justify-content-between mb-3">
                                    <div className="d-flex br-10 align-items-center justify-content-center bg-primary ml-n4" style={{ width: '80px', height: '80px' }}>
                                        <i className="fa fa-2x fa-laptop-code text-secondary"></i>
                                    </div>
                                </div>
                                <h4 className=" mb-3">Software Development</h4>
                                <p className="m-0">Software development service creates customized solutions that enhance your online presence with responsive,
                                    user-friendly software. Manage your business and stay ahead of the competition with our
                                    innovative software.</p>
                            </div>
                        </div>
                        <div className="col-lg-4 col-md-6 mb-2">
                            <div className="service-item active d-flex flex-column justify-content-center px-4 mb-4">
                                <div className="d-flex align-items-center justify-content-between mb-3">
                                    <div className="d-flex br-10 align-items-center justify-content-center bg-primary ml-n4" style={{ width: '80px', height: '80px' }}>
                                        <i className="fab fa-2x fa-facebook text-secondary"></i>
                                    </div>
                                </div>
                                <h4 className=" mb-3">Facebook Ads</h4>
                                <p className="m-0">Facebook ads service helps businesses increase their online visibility and
                                    reach a larger audience. Our experts create effective and well-targeted campaigns to promote your business and boost revenue.</p>
                            </div>
                        </div>
                        <div className="col-lg-4 col-md-6 mb-2">
                            <div className="service-item d-flex flex-column justify-content-center px-4 mb-4">
                                <div className="d-flex align-items-center justify-content-between mb-3">
                                    <div className="d-flex br-10 align-items-center justify-content-center bg-primary ml-n4" style={{ width: '80px', height: '80px' }}>
                                        <i className="fa fa-2x fa-pen text-secondary"></i>
                                    </div>
                                </div>
                                <h4 className=" mb-3 justify">Copywriting</h4>
                                <p className="m-0">Copywriting service offers professional writing that communicates your brand and products values.
                                    We create content that resonates with your target audience and drive conversions.</p>
                            </div>
                        </div>
                        <div className="col-lg-4 col-md-6 mb-2">
                            <div className="service-item d-flex flex-column justify-content-center px-4 mb-4">
                                <div className="d-flex align-items-center justify-content-between mb-3">
                                    <div className="d-flex br-10 align-items-center justify-content-center bg-primary ml-n4" style={{ width: '80px', height: '80px' }}>
                                        <i className="fab fa-2x fa-searchengin text-secondary"></i>
                                    </div>
                                </div>
                                <h4 className=" mb-3">SEO & Ecommerce</h4>
                                <p className="m-0">Seo & Ecommerce service help increase online visibility and drive revenue. Our experts optimize
                                    your website and product pages, making it easy for customers to find you and convert.</p>
                            </div>
                        </div>
                        <div className="col-lg-4 col-md-6 mb-2">
                            <div className="service-item d-flex flex-column justify-content-center px-4 mb-4">
                                <div className="d-flex align-items-center justify-content-between mb-3">
                                    <div className="d-flex br-10 align-items-center justify-content-center bg-primary ml-n4" style={{ width: '80px', height: '80px' }}>
                                        <i className="fa fa-2x fa-palette text-secondary"></i>
                                    </div>
                                </div>
                                <h4 className=" mb-3">Graphic Design</h4>
                                <p className="m-0">Graphic design service creates a visually attractive and meaningful brand identity.
                                    Our designers craft unique and memorable designs that reflects your brand's personality, values, and mission.</p>
                            </div>
                        </div>
                        <div className="col-lg-4 col-md-6 mb-2">
                            <div className="service-item d-flex flex-column justify-content-center px-4 mb-4">
                                <div className="d-flex align-items-center justify-content-between mb-3">
                                    <div className="d-flex br-10 align-items-center justify-content-center bg-primary ml-n4" style={{ width: '80px', height: '80px' }}>
                                        <i className="fab fa-2x fa-instagram text-secondary"></i>
                                    </div>
                                </div>
                                <h4 className=" mb-3">Instagram Growth</h4>
                                <p className="m-0">Instagram growth service helps businesses increase their reach and engagement.
                                    Our experts create well-targeted campaigns that attract followers, drive engagement, and boost revenue.</p>
                            </div>
                        </div>
                        <div className="col-lg-4 col-md-6 mb-2">
                            <div className="service-item d-flex flex-column justify-content-center px-4 mb-4">
                                <div className="d-flex align-items-center justify-content-between mb-3">
                                    <div className="d-flex br-10 align-items-center justify-content-center bg-primary ml-n4" style={{ width: '80px', height: '80px' }}>
                                        <i className="fa fa-2x fa-solid fa-compass text-secondary"></i>
                                    </div>
                                </div>
                                <h4 className=" mb-3">Business Plans</h4>
                                <p className="m-0">Business plans service helps businesses create a step by step and effective roadmap for success.
                                    Our experts provide a well-researched and customized plan that aligns with your goals, vision, and market trends.</p>
                            </div>
                        </div>
                        <div className="col-lg-4 col-md-6 mb-2">
                            <div className="service-item d-flex flex-column justify-content-center px-4 mb-4">
                                <div className="d-flex align-items-center justify-content-between mb-3">
                                    <div className="d-flex br-10 align-items-center justify-content-center bg-primary ml-n4" style={{ width: '80px', height: '80px' }}>
                                        <i className="fa fa-2x fa-signature text-secondary"></i>
                                    </div>
                                </div>
                                <h4 className=" mb-3">Business Names & Slogans</h4>
                                <p className="m-0">Business name generation service offers unique, memorable, and relevant names to reflect your
                                    business's personality and values, helping you differentiate yourself from the competition.</p>
                            </div>
                        </div>
                        <div className="col-lg-4 col-md-6 mb-2">
                            <div className="service-item d-flex flex-column justify-content-center px-4 mb-4">
                                <div className="d-flex align-items-center justify-content-between mb-3">
                                    <div className="d-flex br-10 align-items-center justify-content-center bg-primary ml-n4" style={{ width: '80px', height: '80px' }}>
                                        <i className="fab fa-2x fa-google text-secondary"></i>
                                    </div>
                                </div>
                                <h4 className=" mb-3">Google Ads</h4>
                                <p className="m-0">Google Ads service helps businesses improve their online visibility and attract potential customers through targeted campaigns. We optimize your ads to drive clicks, conversions, and maximize your return</p>
                            </div>
                        </div>
                    </div>
                </div>
            </div>

            {/* Timeline section */}
            <div className="container-fluid py-5">
                <h1 className="display-4  text-center mb-5">Future Steps </h1>
                <Timeline

                    items={[
                        <div className="timeline-item">
                            <span>Step 1: Initial Consultation</span>
                            <div className="content">
                                <h3>Schedule a consultation with us to discuss your business needs</h3>
                                <p>
                                    The first step is to schedule a consultation with our team to discuss your business needs, goals, and challenges.
                                    We'll ask you some questions about your business to get a better understanding of your requirements and determine
                                    if our services are a good fit for you
                                </p>
                            </div>
                        </div>,

                        <div className="timeline-item">
                            <span>Step 2: Proposal</span>
                            <div className="content">
                                <h3>Receive a proposal outlining our services and pricing</h3>
                                <p>
                                    After the initial consultation, we'll provide you with a proposal that outlines our services, pricing, and timeline
                                    for the project. The proposal will be tailored to your specific needs and will provide a detailed breakdown of the
                                    services we'll provide
                                </p>
                            </div>
                        </div>,

                        <div className="timeline-item">
                            <span>Step 3: Onboarding</span>
                            <div className="content">
                                <h3>Onboarding to our platform and software</h3>
                                <p>
                                    Once you accept our proposal, we'll set up your account on our platform and provide you with access to our business
                                    management software. We'll also assign a dedicated account manager who will be your point of contact throughout
                                    the project.
                                </p>
                            </div>
                        </div>,

                        <div className="timeline-item">
                            <span>Step 4: Collaboration and Progress Updates</span>
                            <div className="content">
                                <h3>Stay on track with progress updates and effective communication</h3>
                                <p>
                                    We'll collaborate with you to gather any necessary information or materials needed for the project. Throughout the project,
                                    we'll provide you with regular progress updates and seek your feedback to ensure we're meeting your expectations
                                </p>
                            </div>
                        </div>,

                        <div className="timeline-item">
                            <span>Step 5: Project Completion and Support</span>
                            <div className="content">
                                <h3>Completion handover and continued support</h3>
                                <p>
                                    Once the project is complete, we'll provide you with a final report and any deliverables. We'll also provide ongoing support and
                                    assistance to ensure that you continue to get the most out of our platform and services
                                </p>
                            </div>
                        </div>
                    ]}
                />
            </div>

            {/* Team section */}
            <div className="container-fluid py-5">
                <div className="container py-5">
                    <h1 className="display-4  text-center mb-5">Meet Our Team</h1>
                    <TeamCarousel />
                </div>
            </div>

            {/* Special Offer section */}
            <div className="container-fluid py-5">
                <div className="container py-5">
                    <div className="mainBanner br-10 py-5 px-4 text-center">
                        <div className="py-5">
                            <h1 className="display-1  text-primary mb-4">20% OFF</h1>
                            <h1 className=" text-light mb-4">Special Offer For New Members</h1>
                            <div className="mb-4" style={{ color: 'rgb(231, 229, 225)' }}>
                                <SpecialOffer />
                            </div>
                            <MoveToButton
                                classes="btn btn-primary py-md-3 px-md-5 mt-2 move-to-contact"
                                placeholder="Benefit Now"
                                targetId="contact-id"
                            />
                        </div>
                    </div>
                </div>
            </div>

            <TestimonialSection />

            <VendorsSection />

            <ContactSection title="Contact Us" />

            <FooterSection />

            <BackToTopButton />

        </React.Fragment>
    );
};

export default Home;