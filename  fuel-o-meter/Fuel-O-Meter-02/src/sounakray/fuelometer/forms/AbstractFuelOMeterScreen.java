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
		this.screen = screen;
		this.midlet = midlet;
		screen.setCommandListener(midlet);
	}

	/**
	 * @param c
	 */
	public abstract void executeCommand(final Command c);

	public final Displayable getScreen(){
		return screen;
	}
}
