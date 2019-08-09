package spring.jsample.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import spring.jsample.model.Application;
import spring.jsample.service.AppService;
import spring.jsample.util.AppConstants;

@RestController
public class AppController {

	@Autowired
	private AppService service;

	@GetMapping(value = { AppConstants.URI.GET_APPS }, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<?> getApps() {
		return new ResponseEntity<List<Application>>(service.getApps(), HttpStatus.OK);
	}

	/*
	 * Page number in below APIs related to pagination starts with 0 (zero) 
	 * 
	 */
	@GetMapping(value = { AppConstants.URI.GET_APPS_PAGE_WISE }, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<?> getAppsPageWise(@RequestParam(AppConstants.REQ_PARAM.PAGE_NUMBER) int pageNumber,
			@RequestParam(AppConstants.REQ_PARAM.PAGE_SIZE) int pageSize) {
		return new ResponseEntity<Page<Application>>(service.getAppsPageWise(pageNumber, pageSize), HttpStatus.OK);
	}

	@GetMapping(value = {
			AppConstants.URI.GET_APPS_HAVING_NAMES_PAGE_WISE }, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<?> getAppsHavingNamesPageWise(
			@RequestParam(AppConstants.REQ_PARAM.PAGE_NUMBER) int pageNumber,
			@RequestParam(AppConstants.REQ_PARAM.PAGE_SIZE) int pageSize,
			@RequestParam(AppConstants.REQ_PARAM.NAMES_LIST) List<String> names) {
		return new ResponseEntity<Page<Application>>(service.getAppsByNamesPageWise(names, pageNumber, pageSize),
				HttpStatus.OK);
	}

	@GetMapping(value = {
			AppConstants.URI.GET_RUNNING_APPS_HAVING_NAMES_PAGE_WISE }, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<?> getRunningAppsHavingNamesPageWise(
			@RequestParam(AppConstants.REQ_PARAM.PAGE_NUMBER) int pageNumber,
			@RequestParam(AppConstants.REQ_PARAM.PAGE_SIZE) int pageSize,
			@RequestParam(AppConstants.REQ_PARAM.NAMES_LIST) List<String> names) {
		return new ResponseEntity<Page<Application>>(service.getRunningAppsByNamesPageWise(names, pageNumber, pageSize),
				HttpStatus.OK);
	}

	@GetMapping(value = { AppConstants.URI.GET_RUNNING_APPS_PAGE_WISE }, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<?> getRunningAppsPageWise(@RequestParam(AppConstants.REQ_PARAM.PAGE_NUMBER) int pageNumber,
			@RequestParam(AppConstants.REQ_PARAM.PAGE_SIZE) int pageSize) {
		return new ResponseEntity<Page<Application>>(service.getRunningAppsPageWise(pageNumber, pageSize),
				HttpStatus.OK);
	}

	@PostMapping(value = { AppConstants.URI.ADD_APP }, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<?> addApp(@RequestBody Application app) {
		return new ResponseEntity<Application>(service.addApp(app), HttpStatus.OK);
	}

	@PutMapping(value = { AppConstants.URI.UPDATE_APP }, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<?> updateApp(@RequestBody Application app, @PathVariable String id) {

		return new ResponseEntity<Application>(service.updateApp(app, id), HttpStatus.OK);
	}

	@DeleteMapping(value = { AppConstants.URI.DELETE_APP }, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<?> deleteApp(@PathVariable String id) {
		service.deleteApp(id);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
}
