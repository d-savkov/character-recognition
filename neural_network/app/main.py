from fastapi import FastAPI, File
from fastapi.middleware.cors import CORSMiddleware

from face_descriptor import FaceDescriptor

app = FastAPI()

origins = [
    "http://localhost:3000",
    "http://frontend:3000"
]

app.add_middleware(
    CORSMiddleware,
    allow_origins=origins,
    allow_credentials=True,
    allow_methods=["*"],
    allow_headers=["*"],
)

face_descriptor = FaceDescriptor()


@app.post("/face_descriptor/")
async def create_upload_file(file: bytes = File(...)):
    return face_descriptor.get_face_descriptor(file)


@app.get("/")
async def root():
    return {"Uvicorn": "I'm alive"}
