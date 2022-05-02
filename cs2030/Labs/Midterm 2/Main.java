import java.util.Scanner;
import java.util.Collections;
import java.util.List;
import java.util.ArrayList;
import java.util.Set;
import java.util.HashSet;

class Main {
    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        String plab, id;
        List<Student> students = new ArrayList<>();
        List<Mark> marks = new ArrayList<>();
        List<Integer> groups = new ArrayList<>();
        Set<Integer> set = new HashSet<>();
        plab = sc.next();
        
        while(!plab.equals("end")){
            int group;
            id = sc.next();
            group = sc.nextInt();
            Student student = new Student(plab, id, group);
            students.add(student);
            plab = sc.next();
            if(!set.contains(group)){
                groups.add(group);
                set.add(group);
            }
        }

        plab = sc.next();
        int x;
        while(!plab.equals("end")) {
            x = sc.nextInt();
            marks.add(new Mark(plab, x));
            plab = sc.next();
        }

        Collections.sort(groups);
        System.out.print("Groups(" + groups.size() + "):[");
        for (int i = 0; i < groups.size(); i++) {
            System.out.print(groups.get(i));
            if (i != groups.size() - 1) {
                System.out.print(", ");
            }
        }
        System.out.println("]");

        List<Student> absentees = new ArrayList<>();
        for (Student student : students) {
            System.out.print(student);
            int curMark = 0;
            boolean foundMark = false;
            for (Mark mark : marks) {
                if (mark.getPlab().equals(student.getPlab())) {
                    curMark = mark.getMark();
                    foundMark = true;
                    break;
                }
            }
            System.out.println("," + curMark);
            if (!foundMark) {
                absentees.add(student);
            }
        }

        System.out.println("List of absentees:");
        for (Student student : absentees) {
            System.out.println(student);
        }
        if (absentees.size() == 0) {
            System.out.println("None");
        }

        int minMarks = marks.get(0).getMark();
        int maxMarks = marks.get(0).getMark();
        for (Mark mark : marks) {
            if (mark.getMark() < minMarks) {
                minMarks = mark.getMark();
            }
            if (mark.getMark() > maxMarks) {
                maxMarks = mark.getMark();
            }
        }
        int[] freq = new int[maxMarks - minMarks + 1];
        for (Mark mark : marks) {
            freq[mark.getMark() - minMarks]++;
        }
        System.out.println("Mark frequency from " + minMarks + " to " + maxMarks);
        for (int i = minMarks; i <= maxMarks; i++) {
            System.out.println(i + " : " + freq[i - minMarks]);
        }

        for (int group : groups) {
            int[] freqGroup = new int[maxMarks - minMarks + 1];
            boolean hasStudent = false;
            for (Mark mark : marks) {
                int mygroup = -1;
                for (Student student : students) {
                    if (mark.getPlab().equals(student.getPlab())) {
                        mygroup = student.getGroup();
                        break;
                    }
                }
                if(mygroup == group) {
                    hasStudent = true;
                    freqGroup[mark.getMark() - minMarks]++;
                }
            }
            if (!hasStudent) {
                continue;
            }
            System.out.println("Group #" + group + "...Mark frequency from "
                    + minMarks + " to " + maxMarks);
            for (int i = minMarks; i <= maxMarks; i++) {
                System.out.println(i + " : " + freqGroup[i - minMarks]);
            }
        }
    }
}
