/**
 * FormViewStatistics.java
 * Fuel-O-Meter-02
 * @version %I%, %G%
 * Date Aug 16, 2009
 * © Sounak Ray
 * email: sounakray@gmail.com
 */
package sounakray.fuelometer.forms;

import javax.microedition.lcdui.Command;
import javax.microedition.lcdui.Form;
import javax.microedition.lcdui.TextField;
import sounakray.fuelometer.midlet.FuelOMeter;
import sounakray.fuelometer.model.StatisticsData;

/**
 * @author Sounak Ray
 */
public class FormViewStatistics extends AbstractFuelOMeterScreen {
	// TODO: Make Singleton!
	private final Command cmdMainMenu = new Command("Back", "Main Menu", Command.BACK, 0);
	private TextField txtStartDate, txtEndDate, txtAverageMileage;

	public FormViewStatistics(final FuelOMeter midlet) {
		super(new Form("Statistics"), midlet);

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
	public void refreshScreen(){
		final Form frmStats = (Form) screen;
		frmStats.deleteAll();

		final StatisticsData stats = midlet.manager.getStatistics();
		txtStartDate = new TextField("Start Date: ", stats.getStartDate(), 10, TextField.UNEDITABLE);
		txtEndDate = new TextField("End Date: ", stats.getEndDate(), 10, TextField.UNEDITABLE);
		txtAverageMileage = new TextField("Average Mileage: ", stats.getAverageMileage(), 6, TextField.UNEDITABLE);

		frmStats.append(txtStartDate);
		frmStats.append(txtEndDate);
		frmStats.append(txtAverageMileage);
	}
}
