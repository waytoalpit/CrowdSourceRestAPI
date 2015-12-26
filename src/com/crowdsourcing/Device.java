package com.crowdsourcing;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "Devices")
public class Device {

	private Location loct;
	private String last_scanned;
	private String ue_battery_power;
	private int ue_channel_scanned;
	private String mac;
	private double ue_channel_scanned_power;

	@XmlElement
	public String getLast_scanned() {
		return last_scanned;
	}

	public void setLast_scanned(String last_scanned) {
		this.last_scanned = last_scanned;
	}

	@XmlElement
	public String getUe_battery_power() {
		return ue_battery_power;
	}

	public void setUe_battery_power(String ue_battery_power) {
		this.ue_battery_power = ue_battery_power;
	}

	@XmlElement
	public int getUe_channel_scanned() {
		return ue_channel_scanned;
	}

	public void setUe_channel_scanned(int ue_channel_scanned) {
		this.ue_channel_scanned = ue_channel_scanned;
	}

	@XmlElement
	public String getMac() {
		return mac;
	}

	public void setMac(String mac) {
		this.mac = mac;
	}

	@XmlElement
	public double getUe_channel_scanned_power() {
		return ue_channel_scanned_power;
	}

	public void setUe_channel_scanned_power(double ue_channel_scanned_power) {
		this.ue_channel_scanned_power = ue_channel_scanned_power;
	}

	@XmlElement
	public Location getLoct() {
		return loct;
	}

	public void setLoct(Location loct) {
		this.loct = loct;
	}

}