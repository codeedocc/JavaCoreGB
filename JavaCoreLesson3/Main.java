package GeekBrains.JavaCore.JavaCoreLesson3;

public class Main {
    public static void main(String[] args) {

        Box<Orange> orangeBox = new Box<>();
        orangeBox.fruitsBox.add(new Orange());
        orangeBox.fruitsBox.add(new Orange());

        Box<Apple> appleBox = new Box<>();
        appleBox.fruitsBox.add(new Apple());
        appleBox.fruitsBox.add(new Apple());

        Box<Apple> appleBox2 = new Box<>();
        appleBox2.fruitsBox.add(new Apple());
        appleBox2.fruitsBox.add(new Apple());

        System.out.println(orangeBox.getWeight());
        System.out.println(orangeBox.differenceBetween(orangeBox));

        System.out.println(appleBox.getWeight() + " " + appleBox2.getWeight());

        appleBox.removeFruits(appleBox2);

        System.out.println(appleBox.getWeight() + " " + appleBox2.getWeight());
    
    }
}
