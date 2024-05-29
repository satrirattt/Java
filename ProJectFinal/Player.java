import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.io.IOException;

public class Player extends JPanel implements ActionListener {
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
            BufferedImage frame1 = ImageIO.read(getClass().getResource("image/player/p1.png"));
            playerImages.add(frame1);

            // โหลดภาพเฟรมที่สอง
            BufferedImage frame2 = ImageIO.read(getClass().getResource("image/player/p2.png"));
            playerImages.add(frame2);

            BufferedImage frame3 = ImageIO.read(getClass().getResource("image/player/p3.png"));
            playerImages.add(frame3);

            BufferedImage frame4 = ImageIO.read(getClass().getResource("image/player/p4.png"));
            playerImages.add(frame4);

            BufferedImage frame5 = ImageIO.read(getClass().getResource("image/player/p5.png"));
            playerImages.add(frame5);

            BufferedImage frame6 = ImageIO.read(getClass().getResource("image/player/p6.png"));
            playerImages.add(frame6);

            BufferedImage frame7 = ImageIO.read(getClass().getResource("image/player/p7.png"));
            playerImages.add(frame7);

            BufferedImage frame8 = ImageIO.read(getClass().getResource("image/player/p8.png"));
            playerImages.add(frame8);

            BufferedImage frame9 = ImageIO.read(getClass().getResource("image/player/p9.png"));
            playerImages.add(frame9);

            BufferedImage frame10 = ImageIO.read(getClass().getResource("image/player/p10.png"));
            playerImages.add(frame10);

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
