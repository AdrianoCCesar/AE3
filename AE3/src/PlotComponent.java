import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Shape;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.Ellipse2D;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JComponent;

public class PlotComponent extends JComponent
{
   private static final long serialVersionUID = 1L;
   private ArrayList<Point> points = new ArrayList<Point>();
   private ArrayList<Shape> shapes = new ArrayList<Shape>();
   private List<Double> YScores = new ArrayList<>();
   private List<Double> XScores = new ArrayList<>();
   private SouthPanel southPanel;

   public PlotComponent()
   {
      HandlerClass handler = new HandlerClass();
      this.addMouseListener(handler);
      this.setPreferredSize(new Dimension(300,300));
      System.out.println(this.getWidth());
      System.out.println(this.getHeight());
   }

   // method to create the south panel
   public void setSouthPanel(SouthPanel southPanel)
   {
      this.southPanel = southPanel;
   }

   public void paintComponent(Graphics g)
   {
      Graphics2D g2 = (Graphics2D) g;
      // sets colour
      g2.setColor(Color.BLUE);
      // defines new width and height
      int width = this.getWidth() - 5;
      int height = this.getHeight() - 15;
      double maxX = getMaxX();
      double maxY = getMaxY();
      shapes = new ArrayList<>();// resetting shapes
      // creates the dots
      for (Point p : points)
      {
         // adapts the length of the points
         double x = (p.getX() / maxX) * width;
         double y = (p.getY() / maxY) * height;
         y = height - y;
         double r = p.getRadius();
         // creates a point
         Ellipse2D.Double e = new Ellipse2D.Double(x, y, r, r);
         // add shapes
         shapes.add(e);
         // fill in the ellipse
         g2.fill(e);
         // adds values to y and x arrays for hatches
         XScores.add(x);
         YScores.add(y);
      }

      // s
      g.setColor(Color.BLACK);
      
      g.drawRect((int)this.getMinYScore(),(int) this.getMinXScore(), this.getWidth(), this.getHeight());
      
      
//      g.drawLine((int)getMinXScore(), (int) getMaxYScore(),(int)getMaxXScore(), (int)getMaxYScore()); //x axis
//      g.drawLine((int)getMinXScore(), (int) getMinYScore(), (int)getMinXScore(), (int)getMaxYScore());//y axis
//      g.drawLine(0,10,(int)getMaxXScore(),10);//top line
//      g.drawLine(0,(int)getMaxYScore(),0,0);//far right line

//      g.setFont(new Font("TimesRoman", Font.PLAIN, 9));
//      g.setColor(Color.DARK_GRAY);
//      g.drawLine(10, 400, 20, 400);
//
//      g.drawString("100", 10, 400);
//      g.drawLine(10, 300, 20, 300);
//      g.drawString("200.0", 10, 300);
//      g.drawLine(10, 200, 20, 200);
//      g.drawString("300.0", 10, 200);
//      g.drawLine(10, 100, 20, 100);
//      g.drawString("400.0", 10, 100);
      
   }
  
   // handler event to listen the index of the arraylist shapes the
   // point is at
   public class HandlerClass extends MouseAdapter
   {
      @Override
      public void mousePressed(MouseEvent e)
      {
         int index = -1;
         for (int i = 0; i < shapes.size(); i++)// Shape s : shapes
         {
            if (shapes.get(i).contains(e.getPoint()))
            {
               index = i;
               break;
            }
         }
         // if value is different from the initial, then the variable is passed
         // to the southpanel
         if (index != -1)
         {
            southPanel.bondDetailInfo(index);
         }
      }
   }

   // method to add a point
   public void addPoint(Point p)
   {
      points.add(p);
   }

   // method to set values for values to zero in all arrays
   public void resetPoints()
   {
      points = new ArrayList<Point>();
      YScores = new ArrayList<Double>();
      XScores = new ArrayList<Double>();
   }

   // method to print values of the arrays
   public void printPoints()
   {
      System.out.println(points.toString());
      System.out.println(YScores.toString());
      System.out.println(XScores.toString());
   }

   // method to get the max x value of the points arrayList
   private double getMaxX()
   {
      double maxX = 0;
      for (Point point : points)
      {
         if (point.getX() > maxX)
         {
            maxX = point.getX();
         }
      }
      return maxX;
   }

   // method to get the max y value of the points arrayList
   private double getMaxY()
   {
      double maxY = 0;
      for (Point point : points)
      {
         if (point.getY() > maxY)
         {
            maxY = point.getY();
         }
      }
      return maxY;
   }

   // method to get the minimum value only Y values array list
   private double getMinYScore()
   {
      double minScore = Double.MAX_VALUE;
      for (Double score : YScores)
      {
         minScore = Math.min(minScore, score);
      }
      return minScore;
   }

   // method to get the minimum value only X values array list
   private double getMaxYScore()
   {
      double maxScore = Double.MIN_VALUE;
      for (Double score : YScores)
      {
         maxScore = Math.max(maxScore, score);
      }
      return maxScore;
   }
   private double getMinXScore()
   {
      double minScore = Double.MIN_VALUE;
      for (Double score : XScores)
      {
         minScore = Math.min(minScore, score);
      }     
      return minScore;
   }

   // method to get the minimum value only X values array list
   private double getMaxXScore()
   {
      double maxScore = Double.MAX_VALUE;
      for (Double score : XScores)
      {
         maxScore = Math.max(maxScore, score);
      }
      return maxScore;
   }

   // method to return shapes
   public ArrayList<Shape> getShapes()
   {
      return shapes;
   }
}
