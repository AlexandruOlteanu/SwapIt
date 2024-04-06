import React, { lazy } from 'react'

import softwareDevelopmentAnimation from '../animations/softwareDevelopmentAnimation.json'

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

function SoftwareDevelopment() {
    return (
        <React.Fragment>

            <Preloader />

            <TopbarSection />

            <NavbarSection />

            <div className="container-fluid py-5 mainBanner">
                <div className="container-xl pt-5 pb-3">
                    <h1 className="display-4 text-center mb-5" style={{ color: 'var(--light)' }}> Software <span className="text-primary"> Development </span></h1>
                    <div className="row justify-content-center">
                        <div className="col-lg-5 d-flex justify-content-center align-items-center">
                            <Animation
                                classes={"w-90 mb-4"}
                                animationData={softwareDevelopmentAnimation}
                            />
                        </div>
                        <div className="col-lg-6 d-flex flex-column justify-content-center align-items-start">
                            <p id="headlineDesktop" className='w-100' style={{ fontSize: '2.3rem', color: 'var(--light)' }}>
                                Fuel your business's growth with pragmatic software methodology for fast development and product scaling &#x1F680;
                            </p>
                            <p id="headlineMobile" className='w-100 justify-text' style={{ fontSize: '1.3rem', color: 'var(--light)' }}>
                                Fuel your business's growth with pragmatic software methodology for fast development and product scaling &#x1F680;
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
                                title: 'Overcoming Technological Obsolescence',
                                description: 'Modernize systems, leverage cutting-edge tech, and ensure seamless integration to overcome outdated technology. Enhance efficiency, scalability, and user experience for business growth and competitiveness',
                                image: 'challenges1'
                            },

                            {
                                title: 'Addressing Unique Business Needs',
                                description: 'Develop bespoke applications to unique business requirements. Streamline operations, boost productivity, and drive success with custom software development that aligns perfectly with your specific needs',
                                image: 'challenges2'
                            },

                            {
                                title: 'Streamlining Operations and Maximizing Efficiency',
                                description: ' Streamline operations, integrate systems, and automate workflows. Seamlessly synchronize data across platforms, reduce errors, and optimize productivity for improved business efficiency',
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
                            <h3>Requirement Gathering and Analysis</h3>
                            <p>
                                At the start of our software development process, we take the time to understand your business goals, challenges, and specific requirements. We engage in detailed discussions to gather all the necessary information, including functionality, desired features, and target audience. This helps us develop a clear understanding of your vision and ensures that we are aligned on the project objectives
                            </p>
                        </div>
                    </div>,

                    <div className="timeline-item">
                        <div className="content">
                            Step 2
                            <h3>Design and Planning</h3>
                            <p>
                                Once we have a comprehensive understanding of your requirements, our expert team of software developers and designers starts the planning phase. We create a detailed project plan that outlines the development roadmap, milestones, and timelines. During this phase, we also focus on designing the user interface (UI) and user experience (UX) elements to ensure an intuitive and visually appealing software solution
                            </p>
                        </div>
                    </div>,

                    <div className="timeline-item">
                        <div className="content">
                            Step 3
                            <h3>Development and Coding</h3>
                            <p>
                                With the planning and design phase complete, our skilled developers get to work on coding your software solution. We follow industry best practices and employ modern programming languages and frameworks to build a robust and scalable system. Throughout the development process, we emphasize regular communication and provide you with progress updates to keep you informed and involved
                            </p>
                        </div>
                    </div>,

                    <div className="timeline-item">
                        <div className="content">
                            Step 4
                            <h3>Testing and Quality Assurance</h3>
                            <p>
                                Quality is of utmost importance to us. Before delivering the final product, we conduct rigorous testing and quality assurance procedures to ensure that your software solution meets the highest standards. Our dedicated QA team performs various tests, including functionality testing, performance testing, security testing, and user acceptance testing. This helps us identify and resolve any issues or bugs, ensuring a smooth and error-free experience for your users
                            </p>
                        </div>
                    </div>,

                    <div className="timeline-item">
                        <div className="content">
                            Step 5
                            <h3>Deployment and Support</h3>
                            <p>
                                Once the software solution has successfully passed our rigorous testing phase, we proceed with the deployment process. Our team ensures a seamless transition from development to production, taking care of all the necessary steps for deployment. We also provide comprehensive training and documentation to help your team understand and maximize the benefits of the new software. Additionally, we offer ongoing support and maintenance services to address any future needs or updates
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
                                <i className="fa-solid fa-bolt fa-3x" style={{ color: 'var(--primary)' }}></i>
                            </div>
                            <div className="textData web-text-size">
                                Increase productivity and operational efficiency through the implementation of customized software solutions
                            </div>
                        </div>,

                        <div className="textContainer">
                            <div className="iconContainer">
                                <i className="fa-solid fa-face-smile fa-3x" style={{ color: 'var(--primary)' }}></i>
                            </div>
                            <div className="textData web-text-size">
                                Enhance customer satisfaction and loyalty by delivering user-friendly software with intuitive features and a seamless user experience
                            </div>
                        </div>
                    ]}
                    downItems={[
                        <div className="textContainer">
                            <div className="iconContainer">
                                <i className="fa-solid fa-robot fa-3x" style={{ color: 'var(--primary)' }}></i>
                            </div>
                            <div className="textData web-text-size">
                                Achieve cost savings and return on investment by optimizing processes, automating tasks, and reducing manual errors through software development
                            </div>
                        </div>,

                        <div className="textContainer">
                            <div className="iconContainer">
                                <i className="fa-solid fa-magnifying-glass-chart fa-3x" style={{ color: 'var(--primary)' }}></i>
                            </div>
                            <div className="textData web-text-size">
                                Gain a competitive edge in the market by leveraging innovative technology solutions tailored to specific business needs
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
                            question: 'What is the typical process for software development?',
                            answer: 'Our software development process typically involves several key stages, including requirements gathering, design, development, testing, deployment, and maintenance. We follow industry best practices and employ experienced developers to ensure a smooth and efficient development cycle.'
                        },

                        {
                            question: 'How long does it take to develop custom software?',
                            answer: 'The time required for software development can vary depending on the complexity of the project, the features and functionalities needed, and the availability of resources. We work closely with our clients to understand their specific requirements and provide a project timeline that aligns with their goals.'
                        },

                        {
                            question: 'Can you integrate the software with existing systems or third-party applications?',
                            answer: 'Yes, our software development team has expertise in integrating software with existing systems and third-party applications. We can assess the compatibility of your current infrastructure and develop solutions that seamlessly connect with your desired platforms, ensuring a smooth flow of data and processes.',
                        },

                        {
                            question: 'How do you ensure the security and confidentiality of the software and user data?',
                            answer: 'We prioritize security and take stringent measures to safeguard software and user data. This includes implementing encryption protocols, following secure coding practices, regularly updating and patching software, and conducting thorough security testing. We also sign non-disclosure agreements with our clients to ensure confidentiality.',
                        },

                        {
                            question: 'What kind of support and maintenance do you offer after the software is developed?',
                            answer: 'We provide comprehensive support and maintenance services after the software is developed and deployed. This includes bug fixes, performance optimization, software updates, and technical assistance. We offer different support packages tailored to meet our client\'s specific needs, ensuring their software remains up-to-date and runs smoothly over time.',
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

export default SoftwareDevelopment