class Rubik {
    Face top;
    Face left;
    Face front;
    Face right;
    Face down;
    Face back;
    int[][][] grid = new int[6][3][3];
    int[][] topGrid, leftGrid, frontGrid, rightGrid, downGrid, backGrid;
    int[] leftColumn;
    int[] frontColumn;
    int[] rightColumn;
    int[] backColumn;

    public Rubik(int[][][] grid) {
        this.grid = grid;
        top = new Face(grid[0]);
        left = new Face(grid[1]);
        front = new Face(grid[2]);
        right = new Face(grid[3]);
        down = new Face(grid[4]);
        back = new Face(grid[5]);
        topGrid = top.getGrid();
        leftGrid = left.getGrid();
        frontGrid = front.getGrid();
        rightGrid = right.getGrid();
        downGrid = down.getGrid();
        backGrid = back.getGrid();
        leftColumn = leftGrid[0];
        rightColumn = rightGrid[0];
        frontColumn = frontGrid[0];
        backColumn = backGrid[2];
    }

    public Rubik viewRight() {
        int[][] topGrid, leftGrid, frontGrid, rightGrid, downGrid, backGrid;
        topGrid = top.rotateRight().getGrid();
        leftGrid = this.frontGrid;
        frontGrid = this.rightGrid;
        rightGrid = back.rotateHalf().getGrid();
        downGrid = down.rotateLeft().getGrid();
        backGrid = left.rotateHalf().getGrid();
        int[][][] grid = new int[6][3][3];
        grid[0] = topGrid;
        grid[1] = leftGrid;
        grid[2] = frontGrid;
        grid[3] = rightGrid;
        grid[4] = downGrid;
        grid[5] = backGrid;
        return new Rubik(grid);
    }

    public Rubik viewLeft() {
        Rubik rubik = viewRight().viewRight().viewRight();
        return rubik;
    }

    public Rubik viewUp() {
        int[][] topGrid, leftGrid, frontGrid, rightGrid, downGrid, backGrid;
        topGrid = this.backGrid;
        leftGrid = left.rotateRight().getGrid();
        frontGrid = this.topGrid;
        rightGrid = right.rotateLeft().getGrid();
        downGrid = this.frontGrid;
        backGrid = this.downGrid;
        int[][][] grid = new int[6][3][3];
        grid[0] = topGrid;
        grid[1] = leftGrid;
        grid[2] = frontGrid;
        grid[3] = rightGrid;
        grid[4] = downGrid;
        grid[5] = backGrid;
        return new Rubik(grid);
    }
    
    public Rubik viewDown() {
        Rubik rubik = viewUp().viewUp().viewUp();
        return rubik;
    }

    public Rubik upfaceRight() {
        int[][] newtopGrid = new int[3][3];
        int[][] newleftGrid = new int[3][3];
        int[][] newfrontGrid = new int[3][3];
        int[][] newrightGrid = new int[3][3];
        int[][] newdownGrid = new int[3][3];
        int[][] newbackGrid = new int[3][3];
        int[] leftColumn = new int[3];
        int[] frontColumn = new int[3];
        int[] rightColumn = new int[3];
        int[] backColumn = new int[3];
        newtopGrid = top.rotateRight().getGrid();
        leftColumn = this.frontColumn;
        frontColumn = this.rightColumn;
        for (int i = 2; i >= 0; i--) {
            rightColumn[2 - i] = this.backColumn[i];
        }
        for (int i = 2; i >= 0; i--) {
            backColumn[2 - i] = this.leftColumn[i];
        }
        newleftGrid[0] = leftColumn;
        for (int i = 1; i <3; i++) {
            newleftGrid[i] = left.getGrid()[i];
        }
        newfrontGrid[0] = frontColumn;
        for (int i = 1; i < 3; i++) {
            newfrontGrid[i] = front.getGrid()[i];
        }
        newrightGrid[0] = rightColumn;
        for (int i = 1; i < 3; i++) {
            newrightGrid[i] = right.getGrid()[i];
        }
        newbackGrid[2] = backColumn;
        for (int i = 0; i < 2; i++) {
           newbackGrid[i] = back.getGrid()[i];
        }
        newdownGrid = down.getGrid();
        int[][][] grid = new int[6][3][3];
        grid[0] = newtopGrid;
        grid[1] = newleftGrid;
        grid[2] = newfrontGrid;
        grid[3] = newrightGrid;
        grid[4] = newdownGrid;
        grid[5] = newbackGrid;
        return new Rubik(grid);
    }

    public Rubik upfaceLeft() {
        Rubik rubik = upfaceRight().upfaceRight().upfaceRight();
        return rubik;
    }

    public Rubik upfaceHalf() {
        Rubik rubik = upfaceRight().upfaceRight();
        return rubik;
    }

