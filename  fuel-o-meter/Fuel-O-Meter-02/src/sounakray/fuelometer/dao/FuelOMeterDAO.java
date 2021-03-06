/**
 * FuelOMeterDAO.java
 * Fuel-O-Meter-02
 * @version %I%, %G%
 * Date Aug 15, 2009
 * � Sounak Ray
 * email: sounakray@gmail.com
 */
package sounakray.fuelometer.dao;

import sounakray.fuelometer.model.FillUp;

/**
 * Interface Description: The DAO Interface for the FuelOMeter MIDlet application. <br>
 * The possible implementations of this interface may include RecordStore, File Connections etc, or even a remote cloud
 * storage.
 * @author Sounak Ray
 * @since Aug 16, 2009
 */
public interface FuelOMeterDAO {
	/**
	 * Method Description: This method should save the FillUp bean passed.
	 * @param fillUp The pre-populated bean to be saved.
	 * @return true if the operation was carried out successfully; false otherwise.
	 * @author Sounak Ray
	 * @since Aug 16, 2009
	 */
	boolean saveFillUp(final FillUp fillUp);

	/**
	 * Method Description: This method should fetch an array of all the FillUp records.
	 * @return array of all the FillUp records.
	 * @author Sounak Ray
	 * @since Aug 16, 2009
	 */
	FillUp[] getAllFillUpList();

	/**
	 * Method Description: This method should delete all the data AND settings in the application, and bring it to the
	 * state when it was first installed. <br>
	 * This includes deleting all the fill-up records and application settings/configurations.
	 * @return
	 * @author Sounak Ray
	 * @since Aug 26, 2009
	 */
	boolean resetAllData();
}
