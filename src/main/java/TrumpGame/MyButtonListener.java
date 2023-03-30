package TrumpGame;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MyButtonListener implements ActionListener {
    private final DaifugoApplication simple1;

    public MyButtonListener(DaifugoApplication simple1) {
        this.simple1 = simple1;
    }

    public void actionPerformed(ActionEvent arg0) {
        //simple1.echoButton();
    }

}
