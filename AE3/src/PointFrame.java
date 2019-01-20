import java.util.ArrayList;
import javax.swing.JPanel;

public class PointFrame extends JPanel
{
   /**
    * 
    */
   private static final long serialVersionUID = 1L;
   private PlotComponent plotComponent;
   private ArrayList<Double> xValues;
   private ArrayList<Double> yValues;

   public PointFrame()
   {
      plotComponent = new PlotComponent();
      this.add(plotComponent);
      this.setSize(500, 500);
      this.setVisible(true);
   }
   //paints a new graph when the combo box is changed
   public void resetGraph(ArrayList<Double> xValues, ArrayList<Double> yValues)
   {
      this.xValues = xValues;
      this.yValues = yValues;
      for (int i = 0; i < yValues.size(); i++)
      {
         double radius = 6.0;
         Point p = new Point(xValues.get(i), yValues.get(i), radius);
         plotComponent.addPoint(p);
      }
      plotComponent.repaint();
   }
}
