import React, {useEffect, useState} from 'react';
import {useParams} from 'react-router-dom';
import CharacterService from "../service/CharacterService";

export default function CharacterPage() {
    const characterService = new CharacterService();
    const {id} = useParams();
    const {showId} = useParams();
    const [character, setCharacter] = useState(null);
    useEffect(() => {
        characterService.getById(showId, id)
            .then((response) => response.data)
            .then((value) => setCharacter(value));
    }, [id]);

    return (
        <div>
            {character && (<>
                <div className="wiki-single-image-container">
                    <img className="wiki-single-image-background"
                         src={character.images[0].url}
                         alt={character.name}/>
                    <img className="wiki-single-image"
                         src={character.images[0].url}
                         alt={character.name}/>
                </div>
                <div className="wiki-single-text">
                    <h1 className="wiki-single-name">
                        {character.name}
                    </h1>
                    <p className="wiki-single-played-by">
                        Played by {character.playedBy}
                    </p>
                    <p className="wiki-single-description">
                        {character.description}
                    </p>
                </div>
            </>)}
        </div>
    );
};
