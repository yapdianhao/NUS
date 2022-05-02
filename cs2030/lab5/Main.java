import java.util.Scanner;
import java.util.ArrayList;

class Main {
    public static void main(String[] args) {
        ArrayList<Rubik> rubiks = new ArrayList<Rubik>();
        int[][][] grid = new int[6][3][3];
        Scanner scn = new Scanner(System.in);
        while (scn.hasNextInt()) {
            for (int k = 0; k < 6; k++ ){
                for (int i = 0; i < 3; i++) {
                    for (int j = 0; j < 3; j++) {
                        grid[k][i][j] = scn.nextInt();
                    }
                }
            }
        }
        Rubik rubik = new Rubik(grid);
        rubiks.add(rubik);
        while (scn.hasNext()) {
            String s = scn.next();
            if (s.equals("F")) {
                Rubik r = rubiks.get(rubiks.size() - 1).frontfaceRight();
                rubiks.add(r);
            } else if (s.equals("F'")) {
                Rubik r = rubiks.get(rubiks.size() - 1).frontfaceLeft();
                rubiks.add(r);
            } else if (s.equals("F2")) {
                Rubik r = rubiks.get(rubiks.size() - 1).frontfaceHalf();
                rubiks.add(r);
            } else if (s.equals("R")) {
                Rubik r = rubiks.get(rubiks.size() - 1).rightfaceRight();
                rubiks.add(r);
            } else if (s.equals("R'")) {
                Rubik r = rubiks.get(rubiks.size() - 1).rightfaceLeft();
                rubiks.add(r);
            } else if (s.equals("R2")) {
                Rubik r = rubiks.get(rubiks.size() - 1).rightfaceHalf();
                rubiks.add(r);
            } else if (s.equals("L")) {
                Rubik r = rubiks.get(rubiks.size() - 1).leftfaceRight();
                rubiks.add(r);
            } else if (s.equals("L'")) {
                Rubik r = rubiks.get(rubiks.size() - 1).leftfaceLeft();
                rubiks.add(r);
            } else if (s.equals("L2")) {
                Rubik r = rubiks.get(rubiks.size() - 1).leftfaceHalf();
                rubiks.add(r);
            } else if (s.equals("U")) {
                Rubik r = rubiks.get(rubiks.size() - 1).upfaceRight();
                rubiks.add(r);
            } else if (s.equals("U'")) {
                Rubik r = rubiks.get(rubiks.size() - 1).upfaceLeft();
                rubiks.add(r);
            } else if (s.equals("U2")) {
                Rubik r = rubiks.get(rubiks.size() - 1).upfaceHalf();
                rubiks.add(r);
            } else if (s.equals("B")) {
                Rubik r = rubiks.get(rubiks.size() - 1).backfaceRight();
                rubiks.add(r);
            } else if (s.equals("B'")) {
                Rubik r = rubiks.get(rubiks.size() - 1).backfaceLeft();
                rubiks.add(r);
            } else if (s.equals("B2")) {
                Rubik r = rubiks.get(rubiks.size() - 1).backfaceHalf();
                rubiks.add(r);
            } else if (s.equals("D")) {
                Rubik r = rubiks.get(rubiks.size() - 1).downfaceRight();
                rubiks.add(r);
            } else if (s.equals("D'")) {
                Rubik r = rubiks.get(rubiks.size() - 1).downfaceLeft();
                rubiks.add(r);
            } else if (s.equals("D2")) {
                Rubik r = rubiks.get(rubiks.size() - 1).downfaceHalf();
                rubiks.add(r);
            }
        }
        System.out.println(rubiks.get(rubiks.get(0)));
    }
}
