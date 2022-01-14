package GeekBrains.JavaCore.JavaCoreLesson1;

public class Team {
    String name;
    Member[] members = new Member[4];

    public Team(String name, Member member1, Member member2, Member member3, Member member4) {
        this.name = name;
        members[0] = member1;
        members[1] = member2;
        members[2] = member3;
        members[3] = member4;
    }

    public void showInfoAboutAllMembers() {
        for (Member member: members) {
            System.out.println(member);
        }
    }

    public void showInfoAboutAllWinners() {
        for (Member member: members) {
            if (member.isWinner) {
            System.out.println(member);
            }
        }
    }
}
