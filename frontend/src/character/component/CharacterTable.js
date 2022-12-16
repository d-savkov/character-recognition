import React, {useEffect, useState} from 'react';
import {Link} from 'react-router-dom';
import CharacterService from "../service/CharacterService";
import CardComponent from "../../shared/temp/CardComponent";
import './character-table.css'
import {ButtonGroup} from "react-bootstrap";

export default function CharacterTable(props) {
    const characterService = new CharacterService();
    const showId = props.showId;
    const [characters, setCharacters] = useState([]);
    useEffect(() => {
        characterService.getAllByShowId(showId)
            .then((response) => response.data)
            .then((value) => setCharacters(value));
    }, [showId]);

    return (
        <div className="character-container">
            <div className="character-cards">
                {characters
                    .sort((a, b) => a.id > b.id ? 1 : -1)
                    .map(character => (
                        <div>
                            <Link style={{textDecoration: 'none'}} to={`character/${character.id}`}>
                                <CardComponent character={character}/>
                            </Link>
                            <ButtonGroup className="character-button-bar">
                                <button type="button" className="btn btn-outline-primary btn-lg column-btn">
                                    <i className="bi bi-pencil-square"/>
                                </button>
                                <button type="button" className="btn btn-outline-danger btn-lg column-btn">
                                    <i className="bi bi-trash3"/>
                                </button>
                            </ButtonGroup>
                        </div>
                    ))
                }
            </div>
        </div>
    );
};
