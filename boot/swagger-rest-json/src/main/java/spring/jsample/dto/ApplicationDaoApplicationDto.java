package spring.jsample.dto;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import spring.jsample.dao.model.ApplicationDao;
import spring.jsample.models.Application;
import spring.jsample.models.Application.StatusEnum;

@Component
public class ApplicationDaoApplicationDto {

	public List<Application> convertApplicationDaoToApplication(List<ApplicationDao> appDaos) {
		List<Application> apps = new ArrayList<>();
		for (ApplicationDao appDao : appDaos) {
			apps.add(convertApplicationDaoToApplication(appDao));
		}
		return apps;
	}

	public Application convertApplicationDaoToApplication(ApplicationDao appDao) {
		Application app = new Application();
		app.setId(appDao.getId());
		app.setName(appDao.getName());
		if (appDao.getStatus().equals("running")) {
			app.setStatus(StatusEnum.RUNNING);
		} else {
			app.setStatus(StatusEnum.STOPPED);
		}
		return app;
	}

	public List<ApplicationDao> convertApplicationToApplicationDao(List<Application> apps) {
		List<ApplicationDao> appDaos = new ArrayList<>();
		for (Application app : apps) {
			appDaos.add(convertApplicationToApplicationDao(app));
		}
		return appDaos;
	}

	public ApplicationDao convertApplicationToApplicationDao(Application app) {
		ApplicationDao appDao = new ApplicationDao(app.getId(), app.getName(), app.getStatus().toString());
		return appDao;
	}
}
