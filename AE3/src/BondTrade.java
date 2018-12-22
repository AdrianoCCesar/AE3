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

   public ArrayList<Integer> getSecondCol(String filePath)
   {
      File file = new File(filePath);
      ArrayList<Integer> parsedValues = new ArrayList<Integer>();
      try
      {
         Scanner inputStream = new Scanner(file);
         inputStream.next();
         while (inputStream.hasNext())
         {
            String data = inputStream.next();
            String[] values = data.split(",");
            parsedValues.add(Integer.parseInt(values[1]));
         }
         inputStream.close();
      }
      catch (FileNotFoundException e)
      {
         e.printStackTrace();
      }
      return parsedValues;
   }

   public ArrayList<Integer> getThirdCol(String filePath)
   {
      File file = new File(filePath);
      ArrayList<Integer> parsedValues = new ArrayList<Integer>();
      try
      {
         Scanner inputStream = new Scanner(file);
         inputStream.next();
         while (inputStream.hasNext())
         {
            String data = inputStream.next();
            String[] values = data.split(",");
            parsedValues.add(Integer.parseInt(values[2]));
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
