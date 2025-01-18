import { Route, Routes } from "react-router"
import Home from "./Pages/Home"
import Items from "./Pages/Items"
import Login from "./Component/Login"

function App() {

  return (
    <Routes>

        <Route path="/" element={<Home />} >
          <Route path="/items" element={<Items />} />
          <Route path="/login" element={<Login />} />
        </Route>

    </Routes>
  )
}

export default App
