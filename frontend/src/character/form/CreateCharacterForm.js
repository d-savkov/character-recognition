import CharacterForm from "./CharacterForm";
import {useState} from "react";
import CharacterService from "../service/CharacterService";

export default function CreateCharacterForm(props) {
    const characterService = new CharacterService();
    const [showId, setShowId] = useState(props.showId)

    const onSubmit = (character) => {
        characterService.create(showId, character);
    }

    return (
        <div>
            <CharacterForm showId={showId} onSubmit={onSubmit}/>
        </div>
    );

}
