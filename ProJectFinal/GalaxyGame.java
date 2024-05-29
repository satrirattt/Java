import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import javax.imageio.ImageIO;
import java.io.IOException;
import java.awt.image.BufferedImage;

public class GalaxyGame extends JPanel implements ActionListener {
    private final int WIDTH = 750;
    private final int HEIGHT = 750;
    private Timer timer;
    private int score = 0;
    private boolean running = false;
    private boolean lost = false;
    private Player player;
    private ArrayList<Enemy> enemies;
    private int waveLength = 5;
    private int playerVelocity = 100;
    private int laserVelocity = 5;
    private int enemyVelocity = 1;
    private int lives = 5;
    private ImageIcon heartIcon = new ImageIcon(getClass().getResource("image/player/heart.png"));
    private int heartWidth = 40;
    private int heartHeight = 40;
    private BufferedImage backgroundImage;
    private boolean spacePressed = false;
    private int gameTimeInSeconds = 60;
    private SoundPlayer backgroundMusic;
    private ImageIcon musicOnIcon;
    private ImageIcon musicOffIcon;
    private ImageIcon musicMuteIcon;
    private JLabel musicToggleLabel;
    private Image scaledMusicOnImage, scaledMusicOffImage;
    private ImageIcon scaledMusicOnIcon, scaledMusicOffIcon;

