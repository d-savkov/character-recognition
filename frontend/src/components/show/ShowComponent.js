import React, {useEffect, useState} from 'react';
import ShowService from "../../services/ShowService";
import {Card, CardContent, Typography} from "@mui/material";

export default function ShowComponent() {
    const showService = new ShowService();
    const [shows, setShows] = useState([]);
    useEffect(() => {
        showService.getAll()
            .then((response) => response.data)
            .then((value) => setShows(value));
    }, []);

    return (
        <div>
            {shows
                .sort((a, b) => a.id > b.id ? 1 : -1)
                .map(show => (
                    <Card sx={{minWidth: 275}} key={show.id}>
                        <CardContent>
                            <Typography variant="h3" component="div">
                                {show.name}
                            </Typography>
                            <Typography variant="p" component="div">
                                {show.showType}
                            </Typography>
                        </CardContent>
                    </Card>
                ))
            }
        </div>
    );
}
