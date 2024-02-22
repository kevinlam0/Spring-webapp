import { Header } from "../components/Header";
import { OpeningPictures } from "../components/OpeningPictures";
import { CommentSection } from '../components/CommentSection';
import { useNavigate } from "react-router-dom"
import NavBar from "../NavBar";

export const BlogPost = () => {
    const navigate = useNavigate();
    return (
      <div>
        <NavBar/>
        <Header/>
        <OpeningPictures/>
        <CommentSection/>
      </div>
    );
}
  
