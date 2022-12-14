import axios from 'axios';

const RECOGNITION_REST_API_URL = 'http://localhost:8080/recognition';

export default class RecognitionService {

    getAll(request) {
        return axios.post(
            RECOGNITION_REST_API_URL,
            request,
        )
    }

    getMostSimilar(request) {
        return axios.post(
            `${RECOGNITION_REST_API_URL}/most-similar`,
            request
        )
    }
}
