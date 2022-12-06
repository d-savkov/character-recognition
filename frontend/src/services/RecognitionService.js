import axios from 'axios';

const RECOGNITION_REST_API_URL = 'http://localhost:8080/face-recognition';

class RecognitionService {
	findMostSimilar(formData) {
		return axios.post(
				`${RECOGNITION_REST_API_URL}/most-similar`,
				formData,
				{
					headers: {
						"Content-type": "multipart/form-data",
					},
				}
		)
	}

	getAll(formData) {
		return axios.post(
				`${RECOGNITION_REST_API_URL}/similarity`,
				formData,
				{
					headers: {
						"Content-type": "multipart/form-data",
					},
				}
		)
	}
}

export default new RecognitionService();