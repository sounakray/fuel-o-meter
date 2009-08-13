/**
 * 
 */
package com.sounakray.fuelometer.forms;

import javax.microedition.lcdui.Command;
import javax.microedition.lcdui.Form;

import com.sounakray.fuelometer.midlet.Fuel_O_Meter;

/**
 * @author Sounak Ray
 * 
 */
public abstract class AbstractForm extends Form {
	protected final Fuel_O_Meter midlet;

	/**
	 * @param arg0
	 */
	public AbstractForm(final String name, final Fuel_O_Meter midlet) {
		super(name);
		this.midlet = midlet;
		setCommandListener(midlet);
	}

	/**
	 * @param c
	 */
	public abstract void executeCommand(final Command c);
}
