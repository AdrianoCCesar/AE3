import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.util.ArrayList;
import javax.swing.BorderFactory;
import javax.swing.JComponent;
import javax.swing.JPanel;
import javax.swing.border.Border;

public class CentrePanel extends JComponent
{
   private static final long serialVersionUID = 1L;
   private PlotComponent plotComponent;
   private ArrayList<Double> xValues;
   private ArrayList<Double> yValues;
   private Graphics2D g;
   private SouthPanel southPanel;
//   private JPanel graphPanel;

   public CentrePanel(SouthPanel southPanel)
   {
      //initializing of south panel
      this.southPanel = southPanel;
      //layout
      this.setLayout(new BorderLayout());
      this.setSize(500, 500);
      this.setVisible(true);
      Border inner = BorderFactory.createLineBorder(Color.BLACK);
      Border outer = BorderFactory.createEmptyBorder(10, 10, 10, 10);
      this.setBorder(BorderFactory.createCompoundBorder(inner,outer));
      // Creating border for graph
//      javax.swing.border.Border border = BorderFactory.createEmptyBorder(20, 20, 20, 20);
//      this.setBorder(border);
      // Creating a plot component(graph) and adding it to the this panel
      plotComponent = new PlotComponent();
//      graphPanel = new JPanel();
//      graphPanel.setSize(new Dimension(400, 400));
//      graphPanel.add(plotComponent);
      
      this.add(plotComponent, BorderLayout.CENTER);
   }
   //method to set south panel, has to be done because centre and south need to exist at the same time
   public void setSouthPanel(SouthPanel southPanel)
   {
      this.southPanel = southPanel;
      this.plotComponent.setSouthPanel(southPanel);
   }
   // method that resets the graph and repaints when there is a change in the
   // combo boxes
   public void resetGraph(ArrayList<Double> xValues, ArrayList<Double> yValues)
   {
      Graphics2D g2 = (Graphics2D) g;
      this.xValues = xValues;
      this.yValues = yValues;
      plotComponent.resetPoints();//reset points when there is change in axis values
      
      for (int i = 0; i < xValues.size(); i++)
      {
         double radius = 6.0;
         Point p = new Point(xValues.get(i), yValues.get(i), radius);
         plotComponent.addPoint(p);
      }
      plotComponent.repaint();//repaints graph when there is change in axis values
   }
   
}