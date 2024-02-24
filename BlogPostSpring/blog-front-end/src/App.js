import './styles/App.css';
import { BrowserRouter, Routes, Route,  } from "react-router-dom";
import { BlogPost } from './pages/BlogPost';
import { LoginPage } from './pages/LoginPage';
import { UserProvider } from './context/UserContext';

function App() {

  return (
      <BrowserRouter>
        <UserProvider>
          <Routes>
              <Route exact path="/" element={<BlogPost/>} />
              <Route path="/login" element={<LoginPage/>} />  
            </Routes>
        </UserProvider>
      </BrowserRouter>
  );
}

export default App;
