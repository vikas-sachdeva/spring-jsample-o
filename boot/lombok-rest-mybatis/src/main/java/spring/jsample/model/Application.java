package spring.jsample.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Application {

	private int id;

	private String name;

	private Boolean running;

}
