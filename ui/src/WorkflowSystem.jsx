import React, { lazy } from 'react'

import './css/WorkflowSystem.css'
import AgileMethodologyImg from './img/AgileMethodologyImg.webp'
import AgileMethodologyMobileImg from './img/AgileMethodologyMobileImg.webp'

import Preloader from './js/Preloader'
const TopbarSection = lazy(() => import('./sections/TopbarSection'));
const NavbarSection = lazy(() => import('./sections/NavbarSection'));
const Timeline = lazy(() => import('./sections/Timeline'));
const Star4WaySection = lazy(() => import('./sections/Star4WaySection'));
const FrequentAskedQuestions = lazy(() => import('./sections/FrequentAskedQuestions'));
const FooterSection = lazy(() => import('./sections/FooterSection'));
const BackToTopButton = lazy(() => import('./js/BackToTopButton'));

function WorkflowSystem() {
    return (
        <React.Fragment>

            <Preloader />

            <TopbarSection />

            <NavbarSection />

            <div className="container-fluid py-5 mainBanner">
                <div className="container-xl pt-5 pb-3">
                    <h1 className="display-4 text-center mb-7" style={{ color: 'var(--light)' }}>  Discover The Power Of Our <br /> <span className="text-primary"> Agile </span> Working Process</h1>
                    <div className="row justify-content-center">
                        <div className="col-lg-6 d-flex justify-content-center align-items-center">
                            <p id="headlineDesktop" className='w-95' style={{ fontSize: '2rem', color: 'var(--light)' }}>
                                We're an agile oriented company focused on delivering fast, efficient product releases. Our approach emphasizes scalability to ensure that
                                our products can grow alongside our clients' businesses. With a commitment to agility and a focus on rapid product release, we're dedicated to helping
                                our clients stay ahead of the competition!
                            </p>
                            <p id="headlineMobile" className='w-95 justify-text' style={{ fontSize: '1.5rem', color: 'var(--light)' }}>
                                We're an agile-oriented company committed to delivering fast, efficient, and scalable product releases that help our clients stay ahead of
                                the competition!
                            </p>
                        </div>
                        <div id="AgileMethodologyDesktop" className="col-lg-6 d-flex justify-content-end align-items-end" style={{ padding: '0 !important' }}>
                            <img className="w-100" src={AgileMethodologyImg} alt="" />
                        </div>
                        <div id="AgileMethodologyMobile" className="col-lg-6 d-flex justify-content-center align-items-start" style={{ padding: '0 !important' }}>
                            <img className="w-100" src={AgileMethodologyMobileImg} alt="" />
                        </div>
                    </div>
                </div>
            </div>

            {/* Timeline Start */}
            <div className="container-fluid py-5">
                <h1 className="display-4 text-center mb-5"> Navigating Success </h1>
            </div>
            <Timeline

                items={[
                    <div className="timeline-item">
                        <div className="content">
                            <h3>Defining The Project Vision and Goals</h3>
                            <p>
                                Clearly articulate the vision and goals of the project. This helps the team understand the purpose
                                and desired outcomes, providing a shared understanding of what needs to be achieved.
                            </p>
                        </div>
                    </div>,

                    <div className="timeline-item">
                        <div className="content">
                            <h3>Determine the Minimum Viable Product (MVP)</h3>
                            <p>
                                Develop a prioritized list of features, enhancements, and tasks known as the product backlog.
                                Collaborate with stakeholders and the development team to identify and define the requirements.
                            </p>
                        </div>
                    </div>,

                    <div className="timeline-item">
                        <div className="content">
                            <h3>Create a Product Backlog</h3>
                            <p>
                                Develop a prioritized list of features, enhancements, and tasks known as the product backlog. Collaborate
                                with stakeholders and the development team to identify and define the requirements.
                            </p>
                        </div>
                    </div>,

                    <div className="timeline-item">
                        <div className="content">
                            <h3>Sprint Planning</h3>
                            <p>
                                Plan the work for the upcoming sprint. We select a set of items from the product backlog and define the
                                objectives and goals for the sprint. Break down the selected items into actionable tasks with the help of
                                project managers and scrum masters.
                            </p>
                        </div>
                    </div>,

                    <div className="timeline-item">
                        <div className="content">
                            <h3>Conduct Daily Standup Meetings</h3>
                            <p>
                                Hold daily standup meetings to foster communication and collaboration within the team. Each team member
                                shares their progress, discusses any challenges or impediments, and synchronizes their efforts with the
                                rest of the team.
                            </p>
                        </div>
                    </div>,

                    <div className="timeline-item">
                        <div className="content">
                            <h3>Sprint Execution</h3>
                            <p>
                                Execute the tasks identified during sprint planning. The development team collaborates to complete the tasks,
                                leveraging short iterations or sprints to deliver incremental value. The team follows the Agile principles and
                                adapts their approach as needed.
                            </p>
                        </div>
                    </div>,

                    <div className="timeline-item">
                        <div className="content">
                            <h3>Review and Retrospective</h3>
                            <p>
                                At the end of each sprint, conduct a review and retrospective session. The review allows stakeholders to
                                provide feedback on the completed work, ensuring alignment with expectations. The retrospective is a
                                team-focused session to reflect on the sprint, identify areas for improvement, and make necessary
                                adjustments.
                            </p>
                        </div>
                    </div>,

                    <div className="timeline-item">
                        <div className="content">
                            <h3>Repeat and Iterate</h3>
                            <p>
                                Repeat the sprint process for subsequent iterations, continuously delivering increments of the product.
                                Refine and reprioritize the product backlog based on feedback and evolving requirements. Regularly assess
                                and improve the team's performance and processes.
                            </p>
                        </div>
                    </div>
                ]}
            />
            {/* Timeline End */}
            <div className="container-fluid py-5 mainBanner">
                <Star4WaySection

                    title={"How we develop products"}
                    upItems={[
                        <div className="textContainer">
                            <div className="iconContainer">
                                <i className="fa-solid fa-users fa-3x" style={{ color: 'var(--primary)' }}></i>
                            </div>
                            <div className="textData web-text-size">We prioritize user-friendly products that meet both client's
                                business goals and provide a seamless experience for end users.
                            </div>
                        </div>,

                        <div className="textContainer">
                            <div className="iconContainer">
                                <i className="fa-solid fa-compass-drafting fa-3x" style={{ color: 'var(--primary)' }}></i>
                            </div>
                            <div className="textData web-text-size">We design applications to be visually appealing while prioritizing intuitivity and consistency, ensuring they fulfill their primary purpose effectively.</div>
                        </div>
                    ]}
                    downItems={[
                        <div className="textContainer">
                            <div className="iconContainer">
                                <i className="fa-solid fa-flask-vial fa-3x" style={{ color: 'var(--primary)' }}></i>
                            </div>
                            <div className="textData web-text-size">We extensively test each build to ensure it is a functional and viable product, even at intermediate stages.</div>
                        </div>,

                        <div className="textContainer">
                            <div className="iconContainer">
                                <i className="fa-solid fa-code fa-3x" style={{ color: 'var(--primary)' }}></i>
                            </div>
                            <div className="textData web-text-size">We write clean and forward-thinking code, considering the product's scalability and the need for efficient request processing.</div>
                        </div>
                    ]}
                />
            </div>

            <div className="container-1300 pt-5 pb-3">
                <FrequentAskedQuestions
                    title={"FAQ's"}
                    items={[

                        {
                            question: 'What are the main benefits of Agile working?',
                            answer: 'Agile working offers several benefits, including faster time to market, increased customer satisfaction, improved team collaboration, better adaptability to change, and higher product quality. It allows for greater transparency, reduces risks, and promotes continuous improvement.'
                        },

                        {
                            question: 'How does Agile working handle changing requirements?',
                            answer: 'Agile working embraces change and recognizes that requirements can evolve. Through iterative development and continuous feedback loops, Agile allows for flexibility in adapting to changing circumstances and priorities. The product backlog is regularly reviewed and reprioritized based on feedback and new insights.'
                        },

                        {
                            question: 'What is the role of stakeholders in Agile projects?',
                            answer: 'Stakeholders have an active role in Agile projects. They provide input during the project vision and goal setting, collaborate in defining requirements, participate in sprint reviews, and offer feedback on completed work. Their involvement ensures alignment with business objectives and customer needs.',
                        },

                        {
                            question: 'How does Agile working ensure high-quality product development?',
                            answer: 'Agile working emphasizes delivering working software or products in short iterations. Each increment undergoes testing and quality assurance, ensuring that defects are identified and addressed early. Continuous integration and continuous delivery practices help maintain product quality throughout the development process.',
                        },

                        {
                            question: 'Can Agile working be combined with other project management methodologies?',
                            answer: 'Yes, Agile working can be combined with other project management methodologies based on the needs of the project or organization. Hybrid approaches like Scrum-ban or Agile waterfall can be adopted to incorporate Agile principles while still adhering to specific requirements or constraints.',
                        }

                    ]}
                />
            </div>


            <FooterSection />

            <BackToTopButton />

        </React.Fragment>
    )
}

export default WorkflowSystem