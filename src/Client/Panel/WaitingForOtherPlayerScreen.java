package Client.Panel;

import javax.swing.*;
import java.awt.*;

public class WaitingForOtherPlayerScreen extends JPanel {
    private JLabel waitingImage = new JLabel("Gif kommer här. waiting for other player");
    private JButton backToMenu = new JButton("Avbryt");
    MasterFrame masterFrame;

    public WaitingForOtherPlayerScreen( ){
        this.masterFrame = MasterFrame.getInstance();

        setLayout(new BorderLayout());
        waitingImage.setSize(new Dimension(500,510));
        backToMenu.setSize(new Dimension(500,10));

        backToMenu.addActionListener(e -> {
            masterFrame.showPage("menu");
        });

        add(waitingImage, BorderLayout.CENTER);
        add(backToMenu, BorderLayout.SOUTH);
    }


}
