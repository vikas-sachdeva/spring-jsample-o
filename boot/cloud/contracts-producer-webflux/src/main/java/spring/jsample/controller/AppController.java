package spring.jsample.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import spring.jsample.model.Application;
import spring.jsample.service.AppService;
import spring.jsample.util.AppConstants;

@RestController
public class AppController {

	@Autowired
	private AppService service;

	@GetMapping(value = { AppConstants.URI.GET_APPS })
	@ResponseBody
	public ResponseEntity<Flux<?>> getApps() {
		return new ResponseEntity<Flux<?>>(service.getApps(), HttpStatus.OK);
	}

	@PostMapping(value = { AppConstants.URI.ADD_APP })
	@ResponseBody
	public ResponseEntity<Mono<?>> addApp(@RequestBody Application app) {
		return new ResponseEntity<Mono<?>>(service.addApp(app), HttpStatus.OK);
	}

	@PutMapping(value = { AppConstants.URI.UPDATE_APP })
	@ResponseBody
	public ResponseEntity<Mono<?>> updateApp(@RequestBody Application app, @PathVariable String id) {
		return new ResponseEntity<Mono<?>>(service.updateApp(app, id), HttpStatus.OK);
	}

	@DeleteMapping(value = { AppConstants.URI.DELETE_APP })
	@ResponseBody
	public ResponseEntity<Mono<?>> deleteApp(@PathVariable String id) {
		return new ResponseEntity<Mono<?>>(service.deleteApp(id), HttpStatus.NO_CONTENT);
	}
}