import java.awt.Color;
import java.awt.Graphics;

public class Hp {
    private int maxHp; // Nyawa maksimum
    private int currentHp; // Nyawa saat ini

    public Hp(int maxHp) {
        this.maxHp = maxHp;
        this.currentHp = maxHp;
    }

    // Mengurangi nyawa
    public void decreaseHp(int amount) {
        currentHp -= amount;
        if (currentHp < 0) {
            currentHp = 0;
        }
    }

    // Menambah nyawa
    public void increaseHp(int amount) {
        currentHp += amount;
        if (currentHp > maxHp) {
            currentHp = maxHp;
        }
    }

    // Menggambar bar nyawa
    public void draw(Graphics g, int x, int y, int width, int height) {
        g.setColor(Color.RED);
        int healthWidth = (int) ((currentHp / (double) maxHp) * width);
        g.fillRect(x, y, healthWidth, height);
    }
}
