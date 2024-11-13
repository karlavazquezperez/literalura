
# Proyecto de Gestión de Libros y Autores

Este proyecto es una aplicación Java diseñada para gestionar libros y autores, permitiendo la búsqueda y registro de libros en una base de datos relacional. La aplicación consume una API pública para obtener detalles de libros y sus autores y los almacena en una base de datos utilizando JPA (Java Persistence API) y Hibernate. La aplicación también ofrece un menú interactivo para buscar y listar libros y autores registrados en la base de datos.

## Contenidos

- [Características](#características)
- [Funcionalidades](#funcionalidades)
- [Tecnologías Utilizadas](#tecnologías-utilizadas)
- [Estructura del Proyecto](#estructura-del-proyecto)
- [Instalación y Configuración](#instalación-y-configuración)
- [Uso](#uso)
- [Ejemplos de Uso](#ejemplos-de-uso)
- [Autora](#autora)


## Características

- Representación de datos de autores y libros mediante clases dedicadas.
- Repositorios de datos que permiten almacenar y recuperar información.
- Búsqueda y filtrado de libros y autores en función de ciertos criterios.
- Integración con API externa para ampliar la información disponible.
- Conversión de datos en distintos formatos.

## Funcionalidades

- **Buscar libro por título**: Realiza una búsqueda de un libro en línea (utilizando la API "Gutendex") y guarda sus detalles en la base de datos, incluyendo el autor y otros datos.
- **Listar libros registrados**: Muestra todos los libros guardados en la base de datos.
- **Listar autores registrados**: Muestra todos los autores guardados en la base de datos.
- **Listar autores vivos en un año específico**: Permite consultar qué autores estaban vivos en un año determinado.
- **Listar libros por idiomas**: Muestra todos los libros registrados en un idioma específico.

## Tecnologías Utilizadas

- **Java**: Lenguaje de programación principal.
- **Spring Boot**: Framework para la creación de aplicaciones Java, facilitando la configuración de dependencias y la creación de APIs REST.
- **JPA/Hibernate**: Para la persistencia de datos y el mapeo objeto-relacional.
- **PostgreSQL**: Base de datos utilizada para almacenar la información de libros y autores.
- **Jakarta Persistence (JPA)**: Para manejar las relaciones y la persistencia de entidades.
- **Jackson** para deserializar JSON.

## Estructura del Proyecto

El proyecto está dividido en:

- **Principal**: Clase principal que maneja la lógica del menú interactivo y coordina las operaciones.
- **Servicio de Consumo de API (ConsumoAPI.java)**: Clase que se conecta a una API pública para obtener detalles de libros y autores.
- **Conversión de Datos (ConvierteDatos.java)**: Clase que usa Jackson (`ObjectMapper`) para deserializar respuestas JSON en objetos Java.
- **Modelo**: Contiene las entidades `Libro` y `Autor`, que representan la estructura de la base de datos.
- **Repositorio**: Interfaces JPA para realizar operaciones CRUD en la base de datos.
- **Configuración de JPA**: Configuración automática para manejar la conexión con la base de datos.


## Instalación y Configuración

1. Clona este repositorio:
    ```bash
    git clone https://github.com/tu_usuario/nombre_del_repositorio.git
    ```
   
2. Configura la base de datos PostgreSQL y crea las tablas necesarias para libros y autores.

3. Actualiza el archivo `application.properties` con tus credenciales de base de datos PostgreSQL:
    ```properties
    spring.datasource.url=jdbc:postgresql://localhost:5432/nombre_de_tu_base_de_datos
    spring.datasource.username=tu_usuario
    spring.datasource.password=tu_contraseña
    spring.jpa.hibernate.ddl-auto=update
    spring.jpa.show-sql=true
    ```

4. Ejecuta la aplicación:
    ```bash
    ./mvnw spring-boot:run
    ```

## Uso

Una vez que la aplicación esté en ejecución, puedes realizar las siguientes acciones:

- **Agregar datos de autores y libros**.
- **Buscar autores y libros** dentro de la base de datos mediante el sistema de búsqueda integrado.

## Ejemplos de Uso

A continuación, se muestran capturas de pantalla del funcionamiento de la aplicación:

### Interfaz de Menú Principal
![Menú Principal](./screenshots/menu-principal.png)

### Opción 1
![Opción 1](./screenshots/opcion1.png)

### Opción 2
![Opción 2](./screenshots/opcion2.png)

### Opción 3
![Opción 3](./screenshots/opcion3.png)

### Opción 4
![Opción 4](./screenshots/opcion4.png)

### Opción 5
![Opción 5 parte 1](./screenshots/opcion5-parte1.png)
![Opción 5 parte 2](./screenshots/opcion5-parte2.png)


## Autora

Este proyecto fue desarrollado por [Karla Vázquez Pérez](https://github.com/karlavazquezperez), egresada de Ingeniería en Computación.


