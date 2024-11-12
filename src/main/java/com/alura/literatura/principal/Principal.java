package com.alura.literatura.principal;

import java.util.*;

import com.alura.literatura.model.*;
import com.alura.literatura.repository.AutorRepository;
import com.alura.literatura.repository.LibroRepository;
import com.alura.literatura.service.ConsumoAPI;
import com.alura.literatura.service.ConvierteDatos;


import java.util.stream.Collectors;


public class Principal {
    private Scanner teclado = new Scanner(System.in);
    private ConsumoAPI consumoApi = new ConsumoAPI();
    private final String URL_BASE = "https://gutendex.com/books/";
    private ConvierteDatos conversor = new ConvierteDatos();

    private List<DatosLibro> datosLibro = new ArrayList<>();
    private LibroRepository repositorioLibro;
    private AutorRepository repositorioAutor;

    public Principal(LibroRepository repository,AutorRepository repository2) {
        this.repositorioLibro=repository;
        this.repositorioAutor=repository2;
    }


    public void muestraElMenu(){
        var opcion=-1;
        while(opcion!=0){
            System.out.println("Elija la opción a través de su número: ");
            var menu = """
                    1 - Buscar libro por título
                    2 - Listar libros registrados
                    3 - Listar autores registrados
                    4 - Listar autores vivos en un determinado año
                    5 - Listar libros por idiomas
                                  
                    0 - Salir
                    """;
            System.out.println(menu);
            opcion = teclado.nextInt();
            teclado.nextLine();

            switch (opcion) {
                case 1:
                    buscarLibroWeb();
                    break;
                case 2:
                    imprimirLibrosRegistrados();
                    break;
                case 3:
                    imprimirAutoresRegistrados();
                    break;
                case 4:
                    listarAutoresVivosEnAnio();
                    break;
                case 5:
                    listarLibrosPorIdioma();
                    break;


                case 0:
                    System.out.println("Cerrando la aplicación...");
                    break;
                default:
                    System.out.println("Opción inválida");
            }
        }
    }

    private void imprimirLibrosRegistrados() {
        List<Libro> libros=repositorioLibro.findAllWithIdiomas();
        if(libros.isEmpty()){
            System.out.println("No hay libros registrados :(");
        }else{
            System.out.println("Libros registrados: ");
            for(Libro libro : libros){
                imprimirLibro(libro);
            }
        }
    }
    private void imprimirAutoresRegistrados() {
        List<Autor> autores = repositorioAutor.findAllWithLibros();

        if (autores.isEmpty()) {
            System.out.println("No hay autores registrados :(");
        } else {
            System.out.println("Autores registrados:");
            for (Autor autor : autores) {
                System.out.println("----- AUTOR -----");
                System.out.println("Nombre del autor: " + autor.getNombre());
                System.out.println("Fecha de nacimiento: " + autor.getFechaNacimiento());
                System.out.println("Fecha de defunción: " + Optional.ofNullable(autor.getFechaDefunsion()).orElse(0));
                Set<Libro> librosAutor=autor.getLibros();
                System.out.println("Libros: " + librosAutor.stream()
                        .map(Libro::getTitulo)
                        .collect(Collectors.joining(", ")));

                System.out.println("-----------------");
            }
        }
    }

    private DatosLibro getDatosLibro() {
        System.out.println("Escribe el nombre del libro que deseas buscar");
        var nombreLibro = teclado.nextLine();

        try {

            String jsonBusqueda = consumoApi.obtenerDatos(URL_BASE + "?search=" + nombreLibro.replace(" ", "+"));
            System.out.println("Resultado de la búsqueda: " + jsonBusqueda);


            RespuestaBusqueda respuestaBusqueda = conversor.obtenerDatos(jsonBusqueda, RespuestaBusqueda.class);

            if (respuestaBusqueda.getResults().isEmpty()) {
                System.out.println("No se encontraron libros con ese nombre.");
                return null;
            }


            return respuestaBusqueda.getResults().get(0);
        } catch (Exception e) {
            System.out.println("Ocurrió un error al buscar el libro: " + e.getMessage());
            return null;
        }
    }



    private void buscarLibroWeb() {
        DatosLibro datos = getDatosLibro();
        if (datos != null) {
            Set<Autor> autoresLibro = new HashSet<>();

            for (DatosAutor datosAutor : datos.autores()) {
                Optional<Autor> autorExistente = repositorioAutor.findByNombre(datosAutor.nombre());

                Autor autor;
                if (autorExistente.isPresent()) {
                    autor = autorExistente.get();
                } else {

                    autor = new Autor(datosAutor);
                    repositorioAutor.save(autor);
                    System.out.println("Autor guardado: " + datosAutor);
                }


                autoresLibro.add(autor);
            }


            Libro libro = new Libro(datos);
            libro.addAutores(autoresLibro);
            repositorioLibro.save(libro);
            System.out.println("Libro guardado: " + datos);
            imprimirLibro(libro);
        } else {
            System.out.println("No se pudo guardar el libro porque no se encontraron detalles.");
        }
    }





    private void listarAutoresVivosEnAnio() {
        System.out.print("Ingrese el año para buscar autores vivos: ");
        Scanner scanner = new Scanner(System.in);
        int anio = scanner.nextInt();
        List<Autor> autoresVivos = repositorioAutor.findAutoresVivosEnAnio(anio);

        if (autoresVivos.isEmpty()) {
            System.out.println("No hay autores vivos en el año " + anio + ".");
        } else {
            System.out.println("Autores vivos en el año " + anio + ":");
            for (Autor autor : autoresVivos) {
                System.out.println("----- AUTOR -----");
                System.out.println("Nombre: " + autor.getNombre());
                System.out.println("Fecha de nacimiento: " + autor.getFechaNacimiento());
                System.out.println("Fecha de defunción: " + Optional.ofNullable(autor.getFechaDefunsion()).map(String::valueOf).orElse("Vivo"));
                System.out.println("-----------------");
            }
        }

    }
    private void listarLibrosPorIdioma() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Ingrese el idioma para buscar los libros:");
        System.out.println("es - español");
        System.out.println("en - inglés");
        System.out.println("fr - francés");
        System.out.println("pt - portugués");
        String idioma = scanner.nextLine();

        List<Libro> librosPorIdioma = repositorioLibro.findLibrosByIdioma(idioma);

        if (librosPorIdioma.isEmpty()) {
            System.out.println("No se encontraron libros en el idioma especificado.");
        } else {
            System.out.println("Libros en el idioma: " + idioma);
            for (Libro libro : librosPorIdioma) {
                imprimirLibro(libro);
            }
        }
    }


    public void imprimirLibro(Libro libro) {
        System.out.println("----- LIBRO -----");
        System.out.println("Título: " + libro.getTitulo());

        System.out.print("Autor(es): ");
        for (Autor autor : libro.getAutores()) {
            System.out.print(autor.getNombre() + " ");
        }
        System.out.println();

        System.out.print("Idioma: ");
        for (String idioma : libro.getIdiomas()) {
            System.out.print(idioma + " ");
        }
        System.out.println();

        System.out.println("Número de descargas: " + libro.getDescargas());
        System.out.println("-----------------");
    }




}
