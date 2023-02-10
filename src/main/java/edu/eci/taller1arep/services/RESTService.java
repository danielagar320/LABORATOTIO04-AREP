package edu.eci.taller1arep.services;

import java.io.IOException;

public interface RESTService{
    public String getHeader();

    public String getResponse() throws IOException;
}