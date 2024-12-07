package com.jfsd_lab.exp3.p1;

import javax.persistence.Entity;

@Entity
public class DVD extends Item{

	private String director;
	private String regionCode;

	public String getDirector() {
		return director;
	}

	public void setDirector(String director) {
		this.director = director;
	}

	public String getRegionCode() {
		return regionCode;
	}

	public void setRegionCode(String regionCode) {
		this.regionCode = regionCode;
	}

}
