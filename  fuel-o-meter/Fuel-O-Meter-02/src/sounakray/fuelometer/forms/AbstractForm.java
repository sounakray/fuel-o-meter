/**
 * 
 */
package sounakray.fuelometer.forms;

import javax.microedition.lcdui.Command;
import javax.microedition.lcdui.Form;

import sounakray.fuelometer.midlet.FuelOMeter;

/**
 * @author Sounak Ray
 * 
 */
public abstract class AbstractForm extends Form {
	protected final FuelOMeter midlet;

	/**
	 * @param arg0
	 */
	public AbstractForm(final String name, final FuelOMeter midlet) {
		super(name);
		this.midlet = midlet;
		setCommandListener(midlet);
	}

	/**
	 * @param c
	 */
	public abstract void executeCommand(final Command c);
}
