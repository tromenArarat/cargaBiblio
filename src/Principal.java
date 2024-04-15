import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

public class Principal {
    public static void main(String[] args) throws IOException {
        Scanner lectura = new Scanner(System.in);
        ConsultaLibro consulta = new ConsultaLibro();
        List<VolumeInfo> biblioteca = new ArrayList<>();

        while(true){
            System.out.println("-x-|-x-x-|-x-x-|-x-x-|-x-");
            System.out.println("-_- Registro de libros -_-");
            System.out.println("-x-|-x-x-|-x-x-|-x-x-|-x-");
            System.out.println("Elija una opción:");
            System.out.println("1) Cargar");
            System.out.println("2) Ver cargados");
            System.out.println("3) Ordenar por temática");
            System.out.println("4) Ordenar por autor");
            System.out.println("5) Guardar biblioteca");
            System.out.println("- - - SALIR - - -");
            var busqueda = lectura.nextLine();

            if(busqueda.equalsIgnoreCase("salir")){
                break;
            }

            switch (busqueda) {
                case "1" -> {
                    System.out.println("1) Buscar por ISBN");
                    System.out.println("2) Buscar por título");
                    System.out.println("3) Buscar por autor");
                    System.out.println("- - - SALIR - - -");
                    var opcion = lectura.nextLine();
                    if (opcion.equalsIgnoreCase("salir")) {
                        break;
                    }
                    switch (opcion) {
                        case "1":
                            try {
                                System.out.println("ISBN del libro que desea cargar:");
                                String isbn13 = lectura.nextLine();
                                VolumeInfo book = consulta.buscaLibroXisbn(isbn13);
                                if (book != null) {
                                    System.out.println("Título: " + book.title);
                                    biblioteca.add(book);
                                    System.out.println("Libro agregado a tu biblioteca en la categoría: " + book.categories[0]);
                                    System.out.println("          ");
                                    break;
                                } else {
                                    System.out.println("No se encontró ningún libro con ese ISBN.");
                                    break;
                                }
                            } catch (NumberFormatException e) {
                                System.out.println("Número no encontrado " + e.getMessage());
                            } catch (RuntimeException e) {
                                System.out.println(e.getMessage());
                            }
                        case "2":
                            try {
                                System.out.println("Título del libro que desea cargar:");
                                String title = lectura.nextLine().replace(" ", "+") +
                                        "&apikey=d4d0bf92";
                                VolumeInfo book = consulta.buscaLibroXtitulo(title);

                                biblioteca.add(book);
                                System.out.println(book.title + " agregado a tu biblioteca en la categoría: " + book.categories[0]);
                                System.out.println("          ");
                                break;
                            } catch (NumberFormatException e) {
                                System.out.println("Número no encontrado " + e.getMessage());
                            } catch (RuntimeException e) {
                                System.out.println(e.getMessage());
                            }
                            System.out.println(".-.-.-..-.-");
                            System.out.println("En construcción");
                            System.out.println(".-.-.-..-.-");
                            System.out.println("          ");
                            break;
                        case "3":
                            try {
                                System.out.println("Autor del libro que desea cargar:");
                                String autor = lectura.nextLine().replace(" ", "+") +
                                        "&apikey=d4d0bf92";
                                VolumeInfo book = consulta.buscaLibroXautor(autor);

                                if (book.categories == null || book.categories.length == 0) {
                                    book.categories = new String[]{"Universal"};
                                }
                                biblioteca.add(book);
                                System.out.println(book.title + " agregado a tu biblioteca en la categoría: " + book.categories[0]);
                                System.out.println("          ");
                                break;
                            } catch (NumberFormatException e) {
                                System.out.println("Número no encontrado " + e.getMessage());
                            } catch (RuntimeException e) {
                                System.out.println(e.getMessage());
                            }
                            System.out.println(".-.-.-..-.-");
                            System.out.println("En construcción");
                            System.out.println(".-.-.-..-.-");
                            System.out.println("          ");
                            break;
                    }
                }
                case "2" -> {
                    for (int i = 0; i < biblioteca.size(); i++) {
                        System.out.println("__________" + i + "__________");
                        System.out.println("Título: " + biblioteca.get(i).title);
                        System.out.println("Autor/a: " + biblioteca.get(i).authors[0]);
                        System.out.println("Editorial: " + biblioteca.get(i).publisher);
                        System.out.println("Categoría: " + biblioteca.get(i).categories[0]);
                        System.out.println("          ");
                    }
                }
                case "3" -> {
                    biblioteca.sort(Comparator.comparing(volumeInfo -> volumeInfo.categories[0]));
                    System.out.println(".-.-.-..-.-");
                    System.out.println("Biblioteca ordenada por categorías");
                    System.out.println(".-.-.-..-.-");
                    System.out.println("          ");
                }
                case "4" -> {
//                    break;
                    biblioteca.sort(Comparator.comparing(volumeInfo -> volumeInfo.authors[0]));
                    System.out.println(".-.-.-..-.-");
                    System.out.println("Biblioteca ordenada por autores");
                    System.out.println(".-.-.-..-.-");
                    System.out.println("          ");
                }
                case "5" -> {
                    GeneradorDeArchivo generador = new GeneradorDeArchivo();
                    generador.guardarJson(biblioteca);
                    System.out.println("Archivo JSON guardado correctamente.");
                }
            }
        }


    }
}
