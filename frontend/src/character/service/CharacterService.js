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
        const url = `${this.getShowRestApiUrl(showId)}/${id}`;
        return axios.get(url);
    }

    create(showId, request) {
        const url = this.getShowRestApiUrl(showId);
        return axios.post(url, request);
    }

    updateById(showId, id, request) {
        const url = `${this.getShowRestApiUrl(showId)}/${id}`;
        return axios.put(url, request);
    }

    deleteById(showId, id) {
        const url = `${this.getShowRestApiUrl(showId)}/${id}`;
        return axios.delete(url);
    }
}
