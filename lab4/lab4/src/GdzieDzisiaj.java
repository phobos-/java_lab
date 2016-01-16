import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GdzieDzisiaj extends JFrame {
    private JButton clickMeButton;
    private JPanel rootPanel;

    public GdzieDzisiaj(){
        super("Hello World!");

        setContentPane(rootPanel);

        pack();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        clickMeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                DrawGraph.createAndShowGui();
            }
        });

        setVisible(true);
    }
}
