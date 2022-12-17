import CharacterForm from "./CharacterForm";
import {useState} from "react";
import {create} from "../service/CharacterFormSubmitService";

export default function CreateCharacterForm(props) {

    const [showId, setShowId] = useState(props.showId)

    const onSubmit = (response) => {
        create(showId, response);
    }

    return (
        <div>
            <CharacterForm showId={showId} onSubmit={onSubmit}/>
        </div>
    );

}
