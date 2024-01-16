package Lab01_cjm235;
import java.util.*;
import javax.swing.JOptionPane;

public class Lab01_Part_2_cjm235 {
    public static void main (String[] args){
        //Activity 7
        String[] names = {"Corey", "Abby", "Chris", "Dean","Lily"};
        for(String a : names){
            System.out.println(a);
        }
        // Activity 8
    Scanner input = new Scanner(System.in);
    System.out.println("Please enter a number between 10 and 20.");
    double number = input.nextDouble();
    while( number < 10 || number >20 ){
        System.out.println("The number you enter is not between 10 and 20.");
        System.out.println("Please enter a number between 10 and 20.");
        number = input.nextDouble();
    }
    System.out.println(number+ " is between 10 and 20."); 
    // Activity 9
    double r = Double.parseDouble(JOptionPane.showInputDialog("Please enter the radius of the sphere."));
    double V = 4.0/3 * Math.PI * Math.pow(r, 3);
    JOptionPane.showMessageDialog(null,"The volume of the sphere with a radius of "+ r +
            " is " + V);
     // Activity 10
     System.out.println("Please enter the temperature.");
     Scanner temp_input = new Scanner(System.in);
        double temperature = temp_input.nextDouble();
     if (temperature < 40){
         System.out.println(" it is cold out there.");
     }
     else if (40 <= temperature && temperature <= 60){
         System.out.println("it is ok");
     }
     else{
         System.out.println("it is hot.");
     }
            
    }   
}
