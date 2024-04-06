import React, { useRef, useEffect } from 'react';
import OwlCarousel from 'react-owl-carousel';

import '../lib/owlcarousel/assets/owl.carousel.css'
import '../lib/owlcarousel/assets/owl.theme.default.css'

import alexandru_team from "../img/alexandru_team.webp"
import andra_team from "../img/andra_team.webp"
import andrei_team from "../img/andrei_team.webp"
import bianca_team from "../img/bianca_team.webp"
import adrian_team from "../img/adrian_team.webp"

function TeamCarousel() {
    const carouselRef = useRef(null);

    useEffect(() => {
        setTimeout(() => {

        }, 100);
    }, []);

    return (

        <OwlCarousel
            ref={carouselRef}
            className="owl-carousel team-carousel position-relative"
            style={{ padding: '0 30px' }}
            loop={true}
            margin={30}
            dots={false}
            center={true}
            autoplay={true}
            smartSpeed={3000}
            nav={true}
            responsive={{
                0: {
                    items: 1,
                },
                576: {
                    items: 1,
                },
                768: {
                    items: 2,
                },
                992: {
                    items: 3,
                },
            }}
        >
            <div className="team-item br-10 item">
                <img className="img-fluid w-100" src={alexandru_team} alt="" />
                <div className="position-relative py-4">
                    <h4 className="">Alexandru Olteanu</h4>
                    <p className="m-0">CEO & Founder</p>
                    <div className="team-social position-absolute w-100 h-100 d-flex align-items-center justify-content-center">
                        <a className="btn btn-lg btn-primary btn-lg-square mx-1" href="https://twitter.com/AlexOlteanu2001" aria-label="Twitter"><i className="fab fa-twitter"></i></a>
                        <a className="btn btn-lg btn-primary btn-lg-square mx-1" href="https://www.facebook.com/alexolteanu2001/" aria-label="Facebook"><i className="fab fa-facebook-f"></i></a>
                        <a className="btn btn-lg btn-primary btn-lg-square mx-1" href="https://www.linkedin.com/in/alexandruolteanu2001/" aria-label="Linkedin"><i className="fab fa-linkedin-in"></i></a>
                    </div>
                </div>
            </div>
            <div className="team-item br-10 item">
                <img className="img-fluid w-100" src={andra_team} alt="" />
                <div className="position-relative py-4">
                    <h4 className="">Andra Pescaru</h4>
                    <p className="m-0">Software Project Manager</p>
                    <div className="team-social position-absolute w-100 h-100 d-flex align-items-center justify-content-center">
                        <a className="btn btn-lg btn-primary btn-lg-square mx-1" href="" aria-label="Twitter"><i className="fab fa-twitter"></i></a>
                        <a className="btn btn-lg btn-primary btn-lg-square mx-1" href="https://www.facebook.com/andraanamaria.pescaru" aria-label="Facebook"><i className="fab fa-facebook-f"></i></a>
                        <a className="btn btn-lg btn-primary btn-lg-square mx-1" href="https://www.linkedin.com/in/andrapescaru/" aria-label="Linkedin"><i className="fab fa-linkedin-in"></i></a>
                    </div>
                </div>
            </div>
            <div className="team-item br-10 item">
                <img className="img-fluid w-100" src={andrei_team} alt="" />
                <div className="position-relative py-4">
                    <h4 className="">Andrei Ibinceanu</h4>
                    <p className="m-0">Marketing Director</p>
                    <div className="team-social position-absolute w-100 h-100 d-flex align-items-center justify-content-center">
                        <a className="btn btn-lg btn-primary btn-lg-square mx-1" href="" aria-label="Twitter"><i className="fab fa-twitter"></i></a>
                        <a className="btn btn-lg btn-primary btn-lg-square mx-1" href="https://www.facebook.com/andrei.ibinceanu.3" aria-label="Facebook"><i className="fab fa-facebook-f"></i></a>
                        <a className="btn btn-lg btn-primary btn-lg-square mx-1" href="" aria-label="Linkedin"><i className="fab fa-linkedin-in"></i></a>
                    </div>
                </div>
            </div>
            <div className="team-item br-10 item">
                <img className="img-fluid w-100" src={bianca_team} alt="" />
                <div className="position-relative py-4">
                    <h4 className="">Bianca Constantin</h4>
                    <p className="m-0">Graphic Designer</p>
                    <div className="team-social position-absolute w-100 h-100 d-flex align-items-center justify-content-center">
                        <a className="btn btn-lg btn-primary btn-lg-square mx-1" href="" aria-label="Twitter"><i className="fab fa-twitter"></i></a>
                        <a className="btn btn-lg btn-primary btn-lg-square mx-1" href="" aria-label="Facebook"><i className="fab fa-facebook-f"></i></a>
                        <a className="btn btn-lg btn-primary btn-lg-square mx-1" href="" aria-label="Linkedin"><i className="fab fa-linkedin-in"></i></a>
                    </div>
                </div>
            </div>
            <div className="team-item br-10 item">
                <img className="img-fluid w-100" src={adrian_team} alt="" />
                <div className="position-relative py-4">
                    <h4 className="">Adrian Popescu</h4>
                    <p className="m-0">Business Engineer</p>
                    <div className="team-social position-absolute w-100 h-100 d-flex align-items-center justify-content-center">
                        <a className="btn btn-lg btn-primary btn-lg-square mx-1" href="" aria-label="Twitter"><i className="fab fa-twitter"></i></a>
                        <a className="btn btn-lg btn-primary btn-lg-square mx-1" href="" aria-label="Facebook"><i className="fab fa-facebook-f"></i></a>
                        <a className="btn btn-lg btn-primary btn-lg-square mx-1" href="https://www.linkedin.com/in/adrianpopescupx/" aria-label="Linkedin"><i className="fab fa-linkedin-in"></i></a>
                    </div>
                </div>
            </div>
        </OwlCarousel>
    );
};

export default TeamCarousel