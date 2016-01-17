import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class GdzieDzisiaj extends JFrame {
    private JButton clickMeButton;
    private JPanel rootPanel;
    private JButton pickPubButton;
    private JLabel googleMaps;
    private JTextField textFieldName;
    private JTextField textFieldScore;
    private JTextField textFieldAddr;

    private Random random;

    public GdzieDzisiaj(List<Pub> pubs){
        super("Gdzie dzisiaj?");

        random = new Random();

        setContentPane(rootPanel);

        pack();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        clickMeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                List<Integer> scores = new ArrayList<>();
                pubs.forEach(l -> scores.add(l.getPickCount()));
                DrawGraph.createAndShowGui(scores);
            }
        });

        pickPubButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Pub pub = pubs.get(random.nextInt(pubs.size()));
                pub.pick();
                textFieldName.setText(pub.getName());
                textFieldAddr.setText(pub.getAddr());
                textFieldScore.setText(pub.getScore().toString());
                drawMap(pub.getCoords());
            }
        });

        setVisible(true);
    }

    public void drawMap(String center){
        //JFrame test = new JFrame("Google Maps");

        try {
            String imageUrl = "http://maps.googleapis.com/maps/api/staticmap?center="+ center + "&zoom=17&scale=1&size=600x600&maptype=roadmap&key=ABQIAAAAgb5KEVTm54vkPcAkU9xOvBR30EG5jFWfUzfYJTWEkWk2p04CHxTGDNV791-cU95kOnweeZ0SsURYSA&format=jpg&visual_refresh=true&markers=size:mid%7Ccolor:0xff0000%7Clabel:1%7C"+center;
            String destinationFile = "image.jpg";
            URL url = new URL(imageUrl);
            InputStream is = url.openStream();
            OutputStream os = new FileOutputStream(destinationFile);

            byte[] b = new byte[2048];
            int length;

            while ((length = is.read(b)) != -1) {
                os.write(b, 0, length);
            }

            is.close();
            os.close();
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(1);
        }

        googleMaps.setIcon(new ImageIcon((new ImageIcon("image.jpg")).getImage().getScaledInstance(600, 600,
                java.awt.Image.SCALE_SMOOTH)));

        pack();
    }
}
