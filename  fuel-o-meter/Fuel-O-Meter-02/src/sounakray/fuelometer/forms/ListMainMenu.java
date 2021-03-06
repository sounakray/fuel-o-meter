/**
 * ListMainMenu.java
 * Fuel-O-Meter-02
 * @version %I%, %G%
 * Date Aug 15, 2009
 * � Sounak Ray
 * email: sounakray@gmail.com
 */
package sounakray.fuelometer.forms;

import javax.microedition.lcdui.Alert;
import javax.microedition.lcdui.AlertType;
import javax.microedition.lcdui.Choice;
import javax.microedition.lcdui.Command;
import javax.microedition.lcdui.Font;
import javax.microedition.lcdui.Image;
import javax.microedition.lcdui.List;
import javax.microedition.lcdui.Ticker;
import sounakray.fuelometer.manager.FuelOMeterManager;
import sounakray.fuelometer.midlet.FuelOMeter;

/**
 * @author Sounak Ray
 */
public final class ListMainMenu extends AbstractFuelOMeterScreen {
	// TODO: Make Singleton!
	private transient final Command cmdExit = new Command("Exit", Command.EXIT, 0);
	private static final String INADEQUATE_DATA = "Not enough data!";

	public ListMainMenu(final FuelOMeter midlet) {
		super(new List("Main Menu", List.IMPLICIT), midlet);
		screen.addCommand(cmdExit);
	}

	/*
	 * (non-Javadoc)
	 * @see sounakray.fuelometer.forms.AbstractFuelOMeterScreen#refreshScreen()
	 * @author Sounak Ray
	 */
	public void loadScreen(){
		final Image img = null;
		// try{
		// img = Image.createImage("images/gas-pump.png");
		// }catch(IOException e){
		// e.printStackTrace();
		// }
		final Font font = Font.getFont(Font.FACE_MONOSPACE, Font.STYLE_PLAIN, Font.SIZE_LARGE);
		final List lstMainMenu = (List) screen;
		lstMainMenu.setFont(lstMainMenu.append("1. New Fill-up", img), font);
		lstMainMenu.setFont(lstMainMenu.append("2. Show Data", img), font);
		lstMainMenu.setFont(lstMainMenu.append("3. Statistics", img), font);
		lstMainMenu.setFont(lstMainMenu.append("4. Graph", img), font);
		lstMainMenu.setFont(lstMainMenu.append("5. Setup", img), font);
		lstMainMenu.setFont(lstMainMenu.append("6. About", img), font);
		lstMainMenu.setFitPolicy(Choice.TEXT_WRAP_ON);
		final String nextFillup = FuelOMeterManager.INSTANCE.getNextFillUpEstimate();
		if(!"00000.0".equals(nextFillup)){
			lstMainMenu.setTicker(new Ticker("Fuel is estimated to last till: " + nextFillup));
		}
	}

	/*
	 * (non-Javadoc)
	 * @see sounakray.fuelometer.forms.AbstractFuelOMeterScreen#unloadScreen()
	 * @author Sounak Ray
	 */
	public void unloadScreen(){
		((List) screen).deleteAll();
	}

	/*
	 * (non-Javadoc)
	 * @see com.sounakray.fuelometer.forms.AbstractForm#handleAction(javax.microedition .lcdui.Command)
	 */
	public void executeCommand(final Command command){
		// TODO shift all references to all the screens to this class as this
		// controls navigation to all the screens.
		final FuelOMeterManager manager = FuelOMeterManager.INSTANCE;
		if(command == List.SELECT_COMMAND){
			switch (((List) screen).getSelectedIndex()) {
			case 0: // New Fill Up
				midlet.setDisplay(midlet.scrAddRec, null);
				break;
			case 1: // View All Data
				if(manager.getRecordsCount() < 1){
					midlet.setDisplay(midlet.scrMainMenu, new Alert(INADEQUATE_DATA, "No record has been added yet.",
						null, AlertType.INFO));
				}else{
					midlet.setDisplay(midlet.scrViewData, null);
				}
				break;
			case 2: // View Statistics
				if(manager.getRecordsCount() < 2){
					midlet.setDisplay(midlet.scrMainMenu, new Alert(INADEQUATE_DATA,
						"At least two fill-up records are required to calculate statistics.", null, AlertType.INFO));
				}else{
					midlet.setDisplay(midlet.scrViewStats, null);
				}
				break;
			case 3: // View Graph
				if(manager.getRecordsCount() < 3){
					midlet.setDisplay(midlet.scrMainMenu, new Alert(INADEQUATE_DATA,
						"At least three fill-up records are required to plot a graph.", null, AlertType.INFO));
				}else{
					midlet.setDisplay(midlet.scrGraph, null);
				}
				break;
			case 4: // Setup
				midlet.setDisplay(midlet.scrSetup, null);
				break;
			case 5: // About
				midlet.setDisplay(midlet.scrAbout, null);
				break;
			default:
			}
		}else if(command == cmdExit){ // NOPMD by Sounak Ray on 8/22/09 2:09 PM
			midlet.exitMIDlet();
		}
	}
}
