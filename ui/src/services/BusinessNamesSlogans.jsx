import React, { lazy } from 'react'

import businessNamesAndSlogansAnimation from '../animations/businessNamesAndSlogansAnimation.json'

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


function BusinessNamesSlogans() {
    return (
        <React.Fragment>

            <Preloader />

            <TopbarSection />

            <NavbarSection />

            <div className="container-fluid py-5 mainBanner">
                <div className="container-xl pt-5 pb-3">
                    <h1 className="display-4 text-center mb-5" style={{ color: 'var(--light)' }}> Business Names <span className="text-primary"> & Slogans </span></h1>
                    <div className="row justify-content-center">
                        <div className="col-lg-5 d-flex justify-content-center align-items-center">
                            <Animation
                                classes={"w-100 mb-4"}
                                animationData={businessNamesAndSlogansAnimation}
                            />
                        </div>
                        <div className="col-lg-6 d-flex flex-column justify-content-center align-items-start">
                            <p id="headlineDesktop" className='w-100' style={{ fontSize: '2.3rem', color: 'var(--light)' }}>
                                Strategic branding solutions: Unlock your business's potential with professional researched names and compelling slogans
                            </p>
                            <p id="headlineMobile" className='w-100 justify-text' style={{ fontSize: '1.3rem', color: 'var(--light)' }}>
                                Strategic branding solutions: Unlock your business's potential with professional researched names and compelling slogans
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
                                title: 'Turn Brand Essence Into Memorable Names',
                                description: 'Uncover the true essence of your brand and capture it in memorable names. Our service specializes in crafting business names and slogans that encapsulate your brand\'s unique personality, values, and offerings, leaving a lasting impression on your target audience',
                                image: 'challenges1'
                            },

                            {
                                title: 'Trademark-Free & Social Media-Ready Identity',
                                description: 'We solve the challenge of finding a great business name and slogan that is both trademark-free and available for social media. Our service specializes in crafting unique and compelling names that are free from trademark conflicts and ready for use across various social media platforms. Establish a strong brand identity without legal concerns or online limitations',
                                image: 'challenges2'
                            },

                            {
                                title: 'Standing Out with Differentiation and Impact',
                                description: 'Rise above the competition and make a lasting impact with our expertise in creating distinctive brand identities. We develop business names and slogans that set your brand apart, helping you carve a niche in the market and resonate with your customers on a deeper level',
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
                            <h3>Initial Consultation and Research</h3>
                            <p>
                                At the beginning of our business names and slogans service, we will schedule an initial consultation to understand your business, its values, and target audience. We will delve into your company's unique selling points, vision, and brand personality. Additionally, we will conduct thorough research on your industry, competitors, and current market trends to ensure that your business name and slogan stand out from the crowd
                            </p>
                        </div>
                    </div>,

                    <div className="timeline-item">
                        <div className="content">
                            Step 2
                            <h3>Brainstorming and Ideation</h3>
                            <p>
                                Armed with the insights gained from our research, we will commence the creative brainstorming process. Our experienced team of branding experts will generate a wide range of innovative and captivating ideas for your business name and slogan. We will explore various themes, styles, and messaging approaches to provide you with a diverse selection of options to choose from
                            </p>
                        </div>
                    </div>,

                    <div className="timeline-item">
                        <div className="content">
                            Step 3
                            <h3>Evaluation and Refinement</h3>
                            <p>
                                Once we have compiled a comprehensive list of potential business names and slogans, we will evaluate each option based on several criteria. We will consider factors such as memorability, relevance to your business and target audience, uniqueness, and alignment with your brand identity. We will then refine the list, narrowing it down to the strongest contenders that best represent your company's essence and resonate with your customers
                            </p>
                        </div>
                    </div>,

                    <div className="timeline-item">
                        <div className="content">
                            Step 4
                            <h3>Presentation and Feedback</h3>
                            <p>
                                In this stage, we will present you with the refined selection of business names and slogans. We will provide you with detailed explanations and creative rationales for each option, allowing you to understand the thinking behind each suggestion. We value your feedback and input, so we encourage you to share your thoughts, preferences, and any modifications you may have in mind
                            </p>
                        </div>
                    </div>,

                    <div className="timeline-item">
                        <div className="content">
                            Step 5
                            <h3>Finalization and Delivery</h3>
                            <p>
                                After carefully considering your feedback, we will finalize the chosen business name and slogan. We will conduct a thorough trademark search to ensure their availability and legal compliance. Once approved, we will deliver the final business name and slogan package to you, including all necessary files and guidelines for their implementation across various marketing channels
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
                                <i className="fa-solid fa-trophy fa-3x" style={{ color: 'var(--primary)' }}></i>
                            </div>
                            <div className="textData web-text-size">
                                Achieve a strong brand differentiation and captivate attention with unique and memorable business names and slogans that set your business apart from competitors
                            </div>
                        </div>,

                        <div className="textContainer">
                            <div className="iconContainer">
                                <i className="fa-solid fa-file-contract fa-3x" style={{ color: 'var(--primary)' }}></i>
                            </div>
                            <div className="textData web-text-size">
                                Ensure complete legal compliance and protect your brand's integrity by conducting thorough trademark and legal checks, ensuring that your brand is secure and free from potential conflicts
                            </div>
                        </div>
                    ]}
                    downItems={[
                        <div className="textContainer">
                            <div className="iconContainer">
                                <i className="fa-solid fa-globe fa-3x" style={{ color: 'var(--primary)' }}></i>
                            </div>
                            <div className="textData web-text-size">
                                Establish a compelling and recognizable brand identity that deeply resonates with your target audience, leaving a lasting impression and fostering strong brand loyalty
                            </div>
                        </div>,

                        <div className="textContainer">
                            <div className="iconContainer">
                                <i className="fa-solid fa-bullseye fa-3x" style={{ color: 'var(--primary)' }}></i>
                            </div>
                            <div className="textData web-text-size">
                                Unlock untapped market opportunities and drive sustainable business growth by leveraging impactful branding strategies and messaging that attract new customers
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
                            question: 'How can business naming service help me create a strong brand identity?',
                            answer: 'Our business naming service conducts research and analysis to develop unique and impactful business names that resonate with your target audience, reflect your brand values, and differentiate you from competitors.'
                        },

                        {
                            question: 'What sets slogan development service apart from others in the market?',
                            answer: 'Our slogan development service provides catchy slogans aligned with your brand\'s messaging and positioning, created by experienced copywriters who understand the power of concise and compelling messaging.'
                        },

                        {
                            question: 'Can you assist with the legal and trademark aspects of business naming?',
                            answer: 'We conduct preliminary trademark searches and offer guidance on the legal aspects of business naming, recommending a trademark attorney for comprehensive searches and registration.',
                        },

                        {
                            question: 'How do you ensure the business names and slogans you create resonate with my target audience?',
                            answer: 'Through research and analysis, we gain insights into your target market\'s preferences and develop names and slogans that speak directly to them, evoking emotions and meeting their needs.',
                        },

                        {
                            question: 'What can I expect from the process of working with your business naming and slogan service?',
                            answer: 'We begin with a consultation to understand your brand and requirements. Then, we conduct research, generate options, iterate based on your feedback, and maintain open communication to ensure the final outcome aligns with your brand\'s vision and objectives.',
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

export default BusinessNamesSlogans