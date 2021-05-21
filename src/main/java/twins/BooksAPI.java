package twins;

import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

public class BooksAPI {

    final static String BASE_URL = "https://www.googleapis.com/books/v1/volumes";
    final static String MAX_RESULTS ="10";

    public static Mono<Books> searchByTitle(String title) {
        WebClient client = WebClient.create(BASE_URL);
        return client.get()
                .uri("?q=" + title + "&maxResults="+ MAX_RESULTS)
                .retrieve()
                .bodyToMono(Books.class);
    }
}

