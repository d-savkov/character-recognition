import {useParams} from "react-router-dom";
import {useEffect, useState} from "react";
import ShowService from "../service/ShowService";
import {Typography} from "@mui/material";
import CharacterTable from "../../character/component/CharacterTable";
import "./show-page.css"

export default function ShowPage() {
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
                <div>
                    <div className="show-page-text">
                        <Typography className="show-page-name" variant="h3" component="div">
                            {show.name}
                        </Typography>
                        <Typography className="show-page-type" variant="p" component="div">
                            {show.showType}
                        </Typography>
                        <Typography className="show-page-description" variant="p" component="div">
                            {show.description}
                        </Typography>
                    </div>
                    <CharacterTable showId={id}/>
                </div>
            )}
        </div>
    );

}
