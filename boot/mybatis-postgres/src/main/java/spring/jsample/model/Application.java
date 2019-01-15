package spring.jsample.model;

public class Application {

	private int id;

	private String name;

	private Boolean running;

	public Application(int id, String name, Boolean running) {
		super();
		this.id = id;
		this.name = name;
		this.running = running;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Boolean getRunning() {
		return running;
	}

	public void setRunning(Boolean running) {
		this.running = running;
	}

}
