/**
 * FuelOMeterRecordStoreDAOImpl.java
 * Fuel-O-Meter-02
 * @version %I%, %G%
 * Date Aug 15, 2009
 * © Sounak Ray
 * email: sounakray@gmail.com
 */
package sounakray.fuelometer.DAO;

import javax.microedition.rms.RecordComparator;
import javax.microedition.rms.RecordEnumeration;
import javax.microedition.rms.RecordStore;
import javax.microedition.rms.RecordStoreException;
import sounakray.fuelometer.model.FillUp;

/**
 * @author Sounak Ray
 */
public final class FuelOMeterRecordStoreDAOImpl implements FuelOMeterDAO {
	private static final String RECORDSET_NAME = "RecordSet_FuelOMeter";
	private static RecordStore rs;

	public FuelOMeterRecordStoreDAOImpl() {}

	/*
	 * (non-Javadoc)
	 * @see sounakray.fuelometer.DAO.FuelOMeterDAO#openDataBase()
	 * @author Sounak Ray
	 */
	public boolean openDataStore(){
		boolean isSuccessful = true;
		try{
			rs = RecordStore.openRecordStore(RECORDSET_NAME, true);
		}catch(RecordStoreException e){
			rs = null;
			isSuccessful = false;
		}
		return isSuccessful;
	}

	/*
	 * (non-Javadoc)
	 * @see sounakray.fuelometer.DAO.FuelOMeterDAO#closeDataBase()
	 * @author Sounak Ray
	 */
	public boolean closeDataStore(){
		boolean isSuccessful = true;
		if(rs != null){
			try{
				rs.closeRecordStore();
				rs = null;
			}catch(RecordStoreException e){
				isSuccessful = false;
			}
		}
		return isSuccessful;
	}

	/*
	 * (non-Javadoc)
	 * @see sounakray.fuelometer.DAO.FuelOMeterDAO#getFillUpList()
	 * @author Sounak Ray
	 */
	public FillUp[] getFillUpList(){
		FillUp[] fillUpList;
		int i = 0;
		try{
			final RecordEnumeration recordsEnumeration = rs.enumerateRecords(null, new RecordComparator() {
				public int compare(final byte[] rec1, final byte[] rec2){
					final int result = new String(rec1).compareTo(new String(rec2));
					return result == 0 ? EQUIVALENT : (result < 0 ? PRECEDES : FOLLOWS);
				}
			}, false);
			fillUpList = new FillUp[(recordsEnumeration.numRecords())];
			while (recordsEnumeration.hasNextElement()){
				fillUpList[i++] = new FillUp(recordsEnumeration.nextRecord());
			}
		}catch(Exception e){
			fillUpList = null;
		}
		return fillUpList;
	}

	/*
	 * (non-Javadoc)
	 * @see sounakray.fuelometer.DAO.FuelOMeterDAO#saveFillUp(sounakray.fuelometer.model.FillUp)
	 * @author Sounak Ray
	 */
	public boolean saveFillUp(final FillUp fillUp){
		boolean isSuccessful = true;
		final byte[] record = fillUp.toByteArray();
		try{
			rs.addRecord(record, 0, record.length);
		}catch(RecordStoreException e){
			isSuccessful = false;
		}
		return isSuccessful;
	}
}
