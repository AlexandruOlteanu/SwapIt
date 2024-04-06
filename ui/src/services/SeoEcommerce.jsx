import React, { lazy } from 'react'

import seoAndEcommerceAnimation from '../animations/seoAndEcommerceAnimation.json'

import Preloader from '../js/Preloader'
const TopbarSection = lazy(() => import('../sections/TopbarSection'));
const NavbarSection = lazy(() => import('../sections/NavbarSection'));
const Animation = lazy(() => import('../js/Animation'));
const MoveToButton = lazy(() => import('../js/MoveToButton'));
const ChallengesWeResolve = lazy(() => import('../sections/ChallengesWeResolve'));
const VendorsSection = lazy(() => import('../sections/VendorsSection'));
const Timeline = lazy(() => import('../sections/Timeline'));
const WhatWeNeedSection = lazy(() => import('../sections/WhatWeNeedSection'));
const Star4WaySection = lazy(() => import('../sections/Star4WaySection'));
const WhatDefinesUsSection = lazy(() => import('../sections/WhatDefinesUsSection'));
const FrequentAskedQuestions = lazy(() => import('../sections/FrequentAskedQuestions'));
const ContactSection = lazy(() => import('../sections/ContactSection'));
const FooterSection = lazy(() => import('../sections/FooterSection'));
const BackToTopButton = lazy(() => import('../js/BackToTopButton'));

