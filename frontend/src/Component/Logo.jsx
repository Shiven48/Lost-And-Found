import LFLogo from "../assets/svg/LFLogo.svg"

const Logo = () => {
    return(
    <div className="flex items-center w-10 h-10 absolute bg-white rounded-3xl justify-center m-2 mt-4 border border-white">
        <a href="/">
            <img
                src={LFLogo}
                alt="Lost_Found_Logo"
                className="w-10 h-10"
            />
        </a>
    </div>
    )
}

export default Logo