import React from 'react';
import { VerticalTimeline, VerticalTimelineElement } from 'react-vertical-timeline-component';

import 'react-vertical-timeline-component/style.min.css';
import '../css/Timeline.css';

function Timeline({ items }) {
    return (
        <VerticalTimeline>
            {
                items.map((element, index) => (
                    <VerticalTimelineElement key={index}>
                        {element}
                    </VerticalTimelineElement>
                ))
            }
        </VerticalTimeline>
    );
}

export default Timeline;