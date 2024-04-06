import React from 'react'

import '../scss/HorizontalTimeline.scss'

function HorizontalTimeline({ items }) {
    return (
        <React.Fragment>
            <section className="showOnlyDesktop" id="timeline">
                {items.map((element, index) => (
                    <div className="tl-item" key={index}>
                        {element}
                    </div>
                ))}
            </section>

        </React.Fragment>
    )
}

export default HorizontalTimeline
