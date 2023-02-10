package edu.eci.taller1arep.services;

import edu.eci.taller1arep.HttpServer;


import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.DataOutputStream;
import java.io.ByteArrayOutputStream;

public class ServiceJpg implements RESTService {

    @Override
    public String getHeader() {
        return "HTTP/1.1 200 OK\r\n"
        + "Content-Type: image/jpg\r\n"
        + "\r\n";
    }

    @Override
    public String getResponse() throws IOException {
        String archivo;
        try{
            archivo = getImage();

        }catch(IOException e){
            throw new RuntimeException(e);
        }
        return archivo;
    }

    private String getImage() throws IOException {
        String answer = "HTTP/1.1 200 OK\r\n"
        + "Content-Type: image/jpg\r\n"
        + "\r\n";
        BufferedImage BufferedImage = ImageIO.read(new File("src/main/resource/imagen.jpg"));
        ByteArrayInputStream byteArrayOutputStream = new ByteArrayOutputStream();
        HttpServer server = HttpServer.getInstance();
        DataOutputStream dataOutputStream = new DataOutputStream(server.getOutputStream());
        ImageIO.write(BufferedImage, "jpg", byteArrayOutputStream);
        dataOutputStream.writeBytes(answer);
        dataOutputStream.write(byteArrayOutputStream.toByteArray());
        return answer;
    }
    
}
