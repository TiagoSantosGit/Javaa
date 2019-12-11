package Thread;

import java.awt.Dimension;

import javax.swing.JDialog;

public class TelaTime extends JDialog {

    public TelaTime() {
        setTitle("Minha tela de thread");
        setSize(new Dimension(600, 300));
        setLocationRelativeTo(null);
        setResizable(false);

        setVisible(true);// tela visivel na tela, sempre o ultimo comando
    }

}
