import axios from 'axios';

const WIKI_REST_API_URL = 'http://localhost:8080/wiki';

class WikiService {
	getAll() {
		return axios.get(WIKI_REST_API_URL);
	}

	getById(id) {
		return fetch(`${WIKI_REST_API_URL}/${id}`);
	}
}

export default new WikiService();