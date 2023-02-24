package edu.eci.arep.app.webApps;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;

import edu.eci.arep.app.HttpServer;

public class FirstApp {
    public static void main(String[] args) throws IOException, ClassNotFoundException, IllegalAccessException, InvocationTargetException {
        HttpServer server = HttpServer.getInstance();
        server.run(args);
    }
    
}
