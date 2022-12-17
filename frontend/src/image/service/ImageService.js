import axios from 'axios';

export default class ImageService {

    getImageRestApiUrl(showId, characterId) {
        return `http://localhost:8080/show/${showId}/character/${characterId}/image/`;
    }

    getUploadPresignDetails(showId, characterId, fileType) {
        const url = `${this.getImageRestApiUrl(showId, characterId)}?fileType=${fileType}`;
        return axios.get(url)
    }

    create(showId, characterId, request) {
        const url = this.getImageRestApiUrl(showId, characterId);
        return axios.post(url, request)
    }

    deleteById(showId, characterId, id) {
        const url = `${this.getImageRestApiUrl(showId, characterId)}?${id}`;
        return axios.delete(url);
    }
}
