import './App.css';
import {Route, Routes} from 'react-router-dom';
import {Layout} from './shared/layout/Layout';
import RecognitionComponent from './recognition/component/RecognitionComponent';
import ShowComponent from "./show/component/ShowComponent";
import ShowSingleComponent from "./show/component/ShowSingleComponent";
import CharacterSingleComponent from "./character/component/CharacterSingleComponent";

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
