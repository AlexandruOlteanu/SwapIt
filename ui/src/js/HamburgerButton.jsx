import React, { useState } from "react";
import classNames from "classnames";

function HamburgerButton() {
    const [isClicked, setIsClicked] = useState(false);

    const handleClick = () => {
        setIsClicked(!isClicked);
    };

    const buttonClasses = classNames("navbar-toggler", { pushed: isClicked });

    return (

        <button
            type="button"
            aria-label="HamburgerButton"
            className={buttonClasses}
            data-toggle="collapse"
            data-target="#navbarCollapse"
            onClick={handleClick}
        >

            <span id="nav-container">
                <div className="toggle-icon">
                    <span className="bar"></span>
                    <span className="bar"></span>
                    <span className="bar"></span>
                </div>
            </span>
        </button>
    );
}

export default HamburgerButton
