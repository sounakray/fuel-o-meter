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
import sounakray.fuelometer.manager.FuelOMeterManager;
import sounakray.fuelometer.midlet.FuelOMeter;
import sounakray.fuelometer.model.StatisticsData;

/**
 * @author Sounak Ray
 */
public class FormViewStatistics extends AbstractFuelOMeterScreen {
	// TODO: Make Singleton!
	// private final Command cmdMainMenu = new Command("Back", "Main Menu", Command.BACK, 0);

	public FormViewStatistics(final FuelOMeter midlet) {
		super(new Form("Statistics"), midlet);
		screen.addCommand(FuelOMeterManager.CMD_MAIN_MENU);
	}

	/*
	 * (non-Javadoc)
	 * @see sounakray.fuelometer.forms.AbstractFuelOMeterScreen#refreshScreen()
	 * @author Sounak Ray
	 */
	public void loadScreen(){
		final StatisticsData stats = midlet.manager.getStatistics();
		final Form frmStats = (Form) screen;
		if(stats == null){
			frmStats.append("Not enough data available to generate statistics!");
		}else{
			frmStats.append("Report Range : " + stats.getStartDate() + " - " + stats.getEndDate() + " ("
					+ stats.getTotalDays() + ") days");
			frmStats.append("Last Mileage : " + stats.getLastMileage());
			frmStats.append("Average Mileage : " + stats.getAverageMileage());
			frmStats.append("Cost per distance : " + stats.getAvgCostPerDist());
			frmStats.append("Fuel per 100 Distance : " + stats.getFuelPer100Dist());
			frmStats.append("Total distance : " + stats.getTotalDistance());
			frmStats.append("Total fuel consumed : " + stats.getTotalFuelConsumed());
			frmStats.append("Total money consumed : " + stats.getTotalMoneyCnsmd());
			frmStats.append("Total fuel purchased : " + stats.getTotalFuel());
			frmStats.append("Total money spent : " + stats.getTotalMoney());
		}
	}

	/*
	 * (non-Javadoc)
	 * @see sounakray.fuelometer.forms.AbstractFuelOMeterScreen#unloadScreen()
	 * @author Sounak Ray
	 */
	public void unloadScreen(){
		((Form) screen).deleteAll();
	}

	/*
	 * (non-Javadoc)
	 * @see com.sounakray.fuelometer.forms.AbstractForm#handleAction(javax.microedition .lcdui.Command)
	 */
	public void executeCommand(final Command command){
		if(command == FuelOMeterManager.CMD_MAIN_MENU){
			midlet.setDisplay(midlet.scrMainMenu, null);
		}
	}
}
