package GeekBrains.JavaCore.JavaCoreLesson4;

import java.util.ArrayList;
import java.util.HashMap;

public class PhoneJournal {
    
    private HashMap<String, ArrayList<String>> surnameNumber;

    public PhoneJournal() {
        surnameNumber = new HashMap<>();
    }

    public void add(String surname, String phone) {
        if(surnameNumber.get(surname) == null) {
            ArrayList<String> arrayList = new ArrayList<>();
            arrayList.add(phone);
            surnameNumber.put(surname, arrayList);
        } else {
            surnameNumber.get(surname).add(phone);
        }
    }

    public ArrayList<String> get(String surname) {
        return surnameNumber.get(surname);
    }
}
