package twins;

import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

public class BooksAPI {

    final static String baseUrl = "https://www.googleapis.com/books/v1/volumes?q=";

    public static Mono<Books> searchByTitle(String title) {
        WebClient client = WebClient.create(baseUrl);
        return client.get()
                .uri("?q=" + title + "&maxResults=10")
                .retrieve()
                .bodyToMono(Books.class);
    }
}

