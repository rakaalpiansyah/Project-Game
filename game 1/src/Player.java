import java.awt.*;
import java.awt.image.*;
import java.io.*;
import javax.imageio.*;

public class Player {

    BufferedImage up1, up2, down1, down2, left1, left2, right1, right2;
    int x, y;
    int speed;
    int sp1 = 0, sp2 = 1;
    GamePanel gp;
    Tombol tombol;
    String gerakan;
    Hp hp;
    boolean isJumping = false;
    int playerWidth = 70, playerHeight = 70;  // Sesuaikan dengan ukuran pemain
    
    
    private Hp health; // nyawa pemain
    private static final int GRAVITY = 1;
    private int velocityY = 0; 
   
    // memanggil class yg mendukung
public Player(GamePanel gp, Tombol tombolY, int maxHp, Hp health, int playerWidth, int playerHeight, int x, int y) {
    this.gp = gp;
    this.tombol = tombolY;
    GambarChar();
    this.hp = new Hp(maxHp);
    this.health = health;
    this.x = x;
    this.y = y;
    speed = 4;
    this.gerakan = "0" ;
    
}
public Hp getHealth() {
    return this.health;
}

// untuk mengimport foto setiap gerakan 
public void GambarChar() {
    try {
        // kenapa ada 2 supaya setiap gerakan ada 2 frame/gerakannya agar smooth
        up1 = ImageIO.read(getClass() .getResourceAsStream("./idle.png")); // tiak ketemu untuk 2 frame foto jadi sama tiap frame nya
        up2 = ImageIO.read(getClass() .getResourceAsStream("./idle.png"));
        down1 = ImageIO.read(getClass() .getResourceAsStream("./idle.png"));
        down2 = ImageIO.read(getClass() .getResourceAsStream("./idle.png"));
        left1 = ImageIO.read(getClass() .getResourceAsStream("./kiri1.png"));
        left2 = ImageIO.read(getClass() .getResourceAsStream("./kiri2.png"));
        right1 = ImageIO.read(getClass() .getResourceAsStream("./kanan1.png"));
        right2 = ImageIO.read(getClass() .getResourceAsStream("./kanan2.png"));
    }catch(IOException e) {
        e.printStackTrace();
    }
}
 public void getBounds() {
    y += velocityY;
    velocityY += GRAVITY;
    
     if (x <= 0) { // Pemain berada di luar batas kiri
        x = 0;
    } else if (x + 70 > gp.screenWidth) { // Pemain berada di luar batas kanan
        x = gp.screenHeight - 70;
    }
    if (y <= 0) { // Pemain berada di luar batas atas
        y = 0;
    } else if (y + 70 > gp.screenWidth) { // Pemain berada di luar batas bawah
        y = gp.screenHeight - 70;
        isJumping = false;
    } 
}
public void update() {
    updatePos();
    getBounds();
}
// cara agar gambar bergerak sesuai gerakan
private void updatePos() {
    if(tombol.TekanLoncat == true || tombol.TekanBawah == true || tombol.TekanKiri == true || tombol.TekanKanan == true) {
        sp1++; 
        if(tombol.TekanLoncat == true && !isJumping) {
            velocityY = -15; // Nilai ini dapat disesuaikan sesuai dengan kebutuhan
            gerakan = "up";
           isJumping = true;
        }
        else if(tombol.TekanBawah == true) {
            y += speed;
            gerakan = "down";
        }
        else if(tombol.TekanKiri == true) {
            x -= speed;
            gerakan = "left";
        }
        else if(tombol.TekanKanan == true) {
            x += speed;
            gerakan = "right";
        }
        if(sp1 > 15) {
            if(sp2 == 1) {
                sp2  = 2;
            }
            else if(sp2 == 2) {
                sp2 = 1;
            }
            sp1 = 0;
        }
    }
}
public void draw(Graphics2D g2) {
    BufferedImage image = null;
    hp.draw(g2, x, y - 10, 70, 4);
    switch(gerakan) {
        // karena pasti ada gerakan kaki kanan dan kiri jai 2 foto
        case "up":
            if(sp2 == 1) {
                image = up1;
            }
            if(sp2 == 2) {
                image = up2;
            }default:
            
        case "down":
            if(sp2 == 1) {
                image = down1;
            }
            if(sp2 == 2) {
                image = down2;
            }
            break;
        case "left":
            if(sp2 == 1) {
                image = left1;
            }
            if(sp2 == 2) {
            image = left2;
            }
            break;
        case "right":
            if(sp2 == 1) {
            image = right1;
        }
            if(sp2 == 2) {
            image = right2;
        }
        break;   
    }
    g2.drawImage(image, x, y, 70, 70, null);
    }
}