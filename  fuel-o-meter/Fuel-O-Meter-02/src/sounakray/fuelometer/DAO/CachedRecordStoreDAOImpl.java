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
			int i = 0;
			RecordStore rs = null;
			try{
				rs = RecordStore.openRecordStore(RECORDSET_NAME, true);
				final RecordEnumeration recordsEnumeration = rs.enumerateRecords(null, new RecordComparator() {
					public int compare(final byte[] rec1, final byte[] rec2){
						final int result = new String(rec1).compareTo(new String(rec2));
						return result == 0 ? EQUIVALENT : (result < 0 ? PRECEDES : FOLLOWS);
					}
				}, false);
				allRecords = new FillUp[(recordsEnumeration.numRecords())];
				while (recordsEnumeration.hasNextElement()){
					allRecords[i++] = new FillUp(recordsEnumeration.nextRecord());
				}
			}catch(Exception e){
				allRecords = null;
			}finally{
				if(rs != null){
					try{
						rs.closeRecordStore();
					}catch(RecordStoreException e){
						allRecords = null;
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
		RecordStore rs = null;
		try{
			rs = RecordStore.openRecordStore(RECORDSET_NAME, true);
			rs.addRecord(record, 0, record.length);
		}catch(RecordStoreException e){
			isSuccessful = false;
		}finally{
			allRecords = null;
			if(rs != null){
				try{
					rs.closeRecordStore();
				}catch(RecordStoreException e){
					isSuccessful = false;
				}
			}
		}
		return isSuccessful;
	}
}
