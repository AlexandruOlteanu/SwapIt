import React, { useState, useRef, lazy } from 'react';
import emailjs from '@emailjs/browser';
import checkAnimation from '../animations/check-animation.json';

const Animation = lazy(() => import('../js/Animation'));

function ContactSection(props) {
    const { title } = props;
    const form = useRef();
    const nameLength = 4;
    const emailSymbol = '@';
    const subjectLength = 10;
    const descriptionLength = 20;
    const briefLength = 20;
    const [showAnimation, setShowAnimation] = useState(false);
    const [isFormSubmitted, setIsFormSubmitted] = useState(false);

    const sendEmail = (e) => {
        e.preventDefault();

        let contact_name = document.getElementById('contact_form_name').value;
        let contact_email = document.getElementById('contact_form_email').value;
        let contact_subject = document.getElementById('contact_form_subject').value;
        let contact_description = document.getElementById('contact_form_description').value;
        let contact_brief = document.getElementById('contact_form_brief').value;

        let not_validated = [];

        if (contact_name.length < nameLength) {
            not_validated.push('\u26d4 The name must have at least ' + nameLength + ' characters.');
        }
        if (!contact_email.includes(emailSymbol)) {
            not_validated.push('\u26d4 The email address is not valid.');
        }
        if (contact_subject.length < subjectLength) {
            not_validated.push('\u26d4 The subject must have at least ' + subjectLength + ' characters.');
        }
        if (contact_description.length < descriptionLength) {
            not_validated.push('\u26d4 Description must have at least ' + descriptionLength + ' characters.');
        }
        if (contact_brief.length < briefLength) {
            not_validated.push('\u26d4 Brief must have at least ' + briefLength + ' characters.');
        }

        if (not_validated.length > 0) {
            alert(not_validated.join('\n'));
        } else {
            emailjs
                .sendForm(
                    process.env.REACT_APP_EMAIL_JS_SERVICE_ID,
                    process.env.REACT_APP_EMAIL_JS_CONTACT_TEMPLATE_ID,
                    form.current,
                    process.env.REACT_APP_EMAIL_JS_PUBLIC_KEY
                )
                .then((result) => {
                    console.log(result.text);
                    setIsFormSubmitted(true); // Set the form submitted flag
                    setShowAnimation(true); // Display the success animation
                })
                .catch((error) => {
                    console.log(error.text);
                });
        }
    };

    const handleAnimationClose = () => {
        setShowAnimation(false); // Close the animation
        setIsFormSubmitted(false); // Reset the form submitted flag
    };

    return (
        <div className="container-fluid" id="contact-id">
            <div className="container pt-4 pb-4">
                <h1 className="display-4 text-center mb-5">{title}</h1>
                <div className="row">
                    <div className="col-lg-7 mb-2">
                        {!showAnimation && (
                            <div className="contact-form bg-light bg-grey mb-4" style={{ padding: '30px', borderRadius: '10px' }}>
                                {isFormSubmitted ? (
                                    <div className="text-center">
                                        <div style={{ width: '200px', height: '200px' }}>
                                            <Animation animationData={checkAnimation} loop={false} autoplay />
                                        </div>
                                        <button
                                            onClick={handleAnimationClose}
                                            className="btn btn-primary btn-lg px-5"
                                        >
                                            OKs
                                        </button>



                                    </div>
                                ) : (
                                    <form ref={form}>
                                        <div className="row">
                                            <div className="col-6 form-group">
                                                <input
                                                    type="text"
                                                    className="form-control p-4"
                                                    placeholder="Your Name"
                                                    required="required"
                                                    style={{ borderRadius: '10px' }}
                                                    name="user_name"
                                                    id="contact_form_name"
                                                />
                                            </div>
                                            <div className="col-6 form-group">
                                                <input
                                                    type="email"
                                                    className="form-control p-4"
                                                    placeholder="Your Email"
                                                    required="required"
                                                    style={{ borderRadius: '10px' }}
                                                    name="user_email"
                                                    id="contact_form_email"
                                                />
                                            </div>
                                        </div>
                                        <div className="form-group">
                                            <input
                                                type="text"
                                                className="form-control p-4"
                                                placeholder="Subject"
                                                required="required"
                                                style={{ borderRadius: '10px' }}
                                                name="subject"
                                                id="contact_form_subject"
                                            />
                                        </div>
                                        <div className="form-group">
                                            <textarea
                                                className="form-control py-3 px-4"
                                                rows="5"
                                                placeholder="Business Description"
                                                required="required"
                                                style={{ borderRadius: '10px' }}
                                                name="business_description"
                                                id="contact_form_description"
                                            ></textarea>
                                        </div>
                                        <div className="form-group">
                                            <textarea
                                                className="form-control py-3 px-4"
                                                rows="5"
                                                placeholder="Short Brief On Project Requirements And Needs"
                                                required="required"
                                                style={{ borderRadius: '10px' }}
                                                name="project_requirements"
                                                id="contact_form_brief"
                                            ></textarea>
                                        </div>
                                        <div className="d-flex justify-content-center">
                                            <button onClick={sendEmail} className="btn btn-primary btn-lg px-5" type="submit">
                                                Send Message
                                            </button>
                                        </div>
                                    </form>
                                )}
                            </div>
                        )}
                        {showAnimation && (
                            <div className="contact-form bg-light bg-grey mb-4" style={{ padding: '95px', borderRadius: '10px' }}>
                                <div className="text-center" style={{ display: 'flex', justifyContent: 'center', alignItems: 'center', flexDirection: 'column' }}>
                                    <div style={{ width: '200px', height: '200px' }}>
                                        <Animation animationData={checkAnimation} loop={false} autoplay />
                                    </div>
                                    <p className='success-message-text'>Message sent successfully!</p>
                                    <p className='success-message-text'>Thank you for reaching out.</p>
                                    <button className='confirm-Contact-Button'
                                        onClick={handleAnimationClose}
                                        style={{
                                            padding: '10px 20px',
                                            width: '150px',
                                            background: '#09dec7',
                                            color: 'white',
                                            border: 'none',
                                            borderRadius: '5px',
                                            cursor: 'pointer',
                                        }}
                                    >
                                        OK
                                    </button>
                                </div>
                            </div>

                        )}
                    </div>
                    <div className="col-lg-5 mb-2">
                        <div className="bg-secondary d-flex flex-column justify-content-center px-5 mb-4" style={{ height: '435px', borderRadius: '10px' }}>
                            <div className="d-flex mb-3">
                                <i className="fa fa-2x fa-map-marker-alt text-primary flex-shrink-0 mr-3"></i>
                                <div className="mt-n1">
                                    <h5 className="text-light">Headquarters</h5>
                                    <p>Bucharest, Romania</p>
                                </div>
                            </div>
                            <div className="d-flex mb-3">
                                <i className="fa fa-2x fa-map-marker-alt text-primary flex-shrink-0 mr-3"></i>
                                <div className="mt-n1">
                                    <h5 className="text-light">Branch Office</h5>
                                    <p>Bucharest, Romania</p>
                                </div>
                            </div>
                            <div className="d-flex mb-3">
                                <i className="fa fa-2x fa-envelope-open text-primary flex-shrink-0 mr-3"></i>
                                <div className="mt-n1">
                                    <h5 className="text-light">Customer Service</h5>
                                    <p>support@swapit.com</p>
                                </div>
                            </div>
                            <div className="d-flex">
                                <i className="fa fa-2x fa-envelope-open text-primary flex-shrink-0 mr-3"></i>
                                <div className="mt-n1">
                                    <h5 className="text-light">Return & Refund</h5>
                                    <p className="m-0">refund@swapit.com</p>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    );
}

export default ContactSection;