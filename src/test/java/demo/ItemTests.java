package demo;


import static org.assertj.core.api.Assertions.assertThat;
import javax.annotation.PostConstruct;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.web.client.RestTemplate;

import twins.boundaries.ItemBoundry;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)


public class ItemTests {
	
	@Test
	public void testContext() {
		
	}
	
	private int port;
	private String url; // http://localhost:port/twins/items
	private RestTemplate restTemplate;
	
	@LocalServerPort // inject port number to test case
	public void setPort(int port) {
		this.port = port;
	}
	
	@PostConstruct
	public void init() {
		this.url = "http://localhost:" + this.port + "/twins/items";
		System.err.println(this.url);
		this.restTemplate = new RestTemplate();
	}
	
	@AfterEach
	public void tearDown() {
		/// clean database
		this.restTemplate
			.delete(this.url); 
//			.delete(this.url + "/{space}/{email}", "myspace", "admin@afeka.ac.il");
	}

	
	@Test @Disabled
	public void testDummy (){
		throw new RuntimeException("dummy failure");
	}
	
	@Test 
	public void testUpdateItemAndValidateTheDatabaseIsUpdated() throws Exception{
		// GIVEN the database contains a name
		ItemBoundry existingItem = new ItemBoundry();
		existingItem.setName("old name");
		
		existingItem = this.restTemplate
			.postForObject(this.url, existingItem, ItemBoundry.class);
		
		ItemBoundry update = new ItemBoundry();
		update.setName("new name");
		this.restTemplate
			.put(this.url + "/{userSpace}/{userEmail}/{itemSpace}/{itemId}", update, existingItem.getItemId());
		
		// THEN the database name is updated
		assertThat(this.restTemplate
				.getForObject(this.url + "/{userSpace}/{userEmail}/{itemSpace}/{itemId}", ItemBoundry.class, existingItem.getItemId())
				.getName())
			
				.isEqualTo(update.getName())
				.isNotEqualTo(existingItem.getName());
	}
	
	

	@Test 
	public void testCreatedItemAndValidateTheDatabaseIsUpdated() throws Exception{
		
		ItemBoundry newItem = new ItemBoundry();
		newItem.setName("new item created to check");
		newItem = this.restTemplate
			.postForObject(this.url + "/{userSpace}/{userEmail}",newItem,  ItemBoundry.class);
		
		
		// THEN the database name is updated
		assertThat(this.restTemplate
				.getForObject(this.url + "/{userSpace}/{userEmail}/{itemSpace}/{itemId}", ItemBoundry.class, newItem.getItemId())
				.getName());
		
		assertThat(newItem)
				.isNotNull();
		assertThat(newItem.getName())
				.isEqualTo("new item created to check");
	}
	
}
