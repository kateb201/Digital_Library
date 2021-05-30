package twins.Init;
import twins.BooksAPI;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.List;
import java.util.Random;
import javax.annotation.PostConstruct;

import org.assertj.core.util.Arrays;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import com.fasterxml.jackson.databind.ObjectMapper;

import twins.boundaries.Books;
import twins.boundaries.CreatedBy;
import twins.boundaries.ItemBoundry;
import twins.boundaries.Items;
import twins.logic.*;
@Component
@Profile("initItems")
public class ItemInitializer implements CommandLineRunner{
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
			Map<String, Object> map = new HashMap<String, Object>();
			//"HORROR", "FANTASY", "FICTION", "DRAMA", "COMPUTERS"
			String[] subjects = {"HORROR", "FANTASY", "FICTION", "DRAMA", "COMPUTERS"};
			List<ItemBoundry> itemsSub = createSubItems(map, subjects);
			for (int i = 0; i < itemsSub.size(); i++) {
				Books fromAPI = searchBook(itemsSub.get(i).getItemAttributes());
				for (int j = 0; j < Integer.parseInt(BooksAPI.MAX_RESULTS); j++) {
					ItemBoundry item = new ItemBoundry();
					item.setName("Book");
					item.setType("Book");
					item.setCreatedBy(new CreatedBy("2021b.katyaBoyko", "admin@admin.com"));
					item = insertVolumeInfoToItemAttr(item, fromAPI.getItems(), j);
					allBoundariesToPost.add(item);
				}
			}
			
			allBoundariesToPost // List<HelloBoundary>
				.stream() // Stream<HelloBoundary>
				.map(input -> restTemplate  // lambda expression
							.postForObject(url, input, ItemBoundry.class)) // Stream<HelloBoundary>
				.map(this::convertToJson)// use method reference and finally return Stream <String>
				.forEach(System.err::println); // use method reference to print data and finish handling the stream
		}
		
		private List<ItemBoundry> createSubItems(Map<String, Object> map, String[] subjects){
			List<ItemBoundry> itemsSub = new ArrayList<>();
			for(String val: subjects) {
				ItemBoundry item = new ItemBoundry();
				item.setName("Book");
				item.setType("Book");
				item.setCreatedBy(new CreatedBy("2021b.katyaBoyko", "admin@admin.com"));
				map.put("Subject", val);
				item.setItemAttributes(map);
				map.clear();
				itemsSub.add(item);
			}
			return itemsSub;
		}

		private String convertToJson (Object object) {
			try {
				return this.jackson
					.writeValueAsString(object);
			}catch (Exception e) {
				throw new RuntimeException(e);
			}
		}
		
	    private ItemBoundry insertVolumeInfoToItemAttr(ItemBoundry item, Items[] volumeInfo, int index) {
	    	ItemLogicImplementation itemlog = new ItemLogicImplementation(null, null);
	        Map<String, Object> itemAttr = itemlog.unmarshall(itemlog.marshall(volumeInfo[index]), Map.class);
	        item.setItemAttributes(itemAttr);
	        item.setName((String) itemAttr.get("Title"));
	        return item;
	    }
	    
	    
	    public Books searchBook(Map<String, Object> details) {
	        return BooksAPI.searchByTitle(details);//.block();
	    }
}

	

