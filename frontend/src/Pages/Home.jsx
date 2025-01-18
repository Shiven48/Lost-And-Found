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
        </div>
        </main>
    )
}

export default Home