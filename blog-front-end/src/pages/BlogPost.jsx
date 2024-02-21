import { Header } from "../components/Header";
import { OpeningPictures } from "../components/OpeningPictures";
import { CommentSection } from '../components/CommentSection';
import { useNavigate } from "react-router-dom"
import { getMouseEventOptions } from "@testing-library/user-event/dist/utils";
export const BlogPost = () => {
    const navigate = useNavigate();
    const goToLoginPage = () => {
        navigate("/login");
    }
    return (
      <div>
        <Header/>
        <OpeningPictures/>
        <CommentSection/>
        <button onClick={goToLoginPage}>Click me</button>
      </div>
    );
}
  
