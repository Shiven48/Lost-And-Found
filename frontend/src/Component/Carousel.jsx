// import React, { useState, useEffect } from 'react';
//
// const Carousel = () => {
//
//     // Pagination and sorting api using latest date
//     const data = [
//         { id: 1, name: "keto" },
//         { id: 2, name: "Aeto" },
//         { id: 3, name: "Peto" },
//         { id: 4, name: "Teto" },
//         { id: 5, name: "Geto" }
//     ];
//
//     const [currentIndex, setCurrentIndex] = useState(0);
//     const autoSlideInterval = 2000; // 2 seconds
//
//     useEffect(() => {
//         const interval = setInterval(() => {
//             setCurrentIndex((prevIndex) => (prevIndex + 1) % data.length);
//         }, autoSlideInterval);
//
//         return () => clearInterval(interval); // Cleanup on unmount
//     }, [data.length]);
//
//     return (
//         <div className="w-[90%] h-[80%] flex justify-center items-center">
//             {
//                 (data?.length) ?
//                     <div className="flex overflow-hidden">
//                         {data.map((item, index) => {
//                             const isCurrent = index === currentIndex;
//                             const isPrev = index === (currentIndex - 1 + data.length) % data.length;
//                             const isNext = index === (currentIndex + 1) % data.length;
//
//                             return (
//                                 <div
//                                     className={`m-4 rounded-2xl bg-[#858795] shadow-sm shadow-white
//                                     opacity-85 bg-opacity-30 backdrop-blur-md border-2 border-opacity-80 border-[#abb450]
//                                     ${isCurrent ? 'w-[40%] h-[90%]' : 'w-[30%] h-[80%]'}
//                                     transition-all duration-300 ${isCurrent || isPrev || isNext ? 'block' : 'hidden'}`}
//                                     key={item.id}
//                                 >
//                                     <span> {item.name} </span>
//                                 </div>
//                             );
//                         })}
//                     </div>
//                 : null
//             }
//         </div>
//     );
// }
//
// export default Carousel;


import React, { useState, useEffect } from 'react';
import { ChevronLeft, ChevronRight } from 'lucide-react';

const Carousel = () => {
    const [currentIndex, setCurrentIndex] = useState(0);

    const items = [
        {
            "id":1,
            "image":"sdncwndicmanjd",
            "name":"some name 1",
            "description":"my desc 1",
            "price":1000
        },
        {
            "id":2,
            "image":"sdncwndicmanjd",
            "name":"some name 2",
            "description":"my desc 2",
            "price":2000
        },
        {
            "id":3,
            "image":"sdncwndicmanjd",
            "name":"some name 3",
            "description":"my desc 3",
            "price":3000
        },
        {
            "id":4,
            "image":"sdncwndicmanjd",
            "name":"some name 4",
            "description":"my desc 4",
            "price":4000
        },
        {
            "id":5,
            "image":"sdncwndicmanjd",
            "name":"some name 5",
            "description":"my desc 5",
            "price":5000
        }
    ]


    useEffect(() => {
        const interval = setInterval(() => {
            setCurrentIndex((prevIndex) => (prevIndex + 1) % items.length);
        }, 3000); // Change slide every 3 seconds

        return () => clearInterval(interval);
    }, [items.length]);

    const getPrevIndex = (index) => (index - 1 + items.length) % items.length;
    const getNextIndex = (index) => (index + 1) % items.length;

    const handlePrev = () => {
        setCurrentIndex(getPrevIndex(currentIndex));
    };

    const handleNext = () => {
        setCurrentIndex(getNextIndex(currentIndex));
    };

    return (
        <div className="relative flex items-center justify-center w-full h-full">
            {items.map((item, index) => (
                <div
                    key={item.id}
                    className={`
            absolute m-4 rounded-2xl bg-[#858795] 
            shadow-sm shadow-white opacity-85 
            bg-opacity-30 backdrop-blur-md 
            border-2 border-opacity-80 border-[#abb450]
            transition-all duration-300
            ${index === currentIndex ? 'w-[40%] h-[90%] z-20' :
                        index === getPrevIndex(currentIndex) ? 'w-[30%] h-[80%] left-0 z-10' :
                            index === getNextIndex(currentIndex) ? 'w-[30%] h-[80%] right-0 z-10' : 'hidden'}
          `}
                >
                    <div className="flex flex-col h-full p-4">
                        {item.image && (
                            <img
                                src={item.image}
                                alt={item.name}
                                className="w-full h-48 object-cover rounded-t-xl"
                            />
                        )}
                        <div className="flex-grow p-4">
                            <h2 className="text-xl font-bold mb-2">{item.name}</h2>
                            {item.description && (
                                <p className="text-sm text-gray-600 mb-2">{item.description}</p>
                            )}
                            {item.price && (
                                <div className="font-semibold text-lg">
                                    ${item.price.toFixed(2)}
                                </div>
                            )}
                        </div>
                    </div>
                </div>
            ))}

            <button
                onClick={handlePrev}
                className="absolute left-0 z-30 p-2 bg-white/50 rounded-full shadow-md"
            >
                <ChevronLeft />
            </button>
            <button
                onClick={handleNext}
                className="absolute right-0 z-30 p-2 bg-white/50 rounded-full shadow-md"
            >
                <ChevronRight />
            </button>
        </div>
    );
};

export default Carousel;