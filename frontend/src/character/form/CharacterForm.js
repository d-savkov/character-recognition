import React, {useState} from "react";
import Form from "react-bootstrap/Form";
import Button from "react-bootstrap/Button";
import FaceDescriptorProvider from "../../recognition/service/FaceDescriptorProvider";

export default function CharacterForm(props) {
    const descriptorProvider = new FaceDescriptorProvider();

    const [image, setImage] = useState(props.image);
    const [name, setName] = useState(props.name);
    const [playedBy, setPlayedBy] = useState(props.playedBy);
    const [description, setDescription] = useState(props.description);
    const [showId, setShowId] = useState(props.showId);

    const [selectedFile, setSelectedFile] = useState(null);

    const handleSubmit = (e) => {
        const [descriptor, setDescriptor] = useState([]);
        descriptorProvider.getFaceDescriptor(formData())
            .then((response) => response.data)
            .then((value) => setDescriptor(value))

        const character = {name, playedBy, description, showId};
        const result = {character, descriptor};
        props.onSubmit(result);
    }

    const onFileChange = (event) => {
        setSelectedFile(event.target.files[0]);
        if (event.target.files && event.target.files[0]) {
            let reader = new FileReader();
            reader.onload = (e) => {
                setImage(e.target.result)
            };
            reader.readAsDataURL(event.target.files[0]);
        }
    };

    const onRemove = () => {
        setSelectedFile(null);
        setImage(null);
    };

    const formData = () => {
        const formData = new FormData();
        formData.append('file', selectedFile);
        return formData;
    };

    return (
        <div>
            <Form onSubmit={handleSubmit}>
                <div>
                    {image
                        ? <div>
                            <div className="recognition-image-container">
                                <img src={image} alt={image.name}/>
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

                <Form.Group className="mb-3">
                    <Form.Control
                        type="text"
                        value={name}
                        placeholder="Enter character name"
                        onChange={(event) => setName(event.target.value)}
                    />
                </Form.Group>

                <Form.Group className="mb-3">
                    <Form.Control
                        type="text"
                        value={playedBy}
                        placeholder="Enter actor name"
                        onChange={(event) => setPlayedBy(event.target.value)}
                    />
                </Form.Group>

                <Form.Group className="mb-3">
                    <Form.Control
                        as="textarea"
                        rows={5}
                        value={description}
                        placeholder="Enter character description"
                        onChange={(event) => setDescription(event.target.value)}
                    />
                </Form.Group>

                <Button variant="primary" type="submit">
                    Submit
                </Button>
            </Form>
        </div>
    );
}
