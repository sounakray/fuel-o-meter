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
	private static int[] mileageHistory;

	public GraphCanvas(final FuelOMeter midlet) {
		super(new Canvas() {
			final int margin = 10;

			/*
			 * (non-Javadoc)
			 * @see javax.microedition.lcdui.Canvas#paint(javax.microedition.lcdui.Graphics)
			 * @author Sounak Ray
			 */
			protected void paint(Graphics g){
				System.out.println("Paint: " + System.currentTimeMillis());
				setTitle("Graph");

				g.setColor(255, 255, 255);
				g.fillRect(0, 0, getWidth(), getHeight());

				final int height = getHeight() - 2 * margin, width = getWidth() - 2 * margin;
				if(mileageHistory == null){
					g.setColor(0, 0, 0);
					g.drawString("Insufficient Data!", 0, getHeight() / 2, Graphics.BASELINE | Graphics.LEFT);
				}else{
					final int len = mileageHistory.length;
					int sumMileage = 0, min, max = min = mileageHistory[0];

					for(int i = 0; i < len; i++){
						max = Math.max(max, mileageHistory[i]);
						min = Math.min(min, mileageHistory[i]);
						sumMileage += mileageHistory[i];
					}
					final int avgMileage = sumMileage / len;
					final int x = width / (len - 1);
					final int y = height / (max - min);

					int x1 = 0, x2, y1, y2;
					for(int i = 1; i < len; i++){
						x1 = (margin + x * (i - 1));
						y1 = (margin + y * (max - mileageHistory[i - 1]));
						x2 = x1 + x;
						y2 = (margin + y * (max - mileageHistory[i]));
						g.setColor(0, 255, 0);
						g.setStrokeStyle(Graphics.DOTTED);
						g.drawLine(x1, margin, x1, margin + height);

						g.setColor(0, 0, 0);
						g.drawString(String.valueOf(mileageHistory[i - 1]), x1, y1, Graphics.BOTTOM | Graphics.HCENTER);

						g.setColor(0, 0, 255);
						g.setStrokeStyle(Graphics.SOLID);
						g.drawLine(x1, y1, x2, y2);
						g.fillArc(x1, y1, 3, 3, 0, 360);
					}
					g.setColor(0, 255, 0);
					g.setStrokeStyle(Graphics.DOTTED);
					g.drawLine((x1 + x), margin, (x1 + x), margin + height);

					// Average
					g.setColor(0, 0, 0);
					g.setStrokeStyle(Graphics.DOTTED);
					g.drawLine(margin, (margin + y * (max - avgMileage)), (margin + width), (margin + y
							* (max - avgMileage)));
					g.drawString("Avg:" + avgMileage, 0, (margin + y * (max - avgMileage)), Graphics.TOP
							| Graphics.LEFT);

					// Last Mileage
					g.setColor(0, 0, 0);
					g.drawString(String.valueOf(mileageHistory[len - 1]), x1 + x, (margin + y
							* (max - mileageHistory[len - 1])), Graphics.BOTTOM | Graphics.HCENTER);
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
		// TODO: Test data to be removed.
		// int[] x = { 45, 50, 42, 57, 60, 55, 45, 48, 52 };
		System.out.println("load: " + System.currentTimeMillis());
		mileageHistory = new FuelOMeterManager().getMileageHistory();
		if(mileageHistory != null){
			((Canvas) screen).repaint();
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
