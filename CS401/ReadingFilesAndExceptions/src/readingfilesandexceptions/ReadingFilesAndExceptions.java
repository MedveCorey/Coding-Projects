package readingfilesandexceptions;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ReadingFilesAndExceptions {

    public static void main(String[] args) throws IOException{
        try {
            new ReadingFilesAndExceptions();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(ReadingFilesAndExceptions.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public ReadingFilesAndExceptions() throws IOException {
        try (FileReader fileReader = new FileReader("C:\\Users\\corey\\OneDrive\\Documents\\NetBeansProjects\\ReadingFilesAndExceptions\\src\\readingfilesandexceptions\\students.txt");
            BufferedReader bufferedReader = new BufferedReader(fileReader);){
            
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                System.out.println(line);
            }
        } catch (FileNotFoundException ex) {
            System.out.println("Found an error.");
        }
        System.out.println("Finished reading the file.");

    }
}
