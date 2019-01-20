import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class BondTrade
{
   private String filePath;

   public BondTrade(String filePath)
   {
      this.filePath = filePath;
   }
   //method to return field names, it reads only the first line, where the field names will be located and stores them in an array
   public String[] getFieldNames(String filePath)
   {
      File file = new File(filePath);
      String[] values = null;
      try
      {
         Scanner inputStream = new Scanner(file);
         String line = inputStream.nextLine();      
         values = line.split(",");        
         inputStream.close();
      }
      catch (FileNotFoundException e)
      {
         e.printStackTrace();
      }
      return values;
   }
   //method to return the values in the first column after field names and store them in an arraylist
   public ArrayList<Double> getFirstCol(String filePath)
   {
      File file = new File(filePath);
      ArrayList<Double> parsedValues = new ArrayList<Double>();
      try
      {
         Scanner inputStream = new Scanner(file);
         inputStream.next();
         while (inputStream.hasNext())
         {
            String data = inputStream.next();
            String[] values = data.split(",");
            parsedValues.add(Double.parseDouble(values[0]));
         }
         inputStream.close();
      }
      catch (FileNotFoundException e)
      {
         e.printStackTrace();
      }
      return parsedValues;
   }

   //method to return the values in the second column after field names
   public ArrayList<Double> getSecondCol(String filePath)
   {
      File file = new File(filePath);
      ArrayList<Double> parsedValues = new ArrayList<Double>();
      try
      {
         Scanner inputStream = new Scanner(file);
         inputStream.next();
         while (inputStream.hasNext())
         {
            String data = inputStream.next();
            String[] values = data.split(",");
            parsedValues.add(Double.parseDouble(values[1]));
         }
         inputStream.close();
      }
      catch (FileNotFoundException e)
      {
         e.printStackTrace();
      }
      return parsedValues;
   }
   //method to return the values in the third column after field names
   public ArrayList<Double> getThirdCol(String filePath)
   {
      File file = new File(filePath);
      ArrayList<Double> parsedValues = new ArrayList<Double>();
      try
      {
         Scanner inputStream = new Scanner(file);
         inputStream.next();
         while (inputStream.hasNext())
         {
            String data = inputStream.next();
            String[] values = data.split(",");
            parsedValues.add(Double.parseDouble(values[2]));
         }
         inputStream.close();
      }
      catch (FileNotFoundException e)
      {
         e.printStackTrace();
      }
      return parsedValues;
   }  
}
