/**
 * FormAddNewFillUp.java
 * Fuel-O-Meter-02
 * @version %I%, %G%
 * Date Aug 15, 2009
 * © Sounak Ray
 * email: sounakray@gmail.com
 */
package sounakray.fuelometer.forms;

import java.util.Date;
import javax.microedition.lcdui.Alert;
import javax.microedition.lcdui.AlertType;
import javax.microedition.lcdui.Command;
import javax.microedition.lcdui.DateField;
import javax.microedition.lcdui.Form;
import javax.microedition.lcdui.TextField;
import sounakray.fuelometer.midlet.FuelOMeter;

/**
 * @author Sounak Ray
 */
public final class FormAddNewFillUp extends AbstractFuelOMeterScreen {
	// TODO: Make Singleton!

	final Form form;
	private final Command cmdMainMenu = new Command("Back", "Main Menu", Command.BACK, 0);
	private final Command cmdSaveRec = new Command("Save", "Save >>", Command.SCREEN, 1);
	private final TextField txtOdometer, txtVolume, txtRate;
	private final DateField dtfFillupDate;

	public FormAddNewFillUp(final FuelOMeter midlet) {
		super(new Form("Add New Record"), midlet);
		form = (Form) screen;
		form.append("Fillup Details");

		dtfFillupDate = new DateField("Fillup Date: ", DateField.DATE);
		dtfFillupDate.setDate(new Date());
		txtOdometer = new TextField("Odometer Reading: ", "0.0", 7, TextField.DECIMAL);
		txtVolume = new TextField("Fillup Volume: ", "0.0", 5, TextField.DECIMAL);
		txtRate = new TextField("Rate : ", "0.0", 5, TextField.DECIMAL);

		form.append(dtfFillupDate);
		form.append(txtOdometer);
		form.append(txtVolume);
		form.append(txtRate);

		form.addCommand(cmdMainMenu);
		form.addCommand(cmdSaveRec);
	}

	/*
	 * (non-Javadoc)
	 * @see com.sounakray.fuelometer.forms.AbstractForm#handleAction(javax.microedition .lcdui.Command)
	 */
	public void executeCommand(final Command c){
		if(c == cmdMainMenu){
			midlet.setDisplay(midlet.scrMainMenu, null);
		}else if(c == cmdSaveRec){
			final Alert alert =
				(midlet.manager.saveRecord(dtfFillupDate.getDate(), txtOdometer.getString(), txtVolume.getString(),
					txtRate.getString())) ? new Alert("Saved", "Fill-up data saved successfully!", null, AlertType.INFO) : new Alert(
					"Error!!!", "Fill-up data could not be saved successfully!", null, AlertType.ERROR);
			midlet.setDisplay(midlet.scrMainMenu, alert);
		}
	}

	/*
	 * (non-Javadoc)
	 * @see sounakray.fuelometer.forms.AbstractFuelOMeterScreen#refreshScreen()
	 * @author Sounak Ray
	 */
	public void loadScreen(){
		dtfFillupDate.setDate(new Date());
	}

	/*
	 * (non-Javadoc)
	 * @see sounakray.fuelometer.forms.AbstractFuelOMeterScreen#unloadScreen()
	 * @author Sounak Ray
	 */
	public void unloadScreen(){
	// TODO Auto-generated method stub

	}
}
