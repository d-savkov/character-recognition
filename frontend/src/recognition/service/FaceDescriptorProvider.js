import axios from 'axios';

const FACE_DESCRIPTOR_API_URL = 'http://localhost:80/face_descriptor';

export default class FaceDescriptorProvider {
    getFaceDescriptor(formData) {
        return axios.post(
            FACE_DESCRIPTOR_API_URL,
            formData,
            {
                headers: {
                    "Content-type": "multipart/form-data"
                },
            }
        )
    }
}
