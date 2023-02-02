/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */


/**
 *
 * @author daniela.garcia-r
 */

package com.mycompany.tallerarep;
import java.io.*;
import java.net.*;

public class URLReader {
        public static void main(String[] args) throws Exception {
        URL google = new URL("http://www.google.com/");
        try (BufferedReader reader 
            = new BufferedReader(new InputStreamReader(google.openStream()))) {
        String inputLine = null;
        while ((inputLine = reader.readLine()) != null) {
            System.out.println(inputLine);
        }
        } catch (IOException x) {
            System.err.println(x);
        }
        }
}


