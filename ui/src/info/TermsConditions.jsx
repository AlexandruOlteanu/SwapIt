import React, { lazy } from 'react'

import Preloader from '../js/Preloader'
const TopbarSection = lazy(() => import('../sections/TopbarSection'));
const NavbarSection = lazy(() => import('../sections/NavbarSection'));
const FooterSection = lazy(() => import('../sections/FooterSection'));
const BackToTopButton = lazy(() => import('../js/BackToTopButton'));

function TermsConditions() {
    return (
        <div>

            <Preloader />

            <TopbarSection />

            <NavbarSection />

            <div className="container-fluid py-5">
                <div className="container-lg pt-5 pb-3">
                    <h1 className="display-4 text-center mb-5"> Terms <span className="text-primary"> & Conditions </span></h1>
                </div>
            </div>

            <div className="container-lg web-text-size">
                <p className="mb-3">
                    Welcome to SwapIt! These Terms & Conditions ("Terms") govern your use of our website, products, and services (collectively,
                    "Services"). Please read these Terms carefully before using our Services.
                </p>
                <ul style={{ listStyleType: 'upper-roman' }}>
                    <li>
                        <span className="text-primary" style={{ fontWeight: 'bold' }}> Acceptance Of Terms </span>
                        <br />
                        By using our Services, you agree to be bound by these Terms, as well as our Privacy Policy, which is incorporated by reference
                        into these Terms. If you do not agree to these Terms or the Privacy Policy, you may not use our Services.
                    </li>

                    <li>
                        <span className="text-primary" style={{ fontWeight: 'bold' }}> Use Of Services </span>
                        You may use our Services only for lawful purposes and in accordance with these Terms. You agree not to use our Services:
                        <ul style={{ listStyleType: 'square' }}>
                            <li>
                                In any way that violates any applicable federal, state, local, or international law or regulation.
                            </li>
                            <li>
                                For the purpose of exploiting, harming, or attempting to exploit or harm minors in any way by exposing them to inappropriate content, asking for personally identifiable information, or otherwise.
                            </li>
                            <li>
                                To transmit, or procure the sending of, any advertising or promotional material, including any "junk mail," "chain letter," "spam," or any other similar solicitation.
                            </li>
                            <li>
                                To impersonate or attempt to impersonate SwapIt, a SwapIt employee, another user, or any other person or entity.
                            </li>
                            <li>
                                To engage in any other conduct that restricts or inhibits anyone's use or enjoyment of our Services, or which, as determined by us, may harm SwapIt or users of our Services, or expose them to liability.
                            </li>
                        </ul>
                    </li>

                    <li>
                        <span className="text-primary" style={{ fontWeight: 'bold' }}> Intellectual Property </span>
                        All content on our website and Services, including text, graphics, logos, images, and software, is the property of SwapIt or its
                        content suppliers and is protected by United States and international copyright laws. You may not use any of our content without our
                        express written permission.
                    </li>

                    <li>
                        <span className="text-primary" style={{ fontWeight: 'bold' }}> Disclaimers </span> <br />
                        Our Services are provided "as is" and "as available" without any warranties of any kind, either express or implied. We do not warrant
                        that our Services will be uninterrupted or error-free, or that any defects will be corrected.
                    </li>

                    <li>
                        <span className="text-primary" style={{ fontWeight: 'bold' }}> Limitation of Liability </span> <br />
                        In no event shall SwapIt, its affiliates, directors, officers, employees, agents, or partners be liable for any direct, indirect,
                        incidental, special, consequential, or punitive damages arising from or related to your use of our Services.
                    </li>

                    <li>
                        <span className="text-primary" style={{ fontWeight: 'bold' }}> Indemnification </span> <br />
                        You agree to defend, indemnify, and hold SwapIt and its affiliates, directors, officers, employees, agents, and partners harmless
                        from any and all claims, liabilities, damages, costs, and expenses, including reasonable attorneys' fees, arising from or related to
                        your use of our Services or any violation of these Terms.
                    </li>

                    <li>
                        <span className="text-primary" style={{ fontWeight: 'bold' }}> Governing Law </span> <br />
                        These Terms and your use of our Services shall be governed by and construed in accordance with the laws of Romania, without giving effect
                        to any principles of conflicts of law.
                    </li>

                    <li>
                        <span className="text-primary" style={{ fontWeight: 'bold' }}> Changes to Terms </span> <br />
                        We may update these Terms from time to time by posting a new version on our website. Your continued use of our Services after the posting
                        of any changes to these Terms constitutes your acceptance of such changes.
                    </li>

                    <li>
                        <span className="text-primary" style={{ fontWeight: 'bold' }}> Contact Us </span> <br />
                        If you have any questions or concerns about these Terms & Conditions please contact us at: <br />
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

export default TermsConditions