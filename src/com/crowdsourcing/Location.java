package com.crowdsourcing;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;

public class Location {

	private String type;
	private List<Double> coordinates;

	@XmlElement
	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	@XmlElement
	public List<Double> getCoordinates() {
		return coordinates;
	}

	public void setCoordinates(List<Double> coordinates) {
		this.coordinates = coordinates;
	}

}
