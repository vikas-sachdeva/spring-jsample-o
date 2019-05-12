package spring.jsample.beans;

import java.util.HashSet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import spring.jsample.db.DbManager;
import spring.jsample.model.App;
import spring.jsample.model.Vm;

@Component
public class CommandHandler {

	@Autowired
	private DbManager dbManager;

	public void execute(String... args) throws Exception {

		System.out.println("Insert applications and virtual machines -");
		insert();

		System.out.println("\nAll applications list -");
		dbManager.getAllApplications().forEach(System.out::println);

		App app1 = dbManager.getAllApplications().get(0);

		update(app1);

		System.out.println("\nUpated application -");

		dbManager.getApplicationById(app1.getId()).ifPresentOrElse(System.out::println,
				() -> System.out.println("Application with id " + app1.getId() + " not found."));

		System.out.println("\nDeleting application -");
		dbManager.deleteApplicationById(app1.getId());

		System.out.println("\nAll applications list -");
		dbManager.getAllApplications().forEach(System.out::println);

	}

	private void update(App app1) {
		app1.setRunning(false);
		app1.setVMs(new HashSet<Vm>());

		dbManager.updateApplication(app1);

	}

	private void insert() {
		App application1 = new App();
		application1.setName("App-1");
		application1.setRunning(false);

		App application2 = new App();
		application2.setName("App-2");
		application2.setRunning(false);

		Vm vm1 = new Vm();
		vm1.setHostName("virtualmachine1.in");

		vm1.getApps().add(application1);

		Vm vm2 = new Vm();
		vm2.setHostName("virtualmachine2.in");
		vm2.getApps().add(application1);
		vm2.getApps().add(application2);

		Vm vm3 = new Vm();
		vm3.setHostName("virtualmachine3.in");
		vm3.getApps().add(application1);
		vm3.getApps().add(application2);

		Vm vm4 = new Vm();
		vm4.setHostName("virtualmachine4.in");
		vm4.getApps().add(application2);

		application1.getVMs().add(vm1);
		application1.getVMs().add(vm2);
		application1.getVMs().add(vm3);

		application2.getVMs().add(vm2);
		application2.getVMs().add(vm3);
		application2.getVMs().add(vm4);

		dbManager.insertApplication(application1);
		dbManager.insertApplication(application2);

	}
}