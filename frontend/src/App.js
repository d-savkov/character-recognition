import './App.css';
import {Route, Routes} from 'react-router-dom';
import {Layout} from './components/Layout';
import RecognitionComponent from './components/recognition/RecognitionComponent';
import ShowComponent from "./components/show/ShowComponent";
import ShowSingleComponent from "./components/show/ShowSingleComponent";
import CharacterSingleComponent from "./components/character/CharacterSingleComponent";

function App() {
    return (<div className="App">
        <Routes>
            <Route path="/" element={<Layout/>}>
                <Route index element={<ShowComponent/>}/>
                <Route path="show" element={<ShowComponent/>}/>
                <Route path="show/:id" element={<ShowSingleComponent/>}/>
                <Route path="show/:showId/character/:id" element={<CharacterSingleComponent/>}/>
                <Route path="recognition" element={<RecognitionComponent/>}/>
            </Route>
        </Routes>
    </div>);
}

export default App;
