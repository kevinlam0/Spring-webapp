import './App.css';
import { Header } from "./components/Header";
import { OpeningPictures } from "./components/OpeningPictures";
import { CommentList } from './components/CommentList';
import { CommentForm } from './components/AddComments'
function App() {

  return (
    <div>
      <Header/>
      <OpeningPictures/>
      <CommentList/>
      <CommentForm/>
    </div>
  );
}

export default App;
