package com.sounakray.fuelometer.midlet;

import javax.microedition.lcdui.Command;
import javax.microedition.lcdui.CommandListener;
import javax.microedition.lcdui.Display;
import javax.microedition.lcdui.Displayable;
import javax.microedition.midlet.MIDlet;
import javax.microedition.midlet.MIDletStateChangeException;

import com.sounakray.fuelometer.forms.AbstractForm;
import com.sounakray.fuelometer.forms.FormAddNewFillUp;
import com.sounakray.fuelometer.forms.FormMainMenu;

public final class Fuel_O_Meter extends MIDlet implements CommandListener {
	private Display display;

	public final AbstractForm frmMainMenu;
	public final AbstractForm frmAddRec;
	
//	public Command cmdLeft;
//	publicCommand cmdRight;

	public Fuel_O_Meter() {
		display = Display.getDisplay(this);
		frmMainMenu = new FormMainMenu(this);
		frmAddRec = new FormAddNewFillUp(this);
	}

	protected void startApp() throws MIDletStateChangeException {
		setDisplay(frmMainMenu);
	}

	protected void pauseApp() {
	}

	protected void destroyApp(final boolean unconditional) {
	}

	public void setDisplay(final AbstractForm form) {
		display.setCurrent(form);
	}

	public void commandAction(Command c, Displayable d) {
		if (display.getCurrent() instanceof AbstractForm) {
			((AbstractForm) display.getCurrent()).executeCommand(c);
		}
	}
	
	public void exitMIDlet(){
		destroyApp(false);
		notifyDestroyed();
	}
}
