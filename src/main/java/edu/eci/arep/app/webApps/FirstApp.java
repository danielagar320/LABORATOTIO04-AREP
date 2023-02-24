package edu.eci.arep.app.webApps;

import java.io.IOException;

import edu.eci.arep.app.HttpServer;
import edu.eci.arep.app.sparkServices.ServiceSpark;

public class FirstApp {
    public static void main(String[] args) throws IOException {
        HttpServer server = HttpServer.getInstance();
        server.run(args);
    }
    
}
