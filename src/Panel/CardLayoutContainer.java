package Panel;

import javax.swing.*;
import java.awt.*;

public class CardLayoutContainer extends JPanel {
    LoginPanel loginScreen= new LoginPanel();
    public CardLayoutContainer(CardLayout layout){
        setLayout(layout);

        add(loginScreen, "1");

//        layout.show(CardLayoutContainer, "1");
    }


}
