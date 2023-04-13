package AddScore;

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

import Draw.*;
import Launch.*;
import Game.*;

public class PanelAddScore extends JPanel implements MouseListener{

    private boolean press, isSubmited;
    private int mouseX, mouseY;
    // objects
    private JTextField tf;
    private Rectangle subBut;
    private DrawFunctions drawFunctions;
    private FileRead fRead;

    public PanelAddScore(){
        this.tf = new JTextField(10);
        this.drawFunctions = new DrawFunctions();
        this.fRead = new FileRead();
        this.add(tf);
        this.addMouseListener(this);
        this.press = false;
        this.isSubmited = false;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        subBut = new Rectangle((getWidth() - 100) / 2 - 3, 113, 106, 26);

        // background
        g.setColor(Color.decode("#cbcbcb"));
        g.fillRect(0, 0, getWidth(), getHeight());
        drawFunctions.drawConcaveBorder(g, 0, 0, getWidth(), getHeight());
        drawFunctions.drawConvexBorder(g, 7, 7, getWidth() - 14, 47);
        g.setFont(new Font("Times new roman", Font.BOLD, 17));
        g.setColor(Color.BLACK);
        g.drawString("Please your name to submit", 20, 82);
        g.drawString("your score (" + PanelGame.score + ")" , 20, 102);

        drawFunctions.drawTitle(g, getWidth());
        drawFunctions.drawConvexBorder(g, 227, 75, 140, 25);
        tf.setFont(new Font("Times new roman", Font.BOLD, 15));
        tf.setBorder(null);
        tf.setBackground(Color.decode("#cbcbcb"));
        tf.setLocation(233, 78);

        if (press) {
            drawFunctions.drawConvexBorder(g, (getWidth() - 100) / 2 - 3, 113, 106, 26);
            g.setColor(Color.gray);
        }
        if (!press) {
            drawFunctions.drawConcaveBorder(g, (getWidth() - 100) / 2 - 3, 113, 106, 26);
            g.setColor(Color.BLACK);
        }
        g.setFont(new Font("Times new roman", Font.BOLD, 18));
        g.drawString("Submit", (getWidth() - 100) / 2 + 22, 132);
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        repaint();
    }

    @Override
    public void mousePressed(MouseEvent e) {
        mouseX = e.getX();
        mouseY = e.getY();
        if (subBut.contains(mouseX, mouseY)) {
            press = true;
        }
        repaint();
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        mouseX = e.getX();
        mouseY = e.getY();
        if (subBut.contains(mouseX, mouseY)) {
            if (!(tf.getText().equals(""))) {
                if (!isSubmited) {
                    isSubmited = fRead.addName(PanelGame.difficult, tf.getText(), PanelGame.score);
                }
                if (isSubmited){
                    press = false;
                    isSubmited = false;
                    tf.setText(null);
                    if (!FrameAddScore.isSubmited) {
                        FrameAddScore.isSubmited = true;
                        FrameAddScore.run();
                    }
                }
            }
            press = false;
        }
        repaint();
    }

    public void run() {
        FrameAddScore.run();
    }

    @Override
    public void mouseEntered(MouseEvent e) {
    }

    @Override
    public void mouseExited(MouseEvent e) {
    }

}
