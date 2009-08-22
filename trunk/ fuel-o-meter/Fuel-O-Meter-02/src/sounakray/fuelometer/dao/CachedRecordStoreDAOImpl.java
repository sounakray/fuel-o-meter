/**
 * CachedRecordStoreDAOImpl.java
 * Fuel-O-Meter-02
 * @version %I%, %G%
 * Date Aug 15, 2009
 * © Sounak Ray
 * email: sounakray@gmail.com
 */
package sounakray.fuelometer.dao;

import javax.microedition.rms.RecordComparator;
import javax.microedition.rms.RecordEnumeration;
import javax.microedition.rms.RecordStore;
import javax.microedition.rms.RecordStoreException;
import sounakray.fuelometer.model.FillUp;

/**
 * Class Description: A concrete implementation class of the abstract class {@link FuelOMeterDAO}, using
 * {@link RecordStore}. This class minimises the actual RecordStore operations by internally maintaining a cache of the
 * entire data-set, and returning values from this cache whenever possible. The caching is automatically refreshed
 * internally. The calling class/method need not be aware of it in anyway.
 * @author Sounak Ray
 * @since Aug 16, 2009
 */
public final class CachedRecordStoreDAOImpl implements FuelOMeterDAO {
	private static final String RECORDSET_NAME = "RecordSet_FuelOMeter";
	private static FillUp[] allRecords = null;

	/*
	 * (non-Javadoc)
	 * @see sounakray.fuelometer.DAO.FuelOMeterDAO#getFillUpList()
	 * @author Sounak Ray
	 */
	public FillUp[] getAllFillUpList(){
		if(allRecords == null){
			int index = 0;
			RecordStore recordStore = null;
			try{
				recordStore = RecordStore.openRecordStore(RECORDSET_NAME, true);
				final RecordEnumeration recordsEnum = recordStore.enumerateRecords(null, new RecordComparator() {
					public int compare(final byte[] rec1, final byte[] rec2){
						final int result = new String(rec1).compareTo(new String(rec2));
						return result == 0 ? EQUIVALENT : (result < 0 ? PRECEDES : FOLLOWS);
					}
				}, false);
				allRecords = new FillUp[recordsEnum.numRecords()];
				while (recordsEnum.hasNextElement()){
					allRecords[index++] = new FillUp(recordsEnum.nextRecord()); // NOPMD by Sounak Ray on 8/22/09 2:23
					// PM
				}
			}catch(Exception e){
				allRecords = null; // NOPMD by Sounak Ray on 8/22/09 3:13 PM
			}finally{
				if(recordStore != null){
					try{
						recordStore.closeRecordStore();
					}catch(RecordStoreException e){
						allRecords = null; // NOPMD by Sounak Ray on 8/22/09 3:13 PM
					}
				}
			}
		}
		return allRecords;
	}

	/*
	 * (non-Javadoc)
	 * @see sounakray.fuelometer.DAO.FuelOMeterDAO#saveFillUp(sounakray.fuelometer.model.FillUp)
	 * @author Sounak Ray
	 */
	public boolean saveFillUp(final FillUp fillUp){
		boolean isSuccessful = true;
		final byte[] record = fillUp.toByteArray();
		RecordStore recordsStore = null;
		try{
			recordsStore = RecordStore.openRecordStore(RECORDSET_NAME, true);
			recordsStore.addRecord(record, 0, record.length);
		}catch(RecordStoreException e){
			isSuccessful = false;
		}finally{
			allRecords = null; // NOPMD by Sounak Ray on 8/22/09 3:13 PM
			if(recordsStore != null){
				try{
					recordsStore.closeRecordStore();
				}catch(RecordStoreException e){
					isSuccessful = false;
				}
			}
		}
		return isSuccessful;
	}
}
