package Info;

import javax.swing.*;

import Launch.*;

public class FrameInfo extends JFrame implements InterfaceFrame{

    private PanelInfo pInfo;

    public FrameInfo() {
        this.setTitle("Info");

        this.pInfo = new PanelInfo();
    }

    @Override
    public void run() {
        init();
    }

    @Override
    public void init() {
        setSize(800, 600);
        setResizable(false);
        setLocationRelativeTo(null);
        add(pInfo);

        setVisible(true);
    }
}