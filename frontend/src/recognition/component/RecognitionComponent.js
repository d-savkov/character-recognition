import React, {useState} from 'react';
import RecognitionService from '../service/RecognitionService';
import CardComponent from '../../shared/temp/CardComponent';
import {Link} from 'react-router-dom';
import FaceDescriptorProvider from "../service/FaceDescriptorProvider";

export default function RecognitionComponent() {
    const descriptorProvider = new FaceDescriptorProvider();
    const recognitionService = new RecognitionService();
    const [selectedFile, setSelectedFile] = useState(null);
    const [image, setImage] = useState(null);
    const [characters, setCharacters] = useState([]);

    const onFileChange = (event) => {
        setSelectedFile(event.target.files[0]);
        setCharacters([])
        if (event.target.files && event.target.files[0]) {
            let reader = new FileReader();
            reader.onload = (e) => {
                setImage(e.target.result)
            };
            reader.readAsDataURL(event.target.files[0]);
        }
    };

    const onFind = () => {
        setCharacters([])
        descriptorProvider.getFaceDescriptor(formData())
            .then((response) => {
                recognitionService.getMostSimilar(response.data)
                    .then((response) => {
                        setCharacters(response.data)
                    });
            })
    };

    const onShowAll = () => {
        setCharacters([])
        descriptorProvider.getFaceDescriptor(formData())
            .then((response) => {
                recognitionService.getAll(response.data)
                    .then((response) => {
                        setCharacters(response.data)
                    });
            })
    };

    const onRemove = () => {
        setSelectedFile(null);
        setCharacters([])
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
                            {characters
                                .sort((a, b) => a.similarity < b.similarity ? 1 : -1)
                                .map(c => (
                                    <div key={c.character.id}>
                                        <Link style={{textDecoration: 'none'}}
                                              to={`/show/${c.character.showId}/character/${c.character.id}`}>
                                            <CardComponent character={c.character}/>
                                        </Link>
                                        <p>{c.similarity}%</p>
                                    </div>
                                ))
                            }
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
