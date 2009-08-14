/**
 * 
 */
package sounakray.fuelometer.forms;

import javax.microedition.lcdui.Command;

import sounakray.fuelometer.midlet.FuelOMeter;

/**
 * @author Sounak Ray
 * 
 */
public final class FormMainMenu extends AbstractForm {
	// TODO: Make Singleton!

	public  final Command cmdExit = new Command("Exit", Command.EXIT, 0);
	public final Command cmdAddRec = new Command("Add", "New Fillup Record",
			Command.SCREEN, 1);

	public FormMainMenu(final FuelOMeter midlet) {
		super("Fuel-O-Meter 01", midlet);
		append("Main Menu Screen!");

		addCommand(cmdExit);
		addCommand(cmdAddRec);
		
//		midlet.cmdLeft = cmdExit;
//		midlet.cmdRight = cmdAddRec;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.sounakray.fuelometer.forms.AbstractForm#handleAction(javax.microedition
	 * .lcdui.Command)
	 */
	public void executeCommand(final Command c) {
		if (c == cmdAddRec) {
			midlet.setDisplay(midlet.frmAddRec);
		} else if (c == cmdExit) {
			midlet.exitMIDlet();
		}
	}
}
