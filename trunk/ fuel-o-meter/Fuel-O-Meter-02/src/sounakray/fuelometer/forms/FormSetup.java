/** FormSetup.java
 * 
 */
package sounakray.fuelometer.forms;

import javax.microedition.lcdui.ChoiceGroup;
import javax.microedition.lcdui.Command;
import javax.microedition.lcdui.Form;
import sounakray.fuelometer.manager.FuelOMeterManager;
import sounakray.fuelometer.midlet.FuelOMeter;

/**
 * @author Marshneil
 */
public class FormSetup extends AbstractFuelOMeterScreen {
	/**
	 * Constructor Description:
	 * @param midlet
	 * @author Sounak Ray
	 * @since Aug 22, 2009
	 */
	public FormSetup(final FuelOMeter midlet) {
		super(new Form("Set Format"), midlet);
		((Form) screen).addCommand(FuelOMeterManager.CMD_MAIN_MENU);
	}

	/*
	 * (non-Javadoc)
	 * @see sounakray.fuelometer.forms.AbstractFuelOMeterScreen#loadScreen()
	 */
	public void loadScreen(){
		final Form form = (Form) screen;
		final Command cmdResetData = new Command("Reset", "Reset Data", Command.SCREEN, 1);
		form.addCommand(cmdResetData);
		form.append(new ChoiceGroup("Select Unit", ChoiceGroup.EXCLUSIVE, new String[] { "Kms/Ltr", "Miles/Gallon" },
			null));
	}

	/*
	 * (non-Javadoc)
	 * @see sounakray.fuelometer.forms.AbstractFuelOMeterScreen#unloadScreen()
	 */
	public void unloadScreen(){
		((Form) screen).deleteAll();
	}

	/*
	 * (non-Javadoc)
	 * @see sounakray.fuelometer.forms.AbstractFuelOMeterScreen#executeCommand(javax .microedition.lcdui.Command)
	 */
	public void executeCommand(final Command command){
		if(command == FuelOMeterManager.CMD_MAIN_MENU){
			midlet.setDisplay(midlet.scrMainMenu, null);
		}

	}

}
