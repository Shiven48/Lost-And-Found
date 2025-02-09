import { useState } from "react";
import { NavLink } from "react-router";
import Logo from "./Logo";
import em from "../assets/svg/email.png"
const Signup = () => {

    const [email,setEmail] = useState("")
    const [password,setPassword] = useState("")

    const handleSubmit = () => {    
    }

    const handleEmailChange = (e) => {
        setEmail(e.target.value)
    }

    const handlePasswordChange = (e) => {
        setPassword(e.target.value)
    }
    
    return(
        <div>
            <div
            className="ml-2 justify-start flex"
            >
                <Logo />
            </div>
            <div
            className="ml-2 justify-end flex mt-6 mr-4">
            <NavLink
                to="/"
                className={({ isActive }) => 
                    isActive 
                        ? `w-20 bg-[#abb450] text-black rounded-xl text-base capitalize text-center m-1 border-2 border-black font-semibold hover:bg-[#abb466] mr-6`
                        : "w-20 bg-[#0e1a36] text-base rounded-xl text-center m-1 border border-[#abb466] font-semibold hover:text-[#abb466]"
                }
            >
                Home
            </NavLink>
            </div>
        <div 
            className="fixed top-1/2 left-1/2 transform -translate-x-1/2 -translate-y-1/2 bg-[#858795] flex justify-center items-start
                w-[28%] h-[70%] rounded-xl shadow-lg shadow-black border-2 border-[#abb470] overflow-hidden bg-opacity-40
            "
        >
            <form onSubmit={handleSubmit}
            className="w-[90%] h-[90%] mt-2 rounded flex flex-col">
            <p
            className="text-left mt-10 font-bold text-white font-sans"
            >Log In to Lost & Found</p>
                <p
                className="text-left mt-10 font-medium text-gray-400 font-sans"
                >Email</p>
                <div className="flex relative">
                <input 
                    type="email" 
                    placeholder="Enter Email" 
                    value={email}
                    onChange={(e) => handleEmailChange(e)}
                    required 
                    className="w-full bg-inherit border border-white rounded-md mt-1 pl-5 py-1 text-white placeholder:text-white"
                />
                <img 
                    src={em} 
                    height={80} 
                    width={50} 
                    alt="email svg"
                    className="absolute right-1 pt-1 top-1/2 transform -translate-y-1/2"    
                />
                </div>
                <p
                className="text-left mt-5 font-medium text-gray-400 font-sans"
                >Password</p>
                <input 
                    type="password" 
                    placeholder="Enter Password" 
                    value={password}
                    onChange={(e) => handlePasswordChange(e)}
                    required 
                    className="w-full bg-inherit border border-white text-white rounded-md mt-1 pl-5 py-1 placeholder:text-white"
                />
                <input
                    type="submit"
                    className="w-full border-black text-gray-900 font-medium font-sans rounded-md mt-12 py-1 bg-[#abb435] hover:bg-[#abb450] cursor-pointer"
                />
                <p
                    className="font-medium text-gray-400 mt-16"
                >
                    Already have an Account?
                </p>
                <NavLink
                    to="/login"
                    className="w-full bg-gray-600 border border-[#abb450] rounded-md mt-1 py-1 text-black"
                >
                    Log In 
                </NavLink>
            </form>
        </div>
        </div>
    )
}

export default Signup;