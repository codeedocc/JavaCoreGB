package GeekBrains.JavaCore.JavaCoreLesson8;

import java.io.IOException;
import java.sql.SQLException;

public class Main {
    public static void main(String[] args) throws IOException, SQLException {
        System.out.println(Requester.sendCityIdRequest("Moscow"));
        ReadTable.readTable();
    }
}