import React, { useState, useEffect } from 'react';
import { animateScroll } from 'react-scroll';

function BackToTopButton() {
    const [isVisible, setIsVisible] = useState(false);

    useEffect(() => {
        const handleScroll = () => {
            if (window.scrollY > 100) {
                setIsVisible(true);
            } else {
                setIsVisible(false);
            }
        };
        window.addEventListener('scroll', handleScroll);

        return () => {
            window.removeEventListener('scroll', handleScroll);
        };
    }, []);
    const handleButtonClick = () => {
        animateScroll.scrollToTop({
            duration: 1500,
            smooth: 'easeInOutExp',
        });
    };

    return (
        <div
            className={`btn btn-lg btn-primary btn-lg-square back-to-top back-to-top ${isVisible ? 'show' : 'hide'}`}
            onClick={handleButtonClick}>
            <i className="fa fa-angle-double-up"></i>
        </div>
    );
}

export default BackToTopButton