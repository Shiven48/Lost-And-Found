import { NavLink } from "react-router-dom"
import LFLogo from "../assets/svg/LFLogo.svg"

const Navigation = () => {
    return (
        <div className="items-center flex justify-between relative">
            <div className="flex items-center w-10 h-10 absolute bg-white rounded-3xl justify-center m-2 mt-6 border border-white">
                <a href="/">
                    <img 
                        src={LFLogo} 
                        alt="Lost_Found_Logo"
                        className="w-10 h-10" 
                />
                </a>
            </div>
        <nav className="text-white w-[30%] flex rounded-3xl mt-2 border border-white pl-2 bg-gray-900 ml-auto mr-16">
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
            <NavLink
                to="/items"
                className={({ isActive }) =>
                    isActive
                        ? "w-20 bg-[#abb450] text-black rounded-xl text-base capitalize text-center m-1 border-2 border-black font-semibold hover:bg-[#abb466] mr-6"
                        : "w-20 bg-[#0e1a36] text-base rounded-xl text-center m-1 border border-[#abb466] font-semibold hover:text-[#abb466]"
                }
            >
                Items
            </NavLink>
        </nav>
        <nav className="text-white w-[30%] flex rounded-3xl mt-2 ml-2 justify-end">
            <NavLink
                to="/login"
                className={({ isActive }) =>
                    // isActive
                        "w-20 bg-[#abb450] text-black rounded-xl text-base capitalize text-center m-1 border-2 border-black font-semibold hover:bg-[#abb466] mr-4 mt-2 hover:border-gray-300"
                        // : "w-16 bg-[#0e1a36] text-base rounded-xl text-center m-1 border border-[#abb466] font-semibold hover:text-[#abb466]"
                }
            >
                Login
            </NavLink>
            <NavLink
                to="/signup"
                className={({ isActive }) =>
                    // isActive
                        "w-20 bg-[#abb450] text-black rounded-xl text-base capitalize text-center m-1 border-2 border-black font-semibold hover:bg-[#abb466] mr-4 mt-2 hover:border-gray-300"
                        // : "w-16 bg-[#0e1a36] text-base rounded-xl text-center m-1 border border-[#abb466] font-semibold hover:text-[#abb466]"
                }
            >
                Signup
            </NavLink>
        </nav>
        </div> // changed to div here
    )
}

export default Navigation