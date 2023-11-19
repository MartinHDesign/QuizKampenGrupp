package Client.Panel;

import javax.swing.*;
import java.awt.*;

public class LoginPanel extends JPanel {

    private JLabel welcomeImage = new JLabel("Här kommer det vara en bild med text som tex \n" +
            " Welcome to Quizkampen. Enter user name:");
    private JTextField enterUsername = new JTextField();
    private JButton login = new JButton("Login");
    private JPanel southPanel = new JPanel();
    MasterFrame masterFrame;

    public LoginPanel(MasterFrame masterFrame){
        this.masterFrame = masterFrame;

        setLayout(new BorderLayout());

        welcomeImage.setSize(new Dimension(500,500));
        enterUsername.setSize(new Dimension(500,10));
        login.setSize(new Dimension(500,10));

        add(welcomeImage, BorderLayout.CENTER);

        southPanel.setLayout(new GridLayout(2,1));

        southPanel.add(enterUsername);
        southPanel.add(login);

        login.addActionListener(e -> {
            masterFrame.showPage("2");
            System.out.println("byt till skärm 2");
        });

        add(southPanel,BorderLayout.SOUTH);
    }

    public static void main(String[] args) {

    }
}
