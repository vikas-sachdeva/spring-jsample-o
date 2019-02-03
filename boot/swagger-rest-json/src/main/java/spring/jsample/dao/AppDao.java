package spring.jsample.dao;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.stereotype.Repository;

import spring.jsample.dao.model.ApplicationDao;

@Repository
public class AppDao {

	private List<ApplicationDao> applicationList;

	@PostConstruct
	public void init() {
		ApplicationDao app1 = new ApplicationDao(1, "Application-1", "running");
		ApplicationDao app2 = new ApplicationDao(2, "Application-2", "stopped");
		ApplicationDao app3 = new ApplicationDao(3, "Application-3", "running");

		applicationList = new ArrayList<>(Arrays.asList(app1, app2, app3));

	}

	public List<ApplicationDao> getApps() {
		return applicationList;
	}

	public ApplicationDao getAppById(int id) {
		return applicationList.stream().filter(x -> x.getId() == id).findFirst().orElseThrow();
	}

	public void deleteApp(int id) {
		applicationList.removeIf(x -> x.getId() == id);
	}

	public int addApp(ApplicationDao app) {

		int maxId = applicationList.stream().max((o1, o2) -> {
			return o1.getId() > o2.getId() ? 1 : -1;
		}).get().getId();

		app.setId(maxId + 1);
		applicationList.add(app);
		return app.getId();
	}

	public void updateApp(ApplicationDao app) {
		applicationList.stream().filter(x -> x.getId() == app.getId()).findFirst().ifPresent(x -> {
			x.setName(app.getName());
			x.setStatus(app.getStatus());
		});
	}
}