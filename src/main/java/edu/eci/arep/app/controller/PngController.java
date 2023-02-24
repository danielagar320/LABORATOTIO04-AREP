package edu.eci.arep.app.controller;
import edu.eci.arep.app.HttpServer;
import edu.eci.arep.app.controller.componentes.Component;
import edu.eci.arep.app.controller.componentes.RequestMapping;


import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.IOException;

@Component
public class PngController {
    @RequestMapping(value = "/image")
    public static String index() throws IOException {
        return body();
    }

    private static String body() throws IOException {
        String response = "HTTP/1.1 200 OK\r\n" +
                "Content-type: image/png\r\n"+"\r\n";
        BufferedImage bufferedImage = ImageIO.read(new File("src/main/resource/image.png"));
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        HttpServer server = HttpServer.getInstance();
        DataOutputStream dataOutputStream = new DataOutputStream(server.getOutputStream());
        ImageIO.write(bufferedImage, "png", byteArrayOutputStream);
        dataOutputStream.writeBytes(response);
        dataOutputStream.write(byteArrayOutputStream.toByteArray());
        return response;
    }
    
}
