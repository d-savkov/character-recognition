import {useState} from "react";
import CharacterService from "../service/CharacterService";
import CharacterForm from "./CharacterForm";

export default function UpdateCharacterForm(props) {
    const characterService = new CharacterService();
    const [showId, setShowId] = useState(props.showId);
    const [characterId, setCharacterId] = useState(props.character.id);
    const [character, setCharacter] = useState(props.character);

    const onSubmit = (character) => {
        characterService.updateById(showId, characterId, character);
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