function SeoEcommerce() {
    return (
        <React.Fragment>

            <Preloader />

            <TopbarSection />

            <NavbarSection />

            <div className="container-fluid py-5 mainBanner">
                <div className="container-xl pt-5 pb-3">
                    <h1 className="display-4 text-center mb-5" style={{ color: 'var(--light)' }}> Seo <span className="text-primary"> & Ecommerce </span></h1>
                    <div className="row justify-content-center">
                        <div className="col-lg-5 d-flex justify-content-center align-items-center">
                            <Animation
                                classes={"w-100 mb-4"}
                                animationData={seoAndEcommerceAnimation}
                            />
                        </div>
                        <div className="col-lg-6 d-flex flex-column justify-content-center align-items-start">
                            <p id="headlineDesktop" className='w-100' style={{ fontSize: '2.3rem', color: 'var(--light)' }}>
                                Drive digital success with expert SEO and e-commerce solutions: Maximize online visibility and skyrocket sales for your business
                            </p>
                            <p id="headlineMobile" className='w-100 justify-text' style={{ fontSize: '1.3rem', color: 'var(--light)' }}>
                                Drive digital success with expert SEO and e-commerce solutions: Maximize online visibility and skyrocket sales for your business
                            </p>
                            <MoveToButton
                                classes="btn btn-primary py-md-3 px-md-5 mt-2 move-to-contact"
                                placeholder="Contact Our Experts"
                                targetId="contact-id"
                            />
                        </div>
                    </div>
                    <ChallengesWeResolve
                        items={[

                            {
                                title: 'Increasing Online Visibility and Traffic',
                                description: 'Drive higher online visibility and targeted traffic to your ecommerce website through proven SEO strategies. Enhance search engine rankings, attract more potential customers, and increase your website\'s visibility',
                                image: 'challenges1'
                            },

                            {
                                title: 'Optimizing Product Pages for Conversions',
                                description: 'Maximize conversions on your ecommerce product pages with strategic optimization techniques. Craft compelling product descriptions, titles, and metadata that engage customers and drive higher conversion rates',
                                image: 'challenges2'
                            },

                            {
                                title: 'Enhancing User Experience and Site Performance',
                                description: ' Improve user experience and site performance for your ecommerce business. Enhance website structure, navigation, page load speed, and mobile responsiveness to create a seamless browsing experience that encourages engagement and repeat purchases',
                                image: 'challenges3'
                            }

                        ]}
                    />
                </div>
            </div>

            <VendorsSection />

            {/* Timeline Start */}
            <div className="container-fluid py-5">
                <h1 className="display-4 text-center mb-5"> Complete Process </h1>
            </div>
            <Timeline

                items={[
                    <div className="timeline-item">
                        <div className="content">
                            Step 1
                            <h3>SEO and Ecommerce Audit</h3>
                            <p>
                                To kickstart our SEO and Ecommerce service, we will conduct a thorough audit of your website, online store, and existing SEO efforts. Our team will analyze the technical aspects of your website, including site structure, page load speed, mobile responsiveness, and URL structure. We will also evaluate your current SEO performance, keyword rankings, backlink profile, and overall website health
                            </p>
                        </div>
                    </div>,

                    <div className="timeline-item">
                        <div className="content">
                            Step 2
                            <h3>Keyword Research and Optimization</h3>
                            <p>
                                Based on the audit findings and your business goals, we will perform comprehensive keyword research to identify relevant and high-converting keywords for your industry. Our team will optimize your website and product pages with targeted keywords, meta tags, headings, and structured data markup to improve search engine visibility and drive organic traffic. We will also analyze your competitors' strategies to identify opportunities and stay ahead in the search rankings
                            </p>
                        </div>
                    </div>,

                    <div className="timeline-item">
                        <div className="content">
                            Step 3
                            <h3>On-Page and Technical Optimization</h3>
                            <p>
                                Our SEO experts will implement on-page optimization techniques to enhance the user experience and search engine crawlability of your website. We will optimize page titles, meta descriptions, image alt tags, and internal linking structure to improve keyword relevance and increase click-through rates. Additionally, we will address technical SEO elements such as XML sitemaps, robots.txt, canonical tags, and website indexing to ensure that search engines can effectively crawl and index your site
                            </p>
                        </div>
                    </div>,

                    <div className="timeline-item">
                        <div className="content">
                            Step 4
                            <h3>Content Creation and Link Building</h3>
                            <p>
                                Content plays a crucial role in SEO and Ecommerce success. We will develop a content strategy that aligns with your target audience's interests and search intent. Our team will create engaging and optimized blog posts, product descriptions, category pages, and landing pages to attract organic traffic and enhance your website's authority. We will also employ white-hat link building techniques to acquire high-quality backlinks from authoritative websites, further boosting your website's visibility and credibility
                            </p>
                        </div>
                    </div>,

                    <div className="timeline-item">
                        <div className="content">
                            Step 5
                            <h3>Analytics and Reporting</h3>
                            <p>
                                To measure the effectiveness of our SEO and Ecommerce efforts, we will implement comprehensive analytics tracking and provide regular performance reports. Our team will analyze key metrics such as organic traffic, keyword rankings, conversion rates, and revenue generated. We will provide actionable insights and recommendations based on the data, allowing you to make informed decisions and continually optimize your SEO and Ecommerce strategies
                            </p>
                        </div>
                    </div>
                ]}
            />
            {/* Timeline End */}
            <div className="container-fluid py-5 mainBanner">
                <WhatWeNeedSection
                    title="What We Need From Your End"
                />
                <Star4WaySection

                    title={"Ultimate Objective"}
                    upItems={[
                        <div className="textContainer">
                            <div className="iconContainer">
                                <i className="fa-brands fa-searchengin fa-3x" style={{ color: 'var(--primary)' }}></i>
                            </div>
                            <div className="textData web-text-size">
                                Increase organic search visibility and drive targeted traffic to your ecommerce website by implementing effective SEO strategies that improve your website's search engine rankings
                            </div>
                        </div>,

                        <div className="textContainer">
                            <div className="iconContainer">
                                <i className="fa-solid fa-file-signature fa-3x" style={{ color: 'var(--primary)' }}></i>
                            </div>
                            <div className="textData web-text-size">
                                Optimize product listings and descriptions to enhance discoverability and increase conversions, ensuring that your ecommerce website effectively showcases your offerings to potential customers
                            </div>
                        </div>
                    ]}
                    downItems={[
                        <div className="textContainer">
                            <div className="iconContainer">
                                <i className="fa-solid fa-cart-shopping fa-3x" style={{ color: 'var(--primary)' }}></i>
                            </div>
                            <div className="textData web-text-size">
                                Improve user experience and conversion rates by optimizing website speed, navigation, and mobile responsiveness, creating a seamless and engaging online shopping experience for your customers
                            </div>
                        </div>,

                        <div className="textContainer">
                            <div className="iconContainer">
                                <i className="fa-solid fa-coins fa-3x" style={{ color: 'var(--primary)' }}></i>
                            </div>
                            <div className="textData web-text-size">
                                Maximize ecommerce revenue and ROI through data-driven insights and analysis, leveraging SEO and analytics tools to identify opportunities for growth, optimize campaigns, and drive sales
                            </div>
                        </div>
                    ]}
                />
            </div>

            <WhatDefinesUsSection />

            <div className="container-1300 pt-5 pb-3">
                <FrequentAskedQuestions
                    title={"FAQ's"}
                    items={[

                        {
                            question: 'How can SEO and ecommerce integration benefit my business?',
                            answer: 'Combining SEO and ecommerce integration offers powerful advantages for your business. SEO helps drive organic traffic and improve search engine rankings, while ecommerce integration ensures a seamless online shopping experience, streamlined inventory management, secure payment processing, and efficient order fulfillment.'
                        },

                        {
                            question: 'What SEO and ecommerce integration services do you offer?',
                            answer: 'Our comprehensive services include SEO optimization, keyword research, on-page and technical SEO, site speed optimization, content strategy, link building, and ecommerce integration. We specialize in integrating your ecommerce platform with various systems, such as inventory management, CRM, payment gateways, and shipping providers, to streamline your online operations.'
                        },

                        {
                            question: 'How long does it take to see results from SEO and ecommerce integration efforts?',
                            answer: 'The timeline for results can vary based on factors like your industry competitiveness and the scope of the strategy. Generally, improvements in search rankings and organic traffic can be observed within a few months. Ecommerce integration can be implemented in stages, with initial integrations completed within weeks, depending on the complexity of your systems.',
                        },

                        {
                            question: 'How can you ensure that my ecommerce website ranks higher and operates smoothly?',
                            answer: 'We combine SEO expertise and ecommerce integration skills to deliver optimal results. We optimize your website for search engines, perform technical audits, enhance page load speed, and create relevant, high-quality content. Simultaneously, we seamlessly integrate your ecommerce platform with essential systems, ensuring smooth operations, secure transactions, and enhanced user experience.',
                        },

                        {
                            question: 'Can you assist with other aspects of ecommerce beyond SEO and integration?',
                            answer: 'Absolutely! In addition to SEO and ecommerce integration, we offer a range of services such as website design and development, user experience (UX) optimization, conversion rate optimization, competitor analysis, online advertising, and social media marketing. We provide comprehensive solutions to enhance your online presence, boost conversions, and drive business growth.',
                        }

                    ]}

                />
            </div>

            <ContactSection
                title="Contact Our Experts"
            />

            <FooterSection />

            <BackToTopButton />

        </React.Fragment>
    )
}

export default SeoEcommerce