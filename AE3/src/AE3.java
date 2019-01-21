import java.awt.BorderLayout;
import javax.swing.JFrame;

public class AE3 extends JFrame
{
   private static final long serialVersionUID = 1L;
   private NorthPanel northPanel;
   private SouthPanel southPanel;
   private CentrePanel centrePanel;

   public AE3()
   {  
      //layout setting
      this.setTitle("Bond Trade");
      setLayout(new BorderLayout());
      centrePanel();
      southPanel();
      northPanel();
      //this is done because of the needed sequence to create the panels, so that there are no nullpoint exceptions
      //the centrepanel needs to set the south panel so that the information of an individual bond is passed to the south panel
      centrePanel.setSouthPanel(southPanel);
      

      this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      this.setSize(550, 600);
      this.setLocationRelativeTo(null);
      this.setVisible(true);
   }
   //creates a panel in the north section
   public void northPanel()
   {
      northPanel = new NorthPanel(southPanel);
      add(northPanel, BorderLayout.NORTH);
   }
   //creates a panel in the centre section
   public void southPanel()
   {
      southPanel = new SouthPanel(centrePanel);
      add(southPanel, BorderLayout.SOUTH);
   }
   //creates a panel in the centre section
   public void centrePanel()
   {
      centrePanel = new CentrePanel(southPanel);
      add(centrePanel, BorderLayout.CENTER);
   }

   public static void main(String[] args)
   {
      new AE3();
   }

}