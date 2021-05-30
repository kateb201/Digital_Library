package twins;
//import org.springframework.web.reactive.function.client.WebClient;
//import reactor.core.publisher.Mono;
import twins.boundaries.Books;

import java.util.Map;

import org.springframework.web.client.RestTemplate;

public class BooksAPI {

    final static String BASE_URL = "https://www.googleapis.com/books/v1/volumes";
    public final static String MAX_RESULTS = "10";

    //public static Mono<Books> searchByTitle(Map<String, Object> details) {
    public static Books searchByTitle(Map<String, Object> details) {
    	RestTemplate rest = new RestTemplate();
        //deal with map
        StringBuilder uri = new StringBuilder();
        for (Map.Entry<String, Object> entry : details.entrySet()) {
            switch (entry.getKey()) {
                case "Title":
                    uri.append((String) entry.getValue());
                case "Author": {
                    if (uri.length() != 0) {
                        uri.append("+");
                    }
                    uri.append("inauthor");
                    uri.append((String) entry.getValue());
                }
                case "Publisher": {
                    if (uri.length() != 0) {
                        uri.append("+");
                    }
                    uri.append("inpublisher");
                    uri.append((String) entry.getValue());
                }
                case "Subject": {
                    if (uri.length() != 0) {
                        uri.append("+");
                    }
                    uri.append("insubject");
                    uri.append((String) entry.getValue());
                }
            }

        }
        //WebClient client = WebClient.create(BASE_URL);
        //return client.get()
                //.uri("?q=" + uri + "&maxResults=" + MAX_RESULTS)
                //.retrieve()
                //.bodyToMono(Books.class);
        return rest.getForObject(BASE_URL + "?q=" + uri + "&maxResults=" + MAX_RESULTS, Books.class); //NOT WORKING        
    }
}

