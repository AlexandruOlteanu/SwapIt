import React, { useEffect, lazy } from 'react'

import Logo from '../logo/Logo.webp'

import '../css/NavbarSection.css'

const HamburgerButton = lazy(() => import('../js/HamburgerButton'));

function NavbarSection() {

    function openMenuSection(id) {
        let menuSection = document.querySelector(id);
        if (menuSection.classList.contains("show")) {
            menuSection.classList.remove("show");
        }
        else {
            menuSection.classList.add("show");
        }
    }

    function openMenu() {
        let menuSection = document.querySelector("#navbarCollapse");
        if (menuSection.classList.contains("show")) {
            menuSection.classList.remove("show");
            const menuSections = document.querySelectorAll(".menuItemsDropdown");
            menuSections.forEach(section => {
                section.classList.remove("show");
            });
        }
        else {
            menuSection.classList.add("show");
        }
    }

    useEffect(() => {
        const currentUrl = window.location.href;
        const navLinks = document.querySelectorAll(".navbarMainItem");

        navLinks.forEach(link => {
            if (link.href === currentUrl) {
                link.classList.add("active");
            } else {
                link.classList.remove("active");
            }
        });
    }, []);

    return (
        <div className='position-sticky'>
            {/* Navbar Start */}
            <div className="container-fluid nav-bar p-0">
                <div className="position-sticky px-lg-5 background-nav">
                    <nav className="navbar navbar-expand-lg bg-secondary navbar-dark py-3 py-lg-0 pl-3 pl-lg-5">
                        <a href="/" className="navbar-brand" aria-label="Logo">
                            <img src={Logo} alt="SwapIt Logo" className="swapit-logo" />
                        </a>

                        <div onClick={openMenu}>
                            <HamburgerButton />
                        </div>

                        <div className="collapse navbar-collapse justify-content-between px-3 menu menu-3" id="navbarCollapse">
                            <div className="navbar-nav ml-auto py-0">
                                <div className="nav-item nav-link">
                                    <a href="/" className="navbarMainItem home" aria-label="Home">Home</a>
                                </div>
                                <div className="nav-item nav-link">
                                    <a href="/about" className="navbarMainItem about" aria-label="About">About</a>
                                </div>
                                <div className="nav-item nav-link">
                                    <a href="/workflow-system" className="navbarMainItem workflowSystem" aria-label="Workflow System">Workflow System</a>
                                </div>
                                <div className="nav-item dropdown">
                                    <a href="#" className="nav-link dropdown-toggle" data-toggle="dropdown" aria-label="Services" onClick={() => openMenuSection("#servicesDropdown")}>Services</a>
                                    <div className="dropdown-menu rounded-0 m-0 menuItemsDropdown" id="servicesDropdown">
                                        <div className="dropdown-item-style">
                                            <a href="/services/software-development" className="dropdown-item" style={{ fontSize: '1.2rem' }} aria-label="Software">Software Development</a>
                                        </div>
                                        <div className="dropdown-item-style">
                                            <a href="/services/business-slogans" className="dropdown-item" style={{ fontSize: '1.2rem' }} aria-label="Names">Business Names & Slogans</a>
                                        </div>
                                        <div className="dropdown-item-style">
                                            <a href="/services/facebook-ads" className="dropdown-item" style={{ fontSize: '1.2rem' }} aria-label="FacebookAds">Facebook Ads</a>
                                        </div>
                                        <div className="dropdown-item-style">
                                            <a href="/services/copywriting" className="dropdown-item" style={{ fontSize: '1.2rem' }} aria-label="Copywriting">Copywriting</a>
                                        </div>
                                        <div className="dropdown-item-style">
                                            <a href="/services/seo-ecommerce" className="dropdown-item" style={{ fontSize: '1.2rem' }} aria-label="SEO">SEO & Ecommerce</a>
                                        </div>
                                        <div className="dropdown-item-style">
                                            <a href="/services/graphic-design" className="dropdown-item" style={{ fontSize: '1.2rem' }} aria-label="Graphic">Graphic Design</a>
                                        </div>
                                        <div className="dropdown-item-style">
                                            <a href="/services/instagram-growth" className="dropdown-item" style={{ fontSize: '1.2rem' }} aria-label="Instagram">Instagram Growth</a>
                                        </div>
                                        <div className="dropdown-item-style">
                                            <a href="/services/business-plans" className="dropdown-item" style={{ fontSize: '1.2rem' }} aria-label="BusinessPlans">Business Plans</a>
                                        </div>
                                        <div className="dropdown-item-style">
                                            <a href="/services/google-ads" className="dropdown-item" style={{ fontSize: '1.2rem' }} aria-label="GoogleAds">Google Ads</a>
                                        </div>
                                    </div>
                                </div>
                                <div className="nav-item dropdown">
                                    <a href="#" className="nav-link dropdown-toggle" aria-label="Info" data-toggle="dropdown" onClick={() => openMenuSection("#infoDropdown")}>Info</a>
                                    <div className="dropdown-menu rounded-0 m-0 menuItemsDropdown" id="infoDropdown">
                                        <div className="dropdown-item-style">
                                            <a href="/info/privacy-policy" className="dropdown-item" style={{ fontSize: '1.2rem' }} aria-label="Privacy">Privacy Policy</a>
                                        </div>
                                        <div className="dropdown-item-style">
                                            <a href="/info/terms-and-conditions" className="dropdown-item" style={{ fontSize: '1.2rem' }} aria-label="Terms&Conditions">Terms & Conditions</a>
                                        </div>
                                    </div>
                                </div>
                                <div className="nav-item nav-link">
                                    <a href="/contact" className="navbarMainItem contact" aria-label="Contact">Contact</a>
                                </div>
                            </div>
                        </div>
                    </nav>
                </div>
            </div>
            {/* Navbar End */}
        </div>
    )
}

export default NavbarSection