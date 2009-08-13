/**
 * 
 */
package com.sounakray.fuelometer.model;

import java.util.Date;

/**
 * @author Sounak Ray
 *
 */
public final class FillUp {
	private Date date;
	private int odometer;
	private int volume;
	private int rate;
	/**
	 * @return the date
	 */
	public final Date getDate() {
		return date;
	}
	/**
	 * @param date the date to set
	 */
	public final void setDate(Date date) {
		this.date = date;
	}
	/**
	 * @return the odometer
	 */
	public final int getOdometer() {
		return odometer;
	}
	/**
	 * @param odometer the odometer to set
	 */
	public final void setOdometer(int odometer) {
		this.odometer = odometer;
	}
	/**
	 * @return the volume
	 */
	public final int getVolume() {
		return volume;
	}
	/**
	 * @param volume the volume to set
	 */
	public final void setVolume(int volume) {
		this.volume = volume;
	}
	/**
	 * @return the rate
	 */
	public final int getRate() {
		return rate;
	}
	/**
	 * @param rate the rate to set
	 */
	public final void setRate(int rate) {
		this.rate = rate;
	}
	
}
