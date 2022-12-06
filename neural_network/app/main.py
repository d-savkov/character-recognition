from fastapi import FastAPI, File

from face_descriptor import FaceDescriptor

app = FastAPI()
face_descriptor = FaceDescriptor()


@app.post("/face_descriptor/")
async def create_upload_file(file: bytes = File(...)):
    return face_descriptor.get_face_descriptor(file)

@app.get("/")
async def root():
    return {"Uvicorn": "I'm alive"}
