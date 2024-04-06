import React, { lazy } from 'react'

import copywritingAnimation from '../animations/copywritingAnimation.json'

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

function Copywriting() {
    return (
        <React.Fragment>

            <Preloader />

            <TopbarSection />

            <NavbarSection />

            <div className="container-fluid py-5 mainBanner">
                <div className="container-xl pt-5 pb-3">
                    <h1 className="display-4 text-center mb-5" style={{ color: 'var(--light)' }}> Copywriting </h1>
                    <div className="row justify-content-center">
                        <div className="col-lg-5 d-flex justify-content-center align-items-center">
                            <Animation
                                classes={"w-90 mb-4"}
                                animationData={copywritingAnimation}
                            />
                        </div>
                        <div className="col-lg-6 d-flex flex-column justify-content-center align-items-start">
                            <p id="headlineDesktop" className='w-100' style={{ fontSize: '2.3rem', color: 'var(--light)' }}>
                                Maximize conversions and skyrocket sales with persuasive copywriting: Unlock the power of words to captivate your audience and drive unparalleled business growth
                            </p>
                            <p id="headlineMobile" className='w-100 justify-text' style={{ fontSize: '1.3rem', color: 'var(--light)' }}>
                                Maximize conversions and skyrocket sales with persuasive copywriting: Unlock the power of words to captivate your audience and drive unparalleled business growth
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
                                title: 'Crafting Compelling and Persuasive Copy',
                                description: 'Capture attention and engage your audience with compelling and persuasive copy. Our team specializes in crafting captivating content that effectively communicates your brand\'s message, resonates with your target audience, and drives desired actions',
                                image: 'challenges1'
                            },

                            {
                                title: 'Tailoring Copy to Your Brand\'s Voice and Tone',
                                description: 'Ensure consistency and authenticity in your brand\'s communication by tailoring copy to your unique voice and tone. We will work closely with you to understand your brand\'s personality, values, and target audience, delivering copy that reflects your brand\'s identity and connects with your customers',
                                image: 'challenges2'
                            },

                            {
                                title: 'Enhancing SEO and Web Content',
                                description: 'Improve search engine visibility and optimize your web content with our Copywriting expertise. We create SEO-friendly copy that incorporates relevant keywords and follows best practices, helping your website rank higher in search results and driving organic traffic to your business',
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
                            <h3>Discovery and Understanding</h3>
                            <p>
                                Our copywriting service begins with an in-depth discovery phase where we aim to gain a comprehensive understanding of your business, brand, target audience, and unique selling points. We will conduct thorough research to grasp your industry landscape, competitors, and market trends. By immersing ourselves in your business, we can develop copy that effectively communicates your message and resonates with your audience
                            </p>
                        </div>
                    </div>,

                    <div className="timeline-item">
                        <div className="content">
                            Step 2
                            <h3>Message Development and Planning</h3>
                            <p>
                                Based on the insights gathered during the discovery phase, our skilled copywriters will work closely with you to develop a clear and compelling messaging strategy. We will define the key points, value propositions, and brand voice that need to be conveyed through the copy. Our team will collaborate with you to align the copy with your marketing goals, ensuring consistency across different platforms and campaigns
                            </p>
                        </div>
                    </div>,

                    <div className="timeline-item">
                        <div className="content">
                            Step 3
                            <h3>Creative Copywriting and Editing</h3>
                            <p>
                                Once the messaging strategy is defined, our copywriters will bring your brand's story to life through engaging and persuasive copy. We will create captivating headlines, informative body copy, and impactful calls-to-action tailored to your target audience and specific marketing channels. Our team will carefully edit and refine the copy, ensuring clarity, coherence, and a consistent tone that reflects your brand's personality
                            </p>
                        </div>
                    </div>,

                    <div className="timeline-item">
                        <div className="content">
                            Step 4
                            <h3>Proofreading and Quality Assurance</h3>
                            <p>
                                Before delivering the final copy, our dedicated proofreaders and quality assurance specialists will meticulously review and polish the content. They will ensure that the copy is free from grammatical errors, typos, and inconsistencies. Additionally, they will verify that the copy adheres to any relevant style guides, brand guidelines, or industry-specific requirements
                            </p>
                        </div>
                    </div>,

                    <div className="timeline-item">
                        <div className="content">
                            Step 5
                            <h3>Collaboration and Iteration</h3>
                            <p>
                                We value your input and collaboration throughout the copywriting process. We will present the initial drafts for your review and feedback. Our team will incorporate your suggestions and make revisions as necessary, ensuring that the final copy meets your expectations and effectively communicates your message. We believe in a collaborative approach that results in copy that not only meets your objectives but also exceeds your expectations
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
                                <i className="fa-solid fa-pen fa-3x" style={{ color: 'var(--primary)' }}></i>
                            </div>
                            <div className="textData web-text-size">
                                Craft compelling and persuasive copy that resonates with your target audience, effectively conveying your brand's message and driving engagement
                            </div>
                        </div>,

                        <div className="textContainer">
                            <div className="iconContainer">
                                <i className="fa-solid fa-trophy fa-3x" style={{ color: 'var(--primary)' }}></i>
                            </div>
                            <div className="textData web-text-size">
                                Enhance brand credibility and trust by delivering high-quality, well-researched copy that showcases your expertise and positions your business as a leader in the industry
                            </div>
                        </div>
                    ]}
                    downItems={[
                        <div className="textContainer">
                            <div className="iconContainer">
                                <i className="fa-solid fa-coins fa-3x" style={{ color: 'var(--primary)' }}></i>
                            </div>
                            <div className="textData web-text-size">
                                Drive customer action and conversions through persuasive and impactful copywriting that motivates readers to take the desired steps, whether it's making a purchase, signing up, or contacting your business
                            </div>
                        </div>,

                        <div className="textContainer">
                            <div className="iconContainer">
                                <i className="fa-brands fa-searchengin fa-3x" style={{ color: 'var(--primary)' }}></i>
                            </div>
                            <div className="textData web-text-size">
                                Improve search engine visibility and organic traffic by incorporating SEO-friendly copywriting techniques that optimize your content for relevant keywords and improve your website's search rankings
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
                            question: 'What is copywriting, and why is it important for my business?',
                            answer: 'Copywriting is the art of crafting persuasive and engaging written content for marketing materials. It includes website copy, blog posts, social media content, product descriptions, advertisements, and more. Effective copywriting is crucial for capturing your audience\'s attention, conveying your brand\'s message, and driving desired actions such as conversions and brand loyalty. It helps you stand out in a competitive market and effectively communicate your unique value proposition.'
                        },

                        {
                            question: 'How can a professional copywriting service benefit my business?',
                            answer: 'A professional copywriting service brings expertise in crafting compelling and persuasive content. Copywriters have a deep understanding of consumer psychology, persuasive language techniques, and storytelling. By leveraging their skills, they can create impactful copy that resonates with your target audience, builds brand trust, and drives desired outcomes. A professional copywriting service can save you time and effort while ensuring the quality and effectiveness of your marketing materials.'
                        },

                        {
                            question: 'What types of copywriting projects can your team assist with?',
                            answer: 'We can assist with a wide range of projects, including website copy, landing pages, email marketing campaigns, sales brochures, social media posts, press releases, and more. Whether you need content for online or offline marketing channels, a professional copywriting service can tailor the message and tone to suit your specific needs and target audience.',
                        },

                        {
                            question: 'How do copywriters ensure that the copy aligns with my brand\'s voice and messaging?',
                            answer: 'Experienced copywriters understand the importance of maintaining brand consistency. They work closely with you to understand your brand\'s voice, tone, and messaging guidelines. By conducting thorough research on your brand and target audience, they ensure that the copy reflects your brand\'s values and resonates with your target audience. Copywriters can adapt their writing style and tone to match your brand\'s personality, whether it\'s professional, conversational, authoritative, or creative.',
                        },

                        {
                            question: 'Can this optimize my copy for search engines (SEO)?',
                            answer: 'Yes, many copywriting services offer SEO copywriting. SEO copywriting involves optimizing the content with relevant keywords, meta tags, and headings to improve its visibility in search engine rankings. Copywriters with SEO expertise can help you create content that not only engages readers but also ranks well in search results, driving organic traffic to your website.',
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

export default Copywriting