package demo;

//import org.json.JSONObject;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class OperationController {

	
	@RequestMapping(
            path="/twins/operations/async",
            method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
	public OperationBoundary  AsynchronousOperation(@RequestBody OperationBoundary input) {
		
		return input;
	}
	
	@RequestMapping(
            path="/twins/operations",
            method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
	public Object  InvokeOperationOnItem(@RequestBody OperationBoundary input) {
		
		return input;
	}
}
