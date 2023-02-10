package edu.eci.arep.app.webApps;

import java.io.IOException;

import edu.eci.arep.app.HttpServer;
import edu.eci.arep.app.services.ServiceHtml;
import edu.eci.arep.app.services.ServiceCss;
import edu.eci.arep.app.services.ServiceJs;
import edu.eci.arep.app.services.ServicePng;

public class FirstApp {
    public static void main(String[] args) throws IOException {
        HttpServer server = HttpServer.getInstance();
        server.servicios("/index", new ServiceHtml());
        server.servicios("/style", new ServiceCss());
        server.servicios("/main", new ServiceJs());
        server.servicios("/image", new ServicePng());
        server.run(args);
    }
    
}
