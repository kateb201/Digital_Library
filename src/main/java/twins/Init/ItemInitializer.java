package twins.Init;

import twins.BooksAPI;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.List;
import java.util.UUID;

import javax.annotation.PostConstruct;

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
import twins.boundaries.ItemId;

@Component
@Profile("initItems")
public class ItemInitializer implements CommandLineRunner {
	private RestTemplate restTemplate;
	private String url;
	private int port;
	private ObjectMapper jackson;

	@Value("${server.port:8080}")
	public void setPort(int port) {
		this.port = port;
	}

	@PostConstruct
	public void init() {
		this.restTemplate = new RestTemplate();
		this.url = "http://localhost:" + port + "/twins/items/2021b.katya.boyko/manager@manager.ac.il";
		this.jackson = new ObjectMapper();
	}

	// this method is invoked by Spring once all spring beans are initialized
	@Override
	public void run(String... args) throws Exception {
		List<ItemBoundry> allBoundariesToPost = new ArrayList<>();
		// first message
		Map<String, Object> map = new HashMap<String, Object>();// create new map every iteration
		String[] subjects = { "horror", "fantasy", "fiction", "drama", "computers" };
		List<ItemBoundry> itemsSub = createSubItems(map, subjects);
		for (int i = 0; i < itemsSub.size(); i++) {
			Books fromAPI = searchBook(itemsSub.get(i).getItemAttributes());
			for (int j = 0; j < Integer.parseInt(BooksAPI.MAX_RESULTS); j++) {
				ItemBoundry item = new ItemBoundry(new ItemId("2021b.katya", UUID.randomUUID().toString()), "Book",
						true, new CreatedBy("2021b.katya.boyko", "manager@manager.ac.il"));
				item = insertVolumeInfoToItemAttr(item, fromAPI.getItems()[j], subjects[i]);
				allBoundariesToPost.add(item);
			}
		}

		allBoundariesToPost // List<ItemBoundary>
				.stream() // Stream<ItemBoundary>
				.map(input -> restTemplate // lambda expression
						.postForObject(url, input, ItemBoundry.class)) // Stream<ItemBoundary>
				.map(this::convertToJson)// use method reference and finally return Stream <String>
				.forEach(System.err::println); // use method reference to print data and finish handling the stream
	}

	private List<ItemBoundry> createSubItems(Map<String, Object> map, String[] subjects) {
		List<ItemBoundry> itemsSub = new ArrayList<>();
		for (String val : subjects) {
			ItemBoundry item = new ItemBoundry(new ItemId("2021b.katya", UUID.randomUUID().toString()), "Book", true,
					new CreatedBy("2021b.katya.boyko", "manager@manager.ac.il"));
			map.put("Subject", val);
			item.setItemAttributes(new HashMap<>(map));
			map.clear(); // create new map every iteration
			itemsSub.add(item);
		}
		return itemsSub;
	}

	private String convertToJson(Object object) {
		try {
			return this.jackson.writeValueAsString(object);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	private ItemBoundry insertVolumeInfoToItemAttr(ItemBoundry item, Items volumeInfo, String subject) {
		Map<String, Object> itemAttr = this.jackson.convertValue(volumeInfo.getVolumeInfo(), Map.class);
		itemAttr.put("subject", subject);
		item.setItemAttributes(itemAttr);
		item.setName((String) itemAttr.get("title"));
		return item;
	}

	public Books searchBook(Map<String, Object> details) {
		return BooksAPI.searchByTitle(details);// .block();
	}

	public String marshall(Object value) {
		try {
			return this.jackson.writeValueAsString(value);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	public <T> T unmarshall(String json, Class<T> requiredType) {
		try {
			return this.jackson.readValue(json, requiredType);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
}
