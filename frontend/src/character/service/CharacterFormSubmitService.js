import axios from "axios";
import CharacterService from "./CharacterService";
import ImageService from "../../image/service/ImageService";
import FaceDescriptorProvider from "../../recognition/service/FaceDescriptorProvider";

const characterService = new CharacterService();
const imageService = new ImageService();
const descriptorProvider = new FaceDescriptorProvider();

export const create = (showId, response) => {
    characterService.create(showId, response.character)
        .then((response) => response.data)
        .then((character) => {
            descriptorProvider.getFaceDescriptor(getFormData(response.image))
                .then((response) => response.data)
                .then((obj) => getMapObjectValues(obj))
                .then((faceDescriptor) => {
                    imageService.getUploadPresignDetails(showId, character.id, mimeTypeToFileType(response.image.type))
                        .then((response) => response.data)
                        .then((uploadDetails) => {
                            const options = {
                                headers: {
                                    'Content-Type': response.image.type,
                                }
                            };
                            axios.put(uploadDetails.url, response.image, options)
                                .then((response) => console.log(response));

                            const request = {
                                keyName: uploadDetails.keyName,
                                faceDescriptor: faceDescriptor,
                                characterId: character.id
                            };
                            imageService.create(showId, character.id, request);
                        });
                });
        });
}

export const update = (showId, characterId, response) => {
    characterService.updateById(showId, characterId, response.character)
        .then((response) => response.data)
        .then((character) => {
            if (response.image) {
                character.images.forEach(img => imageService.deleteById(img.id));
                descriptorProvider.getFaceDescriptor(getFormData(response.image))
                    .then((response) => response.data)
                    .then((obj) => getMapObjectValues(obj))
                    .then((faceDescriptor) => {
                        imageService.getUploadPresignDetails(showId, character.id, mimeTypeToFileType(response.image.type))
                            .then((response) => response.data)
                            .then((uploadDetails) => {
                                const options = {
                                    headers: {
                                        'Content-Type': response.image.type,
                                    }
                                };
                                axios.put(uploadDetails.url, response.image, options)
                                    .then((response) => console.log(response));

                                const request = {
                                    keyName: uploadDetails.keyName,
                                    faceDescriptor: faceDescriptor,
                                    characterId: character.id
                                };
                                imageService.create(showId, character.id, request);
                            });
                    });
            }
        })
        .then(window.location.reload);
}

const getFormData = (image) => {
    const formData = new FormData();
    formData.append('file', image);
    return formData;
}

const mimeTypeToFileType = (mimeType) => {
    return mimeType.split('/').pop();
}

const getMapObjectValues = (map) => {
    let result = []
    for (let i in map) {
        result.push(map[i]);
    }
    return result;
}
