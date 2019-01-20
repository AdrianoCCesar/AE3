package View;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.geom.Ellipse2D;
import java.util.ArrayList;
import java.util.Arrays;

import javax.swing.JComponent;

public class Graph extends JComponent implements MouseListener {

	private ArrayList<Ellipse2D> ellipseListG2D = new ArrayList<Ellipse2D>();
	private ArrayList<Double> minimumValues = new ArrayList<Double>();
	private ArrayList<Double> maximumValues = new ArrayList<Double>();
	private ArrayList<Double> storeCalculatedValues = new ArrayList<Double>();
	private View view = null;
	private int canvasHeight;
	private int selectionX;
	private int selectionY;
	private double xPosition;
	private double yPosition;
	private String zPrintPosition;
	private String xPrintPosition;
	private String yPrintPosition;
	private double[][] fullArray;

	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D gg = (Graphics2D) g;
		// Enable anti-aliasing and pure stroke to have better representation and look
		// cleared and easier to the eye
		gg.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		gg.setRenderingHint(RenderingHints.KEY_STROKE_CONTROL, RenderingHints.VALUE_STROKE_PURE);
		// Construct each dot/shape and draw it
		Ellipse2D.Double shape = new Ellipse2D.Double(getxPosition(), getyPosition(), 5, 5);
		gg.fill(shape);
		gg.setColor(Color.BLUE);
		gg.draw(shape);
		ellipseListG2D.add(shape);
	}// End of method

	/*
	 * This method will paint on the canvas the points
	 */
	public void paintTheGraph(View view, double[][] fullArray, int selectionX, int selectionY) {
		clearAllVariables();
		setFullArray(fullArray);
		setSelectionX(selectionX);
		setSelectionY(selectionY);
		setView(view);
		Canvas canvas = getView().canvas;
		setCanvasHeight(canvas.getHeight());
		Graphics g = canvas.getGraphics();
		getMaxMinValues(getFullArray());
		drawXYLines(g);
		flipTheCanvas(g);
		canvas.addMouseListener(this);

		// This for loop will paint the dots and resize them to fit within 500 x 500
		// size window
		for (int j = 0; j < getFullArray()[getSelectionX()].length; j++) {
			// Add +7 to each X-Y to compensate for the X Y LINES
			setxPosition((((getFullArray()[getSelectionX()][j] - minimumValues.get(getSelectionX()))
					/ (maximumValues.get(getSelectionX()) - minimumValues.get(getSelectionX()))) * 500) + 7);
			setyPosition((500 - ((getFullArray()[getSelectionY()][j] - minimumValues.get(getSelectionY()))
					/ (maximumValues.get(getSelectionY()) - minimumValues.get(getSelectionY()))) * 500) + 7);

			// Add and store the values to find them later upon printing in order to print
			// by adding we are creating a unique identification for each Ellipse the proper
			// X Y Z
			storeCalculatedValues.add(j, (getxPosition() + getyPosition()));
			paintComponent(g);
		}
	}

	/*
	 * Clear all variables to allow them to be re-used
	 */
	private void clearAllVariables() {
		ellipseListG2D = new ArrayList<Ellipse2D>();
		minimumValues = new ArrayList<Double>();
		maximumValues = new ArrayList<Double>();
		storeCalculatedValues = new ArrayList<Double>();
		view = null;
		canvasHeight = 0;
		selectionX = 0;
		selectionY = 0;
		xPosition = 0;
		yPosition = 0;
		zPrintPosition = "";
		xPrintPosition = "";
		yPrintPosition = "";
		fullArray = null;
	}

	/*
	 * This method will find the maximum and the minimum value of the csv
	 */
	public void getMaxMinValues(double[][] fullArray) {
		int columnCount = fullArray.length; // 3
		for (int i = 0; i < columnCount; i++) {
			int lengthOf1DArray = fullArray[i].length;
			double[] temporary = new double[lengthOf1DArray];

			// Copying array because it's passed by reference and when sorted, then it
			// breaks it From 0 of fullArray starting the index on 0 of TemporaryArray
			System.arraycopy(fullArray[i], 0, temporary, 0, lengthOf1DArray);
			Arrays.sort(temporary);

			minimumValues.add(temporary[0]);
			maximumValues.add(temporary[lengthOf1DArray - 1]);
		}
	}// End of method

	/*
	 * Mouseclick event listener that listens for mouse clicks within the canvas and
	 * then it checks for specified X,Y parameters to see if the Ellipse2D ArrayList
	 * has a point that's within those X,Y points in order to print its coordinates
	 */
	public void mouseClicked(MouseEvent e) {
		if (e.getButton() == 1) {
			// Fix the offset of Canvas rotation to the mouseEvent
			for (Ellipse2D ellipse : ellipseListG2D) {
				// Using an offset of 500-Y to match inverted Canvas
				if (ellipse.contains(e.getX(), 500 - e.getY())) {
					findPointInFullArray(ellipse);
					getView().detailsLabel.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 10));
					// setting the text to the details label as HTML
					getView().detailsLabel.setText("<HTML>X: " + (getxPrintPosition()) + " Y: " + (getyPrintPosition())
							+ " Z: " + (getzPrintPosition()) + "</HTML>");
					System.out.println("X: " + (getxPrintPosition()) + "\nY: " + (getyPrintPosition()) + "\nZ: "
							+ (getzPrintPosition()));
					System.out.println("----------");
				} // End of inner if
			} // End of for
		} // End of outer if
	}// End of action

	/*
	 * This method gets the ellipse and ADDS X and Y the same way as paintTheGraph,
	 * and then repeats over the saved value to find the index on which it is saved
	 * That index, is the same index where the X Y Z values are in the fullArray By
	 * utilizing this technique we can find the correct X Y Z values and print them
	 */
	private void findPointInFullArray(Ellipse2D ellipse) {
		for (int i = 0; i < storeCalculatedValues.size(); i++) {
			if ((ellipse.getX() + ellipse.getY()) == storeCalculatedValues.get(i)) {

				String xPrint = Double.toString(getFullArray()[getSelectionX()][i]);
				String yPrint = Double.toString(getFullArray()[getSelectionY()][i]);
				String zPrint = null;

				if (getSelectionX() != 0 && getSelectionY() != 0) {
					zPrint = Double.toString(getFullArray()[0][i]);
				} else if (getSelectionX() != 1 && getSelectionY() != 1) {
					zPrint = Double.toString(getFullArray()[1][i]);
				} else if (getSelectionX() != 2 && getSelectionY() != 2) {
					zPrint = Double.toString(getFullArray()[2][i]);
				}// End of inner if else

				xPrint = removeTrailingZeros(xPrint);
				yPrint = removeTrailingZeros(yPrint);
				zPrint = removeTrailingZeros(zPrint);

				setxPrintPosition(xPrint);
				setyPrintPosition(yPrint);
				setzPrintPosition(zPrint);
			}// End of outer if
		}// End of for
	}// End of method

	/*
	 * The Double trailing zeros 1.0 are removed(when you convert a double to
	 * string, it automatically adds .0 at the end of it)and this method allows us
	 * to remove them
	 */
	private String removeTrailingZeros(String input) {
		if (".0".equals(input.substring(input.length() - 2))) {
			input = input.substring(0, input.length() - 2);
		}
		return input;
	}

	/*
	 * By default and definition, the Canvas object/component has the starting 0,0
	 * point on top left of the rectangle. By utilizing translate we can flip it and
	 * scale it so that it starts from bottom left as expected.
	 */
	private void flipTheCanvas(Graphics g) {
		Graphics2D gg = (Graphics2D) g;
		gg.translate(0, getCanvasHeight() - 1);
		gg.scale(1, -1);
	}// End of method

	/*
	 * Drawing the X Y lines for the Graph
	 */
	private void drawXYLines(Graphics g) {
		// Draw the X and Y Axis
		g.setColor(Color.BLACK);
		g.drawLine(10, 500, 10, 0);// Y line
		g.drawLine(0, 490, 490, 490);// X line

		// Draw the arrows on X and Y Axis
		g.setColor(Color.BLUE);
		g.drawLine(0, 10, 10, 0);
		g.drawLine(20, 10, 10, 0);
		g.drawLine(490, 490, 480, 480);
		g.drawLine(490, 490, 480, 500);

		// Draw the points on X and Y Axis
		// X AXIS
		g.setFont(new Font("TimesRoman", Font.PLAIN, 9));
		g.setColor(Color.DARK_GRAY);
		g.drawLine(10, 400, 20, 400);
		g.drawString(getXLinePointOffset(100.0), 10, 400);
		g.drawLine(10, 300, 20, 300);
		g.drawString(getXLinePointOffset(200.0), 10, 300);
		g.drawLine(10, 200, 20, 200);
		g.drawString(getXLinePointOffset(300.0), 10, 200);
		g.drawLine(10, 100, 20, 100);
		g.drawString(getXLinePointOffset(400.0), 10, 100);

		// Y AXIS
		g.drawLine(400, 490, 400, 480);
		g.drawString(getYLinePointOffset(400.0), 400, 490);
		g.drawLine(300, 490, 300, 480);
		g.drawString(getYLinePointOffset(300.0), 300, 490);
		g.drawLine(200, 490, 200, 480);
		g.drawString(getYLinePointOffset(200.0), 200, 490);
		g.drawLine(100, 490, 100, 480);
		g.drawString(getYLinePointOffset(100.0), 100, 490);
	}

	private String getXLinePointOffset(Double inputNumber) {
		String offsetResult = Double.toString(Math.round((((inputNumber - minimumValues.get(getSelectionX()))
				/ (maximumValues.get(getSelectionX()) - minimumValues.get(getSelectionX()))) * 500) + 7));
		offsetResult = removeTrailingZeros(offsetResult);
		return "  " + offsetResult;
	}// End of method

	private String getYLinePointOffset(Double inputNumber) {
		String offsetResult = Double.toString(Math.round((500 - ((inputNumber - minimumValues.get(getSelectionY()))
				/ (maximumValues.get(getSelectionY()) - minimumValues.get(getSelectionY()))) * 500) + 7));

		offsetResult = removeTrailingZeros(offsetResult);
		return "  " + offsetResult;
	}// End of method

	/*
	 * Getters and Setters - START
	 */
	public double getxPosition() {
		return xPosition;
	}

	public void setxPosition(double test) {
		this.xPosition = test;
	}

	public double getyPosition() {
		return yPosition;
	}

	public void setyPosition(double yPosition) {
		this.yPosition = yPosition;
	}

	private int getCanvasHeight() {
		return canvasHeight;
	}

	private void setCanvasHeight(int canvasHeight) {
		this.canvasHeight = canvasHeight;
	}

	// Get the methods of View package
	private View getView() {
		return view;
	}

	private void setView(View view) {
		this.view = view;
	}

	public int getSelectionX() {
		return selectionX;
	}

	public void setSelectionX(int selectionX) {
		this.selectionX = selectionX;
	}

	public int getSelectionY() {
		return selectionY;
	}

	public void setSelectionY(int selectionY) {
		this.selectionY = selectionY;
	}

	public double[][] getFullArray() {
		return fullArray;
	}

	public void setFullArray(double[][] fullArray) {
		this.fullArray = fullArray;
	}

	public String getzPrintPosition() {
		return zPrintPosition;
	}

	public void setzPrintPosition(String zPrintPosition) {
		this.zPrintPosition = zPrintPosition;
	}

	public String getxPrintPosition() {
		return xPrintPosition;
	}

	public void setxPrintPosition(String xPrintPosition) {
		this.xPrintPosition = xPrintPosition;
	}

	public String getyPrintPosition() {
		return yPrintPosition;
	}

	public void setyPrintPosition(String yPrintPosition) {
		this.yPrintPosition = yPrintPosition;
	}

	/*
	 * Getters and Setters - END
	 */

	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mousePressed(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}
}
