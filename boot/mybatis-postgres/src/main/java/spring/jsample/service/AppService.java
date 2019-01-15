package spring.jsample.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import spring.jsample.dao.AppDao;
import spring.jsample.model.Application;

@Service
@Transactional
public class AppService {

	@Autowired
	private AppDao dao;

	public List<Application> getApps() {
		return dao.getApps();
	}

	public Application addApp(Application app) {
		int id = dao.addApp(app);
		return dao.getAppById(id);
	}

	public void deleteApp(int id) {
		dao.deleteApp(id);
	}

	public Application updateApp(Application app) {
		dao.updateApp(app);
		return dao.getAppById(app.getId());
	}
}