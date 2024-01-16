package mainstringargsinput;

public class MainStringArgsInput {

    public static void main(String[] args) {
        System.out.print("Enter the base: ");
        double base = Double.parseDouble(args[0]);
        System.out.print("Enter the height: ");
        double height = Double.parseDouble(args[1]);
        double area = 1.0 / 2 * base * height;
        System.out.println("The area of the triangle with base "
                + base + " and height " + height + " equals " + area);
    }
}
