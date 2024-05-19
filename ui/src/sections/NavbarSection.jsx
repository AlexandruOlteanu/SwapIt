import React, { useEffect, useState, lazy } from 'react'
import { useNavigate } from 'react-router-dom';

import Logo from '../logo/Logo.webp'

import '../css/NavbarSection.css'
import ApiBackendService from '../apiBackend/ApiBackendService';
import SearchBar from './SearchBar';

const HamburgerButton = lazy(() => import('../js/HamburgerButton'));

function NavbarSection() {

    const isLoggedIn = localStorage.getItem('isLoggedIn') === 'true';
    const [username, setUsername] = useState('');
    const navigate = useNavigate();

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

    const handleUserIconClick = async () => {
        if (!isLoggedIn) {
            window.location.href = '/user/auth';
        }

        const userData = await ApiBackendService.getAuthenticatedUserDetails({});
        setUsername(userData.username);
        const userId = userData.userId;
        navigate(`/users/${userData.username}`);
    };

    return (
        <div className='position-sticky'>
            {/* Navbar Start */}
            <div className="container-fluid nav-bar p-0">
                <div className="position-sticky background-nav">
                    <nav className="navbar navbar-expand-lg bg-secondary navbar-dark py-3 py-lg-0 pl-3">
                        <a href="/" className="navbar-brand" aria-label="Logo">
                            <img src={Logo} alt="SwapIt Logo" className="swapit-logo" />
                        </a>

                        <SearchBar />

                        <div onClick={openMenu}>
                            <HamburgerButton />
                        </div>

                        <div className="collapse navbar-collapse justify-content-between px-3 menu menu-3" id="navbarCollapse">
                            <div className="navbar-nav ml-auto py-0 justify-content-end">
                                {!isLoggedIn && (
                                    <>
                                        <div className="col-xl-12 col-lg-4 col-md-6 px-2">
                                            <div className="nav-item nav-link">
                                                <a href="/user/auth">
                                                    <button className="btn btn-primary btn-block" type="submit" style={{ width: "120px", borderRadius: '10px' }}>Sign In</button>
                                                </a>
                                            </div>
                                        </div>
                                    </>

                                )}

                                {isLoggedIn && (
                                    <>
                                        <div className="col-lg-4 col-md-6 px-2 ml-4 pl-2">
                                            <div className="nav-item nav-link">
                                                <a onClick={handleUserIconClick}>
                                                    <div className="iconContainer">
                                                        <i className="fa fa-solid fa-plus-circle navbar-icon" style={{ color: 'var(--primary)', cursor: 'pointer' }}></i>
                                                        <span className="profile-text">Add Product</span>
                                                    </div>
                                                </a>
                                            </div>
                                        </div>
                                        <div className="col-lg-4 col-md-6 px-2 ml-4 pl-2">
                                            <div className="nav-item nav-link">
                                                <a onClick={handleUserIconClick}>
                                                    <div className="iconContainer">
                                                        <i className="fa fa-regular fa-user navbar-icon" style={{ color: 'var(--primary)', cursor: 'pointer' }}></i>
                                                        <span className="profile-text">My Profile</span>
                                                    </div>
                                                </a>
                                            </div>
                                        </div>
                                    </>
                                )}
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