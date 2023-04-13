package Game;

import java.awt.Rectangle;
import java.util.Random;

import AddScore.*;
public class FunctionGame {

    //cells
    private int[][] defaultCells, currentCells;
    //bounds
    public Rectangle[][] cellsBound;
    public Rectangle smile;

    //parameters
    public int mines, flag, correct;
    public boolean isRunning, isWin, isTimeStart, isPress, isLose, onSmile;

    public void newGame(int difficult) {
        int[] size = setSize(difficult);
        defaultCells = new int[size[0]][size[1]];
        currentCells = new int[size[0]][size[1]];
        cellsBound = new Rectangle[size[0]][size[1]];
        mines = size[2];

        isRunning = true;
        isTimeStart = false;
        isWin = false;
        isPress = false;
        isLose = false;
        onSmile = false;
        correct = 0;
        flag = mines;
        setMines();
    }

    private int[] setSize(int difficult) {
        int[] size = new int[3];
        switch (difficult) {
            case 1:
                size[0] = 9;
                size[1] = 9;
                size[2] = 2; //10
                break;
        
            case 2:
                size[0] = 16;
                size[1] = 16;
                size[2] = 40;//40
                break;
    
            case 3:
                size[0] = 16;
                size[1] = 30;
                size[2] = 99;//99
                break;
    
            default:
                break;
        }
        return size;
    }

    private void setMines() {
        Random random = new Random();
        for (int i = 0; i < defaultCells.length; i++) {
            for (int j = 0; j < defaultCells[0].length; j++) {
                currentCells[i][j] = -1;
                defaultCells[i][j] = 0;
            }
        }
        for (int i = 1; i <= mines; i++) {
            int j, k;
            do {
                j = random.nextInt(defaultCells.length);
                k = random.nextInt(defaultCells[0].length);
            } while (defaultCells[j][k] == 9);

            defaultCells[j][k] = 9;
            for (int j2 = -1; j2 <= 1; j2++) {
                for (int k2 = -1; k2 <= 1; k2++) {
                    int row = j + j2, col = k + k2;
                    if (row != -1 && row != defaultCells.length) {
                        if (col != -1 && col != defaultCells[0].length) {
                            if (defaultCells[row][col] != 9) {
                                defaultCells[row][col]++;
                            }
                        }
                    }
                }
            }
        }
        for (int i = 0; i < cellsBound.length; i++) {
            for (int j = 0; j < cellsBound.length; j++) {
                System.out.print(defaultCells[i][j] + " ");
            }
            System.out.println("");
        }
    }

    public void check(int i, int j){
        if(currentCells[i][j] == 0) {
            for (int k1 = -1; k1 <= 1; k1++) {
                for (int k2 = -1; k2 <= 1; k2++) {
                    int row = i + k1, col = j + k2;
                    if(row > -1 && row < defaultCells.length) {
                        if(col > -1 && col < defaultCells[0].length) {
                            if (currentCells[row][col] == -1) {
                                currentCells[row][col] = defaultCells[row][col];
                                check(row, col);
                            }
                        }
                    }
                }
            }
        } else if (currentCells[i][j] == 10) {
            for (int k1 = 0; k1 < currentCells.length; k1++) {
                for (int k2 = 0; k2 < currentCells[0].length; k2++) {
                    if (currentCells[k1][k2] != 10) {
                        if(defaultCells[k1][k2] == 9) {
                            currentCells[k1][k2] = 9;
                        }
                    }
                }
            }
        }
    }

    private void checkWin() {
        correct = 0;
        for (int i = 0; i < currentCells.length; i++) {
            for (int j = 0; j < currentCells[0].length; j++) {
                if (currentCells[i][j] == -2){
                    if (defaultCells[i][j] == 9) {
                        correct++;
                    }
                }
            }
        }
        if (correct == mines) {
            isWin = true;
            isRunning = false;
            isTimeStart = false;
            FrameAddScore.run();
        }
    }

    public int[][] getDefaultCells() {
        return defaultCells;
    }

    public int[][] getCurrentCells() {
        return currentCells;
    }

    public void setCurrentCell(int i, int j, int element) {
        this.currentCells[i][j] = element;
        checkWin();         
    }
}
