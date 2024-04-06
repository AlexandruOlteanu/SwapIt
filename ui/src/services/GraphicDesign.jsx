import React, { lazy } from 'react'

import graphicDesignAnimation from '../animations/graphicDesignAnimation.json'

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

function GraphicDesign() {
    return (
        <React.Fragment>

            <Preloader />

            <TopbarSection />

            <NavbarSection />

            <div className="container-fluid py-5 mainBanner">
                <div className="container-xl pt-5 pb-3">
                    <h1 className="display-4 text-center mb-5" style={{ color: 'var(--light)' }}> Graphic <span className="text-primary"> Design </span></h1>
                    <div className="row justify-content-center">
                        <div className="col-lg-5 d-flex justify-content-center align-items-center">
                            <Animation
                                classes={"w-90 mb-4"}
                                animationData={graphicDesignAnimation}
                            />
                        </div>
                        <div className="col-lg-6 d-flex flex-column justify-content-center align-items-start">
                            <p id="headlineDesktop" className='w-100' style={{ fontSize: '2.3rem', color: 'var(--light)' }}>
                                Ignite your brand's visual potential with expert graphic design: Elevate aesthetics, captivate audiences, and fuel lasting brand growth
                            </p>
                            <p id="headlineMobile" className='w-100 justify-text' style={{ fontSize: '1.3rem', color: 'var(--light)' }}>
                                Ignite your brand's visual potential with expert graphic design: Elevate aesthetics, captivate audiences, and fuel lasting brand growth
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
                                title: 'Creating Captivating Visual Branding and Identity',
                                description: 'Establish a captivating visual branding and identity that reflects your business\'s personality and values. Craft unique and impactful designs that make a memorable impression on your target audience',
                                image: 'challenges1'
                            },

                            {
                                title: 'Designing Eye-Catching Marketing Collateral and Materials',
                                description: 'Stand out from the competition with eye-catching marketing collateral and materials. Specialize in creating visually stunning designs for brochures, flyers, banners, and other promotional materials that capture attention and leave a lasting impact',
                                image: 'challenges2'
                            },

                            {
                                title: 'Enhancing User Experience with Engaging Design',
                                description: 'Improve user experience and engagement with expertly designed websites and user interfaces. Combine aesthetics and usability to create visually appealing and intuitive designs that enhance user satisfaction and drive desired actions',
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
                            <h3>Creative Brief and Consultation</h3>
                            <p>
                                Our graphic design service begins with a thorough creative brief and consultation to understand your design needs, goals, and brand identity. We will collaborate with you to define the scope of the project, target audience, design preferences, and any specific requirements you may have. This step allows us to gather essential information and align our design strategy with your vision
                            </p>
                        </div>
                    </div>,

                    <div className="timeline-item">
                        <div className="content">
                            Step 2
                            <h3>Concept Development and Visualization</h3>
                            <p>
                                Using the insights gained from the creative brief, our talented graphic designers will start the concept development process. We will explore various design ideas, typography options, color palettes, and visual elements to create a unique and visually appealing concept that effectively communicates your message. We will provide you with visual representations, such as sketches or mockups, to help you visualize the design direction
                            </p>
                        </div>
                    </div>,

                    <div className="timeline-item">
                        <div className="content">
                            Step 3
                            <h3>Design Execution and Revision</h3>
                            <p>
                                Once the concept is approved, our designers will proceed to the design execution phase. They will meticulously craft your design, paying attention to details, composition, and aesthetics. Whether it's a logo, branding materials, packaging design, or marketing collateral, our team will ensure that the final design aligns with your brand identity and resonates with your target audience. We value your feedback and will incorporate revisions until you are completely satisfied with the design
                            </p>
                        </div>
                    </div>,

                    <div className="timeline-item">
                        <div className="content">
                            Step 4
                            <h3>File Preparation and Delivery</h3>
                            <p>
                                After finalizing the design, we will prepare the necessary files in the appropriate formats for your specific needs. Whether it's print-ready files, web-optimized graphics, or vector formats, we will ensure that you receive the files that are ready for immediate use. We will also provide guidelines and instructions on how to best implement and reproduce the design across different mediums and platforms
                            </p>
                        </div>
                    </div>,

                    <div className="timeline-item">
                        <div className="content">
                            Step 5
                            <h3>Collaboration and Ongoing Support</h3>
                            <p>
                                We believe in building lasting relationships with our clients. Even after the design is delivered, our team will remain available for ongoing support and collaboration. Whether you need additional design assets, modifications to existing designs, or assistance with future projects, we are here to help. We value your satisfaction and strive to exceed your expectations at every stage of our graphic design service
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
                                <i className="fa-solid fa-paintbrush fa-3x" style={{ color: 'var(--primary)' }}></i>
                            </div>
                            <div className="textData web-text-size">
                                Create visually stunning and impactful designs that effectively communicate your brand's message, capturing the attention and engaging your target audience
                            </div>
                        </div>,

                        <div className="textContainer">
                            <div className="iconContainer">
                                <i className="fa-solid fa-arrow-trend-up fa-3x" style={{ color: 'var(--primary)' }}></i>
                            </div>
                            <div className="textData web-text-size">
                                Enhance brand recognition and consistency by developing a cohesive visual identity across all marketing materials, establishing a strong and memorable brand presence
                            </div>
                        </div>
                    ]}
                    downItems={[
                        <div className="textContainer">
                            <div className="iconContainer">
                                <i className="fa-solid fa-users-viewfinder fa-3x" style={{ color: 'var(--primary)' }}></i>
                            </div>
                            <div className="textData web-text-size">
                                Drive customer engagement and response through visually appealing designs that inspire action, whether it's making a purchase, subscribing to a newsletter, or sharing content on social media
                            </div>
                        </div>,

                        <div className="textContainer">
                            <div className="iconContainer">
                                <i className="fa-solid fa-rocket fa-3x" style={{ color: 'var(--primary)' }}></i>
                            </div>
                            <div className="textData web-text-size">
                                Elevate the professionalism and credibility of your brand with high-quality graphic designs that reflect the quality of your products or services, fostering trust and loyalty among your customers
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
                            question: 'Why is professional graphic design important for my business?',
                            answer: 'Professional graphic design plays a crucial role in creating a strong visual identity for your business. It helps you communicate your brand message effectively, establish credibility, and differentiate yourself from competitors. Well-designed visuals can enhance brand recognition, attract attention, and leave a lasting impression on your target audience.'
                        },

                        {
                            question: 'What types of graphic design services do you offer?',
                            answer: 'Our graphic design services cover a wide range of needs, including logo design, branding collateral, website design, social media graphics, print materials, packaging design, and more. We have a team of experienced designers skilled in various design disciplines to cater to diverse design requirements and ensure cohesive and visually appealing brand assets.'
                        },

                        {
                            question: 'How does the graphic design process work?',
                            answer: 'Our graphic design process starts with a thorough understanding of your business, target audience, and design requirements. We collaborate closely with you to gather ideas, preferences, and any specific guidelines. Our designers then create initial design concepts for your review and feedback. We iterate based on your input until we achieve a final design that aligns with your vision and brand identity.',
                        },

                        {
                            question: 'Can you help with both digital and print graphic design projects?',
                            answer: 'Absolutely! Our graphic design services encompass both digital and print projects. Whether you need graphics for your website, social media platforms, or print materials such as brochures, flyers, or business cards, our designers have the expertise to create visually appealing designs that are optimized for the specific medium and purpose.',
                        },

                        {
                            question: 'What role does communication play in the graphic design process?',
                            answer: 'Effective communication is vital in the graphic design process. We value open and collaborative communication with our clients to ensure we understand your design goals, preferences, and brand identity accurately. Regular feedback and discussions throughout the design process help us create designs that meet your expectations and effectively represent your business.',
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

export default GraphicDesign