package exceptionsexplained;

public class DivisionByZeroException extends Exception {

    public DivisionByZeroException() {
        super("Division by zero. Check Denominator");
    }

}
