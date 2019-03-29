package spring.jsample.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import spring.jsample.exceptions.ApplicationNotFoundException;
import spring.jsample.exceptions.ConcurrentModificationException;
import spring.jsample.model.Application;
import spring.jsample.repo.AppDao;

@Service
@Transactional
public class AppService {

	@Autowired
	private AppDao dao;

	public List<Application> getApps() {
		return dao.findAll();
	}

	public Application addApp(Application app) {
		Application savedApp = dao.save(app);
		return savedApp;
	}

	public void deleteApp(Long id) {
		dao.deleteById(id);
	}

	public Application updateApp(Application app, Long id) {
		Optional<Application> dbApp = dao.findById(id);

		return dbApp.map((row) -> {

			if (row.getLastModifiedDate().getTime() != app.getLastModifiedDate().getTime()) {
				throw new ConcurrentModificationException("Application with id " + id + " is already modified.");
			}

			row.setName(app.getName());
			row.setRunning(app.getRunning());

			return dao.save(row);
		}).orElseThrow(() -> new ApplicationNotFoundException("Application with id " + id + " not found"));

	}
}