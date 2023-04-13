package Game;

import javax.swing.*;

import java.awt.*;
import java.awt.event.*;

import Draw.*;

public class PanelGame extends JPanel implements MouseListener, MouseMotionListener{

    //parameters
    public static int difficult = 1, score = 0, time = 0;
    private int mouseX, mouseY;
    //objects
    private DrawFunctions drawFunctions;
    private FunctionGame functionGame;
    private Timer timer;
    
    public PanelGame() {
        this.drawFunctions = new DrawFunctions();
        this.functionGame = new FunctionGame();
        this.timer = new Timer(1000, new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                if (functionGame.isTimeStart) {
                    score++;
                    time++;
                }
                repaint();
            }
            
        });
        this.timer.start();
    }

    public void newGame(){
        functionGame.newGame(difficult);
        score = 0;
        time = 0;
        repaint();
    }
    
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        //setbound
        for (int i = 0; i < functionGame.cellsBound.length; i++) {
            for (int j = 0; j < functionGame.cellsBound[0].length; j++) {
                int x = 18 + j * 30;
                int y = 115 + i * 30;
                functionGame.cellsBound[i][j] = new Rectangle(x, y, 30, 30);
            }
        }
        functionGame.smile = new Rectangle((getWidth() / 2) - 12, 65, 36, 36);
        //background
        g.setColor(Color.decode("#cbcbcb"));
        g.fillRect(0, 0, getWidth(), getHeight());
        drawFunctions.drawConcaveBorder(g, 0, 0, getWidth(), getHeight());
        drawFunctions.drawConvexBorder(g, 7, 7, getWidth() - 14, 47);
        drawFunctions.drawConvexBorder(g, 7, 58, getWidth() - 14, getHeight() - 65);
        drawFunctions.drawClockBG(g, getWidth(), getHeight());
        //time
        drawFunctions.drawClock(g, getWidth(), getHeight(), time);
        //flag
        drawFunctions.drawFlagNumber(g, getWidth(), getHeight(), functionGame.flag);
        //cells
        drawFunctions.drawCells(g, functionGame.getCurrentCells());
        //smile
        drawFunctions.drawSmile(g, getWidth(), functionGame.isPress, functionGame.isWin, functionGame.isLose, functionGame.onSmile);
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        mouseX = e.getX();
        mouseY = e.getY();
        if (functionGame.isRunning) {
            for (int i = 0; i < functionGame.cellsBound.length; i++) {
                for (int j = 0; j < functionGame.cellsBound[0].length; j++) {
                    if(functionGame.cellsBound[i][j].contains(mouseX, mouseY)) {
                        if(functionGame.getCurrentCells()[i][j] == -1) {
                            functionGame.getCurrentCells()[i][j] = -3;
                        }
                    } else {
                        if(functionGame.getCurrentCells()[i][j] == -3) {
                            functionGame.getCurrentCells()[i][j] = -1;
                        }
                    }
                }
            }
        }
        repaint();
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        mouseX = e.getX();
        mouseY = e.getY();
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        mouseX = e.getX();
        mouseY = e.getY();
        repaint();
    }

    @Override
    public void mousePressed(MouseEvent e) {
        mouseX = e.getX();
        mouseY = e.getY();
        if (functionGame.smile.contains(mouseX, mouseY)) {
            functionGame.onSmile = true;
            repaint();
        } else {
            functionGame.onSmile = false;
            if (functionGame.isRunning) {
                functionGame.isPress = true;
            }
        }
        if (functionGame.isRunning) {
            for (int i = 0; i < functionGame.cellsBound.length; i++) {
                for (int j = 0; j < functionGame.cellsBound[0].length; j++) {
                    if (functionGame.cellsBound[i][j].contains(mouseX, mouseY)) {
                        if (functionGame.getCurrentCells()[i][j] == -1) {
                            functionGame.setCurrentCell(i, j, -3);
                        }
                    } else {
                        if (functionGame.getCurrentCells()[i][j] == -3) {
                            functionGame.setCurrentCell(i, j, -1);
                        }
                    }
                }
            }
        }
        repaint();
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        mouseX = e.getX();
        mouseY = e.getY();
        functionGame.onSmile = false;
        if (functionGame.isRunning && !functionGame.isTimeStart) {
            functionGame.isTimeStart = true;
        }
        if (e.getButton() == MouseEvent.BUTTON1) {
            if (functionGame.isRunning) {
                functionGame.isPress = false;
                for (int i = 0; i < functionGame.cellsBound.length; i++) {
                    for (int j = 0; j < functionGame.cellsBound[0].length; j++) {
                        if (functionGame.cellsBound[i][j].contains(mouseX, mouseY)) {
                            if (functionGame.getCurrentCells()[i][j] != -2) {
                                if (functionGame.getDefaultCells()[i][j] != 9) {
                                    functionGame.setCurrentCell(i, j, functionGame.getDefaultCells()[i][j]);
                                } else {
                                    functionGame.setCurrentCell(i, j, 10);
                                    functionGame.isRunning = false;
                                    functionGame.isTimeStart = false;
                                    functionGame.isLose = true;
                                }
                                functionGame.check(i, j);
                                repaint();
                            }
                        } else {
                            if (functionGame.getCurrentCells()[i][j] == -3) {
                                functionGame.setCurrentCell(i, j, -1);
                            }
                        }
                    }
                }
            }
            if(functionGame.smile.contains(mouseX, mouseY)) {
                newGame();
            }
            /*
             * -3 prss
             * -2 flag
             * -1 notpress
             * 0 empty
             * 1-8 too
             * 9 bomb
             * 10 delbersen bomb
             */
        } else if (e.getButton() == MouseEvent.BUTTON3) {
            if (functionGame.isRunning) {
                functionGame.isPress = false;
                for (int i = 0; i < functionGame.getCurrentCells().length; i++) {
                    for (int j = 0; j < functionGame.getCurrentCells()[0].length; j++) {
                        if (functionGame.cellsBound[i][j].contains(mouseX, mouseY)) {
                            if(functionGame.getCurrentCells()[i][j] == -1 || functionGame.getCurrentCells()[i][j] == -3) {
                                functionGame.setCurrentCell(i, j, -2);
                                functionGame.flag--;
                                repaint();
                            } else if(functionGame.getCurrentCells()[i][j] == -2) {
                                functionGame.setCurrentCell(i, j, -1);
                                functionGame.flag++;
                                repaint();
                            }
                        }
                    }
                }
            }
        }
        repaint();
    }

    @Override
    public void mouseEntered(MouseEvent e) {
    }

    @Override
    public void mouseExited(MouseEvent e) {
    }
}
