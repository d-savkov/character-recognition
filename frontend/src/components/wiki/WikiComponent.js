import React, {useEffect, useState} from 'react';
import WikiService from '../../services/WikiService';
import {Link} from "react-router-dom";
import CardComponent from "../card/CardComponent";

export default function WikiComponent() {
    const wikiService = new WikiService();
    const [persons, setPersons] = useState([]);
    useEffect(() => {
        wikiService.getAll()
            .then((response) => response.data)
            .then((value) => setPersons(value));
    }, []);

    return (
        <div className="wiki-container">
            <div className="cards">
                {persons
                    .sort((a, b) => a.id > b.id ? 1 : -1)
                    .map(person => (
                        <Link style={{textDecoration: 'none'}} to={`/${person.id}`}>
                            <CardComponent person={person}/>
                        </Link>
                    ))
                }
            </div>
        </div>
    );
}
