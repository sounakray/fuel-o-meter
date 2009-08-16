package sounakray.fuelometer.midlet;

import javax.microedition.lcdui.Alert;
import javax.microedition.lcdui.Command;
import javax.microedition.lcdui.CommandListener;
import javax.microedition.lcdui.Display;
import javax.microedition.lcdui.Displayable;
import javax.microedition.midlet.MIDlet;
import javax.microedition.midlet.MIDletStateChangeException;
import sounakray.fuelometer.forms.AbstractFuelOMeterScreen;
import sounakray.fuelometer.forms.FormAbout;
import sounakray.fuelometer.forms.FormAddNewFillUp;
import sounakray.fuelometer.forms.FormViewData;
import sounakray.fuelometer.forms.FormViewStatistics;
import sounakray.fuelometer.forms.ListMainMenu;
import sounakray.fuelometer.manager.FuelOMeterManager;

public final class FuelOMeter extends MIDlet implements CommandListener {
	private Display display;
	private AbstractFuelOMeterScreen currentForm;

	public final FuelOMeterManager manager;
	public final AbstractFuelOMeterScreen scrMainMenu;
	public final AbstractFuelOMeterScreen scrAddRec;
	public final AbstractFuelOMeterScreen scrAbout;
	public final AbstractFuelOMeterScreen scrViewData;
	public final AbstractFuelOMeterScreen scrViewStats;

	public FuelOMeter() {
		display = Display.getDisplay(this);
		manager = new FuelOMeterManager();

		scrMainMenu = new ListMainMenu(this);
		scrAddRec = new FormAddNewFillUp(this);
		scrViewData = new FormViewData(this);
		scrViewStats = new FormViewStatistics(this);
		scrAbout = new FormAbout(this);
	}

	protected void startApp() throws MIDletStateChangeException{
		setDisplay(scrMainMenu, null);
	}

	protected void pauseApp(){}

	protected void destroyApp(final boolean unconditional){}

	public void setDisplay(final AbstractFuelOMeterScreen form, final Alert alert){
		currentForm = form;
		form.refreshScreen();
		if(alert == null){
			display.setCurrent(currentForm.getScreen());
		}else{
			display.setCurrent(alert, currentForm.getScreen());
		}
	}

	public void commandAction(Command c, Displayable d){
		currentForm.executeCommand(c);
	}

	public void exitMIDlet(){
		destroyApp(false);
		notifyDestroyed();
	}
}