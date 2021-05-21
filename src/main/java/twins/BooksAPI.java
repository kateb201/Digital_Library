package twins;

import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import twins.boundaries.BookBoundary;

public class BooksAPI {

    final static String baseUrl = "https://www.googleapis.com/books/v1/volumes?q=";

    public static Flux<BookBoundary> searchByTitle(String title) {
        WebClient client = WebClient.create(baseUrl + "&maxResults=10");
        return client.get()
                .uri(title)
                .retrieve()
                .bodyToFlux(BookBoundary.class);
    }
}

