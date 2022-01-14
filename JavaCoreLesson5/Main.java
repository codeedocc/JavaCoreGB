package GeekBrains.JavaCore.JavaCoreLesson5;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

public class Main {
    public static void main(String[] args) throws FileNotFoundException, IOException {

        File file = new File("test.csv");

        String[] headers = {"Weather 1", "Weather 2", "Weather 2"};
        
        int[][] data = {
            {5, 10, 15},
            {20, 25, 30},
            {35, 40, 45},
        };

        AppData appData = new AppData(headers, data);

        appData.save(file);
        appData.load(file);
        appData.readAppData();
    }
}
