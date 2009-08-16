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
import sounakray.fuelometer.DAO.FuelOMeterDAO;
import sounakray.fuelometer.DAO.FuelOMeterRecordStoreDAOImpl;
import sounakray.fuelometer.model.FillUp;
import sounakray.fuelometer.model.StatisticsData;

/**
 * @author Sounak Ray
 */
public final class FuelOMeterManager {
	final FuelOMeterDAO dao = new FuelOMeterRecordStoreDAOImpl();

	public boolean saveRecord(final Date date, final String odometer, final String volume, final String unitPrice){
		boolean isSuccessful = true;
		try{
			dao.openDataStore();
			dao.saveFillUp(new FillUp(date, odometer, volume, unitPrice));
			dao.closeDataStore();
		}catch(final Exception e){
			isSuccessful = false;
		}
		return isSuccessful;
	}

	public FillUp[] getAllRecords(){
		dao.openDataStore();
		final FillUp[] records = dao.getFillUpList();
		dao.closeDataStore();
		return records;
	}

	public StatisticsData getStatistics(){
		final StatisticsData stats = new StatisticsData();
		final FillUp[] records = getAllRecords();
		int totalFuel = 0, totalDistance, totalCost = 0;
		final int numRecs = records.length;
		if(numRecs > 1){
			FillUp record = records[0];
			final FillUp firstRec = records[0], lastRec = records[numRecs - 1];
			totalDistance = MathFP.sub(lastRec.getOdometer(), firstRec.getOdometer());

			for(int i = 0; i < numRecs - 1; record = records[++i]){
				totalFuel = MathFP.add(totalFuel, record.getVolume());
				totalCost = MathFP.add(totalCost, MathFP.mul(record.getVolume(), record.getUnitPrice()));
			}

			stats.setStartDate(getFormattedDate(firstRec.getDate().getTime()));
			stats.setEndDate(getFormattedDate(lastRec.getDate().getTime()));
			stats.setAverageMileage(MathFP.toString(MathFP.div(totalDistance, totalFuel), 2));

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
