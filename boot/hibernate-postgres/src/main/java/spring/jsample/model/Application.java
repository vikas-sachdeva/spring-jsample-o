package spring.jsample.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "application")
public class Application extends ParentModel {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotBlank
	private String name;

	@NotNull
	private Boolean running;

	public Application() {
		super();
	}

	public Application(Long id, String name, boolean running) {
		this.id = id;
		this.name = name;
		this.running = running;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
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

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Application [id=").append(id).append(", name=").append(name).append(", running=")
				.append(running).append(", createdAt=").append(createdAt).append(", lastModifiedDate=")
				.append(lastModifiedDate).append("]");
		return builder.toString();
	}

}
