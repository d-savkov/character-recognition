import React, {useState} from "react";
import Form from "react-bootstrap/Form";
import Button from "react-bootstrap/Button";
import './character-form.css'

export default function CharacterForm(props) {
    const [image, setImage] = useState(props.image);
    const [name, setName] = useState(props.name);
    const [playedBy, setPlayedBy] = useState(props.playedBy);
    const [description, setDescription] = useState(props.description);
    const [showId, setShowId] = useState(props.showId);
    const [validated, setValidated] = useState(false);

    const [selectedFile, setSelectedFile] = useState(null);

    const handleSubmit = (e) => {
        e.preventDefault();
        const form = e.currentTarget;
        if (form.checkValidity() === false) {
            e.stopPropagation();
        } else {
            setValidated(true);
            const character = {name, playedBy, description, showId};
            const result = {character: character, image: selectedFile};
            props.onSubmit(result);
        }
    }

    const onFileChange = (event) => {
        setSelectedFile(event.target.files[0]);
        if (event.target.files && event.target.files[0]) {
            let reader = new FileReader();
            reader.onload = (e) => {
                setImage(e.target.result)
            };
            reader.readAsDataURL(event.target.files[0]);
        } else {
            setImage(null);
        }
    };

    return (
        <div>
            <div>
                {image
                    ?
                    <div>
                        <div className="character-form-image-container">
                            <img src={image} alt={image.name}/>
                        </div>
                    </div>
                    :
                    <div/>
                }
            </div>

            <Form noValidate validated={validated} onSubmit={handleSubmit}>
                <Form.Group controlId="formFile" className="mb-3">
                    <Form.Control
                        type="file"
                        accept="image/jpg, image/jpeg"
                        onChange={onFileChange}/>
                </Form.Group>

                <Form.Group className="mb-3">
                    <Form.Control
                        type="text"
                        value={name}
                        placeholder="Enter character name"
                        required
                        onChange={(event) => setName(event.target.value)}
                    />
                </Form.Group>

                <Form.Group className="mb-3">
                    <Form.Control
                        type="text"
                        value={playedBy}
                        placeholder="Enter actor name"
                        required
                        onChange={(event) => setPlayedBy(event.target.value)}
                    />
                </Form.Group>

                <Form.Group className="mb-3">
                    <Form.Control
                        as="textarea"
                        rows={5}
                        value={description}
                        placeholder="Enter character description"
                        required
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
