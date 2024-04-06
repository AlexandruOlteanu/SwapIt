import React, { lazy } from 'react'

const VendorsCarousel = lazy(() => import('../js/VendorsCarousel'));

function VendorsSection() {
    return (
        <React.Fragment>
            <div className="container-fluid py-5">
                <h1 className="display-4  text-center mb-5" style={{ position: "relative", zIndex: "1 !important" }}>Our Trusted Partners</h1>
                <div className="container py-5">
                    <VendorsCarousel />
                </div>
            </div>
        </React.Fragment>
    )
}

export default VendorsSection