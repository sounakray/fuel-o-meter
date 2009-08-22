/**
 * FillUp.java
 * Fuel-O-Meter-02
 * @version %I%, %G%
 * Date Aug 15, 2009
 * © Sounak Ray
 * email: sounakray@gmail.com
 */
package sounakray.fuelometer.model;

import java.util.Date;
import net.jscience.util.MathFP;
import sounakray.fuelometer.manager.FuelOMeterManager;

/**
 * @author Sounak Ray
 */
public final class FillUp {
	private Date date;
	private int odometer;
	private int volume;
	private int unitPrice;

	private static final String DELIMITER = "|";

	/**
	 * Constructor Description: Constructs a new bean from the individual values provided. <br/>
	 * Intended use: Creating beans from the UI form.
	 * @param date
	 * @param odometer
	 * @param volume
	 * @param unitPrice
	 * @author Sounak Ray
	 * @since Aug 15, 2009
	 */
	public FillUp(final Date date, final String odometer, final String volume, final String unitPrice) {
		this.date = date;
		this.odometer = MathFP.toFP(odometer);
		this.volume = MathFP.toFP(volume);
		this.unitPrice = MathFP.toFP(unitPrice);
	}

	/**
	 * Constructor Description: Constructs a new bean out of the byte array provided.<br>
	 * Intended use: During re-instantiating objects from record store.
	 * @param recordByteArray
	 * @author Sounak Ray
	 * @since Aug 15, 2009
	 */
	public FillUp(final byte[] recordByteArray) {
		final String record = new String(recordByteArray).trim();
		final int[] delimiterIndex = new int[3];
		for(int i = 0; i < 3; i++){
			delimiterIndex[i] = record.indexOf(DELIMITER, 1 + (i == 0 ? 0 : delimiterIndex[i - 1]));
		}

		date = new Date(Long.parseLong(record.substring(0, delimiterIndex[0])));
		odometer = Integer.parseInt(record.substring(delimiterIndex[0] + 1, delimiterIndex[1]));
		volume = Integer.parseInt(record.substring(delimiterIndex[1] + 1, delimiterIndex[2]));
		unitPrice = Integer.parseInt(record.substring(delimiterIndex[2] + 1));
	}

	/**
	 * Method Description: Converts FillUp bean into a byte array.
	 * <p/>
	 * Intended use: The return value of this method is typically to be used for persisting to ResultStores.
	 * @return A byte array made of the content of the bean, separated by a delimiter.
	 * @author Sounak Ray
	 * @since Aug 15, 2009
	 */
	public byte[] toByteArray(){
		return (date.getTime() + DELIMITER + odometer + DELIMITER + volume + DELIMITER + unitPrice).getBytes();
	}

	public String toString(){
		return FuelOMeterManager.INSTANCE.getFormattedDate(date.getTime()) + " : " + MathFP.toString(odometer, 1)
				+ " [" + MathFP.toString(volume, 2) + " @ " + MathFP.toString(unitPrice, 2) + "]";
	}

	/**
	 * @return the date
	 */
	public Date getDate(){
		return this.date;
	}

	/**
	 * @param date the date to set
	 */
	public void setDate(final Date date){
		this.date = date;
	}

	/**
	 * @return the odometer
	 */
	public int getOdometer(){
		return this.odometer;
	}

	/**
	 * @param odometer the odometer to set
	 */
	public void setOdometer(final int odometer){
		this.odometer = odometer;
	}

	/**
	 * @return the volume
	 */
	public int getVolume(){
		return this.volume;
	}

	/**
	 * @param volume the volume to set
	 */
	public void setVolume(final int volume){
		this.volume = volume;
	}

	/**
	 * @return the unitPrice
	 */
	public int getUnitPrice(){
		return this.unitPrice;
	}

	/**
	 * @param unitPrice the unitPrice to set
	 */
	public void setUnitPrice(final int unitPrice){
		this.unitPrice = unitPrice;
	}
}
