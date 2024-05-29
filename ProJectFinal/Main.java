import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class Main extends JPanel {
    private JFrame frame;
    private JButton startButton;
    private JButton exitButton;
    private JButton forestButton;
    private JButton seaButton;
    private SoundPlayer backgroundMusic;
    private BufferedImage backgroundImage;

    public Main(JFrame frame) {
        this.frame = frame;
        setLayout(new BorderLayout());
        try {
            backgroundImage = ImageIO.read(getClass().getResource("image/bg/d (2).png"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        startButton = new JButton("Galaxy");
        startButton.setFont(new Font("Sonic Advanced 2", Font.BOLD, 24));
        startButton.setFocusPainted(false);
        startButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                startGame(); // เริ่มเกมทันทีเมื่อกดปุ่ม Start
            }
        });

        exitButton = new JButton("Exit");
        exitButton.setFont(new Font("Sonic Advanced 2", Font.BOLD, 24));
        exitButton.setFocusPainted(false);
        exitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

        forestButton = new JButton("Forest"); // เพิ่มปุ่มด่านป่า
        forestButton.setFont(new Font("Sonic Advanced 2", Font.BOLD, 24));
        forestButton.setFocusPainted(false);
        forestButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                selectForestLevel();
            }
        });

        seaButton = new JButton("Sea"); // เพิ่มปุ่มด่านทะเล
        seaButton.setFont(new Font("Sonic Advanced 2", Font.BOLD, 24));
        seaButton.setFocusPainted(false);
        seaButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                selectSeaLevel();
            }
        });

        JPanel buttonPanel = new JPanel(null);

        buttonPanel.setOpaque(false);
        startButton.setBounds(315, 340, 130, 50);
        buttonPanel.add(startButton);
        forestButton.setBounds(315, 420, 130, 50);
        buttonPanel.add(forestButton);
        seaButton.setBounds(315, 500, 130, 50);
        buttonPanel.add(seaButton);
        exitButton.setBounds(315, 580, 130, 50);
        buttonPanel.add(exitButton);

        add(buttonPanel);

        setPreferredSize(new Dimension(750, 750));
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);

        g.setFont(new Font("Sonic Advanced 2", Font.BOLD, 96));
        FontMetrics fm = g.getFontMetrics();
        int stringWidth = fm.stringWidth("Shooter Attack");
        int x = (getWidth() - stringWidth) / 2;
        int y = getHeight() / 4;
        g.setColor(Color.WHITE);
        g.drawString("Shooter Attack", x, y);

    }

    private void startGame() {
        JFrame frame = (JFrame) SwingUtilities.getWindowAncestor(this);
        frame.dispose(); // Close the title screen window
        GalaxyGame game = new GalaxyGame(); // Create a new instance of the game
        JFrame gameFrame = new JFrame("Shoot Attack"); // Create a new JFrame for the game

        gameFrame.add(game); // Add the game to the JFrame
        gameFrame.pack(); // Pack the JFrame
        gameFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Set default close operation
        gameFrame.setVisible(true); // Make the JFrame visible
    }

    private void selectForestLevel() {
        // สร้างหน้าจอใหม่สำหรับเล่นด่าน "ป่า"
        JFrame frame = (JFrame) SwingUtilities.getWindowAncestor(this);
        frame.dispose();
        ForestGame game = new ForestGame();
        JFrame gameFrame = new JFrame("Shoot Attack");

        gameFrame.add(game); // Add the game to the JFrame
        gameFrame.pack(); // Pack the JFrame
        gameFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Set default close operation
        gameFrame.setVisible(true); // Make the JFrame visible

    }

    private void selectSeaLevel() {
        // สร้างหน้าจอใหม่สำหรับเล่นด่าน "ทะเล"
        JFrame frame = (JFrame) SwingUtilities.getWindowAncestor(this);
        frame.dispose();
        SeaGame game = new SeaGame();
        JFrame gameFrame = new JFrame("Shoot Attack");

        gameFrame.add(game); // Add the game to the JFrame
        gameFrame.pack(); // Pack the JFrame
        gameFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Set default close operation
        gameFrame.setVisible(true); // Make the JFrame visible
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Shooter Attack");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.getContentPane().add(new Main(frame));
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

    }
}
