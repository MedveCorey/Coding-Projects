package lab8;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.EOFException;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.Scanner;

public class Lab8 {

    public static void main(String[] args) throws IOException, DivisionByZeroException {
        new Lab8();
    }

    public Lab8() throws FileNotFoundException, IOException, DivisionByZeroException {
        dividingInts();
        writeData();
        readData();
    }

    public void writeData() throws FileNotFoundException {

        FileOutputStream fos = new FileOutputStream("lab8.dat");
        DataOutputStream dos = new DataOutputStream(fos);
        try {
            byte daysOfTheWeek = 7;
            short numberOfDaysInAYear = 365;
            char nameMiddleInitial = 'B';
            float baseLength = (float) 5.23;
            double veryPreciseData = 3.141592612343;
            boolean isRaining = false;
            long cityPopulation = 3000000;
            String streetAddress = "123 Banana Street";
            dos.writeByte(daysOfTheWeek);
            dos.writeShort(numberOfDaysInAYear);
            dos.writeChar(nameMiddleInitial);
            dos.writeFloat(baseLength);
            dos.writeDouble(veryPreciseData);
            dos.writeBoolean(isRaining);
            dos.writeLong(cityPopulation);
            dos.writeUTF(streetAddress);
            dos.close();
        } catch (IOException ex) {
            System.out.println("Data could not be saved.");
        }

    }

    public void readData() throws FileNotFoundException, IOException {
        FileInputStream fis = new FileInputStream("lab8.dat");
        DataInputStream dis = new DataInputStream(fis);

        byte daysOfTheWeek = dis.readByte();
        System.out.println(daysOfTheWeek);
        short numberOfDaysInAYear = dis.readShort();
        System.out.println(numberOfDaysInAYear);
        char nameMiddleInitial = dis.readChar();
        System.out.println(nameMiddleInitial);
        float baseLength = dis.readFloat();
        System.out.println(baseLength);
        double veryPreciseData = dis.readDouble();
        System.out.println(veryPreciseData);
        boolean isRaining = dis.readBoolean();
        System.out.println(isRaining);
        long cityPopulation = dis.readLong();
        System.out.println(cityPopulation);
        String streetAddress = dis.readUTF();
        System.out.println(streetAddress);

    }

    private void dividingInts() throws DivisionByZeroException {
        Scanner scn = new Scanner(System.in);
        double num1 = scn.nextDouble();
        double num2 = scn.nextDouble();
        if (num2 == 0) {
                DivisionByZeroException myException = new DivisionByZeroException();
                throw myException;
            }
        System.out.println(num1/num2);
    }

}
