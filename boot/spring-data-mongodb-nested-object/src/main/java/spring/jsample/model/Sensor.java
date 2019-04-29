package spring.jsample.model;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class Sensor {

	@NotBlank
	private String name;

	@NotNull
	private Type type;
	
	
	public Sensor(@NotBlank String name, @NotNull Type type) {
		this.name = name;
		this.type = type;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Type getType() {
		return type;
	}

	

	public void setType(Type type) {
		this.type = type;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Sensors [name=").append(name).append(", type=").append(type)
				.append("]");
		return builder.toString();
	}

	public enum Type {
		TEMPERATURE_SENSOR, PROXIMITY_SENSOR, ACCELEROMETER, INFRARED_SENSOR, PRESSURE_SENSOR, LIGHT_SENSOR,
		ULTRASONIC_SENSOR, SMOKE_GAS_AND_ALCOHOL_SENSOR, TOUCH_SENSOR, COLOR_SENSOR, HUMIDITY_SENSOR, TILT_SENSOR,
		FLOW_AND_LEVEL_SENSOR

	}
}
