package Client.Panel;

import javax.swing.*;
import java.awt.*;

public class HighScorePanel extends JPanel {
    private JTextArea textArea = new JTextArea("1. Player1 score 6\n2. Player2 score 4");
    private JScrollPane highScoreScrollPane = new JScrollPane(textArea, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
    private JButton exit = new JButton("Meny");

    public HighScorePanel(MasterFrame masterFrame){

        textArea.setEditable(false);
        textArea.setSize(new Dimension(500,510));
        exit.setSize(new Dimension(500,10));

        exit.addActionListener(e -> masterFrame.showPage("2"));

        setLayout(new BorderLayout());
        add(highScoreScrollPane, BorderLayout.CENTER);
        add(exit, BorderLayout.SOUTH);

    }
}
