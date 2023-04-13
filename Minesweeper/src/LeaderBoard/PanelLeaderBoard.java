package LeaderBoard;

import java.awt.*;
import javax.swing.*;
import java.util.ArrayList;

import Draw.*;
import Launch.*;

public class PanelLeaderBoard extends JPanel{

    private DrawFunctions drawFunctions;
    private FileRead fRead;

    public PanelLeaderBoard() {
        this.drawFunctions = new DrawFunctions();
        this.fRead = new FileRead();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        //background
        g.setColor(Color.decode("#cbcbcb"));
        g.fillRect(0, 0, getWidth(), getHeight());

        drawFunctions.drawConcaveBorder(g, 0, 0, getWidth(), getHeight());
        drawFunctions.drawConvexBorder(g, 7, 7, getWidth() - 14, 47);
        for (int i = 1; i <= 3; i++) {
            int colStart = getWidth() / 3 * (i - 1);
            int colEnd = getWidth() / 3 * i;
            drawFunctions.drawConvexBorder(g, colStart + 7, 58, getWidth() / 3 - 14, 30);

            int y = 92, number = 1;
            ArrayList<String> lbList = new ArrayList<>();
            lbList = fRead.list(i);
            do {
                g.setColor(Color.black);
                g.setFont(new Font("Times new roman", Font.PLAIN, 18));
                if (number <= lbList.size()) {
                    String[] score = lbList.get(number - 1).split(" ");
                    g.drawString(score[0], colStart + 50, y + 54);
                    g.drawString(score[1] + "sec", colEnd - 60, y + 54);
                }
                g.setFont(new Font("Times new roman", Font.BOLD, 18));
                if(number < 10){
                    g.drawString("" + number++, colStart + 17, y + 54);
                } else {
                    g.drawString("" + number++, colStart + 12, y + 54);
                }
                drawFunctions.drawConvexBorder(g, colStart + 7, y, 30, 30);
                drawFunctions.drawConvexBorder(g, colStart + 41, y, colEnd - 71 - (colStart + 41), 30);
                drawFunctions.drawConvexBorder(g, colEnd - 67, y, colEnd - 7 - (colEnd - 67), 30);
                y += 34;
            } while (y < getHeight() - 10);
            g.setColor(Color.BLACK);
            g.drawString("№", colStart + 12, 112);
            g.drawString("Name", colStart + 50, 112);
            g.drawString("Time", colEnd - 60, 112);
            g.drawString("Beginner", 88, 79);
            g.drawString("Intermediater", 314, 79);
            g.drawString("Expert", 590, 79);
            drawFunctions.drawTitle(g, getWidth());
            
        }
    }
    
}
