import { useState } from "react";

const Signup = () => {

    const [email,setEmail] = useState("")

    const handleSubmit = () => {    
    }

    const handleEmailChange = (e) => {
        setEmail(e.target.value)
    }
    
    return(
        <div 
            className="fixed top-1/2 left-1/2 transform -translate-x-1/2 -translate-y-1/2 bg-[#858795] flex justify-center items-start
                w-[28%] h-[70%] rounded-xl shadow-lg shadow-black border-2 border-[#abb470] overflow-hidden bg-opacity-40
            "
        >
            <form onSubmit={handleSubmit}
            
            className="w-[90%] h-[90%] mt-2 rounded">
            <p
            className="text-left mt-10 font-bold text-white font-sans"
            >Log In to Lost & Found</p>
                <p
                className="text-left mt-10 font-medium text-gray-400 font-sans"
                >Email</p>
                <input 
                    type="email" 
                    placeholder="Enter Email" 
                    value={email}
                    onChange={(e) => handleEmailChange(e)}
                    required 
                    className="w-full bg-inherit border border-white rounded-md mt-1 pl-5 py-1 text-white placeholder:text-white"
                />
                <p
                className="text-left mt-10 font-medium text-gray-400 font-sans"
                >Password</p>
                <input 
                    type="password" 
                    placeholder="Enter Password" 
                    value={email}
                    onChange={(e) => handleEmailChange(e)}
                    required 
                    className="w-full bg-inherit border border-white text-white rounded-md mt-1 pl-5 py-1 placeholder:text-white"
                />
                <input
                    type="submit"
                    className="w-full border-2 border-black text-gray-900 font-medium font-sans rounded-md mt-16 py-1 bg-[#abb450]"
                />
            </form>
        </div>
    )
}

export default Signup;