/**
 * AbstractFuelOMeterScreen.java
 * Fuel-O-Meter-02
 * @version %I%, %G%
 * Date Aug 15, 2009
 * © Sounak Ray
 * email: sounakray@gmail.com
 */
package sounakray.fuelometer.forms;

import javax.microedition.lcdui.Command;
import javax.microedition.lcdui.Displayable;
import sounakray.fuelometer.midlet.FuelOMeter;

/**
 * Class Description: This abstract class is meant to be inherited by all the display screens in this application.
 * @author Sounak Ray
 * @since Aug 16, 2009
 */
public abstract class AbstractFuelOMeterScreen {
	protected transient final FuelOMeter midlet;
	protected transient final Displayable screen;

	/**
	 * Constructor Description: The only constructor. Mandates setting the screen and the MIDlet. Appends the
	 * application name at the end of the screen name.
	 * @param screen The Displayable screen that this object will have.
	 * @param midlet The MIDlet that this screen will be displayed on.
	 * @author Sounak Ray
	 * @since Aug 16, 2009
	 */
	public AbstractFuelOMeterScreen(final Displayable screen, final FuelOMeter midlet) {
		screen.setTitle(screen.getTitle() + ": Fuel-O-Meter");
		this.screen = screen;
		this.midlet = midlet;
		screen.setCommandListener(midlet);
	}

	/**
	 * Method Description: Implementation of this method is expected to load all the UI components of this screen, when
	 * this screen is set as the current display screen. The {@link AbstractFuelOMeterScreen#unloadScreen()
	 * unloadScreen()} method should be able to unload these and release memory. This method should also e responsible
	 * to update its content, every time called, with changes made to the data-store.
	 * @see FuelOMeter#setDisplay(AbstractFuelOMeterScreen, javax.microedition.lcdui.Alert)
	 * @author Sounak Ray
	 * @since Aug 16, 2009
	 */
	public abstract void loadScreen();

	/**
	 * Method Description: Implementation of this method is expected to release all memory allocated by this screen in
	 * the {@link AbstractFuelOMeterScreen#loadScreen() loadScreen()} method.
	 * @see FuelOMeter#setDisplay(AbstractFuelOMeterScreen, javax.microedition.lcdui.Alert)
	 * @author Sounak Ray
	 * @since Aug 16, 2009
	 */
	public abstract void unloadScreen();

	/**
	 * Method Description: Implementation of this method is expected to execute the action associated to the Command
	 * button being passed.
	 * @param command The command button pressed.
	 * @author Sounak Ray
	 * @since Aug 16, 2009
	 */
	public abstract void executeCommand(final Command command);

	/**
	 * Method Description: Returns the this objects's screen reference (can typically be Form, List, etc...)
	 * @return A Displayable screen reference.
	 * @author Sounak Ray
	 * @since Aug 16, 2009
	 */
	public final Displayable getScreen(){
		return screen;
	}
}
