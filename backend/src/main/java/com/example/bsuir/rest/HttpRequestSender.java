package com.example.bsuir.rest;

import org.springframework.web.multipart.MultipartFile;

public interface HttpRequestSender {

    void send(MultipartFile multipartFile);

    String getResponse();
}
