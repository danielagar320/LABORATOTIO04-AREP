package edu.eci.arep.app.services;


import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;


public class ServicePng implements RESTService {

    @Override
    public String getHeader() {
        return "HTTP/1.1 200 OK\r\n"
        + "Content-Type: image/png\r\n"
        + "\r\n";
    }

    @Override
    public String getResponse() {
        String archivo = null;
        try {
            Path file = new File("src/main/resource/image.png").toPath();
            archivo = Files.probeContentType(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return archivo;
    }    
}
