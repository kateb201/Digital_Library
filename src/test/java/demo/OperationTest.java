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

import twins.boundaries.OperationBoundary;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)

public class OperationTest {

	@Test
	public void testContext() {
		
	}
	
	private int port;
	private String url; // http://localhost:port/twins/operations
	private RestTemplate restTemplate;
	
	@LocalServerPort 
	public void setPort(int port) {
		this.port = port;
	}
	
	@PostConstruct
	public void init() {
		this.url = "http://localhost:" + this.port + "/twins/operations";
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
	public void testOperation() throws Exception {
		OperationBoundary test = new OperationBoundary();

		OperationBoundary operation = new OperationBoundary();
		assertThat(this.restTemplate
				.getForObject(this.url + "/async", OperationBoundary.class, operation));
		assertThat(operation)
				.isNotNull()
				.isEqualTo(test);
				
	}
	

}
