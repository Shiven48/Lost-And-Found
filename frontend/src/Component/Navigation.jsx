import { NavLink } from "react-router-dom"

const Navigation = () => {
    return (
        <nav className="text-white w-[40%] flex rounded-lg mt-2 ml-2">
            <NavLink
                to="/"
                className={({ isActive }) => 
                    isActive 
                        ? "w-16 bg-[#abb450] text-black rounded-xl text-base capitalize text-center m-1 border-2 border-black font-semibold hover:bg-[#abb466] mr-4"
                        : "w-16 bg-[#0e1a36] text-base rounded-xl text-center m-1 border border-[#abb466] font-semibold"
                }
            >
                Home
            </NavLink>
            <NavLink
                to="/items"
                className={({ isActive }) =>
                    isActive
                        ? "w-16 bg-[#abb450] text-black rounded-xl text-base capitalize text-center m-1 border-2 border-black font-semibold hover:bg-[#abb466]"
                        : "w-16 bg-[#0e1a36] text-base rounded-xl text-center m-1 border border-[#abb466] font-semibold hover:text-[#abb466]"
                }
            >
                Items
            </NavLink>
        </nav>
    )
}

export default Navigation