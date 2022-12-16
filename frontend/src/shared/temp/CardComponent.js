import React from 'react';

export default function CardComponent(props) {
    const character = props.character;

    return (
        <div className="card">
            <article>
                <img className="card-img"
                     src={character.images[0].url}
                     alt={character.name}/>
                <h3 className="card-name">
                    {character.name}
                </h3>
                <p className="card-played-by">
                    {character.playedBy}
                </p>
            </article>
        </div>
    );
}
