/**
 * FuelOMeter.java
 * Fuel-O-Meter-02
 * @version %I%, %G%
 * Date Aug 15, 2009
 * © Sounak Ray
 * email: sounakray@gmail.com
 */
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
import sounakray.fuelometer.forms.FormSetup;
import sounakray.fuelometer.forms.FormViewData;
import sounakray.fuelometer.forms.FormViewStatistics;
import sounakray.fuelometer.forms.GraphCanvas;
import sounakray.fuelometer.forms.ListMainMenu;
import sounakray.fuelometer.manager.FuelOMeterManager;

public final class FuelOMeter extends MIDlet implements CommandListener {
	private Display display;
	private AbstractFuelOMeterScreen currentScreen;

	public final FuelOMeterManager manager;
	public final AbstractFuelOMeterScreen scrMainMenu;
	public final AbstractFuelOMeterScreen scrAddRec;
	public final AbstractFuelOMeterScreen scrAbout;
	public final AbstractFuelOMeterScreen scrViewData;
	public final AbstractFuelOMeterScreen scrViewStats;
	public final AbstractFuelOMeterScreen scrGraph;
	public final AbstractFuelOMeterScreen scrSetup;

	/**
	 * Constructor Description: Default constructor for the MIDlet. Sets the
	 * display, and instantiates the manager.
	 * 
	 * @author Sounak Ray
	 * @since Aug 16, 2009
	 */
	public FuelOMeter() {
		display = Display.getDisplay(this);
		manager = new FuelOMeterManager();

		scrMainMenu = new ListMainMenu(this);
		scrAddRec = new FormAddNewFillUp(this);
		scrViewData = new FormViewData(this);
		scrViewStats = new FormViewStatistics(this);
		scrGraph = new GraphCanvas(this);
		scrSetup = new FormSetup(this);
		scrAbout = new FormAbout(this);
	}

	/**
	 * Method Description: Displays the screen provided, after showing the alert
	 * if it is not null. This method also invokes the
	 * {@link AbstractFuelOMeterScreen#loadScreen() loadScreen()} and
	 * {@link AbstractFuelOMeterScreen#unloadScreen() unloadScreen()} method on
	 * the next and the current screen respectively.
	 * 
	 * @param screen
	 *            An AbstractFuelOMeterScreen implementation that will be
	 *            displayed as the current screen.
	 * @param alert
	 *            An alert that is to be shown before showing the screen
	 *            mentioned, if it is not null.
	 * @author Sounak Ray
	 * @since Aug 16, 2009
	 * @see AbstractFuelOMeterScreen#loadScreen()
	 * @see AbstractFuelOMeterScreen#unloadScreen()
	 */
	public void setDisplay(final AbstractFuelOMeterScreen screen,
			final Alert alert) {
		if (currentScreen != null) {
			currentScreen.unloadScreen();
		}
		currentScreen = screen;
		currentScreen.loadScreen();
		if (alert == null) {
			display.setCurrent(currentScreen.getScreen());
		} else {
			display.setCurrent(alert, currentScreen.getScreen());
		}
	}

	/**
	 * Method Description: Shuts down the MIDlet application.
	 * 
	 * @author Sounak Ray
	 * @since Aug 16, 2009
	 */
	public void exitMIDlet() {
		destroyApp(false);
		notifyDestroyed();
	}

	// /////////////////////////////////// MIDlet API Methods
	// //////////////////////////////////////
	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.microedition.midlet.MIDlet#startApp()
	 * 
	 * @author Sounak Ray
	 */
	protected void startApp() throws MIDletStateChangeException {
		setDisplay(scrMainMenu, null);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.microedition.midlet.MIDlet#pauseApp()
	 * 
	 * @author Sounak Ray
	 */
	protected void pauseApp() {
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.microedition.midlet.MIDlet#destroyApp(boolean)
	 * 
	 * @author Sounak Ray
	 */
	protected void destroyApp(final boolean unconditional) {
	}

	// /////////////////////////////////// Listener/Handler Methods
	// //////////////////////////////////////
	/**
	 * Method Description: This method simply delegates the function to the
	 * current screen class.
	 * 
	 * @param command
	 * @param displayable
	 * @see javax.microedition.lcdui.CommandListener#commandAction(javax.microedition.lcdui.Command,
	 *      javax.microedition.lcdui.Displayable)
	 * @see AbstractFuelOMeterScreen#executeCommand(Command)
	 * @author Sounak Ray
	 */
	public void commandAction(final Command command, Displayable displayable) {
		currentScreen.executeCommand(command);
	}
}