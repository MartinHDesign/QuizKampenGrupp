package SinglePplayer.Panel;

import javax.swing.*;
import java.awt.*;

public class WAIT extends JPanel {
    private JLabel waitingImage = new JLabel("Gif kommer här. waiting for other player");

    protected int currentCategory = 0;

    private JButton answerQuestions = new JButton("Svara på frågor");
    MasterFrame masterFrame;

    public WAIT(MasterFrame masterFrame) {
        this.masterFrame = masterFrame;

        setLayout(new BorderLayout());
        waitingImage.setSize(new Dimension(500, 510));

        answerQuestions.addActionListener(l -> {
            masterFrame.sendToServer(currentCategory);
        });

        add(waitingImage, BorderLayout.CENTER);
        add(answerQuestions, BorderLayout.SOUTH);
    }

    private void showPopup(JFrame frame) {

        JDialog popupDialog = new JDialog(frame, "Action", true);
        popupDialog.setLayout(new BorderLayout());


        JButton newButton = new JButton("Svara på frågor!");
        newButton.addActionListener(e -> {
            masterFrame.sendToServer(masterFrame.getCurrentCategory());
            popupDialog.dispose();
        });

        popupDialog.add(newButton, BorderLayout.CENTER);
        popupDialog.setSize(200, 100); // Set a small size
        popupDialog.setLocationRelativeTo(frame); // Center on the JFrame
        popupDialog.setVisible(true); // Show the dialog
    }
}



