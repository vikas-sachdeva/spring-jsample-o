package spring.jsample.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import spring.jsample.dao.AppDao;
import spring.jsample.dao.model.ApplicationDao;
import spring.jsample.dto.ApplicationDaoApplicationDto;
import spring.jsample.models.Application;

@Service
public class AppService {

	@Autowired
	private AppDao dao;

	@Autowired
	private ApplicationDaoApplicationDto dto;

	public List<Application> getApps() {
		List<ApplicationDao> appDaos = dao.getApps();
		return dto.convertApplicationDaoToApplication(appDaos);
	}

	public Application addApp(Application app) {
		ApplicationDao appDao = dto.convertApplicationToApplicationDao(app);
		int id = dao.addApp(appDao);
		return dto.convertApplicationDaoToApplication(dao.getAppById(id));
	}

	public void deleteApp(int id) {
		dao.deleteApp(id);
	}

	public Application updateApp(Application app) {
		ApplicationDao appDao = dto.convertApplicationToApplicationDao(app);
		dao.updateApp(appDao);
		return dto.convertApplicationDaoToApplication(dao.getAppById(app.getId()));
	}
}