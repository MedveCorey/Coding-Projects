package Lab01_cjm235;

import java.util.Scanner;
import javax.swing.JOptionPane;


public class Lab01_cjm235 {
    public static void main (String[] args){

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
