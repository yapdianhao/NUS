class Face {
    public final int[][] grid;
    public final int[][] gridCopy;

    public Face(final int[][] grid) {
        this.grid = grid;
        this.gridCopy = new int[3][3];
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                gridCopy[i][j] = grid[i][j];
            }
        }
    }

    public final Face rotateRight() {
        int[][] grid = new int[3][3];
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                grid[i][j] = this.gridCopy[2 - j][i];
            }
        }
        return new Face(grid);
    }

    public final Face rotateLeft() {
        Face face = rotateRight().rotateRight().rotateRight();
        return face;
    }

    public final Face rotateHalf() {
        Face face = rotateRight().rotateRight();
        return face;
    }

    public final int[][] getGrid() {
        /*final int[][] finalGridCopy = new int[3][3];
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                finalGridCopy[i][j] = gridCopy[i][j];
            }
        }
        return finalGridCopy;*/
        return this.gridCopy;
    }

    @Override
    public final String toString() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                sb.append(String.format("%02d", gridCopy[i][j]));
            }
            sb.append("\n");
        }
        return sb.toString();
    }
}
