import React, { useState, useEffect } from 'react';

const Carousel = () => {

    // Pagination and sorting api using latest date
    const data = [
        { id: 1, name: "keto" },
        { id: 2, name: "Aeto" },
        { id: 3, name: "Peto" },
        { id: 4, name: "Teto" },
        { id: 5, name: "Geto" }
    ];

    const [currentIndex, setCurrentIndex] = useState(0);
    const autoSlideInterval = 2000; // 2 seconds

    useEffect(() => {
        const interval = setInterval(() => {
            setCurrentIndex((prevIndex) => (prevIndex + 1) % data.length);
        }, autoSlideInterval);

        return () => clearInterval(interval); // Cleanup on unmount
    }, [data.length]);

    return (
        <div className="w-[90%] h-[80%] flex justify-center items-center">
            {
                (data?.length) ? 
                    <div className="flex overflow-hidden">
                        {data.map((item, index) => {
                            const isCurrent = index === currentIndex;
                            const isPrev = index === (currentIndex - 1 + data.length) % data.length;
                            const isNext = index === (currentIndex + 1) % data.length;

                            return (
                                <div 
                                    className={`m-4 rounded-2xl bg-[#858795] shadow-sm shadow-white 
                                    opacity-85 bg-opacity-30 backdrop-blur-md border-2 border-opacity-80 border-[#abb450]
                                    ${isCurrent ? 'w-[40%] h-[90%]' : 'w-[30%] h-[80%]'} 
                                    transition-all duration-300 ${isCurrent || isPrev || isNext ? 'block' : 'hidden'}`} 
                                    key={item.id}
                                >
                                    <span> {item.name} </span>
                                </div>
                            );
                        })}
                    </div>
                : null
            }
        </div>
    );
}

export default Carousel;