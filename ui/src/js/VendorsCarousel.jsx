import React, { useRef, useEffect } from 'react';
import OwlCarousel from 'react-owl-carousel';

import '../lib/owlcarousel/assets/owl.carousel.css'
import '../lib/owlcarousel/assets/owl.theme.default.css'

import vendor_1 from "../img/vendor-1.webp"
import vendor_2 from "../img/vendor-2.webp"
import vendor_3 from "../img/vendor-3.webp"
import vendor_4 from "../img/vendor-4.webp"
import vendor_5 from "../img/vendor-5.webp"
import vendor_6 from "../img/vendor-6.webp"
import vendor_7 from "../img/vendor-7.webp"
import vendor_8 from "../img/vendor-8.webp"

const VendorsCarousel = () => {
    const carouselRef = useRef(null);

    useEffect(() => {
        setTimeout(() => {

        }, 100);
    }, []);

    return (
        <OwlCarousel
            ref={carouselRef}
            className="vendor-carousel owl-carousel owl-theme"
            loop={true}
            margin={30}
            dots={false}
            center={true}
            autoplay={true}
            smartSpeed={3000}
            responsive={{
                0: {
                    items: 2,
                },
                576: {
                    items: 3,
                },
                768: {
                    items: 4,
                },
                992: {
                    items: 5,
                },
                1200: {
                    items: 6,
                },
            }}
        >
            <div className="bg-light p-4 br-10 item">
                <a href="https://www.fiverr.com/" target="_blank" rel="noopener noreferrer" aria-label="Vendor">
                    <img src={vendor_1} alt="" />
                </a>
            </div>
            <div className="bg-light p-4 br-10 item">
                <a href="https://landlord-go.com/" target="_blank" rel="noopener noreferrer" aria-label="Vendor">
                    <img src={vendor_2} alt="" />
                </a>
            </div>
            <div className="bg-light p-4 br-10 item">
                <a href="https://www.atlasaffairs.com/" target="_blank" rel="noopener noreferrer" aria-label="Vendor">
                    <img src={vendor_3} alt="" />
                </a>
            </div>
            <div className="bg-light p-4 br-10 item">
                <a href="https://newtronx.com/" target="_blank" rel="noopener noreferrer" aria-label="Vendor">
                    <img src={vendor_4} alt="" />
                </a>
            </div>
            <div className="bg-light p-4 br-10 item">
                <a href="https://intelblaze.com/" target="_blank" rel="noopener noreferrer" aria-label="Vendor">
                    <img src={vendor_5} alt="" />
                </a>
            </div>
            <div className="bg-light p-4 br-10 item">
                <a href="https://vendduro.com/" target="_blank" rel="noopener noreferrer" aria-label="Vendor">
                    <img src={vendor_6} alt="" />
                </a>
            </div>
            <div className="bg-light p-4 br-10 item">
                <a href="https://www.ajsmart.com/" target="_blank" rel="noopener noreferrer" aria-label="Vendor">
                    <img src={vendor_7} alt="" />
                </a>
            </div>
            <div className="bg-light p-4 br-10 item">
                <a href="" target="_blank" rel="noopener noreferrer" aria-label="Vendor">
                    <img src={vendor_8} alt="" />
                </a>
            </div>
        </OwlCarousel>
    );
};

export default VendorsCarousel;
