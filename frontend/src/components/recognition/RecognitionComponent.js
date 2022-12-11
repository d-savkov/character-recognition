import React, {useState} from 'react';
import RecognitionService from '../../services/RecognitionService';
import CardComponent from '../card/CardComponent';
import {Link} from 'react-router-dom';

export default function RecognitionComponent() {
    const recognitionService = new RecognitionService();
    const [selectedFile, setSelectedFile] = useState(null);
    const [image, setImage] = useState(null);
    const [persons, setPersons] = useState([]);

    const onFileChange = (event) => {
        setSelectedFile(event.target.files[0]);
        setPersons([])
        if (event.target.files && event.target.files[0]) {
            let reader = new FileReader();
            reader.onload = (e) => {
                setImage(e.target.result)
            };
            reader.readAsDataURL(event.target.files[0]);
        }
    };

    const onFind = () => {
        setPersons([])
        recognitionService.findMostSimilar(formData)
            .then((response) => setPersons(response.data));
    };

    const onShowAll = () => {
        setPersons([])
        recognitionService.getAll(formData)
            .then((response) => setPersons(response.data));
    };

    const onRemove = () => {
        setSelectedFile(null);
        setPersons([])
        setImage(null);
    };

    const formData = () => {
        const formData = new FormData();
        formData.append('file', selectedFile);
        return formData;
    };

    return (
        <div>
            {image
                ? <div>
                    <div className="recognition-image-container">
                        {image ? <img src={image} alt={image.name}/> : <div/>}
                    </div>
                    <div className="recognition-button-bar">
                        <button type="button" className="btn btn-outline-success btn-lg" onClick={onFind}>
                            <label>Find</label>
                            <i className="bi bi-search"/>
                        </button>
                        <button type="button" className="btn btn-outline-success btn-lg" onClick={onShowAll}>
                            <label>Show all</label>
                            <i className="bi bi-collection"/>
                        </button>
                        <button type="button" className="btn btn-outline-danger btn-lg" onClick={onRemove}>
                            <label>Remove</label>
                            <i className="bi bi-trash3"/>
                        </button>
                    </div>
                    <div className="recognition-container">
                        <div className="cards">
                            {persons
                                .sort((a, b) => a.similarity < b.similarity ? 1 : -1)
                                .map(p => (<div key={p.person.id}>
                                    <Link style={{textDecoration: 'none'}} to={`/${p.person.id}`}>
                                        <CardComponent person={p.person}/>
                                    </Link>
                                    <p>{p.similarity}%</p>
                                </div>))}
                        </div>
                    </div>
                </div>

                : <div className="image-input">
                    <div className="form-group">
                        <label className="label">
                            <i className="bi bi-paperclip"/>
                            <span className="title">Загрузить фото</span>
                            <input type="file" accept="image/jpg, image/jpeg" onChange={onFileChange}/>
                        </label>
                    </div>
                </div>
            }
        </div>
    );
}
