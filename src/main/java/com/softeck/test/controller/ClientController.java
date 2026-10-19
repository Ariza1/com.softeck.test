package com.softeck.test.controller;

import java.util.List;

import com.softeck.test.model.Client;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.softeck.test.model.dtos.ClientDto;
import com.softeck.test.model.dtos.ServiceResponse;
import com.softeck.test.service.IClientService;

@RestController
@CrossOrigin(origins = { "http://localhost:8080" })
@RequestMapping("/api")
@Tag(name = "Client", description = "Client API")
public class ClientController {
	@Autowired
	private IClientService clientService;
	
	@CrossOrigin(origins = "http://localhost:4200")
	@Operation(summary = "Get all clients", description = "Returns a list of all clients")
    @GetMapping("/clients")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Clients retrieved successfully"),
			@ApiResponse(responseCode = "404", description = "Clients not found"),
	})
    public ResponseEntity<List<ClientDto>> getClients() {
    	return ResponseEntity.ok(clientService.findAll());
    	
    }

	@CrossOrigin(origins = "http://localhost:4200")
	@Operation(summary = "Save client", description = "Saves a new client")
    @PostMapping("/client")
	@Parameter(
			name = "client",
			description = "Client data to save",
			required = true
	)
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Client saved successfully"),
	})
    public ResponseEntity<ServiceResponse> saveClient(@RequestBody ClientDto client) {
    	ServiceResponse serviceReponse = new ServiceResponse();
		Client saved = clientService.save(client);
		if (saved != null) {
    		serviceReponse.setSuccess(true);
	    	serviceReponse.setMessage("Client saved with sucesss");
    	}
    	
    	return new ResponseEntity<>(serviceReponse, HttpStatus.OK);
    	
    }


	@CrossOrigin(origins = "http://localhost:4200")
	@Operation(summary = "delete client", description = "Deletes an existing client")
	@DeleteMapping("/client/{id}")
	@Parameter(
			name = "id",
			description = "client id to delete",
			required = true,
			example = "1"
	)
	@ApiResponses(value = {
			@ApiResponse(responseCode = "204", description = "Client deleted successfully"),
			@ApiResponse(responseCode = "404", description = "Client not found"),
	})
	public ResponseEntity<ServiceResponse> deleteClient(@PathVariable("id") Long id) {
		ServiceResponse serviceReponse = new ServiceResponse();
		int result = clientService.delete(id);
		if (result == 1) {
			serviceReponse.setSuccess(true);
			serviceReponse.setMessage("Client deleted with sucesss");
		}

		return new ResponseEntity<>(serviceReponse, HttpStatus.OK);
	}
}
