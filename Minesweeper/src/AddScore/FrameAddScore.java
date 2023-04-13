package AddScore;

import javax.swing.JFrame;

public  class FrameAddScore {

    private static PanelAddScore pAddScore = new PanelAddScore();
    private static JFrame addScoreF = new JFrame();
    public static boolean isSubmited = false;

    public static void run() {
        init();
        if (isSubmited) {
            addScoreF.setVisible(false);
        } else {
            addScoreF.setVisible(true);
        }
    }

    public static void init() {
        addScoreF.setTitle("Add Score");
        addScoreF.setSize(400, 193);
        addScoreF.setLocationRelativeTo(null);
        addScoreF.setResizable(false);
        addScoreF.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        addScoreF.add(pAddScore);
    }
}
