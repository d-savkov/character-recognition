import './App.css';
import {Route, Routes} from 'react-router-dom';
import {Layout} from './shared/layout/Layout';
import RecognitionComponent from './recognition/component/RecognitionComponent';
import ShowTable from "./show/component/ShowTable";
import ShowPage from "./show/component/ShowPage";
import CharacterPage from "./character/component/CharacterPage";

function App() {
    return (<div className="App">
        <Routes>
            <Route path="/" element={<Layout/>}>
                <Route index element={<ShowTable/>}/>
                <Route path="show" element={<ShowTable/>}/>
                <Route path="show/:id" element={<ShowPage/>}/>
                <Route path="show/:showId/character/:id" element={<CharacterPage/>}/>
                <Route path="recognition" element={<RecognitionComponent/>}/>
            </Route>
        </Routes>
    </div>);
}

export default App;
