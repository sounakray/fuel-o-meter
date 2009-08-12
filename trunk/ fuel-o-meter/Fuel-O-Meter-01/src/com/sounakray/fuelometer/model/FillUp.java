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
	private float odometer;
	private float volume;
	private float rate;
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
	public final float getOdometer() {
		return odometer;
	}
	/**
	 * @param odometer the odometer to set
	 */
	public final void setOdometer(float odometer) {
		this.odometer = odometer;
	}
	/**
	 * @return the volume
	 */
	public final float getVolume() {
		return volume;
	}
	/**
	 * @param volume the volume to set
	 */
	public final void setVolume(float volume) {
		this.volume = volume;
	}
	/**
	 * @return the rate
	 */
	public final float getRate() {
		return rate;
	}
	/**
	 * @param rate the rate to set
	 */
	public final void setRate(float rate) {
		this.rate = rate;
	}
	
}
