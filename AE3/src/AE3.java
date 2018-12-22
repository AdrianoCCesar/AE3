import java.awt.BorderLayout;
import javax.swing.JFrame;

public class AE3 extends JFrame
{
   private NorthPanel northPanel;
   private SouthPanel southPanel;
   private CentrePanel centrePanel;

   public AE3()
   {
      this.setTitle("Bond Trade");
      setLayout(new BorderLayout());
     //centrePanel();// center Panel should be the first
      southPanel();// let's create south panel first
      northPanel();// after that we will pass south panel's reference to north

      this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      this.setSize(600, 800);
      this.setLocationRelativeTo(null);
      this.setVisible(true);
   }

   public void northPanel()
   {
      northPanel = new NorthPanel(southPanel);
      add(northPanel, BorderLayout.NORTH);
   }

   public void southPanel()
   {
      southPanel = new SouthPanel(centrePanel);
      add(southPanel, BorderLayout.SOUTH);
   }

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