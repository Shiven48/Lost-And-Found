import { Route, Routes } from "react-router"
import Home from "./Pages/Home"
import Items from "./Pages/Items"
import Login from "./Component/Login"
import Signup from "./Component/Signup.jsx"

function App() {

  return (
    <Routes>

        <Route path="/" element={<Home />} >
          <Route path="/items" element={<Items />} />
          <Route path="/login" element={<Login />} />
          <Route path="/signup" element={<Signup />} />
        </Route>

    </Routes>
  )
}

export default App
