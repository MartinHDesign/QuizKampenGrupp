package SinglePplayer.Panel;

import javax.swing.*;
import java.awt.*;

public class CATEGORY extends JPanel {

    private JLayeredPane layeredPane;
    private JPanel popupPanel;
    private JButton history = new JButton("HISTORIA");
    private JButton sport = new JButton("SPORT");
    private JButton music = new JButton("MUSIK");
    private MasterFrame masterFrame;

    public CATEGORY(MasterFrame masterFrame) {
        this.masterFrame = masterFrame;
        setLayout(new BorderLayout());

        // LayeredPane setup
        layeredPane = new JLayeredPane();
        layeredPane.setPreferredSize(new Dimension(500, 200));

        // Adding buttons to the layeredPane on the default layer
        JPanel buttonsPanel = new JPanel(new GridLayout(5, 1));
        buttonsPanel.add(history);
        buttonsPanel.add(sport);
        buttonsPanel.add(music);
        buttonsPanel.setBounds(0, 0, 500, 520);

        layeredPane.add(buttonsPanel, JLayeredPane.DEFAULT_LAYER);

        initPopupPanel();
        layeredPane.add(popupPanel, JLayeredPane.POPUP_LAYER);

        add(layeredPane, BorderLayout.CENTER);

        history.addActionListener(l -> {
            masterFrame.sendToServer("history");
        });
        sport.addActionListener(l -> {
            masterFrame.sendToServer("sport");
        });
        music.addActionListener(l -> {
            masterFrame.sendToServer("music");
        });
    }

    private void initPopupPanel() {
        popupPanel = new JPanel();
        popupPanel.setLayout(new BorderLayout());
        JButton button = new JButton("Börja svara på frågor");
        button.addActionListener(e -> {
            masterFrame.sendToServer(masterFrame.getCurrentCategory());
            togglePopupVisibility(false);
        });

        popupPanel.add(button, BorderLayout.CENTER);
        popupPanel.setBounds(50, 50, 200, 100);
        popupPanel.setVisible(false);
    }

    private void togglePopupVisibility(boolean visible) {
        popupPanel.setVisible(visible);
        layeredPane.moveToFront(popupPanel);
    }
}
