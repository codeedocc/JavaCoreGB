package GeekBrains.JavaCore.JavaCoreLesson1;

public class Course {
    int[] obstacles = new int[3];

    public Course(int o1, int o2, int o3) {
        obstacles[0] = o1;
        obstacles[1] = o2;
        obstacles[2] = o3;
    }

    public void doIt(Team team) {
        for (Member member: team.members) {
            for (int distance: obstacles) {
                if (member.maxRunDistance >= distance) {
                    member.isWinner = true;
                } else {
                    member.isWinner = false;
                    break;
                }
            }
        }
    }
}
