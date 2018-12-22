import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import javax.swing.BorderFactory;
import javax.swing.JComboBox;
import javax.swing.JPanel;
//import com.sun.prism.Graphics;
import javax.swing.JTextArea;

public class SouthPanel extends JPanel
{
   private NorthPanel northPanel;
   private CentrePanel centrePanel;
   private String filePath;
   private BondTrade bondTrade;
   private String[] items;
   private JTextArea detailInformation;
   private JComboBox<String> itemsBoxX, itemsBoxY;

   
   public SouthPanel(CentrePanel centrePanel)
   {
      this.centrePanel = centrePanel;
      bondTrade = new BondTrade(filePath);
      // Layout for x combo box
      itemsBoxX = new JComboBox<String>();
      itemsBoxX.setBorder(BorderFactory.createTitledBorder("X"));
      add(itemsBoxX);
      // Layout for x combo box
      itemsBoxY = new JComboBox<String>();
      itemsBoxY.setBorder(BorderFactory.createTitledBorder("Y"));
      add(itemsBoxY);
      
      // creates and adds detail information about the position of the
      // selected variable

      detailInformation = new JTextArea(6, 15);
      detailInformation.setEditable(false);
      detailInformation
            .setBorder(BorderFactory.createTitledBorder("Bond Information"));

      add(detailInformation);
   }
   //sets the file path from the north panel
   public void setFilePath(String filePath) {
      this.filePath = filePath;
   }
   //populate values in boxes
   public void populateItemBoxes()
   {
      itemsBoxX.removeAllItems();
      itemsBoxY.removeAllItems();
      items = bondTrade.getFieldNames(filePath);
      for (String item : items)
      {
         itemsBoxX.addItem(item);
         itemsBoxY.addItem(item);
      }
      itemsBoxX.setSelectedIndex(0);
      itemsBoxY.setSelectedIndex(1);
   }
}
