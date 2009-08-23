/**
 * FormAbout.java
 * Fuel-O-Meter-02
 * @version %I%, %G%
 * Date Aug 15, 2009
 * © Sounak Ray
 * email: sounakray@gmail.com
 */
package sounakray.fuelometer.forms;

import javax.microedition.lcdui.Command;
import javax.microedition.lcdui.Form;
import sounakray.fuelometer.manager.FuelOMeterManager;
import sounakray.fuelometer.midlet.FuelOMeter;

/**
 * @author Sounak Ray
 */
public final class FormAbout extends AbstractFuelOMeterScreen {
	// TODO: Make Singleton!

	// private final Command cmdMainMenu = new Command("Back", "Main Menu", Command.BACK, 0);

	public FormAbout(final FuelOMeter midlet) {
		super(new Form("About Fuel-O-Meter"), midlet);
		((Form) screen).addCommand(FuelOMeterManager.CMD_MAIN_MENU);
	}

	/*
	 * (non-Javadoc)
	 * @see sounakray.fuelometer.forms.AbstractFuelOMeterScreen#loadScreen()
	 * @author Sounak Ray
	 */
	public void loadScreen(){
		((Form) screen).append("Designed and Devloped by : Sounak Ray");
		((Form) screen)
			.append("    This application has been developed as a part of my hobby programming, and is not to be "
					+ "distributed commercially. It is made available without any warranty or guarantee, and "
					+ "should be used at user's own risk.");
	}

	/*
	 * (non-Javadoc)
	 * @see sounakray.fuelometer.forms.AbstractFuelOMeterScreen#unloadScreen()
	 * @author Sounak Ray
	 */
	public void unloadScreen(){
		((Form) screen).deleteAll();
	}

	/*
	 * (non-Javadoc)
	 * @see com.sounakray.fuelometer.forms.AbstractForm#handleAction(javax.microedition .lcdui.Command)
	 */
	public void executeCommand(final Command command){
		if(command == FuelOMeterManager.CMD_MAIN_MENU){
			midlet.setDisplay(midlet.scrMainMenu, null);
		}
	}
}
