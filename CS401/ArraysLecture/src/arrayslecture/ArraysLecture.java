package arrayslecture;

import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

public class ArraysLecture {

    public static void main(String[] args) {
        new ArraysLecture();
    }

    public ArraysLecture() {
        int a = 10;
        int[] b ={10, 20, -5};
        int firstNumber = b[0];
        b[1] = -30;
        // ways of creating an array
        waysOfCreatingAnArray();

        // ways of filling an array
        waysOfAddingValuesIntoAnArray();

        // ways of going over all the elements of an array
        waysOfGoingOverAllTheElementsOfAnArray();

        // ways to increase (or decrease) the size of an existing array
        waysOfIncreasingAnExistingArraySize();

        // ways of copying all elements of an array into another one
        waysOfCopyingArrayContents();

        // ways reseting an array to null state or smaller or higher size
        waysOfResetingAnArray();

        // ways of sorting an array
        waysOfSortingTheElementsInAnArray();

        // ways of searching for an item in an Array
        waysOfSearchingAnArray();

        // multi-dimensional arrays
        waysOfCreatingMultiDimensionalArrays();
    }

    private void waysOfCreatingAnArray() {
        // method 1: creating an array based on a hardcoded size, such as 10
        int[] grades = new int[10];

        // method 2: creating an array based on a variable, such as "arraySize"
        Scanner keyboard = new Scanner(System.in);
        System.out.print("Enter the size of the array: ");
        int arraySize = keyboard.nextInt();
        int[] values = new int[arraySize];

        // method 3: creating an array based on a method return
        double[] randomValues = getRandomValues(10);
    }

    private double[] getRandomValues(int maxNumberOfValues) {
        double[] values = new double[maxNumberOfValues];
        Random random = new Random();
        for (int i = 0; i < maxNumberOfValues; i++) {
            values[i] = random.nextDouble() * 100;
        }
        return values;
    }

    private void waysOfAddingValuesIntoAnArray() {
        addingValuesIntoAnArrayMethod1();
        addingValuesIntoAnArrayMethod2();
    }

    private void addingValuesIntoAnArrayMethod1() {
        // method 1: adding elements into a new array one by one
        int[] grades = new int[10];
        grades[0] = 3;
        grades[2] = 5;
        grades[6] = 4;

        // what happens when we try to print the whole array?
        for (int i = 0; i < grades.length; i++) {
            System.out.println("item[" + i + "] = " + grades[i]);
        }

        ////////////////////////////////////////
        String[] names = new String[10];
        names[3] = "John";
        names[0] = new String("Mary");   // same for Cars, Bikes, etc.
        names[6] = "Brendon";

        // what happens when we try to print the whole array?
        for (int i = 0; i < 10; i++) {
            System.out.println("names[" + i + "] = " + names[i]);
        }
    }

    private void addingValuesIntoAnArrayMethod2() {
        // method 2: adding values at the same time we declare/create the array
        // way #1: using the "new int[]" syntase
        int[] labGrades = new int[]{3, 4, 2, 1, 111, 234, 65, 24, 76, 10};

        // way #2: not using the "new int[]"
        int[] exam2Grades = {3, 4, 2, 1, 111, 234, 65};

        ////////////////////////////////////////
        // way #1: using the "new int[]" syntase
        String[] names = new String[]{"John", "Mary", "Brendon"};

        // way #2: not using the "new int[]"
        String[] names2 = {"John", "Mary", "Brendon"};
    }

    private void waysOfGoingOverAllTheElementsOfAnArray() {
        double[] randomValues = getRandomValues(10);

        System.out.println("\ngoing over all the elements of an array using standard FOR loop");
        for (int i = 0; i < randomValues.length; i++) {
            System.out.println(randomValues[i] + ", ");
        }

        System.out.println("\ngoing over all the elements of an array using enhanced FOR loop");
        for (double singleValue : randomValues) {
            System.out.println(singleValue + ", ");
        }

        System.out.println("\nshowing the array in reverse order: from last to first element");
        for (int i = randomValues.length - 1; i >= 0; i--) {
            System.out.println(randomValues[i] + ", ");
        }
    }

