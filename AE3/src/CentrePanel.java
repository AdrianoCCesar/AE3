import java.util.ArrayList;
import javax.swing.JPanel;

public class CentrePanel extends JPanel
{
   private NorthPanel northPanel;
   private SouthPanel southPanel;
   private String filePath;
   private BondTrade bondTrade;
   private ArrayList<Double> firstCol;
   private ArrayList<Integer> secondCol;
   private ArrayList<Integer> thirdCol;
 
   public CentrePanel(SouthPanel southPanel)
   {
      this.southPanel = southPanel;
      bondTrade = new BondTrade(filePath);
      
//      firstCol = bondTrade.getFirstCol(filePath);
//      secondCol = bondTrade.getSecondCol(filePath);
//      thirdCol = bondTrade.getThirdCol(filePath);

   }

   public void setFilePath(String filePath)
   {
      this.filePath = filePath;
   }
}