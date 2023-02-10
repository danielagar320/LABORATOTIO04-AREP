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
        server.addServices("/index", new ServiceHtml());
        server.addServices("/estilos", new ServiceCss());
        server.addServices("/javaScript", new ServiceJs());
        server.addServices("/imagen", new ServicePng());
        server.run(args);
    }
    
}
