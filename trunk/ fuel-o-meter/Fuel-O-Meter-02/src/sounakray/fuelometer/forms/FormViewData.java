/**
 * FormViewData.java
 * Fuel-O-Meter-02
 * @version %I%, %G%
 * Date Aug 15, 2009
 * © Sounak Ray
 * email: sounakray@gmail.com
 */
package sounakray.fuelometer.forms;

import javax.microedition.lcdui.Command;
import javax.microedition.lcdui.List;
import sounakray.fuelometer.midlet.FuelOMeter;
import sounakray.fuelometer.model.FillUp;

/**
 * @author Sounak Ray
 */
public class FormViewData extends AbstractFuelOMeterScreen {
	// TODO: Make Singleton!
	private final Command cmdMainMenu = new Command("Back", "Main Menu", Command.BACK, 0);

	public FormViewData(final FuelOMeter midlet) {
		super(new List("All Fill-ups ", List.EXCLUSIVE), midlet);

		screen.addCommand(cmdMainMenu);
	}

	/*
	 * (non-Javadoc)
	 * @see com.sounakray.fuelometer.forms.AbstractForm#handleAction(javax.microedition .lcdui.Command)
	 */
	public void executeCommand(final Command command){
		if(command == cmdMainMenu){
			midlet.setDisplay(midlet.scrMainMenu, null);
		}
	}

	/*
	 * (non-Javadoc)
	 * @see sounakray.fuelometer.forms.AbstractFuelOMeterScreen#refreshScreen()
	 * @author Sounak Ray
	 */
	public void loadScreen(){
		final List lstMainMenu = (List) screen;
		lstMainMenu.deleteAll();
		final FillUp[] allRecords = midlet.manager.getAllRecords();
		for(int i = 0; i < allRecords.length; i++){
			lstMainMenu.append(allRecords[i].toString(), null);
		}
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
