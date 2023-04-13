package LeaderBoard;

import javax.swing.JFrame;

import Launch.*;

public class FrameLeaderBoard extends JFrame implements InterfaceFrame{

    private PanelLeaderBoard pLeaderBoard;
    public FrameLeaderBoard() {
        this.setTitle("Leader Board");

        this.pLeaderBoard = new PanelLeaderBoard();
    }
    @Override
    public void run() {
        init();
    }

    @Override
    public void init() {
        setSize(750, 575);
        setResizable(false);
        setLocationRelativeTo(null);
        add(pLeaderBoard);
        setVisible(true);
    }
}
