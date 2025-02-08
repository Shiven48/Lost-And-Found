import React, { useState, useEffect } from 'react';
import { ChevronLeft, ChevronRight } from 'lucide-react';
import axios from "axios"
import img from "../assets/images/buds.jpg"

const Carousel = () => {
    const [currentIndex, setCurrentIndex] = useState(0);
    const [items, setItems] = useState([])
    const [loading, setLoading] = useState(false)
    const [error, setError] = useState(false)

    const fetchItems = async () => {
        try {
            setLoading(true)
            const response = await axios.get("http://localhost:8080/api/items/lost")
            setItems(response.data)
            console.log(response.data)
            setLoading(false)
        } catch (error) {
            console.error('Error fetching items:', error);
            setError(true)
            setLoading(false)
        }
    }

    useEffect(() => {
        fetchItems()
    }, [])

    useEffect(() => {
        if (items && items.length > 0) {
            const interval = setInterval(() => {
                setCurrentIndex((prevIndex) => (prevIndex + 1) % items.length);
            }, 2000)
            return () => clearInterval(interval)
        }
    }, [items])

    const getPrevIndex = (index) => (index - 1 + items.length) % items.length;
    const getNextIndex = (index) => (index + 1) % items.length;

    const handlePrev = () => {
        setCurrentIndex(getPrevIndex(currentIndex));
    };

    const handleNext = () => {
        setCurrentIndex(getNextIndex(currentIndex));
    };

    if (loading) return <div>Loading...</div>
    if (error) return <div>Error loading items</div>

    console.log(items.image)

    return (
        <div className="relative flex items-center justify-center w-full h-full">
            {items ? items.map((item, index) => (
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
                            {item.objImage && (
                                <img
                                    src={item.objImage}
                                    alt={item.name}
                                    className="w-full h-48 object-cover rounded-t-xl"
                                />
                             )}
                            <div className="flex-grow p-4">
                            <span>This is image</span>
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
                ))
                : null}

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