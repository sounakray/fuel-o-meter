/**
 * 
 */
package sounakray.fuelometer.forms;

import javax.microedition.lcdui.Command;
import javax.microedition.lcdui.Displayable;
import sounakray.fuelometer.midlet.FuelOMeter;

/**
 * @author Sounak Ray
 */
public abstract class AbstractFuelOMeterScreen {
	protected final FuelOMeter midlet;
	protected final Displayable screen;

	/**
	 * @param arg0
	 */
	public AbstractFuelOMeterScreen(final Displayable screen, final FuelOMeter midlet) {
		screen.setTitle(screen.getTitle() + " :Fuel-O-Meter");
		this.screen = screen;
		this.midlet = midlet;
		screen.setCommandListener(midlet);
	}

	/**
	 * Method Description:
	 * @param command
	 * @author Sounak Ray
	 * @since Aug 16, 2009
	 */
	public abstract void executeCommand(final Command command);

	public abstract void refreshScreen();

	public final Displayable getScreen(){
		return screen;
	}
}
