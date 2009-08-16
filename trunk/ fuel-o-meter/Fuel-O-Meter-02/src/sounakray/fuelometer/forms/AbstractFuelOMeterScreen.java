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
	protected final FuelOMeter midlet;
	protected final Displayable screen;

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
	 * Method Description:
	 * @author Sounak Ray
	 * @since Aug 16, 2009
	 */
	public abstract void loadScreen();

	/**
	 * Method Description:
	 * @author Sounak Ray
	 * @since Aug 16, 2009
	 */
	public abstract void unloadScreen();

	/**
	 * Method Description:
	 * @param command
	 * @author Sounak Ray
	 * @since Aug 16, 2009
	 */
	public abstract void executeCommand(final Command command);

	/**
	 * Method Description:
	 * @return
	 * @author Sounak Ray
	 * @since Aug 16, 2009
	 */
	public final Displayable getScreen(){
		return screen;
	}
}
