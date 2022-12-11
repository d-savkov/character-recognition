import React, {useEffect, useState} from 'react';
import WikiService from '../../services/WikiService';
import {useParams} from 'react-router-dom';
import ImageComponent from '../image/ImageComponent';

export default function WikiSingleComponent() {
    const wikiService = new WikiService();
    const {id} = useParams();
    const [person, setPerson] = useState(null);
    useEffect(() => {
        wikiService.getById(id)
            .then((response) => response.data)
            .then((value) => setPerson(value));
    }, [id]);

    return (
        <div>
            {person && (<>
                <div className="wiki-single-image-container">
                    <ImageComponent className="wiki-single-image-background" img={person.base64Image}
                                    alt={person.name}/>
                    <ImageComponent className="wiki-single-image" img={person.base64Image} alt={person.name}/>
                </div>
                <div className="wiki-single-text">
                    <h1 className="wiki-single-name">
                        {person.name}
                    </h1>
                    <p className="wiki-single-played-by">
                        Played by {person.playedBy}
                    </p>
                    <p className="wiki-single-description">
                        {person.description}
                    </p>
                </div>
            </>)}
        </div>
    );
};