    private void waysOfIncreasingAnExistingArraySize() {
        // method 1: create a temporary array with the exact new size you want
        increaseArrayByADefinedNumberOfItems();

        // method 2: create a temporary array with more space than you need
        increaseArrayByLargerSize();
    }

    private void increaseArrayByADefinedNumberOfItems() {
        int[] grades = new int[]{3, 4, 2, 1, 76, 10};

        // step 1: create the temp array
        int[] temp = new int[grades.length + 1];

        // step 2: copy all elements from the original array into the temp array
        for (int i = 0; i < grades.length; i++) {
            temp[i] = grades[i];
        }

        // setp 3: copy the new item
        temp[grades.length] = 25;

        // step 4: finally make the original array to point to the memory 
        // location of the temporary array
        grades = temp;

        // what is the basic desadvantage of this method?
    }

    private void increaseArrayByLargerSize() {
        int[] grades = new int[]{3, 4, 2, 1, 76, 10};

        // step 1: create the temp array
        int[] temp = new int[50];

        // step 2: copy all elements from the original array into the temp array
        for (int i = 0; i < grades.length; i++) {
            temp[i] = grades[i];
        }

        // setp 3: copy the new item
        temp[6] = 25;
        temp[7] = 32;
        temp[8] = 40;

        // step 4: finally make the original array to point to the memory 
        // location of the temporary array
        grades = temp;

        // What are the advantages and disadvantages of this method?
        // advantage: faster when adding multiple numbers 
        // disadvantage: you need to keep track of how many numbers the array 
        // actually has, so you do not use the not-used locations 
        // example: averaging all the numbers would be wrong if you consider
        // all the numbers in the array.
    }

    private void waysOfCopyingArrayContents() {
        waysOfCopyingAnArrayContentsMethod1();
        waysOfCopyingAnArrayContentsMethod2();
    }

    private void waysOfCopyingAnArrayContentsMethod1() {
        // method #1: pointing the new array to the same memory location as the
        // first array
        double[] grades = new double[]{3.41, 4.32, 2.11, 1.76, 4.17, 10.2};

        // copying an array
        double[] copiedArray = grades;
        grades[0] = 1.0;
        copiedArray[0] = 1000.0;
        boolean areItemsEqual = (grades[0] == copiedArray[0]);

        // this is the way arrays are passed by method arguments
        int age = 35;
        passingAnArrayAsArgument(age, grades);
        System.out.println("Age: " + age + "  grades[0]: " + grades[0]);
    }

    private void passingAnArrayAsArgument(int argAge, double[] argArray) {
        argAge = 21;
        argArray[0] = 111.11;
    }

    private void passingAnArrayAsArgument2(int argAge, double[] argArray) {
        double[] clonedArray = argArray.clone();
        argAge = 21;
        clonedArray[0] = 111.11;
    }

    private void waysOfCopyingAnArrayContentsMethod2() {
        // method #2: cloning an array
        double[] grades = new double[]{3.41, 4.32, 2.11, 1.76, 4.17, 10.2};
        double[] copiedArray = new double[grades.length];

        // copying element by element
        for (int i = 0; i < grades.length; i++) {
            copiedArray[i] = grades[i];
        }

        // or using a method called "clone"
        // copiedArray = grades.clone();
        grades[0] = 1.0;
        copiedArray[0] = 1000.0;
        boolean areItemsEqual = (grades[0] == copiedArray[0]);

        // what happens now when we call the followinng method?
        int age = 35;
        passingAnArrayAsArgument2(age, grades);
        System.out.println("Age: " + age + "  grades[0]: " + grades[0]);
    }

    private void waysOfResetingAnArray() {
        int[] grades = new int[]{3, 4, 2, 1, 111, 234, 65, 24, 76, 10};

        // method #1: setting all the array values to zero (for numbers)
        grades = new int[20];       // all previous data will be lost

        // method #2: null(ing) an array: no memory is allocated
        grades = null;

        // method #3: reseting an array to new size and/or values
        grades = new int[]{3, 4, 2, 1, 76, 10};
    }

