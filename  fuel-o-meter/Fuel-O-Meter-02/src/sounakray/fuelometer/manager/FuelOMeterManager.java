/**
 * FuelOMeterManager.java
 * Fuel-O-Meter-02
 * @version %I%, %G%
 * Date Aug 15, 2009
 * © Sounak Ray
 * email: sounakray@gmail.com
 */
package sounakray.fuelometer.manager;

import java.util.Calendar;
import java.util.Date;
import javax.microedition.lcdui.Command;
import net.jscience.util.MathFP;
import sounakray.fuelometer.dao1.CachedRecordStoreDAOImpl;
import sounakray.fuelometer.dao1.FuelOMeterDAO;
import sounakray.fuelometer.model.FillUp;
import sounakray.fuelometer.model.StatisticsData;

/**
 * @author Sounak Ray
 */
public final class FuelOMeterManager {
	public static final FuelOMeterManager INSTANCE = new FuelOMeterManager();
	public static final Command CMD_MAIN_MENU = new Command("Back", "Main Menu", Command.BACK, 0);
	public static final long DAY_DURATION = 86400000;
	private static final FuelOMeterDAO DAO = new CachedRecordStoreDAOImpl();

	/**
	 * Constructor Description: Private constructor to enforce singleton behaviour.
	 * @author Sounak Ray
	 * @since Aug 22, 2009
	 */
	private FuelOMeterManager() {}

	/**
	 * Method Description:
	 * @param date
	 * @param odometer
	 * @param volume
	 * @param unitPrice
	 * @return
	 * @author Sounak Ray
	 * @since Aug 18, 2009
	 */
	public boolean saveRecord(final Date date, final String odometer, final String volume, final String unitPrice){
		boolean isSuccessful = true;
		try{
			DAO.saveFillUp(new FillUp(date, odometer, volume, unitPrice));
		}catch(final Exception e){
			isSuccessful = false;
		}
		return isSuccessful;
	}

	/**
	 * Method Description:
	 * @return
	 * @author Sounak Ray
	 * @since Aug 18, 2009
	 */
	public FillUp[] getAllRecords(){
		return DAO.getAllFillUpList();
		// final FillUp[] records = DAO.getAllFillUpList();
		// return records;
	}

	/**
	 * Method Description: Generates a statistical report out of the available data.
	 * @return
	 * @author Sounak Ray
	 * @since Aug 18, 2009
	 */
	public StatisticsData getStatistics(){
		StatisticsData stats = null;
		final FillUp[] records = getAllRecords();
		int totalFuel = 0, totalDistance, totalCost = 0;
		final int numRecs = records.length;
		if(numRecs > 1){
			FillUp record = records[0];
			final FillUp firstRec = records[0], lastRec = records[numRecs - 1], scndLastRec = records[numRecs - 2];
			totalDistance = MathFP.sub(lastRec.getOdometer(), firstRec.getOdometer());

			for(int i = 0; i < numRecs - 1; record = records[++i]){
				totalFuel = MathFP.add(totalFuel, record.getVolume());
				totalCost = MathFP.add(totalCost, MathFP.mul(record.getVolume(), record.getUnitPrice()));
			}
			final int avgMileage = MathFP.div(totalDistance, totalFuel);

			stats = new StatisticsData();
			stats.setStartDate(getFormattedDate(firstRec.getDate().getTime()));
			stats.setEndDate(getFormattedDate(lastRec.getDate().getTime()));
			stats.setTotalDays(String.valueOf((lastRec.getDate().getTime() - firstRec.getDate().getTime())
					/ DAY_DURATION));
			stats.setAverageMileage(MathFP.toString(avgMileage, 2));
			stats.setAvgCostPerDist(MathFP.toString(MathFP.div(totalCost, totalDistance), 2));
			stats.setFuelPer100Dist(MathFP.toString(MathFP.div(MathFP.toFP("100"), avgMileage), 2));
			stats.setLastMileage(MathFP.toString(MathFP.div(MathFP
				.sub(lastRec.getOdometer(), scndLastRec.getOdometer()), scndLastRec.getVolume()), 2));
			stats.setTotalDistance(MathFP.toString(totalDistance, 1));
			stats.setTotalFuelConsumed(MathFP.toString(totalFuel, 2));
			stats.setTotalFuel(MathFP.toString(MathFP.add(totalFuel, lastRec.getVolume()), 2));
			stats.setTotalMoneyCnsmd(MathFP.toString(totalCost, 2));
			stats.setTotalMoney(MathFP.toString(MathFP.add(totalCost, MathFP.mul(lastRec.getVolume(), lastRec
				.getUnitPrice())), 2));
		}
		return stats;
	}

