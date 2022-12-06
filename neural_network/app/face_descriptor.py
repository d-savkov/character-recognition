import cv2
import dlib
import numpy as np
from fastapi import File

# os.path join


class FaceDescriptor:
    def __init__(self):
        self.shape_predictor = dlib.shape_predictor("data/shape_predictor_68_face_landmarks.dat")
        self.face_recognition_model = dlib.face_recognition_model_v1("data/dlib_face_recognition_resnet_model_v1.dat")
        self.frontal_face_detector = dlib.get_frontal_face_detector()

    def get_face_descriptor(self, file: bytes = File(...)) -> dict:
        """

        :param file:
        :return:
        """
        img = cv2.imdecode(np.frombuffer(file, np.uint8), -1)
        dets = self.frontal_face_detector(img, 1)
        shape = self.shape_predictor(img, list(dets)[-1])
        return {k: v for k, v in zip(range(128), self.face_recognition_model.compute_face_descriptor(img, shape))}