    public Rubik frontfaceRight() {
        Rubik rubik = viewDown().upfaceRight().viewUp();
        return rubik;
    }

    public Rubik frontfaceLeft() {
        Rubik rubik = viewDown().upfaceLeft().viewUp();
        return rubik;
    }

    public Rubik frontfaceHalf() {
        Rubik rubik = viewDown().upfaceHalf().viewUp();
        return rubik;
    }

    public Rubik rightfaceLeft() {
        Rubik rubik = viewRight().viewDown().upfaceLeft().viewUp().viewLeft();
        return rubik;
    }

    public Rubik rightfaceRight() {
        Rubik rubik = viewRight().viewDown().upfaceRight().viewUp().viewLeft();
        return rubik;
    }

    public Rubik rightfaceHalf() {
        Rubik rubik = viewRight().viewDown().upfaceHalf().viewUp().viewLeft();
        return rubik;
    }

    public Rubik leftfaceRight() {
        Rubik rubik = viewLeft().viewDown().upfaceRight().viewUp().viewRight();
        return rubik;
    }

    public Rubik leftfaceLeft() {
        Rubik rubik = viewLeft().viewDown().upfaceLeft().viewUp().viewRight();
        return rubik;
    }

    public Rubik leftfaceHalf() {
        Rubik rubik = viewLeft().viewDown().upfaceHalf().viewUp().viewRight();
        return rubik;
    }

    public Rubik downfaceRight() {
        Rubik rubik = viewUp().viewUp().upfaceRight().viewUp().viewUp();
        return rubik;
    }

    public Rubik downfaceLeft() {
        Rubik rubik = viewDown().viewDown().upfaceLeft().viewUp().viewUp();
        return rubik;
    }

    public Rubik downfaceHalf() {
        Rubik rubik = viewDown().viewDown().upfaceHalf().viewUp().viewUp();
        return rubik;
    }

    public Rubik backfaceRight() {
        Rubik rubik = viewUp().upfaceRight().viewDown();
        return rubik;
    }

    public Rubik backfaceLeft() {
        Rubik rubik = viewUp().upfaceLeft().viewDown();
        return rubik;
    }

    public Rubik backfaceHalf() {
        Rubik rubik = viewUp().upfaceHalf().viewDown();
        return rubik;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 3; i++) {
            sb.append("......");
            for (int j = 0; j < 3; j++) {
                sb.append(String.format("%02d", topGrid[i][j]));
            }
            sb.append("......");
            sb.append("\n");
        }
        StringBuilder sb1 = new StringBuilder();
        StringBuilder sb2 = new StringBuilder();
        StringBuilder sb3 = new StringBuilder();
        StringBuilder sb4 = new StringBuilder();
        StringBuilder sb5 = new StringBuilder();
        StringBuilder sb6 = new StringBuilder();
        StringBuilder sb7 = new StringBuilder();
        StringBuilder sb8 = new StringBuilder();
        StringBuilder sb9 = new StringBuilder();
        for (int j = 0; j < 3; j++) {
            sb1.append(String.format("%02d", leftGrid[0][j]));
            sb2.append(String.format("%02d", frontGrid[0][j]));
            sb3.append(String.format("%02d", rightGrid[0][j]));
        }
        sb.append(sb1.toString());
        sb.append(sb2.toString());
        sb.append(sb3.toString());
        sb.append("\n");
        for (int j = 0; j < 3; j++) {
            sb4.append(String.format("%02d", leftGrid[1][j]));
            sb5.append(String.format("%02d", frontGrid[1][j]));
            sb6.append(String.format("%02d", rightGrid[1][j]));
        }
        sb.append(sb4.toString());
        sb.append(sb5.toString());
        sb.append(sb6.toString());
        sb.append("\n");
        for (int j = 0; j < 3; j++) {
            sb7.append(String.format("%02d", leftGrid[2][j]));
            sb8.append(String.format("%02d", frontGrid[2][j]));
            sb9.append(String.format("%02d", rightGrid[2][j]));
        }
        sb.append(sb7.toString());
        sb.append(sb8.toString());
        sb.append(sb9.toString());
        sb.append("\n");
        for (int i = 0; i < 3; i++) {
            sb.append("......");
            for (int j = 0; j < 3; j++) {
                sb.append(String.format("%02d", downGrid[i][j]));
            }
            sb.append("......");
            sb.append("\n");
        }
        for (int i = 0; i < 3; i++) {
            sb.append("......");
            for (int j = 0; j < 3; j++) {
                sb.append(String.format("%02d", backGrid[i][j]));
            }
            sb.append("......");
            sb.append("\n");
        }
        return sb.toString();
    }
}

