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
import javax.microedition.lcdui.Font;
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

	/**
	 * Constructor Description:
	 * @param midlet
	 * @author Sounak Ray
	 * @since Aug 19, 2009
	 */
	public GraphCanvas(final FuelOMeter midlet) {
		super(new Canvas() {
			private static final int MARGIN = 10;
			final int numEntries = getWidth() < 240 ? 5 : 10;
			private static final int DOT_DIAMTR = 6;
			private static final int DOT_RADIUS = DOT_DIAMTR / 2;

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

				final int height = getHeight() - 2 * MARGIN, width = getWidth() - 2 * MARGIN;
				if(mileageHistory == null){
					g.setColor(0, 0, 0);
					g.drawString("Insufficient Data!", 0, getHeight() / 2, Graphics.BASELINE | Graphics.LEFT);
				}else{
					final int avgMileage = mileageHistory[0];
					final int minIndex = Math.max(1, mileageHistory.length - numEntries);
					final int len = mileageHistory.length - minIndex;
					int min, max = min = avgMileage;

					for(int i = minIndex; i < mileageHistory.length; i++){
						max = Math.max(max, mileageHistory[i]);
						min = Math.min(min, mileageHistory[i]);
						// sumMileage += mileageHistory[i];
					}
					final int x = width / (len - 1);
					final int y = height / (max - min);

					g.setFont(Font.getFont(Font.FACE_PROPORTIONAL, Font.STYLE_PLAIN, Font.SIZE_SMALL));

					int x1 = MARGIN - x, x2, y1, y2;
					for(int i = minIndex + 1; i < mileageHistory.length; i++){
						x1 += x;
						y1 = (MARGIN + y * (max - mileageHistory[i - 1]));
						x2 = x1 + x;
						y2 = (MARGIN + y * (max - mileageHistory[i]));
						g.setColor(180, 180, 180);
						g.setStrokeStyle(Graphics.DOTTED);
						g.drawLine(x1, MARGIN, x1, MARGIN + height);

						g.setColor(0, 0, 0);
						g.drawString(String.valueOf(mileageHistory[i - 1]), x1 + DOT_RADIUS, y1, Graphics.TOP
								| Graphics.LEFT);

						g.setColor(0, 0, 255);
						g.setStrokeStyle(Graphics.SOLID);
						g.drawLine(x1, y1, x2, y2);
						g.fillArc(x1 - DOT_RADIUS, y1 - DOT_RADIUS, DOT_DIAMTR, DOT_DIAMTR, 0, 360);
					}
					x1 += x;
					g.setColor(180, 180, 180);
					g.setStrokeStyle(Graphics.DOTTED);
					g.drawLine((x1), MARGIN, (x1), MARGIN + height);

					// Average
					g.setFont(Font.getFont(Font.FACE_SYSTEM, Font.STYLE_ITALIC, Font.SIZE_MEDIUM));
					g.setColor(0, 0, 0);
					g.setStrokeStyle(Graphics.DOTTED);
					g.drawLine(MARGIN / 2, (MARGIN + y * (max - avgMileage)), (MARGIN * 3 / 2 + width), (MARGIN + y
							* (max - avgMileage)));
					g.drawString("Avg:" + avgMileage, 0, (MARGIN + y * (max - avgMileage)), Graphics.TOP
							| Graphics.LEFT);

					// Last Mileage
					g.setFont(Font.getFont(Font.FACE_PROPORTIONAL, Font.STYLE_BOLD, Font.SIZE_MEDIUM));
					if(avgMileage < mileageHistory[mileageHistory.length - 1]){
						g.setColor(0, 255, 0);
					}else{
						g.setColor(255, 0, 0);
					}
					g.drawString(String.valueOf(mileageHistory[mileageHistory.length - 1]), x1 - DOT_RADIUS,
						(MARGIN + y * (max - mileageHistory[mileageHistory.length - 1])), Graphics.TOP
								| Graphics.HCENTER);
					g.fillArc(x1 - DOT_RADIUS, (MARGIN + y * (max - mileageHistory[mileageHistory.length - 1]))
							- DOT_RADIUS, DOT_DIAMTR, DOT_DIAMTR, 0, 360);
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
		// mileageHistory = new int[] { 53, 50, 42, 57, 60, 55, 45, 48, 52, 55, 58, 58 };
		mileageHistory = FuelOMeterManager.INSTANCE.getMileageHistory();
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
