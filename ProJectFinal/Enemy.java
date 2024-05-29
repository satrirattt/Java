import java.awt.*;
import java.util.ArrayList;
import javax.swing.Timer;

public class Enemy {
    private int x;
    private int y;
    private int width;
    private int height;
    private int health;
    private ArrayList<Laser> lasers;
    private String color;
    private Timer animationTimer; // เปลี่ยนชื่อตัวแปรจาก getAnimationTimer เป็น animationTimer

    public Enemy(int x, int y, String color, int health) {
        this.x = x;
        this.y = y;
        this.color = color;
        this.health = health;
        this.width = 50;
        this.height = 50;
        this.lasers = new ArrayList<>();
        this.animationTimer = new Timer(0, null); // สร้างตัวจับเวลา (Timer) ใหม่
    }

    public void draw(Graphics g) {
        Color enemyColor = Color.RED;
        switch (color) {
            case "red":
                enemyColor = Color.RED;
                break;
            case "blue":
                enemyColor = Color.BLUE;
                break;
            case "green":
                enemyColor = Color.GREEN;
                break;
            case "pink":
                enemyColor = Color.PINK;
                break;
            case "orange":
                enemyColor = Color.ORANGE;
                break;
            case "yel":
                enemyColor = Color.YELLOW;
                break;
            case "white":
                enemyColor = Color.WHITE;
                break;
            case "purple":
                enemyColor = new Color(128, 0, 128);
                break;
            case "babyblue":
                enemyColor = new Color(173, 216, 230);
                break;
            case "babypink":
                enemyColor = new Color(255, 192, 203);
                break;
        }
        g.setColor(enemyColor);
        g.fillOval(x, y, width, height);
        for (Laser laser : lasers) {
            laser.draw(g);
        }
    }

    public void update(int enemyVelocity) {
        y += enemyVelocity;
        for (int i = 0; i < lasers.size(); i++) {
            Laser laser = lasers.get(i);
            laser.update();
            if (laser.getY() > 750) {
                lasers.remove(i);
            }
        }
    }

    public void shoot() {
        lasers.add(new Laser(x + width / 2, y + height, -5));
    }

    public Rectangle getBounds() {
        return new Rectangle(x, y, width, height);
    }

    public int getY() {
        return y;
    }

    public Timer getAnimationTimer() {
        return animationTimer; // เปลี่ยนชื่อเมธอดเป็น getAnimationTimer ตามที่คุณต้องการ
    }
}
