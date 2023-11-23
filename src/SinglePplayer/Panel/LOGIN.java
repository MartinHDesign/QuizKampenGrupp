package SinglePplayer.Panel;

import javax.swing.*;
import java.awt.*;


public class LOGIN extends JPanel {
    private JLabel welcomeImage = new JLabel(new ImageIcon("src/SinglePplayer/Panel/GifAndImages/quizlogin.gif"));
    private JTextField enterUsername = new JTextField();
    private JButton login = new JButton("Login");
    private JPanel southPanel = new JPanel();
    MasterFrame masterFrame;

    public LOGIN(MasterFrame masterFrame){
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
            Object userName = enterUsername.getText();
            masterFrame.startConnection(userName);
            masterFrame.setTitleNameToUserName("Jesus Quiztus "+enterUsername.getText());
            masterFrame.showPage("MENU");
            System.out.println(userName + " connected to server");
            MasterFrame.ReadFromServer rf = masterFrame.getReadFromServer();
            Thread t = new Thread(rf);
            t.start();
        });

        add(southPanel,BorderLayout.SOUTH);
    }
}
