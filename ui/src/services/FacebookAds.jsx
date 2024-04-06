import React, { lazy } from 'react'

import facebookAdsAnimation from '../animations/facebookAdsAnimation.json'

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

function FacebookAds() {
    return (
        <React.Fragment>

            <Preloader />

            <TopbarSection />

            <NavbarSection />

            <div className="container-fluid py-5 mainBanner">
                <div className="container-xl pt-5 pb-3">
                    <h1 className="display-4 text-center mb-5" style={{ color: 'var(--light)' }}> Facebook <span className="text-primary"> Ads </span></h1>
                    <div className="row justify-content-center">
                        <div className="col-lg-5 d-flex justify-content-center align-items-center">
                            <Animation
                                classes={"w-85 mb-4"}
                                animationData={facebookAdsAnimation}
                            />
                        </div>
                        <div className="col-lg-6 d-flex flex-column justify-content-center align-items-start">
                            <p id="headlineDesktop" className='w-100' style={{ fontSize: '2.3rem', color: 'var(--light)' }}>
                                Improve your business's online presence: Drive engagement and boost conversions with persuasive Facebook ads strategies
                            </p>
                            <p id="headlineMobile" className='w-100 justify-text' style={{ fontSize: '1.3rem', color: 'var(--light)' }}>
                                Improve your business's online presence: Drive engagement and boost conversions with persuasive Facebook ads strategies
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
                                title: 'Targeted Audience Reach and Engagement',
                                description: 'Reach and engage your target audience effectively with our Facebook Ads expertise. We utilize advanced targeting strategies and audience segmentation techniques to ensure your ads reach the right people, resulting in increased engagement, conversions, and business growth',
                                image: 'challenges1'
                            },

                            {
                                title: 'Optimizing Ad Performance and ROI',
                                description: 'Maximize the performance of your Facebook ads and achieve a strong return on investment (ROI) with our data-driven approach. Our team continuously monitors and optimizes your campaigns, leveraging insights and A/B testing to improve click-through rates, conversions, and overall campaign success',
                                image: 'challenges2'
                            },

                            {
                                title: 'Crafting Compelling Ad Creatives and Copywriting',
                                description: 'Capture attention and drive action with captivating ad creatives and persuasive copywriting. Our Facebook Ads Service creates visually stunning ads and compelling messaging that effectively communicates your brand\'s value proposition, increasing engagement and conversion rates',
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
                                In the initial stage of our Facebook Ads service, we will begin by understanding your business objectives, target audience, and specific goals for your advertising campaign. We will collaborate with you to define key performance indicators (KPIs) that align with your overall marketing strategy. This step will ensure that our Facebook Ads campaigns are tailored to your specific needs and help you achieve the desired outcomes
                            </p>
                        </div>
                    </div>,

                    <div className="timeline-item">
                        <div className="content">
                            Step 2
                            <h3>Audience Research and Segmentation</h3>
                            <p>
                                To maximize the effectiveness of your Facebook Ads, we will conduct in-depth audience research to identify your target market segments. We will analyze demographics, interests, behaviors, and psychographics to create accurate buyer personas. By understanding your audience on a deeper level, we can develop highly targeted campaigns that resonate with your ideal customers, resulting in better engagement and conversions
                            </p>
                        </div>
                    </div>,

                    <div className="timeline-item">
                        <div className="content">
                            Step 3
                            <h3>Ad Creation and Optimization</h3>
                            <p>
                                Our skilled team of creatives and copywriters will craft compelling ad content that captivates your audience and drives action. We will create eye-catching visuals, write persuasive ad copy, and incorporate persuasive call-to-action statements. Throughout the campaign, we will continuously monitor and optimize the ads based on performance metrics such as click-through rates (CTR), conversion rates, and return on ad spend (ROAS) to ensure maximum effectiveness and ROI
                            </p>
                        </div>
                    </div>,

                    <div className="timeline-item">
                        <div className="content">
                            Step 4
                            <h3>A/B Testing and Performance Analysis</h3>
                            <p>
                                To further enhance the performance of your Facebook Ads, we will conduct A/B testing to compare different variations of ads, targeting options, and messaging. By testing different elements, we can identify the most effective combinations and optimize your campaigns accordingly. We will regularly analyze the results, providing you with detailed reports on key metrics, campaign performance, and actionable insights
                            </p>
                        </div>
                    </div>,

                    <div className="timeline-item">
                        <div className="content">
                            Step 5
                            <h3>Ongoing Monitoring and Campaign Management</h3>
                            <p>
                                Our Facebook Ads service is not a one-time effort. We will continuously monitor your campaigns, adjusting targeting, ad creative, and bidding strategies as needed. We will stay up-to-date with the latest Facebook advertising features and industry trends to keep your campaigns fresh and effective. Our team will provide you with regular updates, performance reports, and proactive recommendations to ensure that your Facebook Ads drive sustainable growth and success
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
                                Maximize reach and engagement by crafting compelling and targeted Facebook ad campaigns that captivate your audience and drive meaningful interactions
                            </div>
                        </div>,

                        <div className="textContainer">
                            <div className="iconContainer">
                                <i className="fa-solid fa-bolt fa-3x" style={{ color: 'var(--primary)' }}></i>
                            </div>
                            <div className="textData web-text-size">
                                Optimize ad performance and ROI through data-driven strategies, leveraging Facebook's powerful advertising platform to deliver measurable results and drive business growth
                            </div>
                        </div>
                    ]}
                    downItems={[
                        <div className="textContainer">
                            <div className="iconContainer">
                                <i className="fa-solid fa-arrow-trend-up fa-3x" style={{ color: 'var(--primary)' }}></i>
                            </div>
                            <div className="textData web-text-size">
                                Enhance brand visibility and awareness by strategically placing your business in front of the right audience on Facebook, increasing brand recognition and expanding your online presence
                            </div>
                        </div>,

                        <div className="textContainer">
                            <div className="iconContainer">
                                <i className="fa-solid fa-coins fa-3x" style={{ color: 'var(--primary)' }}></i>
                            </div>
                            <div className="textData web-text-size">
                                Drive conversions and revenue by creating persuasive Facebook ad campaigns that effectively communicate your value proposition, enticing users to take action and become valuable customers
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
                            question: 'How can Facebook Ads benefit my business?',
                            answer: 'Facebook Ads offer a powerful and targeted advertising platform that can help businesses reach their target audience effectively. With its vast user base and advanced targeting options, Facebook Ads enable businesses to increase brand awareness, drive website traffic, generate leads, and boost conversions. It allows you to reach specific demographics, interests, and behaviors, ensuring your ads are seen by the right people at the right time.'
                        },

                        {
                            question: 'What are the main benefits of using Facebook Ads?',
                            answer: 'We provide a range of benefits to help businesses maximize their advertising efforts on the platform. This can include ad campaign strategy development, audience targeting, ad creative design, ad copywriting, budget optimization, ongoing campaign monitoring and optimization, and performance reporting. The goal is to ensure your Facebook Ads campaigns are effective, efficient, and aligned with your business objectives.'
                        },

                        {
                            question: 'How can SwapIt team help improve my campaign performance?',
                            answer: 'The process employs experts who understand the intricacies of the platform and the nuances of effective advertising. They can conduct thorough audience research, develop targeted strategies, create engaging ad creatives, and optimize campaigns based on real-time data and insights. With their expertise, they can help improve your campaign performance by maximizing reach, engagement, and conversion rates while minimizing wasted ad spend.',
                        },

                        {
                            question: 'How do you handle ad targeting and audience segmentation?',
                            answer: 'We utilize Facebook\'s robust targeting capabilities to reach specific audiences. We conduct audience research, leveraging demographic, geographic, and psychographic information to define the target audience for your ads. Our team also uses custom and lookalike audiences to target users who resemble your existing customer base.',
                        },

                        {
                            question: 'Can I get help with ad performance tracking and reporting?',
                            answer: 'Absolutely! We can help set up proper tracking mechanisms to monitor the performance of your ads. We can implement Facebook Pixel, conversion tracking, and other tracking tools to measure key performance indicators (KPIs) such as click-through rates, conversions, cost per acquisition, and return on ad spend.',
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

export default FacebookAds