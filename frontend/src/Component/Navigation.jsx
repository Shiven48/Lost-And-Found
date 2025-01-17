import { NavLink } from "react-router"

const Navigation = () => {
    return(
        <nav className="text-white w-[40%] align-middle 
            justify-around flex rounded-lg border border-cyan"
        >
        <NavLink 
            to="/"
            className={
                ({isActive}) => (
                    `w-full bg-[#abb450] text-base rounded capitalize text-center m-1 border-2 border-black font-semibold
                    ${isActive ? `rounded text-black capitalize hover:bg-[#abb466]` : `bg-[#0e1a38] rounded-lg hover:text-[#abb466] hover:border border-[#abb466]`}
                   
                    `
                )
            }
        >
            Home
        </NavLink>

        <NavLink 
            to="/items"
            className={
                ({isActive}) => (
                    `w-full bg-[#abb450] text-base rounded capitalize text-center m-1 border-2 border-black font-semibold
                    ${isActive ? `rounded text-black capitalize hover:bg-[#abb466]` : `bg-[#0e1a38] rounded-lg hover:text-[#abb466] hover:border border-[#abb466]`}
                    `
                )
            }
        >
            Items
        </NavLink>
        </nav>
    )
}
export default Navigation