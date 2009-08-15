/**
 * 
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
	private final List lstMainMenu;

	public ListMainMenu(final FuelOMeter midlet) {
		super(new List("Main Menu: ", List.IMPLICIT), midlet);

		lstMainMenu = (List) screen;
		lstMainMenu.append("1. New Fill-up", null);
		lstMainMenu.append("2. Show Data", null);
		lstMainMenu.append("3. Statistics", null);
		lstMainMenu.append("4. Graph", null);
		lstMainMenu.append("5. Setup", null);
		lstMainMenu.append("6. About", null);

		screen.addCommand(cmdExit);
	}

	/*
	 * (non-Javadoc)
	 * @see com.sounakray.fuelometer.forms.AbstractForm#handleAction(javax.microedition .lcdui.Command)
	 */
	public void executeCommand(final Command command){
		if(command == List.SELECT_COMMAND){
			switch (lstMainMenu.getSelectedIndex()) {
			case 0:
				midlet.setDisplay(midlet.scrAddRec);
				break;
			case 1:
				break;
			case 2:
				break;
			case 3:
				break;
			case 4:
				break;
			case 5:
				midlet.setDisplay(midlet.scrAbout);
				break;
			default:
			}
		}else if(command == cmdExit){
			midlet.exitMIDlet();
		}
	}
}
