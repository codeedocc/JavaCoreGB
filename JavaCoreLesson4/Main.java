package GeekBrains.JavaCore.JavaCoreLesson4;

import java.util.HashMap;

public class Main {
    public static void main(String[] args) {

        String[] words = {"aaa", "bbb", "aaa", "ccc", "ddd", "aaa"};

         HashMap<String, Integer> hashMap = new HashMap<>();

        for (String word: words) {
            if(hashMap.get(word) == null) {
                hashMap.put(word, 1);
            } else {
                hashMap.put(word, hashMap.get(word) + 1);
            }
        }

        System.out.println(hashMap);

        PhoneJournal phoneJournal = new PhoneJournal();
        
        phoneJournal.add("Руслан", "123456789");
        phoneJournal.add("Руслан", "222222222");
        phoneJournal.add("Руслан", "333333333");

        phoneJournal.add("Михаил", "444444444");
        phoneJournal.add("Михаил", "444444444");
        phoneJournal.add("Михаил", "444444444");

        System.out.println(phoneJournal.get("Михаил"));
        System.out.println(phoneJournal.get("Руслан"));
    }
}
