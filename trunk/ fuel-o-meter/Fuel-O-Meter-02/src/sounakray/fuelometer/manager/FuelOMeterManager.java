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
import net.jscience.util.MathFP;
import sounakray.fuelometer.DAO.CachedRecordStoreDAOImpl;
import sounakray.fuelometer.DAO.FuelOMeterDAO;
import sounakray.fuelometer.model.FillUp;
import sounakray.fuelometer.model.StatisticsData;

/**
 * @author Sounak Ray
 */
public final class FuelOMeterManager {
	final FuelOMeterDAO dao = new CachedRecordStoreDAOImpl();

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
			dao.saveFillUp(new FillUp(date, odometer, volume, unitPrice));
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
		final FillUp[] records = dao.getAllFillUpList();
		// TODO: Move the sorting logic to here from the DAO layer.
		return records;
	}

	/**
	 * Method Description: Generates a statistical report out of the available data.
	 * @return
	 * @author Sounak Ray
	 * @since Aug 18, 2009
	 */
	public StatisticsData getStatistics(){
		final StatisticsData stats;
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
			stats.setAverageMileage(MathFP.toString(avgMileage, 2));
			stats.setAverageCostPerDist(MathFP.toString(MathFP.div(totalCost, totalDistance), 2));
			stats.setFuelPer100Dist(MathFP.toString(MathFP.div(MathFP.toFP("100"), avgMileage), 2));
			stats.setLastMileage(MathFP.toString(MathFP.div(MathFP
				.sub(lastRec.getOdometer(), scndLastRec.getOdometer()), scndLastRec.getVolume()), 2));
			// stats.setTotalDays(totalDays);
			stats.setTotalDistance(MathFP.toString(totalDistance, 1));
			stats.setTotalFuelConsumed(MathFP.toString(totalFuel, 2));
			stats.setTotalFuel(MathFP.toString(MathFP.add(totalFuel, lastRec.getVolume()), 2));
			stats.setTotalMoneyConsumed(MathFP.toString(totalCost, 2));
			stats.setTotalMoney(MathFP.toString(MathFP.add(totalCost, MathFP.mul(lastRec.getVolume(), lastRec
				.getUnitPrice())), 2));
		}else{
			stats = null;
		}
		return stats;
	}

	/**
	 * Method Description: This method formats the given time from epoch, in 'MM/DD/YYYY' format;
	 * @param date the milliseconds since January 1, 1970, 00:00:00 GMT.
	 * @return The same date in 'MM/DD/YYYY' format
	 * @author Sounak Ray
	 * @since Aug 16, 2009
	 */
	public String getFormattedDate(final long date){
		final Calendar c = Calendar.getInstance();
		c.setTime(new Date(date));
		return (c.get(Calendar.MONTH) + 1) + "/" + c.get(Calendar.DATE) + "/" + c.get(Calendar.YEAR);
	}
}
