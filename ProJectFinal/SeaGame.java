import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import javax.imageio.ImageIO;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;

import java.io.IOException;
import java.net.URL;
import java.awt.image.BufferedImage;

public class SeaGame extends JPanel implements ActionListener {
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

    public SeaGame() {
        setPreferredSize(new Dimension(WIDTH, HEIGHT));
        try {
            backgroundImage = ImageIO.read(getClass().getResource("image/bg/sea.gif"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Load background music and icons for music toggle button
        backgroundMusic = new SoundPlayer("sound/T.wav");
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
            JFrame frame = new JFrame("Sea Game");
            SeaGame game = new SeaGame();
            frame.add(game);
            frame.pack();
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setVisible(true);
        });
    }

    private class Laser {
        private int x;
        private int y;
        private int dy;
        private Clip laserSound;

        public Laser(int x, int y, int dy) {
            this.x = x;
            this.y = y;
            this.dy = -dy;

            try {
                // โหลดไฟล์เสียง
                URL soundURL = getClass().getResource("sound/laserrrr.wav");
                AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(soundURL);
                laserSound = AudioSystem.getClip();
                laserSound.open(audioInputStream);
                FloatControl gainControl = (FloatControl) laserSound.getControl(FloatControl.Type.MASTER_GAIN);
                gainControl.setValue(-30.0f); // ปรับระดับเสียงเป็น -20.0 dB (ลดเสียงลง)
            } catch (Exception e) {
                e.printStackTrace();
            }

            // เล่นเสียง
            playSound();
        }

        private void playSound() {
            if (laserSound != null) {
                laserSound.setFramePosition(0);
                laserSound.start();
            }
        }

        public void draw(Graphics g) {
            g.setColor(Color.WHITE);
            g.fillRect(x, y, 2, 10);
        }

        public void update() {
            y += dy;
        }

        public int getY() {
            return y;
        }

        public Rectangle getBounds() {
            return new Rectangle(x, y, 2, 10);
        }

        // ปิด Clip เมื่อไม่ได้ใช้งาน
        public void close() {
            if (laserSound != null) {
                laserSound.close();
            }
        }
    }

    private class Player extends JPanel implements ActionListener {
        private int x;
        private int y;
        private int width;
        private int height;
        private int health;
        private int lives;
        private boolean left, right, up, down;
        private ArrayList<Laser> lasers;
        private Timer timer;
        private ArrayList<Image> playerImages;
        private int currentFrame = 0;
        private boolean canShoot = true;

        public Player(int x, int y, int health, int lives) {
            this.x = x;
            this.y = y;
            this.health = health;
            this.lives = lives;
            this.width = 50;
            this.height = 50;
            this.lasers = new ArrayList<>();
            this.playerImages = new ArrayList<>();

            try {
                // โหลดภาพเฟรมแรก
                BufferedImage frame1 = ImageIO.read(getClass().getResource("image/player/f/frame_0_delay-0.2s.gif"));
                playerImages.add(frame1);

                // โหลดภาพเฟรมที่สอง
                BufferedImage frame2 = ImageIO.read(getClass().getResource("image/player/f/frame_1_delay-0.2s.gif"));
                playerImages.add(frame2);

                BufferedImage frame3 = ImageIO.read(getClass().getResource("image/player/f/frame_2_delay-0.2s.gif"));
                playerImages.add(frame3);

                BufferedImage frame4 = ImageIO.read(getClass().getResource("image/player/f/frame_3_delay-0.2s.gif"));
                playerImages.add(frame4);

                BufferedImage frame5 = ImageIO.read(getClass().getResource("image/player/f/frame_4_delay-0.2s.gif"));
                playerImages.add(frame5);

                BufferedImage frame6 = ImageIO.read(getClass().getResource("image/player/f/frame_5_delay-0.2s.gif"));
                playerImages.add(frame6);
            } catch (IOException e) {
                e.printStackTrace();
            }

            timer = new Timer(100, this);
            timer.start();
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            if (!playerImages.isEmpty()) {
                g.drawImage(playerImages.get(currentFrame), x, y, width, height, null);
            }
            for (Laser laser : lasers) {
                laser.draw(g);
            }
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            // สลับเฟรมในแต่ละรอบ
            currentFrame = (currentFrame + 1) % playerImages.size();
            repaint();
        }

        public void draw(Graphics g) {

            if (!playerImages.isEmpty()) {
                g.drawImage(playerImages.get(currentFrame), x, y, width, height, null);
            }

            for (Laser laser : lasers) {
                laser.draw(g);
            }
        }

        public void update(int playerVelocity, boolean spacePressed) {
            if (left) {
                if (x > 10)
                    x -= playerVelocity;
            }
            if (right) {
                if (x < 700)
                    x += playerVelocity;
            }
            if (up) {
                if (y > 10)
                    y -= playerVelocity;
            }
            if (down) {
                if (y < 700)
                    y += playerVelocity;
            }
            if (spacePressed && canShoot) {
                shoot();
                canShoot = false;
            }
            for (int i = 0; i < lasers.size(); i++) {
                Laser laser = lasers.get(i);
                laser.update();
                if (laser.getY() < 0) {
                    lasers.remove(i);
                }
            }
        }

        public void shoot() {
            lasers.add(new Laser(x + width / 2, y, 5));
        }

        public void resetShoot() {
            canShoot = true;
        }

        public ArrayList<Laser> getLasers() {
            return lasers;
        }

        public int getHealth() {
            return health;
        }

        public void setHealth(int health) {
            this.health = health;
        }

        public void setLeft(boolean left) {
            this.left = left;
        }

        public void setRight(boolean right) {
            this.right = right;
        }

        public void setUp(boolean up) {
            this.up = up;
        }

        public void setDown(boolean down) {
            this.down = down;
        }

    }
}
