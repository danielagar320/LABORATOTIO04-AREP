package edu.eci.arep.app;
import java.net.*;
import java.io.*;
import java.util.*;
import java.lang.reflect.Method;
import java.lang.reflect.InvocationTargetException;

import java.util.Arrays;
import java.util.HashMap;

import edu.eci.arep.app.sparkServices.Answer;



import edu.eci.arep.app.controller.annotations.Component;
import edu.eci.arep.app.controller.annotations.RequestMapping;


/**
 *
 * @author daniela.garcia-r
 */
public class HttpServer {

    private static HttpServer instance = new HttpServer();
    private Map<String, Method> services = new HashMap<>();
    private Answer ans;
    private static OutputStream outputStream = null;
    private final String direccion = "edu/eci/arep/app/controller";

    private HttpServer(){}

    public static HttpServer getInstance(){
        return instance;
    }


    public void run(String[] args) throws IOException, ClassNotFoundException {
        List<Class<?>> classes = getClasses();
        for (Class<?> clasS:classes){
            if(clasS.isAnnotationPresent(Component.class)){
                Class<?> c = Class.forName(clasS.getName());
                Method[] m = c.getMethods();
                for (Method me: m){
                    if(me.isAnnotationPresent(RequestMapping.class)){
                        String key = me.getAnnotation(RequestMapping.class).value();
                        services.put(key,me);
                    }
                }
            }

        }
        ServerSocket serverSocket = null;
        try {
            serverSocket = new ServerSocket(35000);
        } catch (IOException e) {
            System.err.println("Could not listen on port: 35000.");
            System.exit(1);
        }

        boolean running = true;
        while(running) {
            Socket clientSocket = null;
            try {
                System.out.println("Listo para recibir ...");
                clientSocket = serverSocket.accept();
            } catch (IOException e) {
                System.err.println("Accept failed.");
                System.exit(1);
            }
            PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
            BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            String inputLine, outputLine = null;
            String title = "";
            boolean first_line = true;
            String request = "/simple";
            String verb = "";
            outputStream = clientSocket.getOutputStream();
            while ((inputLine = in.readLine()) != null) {
                System.out.println("Received: " + inputLine);
                if(first_line){
                    request = inputLine.split(" ")[1];
                    verb = inputLine.split(" ")[0];
                    first_line = false;
                }
                if(inputLine.contains("title?name")){
                    String[] firstSplit = inputLine.split("=");
                    title = (firstSplit[1].split("HTTP"))[0];
                }
                if (!in.ready()) {
                    break;
                }
            }
            if (Objects.equals(verb, "GET")) {
                System.out.println(request);
                if(services.containsKey(request)){
                    outputLine = services.get(request).invoke(null).toString();
                }
            }
            else if(!Objects.equals(title, "")){
                outputLine = answer(title);
            }else {
                outputLine = respuesta();
            }
            out.println(outputLine);
            out.close();
            in.close();
            clientSocket.close();
        }
        serverSocket.close();
    }

    /**
     * Metodo que organiza el formato JSON en una tabla
     * @param info en formato Json con la informacion de la pelicula
     * @return Tabla en formato html
     */
    private static String table(String res){
        String[] info = res.split(":");
        String tabla = "<table> \n";
        for (int i = 0;i<(info.length);i++) {
                String[] temporalAnswer = info[i].split(",");
                tabla+="<td>" + Arrays.toString(Arrays.copyOf(temporalAnswer, temporalAnswer.length - 1)).replace("[","").replace("]","").replace("}","") + "</td>\n</tr>\n";
                tabla+="<tr>\n<td>" +  temporalAnswer[temporalAnswer.length-1].replace("{","").replace("[","") + "</td>\n";
        }
        tabla += "</table>";
        return tabla;

    }

    private static String respuesta(){
        return "HTTP/1.1 200 OK\r\n"
        + "Content-Type: text/html\r\n"
        + "\r\n"
        + "<!DOCTYPE html>\n" +
        "<html>\n" +
        "    <head>\n" +
        "        <title>Form Example</title>\n" +
        "        <meta charset=\"UTF-8\">\n" +
        "        <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\n" +
        "    </head>\n" +
        "    <body bgcolor=\"#AB82FF\">\n" +
        "        <center><h1>Introduce el nombre de la pelicula</h1></center>\n" +
        "        <form action=\"/hello\">\n" +
        "            <center><label for=\"name\">Title:</label><br><center>\n" +
        "            <input type=\"text\" id=\"name\" name=\"name\" value=\"John\"><br><br>\n" +
        "            <input type=\"button\" value=\"Submit\" onclick=\"loadGetMsg()\">\n" +
        "        </form> \n" + "<br>"+
        "        <div id=\"getrespmsg\"></div>\n" +
        "\n" +
        "        <script>\n" +
        "            function loadGetMsg() {\n" +
        "                let nameVar = document.getElementById(\"name\").value;\n" +
        "                const xhttp = new XMLHttpRequest();\n" +
        "                xhttp.onload = function() {\n" +
        "                    document.getElementById(\"getrespmsg\").innerHTML =\n" +
        "                    this.responseText;\n" +
        "                }\n" +
        "                xhttp.open(\"GET\", \"/title?name=\"+nameVar);\n" +
        "                xhttp.send();\n" +
        "            }\n" +
        "        </script>\n" +
        "\n" +
        "</html>";
    }

    private static String answer(String title) throws IOException {
        return "HTTP/1.1 200 OK\r\n"
                + "Content-Type: application/json\r\n"
                + "\r\n" +
                "<style>\n" +
                "table, th, td {\n" +
                "  border:1px solid black;\n" +
                "}\n" +
                "</style>"+
                table(Cache.inMemory(title));
    }

    private List<Class<?>> getClasses(){
        List<Class<?>> classes = new ArrayList<>();
        try{
            for (String cp: classPaths()){
                File file = new File(cp + "/" + direccion);
                if(file.exists() && file.isDirectory()){
                    for (File cf: Objects.requireNonNull(file.listFiles())){
                        if(cf.isFile() && cf.getName().endsWith(".class")){
                            String rootTemp = direccion.replace("/",".");
                            String className = rootTemp+"."+cf.getName().replace(".class","");
                            Class<?> clasS =  Class.forName(className);
                            classes.add(clasS);
                        }
                    }
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return classes;
    }

    private ArrayList<String> classPaths(){
        String classPath = System.getProperty("java.class.path");
        String[] classPaths =  classPath.split(System.getProperty("path.separator"));
        return new ArrayList<>(Arrays.asList(classPaths));
    }


}