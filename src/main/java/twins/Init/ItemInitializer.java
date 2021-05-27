package twins.Init;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.List;
import java.util.Random;
import javax.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import com.fasterxml.jackson.databind.ObjectMapper;
import twins.boundaries.ItemBoundry;


public class ItemInitializer {
	
	@Component
	@Profile("initAfekaMessages")
	public class MessageInitializer implements CommandLineRunner{
		private RestTemplate restTemplate;
		private String url;
		private int port;
		private ObjectMapper jackson;
		
		@Value("${server.port:8080}")
		public void setPort(int port) {
			this.port = port;
		}
		
		@PostConstruct
		public void init (){
			this.restTemplate = new RestTemplate();
			this.url = "http://localhost:" + port + "/twins/items/2021b.katyaBoyko/admin@admin.com";
			this.jackson = new ObjectMapper();
		}

		// this method is invoked by Spring once all spring beans are initialized
		@Override
		public void run(String... args) throws Exception {
			List<ItemBoundry> allBoundariesToPost = new ArrayList<>();
			// first message
			ItemBoundry item = new ItemBoundry();
			Map<String, Object> map = new HashMap<String, Object>();
			List<String> subjects = new ArrayList<>();
			subjects.add("HORROR");
			subjects.add("FANTASY");
			subjects.add("FICTION");
			subjects.add("DRAMA");
			subjects.add("COMPUTERS");
			map.put("Subject", subjects.get(0));
			item.setItemAttributes(map);
			allBoundariesToPost.add(item);
			
//			HelloBoundary messageReturnedFromServer ;
//			for (HelloBoundary msg : allBoundariesToPost) {
//				messageReturnedFromServer =  this.restTemplate
//						.postForObject(this.url, message, HelloBoundary.class);
//				System.err.println(convertToJson(messageReturnedFromServer));
//			}
			
			// Streaming API
//			allBoundariesToPost // List<HelloBoundary>
//				.stream() // Stream<HelloBoundary>
////				.map(new Function<HelloBoundary, HelloBoundary>() {
	////
////					@Override
////					public HelloBoundary apply(HelloBoundary input) {
	////
////						return restTemplate
////								.postForObject(url, input, HelloBoundary.class);
////					}
////				}) 
//				.map(input -> restTemplate  // lambda expression
//								.postForObject(url, input, HelloBoundary.class)) // Stream<HelloBoundary>
////				.map(new Function<HelloBoundary, String>() {
	////
////					@Override
////					public String apply(HelloBoundary input) {
////						return convertToJson(input);
////					}
////				})
//				.map(this::convertToJson)// use method reference and finally return Stream <String>
////				.forEach(new Consumer<String>() {
	////
////					@Override
////					public void accept(String input) {
////						System.err.println(input);
////					}
////				}); // return nothing (void)
//				.forEach(System.err::println); // use method reference to print data
			
			
			allBoundariesToPost // List<HelloBoundary>
				.stream() // Stream<HelloBoundary>
				.map(input -> restTemplate  // lambda expression
							.postForObject(url, input, ItemBoundry.class)) // Stream<HelloBoundary>
				.map(this::convertToJson)// use method reference and finally return Stream <String>
				.forEach(System.err::println); // use method reference to print data and finish handling the stream
		}

		private String convertToJson (Object object) {
			try {
				return this.jackson
					.writeValueAsString(object);
			}catch (Exception e) {
				throw new RuntimeException(e);
			}
		}
	}

	
}
