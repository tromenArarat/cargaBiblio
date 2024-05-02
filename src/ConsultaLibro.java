import com.google.gson.Gson;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ConsultaLibro {
    public VolumeInfo buscaLibroXisbn(String isbn) {
        URI direccion = URI.create("https://www.googleapis.com/books/v1/volumes?q=" + isbn);

        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(direccion)
                .build();
        try {
            HttpResponse<String> response = client
                    .send(request, HttpResponse.BodyHandlers.ofString());

            // Parse JSON response
            GoogleBooksResponse googleBooksResponse = new Gson().fromJson(response.body(), GoogleBooksResponse.class);

            // Access the first item in the list
            Item item = googleBooksResponse.items.get(0);

            // Return volumeInfo of the book
            return item.volumeInfo;
        } catch (Exception e) {
            throw new RuntimeException("No encontré ese libro o hubo un problema de conexión.");
        }
    }

public VolumeInfo buscaLibroXtitulo(String titulo) {
        Scanner lectura = new Scanner(System.in);
        URI direccion = URI.create("https://www.googleapis.com/books/v1/volumes?=q" + titulo);

        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(direccion)
                .build();
        try {
            HttpResponse<String> response = client
                    .send(request, HttpResponse.BodyHandlers.ofString());

            // Parse JSON response
            GoogleBooksResponse googleBooksResponse = new Gson().fromJson(response.body(), GoogleBooksResponse.class);

            int size = googleBooksResponse.items.size();


            System.out.println("Seleccione autor:");
            for (int i = 0; i < size; i++) {
                Item broli = googleBooksResponse.items.get(i);
                if (broli.volumeInfo.categories == null || broli.volumeInfo.categories.length == 0) {
                    broli.volumeInfo.categories = new String[]{"Universal"};
                }
                if(broli.volumeInfo.publisher==null){
                    broli.volumeInfo.publisher = new String("S/E");
                }if(broli.volumeInfo.authors==null|| broli.volumeInfo.authors.length == 0){
                    broli.volumeInfo.authors = new String[]{"S/A"};
                }
                System.out.println("("+i+") "+ broli.volumeInfo.title + " De: "+broli.volumeInfo.authors[0]+" Publicado por: "+broli.volumeInfo.publisher);
            }
            int rta = lectura.nextInt();
            return googleBooksResponse.items.get(rta).volumeInfo;
        } catch (Exception e) {
            throw new RuntimeException("No encontré ese libro o hubo un problema de conexión.");
        }
    }

    public VolumeInfo buscaLibroXautor(String autor) {
        Scanner lecture = new Scanner(System.in);
        URI direccion = URI.create("https://www.googleapis.com/books/v1/volumes?q=" + autor);

        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(direccion)
                .build();
        try {
            HttpResponse<String> response = client
                    .send(request, HttpResponse.BodyHandlers.ofString());

            // Parse JSON response
            GoogleBooksResponse googleBooksResponse = new Gson().fromJson(response.body(), GoogleBooksResponse.class);

            int size = googleBooksResponse.items.size();


            System.out.println("Seleccione título:");

            for (int i = 0; i < size; i++) {
                Item broli = googleBooksResponse.items.get(i);
                if(broli.volumeInfo.publisher==null){
                    broli.volumeInfo.publisher="N/A";
                }
                System.out.println("("+i+") "+broli.volumeInfo.title+" Publicado por: "+broli.volumeInfo.publisher);
            }
            int rta = lecture.nextInt();


            return googleBooksResponse.items.get(rta).volumeInfo;
        } catch (Exception e) {
            throw new RuntimeException("No encontré ese libro o hubo un problema de conexión.");
        }
    }
}
