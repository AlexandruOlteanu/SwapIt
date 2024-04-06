import React, { useRef, useEffect } from 'react';
import OwlCarousel from 'react-owl-carousel';

import '../lib/owlcarousel/assets/owl.carousel.css'
import '../lib/owlcarousel/assets/owl.theme.default.css'

import testimonial_1 from "../img/testimonial-1.webp"
import testimonial_2 from "../img/testimonial-2.webp"
import testimonial_3 from "../img/testimonial-3.webp"
import testimonial_4 from "../img/testimonial-4.webp"

function TestimonialCarousel() {
    const carouselRef = useRef(null);

    useEffect(() => {
        setTimeout(() => {

        }, 100);
    }, []);

    return (

        <OwlCarousel
            ref={carouselRef}
            className="owl-carousel testimonial-carousel"
            loop={true}
            margin={30}
            dots={false}
            center={true}
            autoplay={true}
            smartSpeed={3000}
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

            <div className="testimonial-item br-10 d-flex flex-column justify-content-center px-4 item">
                <div className="d-flex align-items-center justify-content-between mb-3">
                    <img className="img-fluid ml-n4" src={testimonial_1} alt="" />
                    <h1 className="display-2 text-white m-0 fa fa-quote-right"></h1>
                </div>
                <h4 className=" mb-2">Brandon Huy</h4>
                <i className="mb-2">Fitness Trainer</i>
                <p className="m-0">I needed a website ASAP and SwapIt team made it possible! They delivered fast and perfect, exectly what I was looking for. I will reach out for more help in the near future!</p>
            </div>
            <div className="testimonial-item br-10 d-flex flex-column justify-content-center px-4 item">
                <div className="d-flex align-items-center justify-content-between mb-3">
                    <img className="img-fluid ml-n4" src={testimonial_2} alt="" />
                    <h1 className="display-2 text-white m-0 fa fa-quote-right"></h1>
                </div>
                <h4 className=" mb-2">Marie Johnson</h4>
                <i className="mb-2">Real Estate Agent</i>
                <p className="m-0">A great experience working with SwapIt! Excellent service and communication! I received a great variety of names to choose for my business! They definitely know what they're doing. Thank you! Highly recommended!</p>
            </div>
            <div className="testimonial-item br-10 d-flex flex-column justify-content-center px-4 item">
                <div className="d-flex align-items-center justify-content-between mb-3">
                    <img className="img-fluid ml-n4" src={testimonial_3} alt="" />
                    <h1 className="display-2 text-white m-0 fa fa-quote-right"></h1>
                </div>
                <h4 className=" mb-2">Paval Delis</h4>
                <i className="mb-2">Construction Entrepreneur</i>
                <p className="m-0">I outsourced my business plan to SwapIt and received a comprehensive PDF in under a week. It included competitor research, capital management, and area targets. I am thrilled with the result.</p>
            </div>
            <div className="testimonial-item br-10 d-flex flex-column justify-content-center px-4 item">
                <div className="d-flex align-items-center justify-content-between mb-3">
                    <img className="img-fluid ml-n4" src={testimonial_4} alt="" />
                    <h1 className="display-2 text-white m-0 fa fa-quote-right"></h1>
                </div>
                <h4 className=" mb-2">Catalina Murrer</h4>
                <i className="mb-2">Web Designer</i>
                <p className="m-0">Glad that I found SwapIt, I needed to promote myself better so I can get more jobs so I reached out for help. So happy I did, they didn't only get the task done but they also gave me insights on how to mentain the customers flow from now one.</p>
            </div>

        </OwlCarousel>
    );
};

export default TestimonialCarousel