    public GalaxyGame() {
        setPreferredSize(new Dimension(WIDTH, HEIGHT));
        try {
            backgroundImage = ImageIO.read(getClass().getResource("image/bg/bg.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Load background music and icons for music toggle button
        backgroundMusic = new SoundPlayer("sound/A.wav");
        try {
            musicOnIcon = new ImageIcon(ImageIO.read(getClass().getResource("image/player/music.png")));
            // ปรับขนาดของไอคอน
            scaledMusicOnImage = musicOnIcon.getImage().getScaledInstance(50, 50, Image.SCALE_SMOOTH);
            // สร้าง ImageIcon ใหม่จากรูปที่ปรับขนาดแล้ว
            scaledMusicOnIcon = new ImageIcon(scaledMusicOnImage);
            musicOffIcon = new ImageIcon(ImageIO.read(getClass().getResource("image/player/mutemusic.png")));

            scaledMusicOffImage = musicOffIcon.getImage().getScaledInstance(50, 50, Image.SCALE_SMOOTH);
            scaledMusicOffIcon = new ImageIcon(scaledMusicOffImage);
            musicMuteIcon = new ImageIcon(ImageIO.read(getClass().getResource("image/player/music.png")));
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Create music toggle button
        musicToggleLabel = new JLabel();
        musicToggleLabel.setIcon(scaledMusicOnIcon);
        musicToggleLabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                toggleMusic();
            }
        });

        add(musicToggleLabel);

        setFocusable(true);
        addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                int key = e.getKeyCode();
                if (key == KeyEvent.VK_SPACE) {
                    spacePressed = true;
                    player.shoot();
                } else if (key == KeyEvent.VK_LEFT) {
                    player.setLeft(true);
                } else if (key == KeyEvent.VK_RIGHT) {
                    player.setRight(true);
                } else if (key == KeyEvent.VK_UP) {
                    player.setUp(true);
                } else if (key == KeyEvent.VK_DOWN) {
                    player.setDown(true);
                } else if (key == KeyEvent.VK_ENTER) {
                    if (lost) {
                        startGame();
                    }
                }
            }

            @Override
            public void keyReleased(KeyEvent e) {
                int key = e.getKeyCode();
                if (key == KeyEvent.VK_SPACE) {
                    spacePressed = false;
                    player.resetShoot();
                } else if (key == KeyEvent.VK_LEFT) {
                    player.setLeft(false);
                } else if (key == KeyEvent.VK_RIGHT) {
                    player.setRight(false);
                } else if (key == KeyEvent.VK_UP) {
                    player.setUp(false);
                } else if (key == KeyEvent.VK_DOWN) {
                    player.setDown(false);
                }
            }
        });

        timer = new Timer(1000 / 60, this);
        timer.start();

        player = new Player(300, 630, 100, lives);
        enemies = new ArrayList<>();
        startGame();
    }

    private void toggleMusic() {
        if (backgroundMusic.isPlaying()) {
            backgroundMusic.stop();
            musicToggleLabel.setIcon(scaledMusicOffIcon);
        } else {
            backgroundMusic.play();
            musicToggleLabel.setIcon(scaledMusicOnIcon);
        }
    }

    public void startGame() {
        running = true;
        lost = false;
        score = 0;
        lives = 5;
        player = new Player(300, 630, 100, lives);
        enemies.clear();
        waveLength = 5;
        playerVelocity = 5;
        laserVelocity = 5;
        enemyVelocity = 1;
        createEnemies();
        backgroundMusic.play();
        repaint();
    }

    public void createEnemies() {
        for (int i = 0; i < waveLength; i++) {
            String[] colors = { "red", "blue", "green", "pink", "orange", "yel", "white", "purple", "babyblue",
                    "babypink" };
            int randomX = (int) (Math.random() * (WIDTH - 100)) + 50;
            int randomY = (int) (Math.random() * 1000) * -1;
            String randomColor = colors[(int) (Math.random() * colors.length)];
            enemies.add(new Enemy(randomX, randomY, randomColor, 100));
        }
        repaint();
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(backgroundImage, 0, 0, WIDTH, HEIGHT, null);

        player.draw(g);

        for (Enemy enemy : enemies) {
            enemy.draw(g);
        }
        for (int i = 0; i < lives; i++) {
            g.drawImage(heartIcon.getImage(), 10 + i * (heartWidth + 5), 12, heartWidth, heartHeight, null);
        }
        g.setColor(Color.WHITE);
        g.setFont(new Font("Pixeland", Font.PLAIN, 45));
        g.drawString("Score:" + score, WIDTH - 180, 40);
    }

    public void update() {
        if (running) {
            player.update(playerVelocity, spacePressed);
            updateEnemies();
        }
    }

    public void updateEnemies() {
        for (int i = 0; i < enemies.size(); i++) {
            Enemy enemy = enemies.get(i);
            enemy.update(enemyVelocity);
            if (enemy.getY() > HEIGHT) {
                enemies.remove(i);
                lives--;
                if (lives <= 0) {
                    running = false;
                    lost = true;
                    gameOver();
                }
            }
        }
        checkCollisions();
        if (enemies.isEmpty()) {
            score += 100;
            waveLength += 2;
            createEnemies();
            enemyVelocity += 1;
        }
    }

    public void checkCollisions() {
        Rectangle playerBounds = player.getBounds();
        for (int i = 0; i < enemies.size(); i++) {
            Enemy enemy = enemies.get(i);
            Rectangle enemyBounds = enemy.getBounds();
            if (playerBounds.intersects(enemyBounds)) {
                enemies.remove(i);
                break;
            }
            for (Laser laser : player.getLasers()) {
                Rectangle laserBounds = laser.getBounds();
                if (laserBounds.intersects(enemyBounds)) {
                    enemies.remove(i);
                    score += 20;
                    player.getLasers().remove(laser);
                    break;
                }
            }
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (running) {
            update();
            repaint();
        }
    }

    public void gameOver() {
        timer.stop();
        backgroundMusic.stop();

        for (Enemy enemy : enemies) {
            enemy.getAnimationTimer().stop();
        }

        int selectedOption = JOptionPane.showConfirmDialog(this,
                ("Your Score: " + score + "\nDo you want to play a new Game?"),
                "GAME OVER", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);

        switch (selectedOption) {
            case JOptionPane.YES_OPTION:
                // สร้างหน้าจอ TitleScreen ใหม่
                JFrame frame = (JFrame) SwingUtilities.getWindowAncestor(this);
                frame.getContentPane().removeAll(); // ลบเนื้อหาทั้งหมดของ JFrame เก่า
                frame.getContentPane().add(new Main(frame)); // เพิ่มหน้าจอ TitleScreen เข้าไปใน JFrame
                frame.pack(); // ปรับขนาด JFrame
                frame.setLocationRelativeTo(null); // กำหนดให้ JFrame แสดงกลางหน้าจอ
                frame.setVisible(true); // ทำให้ JFrame แสดงผลบนหน้าจอ
                break;

            case JOptionPane.NO_OPTION:
                System.exit(0);
                break;
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Main Game");
            GalaxyGame game = new GalaxyGame();
            frame.add(game);
            frame.pack();
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setVisible(true);
        });
    }
}
