import { Route, Routes } from "react-router"
import Home from "./Pages/Home"
import Items from "./Pages/Items"

function App() {

  return (
    <Routes>

        <Route path="/" element={<Home />} >
          <Route path="/items" element={<Items />} />
        </Route>

    </Routes>
  )
}

export default App
