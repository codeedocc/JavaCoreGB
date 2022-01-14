package GeekBrains.JavaCore.JavaCoreLesson3;

import java.util.ArrayList;

public class Box<T extends Fruit> {
    ArrayList<T> fruitsBox;

    public Box() {
        this.fruitsBox = new ArrayList<>();
    }

    public double getWeight() {
        if(fruitsBox.isEmpty())
            return 0;
        else {
            return fruitsBox.size() * fruitsBox.get(0).getWeight();
        }
    }

    public boolean differenceBetween(Box<?> anotherBox) {
        if (this.getWeight() == anotherBox.getWeight()) {
            return true;
        } else {
            return false;
        }
    }

    public void removeFruits(Box<T> anotherBox) {
        anotherBox.fruitsBox.addAll(this.fruitsBox);
        this.fruitsBox.clear();
    }
}
