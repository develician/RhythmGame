package mp3Player;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.IOException;

public class mp3Player extends JFrame {

    private Image screenImage;
    private Graphics screenGraphic;

    private ImageIcon exitButtonBasicImage = new ImageIcon(Main.class.getResource("../imgs/exitButtonBasic.png"));
    private ImageIcon exitButtonEnteredImage = new ImageIcon(Main.class.getResource("../imgs/exitButtonEntered.png"));

    private BufferedImage backgroundImage = null;

    private JButton exitButton = new JButton(exitButtonBasicImage);

    private int mouseX, mouseY;

    private JMenuBar menu = new JMenuBar();
    private JMenu fileTab = new JMenu("File");
    private JMenuItem open = new JMenuItem("Open");
    private JMenuItem close = new JMenuItem("Close");
    private JFileChooser fileChooser = new JFileChooser();

    public mp3Player() {
        setUndecorated(true);
        setTitle("mp3Player");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setSize(Main.SCREEN_WIDTH, Main.SCREEN_HEIGHT);
        setLocationRelativeTo(null);
        setResizable(false);
        setLayout(null);


        try {
            backgroundImage = ImageIO.read(Main.class.getResource("../imgs/introBackground.jpg"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        JLayeredPane layeredPane = new JLayeredPane();
        layeredPane.setBounds(0, 0, Main.SCREEN_WIDTH, Main.SCREEN_HEIGHT);
        layeredPane.setLayout(null);


        wholePanel panel = new wholePanel();
        panel.setBounds(0, 0, Main.SCREEN_WIDTH, Main.SCREEN_HEIGHT);
        panel.setLayout(null);

        fileChooser.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(e.getSource() == open){
                    int returnVal = fileChooser.showOpenDialog(mp3Player.this);

                }

            }
        });

        exitButton.setBounds(765, 0, 30, 30);
        exitButton.setBorderPainted(false);
        exitButton.setContentAreaFilled(false);
        exitButton.setFocusPainted(false);
        exitButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                exitButton.setIcon(exitButtonEnteredImage);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                exitButton.setIcon(exitButtonBasicImage);
            }

            @Override
            public void mouseClicked(MouseEvent e) {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e1) {
                    e1.printStackTrace();
                }
                System.exit(0);
            }
        });

        panel.add(exitButton);

        menu.add(fileTab);
        fileTab.add(open);
        fileTab.add(close);
        menu.setBounds(0, 0, Main.SCREEN_WIDTH, 30);
        menu.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                mouseX = e.getX();
                mouseY = e.getY();
            }
        });

        menu.addMouseMotionListener(new MouseMotionAdapter() {
            @Override
            public void mouseDragged(MouseEvent e) {
                int x = e.getXOnScreen();
                int y = e.getYOnScreen();
                setLocation(x - mouseX, y - mouseY);
            }
        });

        panel.add(menu);



        layeredPane.add(panel);
        add(layeredPane);

        setVisible(true);

    }

    class wholePanel extends JPanel {
        @Override
        public void paint(Graphics g) {
            screenImage = createImage(Main.SCREEN_WIDTH, Main.SCREEN_HEIGHT);
            screenGraphic = screenImage.getGraphics();
            screenDraw(screenGraphic);
            g.drawImage(screenImage, 0, 0, null);
        }

        public void screenDraw(Graphics g) {
            g.drawImage(backgroundImage, 0, 0, null);
            paintComponents(g);
            this.repaint();

        }


    }
}
