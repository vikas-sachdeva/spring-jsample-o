package spring.jsample.dao.model;

import java.util.Objects;

public class ApplicationDao {

	private int id;

	private String name;

	private String status;

	public ApplicationDao(int id, String name, String status) {
		super();
		this.id = id;
		this.name = name;
		this.status = status;
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

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@Override
	public boolean equals(java.lang.Object o) {
		if (this == o) {
			return true;
		}
		if (o == null || getClass() != o.getClass()) {
			return false;
		}
		ApplicationDao application = (ApplicationDao) o;
		return Objects.equals(this.id, application.id) && Objects.equals(this.name, application.name)
				&& Objects.equals(this.status, application.status);
	}
}
