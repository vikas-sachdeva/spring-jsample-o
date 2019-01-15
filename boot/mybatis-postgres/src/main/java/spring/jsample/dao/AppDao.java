package spring.jsample.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import spring.jsample.exceptions.ApplicationInsertionException;
import spring.jsample.exceptions.ApplicationNotFoundException;
import spring.jsample.mapper.AppMapper;
import spring.jsample.model.Application;

@Repository
public class AppDao {

	@Autowired
	private AppMapper appMapper;

	public List<Application> getApps() {
		return appMapper.getApps();
	}

	public Application getAppById(int id) {
		return appMapper.getAppById(id);
	}

	public void deleteApp(int id) {
		int count = appMapper.deleteApp(id);
		if (count != 1) {
			throw new ApplicationNotFoundException("Application with id " + id + " not found in the database.");
		}
	}

	public int addApp(Application app) {
		if (appMapper.insertApp(app) != 1) {
			throw new ApplicationInsertionException("Error occurred while inserting app " + app);
		}
		return app.getId();
	}

	public void updateApp(Application app) {
		int count = appMapper.updateApp(app);
		if (count != 1) {
			throw new ApplicationNotFoundException(
					"Application with id " + app.getId() + " not found in the database.");
		}
	}
}