package dynamic_beat_4;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;

public class DynamicBeat extends JFrame {

    private Image screenImage;
    private Graphics screenGraphic;

    private ImageIcon exitButtonBasicImage = new ImageIcon(Main.class.getResource("../imgs/exitButtonBasic.png"));
    private ImageIcon exitButtonEnteredImage = new ImageIcon(Main.class.getResource("../imgs/exitButtonEntered.png"));

    private Image introBackground = new ImageIcon(Main.class.getResource("../imgs/introBackground(Title).jpg")).getImage();
    private JLabel menuBar = new JLabel(new ImageIcon(Main.class.getResource("../imgs/menuBar.png")));
    private JButton exitButton = new JButton(exitButtonBasicImage);



    private int mouseX, mouseY;


    public DynamicBeat() {
        setUndecorated(true);
        setTitle("Dynamic Beat");
        setSize(Main.SCREEN_WIDTH, Main.SCREEN_HEIGHT);
        setResizable(false);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);


        setVisible(true);
        setBackground(new Color(0, 0, 0, 0));
        setLayout(null);

        JLayeredPane layeredPane = new JLayeredPane();
        layeredPane.setBounds(0, 0, 1280, 720);
        layeredPane.setLayout(null);

        MyPanel panel = new MyPanel();
        panel.setBounds(0, 0, 1280, 720);

        exitButton.setBounds(1245, 0, 30, 30);
        exitButton.setBorderPainted(false);
        exitButton.setContentAreaFilled(false);
        exitButton.setFocusPainted(false);
        exitButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                exitButton.setIcon(exitButtonEnteredImage);
                exitButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
                Music buttonEnteredMusic = new Music("buttonEnteredMusic.mp3", false);
                buttonEnteredMusic.start();

            }

            @Override
            public void mouseExited(MouseEvent e) {
                exitButton.setIcon(exitButtonBasicImage);
                exitButton.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));


            }

            @Override
            public void mouseClicked(MouseEvent e) {

                Music buttonPressedMusic = new Music("buttonPressedMusic.mp3", false);
                buttonPressedMusic.start();

                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e1) {
                    e1.printStackTrace();
                }
                System.exit(0);
            }
        });
        panel.add(exitButton);

        menuBar.setBounds(0, 0, 1280, 30);
        menuBar.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                mouseX = e.getX();
                mouseY = e.getY();
            }

        });
        menuBar.addMouseMotionListener(new MouseMotionAdapter() {
            @Override
            public void mouseDragged(MouseEvent e) {
                int x = e.getXOnScreen();
                int y = e.getYOnScreen();
                setLocation(x - mouseX, y - mouseY);

            }
        });
        panel.add(menuBar);



        layeredPane.add(panel);
        add(layeredPane);

        Music introMusic = new Music("introMusic.mp3", true);
        introMusic.start();

    }





    class MyPanel extends JPanel {
        @Override
        public void paint(Graphics g) {
            screenImage = createImage(Main.SCREEN_WIDTH, Main.SCREEN_HEIGHT);
            screenGraphic = screenImage.getGraphics();
            screenDraw(screenGraphic);
            g.drawImage(screenImage, 0, 0, null);
        }
        public void screenDraw(Graphics g) {
            g.drawImage(introBackground, 0, 0, null);
            paintComponents(g);
            this.repaint();

        }
    }
}
