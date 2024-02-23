import { Header } from "../components/Header";
import { OpeningPictures } from "../components/OpeningPictures";
import { CommentSection } from '../components/commentComponents/CommentSection';
import NavBar from "../NavBar";
import { useState } from 'react';
import { BlogContent } from "../components/blogContentComponents/BlogContent";

export const BlogPost = () => {
  const [viewComment, setViewComment] = useState(false);

    const toggleViewComment = () => {
      setViewComment(!viewComment);
    }
    return (
      <div>
        <NavBar/>
        <OpeningPictures/>
        <Header/>
        <BlogContent/>
        {
          viewComment ? 
            <div>
              <button onClick={toggleViewComment}>Close comments</button>
              <CommentSection/>
            </div>
          :
            <button onClick={toggleViewComment}>View Comments</button>
        }
      </div>
    );
}
  
