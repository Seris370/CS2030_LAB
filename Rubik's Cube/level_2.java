public class Face {
    private int[][] grid;

    public Face(int[][] _grid) {
      int[][] grid = {{1,2,3},{4,5,6},{7,8,9}};
      grid[0] = _grid[0].clone();
      grid[1] = _grid[1].clone();
      grid[2] = _grid[2].clone();
      this.grid = grid;
    }

    public Face rotateRight() {
        int newGrid[][] = new int[3][3];
        int[][] grid = {{1,2,3},{4,5,6},{7,8,9}};
        grid[0] = this.grid[0].clone();
        grid[1] = this.grid[1].clone();
        grid[2] = this.grid[2].clone();
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                newGrid[i][j] = grid[2 - j][i];
            }
        }
        return new Face(newGrid);
    }

    public Face rotateLeft() {
        int[][] newGrid = new int[3][3];
        int[][] grid = {{1,2,3},{4,5,6},{7,8,9}};
        grid[0] = this.grid[0].clone();
        grid[1] = this.grid[1].clone();
        grid[2] = this.grid[2].clone();
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                newGrid[2 - j][i] = grid[i][j];
            }
        }
        return new Face(newGrid);
    }

    public Face rotateHalf() {
        return this.rotateLeft().rotateLeft();
    }

    public int[][] getGrid() {
        return this.grid;
    }

    public String toString() {
        int r = grid.length;
        String str = "";
        for (int i = 0; i < r; i++) {
            for (int j = 0; j < r; j++) {
                str += String.format("%02d", grid[i][j]);
                if (j == r - 1) {
                    str += String.format("\n");
                }
            }
        }
        return str;
    }

    public String pR() {
        int r = grid.length;
        String str = "";
        String dot = "......";
        for (int i = 0; i < r; i++) {
            str += dot;
            for (int j = 0; j < r; j++) {
                str += String.format("%02d", grid[i][j]);
            }
            str += dot;
            str += "\n";
        }
        return str;
    }
}

public class Rubik {
    private int[][][] grid;
    private int r;
    private Face F, R, U, L, B, D;

    public Rubik(int[][][] grid) {
        this.grid = grid;
        this.U = new Face(grid[0]);
        this.L = new Face(grid[1]);
        this.F = new Face(grid[2]);
        this.R = new Face(grid[3]);
        this.D = new Face(grid[4]);
        this.B = new Face(grid[5]);
    }

    public Rubik viewRight() {
        int[][][] newGrid = this.grid.clone();
        newGrid[0] = U.rotateRight().getGrid();
        newGrid[1] = F.getGrid();
        newGrid[2] = R.getGrid();
        newGrid[3] = B.rotateHalf().getGrid();
        newGrid[4] = D.rotateLeft().getGrid();
        newGrid[5] = L.rotateHalf().getGrid();
        return new Rubik(newGrid);
    }

    public Rubik viewLeft() {
      int[][][] newGrid = this.grid.clone();
      newGrid[0] = U.rotateLeft().getGrid();
      newGrid[1] = B.rotateHalf().getGrid();
      newGrid[2] = L.getGrid();
      newGrid[3] = F.getGrid();
      newGrid[4] = D.rotateRight().getGrid();
      newGrid[5] = R.rotateHalf().getGrid();
        return new Rubik(newGrid);
    }

    public Rubik viewUp() {
      int[][][] newGrid = this.grid.clone();
      newGrid[0] = B.getGrid();
      newGrid[1] = L.rotateRight().getGrid();
      newGrid[2] = U.getGrid();
      newGrid[3] = R.rotateLeft().getGrid();
      newGrid[4] = F.getGrid();
      newGrid[5] = D.getGrid();
        return new Rubik(newGrid);
    }

    public Rubik viewDown() {
      int[][][] newGrid = this.grid.clone();
      newGrid[0] = F.getGrid();
      newGrid[1] = L.rotateLeft().getGrid();
      newGrid[2] = D.getGrid();
      newGrid[3] = R.rotateRight().getGrid();
      newGrid[4] = B.getGrid();
      newGrid[5] = U.getGrid();
        return new Rubik(newGrid);
    }

    public String toString() {
        String str = "";
        str += U.pR();
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                str += String.format("%02d", L.getGrid()[i][j]);
            }
            for (int j = 0; j < 3; j++) {
                str += String.format("%02d", F.getGrid()[i][j]);
            }
            for (int j = 0; j < 3; j++) {
                str += String.format("%02d", R.getGrid()[i][j]);
            }
            str += "\n";
        }
        str += D.pR();
        str += B.pR();
        return str;
    }

    // public Rubik frontFaceRight() {
    //     for (int i = 0; i < 3; i++) {
    //         int temp = U[2][i];
    //         U[2][i] = L[2-i][2];
    //         L[2-i][2] = B[0][2-i];
    //         B[0][2-i] = R[i][0];
    //         R[i][0] = temp;
    //     }
    //     return new Rubik({U, L, F.rotateRight(), R, D, B});
    // }
}
