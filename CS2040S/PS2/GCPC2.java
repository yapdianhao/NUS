import java.util.*;

class GCPC2 {

    public static void main(String[] args) {
        Kattio kattio = new Kattio(System.in);
        TreeSet<Team> ts = new TreeSet<Team>();
        int teams = kattio.getInt();
        int events = kattio.getInt();
        Team[] arr = new Team[teams];
        for (int i = 0; i < teams; i++) {
            Team t = new Team(i + 1, 0, 0);
            arr[i] = t; //
            ts.add(t); // the tree
        }
        int rank = 0;
        for (int i = 0; i < events; i++) {
            int teamNumber = kattio.getInt();
            int penalty = kattio.getInt();
            Team temp = arr[teamNumber - 1];
            Team toAdd = new Team(temp.id, temp.score, temp.penalty);
            toAdd.score++;
            toAdd.penalty += penalty;
            ts.remove(temp);
            ts.add(toAdd);
            arr[teamNumber - 1] = toAdd;
            if (teamNumber != 1) {
                if (toAdd.compareTo(arr[0]) < 0 && temp.compareTo(arr[0]) > 0) {
                    rank++;
                }
            } else {
                rank = ts.headSet(toAdd).size();
            }
            kattio.println(rank + 1);
            //kattio.println(ts);
        }
        kattio.close();
    }

    static class Team implements Comparable<Team> {

        int score;
        int id;
        int penalty;

        public Team(int id, int score, int penalty) {
            this.id = id;
            this.score = score;
            this.penalty = penalty;
        }

        @Override
        public int compareTo(Team t2) {
            if (score != t2.score) {
                // if team 1 15, team 2 12 
                // if team 1 15, team 2 20
                return -(score - t2.score);
            }
            // if team 1 15, team 2 9
            // if team 1 15, team 2 20
            return (penalty == t2.penalty ? id - t2.id : penalty - t2.penalty);
        }

        public String toString() {
            return "" + id;
        }
    }
}