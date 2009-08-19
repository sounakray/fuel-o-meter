/**
 * ListMainMenu.java
 * Fuel-O-Meter-02
 * @version %I%, %G%
 * Date Aug 15, 2009
 * � Sounak Ray
 * email: sounakray@gmail.com
 */
package sounakray.fuelometer.forms;

import javax.microedition.lcdui.Command;
import javax.microedition.lcdui.List;

import sounakray.fuelometer.midlet.FuelOMeter;

/**
 * @author Sounak Ray
 */
public final class ListMainMenu extends AbstractFuelOMeterScreen {
	// TODO: Make Singleton!
	private final Command cmdExit = new Command("Exit", Command.EXIT, 0);

	public ListMainMenu(final FuelOMeter midlet) {
		super(new List("Main Menu: ", List.IMPLICIT), midlet);
		screen.addCommand(cmdExit);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see sounakray.fuelometer.forms.AbstractFuelOMeterScreen#refreshScreen()
	 * 
	 * @author Sounak Ray
	 */
	public void loadScreen() {
		final List lstMainMenu = (List) screen;
		lstMainMenu.append("1. New Fill-up", null);
		lstMainMenu.append("2. Show Data", null);
		lstMainMenu.append("3. Statistics", null);
		lstMainMenu.append("4. Graph", null);
		lstMainMenu.append("5. Setup", null);
		lstMainMenu.append("6. About", null);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see sounakray.fuelometer.forms.AbstractFuelOMeterScreen#unloadScreen()
	 * 
	 * @author Sounak Ray
	 */
	public void unloadScreen() {
		((List) screen).deleteAll();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.sounakray.fuelometer.forms.AbstractForm#handleAction(javax.microedition
	 * .lcdui.Command)
	 */
	public void executeCommand(final Command command) {
		// TODO shift all references to all the screens to this class as this
		// controls navigation to all the screens.
		if (command == List.SELECT_COMMAND) {
			switch (((List) screen).getSelectedIndex()) {
			case 0:
				midlet.setDisplay(midlet.scrAddRec, null);
				break;
			case 1:
				midlet.setDisplay(midlet.scrViewData, null);
				break;
			case 2:
				midlet.setDisplay(midlet.scrViewStats, null);
				break;
			case 3:
				midlet.setDisplay(midlet.scrGraph, null);
				break;
			case 4:
				midlet.setDisplay(midlet.scrSetup, null);
				break;
			case 5:
				midlet.setDisplay(midlet.scrAbout, null);
				break;
			default:
			}
		} else if (command == cmdExit) {
			midlet.exitMIDlet();
		}
	}
}