	/**
	 * Method Description: Returns an array of integers representing the previous mileages. This method requires at
	 * least three fill up records to calculate the history. If number of records is insufficient, null is returned. The
	 * values are rounded off to integers. <br>
	 * <b>Note:</b>The element at index position 0 represents the weighted-mean (average) mileage.
	 * @return list of integers representing the mileage history.
	 * @author Sounak Ray
	 * @since Aug 19, 2009
	 */
	public int[] getMileageHistory(){
		int[] mileageHistory = null;
		final FillUp[] records = getAllRecords();
		final int numRecs = records.length;
		int totalCost = 0, totalFuel = 0;
		if(numRecs > 2){
			FillUp record, prevRec;
			mileageHistory = new int[numRecs];
			for(int i = 1; i < numRecs; i++){
				prevRec = records[i - 1];
				record = records[i];

				totalFuel = MathFP.add(totalFuel, prevRec.getVolume());
				totalCost = MathFP.add(totalCost, MathFP.mul(prevRec.getVolume(), prevRec.getUnitPrice()));
				mileageHistory[i] =
					MathFP.toInt(MathFP.div(MathFP.sub(record.getOdometer(), prevRec.getOdometer()), prevRec
						.getVolume()));
			}
			mileageHistory[0] =
				MathFP.toInt(MathFP.div(MathFP.sub(records[numRecs - 1].getOdometer(), records[0].getOdometer()),
					totalFuel));
		}
		return mileageHistory;
	}

	/**
	 * Method Description: This method formats the given time from epoch, in 'MM/DD/YYYY' format;
	 * @param date the milliseconds since January 1, 1970, 00:00:00 GMT.
	 * @return The same date in 'MM/DD/YYYY' format
	 * @author Sounak Ray
	 * @since Aug 16, 2009
	 */
	public String getFormattedDate(final long date){
		final Calendar calendar = Calendar.getInstance();
		calendar.setTime(new Date(date));
		return (calendar.get(Calendar.MONTH) + 1) + "/" + calendar.get(Calendar.DATE) + "/"
				+ calendar.get(Calendar.YEAR);
	}

	/**
	 * Method Description: Based on current statistics, calculates the estimated odometer reading, when the vehicle will
	 * run out of fuel.
	 * @return The estimated odometer reading.
	 * @author Sounak Ray
	 * @since Aug 22, 2009
	 */
	public String getNextFillUpEstimate(){
		String nextFillup = null;
		final FillUp[] records = getAllRecords();
		final int numRecs = records.length;
		if(numRecs > 2){
			final FillUp firstRec = records[0], lastRec = records[numRecs - 1];
			int totalFuel = 0;
			for(int i = 0; i < records.length - 1; i++){
				totalFuel = MathFP.add(totalFuel, records[i].getVolume());
			}
			// int totalDays = (int) ((lastRec.getDate().getTime() - firstRec.getDate().getTime()) / DAY_DURATION);
			// MathFP.mul(MathFP.div(totalDays, totalFuel), lastRec.getVolume()); // more days
			nextFillup = // Next Fill up Odo-reading.
				MathFP.toString(MathFP.add(lastRec.getOdometer(), MathFP.mul(lastRec.getVolume(), MathFP.div(MathFP
					.sub(records[records.length - 1].getOdometer(), firstRec.getOdometer()), totalFuel))), 1);
		}
		return nextFillup;
	}
}
