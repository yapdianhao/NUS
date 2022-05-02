import java.util.Scanner;
import java.util.ArrayList;
import java.util.Collections;

class Main {
    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        ArrayList<Student> students = new ArrayList<Student>();
        ArrayList<Mark> marks = new ArrayList<Mark>();
        ArrayList<Integer> tutorials = new ArrayList<Integer>();
        ArrayList<Student> missingStudents = new ArrayList<Student>();
        ArrayList<Integer> markFrequency = new ArrayList<Integer>();
        ArrayList<Integer> existingTutorials = new ArrayList<Integer>();

        while (sc.hasNext()) {
            String plab = sc.next();
            if (!plab.equals("end")) {
                String id = sc.next();
                int tutorial = sc.nextInt();
                if (!tutorials.contains(tutorial)) {
                    tutorials.add(tutorial);
                }
                Student student = new Student(plab, id, tutorial);
                students.add(student);
            } else { break;}
        }
        while (sc.hasNext()) {
                String plab = sc.next();
                if (!plab.equals("end")) {
                    int point = sc.nextInt();
                    Mark mark = new Mark(plab, point);
                    marks.add(mark);
                } else {
                    break;
                }
        }

        Collections.sort(tutorials);
        System.out.println(String.format("Groups(%d):%s", tutorials.size(), tutorials.toString()));

        for (Student student : students) {
            for (Mark mark : marks) {
                if (mark.getPlab().equals(student.getPlab())) {
                    System.out.println(student.toString() + "," + mark.getMark());
                    break;
                } else if (!mark.getPlab().equals(student.getPlab()) && marks.indexOf(mark) == marks.size() - 1) {
                    System.out.println(student.toString() + "," + 0);
                    missingStudents.add(student);
                    break;
                } else {
                    continue;
                }
            }
        }

        System.out.println("List of absentees:");
        if (missingStudents.size() == 0) {
            System.out.println("None");
        } else {
            for (Student student : missingStudents) {
                System.out.println(student);
            }
        }

        int lowestMark = marks.get(0).getMark();
        int highestMark = marks.get(0).getMark();
        for (Mark mark : marks) {
            if (mark.getMark() <= lowestMark) {
                lowestMark = mark.getMark();
            } 
        }
        for (Mark mark : marks) {
            if (mark.getMark() >= highestMark) {
                highestMark = mark.getMark();
            }
        }
        for (int i = lowestMark; i < highestMark + 1; i++) {
            int count = 0;
            for (Mark mark : marks) {
                if (mark.getMark() == i) {
                    count++;
                }
            }
            markFrequency.add(count);
        }
        System.out.println("Mark frequency from " + lowestMark + " to " + highestMark);
        int count = 0;
        for (int i = lowestMark; i < highestMark + 1; i++) {
            System.out.println(i + " : " + markFrequency.get(count));
            count++;
        }

       /* ArrayList<Student> existingStudents = new ArrayList<Student>(students);
        for (Student student : missingStudents) {
            existingStudents.remove(student);
        }
        for (Student student : existingStudents) {
            if (!existingTutorials.contains(student.getGroup())) {
                existingTutorials.add(student.getGroup());
            }
        }
        Collections.sort(existingTutorials);
        for (int i : existingTutorials) {
            ArrayList<Student> studentsWithinTutorial = new ArrayList<Student>();
            for (Student student : existingStudents) {
                if (student.getGroup() == i) {
                    studentsWithinTutorial.add(student);
                }
            }
            ArrayList<Integer> marksWithinTutorial = new ArrayList<Integer>();
            System.out.println("Group #" + i + "...Mark frequency from " + lowestMark + " to " +highestMark);
            for (int j = lowestMark; j < highestMark + 1; j++) {
                int c = 0;
                for (Mark mark : marks) {
                    for (Student student : studentsWithinTutorial) {
                        if (mark.getPlab().equals(student.getPlab()) && mark.getMark() == j) {
                            c++;
                        }
                    }
                }
                marksWithinTutorial.add(c);
            }
            int c = 0;
            for (int j = lowestMark; j < highestMark + 1; j++) {
                System.out.println(j + " : " + marksWithinTutorial.get(c));
                c++;
            }
        }*/   
    }
}

