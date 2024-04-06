import React from 'react'

import '../css/Star4WaySection.css'

function Star4WaySection({ title, upItems, downItems }) {
    return (
        <React.Fragment>

            <h1 className="display-4 text-center mb-5" style={{ color: 'var(--light)', paddingTop: '3rem' }}> {title} </h1>
            <div className="container-lg pt-5 pb-3">
                <div className="rowContainer">
                    <div className="rowData">
                        {
                            upItems.map((element, index) => (
                                <React.Fragment key={index}>
                                    {element}
                                </React.Fragment>
                            ))
                        }
                        <div className="rowData-block showOnlyDesktop"></div>
                        {
                            downItems.map((element, index) => (
                                <React.Fragment key={index}>
                                    {element}
                                </React.Fragment>
                            ))
                        }
                    </div>
                </div>
            </div>

        </React.Fragment>
    )
}

export default Star4WaySection
