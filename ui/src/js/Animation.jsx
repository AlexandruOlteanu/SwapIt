import React, { useMemo } from 'react';
import Lottie from 'lottie-react';

const Animation = ({ classes, animationData }) => {
    const defaultOptions = useMemo(() => {
        return {
            loop: true,
            autoplay: true,
            animationData: animationData,
            rendererSettings: {
                preserveAspectRatio: 'xMidYMid slice'
            }
        };
    }, [animationData]);

    return (
        <div className={classes}>
            <Lottie options={defaultOptions} animationData={animationData} />
        </div>
    );
};

export default Animation;
