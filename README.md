# LABORATORIO 1 AREP

Creacion de API que busca la información asociada a una pelicula introducida por el usuario.

### Prerequisitos
* Maven: Herramienta para la gestión y construcción de proyectos.
* Java: Lenguaje de programación.
* Git: Sistema de control de versiones distribuido.


### Uso de la aplicación

Para poder correr el programa se deben seguir los siguientes pasos: 
* Clonar el respositorio con el comando

```
git clone https://github.com/danielagar320/LABORATORIO1-AREP.git

```
* Ingresar a la capeta descargada y ejecutar el siguiente comando

```
mvn clean package exec:java -D "exec.mainClass"="edu.eci.taller1arep.HttpServer" 

```

* Entrar al browser que desee e ingresar la siguiente direccion: 

http://localhost:35000


* Por ultimo digitar la pelicula de la cual se desea consultar la información.

### Documentación

* Para generar el JavaDoc ejecutar el siguiente comando:

```
mvn javadoc:javadoc 

```

### Construido con

* [NetBeans](https://netbeans.apache.org/) 


### Autor

* **Daniela García Romero**:[danielagar320](https://github.com/danielagar320)






