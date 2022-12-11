import axios from 'axios';

const WIKI_REST_API_URL = 'http://localhost:8080/wiki';

export default class WikiService {
    getAll() {
        return axios.get(WIKI_REST_API_URL);
    }

    getById(id) {
        return axios.get(`${WIKI_REST_API_URL}/${id}`);
    }
}
