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
public final class FormAddNewFillUp extends AbstractForm {
	// TODO: Make Singleton!

	private final Command cmdMainMenu = new Command("Back", "Main Menu", Command.BACK, 0);
	private final Command cmdSaveRec = new Command("Save", "Save >>", Command.SCREEN, 1);

	public FormAddNewFillUp(final FuelOMeter midlet) {
		super("Add New Record", midlet);
		append("Fillup Details");

		addCommand(cmdMainMenu);
		addCommand(cmdSaveRec);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.sounakray.fuelometer.forms.AbstractForm#handleAction(javax.microedition
	 * .lcdui.Command)
	 */
	public void executeCommand(final Command c) {
		if (c == cmdMainMenu) {
			midlet.setDisplay(midlet.frmMainMenu);
		} else if (c == cmdSaveRec) {
			//Save record...
		}
	}
}
