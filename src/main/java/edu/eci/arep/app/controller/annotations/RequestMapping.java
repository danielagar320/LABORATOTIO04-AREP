package edu.eci.arep.app.controller.annotations;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.RUNTIME)

public @interface RequestMapping {
    String value();
    
}
