import { Outlet } from "react-router"
import Navigation from "../Component/Navigation"

const Home = () => {
    return(
        <main className="min-h-screen bg-[#071740] bg-blend-darken justify-between items-center text-[#abb466] text-center bg-gradient-to-bl from-[#000000]
            flex flex-col content-center fixed
        ">
        <div className=" w-screen h-screen">
            <Navigation />
            <Outlet />

            <div className="w-[95%] h-[85%] rounded-2xl bg-[#858795] mx-auto opacity-85 mt-8 bg-opacity-30 backdrop-blur-md  border border-white border-opacity-20">
                <div className=" w-[95%] h-[90%] mx-auto my-8">

                </div>
            </div>
        </div>
        </main>
    )
}

export default Home