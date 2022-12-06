package com.example.bsuir.rest.impl;

import com.example.bsuir.exception.ThirdPartyApiException;
import com.example.bsuir.rest.HttpRequestSender;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.UUID;

import static java.net.HttpURLConnection.HTTP_OK;
import static java.nio.charset.StandardCharsets.UTF_8;

@Service
public class HttpPostRequestSender implements HttpRequestSender {

    @Value("${project.api.url}")
    private String urlAddress;

    @Getter
    private String response;

    private String boundary;

    private InputStreamReader inputStreamReader;

    @Override
    public void send(MultipartFile multipartFile) {
        boundary = UUID.randomUUID().toString();
        HttpURLConnection connection = openConnection();
        try (OutputStream outputStream = connection.getOutputStream()) {
            buildRequest(outputStream, multipartFile).flush();
            inputStreamReader = getInputStreamReader(connection);
            response = buildResponse();
        } catch (IOException exception) {
            throw new ThirdPartyApiException("Can't create connection output stream", exception);
        } finally {
            connection.disconnect();
        }
    }

    private OutputStream buildRequest(OutputStream source, MultipartFile file) {
        try (InputStream is = file.getInputStream()) {
            String header = String.format(
                    "--%s\r\nContent-Disposition: form-data; name=\"file\"; filename=\"%s\"\r\nContent-Type: "
                    + "application/octet-stream\r\n\r\n",
                    boundary, file.getName());
            source.write(header.getBytes(UTF_8));

            int size = -1;
            byte[] image = file.getBytes();
            while (-1 != (size = is.read(image))) {
                source.write(image, 0, size);
            }

            source.write(("\r\n--" + boundary + "--\r\n").getBytes(UTF_8));
            return source;
        } catch (IOException e) {
            throw new ThirdPartyApiException("Can't build request");
        }
    }

    private InputStreamReader getInputStreamReader(HttpURLConnection con) throws IOException {
        return new InputStreamReader(con.getResponseCode() == HTTP_OK
                                     ? con.getInputStream()
                                     : con.getErrorStream()
        );
    }

    private HttpURLConnection openConnection() {
        try {
            URL url = new URL(urlAddress);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setDoOutput(true);
            connection.setDoInput(true);
            connection.setConnectTimeout(5000);
            connection.setReadTimeout(5000);
            connection.setRequestMethod("POST");
            connection.setRequestProperty("Content-Type", "multipart/form-data; boundary=" + boundary);
            return connection;
        } catch (IOException exception) {
            throw new ThirdPartyApiException(
                    String.format("Impossible connect to %s", urlAddress),
                    exception
            );
        }
    }

    private String buildResponse() {
        StringBuilder builder = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(inputStreamReader)) {
            String line;
            while ((line = reader.readLine()) != null) {
                builder.append(line);
            }
        } catch (IOException exception) {
            throw new ThirdPartyApiException("Can't process response body", exception);
        }
        return builder.toString();
    }
}
