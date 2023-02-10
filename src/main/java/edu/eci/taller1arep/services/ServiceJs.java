package edu.eci.taller1arep.services;


import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class ServiceJs implements RESTService {

    @Override
    public String getHeader() {
        return "HTTP/1.1 200 OK\r\n"
        + "Content-Type: text/javascript\r\n"
        + "\r\n";
    }

    @Override
    public String getResponse() throws IOException {
        byte[] data = new byte[0];
        try{
            Path archivo = Paths.get("src/main/resource/main.js");
            data = Files.readAllBytes(archivo);

        }catch(IOException e){
            throw new RuntimeException(e);
        }
        return new String(data);
    }
    
}