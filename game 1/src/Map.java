

import java.awt.*;
import java.awt.image.*;
import java.io.*;
import javax.imageio.ImageIO;

public class Map {
    BufferedImage image1, image3;
    BufferedImage image2;
    GamePanel gp;
   
    
    // memanggil class 
    public Map(GamePanel gp) {
        this.gp = gp;
        getMap(gp);
    }
    public void getMap(GamePanel gp) {
        try {
            // mengimport image
            image1 = ImageIO.read(getClass().getResourceAsStream("./6.png"));        
            image2 = ImageIO.read(getClass().getResourceAsStream("./6.png"));        
    }
    // biar ada yang salah tidak ngaruh ke yang lainnya
    catch(IOException e) {
        e.printStackTrace();
    }
}
    // memanggil, mengatur, menampilkan image
    public void draw(Graphics2D g2) {
        g2.drawImage(image1, 0, 0, 1280, 1200, null);
       
    }
}