import javax.sound.sampled.*;
import java.awt.*;
import java.net.URL;

public class Laser {
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
        g.setColor(Color.RED);
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
