package Client.Panel;

import java.io.IOException;

public class GameClient {

    public static void main(String[] args) throws IOException, ClassNotFoundException {

        MasterFrame masterFrame = new MasterFrame();

        ProtocolGamePanel protocol = new ProtocolGamePanel(masterFrame);


        while (true) {
            System.out.println("nu är vi här");


            protocol.panelProcess();
            protocol.gameProcess(masterFrame);


        }
    }
}
