import './App.css';
import { Header } from "./components/Header";
import { OpeningPictures } from "./components/OpeningPictures";
import { Comments } from './components/Comments';
import { CommentForm } from './components/AddComments'
import { CommentSection } from 'replyke';
function App() {

  return (
    <div>
      <Header/>
      <OpeningPictures/>
      <Comments/>
      <CommentSection
        articleId="UNIQUE_ARTICLE_ID"
        styleId="STYLE_ID"
      />
    </div>
  );
}

export default App;
