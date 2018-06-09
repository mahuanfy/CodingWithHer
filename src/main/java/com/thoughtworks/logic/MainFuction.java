package com.thoughtworks.logic;

public class MainFuction {
    private int n = 100;
    public int[][] matrix = new int[n][n];
    public int[][] nextmatrix = new int[n][n];

    public void setN(int n) {
        this.n = n;
    }

    public int getN() {
        return n;
    }

    public void initMatrix() {
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                matrix[i][j] = (int) Math.round(Math.random());
            }
        }

//        matrix = new int[][]{{0, 0, 0}, {1, 1, 1}, {0, 0, 0}};
//        matrix = new int[][]{{0, 0, 0, 0, 0}, {0, 0, 0, 0, 0}, {1, 1, 1, 1, 1}, {0, 0, 0, 0, 0}, {0, 0, 0, 0, 0}};
    }

    public void setMatrix() {
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                matrix[i][j] = nextmatrix[i][j];
                nextmatrix[i][j] = 0;
            }
        }
    }

    public int getNum(int x, int y, int matrix[][]) {
        int num = 0;
        int d[][] = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}, {1, 1}, {1, -1}, {-1, -1}, {-1, 1}};
        for (int i = 0; i < 8; i++) {
            int xx = x + d[i][0];
            int yy = y + d[i][1];
            if (xx >= 0 && xx < n && yy >= 0 && yy < n && matrix[xx][yy] != 0) {
                num++;
            }
        }
        return num;
    }

    public void changeState(int x, int y) {
        int neighbour = getNum(x, y, matrix);
        if (neighbour == 3) {
            nextmatrix[x][y] = matrix[x][y] + 1;
        } else if (neighbour == 2) {
            if (matrix[x][y] > 0) {
                nextmatrix[x][y] = matrix[x][y] + 1;
            }
        } else {
            nextmatrix[x][y] = 0;
        }
    }

    public void check() {
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                changeState(i, j);
            }
        }
        setMatrix();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void printWhole() {
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                System.out.print(matrix[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println();
    }
}
