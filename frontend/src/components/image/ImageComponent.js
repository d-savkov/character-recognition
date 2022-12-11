import React from 'react';

export default function ImageComponent(props) {
    const className = props.className;
    const img = props.img;
    const alt = props.alt;

    return (
        <div>
            <img className={className}
                 src={`data:image/jpeg;base64,${img}`}
                 alt={alt}/>
        </div>
    );
}
