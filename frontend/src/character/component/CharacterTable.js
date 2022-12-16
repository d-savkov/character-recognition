import React, {useEffect, useState} from 'react';
import {Link} from 'react-router-dom';
import CharacterService from "../service/CharacterService";
import CardComponent from "../../shared/temp/CardComponent";
import './character-table.css'
import {ButtonGroup} from "react-bootstrap";
import Popup from "../../shared/popup/Popup";
import CreateCharacterForm from "../form/CreateCharacterForm";
import UpdateCharacterForm from "../form/UpdateCharacterForm";

export default function CharacterTable(props) {
    const characterService = new CharacterService();
    const showId = props.showId;
    const [characters, setCharacters] = useState([]);
    const [popupActive, setPopupActive] = useState(false);
    const [form, setForm] = useState(null);
    useEffect(() => {
        characterService.getAllByShowId(showId)
            .then((response) => response.data)
            .then((value) => setCharacters(value));
    }, [showId]);

    const onDelete = (id) => {
        characterService.deleteById(showId, id);
        window.location.reload();
    }

    const onUpdate = (character) => {
        setForm(<UpdateCharacterForm showId={showId} character={character}/>)
        setPopupActive(true);
    }

    const onCreate = () => {
        setForm(<CreateCharacterForm showId={showId}/>)
        setPopupActive(true);
    }

    return (
        <div className="character-container">
            <div>
                <button type="button" className="btn btn-outline-success btn-lg row-btn" onClick={onCreate}>
                    <i className="bi bi-plus-circle"/>
                </button>
            </div>
            <div className="character-cards">
                {characters
                    .sort((a, b) => a.id > b.id ? 1 : -1)
                    .map(character => (
                        <div>
                            <Link style={{textDecoration: 'none'}} to={`character/${character.id}`}>
                                <CardComponent character={character}/>
                            </Link>
                            <ButtonGroup className="character-button-bar">
                                <button type="button"
                                        className="btn btn-outline-primary btn-lg column-btn"
                                        onClick={() => onUpdate(character)}>
                                    <i className="bi bi-pencil-square"/>
                                </button>
                                <button type="button"
                                        className="btn btn-outline-danger btn-lg column-btn"
                                        onClick={() => onDelete(character.id)}>
                                    <i className="bi bi-trash3"/>
                                </button>
                            </ButtonGroup>
                        </div>
                    ))
                }
            </div>
            <Popup active={popupActive} setActive={setPopupActive}>
                {form}
            </Popup>
        </div>
    );
};
