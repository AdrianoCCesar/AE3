
public class Point
{
   private double x;
   private double y;
   private double radius;
   //creates a point with two coordinates and a radius
   public Point(double x, double y, double r)
   {
      this.x = x;
      this.y = y;
      this.radius = r;

   }
   //get method for x
   public double getX()
   {
      return x;
   }
   //get method for y
   public double getY()
   {
      return y;
   }
   //get method for radius
   public double getRadius()
   {
      return radius;
   }
}
