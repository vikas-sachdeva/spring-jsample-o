package spring.jsample.model;

import java.util.Date;

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

	public Application() {
		super();
	}

	public Application(String id, String name, boolean running) {
		this.id = id;
		this.name = name;
		this.running = running;
	}
	
	

	public Application(String id, String name,
			Boolean running,Date createdAt, Date lastModifiedDate) {
		super(createdAt, lastModifiedDate);
		this.id = id;
		this.name = name;
		this.running = running;
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

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Application other = (Application) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (running == null) {
			if (other.running != null)
				return false;
		} else if (!running.equals(other.running))
			return false;
		return true;
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
