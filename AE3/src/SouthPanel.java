import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.BorderFactory;
import javax.swing.JComboBox;
import javax.swing.JPanel;
import javax.swing.JTextArea;

public class SouthPanel extends JPanel
{
   /**
    * 
    */
   private static final long serialVersionUID = 1L;
   private CentrePanel centrePanel;
   private String filePath;
   private BondTrade bondTrade;
   private String[] items;
   private JTextArea detailInformation;
   private JComboBox<String> itemsBoxX, itemsBoxY;
   private ComboBoxesListener comboBoxesListener;
   private ArrayList<Double> firstCol;
   private ArrayList<Double> secCol;
   private ArrayList<Double> thirdCol;

   public SouthPanel(CentrePanel centrePanel)
   {
      comboBoxesListener = new ComboBoxesListener();
      this.centrePanel = centrePanel;
      bondTrade = new BondTrade(filePath);
      // Layout for x combo box
      itemsBoxX = new JComboBox<String>();
      itemsBoxX.setBorder(BorderFactory.createTitledBorder("X"));
      itemsBoxX.addActionListener(comboBoxesListener);
      add(itemsBoxX);
      // Layout for x combo box
      itemsBoxY = new JComboBox<String>();
      itemsBoxY.setBorder(BorderFactory.createTitledBorder("Y"));
      itemsBoxY.addActionListener(comboBoxesListener);
      add(itemsBoxY);

      // creates and adds detail information about the position of the
      // selected point
      detailInformation = new JTextArea(6, 15);
      detailInformation.setEditable(false);
      detailInformation
            .setBorder(BorderFactory.createTitledBorder("Bond Information"));
      add(detailInformation);

   }

   // populate values in boxes
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

   // action listener for different possible combinations of combo boxes
   private class ComboBoxesListener implements ActionListener
   {
      @Override
      public void actionPerformed(ActionEvent e)
      {
         int indexX = itemsBoxX.getSelectedIndex();
         int indexY = itemsBoxY.getSelectedIndex();
         if (indexX == 0 && indexY == 0)
         {
            centrePanel.resetGraph(firstCol, firstCol);
         }
         else if (indexX == 0 && indexY == 1)
         {
            centrePanel.resetGraph(firstCol, secCol);
         }
         else if (indexX == 0 && indexY == 2)
         {
            centrePanel.resetGraph(firstCol, thirdCol);
         }
         else if (indexX == 1 && indexY == 0)
         {
            centrePanel.resetGraph(secCol, firstCol);
         }
         else if (indexX == 1 && indexY == 1)
         {
            centrePanel.resetGraph(secCol, secCol);
         }
         else if (indexX == 1 && indexY == 2)
         {
            centrePanel.resetGraph(secCol, thirdCol);
         }
         else if (indexX == 2 && indexY == 0)
         {
            centrePanel.resetGraph(thirdCol, firstCol);
         }
         else if (indexX == 2 && indexY == 1)
         {
            centrePanel.resetGraph(thirdCol, secCol);
         }
         else if (indexX == 2 && indexY == 2)
         {
            centrePanel.resetGraph(thirdCol, thirdCol);
         }
      }
   }

   // method to read the values from each column
   public void readFile(String filePath)
   {
      this.filePath = filePath;
      firstCol = bondTrade.getFirstCol(filePath);
      secCol = bondTrade.getSecondCol(filePath);
      thirdCol = bondTrade.getThirdCol(filePath);
   }
   //method to show the information about an individual bond
   public void bondDetailInfo(int i)
   {  
      detailInformation.setText("");
      detailInformation.append("\nBond: " + i + "\n");
      detailInformation.append(items[0] + ": " + firstCol.get(i) + "\n");
      detailInformation.append(items[1] + ": " + secCol.get(i).intValue() + "\n");
      detailInformation.append(items[2] + ": " + thirdCol.get(i).intValue() + "\n");
   }
}
