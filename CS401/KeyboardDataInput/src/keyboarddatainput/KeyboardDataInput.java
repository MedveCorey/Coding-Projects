
package keyboarddatainput;

import java.util.*;

public class KeyboardDataInput {
    
    public static void main(String[] args) {
        
      Scanner input = new Scanner(System.in);
      
      System.out.print("Enter the base: ");
      double base = input.nextDouble();
      System.out.print("Enter the height: ");
      double height = input.nextDouble();
      double area = 1.0/2 * base * height;
      System.out.println("The area of the triangle with base "+ 
              base + " and height "+ height + "equals " + area);
    }
    
}
