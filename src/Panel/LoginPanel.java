package Panel;

import javax.swing.*;
import java.awt.*;

public class LoginPanel extends JPanel {
    JLabel welcomeImage = new JLabel("Här kommer det vara en bild med text som tex \n" +
            " Welcome to Quizkampen. Enter user name:");
    JTextField enterUsername = new JTextField();
    JButton login = new JButton("Login");
    JPanel southPanel = new JPanel();

    public LoginPanel(){
        setLayout(new BorderLayout());

        welcomeImage.setSize(new Dimension(500,500));
        enterUsername.setSize(new Dimension(500,10));
        login.setSize(new Dimension(500,10));

        add(welcomeImage, BorderLayout.CENTER);

        southPanel.setLayout(new GridLayout(2,1));

        southPanel.add(enterUsername);
        southPanel.add(login);

        add(southPanel,BorderLayout.SOUTH);
    }

    public static void main(String[] args) {

    }
}
