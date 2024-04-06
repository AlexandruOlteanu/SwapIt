import React, { lazy } from 'react'

import businessPlansAnimation from '../animations/businessPlansAnimation.json'

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

function BusinessPlans() {
    return (
        <React.Fragment>

            <Preloader />

            <TopbarSection />

            <NavbarSection />

            <div className="container-fluid py-5 mainBanner">
                <div className="container-xl pt-5 pb-3">
                    <h1 className="display-4 text-center mb-5" style={{ color: 'var(--light)' }}> Business <span className="text-primary"> Plans </span></h1>
                    <div className="row justify-content-center">
                        <div className="col-lg-5 d-flex justify-content-center align-items-center">
                            <Animation
                                classes={"w-90 mb-4"}
                                animationData={businessPlansAnimation}
                            />
                        </div>
                        <div className="col-lg-6 d-flex flex-column justify-content-center align-items-start">
                            <p id="headlineDesktop" className='w-100' style={{ fontSize: '2.3rem', color: 'var(--light)' }}>
                                Build a solid foundation for your business's success: Empower growth, seize opportunities, and navigate challenges with meticulous business planning and strategic foresight
                            </p>
                            <p id="headlineMobile" className='w-100 justify-text' style={{ fontSize: '1.3rem', color: 'var(--light)' }}>
                                Build a solid foundation for your business's success: Empower growth, seize opportunities, and navigate challenges with meticulous business planning and strategic foresight
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
                                title: 'Crafting Comprehensive Business Plans',
                                description: 'Develop comprehensive business plans that outline your vision, strategy, and financial projections. Benefit from expert guidance and support in creating well-structured and detailed plans that effectively communicate your business goals and attract potential investors',
                                image: 'challenges1'
                            },

                            {
                                title: 'Tailoring Business Plans for Funding Success',
                                description: 'Customize business plans to increase your chances of securing funding. Build plans to align with the requirements and expectations of investors or lenders, highlighting the value proposition, market analysis, and financial feasibility of your business to present a compelling case for investment',
                                image: 'challenges2'
                            },

                            {
                                title: 'Refining Existing Business Plans for Growth',
                                description: 'Enhance and refine existing business plans to drive business growth. Conduct in-depth reviews and analysis, identify areas for improvement, and provide strategic recommendations to optimize your plan, align it with current market trends, and position your business for success',
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
                            <h3>Discovery and Goal Setting</h3>
                            <p>
                                Our business plans service begins with a thorough discovery phase, where we work closely with you to understand your business concept, objectives, and long-term goals. We will gather essential information about your industry, target market, competition, and unique value proposition. This step allows us to align our efforts with your vision and develop a customized business plan that meets your specific needs
                            </p>
                        </div>
                    </div>,

                    <div className="timeline-item">
                        <div className="content">
                            Step 2
                            <h3>Market Research and Analysis</h3>
                            <p>
                                To create a comprehensive and effective business plan, we will conduct in-depth market research and analysis. Our team will explore market trends, customer demographics, industry dynamics, and competitive landscape. We will identify opportunities, potential challenges, and key success factors. This research will provide valuable insights to guide the strategic direction of your business plan
                            </p>
                        </div>
                    </div>,

                    <div className="timeline-item">
                        <div className="content">
                            Step 3
                            <h3>Business Plan Development</h3>
                            <p>
                                Based on the gathered information and analysis, our experienced team will begin the process of developing your business plan. We will create a clear and concise executive summary, outlining the core elements of your business. We will then delve into sections such as company description, market analysis, product or service offerings, marketing and sales strategies, organizational structure, operational plans, and financial projections. Our team will ensure that the business plan is well-structured, compelling, and aligned with industry best practices
                            </p>
                        </div>
                    </div>,

                    <div className="timeline-item">
                        <div className="content">
                            Step 4
                            <h3>Financial Projections and Analysis</h3>
                            <p>
                                One crucial aspect of a business plan is the financial projections. Our team will work closely with you to develop realistic and accurate financial forecasts, including sales projections, expenses, cash flow analysis, and profitability assessment. We will utilize financial modeling techniques to evaluate the financial viability and potential return on investment (ROI) of your business. Our goal is to provide you with a solid foundation for financial planning and decision-making
                            </p>
                        </div>
                    </div>,

                    <div className="timeline-item">
                        <div className="content">
                            Step 5
                            <h3>Review, Refinement, and Presentation</h3>
                            <p>
                                Once the initial business plan draft is completed, we will review it together, seeking your feedback and input. Our team will incorporate your suggestions and make revisions to ensure that the business plan accurately represents your vision and aligns with your goals. We will provide you with a finalized, polished business plan that you can confidently present to potential investors, lenders, or stakeholders
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
                                <i className="fa-solid fa-map-location-dot fa-3x" style={{ color: 'var(--primary)' }}></i>
                            </div>
                            <div className="textData web-text-size">
                                Develop comprehensive and strategic business plans that provide a roadmap for success, outlining clear objectives, strategies, and financial projections to guide your business growth
                            </div>
                        </div>,

                        <div className="textContainer">
                            <div className="iconContainer">
                                <i className="fa-solid fa-magnifying-glass-dollar fa-3x" style={{ color: 'var(--primary)' }}></i>
                            </div>
                            <div className="textData web-text-size">
                                Attract investors and secure funding by creating well-crafted business plans that effectively communicate your business concept, market potential, and financial viability
                            </div>
                        </div>
                    ]}
                    downItems={[
                        <div className="textContainer">
                            <div className="iconContainer">
                                <i className="fa-solid fa-chart-simple fa-3x" style={{ color: 'var(--primary)' }}></i>
                            </div>
                            <div className="textData web-text-size">
                                Identify potential risks and mitigate challenges through thorough market research and analysis, ensuring that your business plan is built on a solid foundation and prepared for potential obstacles
                            </div>
                        </div>,

                        <div className="textContainer">
                            <div className="iconContainer">
                                <i className="fa-solid fa-layer-group fa-3x" style={{ color: 'var(--primary)' }}></i>
                            </div>
                            <div className="textData web-text-size">
                                Streamline operations and optimize resource allocation by providing actionable insights and recommendations through our business plans, helping you make informed decisions that drive efficiency and profitability
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
                            question: 'Why is a well-written business plan important for my company?',
                            answer: 'A well-written business plan serves as a roadmap for your company\'s success. It outlines your business goals, strategies, and financial projections, helping you make informed decisions and attract investors or lenders. A comprehensive business plan demonstrates your understanding of the market, competitive landscape, and potential risks, while also highlighting your unique value proposition and growth opportunities.'
                        },

                        {
                            question: 'How can a professional business plan service benefit my company?',
                            answer: 'A professional business plan service brings expertise and industry knowledge to the table. They have experience in crafting business plans that effectively communicate your business concept, target market analysis, financial projections, and growth strategies. By leveraging their skills, they can help you create a compelling business plan that showcases your company\'s potential and increases your chances of securing funding or attracting strategic partners.'
                        },

                        {
                            question: 'What components should be included in a well-rounded business plan?',
                            answer: 'A well-rounded business plan typically includes an executive summary, company description, market analysis, organization and management structure, product or service offerings, marketing and sales strategies, financial projections, and an appendix with supporting documentation. Each section plays a vital role in presenting a comprehensive overview of your business and demonstrating its viability.',
                        },

                        {
                            question: 'Can I get assistance with financial projections and forecasting?',
                            answer: 'Absolutely! A business plan service can assist with developing realistic financial projections and forecasting. They can analyze your market, business model, and cost structures to help you estimate revenue, expenses, and profitability. Additionally, they can provide insights on key financial indicators, such as cash flow, break-even analysis, and return on investment, which are essential for understanding the financial health and growth potential of your business.',
                        },

                        {
                            question: 'Can a business plan be modified or updated as my company evolves?',
                            answer: 'Yes, a business plan should be a dynamic document that can be modified or updated as your company evolves. As your business grows, you may need to revise your strategies, goals, or financial projections. A professional business plan service can assist you in making these updates, ensuring that your business plan remains accurate, relevant, and aligned with your company\'s current objectives and market conditions.',
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

export default BusinessPlans