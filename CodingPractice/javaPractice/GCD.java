
import java.util.Scanner;

public class GCD{

    public static void main(String[] args) {
        int value1;
        int value2;
        int input_value_1;
        int input_value_2;
        int result = 1;
        Scanner input = new Scanner(System.in);

        //get the first value
        System.out.println("Enter your first value");
        value1 = input.nextInt();
        input_value_1 = value1;

        //get the second value
        System.out.println("Enter your second value");
        value2 = input.nextInt();
        input_value_2 = value2;

        //Use Euclidian Algorithm to find the Greatest Common Divisor
        while (value2 != 0) {
            result = value1 % value2;
            System.out.println(result+" = " + value1 + " % " + value2);
            value1 = value2;
            if(result != 0){
                value2 = result;
            }else{
                break;
            }

        }

        if(value2 == 1){
            System.out.println(input_value_1 + " and " + input_value_2 + " are relatively prime");
        }else{
            System.out.println(input_value_1 + " and " + input_value_2 + " Greatest Common Divisor is: "+ value2 );
        }
    }
}