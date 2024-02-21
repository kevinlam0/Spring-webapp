import './App.css';
import { BrowserRouter, Routes, Route,  } from "react-router-dom";
import { BlogPost } from './pages/BlogPost';
import { LoginPage } from './pages/LoginPage';

function App() {

  return (

    <BrowserRouter>
      <Routes>
          <Route exact path="/" element={<BlogPost/>} />
          <Route path="/login" element={<LoginPage/>} />  
        </Routes>
    </BrowserRouter>
  );
}

export default App;
