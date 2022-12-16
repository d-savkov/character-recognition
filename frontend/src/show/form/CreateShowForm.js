import ShowForm from "./ShowForm";
import ShowService from "../service/ShowService";

export default function CreateShowForm() {
    const showService = new ShowService();

    const onSubmit = (show) => {
        showService.create(show);
    }

    return (
        <div>
            <ShowForm onSubmit={onSubmit}/>
        </div>
    );

}
