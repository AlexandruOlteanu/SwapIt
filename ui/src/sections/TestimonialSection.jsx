import React, { lazy } from 'react'

const TestimonialCarousel = lazy(() => import('../js/TestimonialCarousel'));

function TestimonialSection() {
    return (
        <div className="container-fluid py-5">
            <div className="container py-5">
                <h1 className="display-4 text-center mb-5">Our Client's Say</h1>
                <TestimonialCarousel />
            </div>
        </div>
    )
}

export default TestimonialSection