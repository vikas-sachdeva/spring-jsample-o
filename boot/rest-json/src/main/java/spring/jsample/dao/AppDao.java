package spring.jsample.dao;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

import javax.annotation.PostConstruct;

import org.springframework.stereotype.Repository;

import spring.jsample.model.Application;

@Repository
public class AppDao {

	private List<Application> applicationList;

	@PostConstruct
	private void init() {
		Application app1 = new Application(1, "Application-1", "running");
		Application app2 = new Application(2, "Application-2", "stopped");
		Application app3 = new Application(3, "Application-3", "running");

		applicationList = new ArrayList<>(Arrays.asList(app1, app2, app3));

	}

	public List<Application> getApps() {
		return applicationList;
	}

	public Application getAppById(int id) {
		return applicationList.stream().filter(x -> x.getId() == id).findFirst().get();
	}

	public void deleteApp(int id) {
		applicationList.removeIf(x -> x.getId() == id);
	}

	public int addApp(Application app) {
		Optional<Application> maxId = applicationList.stream().max(new Comparator<Application>() {
			@Override
			public int compare(Application o1, Application o2) {
				if (o1.getId() > o2.getId())
					return 1;
				else
					return -1;
			}
		});
		app.setId(maxId.get().getId() + 1);
		applicationList.add(app);
		return app.getId();
	}

	public void updateApp(Application app) {
		applicationList.stream().filter(x -> x.getId() == app.getId()).findFirst().ifPresent(x -> {
			x.setName(app.getName());
			x.setStatus(app.getStatus());
		});
	}
}