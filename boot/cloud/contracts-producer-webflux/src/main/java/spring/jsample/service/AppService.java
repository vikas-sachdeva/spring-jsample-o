package spring.jsample.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import spring.jsample.exceptions.ApplicationNotFoundException;
import spring.jsample.exceptions.ConcurrentModificationException;
import spring.jsample.model.Application;
import spring.jsample.repo.AppDao;

@Service
@Transactional
public class AppService {

	@Autowired
	private AppDao dao;

	public Flux<Application> getApps() {
		return dao.findAll();
	}

	public Mono<Application> addApp(Application app) {
		Mono<Application> savedApp = dao.insert(app);
		return savedApp;
	}

	public Mono<Void> deleteApp(String id) {

		return dao.findById(id)
				.switchIfEmpty(Mono.error(new ApplicationNotFoundException("Application with id " + id + " not found")))
				.flatMap(x -> dao.deleteById(id));

	}

	public Mono<Application> updateApp(Application app, String id) {

		return dao.findById(id).flatMap(row -> {

			if (row.getLastModifiedDate().getTime() != app.getLastModifiedDate().getTime()) {
				return Mono.error(
						new ConcurrentModificationException("Application with id " + id + " is already modified."));
			}

			row.setName(app.getName());
			row.setRunning(app.getRunning());

			return dao.save(row);

		}).switchIfEmpty(Mono.error((new ApplicationNotFoundException("Application with id " + id + " not found"))));

	}
}