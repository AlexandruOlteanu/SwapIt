import React, { lazy } from 'react'

import Preloader from '../js/Preloader'
const TopbarSection = lazy(() => import('../sections/TopbarSection'));
const NavbarSection = lazy(() => import('../sections/NavbarSection'));
const FooterSection = lazy(() => import('../sections/FooterSection'));
const BackToTopButton = lazy(() => import('../js/BackToTopButton'));

function PrivacyPolicy() {
    return (
        <div>

            <Preloader />

            <TopbarSection />

            <NavbarSection />

            <div className="container-fluid py-5">
                <div className="container-lg pt-5 pb-3">
                    <h1 className="display-4 text-center mb-5"> Privacy <span className="text-primary"> Policy </span></h1>
                </div>
            </div>

            <div className="container-lg web-text-size">
                <p className="mb-3">
                    This Privacy Policy describes how SwapIt (“we,” “us,” “our,” or “SwapIt”) collects, uses, and discloses personal
                    information when you use our website, services, or products (collectively, “Services”).
                </p>
                <ul style={{ listStyleType: 'upper-roman' }}>
                    <li>
                        <span className="text-primary" style={{ fontWeight: 'bold' }}> Information We Collect </span> <br />
                        We collect the following types of personal information from our users:
                        <ul style={{ listStyleType: 'square' }}>
                            <li>
                                Contact Information: We collect your name, email address, postal address, and phone number when you create an account or contact us.
                            </li>
                            <li>
                                Payment Information: We collect your payment information, such as credit card details, when you make a purchase on our website.
                            </li>
                            <li>
                                Usage Information: We collect information about how you use our website and Services, including your IP address, browser type, operating system, and pages visited.
                            </li>
                            <li>
                                Cookies: We use cookies and similar tracking technologies to collect information about your preferences and interactions with our website and Services.
                            </li>
                        </ul>
                    </li>

                    <li>
                        <span className="text-primary" style={{ fontWeight: 'bold' }}> Use of Information </span> <br />
                        We use the information we collect to provide and improve our Services, respond to your inquiries, and communicate with you.
                        Specifically, we use your information for the following purposes:
                        <ul style={{ listStyleType: 'square' }}>
                            <li>
                                To provide and improve our Services, including software development, Facebook Ads, copywriting, SEO and Ecommerce, graphic design, Instagram growth, business plans, business names and slogans, and Google Ads.
                            </li>
                            <li>
                                To communicate with you about our Services, promotions, and marketing campaigns.
                            </li>
                            <li>
                                To respond to your inquiries and customer support requests.
                            </li>
                            <li>
                                To process and fulfill your orders and payments.
                            </li>
                        </ul>
                    </li>

                    <li>
                        <span className="text-primary" style={{ fontWeight: 'bold' }}> Sharing of Information </span> <br />
                        We do not sell or rent your personal information to third parties. We may share your information with third-party service
                        providers who perform services on our behalf, such as payment processors, hosting providers, and marketing agencies. We
                        ensure that all third-party service providers have appropriate data protection measures in place.
                        <br />
                        We may also disclose your personal information if required by law or in response to legal process or government requests.
                    </li>

                    <li>
                        <span className="text-primary" style={{ fontWeight: 'bold' }}> Security </span> <br />
                        We take appropriate measures to protect your personal information from unauthorized access, use, or disclosure. We use
                        industry-standard security technologies and procedures to safeguard your data, including encryption, access controls, and
                        monitoring.
                    </li>

                    <li>
                        <span className="text-primary" style={{ fontWeight: 'bold' }}> Data Retention </span> <br />
                        We retain your personal information only for as long as necessary to fulfill the purposes for which we collected it or as
                        required by law. When we no longer need your personal information, we securely delete or destroy it.
                    </li>

                    <li>
                        <span className="text-primary" style={{ fontWeight: 'bold' }}> Your Rights </span> <br />
                        You have the right to access, correct, and delete your personal information. You may also object to the processing of your
                        personal information or request that we restrict its use. To exercise your rights, please contact us using the contact
                        information provided below.
                    </li>

                    <li>
                        <span className="text-primary" style={{ fontWeight: 'bold' }}> Cookies </span> <br />
                        We use cookies and similar tracking technologies to collect information about your preferences and interactions with our website
                        and Services. You can manage your cookie preferences through your browser settings.
                    </li>

                    <li>
                        <span className="text-primary" style={{ fontWeight: 'bold' }}> Changes to Privacy Policy </span> <br />
                        We may update this Privacy Policy from time to time to reflect changes in our data practices or applicable laws and regulations.
                        We will notify you of any material changes to this Privacy Policy by posting a notice on our website or by sending you an email.
                    </li>

                    <li>
                        <span className="text-primary" style={{ fontWeight: 'bold' }}> Contact Us </span> <br />
                        If you have any questions or concerns about our Privacy Policy or our data practices, please contact us at: <br />
                        <br />
                        SwapIt
                        <br />
                        Bucharest, Romania
                        <br />
                        Email: legal@swapit.com
                    </li>

                </ul>
            </div>

            <FooterSection />

            <BackToTopButton />

        </div>
    )
}

export default PrivacyPolicy