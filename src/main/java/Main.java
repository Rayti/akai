import com.google.gson.Gson;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Comparator;
import java.util.stream.Stream;

public class Main {

    public static void main(String[] args) throws IOException, InterruptedException {
        /*
        Twoim zadaniem jest napisanie prostego programu do pobierania i transformowania danych
        udostępnianych przez API. Dokumentacje API możesz znależć pod poniższym linkiem:
        https://akai-recruitment.herokuapp.com/documentation.html
        Całe API zawiera jeden endpoint: https://akai-recruitment.herokuapp.com/book
        Endpoint ten zwraca liste książek zawierajacch informację takie jak:
        - id
        - tytuł
        - autor
        - ocena
        Twoim zadaniem jest:
        1. Stworzenie odpowiedniej klasy do przechowywania informacji o książce
        2. Sparsowanie danych udostępnianych przez endpoint. Aby ułatwić to zadanie,
           do projektu są dołaczone 3 najpopularniejsze biblioteki do parsowania JSONów
           do obiektów Javy - Gson, Org.Json, Jackson. Możesz wykorzystać dowolną z nich
        3. Po sparsowaniu JSONu do obiektów Javy, uzupełnij program o funkcję wypisującą 3 autorów z
           najwyższą średnią ocen (wypisz także średnie ocen)
       Projekt został utworzony przy użyciu najnowszej Javy 17,
       jednakże nic nie stoi na przeszkodzie użycia innej wersji jeśli chcesz
     */

        HttpClient client = HttpClient.newHttpClient();

        URI endpoint = URI.create("https://akai-recruitment.herokuapp.com/book");

        HttpRequest request = HttpRequest.newBuilder()
                .uri(endpoint)
                .GET()
                .build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        Book[] books = new Gson().fromJson(response.body(), Book[].class);

        Stream.of(books)
                .sorted(Comparator.comparingDouble(Book::getRating).reversed())
                .limit(3)
                .forEach(book -> System.out.printf("%s - %.1f\n", book.getAuthor(), book.getRating()));

    }
}
