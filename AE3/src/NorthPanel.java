import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.filechooser.FileSystemView;

public class NorthPanel extends JPanel
{
   /**
    * 
    */
   private static final long serialVersionUID = 1L;
   private JButton openBtn, quitBtn;
   private JTextArea fileName;
   private String filePath;
   private SouthPanel southPanel;

   public NorthPanel(SouthPanel southPanel)
   {
      this.southPanel = southPanel;

      // Open Button
      openBtn = new JButton("Open");
      openBtn.addActionListener(new ActionListener()
      {
         public void actionPerformed(ActionEvent e) throws NullPointerException
         {
            JFileChooser fc = new JFileChooser(
                  FileSystemView.getFileSystemView());
            
            // sets the file extension to be shown only .csv files
            FileNameExtensionFilter filter = new FileNameExtensionFilter(
                  "*.csv", "csv");
            fc.setAcceptAllFileFilterUsed(false);// only csv files are accepted
            fc.setFileFilter(filter);
            
            // sets the directory to open the JFileChooser
            fc.setCurrentDirectory(new File("../AE3/src/"));
            int returnVal = fc.showOpenDialog(new JFrame());

            // Gets the path and the name of the file chosen by the user
            if (returnVal == JFileChooser.APPROVE_OPTION)
            {
               filePath = fc.getSelectedFile().getAbsolutePath();
               fileName.setText(fc.getSelectedFile().getName().toString());
            }
            
            //exception if no file is chosen
            try {
            // Giving information about the file path to the south panel
            southPanel.readFile(filePath);
            }
            catch(NullPointerException e2) {
               System.err.println("No file was chosen.");
            }

            // populating the boxes with items
            southPanel.populateItemBoxes();
         }
      });
      // Text area for file name
      fileName = new JTextArea("No file selected", 1, 20);
      fileName.setEditable(false);

      // Quit Button
      quitBtn = new JButton("Quit");
      quitBtn.addActionListener(new ActionListener()
      {
         public void actionPerformed(ActionEvent e)
         {
            System.exit(0);
         }
      });
      
      // adding buttons
      add(openBtn);
      add(fileName);
      add(quitBtn);
   }
}
