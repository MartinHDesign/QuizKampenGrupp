package Panel;

import javax.swing.*;
import java.awt.*;

public class WaitingForOtherPlayerScreen extends JPanel {
    private JLabel waitingImage = new JLabel("Gif kommer här. waiting for other player");
    private JButton backToMenu = new JButton("Avbryt");
    MasterFrame masterFrame;

    public WaitingForOtherPlayerScreen(MasterFrame masterFrame){
        this.masterFrame = masterFrame;

        setLayout(new BorderLayout());
        waitingImage.setSize(new Dimension(500,510));
        backToMenu.setSize(new Dimension(500,10));

        backToMenu.addActionListener(e -> {
            masterFrame.showPage("2");
        });

        add(waitingImage, BorderLayout.CENTER);
        add(backToMenu, BorderLayout.SOUTH);
    }


}
