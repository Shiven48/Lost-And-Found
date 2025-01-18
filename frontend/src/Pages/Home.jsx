import { Outlet, useLocation } from "react-router"
import Navigation from "../Component/Navigation"

const Home = () => {
    const location = useLocation();
    const isHomePath = location.pathname === "/";

    return(
        <main className="min-h-screen bg-[#071740] bg-blend-darken justify-between items-center text-[#abb466] text-center bg-gradient-to-bl from-[#000000]
            flex flex-col content-center fixed
        ">
        <div className=" w-screen h-screen">
            <Navigation />
            <Outlet />
            {
               isHomePath ? 
                <div className="h-[85%] rounded-2xl bg-[#858795] shadow-sm shadow-white opacity-85 mt-6 bg-opacity-30 backdrop-blur-md  border-2 border-opacity-80 border-[#abb450] mx-3 ">
                    <img src="./src/assets/images/bg4.png" alt="home_background" className="w-full h-full opacity-60 rounded-2xl"> 
                    </img>
                </div>
                : null
            }
        </div>
        </main>
    )
}

export default Home