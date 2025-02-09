import { Outlet, useLocation } from "react-router"
import Navigation from "../Component/Navigation"
import Carousel from "../Component/Carousel";
import Signup from "../Component/Signup";

const Home = () => {
    const location = useLocation();
    const isHomePath = location.pathname === "/";
    const ignorePaths = {
        loginPage: "/login",
        signupPage: "/signup"
    }

    return(
        <main className="min-h-screen bg-[#071740] bg-blend-darken justify-between items-center text-[#abb466] text-center bg-gradient-to-bl from-[#000000]
            flex flex-col content-center fixed"
            // style={{backgroundImage:`url(./src/assets/images/bg4.png)`}}
        >
        <div className=" w-screen h-screen">
            {
                location.pathname !== ignorePaths.loginPage && location.pathname !== ignorePaths.signupPage ?
                <Navigation /> :
                null
            }
            <Outlet />
            {
               isHomePath ? 
                <div className=" w-[80%] mx-40 bottom-14 grid place-items-center fixed h-[45%]  rounded-2xl bg-[#858795] shadow-sm shadow-white opacity-85 mt-6 
                    bg-opacity-30 backdrop-blur-md  border-2 border-opacity-80 border-[#abb450] ">
                    <Carousel />
                </div>
                : null
            }
        </div>
        </main>
    )
}

export default Home