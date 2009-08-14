package sounakray.fuelometer.midlet;

import javax.microedition.lcdui.Command;
import javax.microedition.lcdui.CommandListener;
import javax.microedition.lcdui.Display;
import javax.microedition.lcdui.Displayable;
import javax.microedition.midlet.MIDlet;
import javax.microedition.midlet.MIDletStateChangeException;
import sounakray.fuelometer.forms.AbstractFuelOMeterScreen;
import sounakray.fuelometer.forms.FormAddNewFillUp;
import sounakray.fuelometer.forms.FormMainMenu;

public final class FuelOMeter extends MIDlet implements CommandListener {
	private Display display;
	private AbstractFuelOMeterScreen currentForm;

	public final AbstractFuelOMeterScreen frmMainMenu;
	public final AbstractFuelOMeterScreen frmAddRec;

	public FuelOMeter() {
		display = Display.getDisplay(this);
		frmMainMenu = new FormMainMenu(this);
		frmAddRec = new FormAddNewFillUp(this);
	}

	protected void startApp() throws MIDletStateChangeException{
		setDisplay(frmMainMenu);
	}

	protected void pauseApp(){}

	protected void destroyApp(final boolean unconditional){}

	public void setDisplay(final AbstractFuelOMeterScreen form){
		currentForm = form;
		display.setCurrent(currentForm.getScreen());
	}

	public void commandAction(Command c, Displayable d){
		currentForm.executeCommand(c);
	}

	public void exitMIDlet(){
		destroyApp(false);
		notifyDestroyed();
	}
}