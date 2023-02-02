/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.tallerarep;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Map;
import java.util.Set;
import java.util.List;
import java.net.*;


/**
 *
 * @author daniela.garcia-r
 */
public class URLReaderWithHeader {
    
    public static void main(String[] args) throws MalformedURLException, IOException{
    // Crea el objeto que representa una URL
    URL siteURL = new URL("http://www.google.com/");
    // Crea el objeto que URLConnection
    URLConnection urlConnection = siteURL.openConnection();
    // Obtiene los campos del encabezado y los almacena en un estructura Map
    Map<String, List<String>> headers = urlConnection.getHeaderFields();
    // Obtiene una vista del mapa como conjunto de pares <K,V>
    // para poder navegarlo
    Set<Map.Entry<String, List<String>>> entrySet = headers.entrySet();
    // Recorre la lista de campos e imprime los valores
    for (Map.Entry<String, List<String>> entry : entrySet) {
    String headerName = entry.getKey();
    //Si el nombre es nulo, significa que es la linea de estado
    if(headerName !=null){System.out.print(headerName + ":");}
    List<String> headerValues = entry.getValue();
    for (String value : headerValues) {
        System.out.print(value);
    }
    System.out.println("");
    //System.out.println("");
    }
    System.out.println("-------message-body------");
    try (BufferedReader reader 
            = new BufferedReader(new InputStreamReader(siteURL.openStream()))) {
        String inputLine = null;
        while ((inputLine = reader.readLine()) != null) {
            System.out.println(inputLine);
        }
        } catch (IOException x) {
            System.err.println(x);
        }
    }
}

