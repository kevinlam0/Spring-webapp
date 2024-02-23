import { Header } from "../components/Header";
import { OpeningPictures } from "../components/OpeningPictures";
import { CommentSection } from '../components/CommentSection';
import NavBar from "../NavBar";
import { useState } from 'react';

export const BlogPost = () => {
  const [viewComment, setViewComment] = useState(false);

    const toggleViewComment = () => {
      setViewComment(!viewComment);
    }
    return (
      <div>
        <NavBar/>
        <Header/>
        <OpeningPictures/>
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
  