    private void waysOfSortingTheElementsInAnArray() {
        sortingAnArrayMethod1();
        sortingAnArrayMethod2();
    }

    private void sortingAnArrayMethod1() {
        int[] grades = new int[]{3, 4, 2, 1, 111, 234, 65, 24, 76, 10};
        boolean swapped = true;

        int j = 0;
        int tmp;
        while (swapped) {
            swapped = false;
            j++;
            for (int i = 0; i < grades.length - j; i++) {
                if (grades[i] > grades[i + 1]) {
                    tmp = grades[i];
                    grades[i] = grades[i + 1];
                    grades[i + 1] = tmp;
                    swapped = true;
                }
            }
        }
    }

    private void sortingAnArrayMethod2() {
        int[] grades = new int[]{3, 4, 2, 1, 111, 234, 65, 24, 76, 10};
        Arrays.sort(grades);
    }

    private void waysOfSearchingAnArray() {
        // both methods require that you sort the array first! Don't forget it!
        waysOfSearchingAnArrayMethod1();
        waysOfSearchingAnArrayMethod2();
    }

    private void waysOfSearchingAnArrayMethod1() {
        // Method #1: Sequential Search
        double[] values = new double[]{0.000, 0.087, 0.174, 0.259, 0.342, 0.423,
            0.500, 0.574, 0.643, 0.707, 0.766, 0.819, 0.866, 0.906, 0.940,
            0.966, 0.985, 0.996, 1.000};

        // sequential search
        double searchedNumber = 0.9059;
        double tolerance = 0.005;
        for (int i = 0; i < values.length; i++) {
            System.out.println("Comparing values: " + searchedNumber + " , " + values[i]);
            if (Math.abs(searchedNumber - values[i]) < tolerance) {
                System.out.println("Found searched value of " + searchedNumber
                        + " in the " + i + " array location");
                break;
            }
        }

        // why do we have the "break" inside the FOR loop?
        // what is the cons of this search?
    }

    private void waysOfSearchingAnArrayMethod2() {
        // Method #1: Binary Search
        boolean numberHasBeenFound = false;
        int numberLocationInTheSearchedArray = -1;
        int[] data = new int[]{1, 2, 3, 6, 7, 11, 23, 30, 35, 47, 60, 70, 80, 90, 100, 106, 235};
        int searchedNumber = 100;
        int low = 0;
        int high = data.length - 1;

        while (high >= low) {
            int middle = (low + high) / 2;
            System.out.println("Comparing values: " + searchedNumber + " , " + data[middle]);
            if (data[middle] == searchedNumber) {
                numberHasBeenFound = true;
                numberLocationInTheSearchedArray = middle;
                break;
            }
            if (data[middle] < searchedNumber) {
                low = middle + 1;
            }
            if (data[middle] > searchedNumber) {
                high = middle - 1;
            }
        }

        if (numberHasBeenFound) {
            System.out.println("Searched number has been found at the following"
                    + " array location: " + numberLocationInTheSearchedArray);
        } else {
            System.out.println("Number has not been found");
        }
    }

    private double[] cloneArray(double[] randomValues) {
        // cloning an array
        double[] clonedArray = new double[randomValues.length];
        for (int i = 0; i < randomValues.length; i++) {
            clonedArray[i] = randomValues[i];
        }
        return clonedArray;
    }

    private void waysOfCreatingMultiDimensionalArrays() {
        // creating two dimensional arrays
        double[][] gameBoard = new double[8][10];
        double[][] terrainLevel = new double[][]{ { 1.1, 1.2, 1.3, 1.4},
             { 1.1, 1.2, 1.3, 1.4}, 
             { 1.1, 1.2, 1.3, 1.4}, 
             { 1.1, 1.2, 1.3, 1.4}};
        
        // looping over a 2d array
        for (int row = 0; row < 8; row++) {
            for (int col = 0; col < 10; col++) {
                System.out.print((row*8) + col + " ");
            }
            System.out.println();
        }
        
        // creating three dimensional arrays
        double[][][] ctr = new double[8][8][8]; 
    }
}
