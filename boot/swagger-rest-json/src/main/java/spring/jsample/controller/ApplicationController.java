package spring.jsample.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import spring.jsample.api.AppApi;
import spring.jsample.models.Application;
import spring.jsample.service.AppService;

@RestController
public class ApplicationController implements AppApi {

	@Autowired
	private AppService service;

	@Override
	public ResponseEntity<Application> addAdd(@Valid Application application) {
		return new ResponseEntity<Application>(service.addApp(application), HttpStatus.OK);
	}

	@Override
	public ResponseEntity<String> deleteApp(Integer id) {
		service.deleteApp(id);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}

	@Override
	public ResponseEntity<List<Application>> getApps() {
		return new ResponseEntity<List<Application>>(service.getApps(), HttpStatus.OK);
	}

	@Override
	public ResponseEntity<Application> updateApp(@Valid Application application, Integer id) {
		application.setId(id);
		return new ResponseEntity<Application>(service.updateApp(application), HttpStatus.OK);
	}
}
