import React from 'react';
import ImageComponent from './ImageComponent';

export default function CardComponent(props) {
    const person = props.person;

    return (
        <div className="card">
            <article>
                <ImageComponent className="card-img"
                                img={person.base64Image}
                                alt={person.name}/>
                <h3 className="card-name">
                    {person.name}
                </h3>
                <p className="card-played-by">
                    {person.playedBy}
                </p>
            </article>
        </div>
    );
}
