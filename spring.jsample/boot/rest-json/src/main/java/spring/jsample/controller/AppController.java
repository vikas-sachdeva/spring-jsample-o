package spring.jsample.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import spring.jsample.model.Application;
import spring.jsample.service.AppService;

@RestController
public class AppController {

	@Autowired
	private AppService service;

	@GetMapping(value = { "/getApps", }, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<?> getApps() {
		return new ResponseEntity<List<Application>>(service.getApps(), HttpStatus.OK);
	}

	@PostMapping(value = { "/addApp", }, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<?> addApp(@RequestBody Application app) {
		return new ResponseEntity<Application>(service.addApp(app), HttpStatus.OK);
	}

	@PutMapping(value = { "/updateApp/{id}", }, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<?> updateApp(@RequestBody Application app, @PathVariable int id) {
		app.setId(id);
		return new ResponseEntity<Application>(service.updateApp(app), HttpStatus.OK);
	}

	@DeleteMapping(value = { "/deleteApp/{id}", }, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<?> deleteApp(@PathVariable int id) {
		service.deleteApp(id);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
}
