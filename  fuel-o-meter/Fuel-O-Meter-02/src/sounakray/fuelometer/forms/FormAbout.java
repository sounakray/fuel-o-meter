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
import sounakray.fuelometer.midlet.FuelOMeter;

/**
 * @author Sounak Ray
 */
public final class FormAbout extends AbstractFuelOMeterScreen {
	// TODO: Make Singleton!

	final Form form;
	private final Command cmdMainMenu = new Command("Back", "Main Menu", Command.BACK, 0);
	private final String strAboutText;

	public FormAbout(final FuelOMeter midlet) {
		super(new Form("About Fuel-O-Meter"), midlet);

		strAboutText =
			" \t This application has been developed as a part of my hobby programming, and is not to be "
					+ "distributed commercially. It is made available without any warranty or guarantee, "
					+ "and should be used at user's own risk.";

		form = (Form) screen;
		form.append(strAboutText);
		form.addCommand(cmdMainMenu);
	}

	/*
	 * (non-Javadoc)
	 * @see com.sounakray.fuelometer.forms.AbstractForm#handleAction(javax.microedition .lcdui.Command)
	 */
	public void executeCommand(final Command c){
		if(c == cmdMainMenu){
			midlet.setDisplay(midlet.scrMainMenu, null);
		}
	}

	/*
	 * (non-Javadoc)
	 * @see sounakray.fuelometer.forms.AbstractFuelOMeterScreen#loadScreen()
	 * @author Sounak Ray
	 */
	public void loadScreen(){}

	/*
	 * (non-Javadoc)
	 * @see sounakray.fuelometer.forms.AbstractFuelOMeterScreen#unloadScreen()
	 * @author Sounak Ray
	 */
	public void unloadScreen(){
	// TODO Auto-generated method stub

	}
}
