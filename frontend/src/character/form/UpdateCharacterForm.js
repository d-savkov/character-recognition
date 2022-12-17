import {useState} from "react";
import CharacterForm from "./CharacterForm";
import {update} from "../service/CharacterFormSubmitService";

export default function UpdateCharacterForm(props) {
    const [showId, setShowId] = useState(props.showId);
    const [characterId, setCharacterId] = useState(props.character.id);
    const [character, setCharacter] = useState(props.character);

    const onSubmit = (response) => {
        update(showId, characterId, response);
    }

    return (
        <div>
            <CharacterForm name={character.name}
                           playedBy={character.playedBy}
                           description={character.description}
                           showId={showId}
                           image={character.images[0].url}
                           onSubmit={onSubmit}/>
        </div>
    );

}
