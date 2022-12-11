import './App.css';
import WikiComponent from './components/wiki/WikiComponent';
import {Route, Routes} from 'react-router-dom';
import {Layout} from './components/Layout';
import WikiSingleComponent from './components/wiki/WikiSingleComponent';
import RecognitionComponent from './components/recognition/RecognitionComponent';
import ShowComponent from "./components/show/ShowComponent";
import ShowSingleComponent from "./components/show/ShowSingleComponent";

function App() {
    return (<div className="App">
        <Routes>
            <Route path="/" element={<Layout/>}>
                <Route index element={<WikiComponent/>}/>
                <Route path=":id" element={<WikiSingleComponent/>}/>
                <Route path="recognition" element={<RecognitionComponent/>}/>
                <Route path="show" element={<ShowComponent/>}/>
                <Route path="show/:id" element={<ShowSingleComponent/>}/>
            </Route>
        </Routes>
    </div>);
}

export default App;
