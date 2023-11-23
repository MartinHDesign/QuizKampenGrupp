package Client.Panel;

import javax.swing.*;
import java.awt.*;


public class LoginPanel extends JPanel {
    private JLabel welcomeImage = new JLabel(new ImageIcon("src/Client/Panel/GifAndImages/quizlogin.gif"));
    private JTextField enterUsername = new JTextField();
    private JButton login = new JButton("Login");
    private JPanel southPanel = new JPanel();
    MasterFrame masterFrame;

    public LoginPanel(){


        SwingUtilities.invokeLater(()-> {
            this.setLayout(new BorderLayout());
        });



        welcomeImage.setSize(new Dimension(500,500));
        enterUsername.setSize(new Dimension(500,10));
        login.setSize(new Dimension(500,10));

        add(welcomeImage, BorderLayout.CENTER);

        southPanel.setLayout(new GridLayout(2,1));

        southPanel.add(enterUsername);
        southPanel.add(login);

        login.addActionListener(e -> {
            this.masterFrame = MasterFrame.getInstance();
            masterFrame.showPage("menu");
            masterFrame.revalidate();
            masterFrame.repaint();
            // koppla upp till server
            // skicka med username från textField
            // byt till menu fönster
        });

        add(southPanel,BorderLayout.SOUTH);
    }

        public static void main(String[] args) {

    }
}
