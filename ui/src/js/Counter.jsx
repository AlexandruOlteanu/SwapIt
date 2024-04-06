import React, { useState, useEffect, useRef } from 'react';

function Counter({ countTo, plus }) {
    const [count, setCount] = useState(0);
    const ref = useRef(null);

    useEffect(() => {
        const observer = new IntersectionObserver(([entry]) => {
            if (entry.isIntersecting) {
                let timer;
                if (count < countTo) {
                    timer = setTimeout(() => {
                        setCount(count + 1);
                    }, 2000 / countTo);
                }
                return () => clearTimeout(timer);
            }
        });
        observer.observe(ref.current);
        return () => observer.disconnect();
    }, [count, countTo]);

    if (plus === true) {
        return (
            <h1 ref={ref} className='m-0'>{count}+</h1>
        );
    }

    return (
        <h1 ref={ref} className='m-0'>{count}</h1>
    );
}

export default Counter;