import React, {useState} from "react";
import Form from "react-bootstrap/Form";
import Button from "react-bootstrap/Button";

export default function ShowForm(props) {
    const [name, setName] = useState(props.name);
    const [description, setDescription] = useState(props.description);
    const [showType, setShowType] = useState(props.showType || 'MOVIE');

    const handleSubmit = (e) => {
        const show = {name, description, showType};
        props.onSubmit(show);
    }

    return (
        <div>
            <Form onSubmit={handleSubmit}>
                <Form.Group className="mb-3">
                    <Form.Control
                        type="text"
                        value={name}
                        placeholder="Enter show name"
                        onChange={(event) => setName(event.target.value)}
                    />
                </Form.Group>

                <Form.Group className="mb-3">
                    <Form.Control
                        as="textarea"
                        rows={5}
                        value={description}
                        placeholder="Enter show description"
                        onChange={(event) => setDescription(event.target.value)}
                    />
                </Form.Group>

                <Form.Group className="mb-3">
                    <Form.Select value={showType} onChange={(event) => setShowType(event.target.value)}>
                        <option>MOVIE</option>
                        <option>TV_SERIES</option>
                    </Form.Select>
                </Form.Group>

                <Button variant="primary" type="submit">
                    Submit
                </Button>
            </Form>
        </div>
    );
}
