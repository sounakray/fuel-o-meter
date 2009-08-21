/** FormSetup.java
 * 
 */
package sounakray.fuelometer.forms;

import javax.microedition.lcdui.ChoiceGroup;
import javax.microedition.lcdui.Command;
import javax.microedition.lcdui.Form;
import sounakray.fuelometer.midlet.FuelOMeter;

/**
 * @author Marshneil
 */
public class FormSetup extends AbstractFuelOMeterScreen {

	final Form form;
	private final Command cmdMainMenu = new Command("Back", "Main Menu", Command.BACK, 0);
	String[] strUnits = { "Kms/Hr", "Miles/Gallon" };

	public FormSetup(final FuelOMeter midlet) {
		super(new Form("Set Format"), midlet);
		form = (Form) screen;
		form.addCommand(cmdMainMenu);
		ChoiceGroup chcUnit = new ChoiceGroup("Select Unit", ChoiceGroup.EXCLUSIVE, strUnits, null);
		Command cmdResetData = new Command("Reset", "Reset Data", Command.OK, 0);
		form.append(chcUnit);
		form.addCommand(cmdResetData);

	}

	/*
	 * (non-Javadoc)
	 * @see sounakray.fuelometer.forms.AbstractFuelOMeterScreen#executeCommand(javax .microedition.lcdui.Command)
	 */
	public void executeCommand(Command cmd){
		if(cmd == cmdMainMenu){
			midlet.setDisplay(midlet.scrMainMenu, null);
		}

	}

	/*
	 * (non-Javadoc)
	 * @see sounakray.fuelometer.forms.AbstractFuelOMeterScreen#loadScreen()
	 */
	public void loadScreen(){
	// TODO Auto-generated method stub

	}

	/*
	 * (non-Javadoc)
	 * @see sounakray.fuelometer.forms.AbstractFuelOMeterScreen#unloadScreen()
	 */
	public void unloadScreen(){
	// TODO Auto-generated method stub

	}

}
