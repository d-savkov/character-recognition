import React, {useEffect, useState} from 'react';
import {Link} from 'react-router-dom';
import CharacterService from "../service/CharacterService";
import CardComponent from "../../shared/temp/CardComponent";

export default function CharacterComponent(props) {
    const characterService = new CharacterService();
    const showId = props.showId;
    const [characters, setCharacters] = useState([]);
    useEffect(() => {
        characterService.getAllByShowId(showId)
            .then((response) => response.data)
            .then((value) => setCharacters(value));
    }, [showId]);

    return (
        <div className="wiki-container">
            <div className="cards">
                {characters
                    .sort((a, b) => a.id > b.id ? 1 : -1)
                    .map(character => (
                        <Link style={{textDecoration: 'none'}} to={`character/${character.id}`}>
                            <CardComponent character={character}/>
                        </Link>
                    ))
                }
            </div>
        </div>
    );
};
