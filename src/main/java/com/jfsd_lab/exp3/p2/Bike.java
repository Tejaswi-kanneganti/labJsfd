package com.jfsd_lab.exp3.p2;

import javax.persistence.Entity;

@Entity
public class Bike extends Vehicle {

	private String type;
	private int engineCapacity;

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public int getEngineCapacity() {
		return engineCapacity;
	}

	public void setEngineCapacity(int engineCapacity) {
		this.engineCapacity = engineCapacity;
	}

}
