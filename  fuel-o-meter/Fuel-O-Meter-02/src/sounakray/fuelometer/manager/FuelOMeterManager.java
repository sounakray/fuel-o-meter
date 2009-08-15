/**
 * FuelOMeterManager.java
 * Fuel-O-Meter-02
 * @version %I%, %G%
 * Date Aug 15, 2009
 * © Sounak Ray
 * email: sounakray@gmail.com
 */
package sounakray.fuelometer.manager;

import java.util.Date;
import sounakray.fuelometer.DAO.FuelOMeterDAO;
import sounakray.fuelometer.DAO.FuelOMeterRecordStoreDAOImpl;
import sounakray.fuelometer.model.FillUp;

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
}
