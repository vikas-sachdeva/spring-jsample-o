package spring.jsample.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

@Entity
@Table(name = "vms")
public class Vm extends ParentModel {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotBlank
	private String hostName;

	/*
	 * value of mappedBy attribute should match the name of the variable declared in
	 * App class
	 */
	@ManyToMany(cascade = { CascadeType.PERSIST, CascadeType.MERGE }, fetch = FetchType.EAGER, mappedBy = "vMs")
	private Set<App> apps = new HashSet<>();

	public Vm() {
		super();
	}

	public Vm(Long vmId, @NotBlank String hostName, Set<App> apps) {
		super();
		this.id = vmId;
		this.hostName = hostName;
		this.apps = apps;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long vmId) {
		this.id = vmId;
	}

	public String getHostName() {
		return hostName;
	}

	public void setHostName(String hostName) {
		this.hostName = hostName;
	}

	public Set<App> getApps() {
		return apps;
	}

	public void sets(Set<App> apps) {
		this.apps = apps;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("VirtualMachine [id=").append(id).append(", hostName=").append(hostName).append(", createdAt=")
				.append(createdAt).append(", lastModifiedDate=").append(lastModifiedDate).append("]");
		return builder.toString();
	}

}
