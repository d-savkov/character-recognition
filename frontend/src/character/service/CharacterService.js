import axios from 'axios';

export default class CharacterService {

    getCharacterRestApiUrl(showId) {
        return `http://localhost:8080/show/${showId}/character`;
    }

    getAllByShowId(showId) {
        const url = this.getCharacterRestApiUrl(showId);
        return axios.get(url);
    }

    getById(showId, id) {
        const url = `${this.getCharacterRestApiUrl(showId)}/${id}`;
        return axios.get(url);
    }

    create(showId, request) {
        const url = this.getCharacterRestApiUrl(showId);
        return axios.post(url, request);
    }

    updateById(showId, id, request) {
        const url = `${this.getCharacterRestApiUrl(showId)}/${id}`;
        return axios.put(url, request);
    }

    deleteById(showId, id) {
        const url = `${this.getCharacterRestApiUrl(showId)}/${id}`;
        return axios.delete(url);
    }
}
