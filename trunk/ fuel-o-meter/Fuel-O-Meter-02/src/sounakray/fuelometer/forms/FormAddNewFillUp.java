/**
 * 
 */
package sounakray.fuelometer.forms;

import java.util.Date;
import java.util.TimeZone;

import javax.microedition.lcdui.Command;
import javax.microedition.lcdui.DateField;
import javax.microedition.lcdui.TextBox;
import javax.microedition.lcdui.TextField;

import sounakray.fuelometer.midlet.FuelOMeter;

/**
 * @author Sounak Ray
 *
 */
public final class FormAddNewFillUp extends AbstractForm {
	// TODO: Make Singleton!

	private final Command cmdMainMenu = new Command("Back", "Main Menu", Command.BACK, 0);
	private final Command cmdSaveRec = new Command("Save", "Save >>", Command.SCREEN, 1);
	private final TextField txtOdometer, txtVolume, txtRate;
	private final DateField dtfFillupDate;

	public FormAddNewFillUp(final FuelOMeter midlet) {
		super("Add New Record", midlet);
		append("Fillup Details");
		
		dtfFillupDate = new DateField("Fillup Date: ", DateField.DATE, TimeZone.getDefault());
		dtfFillupDate.setDate(new Date());
		txtOdometer = new TextField("Odometer Reading: ", "0.0", 7, TextField.DECIMAL);
		txtVolume= new TextField("Fillup Volume: ", "0.0", 5, TextField.DECIMAL);;
		txtRate= new TextField("Rate : ", "0.0", 5, TextField.DECIMAL);;

		append(dtfFillupDate);
		append(txtOdometer);
		append(txtVolume);
		append(txtRate);
		
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
