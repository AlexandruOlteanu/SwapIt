import React, { useEffect, useState } from 'react'

import '../css/Preloader.css'

function Preloader() {

    const [showPreloader, setShowPreloader] = useState(true);
    const [hidePreloader, setHidePreloader] = useState(false);

    useEffect(() => {
        const timer = setTimeout(() => {
            setShowPreloader(false);
        }, 500);

        return () => clearTimeout(timer);
    }, []);

    useEffect(() => {
        if (!showPreloader) {
            const timer = setTimeout(() => {
                setHidePreloader(true);
            }, 500);
            return () => clearTimeout(timer);
        }
    }, [showPreloader]);

    return (
        <div className={`preloader${showPreloader ? "" : " fadeout"}${hidePreloader ? " hide" : ""}`}>
            <span className="square"></span>
        </div>
    )
}

export default Preloader