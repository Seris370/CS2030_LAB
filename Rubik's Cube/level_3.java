public class Face {
    private int[][] grid = new int[3][3];

    public Face(int[][] _grid) {
      for (int i = 0; i < 3; i++) {
          for (int j = 0; j < 3; j++) {
              grid[i][j] = _grid[i][j];
          }
      }
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
        return new Face(grid).grid;
    }

    public String toString() {
        int r = grid.length;
        String str = "";
        for (int i = 0; i < r; i++) {
            for (int j = 0; j < r; j++) {
                str += String.format("%02d", grid[i][j]);
            }
            str += String.format("\n");
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

    public Rubik frontfaceRight() {
      int[][][] newGrid = this.grid.clone();
      for (int i = 0; i < 3; i++) {
            newGrid[0][2][i] = L.getGrid()[2-i][2];
            newGrid[1][i][2] = D.getGrid()[0][i];
            newGrid[4][0][2-i] = R.getGrid()[i][0];
            newGrid[3][i][0] = U.getGrid()[2][i];
      }
      newGrid[2] = F.rotateRight().getGrid();
      return new Rubik(newGrid);
    }

    public Rubik frontfaceLeft() {
        int[][][] newGrid = this.grid.clone();
        for (int i = 0; i < 3; i++) {
            newGrid[1][i][2] = U.getGrid()[2][2-i];
            newGrid[0][2][i] = R.getGrid()[i][0];
            newGrid[3][i][0] = D.getGrid()[0][2-i];
            newGrid[4][0][i] = L.getGrid()[i][2];
        }
        newGrid[2] = F.rotateLeft().getGrid();
        return new Rubik(newGrid);
    }

    public Rubik frontfaceHalf() {
        return this.frontfaceLeft().frontfaceLeft();
    }

    public Rubik rightfaceRight() {
        return this.viewRight().frontfaceRight().viewLeft();
    }

    public Rubik rightfaceLeft() {
        return this.viewRight().frontfaceLeft().viewLeft();
    }

    public Rubik rightfaceHalf() {
        return this.viewRight().frontfaceHalf().viewLeft();
    }

    public Rubik leftfaceRight() {
        return this.viewLeft().frontfaceRight().viewRight();
    }

    public Rubik leftfaceLeft() {
        return this.viewLeft().frontfaceLeft().viewRight();
    }

    public Rubik leftfaceHalf() {
        return this.viewLeft().frontfaceHalf().viewRight();
    }

    public Rubik upfaceRight() {
        return this.viewUp().frontfaceRight().viewDown();
    }

    public Rubik upfaceLeft() {
        return this.viewUp().frontfaceLeft().viewDown();
    }

    public Rubik upfaceHalf() {
        return this.viewUp().frontfaceHalf().viewDown();
    }

    public Rubik downfaceRight() {
        return this.viewDown().frontfaceRight().viewUp();
    }

    public Rubik downfaceLeft() {
        return this.viewDown().frontfaceLeft().viewUp();
    }

    public Rubik downfaceHalf() {
        return this.viewDown().frontfaceHalf().viewUp();
    }

    public Rubik backfaceRight() {
        return this.viewUp().viewUp().frontfaceRight().viewDown().viewDown();
    }

    public Rubik backfaceLeft() {
        return this.viewUp().viewUp().frontfaceLeft().viewDown().viewDown();
    }

    public Rubik backfaceHalf() {
        return this.viewUp().viewUp().frontfaceHalf().viewDown().viewDown();
    }
}
