package GeekBrains.JavaCore.JavaCoreLesson1;

public class Main {
    public static void main (String[] args) {
        Member member1 = new Member("Ruslan", 400);
        Member member2 = new Member("Pasha", 300);
        Member member3 = new Member("Misha", 200);
        Member member4 = new Member("Kostya", 100);
        Team team = new Team("Синие", member1, member2, member3, member4);
        Course course = new Course(150, 200, 300);

        team.showInfoAboutAllMembers();
        System.out.println();

        course.doIt(team);

        team.showInfoAboutAllWinners();
    }
}
