/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.eci.arep.app;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Objects;

/**
 *
 * @author daniela.garcia-r
 */
public class HttpClient {
    private static final String USER_AGENT = "Mozilla/5.0";
    private static String GET_URL = "http://www.omdbapi.com/?t=";
    private static final String API_KEY = "&apikey=b67e1f4e";

        /**
     *
     * @param title String del titulo de la pelicula que se desea buscar
     * @return JSON con la informacion de la pelicula
     * @throws IOException
     */
    public static String consultarInfo(String title) throws IOException {
        String res = "";
        if (!Objects.equals(title, "")) {
            GET_URL += title;
            GET_URL += API_KEY;

            URL obj = new URL(GET_URL);
            HttpURLConnection con = (HttpURLConnection) obj.openConnection();
            con.setRequestMethod("GET");
            con.setRequestProperty("User-Agent", USER_AGENT);
            int responseCode = con.getResponseCode();
            System.out.println("GET Response Code :: " + responseCode);

            if (responseCode == HttpURLConnection.HTTP_OK) { // success
                BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
                String inputLine;
                StringBuffer response = new StringBuffer();

                while ((inputLine = in.readLine()) != null) {
                    response.append(inputLine);
                }
                in.close();

                res = response.toString();
            } else {
                res = "GET request not worked";
                System.out.println("GET request not worked");
            }
            System.out.println("GET DONE");
        }
        GET_URL = "http://www.omdbapi.com/?t=";
        return res;
    }
}

    