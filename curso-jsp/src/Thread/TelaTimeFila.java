package Thread;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class TelaTimeFila extends JDialog {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    private JPanel jPanel = new JPanel(new GridBagLayout());
    private JLabel descricaoHora = new JLabel("Nome");
    private JLabel descricaoHora2 = new JLabel("E-Mail");
    private JTextField mostraTempo = new JTextField();
    private JTextField mostraTempo2 = new JTextField();
    private JButton jButton = new JButton("Add Lista");
    private JButton jButton2 = new JButton("Stop");

    private ImplementacaoFilaThread fila = new ImplementacaoFilaThread();

    public TelaTimeFila() {
        setTitle("Minha tela de thread");
        setSize(new Dimension(600, 300));
        setLocationRelativeTo(null);
        setResizable(false);

        // controlador de posicionamento de componentes
        GridBagConstraints gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.insets = new Insets(5, 10, 5, 5); // espacamentos
        gridBagConstraints.anchor = GridBagConstraints.WEST;

        descricaoHora.setPreferredSize(new Dimension(200, 25));
        gridBagConstraints.gridy++;
        jPanel.add(descricaoHora, gridBagConstraints);

        mostraTempo.setPreferredSize(new Dimension(200, 25));
        gridBagConstraints.gridy++;
        jPanel.add(mostraTempo, gridBagConstraints);

        descricaoHora2.setPreferredSize(new Dimension(200, 25));
        gridBagConstraints.gridy++;
        jPanel.add(descricaoHora2, gridBagConstraints);

        mostraTempo2.setPreferredSize(new Dimension(200, 25));
        gridBagConstraints.gridy++;
        jPanel.add(mostraTempo2, gridBagConstraints);

        gridBagConstraints.gridwidth = 1;

        jButton.setPreferredSize(new Dimension(92, 25));
        gridBagConstraints.gridy++;
        jPanel.add(jButton, gridBagConstraints);

        jButton2.setPreferredSize(new Dimension(92, 25));
        gridBagConstraints.gridx++;
        jPanel.add(jButton2, gridBagConstraints);

        jButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {

                for (int qtd = 0; qtd < 100; qtd++) { // simulando 100 evios em massa

                    ObjetoFilaThread filaThread = new ObjetoFilaThread();
                    filaThread.setNome(mostraTempo.getText());
                    filaThread.setEmail(mostraTempo2.getText() + " - " + qtd);
                    // fila.add(filaThread);
                    ImplementacaoFilaThread.add(filaThread);
                }
            }
        });

        jButton2.addActionListener(new ActionListener() {

            @SuppressWarnings("deprecation")
	    @Override
            public void actionPerformed(ActionEvent e) {

                if (jButton2.getText().equals("Stop")) {
                    fila.stop();
                    jButton2.setText("Start");
                } else {
                    fila.start();
                    jButton2.setText("Stop");
                }
            }
        });

        fila.start();
        add(jPanel, BorderLayout.WEST);
        setVisible(true);// tela visivel na tela, sempre o ultimo comando
    }

}
