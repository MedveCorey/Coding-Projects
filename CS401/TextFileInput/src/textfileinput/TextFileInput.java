package textfileinput;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class TextFileInput {

    public static void main(String[] args) throws FileNotFoundException {
        File file = new File("C:\\Users\\corey\\OneDrive\\Documents\\"
                + "NetBeansProjects\\TextFileInput\\src\\"
                + "textfileinput\\numbers.txt");
        Scanner input = new Scanner(file);
        double base = input.nextDouble();
        double height = input.nextDouble();
        input.close();
        double area = 1.0/2 * base * height;
        System.out.println(area);
    }
    
}
