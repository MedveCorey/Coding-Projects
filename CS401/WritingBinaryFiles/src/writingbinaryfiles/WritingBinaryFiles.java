package writingbinaryfiles;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.EOFException;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.RandomAccessFile;

public class WritingBinaryFiles {

    public static void main(String[] args) throws FileNotFoundException, IOException {
        new WritingBinaryFiles();
    }

    public WritingBinaryFiles() throws FileNotFoundException, IOException {
        writeData();
        readData();
        readRandomData();
    }

    public void writeData() throws FileNotFoundException, IOException {
        FileOutputStream fos = new FileOutputStream("customers_info.dat");
        DataOutputStream dos = new DataOutputStream(fos);

        try {
            for (int i = 0; i < 100; i++) {
                dos.write(i * 2);
            }
            double pi = 3.1415;
            dos.writeDouble(pi);
            dos.writeUTF("Hello");
        } catch (IOException ex) {
            System.out.println("Data could not be saved.");
        }
        dos.close();
    }

    public void readData() throws FileNotFoundException, IOException {
        FileInputStream fis = new FileInputStream("customers_info.dat");
        DataInputStream dis = new DataInputStream(fis);
        int age = dis.readInt();
        System.out.println("age " + age);
        double value = dis.readDouble();
        System.out.println("value " + value);

        boolean endOfFile = false;
        while (!endOfFile) {
            try{
                System.out.println(dis.readInt());
            }catch(EOFException e){
                endOfFile = true;
            }
        }
        dis.close();
    }

    public void readRandomData() throws FileNotFoundException, IOException {
       RandomAccessFile raf; 
        raf = new RandomAccessFile("customers_info.dat","r");
        int age = raf.readInt();
        raf.seek(10*Double.BYTES);
        double value = raf.readDouble();
        raf.seek(5*Integer.BYTES);
    }
}
