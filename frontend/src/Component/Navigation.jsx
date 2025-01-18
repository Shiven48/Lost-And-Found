import { NavLink } from "react-router-dom"

const Navigation = () => {
    return (
        <div className="items-center flex justify-between relative">
        <nav className="text-white w-[30%] flex rounded-3xl mt-2 ml-2 border border-white pl-2 bg-gray-900">
            <NavLink
                to="/"
                className={({ isActive }) => 
                    isActive 
                        ? "w-16 bg-[#abb450] text-black rounded-xl text-base capitalize text-center m-1 border-2 border-black font-semibold hover:bg-[#abb466] mr-4"
                        : "w-16 bg-[#0e1a36] text-base rounded-xl text-center m-1 border border-[#abb466] font-semibold hover:text-[#abb466]"
                }
            >
                Home
            </NavLink>
            <NavLink
                to="/items"
                className={({ isActive }) =>
                    isActive
                        ? "w-16 bg-[#abb450] text-black rounded-xl text-base capitalize text-center m-1 border-2 border-black font-semibold hover:bg-[#abb466] mr-4"
                        : "w-16 bg-[#0e1a36] text-base rounded-xl text-center m-1 border border-[#abb466] font-semibold hover:text-[#abb466]"
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
                        "w-16 bg-[#abb450] text-black rounded-xl text-base capitalize text-center m-1 border-2 border-black font-semibold hover:bg-[#abb466] mr-4 mt-2"
                        // : "w-16 bg-[#0e1a36] text-base rounded-xl text-center m-1 border border-[#abb466] font-semibold hover:text-[#abb466]"
                }
            >
                Login
            </NavLink>
        </nav>
        </div> // changed to div here
    )
}

export default Navigation