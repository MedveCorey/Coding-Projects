package firstexam;

import java.util.Scanner;
import javax.swing.JOptionPane;

public class FirstExam {

    public static void main(String[] args) {
        new FirstExam();

    }

    public FirstExam() {
        Instructor instructor = new Instructor();
        instructor.setAge(32);
        instructor.setName("Corey");
        instructor.getAge();
        instructor.getName();

        Instructor instructor02 = new Instructor(32, "Corey");

        if (instructor.equals(instructor02)) {
            System.out.println("that is your professor");
        }
        int[] ages = {1, 2, 14, 15};
        System.out.println(ages[3]);
        String name;

        name = JOptionPane.showInputDialog("Please enter a name");

        Scanner input = new Scanner(System.in);
        
        for(int i = 0; i < ages.length ;i++){
            System.out.println(ages[i]);
        }
        for(int CurrentAge:ages){
            System.out.println(CurrentAge);
        }
    }

}
