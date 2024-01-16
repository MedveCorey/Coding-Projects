package exceptionsexplained;

public class ExceptionsExplained {

    public static void main(String[] args) {
        new ExceptionsExplained();

    }

    public ExceptionsExplained() {
        String[] ages = {"12", "16", "81", "0"};
        try {
            for (int i = 0; i <= 3; i++) {
                int currentNumber = Integer.parseInt(ages[i]);
                System.out.println(currentNumber);
            }

            double num = Double.parseDouble(ages[0]);
            double den = Double.parseDouble(ages[3]);
            if (den == 0) {
                DivisionByZeroException myException = new DivisionByZeroException();
                throw myException;
            }
            double result = num / den;
            System.out.println(result + "result");
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Incomplete data set. The program is expecting four numbers");

        } catch (NumberFormatException e) {
            System.out.println("Invalid inputs. They must be integer numbers");
        } catch (DivisionByZeroException e) {
            System.out.println(e.getMessage());
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        for (int i = 0; i < 10; i++) {
            System.out.println(i + " " + i * i);
        }
    }
}
