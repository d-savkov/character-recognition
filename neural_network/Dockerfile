FROM python:3.8-slim-buster

RUN apt-get update && apt-get install -y

EXPOSE 80

RUN apt-get update \
    && apt-get install -y \
        build-essential \
        cmake \
        git \
        wget \
        unzip \
        yasm \
        pkg-config \
        libswscale-dev \
        libtbb2 \
        libtbb-dev \
        libjpeg-dev \
        libpng-dev \
        libtiff-dev \
        libavformat-dev \
        libpq-dev \
    && rm -rf /var/lib/apt/lists/*

RUN pip install -U opencv-python dlib
RUN pip install numpy
RUN pip install fastapi
RUN pip install uvicorn
RUN pip install python-multipart

COPY ./app .

CMD uvicorn main:app --host 0.0.0.0 --port 80
