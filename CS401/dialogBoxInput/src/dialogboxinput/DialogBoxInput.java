package dialogboxinput;

import javax.swing.JOptionPane;

public class DialogBoxInput {

    public static void main(String[] args) {
        double base = Double.parseDouble(JOptionPane.showInputDialog("Enter base"));
        double height = Double.parseDouble(JOptionPane.showInputDialog("Enter height"));
        double area = 1.0 / 2 * base * height;
        System.out.println("The area of the triangle with base "
                + base + " and height " + height + " equals " + area);
    }

}
