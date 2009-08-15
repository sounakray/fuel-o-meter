/**
 * FuelOMeterDAO.java
 * Fuel-O-Meter-02
 * @version %I%, %G%
 * Date Aug 15, 2009
 * © Sounak Ray
 * email: sounakray@gmail.com
 */
package sounakray.fuelometer.DAO;

import sounakray.fuelometer.model.FillUp;

/**
 * @author Sounak Ray
 */
public interface FuelOMeterDAO {
	boolean openDataStore();

	boolean closeDataStore();

	boolean saveFillUp(final FillUp fillUp);

	FillUp[] getFillUpList();
}
