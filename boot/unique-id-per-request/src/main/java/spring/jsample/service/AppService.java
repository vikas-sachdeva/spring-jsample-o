package spring.jsample.service;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import spring.jsample.dao.AppDao;
import spring.jsample.model.Application;

@Service
public class AppService {
	
	private static final Logger LOGGER = LogManager.getLogger(AppService.class);

	@Autowired
	private AppDao dao;

	public List<Application> getApps() {
		LOGGER.info("getApps() method called");
		return dao.getApps();
	}

	public Application addApp(Application app) {
		LOGGER.info("addApp() method called");
		int id = dao.addApp(app);
		return dao.getAppById(id);
	}

	public void deleteApp(int id) {
		LOGGER.info("deleteApp() method called");
		dao.deleteApp(id);
	}

	public Application updateApp(Application app) {
		LOGGER.info("updateApp() method called");
		dao.updateApp(app);
		return dao.getAppById(app.getId());
	}
}