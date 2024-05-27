import React, { useRef } from 'react'

import emailjs from '@emailjs/browser';

function FooterSection() {

    const form = useRef();

    const emailSymbol = '@';

    const sendEmail = (e) => {
        e.preventDefault();

        let contact_email = document.getElementById("businessletter_email").value;

        if (!contact_email.includes(emailSymbol)) {
            alert("\u26d4 The email adress is not valid.");
        }
        else {
            emailjs.sendForm(process.env.REACT_APP_EMAIL_JS_SERVICE_ID, process.env.REACT_APP_EMAIL_JS_BUSINESS_LETTER_TEMPLATE_ID, form.current, process.env.REACT_APP_EMAIL_JS_PUBLIC_KEY)
                .then((result) => {
                    console.log(result.text);
                }, (error) => {
                    console.log(error.text);
                });
            alert("\u2705 Succesfully subscribed to our Businessletter")
        }
    };

    return (
        <React.Fragment>
            <div className="container-fluid bg-secondary py-5 px-sm-3 px-md-5">
                <div className="row pt-5">
                    <div className="col-lg-3 col-md-6 mb-5">
                        <h4 className=" text-light mb-4">Get In Touch</h4>
                        <p className="mb-2"><i className="fa fa-map-marker-alt text-white mr-3"></i>Bucharest, Romania</p>
                        <p className="mb-2"><i className="fa fa-phone-alt text-white mr-3"></i>+40 729 868 263</p>
                        <p><i className="fa fa-envelope text-white mr-3"></i>support@swapit.com</p>
                        <h6 className=" text-white py-2">Follow Us</h6>
                        <div className="d-flex justify-content-start">
                            <a className="btn btn-lg btn-primary btn-dark btn-lg-square mr-2" href="https://twitter.com/SwapIt" aria-label="Twitter"><i className="fab fa-twitter smIcon"></i></a>
                            <a className="btn btn-lg btn-primary btn-dark btn-lg-square mr-2" href="https://www.facebook.com/SwapIt" aria-label="Facebook"><i className="fab fa-facebook-f smIcon"></i></a>
                            <a className="btn btn-lg btn-primary btn-dark btn-lg-square mr-2" href="https://www.linkedin.com/company/swapit/" aria-label="Linkedin"><i className="fab fa-linkedin-in smIcon"></i></a>
                            <a className="btn btn-lg btn-primary btn-dark btn-lg-square" href="https://www.instagram.com/swapit/" aria-label="Instagram"><i className="fab fa-instagram smIcon"></i></a>
                        </div>
                    </div>
                    <div className="col-lg-3 col-md-6 mb-5">
                        <h4 className=" text-light mb-4">Usefull Links</h4>
                        <div className="d-flex flex-column justify-content-start">
                            <a className="text-body mb-2" href="/info/privacy-policy" aria-label="Privacy"><i className="fa fa-angle-right text-white mr-2"></i>Private Policy</a>
                            <a className="text-body mb-2" href="/info/terms-and-conditions" aria-label="Terms&Conditions"><i className="fa fa-angle-right text-white mr-2"></i>Term & Conditions</a>
                            <a className="text-body mb-2" aria-label="Registration"><i className="fa fa-angle-right text-white mr-2"></i>New Member Registration</a>
                            <a className="text-body mb-2" aria-label="Affiliate"><i className="fa fa-angle-right text-white mr-2"></i>Affiliate Programme</a>
                            <a className="text-body mb-2" aria-label="Refund"><i className="fa fa-angle-right text-white mr-2"></i>Refund Policy</a>
                        </div>
                    </div>

                    <div className="col-lg-3 col-md-6 mb-5">
                        <h4 className=" text-light mb-4">Business</h4>
                        <p className="mb-4">Get on board with up to date strategies and skills to succed in your brand building journey!</p>
                        <div className="w-100 mb-3">
                            <form ref={form}>
                                <div className="input-group">
                                    <input type="text" className="form-control bg-dark border-dark" style={{ padding: '25px' }} placeholder="Your Email" name="user_email" id="businessletter_email" />
                                    <div className="input-group-append">
                                        <button className="btn btn-primary  px-3" onClick={sendEmail}>Sign Up</button>
                                    </div>
                                </div>
                            </form>

                        </div>
                        <i>You can always unsubscribe from our business newsletter</i>
                    </div>
                </div>
            </div>
            <div className="container-fluid bg-dark py-4 px-sm-3 px-md-5">
                <p className="mb-2 text-center text-body">&copy; <a href="">SwapIt 2023-2024</a>. All Rights Reserved.</p>
            </div>
        </React.Fragment>
    )
}

export default FooterSection