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

import twins.boundaries.UserBoundary;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)

public class UserTests {

	@Test
	public void testContext() {
		
	}
	
	private int port;
	private String url; // http://localhost:port/twins/users
	private RestTemplate restTemplate;
	
	@LocalServerPort 
	public void setPort(int port) {
		this.port = port;
	}
	
	@PostConstruct
	public void init() {
		this.url = "http://localhost:" + this.port + "/twins/users";
		System.err.println(this.url);
		this.restTemplate = new RestTemplate();
	}
	
	@AfterEach
	public void tearDown() {
		this.restTemplate
			.delete(this.url); 
//			.delete(this.url + "/{space}/{email}", "myspace", "admin@afeka.ac.il");
	}

	
	@Test @Disabled
	public void testDummy (){
		throw new RuntimeException("dummy failure");
	}
	
	@Test 
	public void testUpdateUserAndValidateTheDatabaseIsUpdated() throws Exception {
		// GIVEN the database contains a name
		UserBoundary existingUser = new UserBoundary();
		existingUser.setUsername("old username");
		
		existingUser = this.restTemplate
			.postForObject(this.url, existingUser, UserBoundary.class);
		
		UserBoundary update = new UserBoundary();
		update.setUsername("new username");
		this.restTemplate
			.put(this.url + "/{userSpace}/{userEmail}", update, existingUser.getUserId());
		
		// THEN the database name is updated
		assertThat(this.restTemplate
				.getForObject(this.url + "/{userSpace}/{userEmail}", UserBoundary.class, existingUser.getUserId())
				.getUsername())
			
				.isEqualTo(update.getUsername())
				.isNotEqualTo(existingUser.getUsername());
	}
	
	

	@Test 
	public void testCreatedUserAndValidateTheDatabaseIsUpdated() throws Exception {
		
		UserBoundary newUser = new UserBoundary();
		newUser.setUsername("new username to check ");
		newUser = this.restTemplate
			.postForObject(this.url ,newUser,  UserBoundary.class);
		
		
		// THEN the database name is updated
		assertThat(this.restTemplate
				.getForObject(this.url , UserBoundary.class, newUser.getUserId())
				.getUsername());
		
		assertThat(newUser)
				.isNotNull();
		assertThat(newUser.getUsername())
				.isEqualTo("new item created to check");
	}
	
}
