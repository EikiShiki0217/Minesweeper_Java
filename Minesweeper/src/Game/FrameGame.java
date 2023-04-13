package Game;

import java.awt.Dimension;
import java.awt.event.*;

import javax.swing.*;
import Launch.*;
import Info.*;
import LeaderBoard.*;

public class FrameGame extends JFrame implements InterfaceFrame{

    /**
     * @param width is width of game frame
     * @param height is height of game frame
     */

    private int width, height;

    /**
     * 
     */
    private PanelGame pGame;
    private JMenuBar menuBar;
    private JMenu gameModeItemList;
    private JMenuItem beginnerItem, IntermediaterItem, expertItem, infoItem, leaderBoardItem;

    public FrameGame() {
        this.width = 34 + 30 * 9;
        this.height = 131 + 30 * 9;
        this.setTitle("Minesweeper");
        this.pGame = new PanelGame();
        //menubar
        this.menuBar = new JMenuBar();
        this.gameModeItemList = new JMenu("Game");
        this.beginnerItem = new JMenuItem("Beginner");
        this.IntermediaterItem = new JMenuItem("Intermediater");
        this.expertItem = new JMenuItem("Expert");
        this.infoItem = new JMenuItem("Info");
        this.leaderBoardItem = new JMenuItem("Leader Board");
        this.setMenuBar();
    }

    @Override
    public void run() {
        init();
    }

    @Override
    public void init() {
        setSize(width, height);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setLocationRelativeTo(null);
        add(pGame);
        setJMenuBar(menuBar);
        addMouseListener(pGame);
        addMouseMotionListener(pGame);
        pGame.newGame();
        setVisible(true);
    }

    private void setMenuBar() {
        menuBar.setSize(width, 30);
        gameModeItemList = new JMenu("Game");
        infoItem = new JMenuItem("Info");
        infoItem.setMaximumSize(new Dimension(35, 30));
        infoItem.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                FrameInfo fInfo = new FrameInfo();
                fInfo.run();
            }
        });
        leaderBoardItem = new JMenuItem("LeaderBoard");
        leaderBoardItem.setMaximumSize(new Dimension(90, 30));
        leaderBoardItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                FrameLeaderBoard fLeaderBoard = new FrameLeaderBoard();
                fLeaderBoard.run();
            }
        });
        beginnerItem = new JMenuItem("Beginner");
        beginnerItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                changeDifficult(1);
            }
        });
        IntermediaterItem = new JMenuItem("Intermediater");
        IntermediaterItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                changeDifficult(2);
            }
        });
        expertItem = new JMenuItem("Expert");
        expertItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                changeDifficult(3);
            }
        });
        gameModeItemList.add(beginnerItem);
        gameModeItemList.add(IntermediaterItem);
        gameModeItemList.add(expertItem);
        menuBar.add(gameModeItemList);
        menuBar.add(infoItem);
        menuBar.add(leaderBoardItem);
    }
    
    private void changeDifficult(int difficult) {
        if (difficult == 1) {
            width = 34 + 30 * 9;
            height = 131 + 30 * 9;
        } else if (difficult == 2) {
            width = 34 + 30 * 16;
            height = 131 + 30 * 16;
        } else if (difficult == 3) {
            width = 34 + 30 * 30;
            height = 131 + 30 * 16;
        }
        this.setSize(width, height);
        this.setLocationRelativeTo(null);
        this.repaint();
        pGame.repaint();
        PanelGame.difficult = difficult;
        pGame.newGame();
    }
}
