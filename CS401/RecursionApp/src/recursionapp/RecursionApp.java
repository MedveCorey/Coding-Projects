package recursionapp;

public class RecursionApp {

    public static void main(String[] args) {
        new RecursionApp();
    }

    public RecursionApp() {
        int value = factorial(5);
        System.out.println(value);
        int maximumNumberOfTerms = 1;
        double piValue = 4 * estimatePi(0, maximumNumberOfTerms);
        System.out.println("pi value for " + maximumNumberOfTerms + " is "
                + piValue + " diff: " + (piValue - Math.PI));
        double minAngle=-360;
        double maxAngle= 360;
        int maxNumberOfTerms = 10;
        for (double currentAngle = minAngle; currentAngle < maxAngle; currentAngle++) {
            double sineValue = estimateSine(1, maxNumberOfTerms, currentAngle);
            System.out.println("Sin(" + currentAngle + "):" + sineValue
                    + "Actual Value: " + Math.sin(currentAngle * Math.PI / 180));
        }

    }

    private int factorial(int number) {
        if (number == 1) {
            return 1;
        } else {
            return number * factorial(number - 1);
        }
    }

    private double estimatePi(int currentTerm, int maximumNumberOfTerms) {
        double currentTermValue = Math.pow(-1, currentTerm) / (2 * currentTerm + 1);
        if (currentTerm == maximumNumberOfTerms - 1) {
            return currentTermValue;
        } else {
            return currentTermValue + estimatePi(currentTerm + 1, maximumNumberOfTerms);
        }
    }

    private double estimateSine(int currentTerm, int maximumNumberOfTerms, double angleInDegrees) {
        double angleInRadians = (angleInDegrees * Math.PI) / 180;
        double currentTermValue = (Math.pow(-1, currentTerm) / 2 * currentTerm + 1)
                * Math.pow(angleInRadians, 2 * currentTerm + 1);
        if (currentTermValue == maximumNumberOfTerms - 1) {
            return currentTermValue;
            
        } else {
            return currentTermValue + estimateSine(currentTerm + 1, maximumNumberOfTerms, angleInDegrees);
        }

    }
}
