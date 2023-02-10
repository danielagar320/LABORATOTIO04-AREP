package edu.eci.arep.app;

import java.io.IOException;
import java.util.concurrent.ConcurrentHashMap;

public class Cache {
    public static ConcurrentHashMap<String,String> movies = new ConcurrentHashMap<>();

    /**
     * Busca el titulo en de la memoria
     * @param titulo String del titulo de la pelicula
     * @return Si el tutulo se encuentra en memoria retorna la información
     * @throws IOException
     */
    public static String inMemory(String titulo) throws IOException {
        String n="";
        if (movies.containsKey(titulo)){
            n += movies.get(titulo);
        }else{
            n += HttpClient.consultarInfo(titulo);
            movies.put(titulo,n);
        }
        return n;
    }
}
