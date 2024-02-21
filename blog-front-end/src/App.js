import './App.css';
import { Header } from "./components/Header";
import { OpeningPictures } from "./components/OpeningPictures";
import { CommentList } from './components/CommentList';
import { CommentForm } from './components/CommentForm'
import { CommentSection } from './components/CommentSection';
function App() {

  return (
    <div>
      <Header/>
      <OpeningPictures/>
      <CommentSection/>
    </div>
  );
}

export default App;
