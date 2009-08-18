/**
 * GraphCanvas.java
 * Fuel-O-Meter-02
 * Date Aug 19, 2009
 * © Sounak Ray
 * email: sounakray@gmail.com
 */
package sounakray.fuelometer.forms;

import javax.microedition.lcdui.Canvas;
import javax.microedition.lcdui.Command;
import javax.microedition.lcdui.Graphics;
import sounakray.fuelometer.manager.FuelOMeterManager;
import sounakray.fuelometer.midlet.FuelOMeter;

/**
 * Class Description:
 * @author Sounak Ray
 * @since Aug 19, 2009
 */
public class GraphCanvas extends AbstractFuelOMeterScreen {

	private final Command cmdMainMenu = new Command("Back", "Main Menu", Command.BACK, 0);

	public GraphCanvas(final FuelOMeter midlet) {
		super(new Canvas() {
			final int margin = 10;

			protected void paint(Graphics g){
				setTitle("Graph");

				final int height = getHeight() - 2 * margin, width = getWidth() - 2 * margin;
				final int[] mileageHistory = new FuelOMeterManager().getMileageHistory();
				int min, max = min = mileageHistory[0];
				for(int i = 0; i < mileageHistory.length; i++){
					max = Math.max(max, mileageHistory[i]);
					min = Math.min(min, mileageHistory[i]);
				}
				int x = width / mileageHistory.length;
				int y = height / (max - min);

				g.setColor(255, 255, 255);
				for(int i = 1; i < mileageHistory.length; i++){
					g.drawLine((margin + x * (i - 1)), (margin + y * (max - mileageHistory[i - 1])), (margin + x * i),
						(margin + y * (max - mileageHistory[i])));
				}
			}
		}, midlet);
		screen.addCommand(cmdMainMenu);
	}

	/*
	 * (non-Javadoc)
	 * @see sounakray.fuelometer.forms.AbstractFuelOMeterScreen#executeCommand(javax.microedition.lcdui.Command)
	 * @author Sounak Ray
	 */
	public void executeCommand(Command command){
		if(command == cmdMainMenu){
			midlet.setDisplay(midlet.scrMainMenu, null);
		}
	}

	/*
	 * (non-Javadoc)
	 * @see sounakray.fuelometer.forms.AbstractFuelOMeterScreen#loadScreen()
	 * @author Sounak Ray
	 */
	public void loadScreen(){
		((Canvas) screen).repaint();
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
