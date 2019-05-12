package spring.jsample.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "apps")
public class App extends ParentModel {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotBlank
	private String name;

	@NotNull
	private Boolean running;

	@ManyToMany(cascade = { CascadeType.PERSIST, CascadeType.MERGE }, fetch = FetchType.EAGER)
	@JoinTable(name = "app_vms", joinColumns = {
			@JoinColumn(name = "app_id", nullable = false, updatable = false) }, inverseJoinColumns = {
					@JoinColumn(name = "vm_id", nullable = false, updatable = false) })
	private Set<Vm> vMs = new HashSet<>();

	public App() {
		super();
	}

	public App(Long appId, @NotBlank String name, @NotNull Boolean running, Set<Vm> virturalMachines) {
		super();
		this.id = appId;
		this.name = name;
		this.running = running;
		this.vMs = virturalMachines;
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

	public Set<Vm> getVMs() {
		return vMs;
	}

	public void setVMs(Set<Vm> virturalMachines) {
		this.vMs = virturalMachines;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Application [id=").append(id).append(", name=").append(name).append(", running=")
				.append(running).append(", virturalMachines=").append(vMs).append(", createdAt=").append(createdAt)
				.append(", lastModifiedDate=").append(lastModifiedDate).append("]");
		return builder.toString();
	}

}
