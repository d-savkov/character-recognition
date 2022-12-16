import ShowForm from "./ShowForm";
import {useState} from "react";
import ShowService from "../service/ShowService";

export default function UpdateShowForm(props) {
    const showService = new ShowService();
    const [id, setId] = useState(props.show.id)
    const [show, setShow] = useState(props.show)

    const onSubmit = (show) => {
        showService.update(id, show);
    }

    return (
        <div>
            <ShowForm name={show.name}
                      description={show.description}
                      showType={show.showType}
                      onSubmit={onSubmit}/>
        </div>
    );

}
