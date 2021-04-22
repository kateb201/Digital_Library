package twins.controllers;
import twins.logic.*;
import org.springframework.beans.factory.annotation.Autowired;
//import org.json.JSONObject;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import twins.boundaries.OperationBoundary;

@RestController
public class OperationController {
	
	private OperationsService operServ;
	
	@Autowired
	public void setOperServ(OperationsService operServ) {
		this.operServ = operServ;
	}
	
	@RequestMapping(
            path="/twins/operations/async",
            method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
	public OperationBoundary  AsynchronousOperation(@RequestBody OperationBoundary input) {
		input = (OperationBoundary) operServ.invokeAsynchronous(input);
		return input;
	}
	

	@RequestMapping(
            path="/twins/operations",
            method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
	public Object  InvokeOperationOnItem(@RequestBody OperationBoundary input) {
		input = (OperationBoundary) operServ.invokeOperation(input);
		return input;
	}
}
