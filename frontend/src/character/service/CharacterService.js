import axios from 'axios';

export default class CharacterService {

    getShowRestApiUrl(showId) {
        return `http://localhost:8080/show/${showId}/character`;
    }

    getAllByShowId(showId) {
        const url = this.getShowRestApiUrl(showId);
        return axios.get(url);
    }

    getById(showId, id) {
        const url = this.getShowRestApiUrl(showId);
        return axios.get(`${url}/${id}`);
    }
}
