package com.jfsd_lab.exp3.p2;

import javax.persistence.Entity;

@Entity
public class Car extends Vehicle {

	private int seats;
	private String fuelType;

	public int getSeats() {
		return seats;
	}

	public void setSeats(int seats) {
		this.seats = seats;
	}

	public String getFuelType() {
		return fuelType;
	}

	public void setFuelType(String fuelType) {
		this.fuelType = fuelType;
	}

}
