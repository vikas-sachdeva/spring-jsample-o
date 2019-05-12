package spring.jsample.db;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import spring.jsample.exceptions.ConcurrentModificationException;
import spring.jsample.model.App;
import spring.jsample.repo.ApplicationDao;

@Component
@Transactional
public class DbManager {

	@Autowired
	private ApplicationDao appDao;

	public List<App> getAllApplications() {
		return appDao.findAll();
	}

	public Optional<App> getApplicationById(Long id) {
		return appDao.findById(id);
	}

	public App insertApplication(App app) {
		return appDao.save(app);

	}

	public App updateApplication(App app) {
		appDao.findById(app.getId()).map(row -> {

			if (row.getLastModifiedDate().getTime() != app.getLastModifiedDate().getTime()) {
				throw new ConcurrentModificationException(
						"Application with id " + app.getId() + " is already modified.");
			}

			row.setName(app.getName());
			row.setRunning(app.getRunning());
			row.setVMs(app.getVMs());

			return appDao.save(row);

		}).orElseThrow(() -> new ConcurrentModificationException("Application is already modified."));
		return null;
	}

	public void deleteApplicationById(Long id) {
		appDao.deleteById(id);
	}
}
