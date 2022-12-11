import {useParams} from "react-router-dom";
import {useEffect, useState} from "react";
import ShowService from "../../services/ShowService";
import {Typography} from "@mui/material";

export default function ShowSingleComponent() {
    const showService = new ShowService();
    const {id} = useParams();
    const [show, setShow] = useState(null);
    useEffect(() => {
        showService.getById(id)
            .then((response) => response.data)
            .then((value) => setShow(value));
    }, [id]);

    return (
        <div>
            {show && (
                <>
                    <Typography variant="h3" component="div">
                        {show.name}
                    </Typography>
                    <Typography variant="p" component="div">
                        {show.showType}
                    </Typography>
                    <Typography variant="p" component="div">
                        {show.description}
                    </Typography>
                </>
            )}
        </div>
    );

}
