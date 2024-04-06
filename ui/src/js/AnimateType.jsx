import React, { useEffect } from "react";
import Typed from 'typed.js'

function AnimateType({ className, strings }) {
    useEffect(() => {
        const typeClass = document.getElementsByClassName(className);
        if (typeClass.length !== 0) {
            new Typed(`.${className}`, {
                strings: strings,
                typeSpeed: 120,
                backSpeed: 50,
                loop: true,
                cursorChar: "",
            });
        }
    }, [className, strings]);

    return <span className={className}></span>;
}

export default AnimateType