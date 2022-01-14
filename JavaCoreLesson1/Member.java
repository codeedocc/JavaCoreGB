package GeekBrains.JavaCore.JavaCoreLesson1;

public class Member {
    String name;
    boolean isWinner;
    int maxRunDistance;

    public Member(String name, int maxRunDistance) {
        this.name = name;
        this.maxRunDistance = maxRunDistance;
        isWinner = false;
    }

    @Override
    public String toString() {
        return '[' + name + ", " + maxRunDistance + ']';
    }


}
