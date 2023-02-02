/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Project/Maven2/JavaApp/src/main/java/${packagePath}/${mainClassName}.java to edit this template
 */

package com.mycompany.tallerarep;
import java.net.MalformedURLException;
import java.net.URL;

/**
 *
 * @author daniela.garcia-r
 */
public class Tallerarep {

    public static void main(String[] args) throws MalformedURLException {
        URL personalSite = new URL("http://ldbn.escuelaing.edu.co:80/respuestas.txt?name=pedro#Daniela");
        
        System.out.println("Protocol: " + personalSite.getProtocol());
        System.out.println("Authority: " + personalSite.getAuthority());
        System.out.println("Host: " + personalSite.getHost());
        System.out.println("Port: " + personalSite.getPort());
        System.out.println("Path: " + personalSite.getPath());
        System.out.println("Query: " + personalSite.getQuery());
        System.out.println("File: " + personalSite.getFile());
        System.out.println("Ref: " + personalSite.getRef());
    
    }
}
