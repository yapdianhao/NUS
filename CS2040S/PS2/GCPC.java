import java.util.TreeSet;
import java.util.ArrayList;
import java.util.Comparator;

class GCPC {

    public static void main(String[] args) {
        Kattio kattio = new Kattio(System.in);
        ArrayList<Team> lst = new ArrayList<Team>();
        TreeSet<Team> treeSet = new TreeSet<Team>(new Comparator<Team>() {
            @Override
            public int compare(Team t1, Team t2) {
                if (t1.score < t2.score) {
                    return 1;
                } else if (t1.score > t2.score) {
                    return -1;
                } else {
                    if (t1.penalty > t2.penalty) {
                        return 1;
                    } else if (t1.penalty < t2.penalty) {
                        return -1;
                    } else {
                        return t1.id - t2.id;
                    }
                }
            }
        });
        //int id = 1;
        //Team one = new Team(id++, 0, 0);
        //treeSet.add(one);
        //lst.add(one);
        int teams = kattio.getInt();
        int events = kattio.getInt();
        for (int id = 1; id <= teams; id++) {
            Team team = new Team(id, 0, 0);
            treeSet.add(team);
            lst.add(team);
        }
        Team one = lst.get(0);
        for (int i = 0; i < events; i++) {
            int id = kattio.getInt();
            int penalty = kattio.getInt();
            treeSet.remove(lst.get(id - 1));
            lst.get(id - 1).score++;
            lst.get(id - 1).penalty += penalty;
            treeSet.add(lst.get(id - 1));
            kattio.println(treeSet.headSet(one).size() + 1);
        }
        kattio.close();
    }
}