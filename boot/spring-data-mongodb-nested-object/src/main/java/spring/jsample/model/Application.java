package spring.jsample.model;

import java.util.List;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(value = "application")
public class Application extends ParentModel {

	@Id
	private String id;

	@NotBlank
	private String name;

	@NotNull
	private Boolean running;

	@NotNull
	private List<Sensor> sensors;

	public Application() {

	}

	public Application(String id, @NotBlank String name, @NotNull Boolean running, @NotNull List<Sensor> sensors) {
		this.id = id;
		this.name = name;
		this.running = running;
		this.sensors = sensors;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
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

	public List<Sensor> getSensors() {
		return sensors;
	}

	public void setSensors(List<Sensor> sensors) {
		this.sensors = sensors;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Application [id=").append(id).append(", name=").append(name).append(", running=")
				.append(running).append(", sensors=").append(sensors).append("]");
		return builder.toString();
	}
}
