import React, {useEffect, useState} from 'react';
import ShowService from "../service/ShowService";
import {Link} from "react-router-dom";
import Table from 'react-bootstrap/Table';

export default function ShowComponent() {
    const showService = new ShowService();
    const [shows, setShows] = useState([]);
    useEffect(() => {
        showService.getAll()
            .then((response) => response.data)
            .then((value) => setShows(value));
    }, []);

    const onCreate = () => {
        console.log("create");
    }

    const onUpdate = (id) => {
        console.log("update" + id);
    }

    const onDelete = (id) => {
        showService.deleteById(id);
    }

    return (
        <div className="show-table">
            <Table striped>
                <thead>
                <tr>
                    <th>#</th>
                    <th>Name</th>
                    <th>Type</th>
                    <th>Actions</th>
                </tr>
                </thead>
                <tbody>
                {shows
                    .sort((a, b) => a.id > b.id ? 1 : -1)
                    .map(show => (
                        <tr key={show.id}>
                            <td>{show.id}</td>
                            <td>
                                <Link to={`/show/${show.id}`}>
                                    {show.name}
                                </Link>
                            </td>
                            <td>{show.showType}</td>
                            <td>
                                <div>
                                    <button type="button" className="btn btn-outline-primary btn-lg column-btn"
                                            onClick={() => onUpdate(show.id)}>
                                        <i className="bi bi-pencil-square"/>
                                    </button>
                                    <button type="button" className="btn btn-outline-danger btn-lg"
                                            onClick={() => onDelete(show.id)}>
                                        <i className="bi bi-trash3"/>
                                    </button>
                                </div>
                            </td>
                        </tr>
                    ))
                }
                <tr>
                    <td colspan="4">
                        <button type="button" className="btn btn-outline-success btn-lg row-btn" onClick={onCreate}>
                            <i className="bi bi-plus-circle"/>
                        </button>
                    </td>
                </tr>
                </tbody>
            </Table>
        </div>
    );
}
