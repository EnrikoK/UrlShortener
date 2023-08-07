import {BrowserRouter as router, Routes, Route, BrowserRouter} from "react-router-dom";
import ShortenerFrom from "./ShortenerForm"
import ShortUrlComponent from "./ShortUrlComponent";
import './index.css';
import logo from "./assets/Group.svg";

function App() {
  return (
      <>
      <header>
        <h1 className="greeting">Welcome to the linkshortener service!</h1>
        <img className="logo" src={logo}/>
      </header>
      
      <BrowserRouter> 
        <Routes>
          <Route path="/" element={<ShortenerFrom/>}/>
          <Route path="/:shortUrl" element={<ShortUrlComponent/>} /> 
        </Routes>

      </BrowserRouter>
      </>

    
  )
}

export default App
