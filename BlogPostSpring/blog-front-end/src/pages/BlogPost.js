import { Header } from "../components/Header";
import { OpeningPictures } from "../components/OpeningPictures";
import { CommentSection } from '../components/commentComponents/CommentSection';
import NavBar from "../components/NavBar";
import { useState } from 'react';
import { BlogContent } from "../components/BlogContent";
import "../styles/BlogPost.css"

export const BlogPost = () => {
  const [viewComment, setViewComment] = useState(false);

    const toggleViewComment = () => {
      setViewComment(!viewComment);
    }
    return (
      <div className="blog-page-container">
        <NavBar/>
        <OpeningPictures/>
        <Header/>
        <div className="content">
          <BlogContent/>
          <div className="comment-section-container">
            {
              viewComment ? 
                <>
                  <button id='comment-main-btn' onClick={toggleViewComment}>Close comments</button> 
                  <div className='comment-section-data'>
                   <CommentSection/>
                  </div>
                </>
              :
                <button id='view-comment-btn' onClick={toggleViewComment}>View Comments</button>
            }
          </div>
        </div>
      </div>
    );
}
  
