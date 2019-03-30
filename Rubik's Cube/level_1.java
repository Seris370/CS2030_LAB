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
