import axios from 'axios';

const SHOW_REST_API_URL = 'http://localhost:8080/show';

export default class ShowService {
    getAll() {
        return axios.get(SHOW_REST_API_URL);
    }

    getById(id) {
        return axios.get(`${SHOW_REST_API_URL}/${id}`);
    }

    create(request) {
        return axios.post(`${SHOW_REST_API_URL}/create`,
            request,
        )
    }

    update(id, request) {
        return axios.put(`${SHOW_REST_API_URL}/${id}`,
            request
        )
    }

    deleteById(id) {
        return axios.delete(`${SHOW_REST_API_URL}/${id}`);
    }
}
