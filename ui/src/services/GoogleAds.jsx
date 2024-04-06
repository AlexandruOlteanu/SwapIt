import React, { lazy } from 'react'

import googleAdsAnimation from '../animations/googleAdsAnimation.json'

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

function GoogleAds() {
    return (
        <React.Fragment>

            <Preloader />

            <TopbarSection />

            <NavbarSection />

            <div className="container-fluid py-5 mainBanner">
                <div className="container-xl pt-5 pb-3">
                    <h1 className="display-4 text-center mb-5" style={{ color: 'var(--light)' }}> Google <span className="text-primary"> Ads </span></h1>
                    <div className="row justify-content-center">
                        <div className="col-lg-5 d-flex justify-content-center align-items-center">
                            <Animation
                                classes={"w-100 mb-4"}
                                animationData={googleAdsAnimation}
                            />
                        </div>
                        <div className="col-lg-6 d-flex flex-column justify-content-center align-items-start">
                            <p id="headlineDesktop" className='w-100' style={{ fontSize: '2.3rem', color: 'var(--light)' }}>
                                Discover the true potential of your online campaigns: Skyrocket your business's reach and conversions with custom Google Ads solutions that deliver unrivaled results
                            </p>
                            <p id="headlineMobile" className='w-100 justify-text' style={{ fontSize: '1.3rem', color: 'var(--light)' }}>
                                Discover the true potential of your online campaigns: Skyrocket your business's reach and conversions with custom Google Ads solutions that deliver unrivaled results
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
                                title: 'Maximizing Google Ads Performance and ROI',
                                description: 'Maximize the performance and return on investment (ROI) of your Google Ads campaigns. Our Google Ads service utilizes data-driven strategies, targeting techniques, and continuous optimization to drive higher click-through rates, conversions, and overall campaign success',
                                image: 'challenges1'
                            },

                            {
                                title: 'Targeted Audience Reach and Engagement with Google Ads',
                                description: 'Reach and engage your target audience effectively through Google Ads. Our service specializes in developing tailored strategies, creating compelling ad campaigns, and leveraging advanced targeting tools to ensure your ads reach the right people, resulting in increased engagement, conversions, and business growth',
                                image: 'challenges2'
                            },

                            {
                                title: 'Optimizing Google Ads Budget and Cost Efficiency',
                                description: 'Optimize your Google Ads budget and maximize cost efficiency. Our Google Ads service performs thorough keyword research, bid management, and ad testing to minimize costs while achieving optimal results, helping you make the most out of your advertising budget',
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
                            <h3>Strategy and Goal Setting</h3>
                            <p>
                                Our Google Ads service begins with a strategic planning phase. We will collaborate with you to understand your business goals, target audience, and desired outcomes. We will define key performance indicators (KPIs) that align with your objectives, such as website traffic, conversions, or return on ad spend (ROAS). This step allows us to develop a tailored Google Ads strategy that maximizes your campaign's effectiveness
                            </p>
                        </div>
                    </div>,

                    <div className="timeline-item">
                        <div className="content">
                            Step 2
                            <h3>Keyword Research and Ad Group Creation</h3>
                            <p>
                                To optimize your Google Ads campaign, we will perform thorough keyword research to identify relevant and high-converting keywords for your industry. Our team will organize these keywords into targeted ad groups, ensuring that your ads are displayed to the right audience at the right time. We will also conduct competitor analysis to identify keyword opportunities and stay ahead in the search rankings
                            </p>
                        </div>
                    </div>,

                    <div className="timeline-item">
                        <div className="content">
                            Step 3
                            <h3>Ad Creation and Optimization</h3>
                            <p>
                                Our experienced team of copywriters and designers will craft compelling and relevant ad copy and visuals that engage your target audience. We will create persuasive headlines, captivating descriptions, and compelling calls-to-action to drive clicks and conversions. Throughout the campaign, we will continuously monitor and optimize your ads based on performance metrics such as click-through rates (CTR), conversion rates, and cost per acquisition (CPA)
                            </p>
                        </div>
                    </div>,

                    <div className="timeline-item">
                        <div className="content">
                            Step 4
                            <h3>Landing Page Optimization and Tracking</h3>
                            <p>
                                An effective Google Ads campaign goes hand in hand with optimized landing pages. We will review your website's landing pages and make recommendations to improve their relevance, load speed, user experience, and conversion rates. Our team will implement tracking tools like Google Analytics and conversion tracking to monitor the performance of your ads, analyze user behavior, and measure the success of your campaign
                            </p>
                        </div>
                    </div>,

                    <div className="timeline-item">
                        <div className="content">
                            Step 5
                            <h3>Performance Monitoring and Reporting</h3>
                            <p>
                                Our Google Ads service includes regular performance monitoring and detailed reporting. We will track the performance of your ads, analyze key metrics, and provide you with comprehensive reports. These reports will highlight campaign insights, ad performance, conversions, and ROI. Our team will interpret the data, provide actionable recommendations, and work closely with you to optimize your Google Ads campaigns for maximum results
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
                                <i className="fa-solid fa-users-viewfinder fa-3x" style={{ color: 'var(--primary)' }}></i>
                            </div>
                            <div className="textData web-text-size">
                                Drive targeted traffic and increase online visibility by creating effective Google Ads campaigns that reach your desired audience at the right moment, generating quality leads and conversions
                            </div>
                        </div>,

                        <div className="textContainer">
                            <div className="iconContainer">
                                <i className="fa-solid fa-coins fa-3x" style={{ color: 'var(--primary)' }}></i>
                            </div>
                            <div className="textData web-text-size">
                                Maximize your advertising budget and return on investment (ROI) by implementing strategic bidding strategies, ad targeting, and optimization techniques to optimize campaign performance and cost-efficiency
                            </div>
                        </div>
                    ]}
                    downItems={[
                        <div className="textContainer">
                            <div className="iconContainer">
                                <i className="fa-solid fa-arrow-trend-up fa-3x" style={{ color: 'var(--primary)' }}></i>
                            </div>
                            <div className="textData web-text-size">
                                Enhance brand recognition and awareness by leveraging the power of Google Ads to place your business in front of a wider audience, increasing your brand's visibility and market presence
                            </div>
                        </div>,

                        <div className="textContainer">
                            <div className="iconContainer">
                                <i className="fa-brands fa-searchengin fa-3x" style={{ color: 'var(--primary)' }}></i>
                            </div>
                            <div className="textData web-text-size">
                                Improve campaign performance and insights through data-driven analysis and optimization, utilizing Google Ads' robust analytics tools to make data-informed decisions that continuously improve your advertising effectiveness
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
                            question: 'How can Google Ads help my business stand out in online searches?',
                            answer: 'Google Ads offers a powerful platform to enhance your online visibility, allowing your business to stand out prominently in relevant search results and capture the attention of potential customers who are actively searching for products or services like yours.'
                        },

                        {
                            question: 'What services does your Google Ads service provide to optimize our campaigns?',
                            answer: 'Our comprehensive Google Ads service offers a range of services to optimize your campaigns, including thorough keyword research, expert ad copywriting, precise campaign setup, strategic bid management, ongoing monitoring, continuous optimization, and detailed reporting to maximize the effectiveness of your advertising efforts.'
                        },

                        {
                            question: 'How do you ensure that our Google Ads campaigns target the right audience?',
                            answer: 'To ensure effective audience targeting, we conduct in-depth research and analysis to gain a deep understanding of your target audience\'s demographics, interests, search behavior, and purchase intent. By implementing precise targeting settings and employing advanced audience segmentation techniques, we ensure that your ads are reaching the most relevant potential customers who are most likely to engage with your business and convert into valuable leads or sales.',
                        },

                        {
                            question: 'How can Google Ads help us measure the success of our advertising campaigns?',
                            answer: 'Google Ads offers robust tracking and reporting features that enable us to measure the success of your advertising campaigns accurately. By setting up comprehensive conversion tracking, we monitor specific actions valuable to your business, such as form submissions, purchases, or sign-ups. Detailed performance reports provide insights into key metrics such as click-through rates, conversion rates, cost per acquisition, and return on ad spend.',
                        },

                        {
                            question: 'Can your Google Ads service help us maximize our advertising budget?',
                            answer: 'Absolutely! Our Google Ads service is dedicated to maximizing the value of your advertising budget. We employ cost-effective bidding strategies, ongoing optimization techniques, continuous performance monitoring, and in-depth analysis of campaign data to ensure that your budget is allocated efficiently and effectively.',
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

export default GoogleAds