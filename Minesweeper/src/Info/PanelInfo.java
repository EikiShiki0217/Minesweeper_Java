package Info;

import java.awt.*;

import javax.swing.*;

import Draw.*;

public class PanelInfo extends JPanel{

    private DrawFunctions drawFunctions;

    public PanelInfo() {
        this.drawFunctions = new DrawFunctions();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        //background
        g.setColor(Color.decode("#cbcbcb"));
        g.fillRect(0, 0, getWidth(), getHeight());
        drawFunctions.drawConcaveBorder(g, 0, 0, getWidth(), getHeight());
        drawFunctions.drawConvexBorder(g, 7, 7, getWidth() - 14, 47);
        drawFunctions.drawConvexBorder(g, 7, 58, getWidth() / 2 - 11, getHeight() - 65);
        drawFunctions.drawConvexBorder(g, getWidth() / 2 + 4, 58, getWidth() / 2 - 11, getHeight() - 65);

        drawFunctions.drawTitle(g, getWidth());
    
        g.setFont(new Font("Times new roman", Font.BOLD, 28));
        g.setColor(Color.BLACK);
        g.drawString("Gameplay", 147, 93);
        g.drawString("Control", 550, 93);

        g.setFont(new Font("Times new roman", Font.ITALIC | Font.BOLD, 17));
        g.drawString("~ Understand the principles behind Minesweeper.", 26, 120);
        g.drawString("~ Know what the numbers mean.", 26, 300);
        g.drawString("~ Left Click", (getWidth() / 2) + 30, 120);
        g.drawString("~ Right Click", (getWidth() / 2) + 30, 140);
        g.drawString("~ Press Smile", (getWidth() / 2) + 30, 160);
        g.setFont(new Font("Times new roman", Font.ITALIC, 17));
        g.drawString("Each Minesweeper game starts out with a grid", 40, 140);
        g.drawString("of unmarked squares. After clicking one of", 40, 160);
        g.drawString("these squares, After clicking one of these,", 40, 180);
        g.drawString("some will remain blank, and some will have", 40, 200);
        g.drawString("numbers on them. It's your job to use the", 40, 220);
        g.drawString("numbers to figure out which of the blank", 40, 240);
        g.drawString("squares have mines and which are safe to click.", 40, 260);
        g.drawString("whic  h are safe to click.", 40, 280);
        g.drawString("A number on a square refers to the number of", 40, 320);
        g.drawString("mines that are currently touching that square.", 40, 340);
        g.drawString("For example, if there are two squares touching", 40, 360);
        g.drawString("each other and one of the squares has '1' on it,", 40, 380);
        g.drawString("you know that the square next to it has a mine", 40, 400);
        g.drawString("beneath it.", 40, 420);
        g.drawString("Reveal cells", (getWidth() / 2) + 280, 120);
        g.drawString("Put flag", (getWidth() / 2) + 305, 140);
        g.drawString("Start new game", (getWidth() / 2) + 253, 160);

    }
    
}
