import React from 'react'

function WhatWeNeedSection({ title }) {
    return (
        <div className="container-xl pt-5 pb-3">
            <h1 className="display-4 text-center mb-5" style={{ color: 'var(--light)' }}> {title} </h1>
            <div className="row justify-content-center">
                <div className="col-lg-2 d-flex flex-column justify-content-center align-items-center">
                    <div className="iconContainer">
                        <i className="fa-solid fa-briefcase fa-3x" style={{ color: 'var(--primary)' }}></i>
                    </div>
                    <div className="textData width-section" style={{ fontSize: '1.5rem' }}>
                        Business Description & Goals
                    </div>
                </div>
                <div className="col-lg-2 d-flex flex-column justify-content-center align-items-center">
                    <div className="iconContainer">
                        <i className="fa-solid fa-comment fa-3x" style={{ color: 'var(--primary)' }}></i>
                    </div>
                    <div className="textData width-section" style={{ fontSize: '1.5rem' }}>
                        Communication
                    </div>
                </div>
                <div className="col-lg-2 d-flex flex-column justify-content-center align-items-center">
                    <div className="iconContainer">
                        <i className="fa-solid fa-money-check-dollar fa-3x" style={{ color: 'var(--primary)' }}></i>
                    </div>
                    <div className="textData width-section" style={{ fontSize: '1.5rem' }}>
                        Budget Insights
                    </div>
                </div>
                <div className="col-lg-2 d-flex flex-column justify-content-center align-items-center">
                    <div className="iconContainer">
                        <i className="fa-solid fa-timeline fa-3x" style={{ color: 'var(--primary)' }}></i>
                    </div>
                    <div className="textData width-section" style={{ fontSize: '1.5rem' }}>
                        Deadline and Timeline
                    </div>
                </div>
            </div>
        </div>
    )
}

export default WhatWeNeedSection
