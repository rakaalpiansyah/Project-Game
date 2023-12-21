import java.awt.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Iterator;

import javax.swing.*;

public class GamePanel extends JPanel implements Runnable  {
    
    int FPS = 60;
    int CharSize = 25 * 3; 
    int screenWidth = 40 * 25; 
    int screenHeight = 40 *25; 
    Tombol tombolY = new Tombol();
    Thread gameThread;
    
    Map mp = new Map(this);
    Player player = new Player(this,tombolY, CharSize, null);
    Map map;
    FallingBox fallingBox;
    ArrayList<FallingBox> boxes = new ArrayList<>();
    public GamePanel(){
        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(Color.gray);
        this.setDoubleBuffered(true);
        this.addKeyListener(tombolY);
        this.setFocusable(true);      
    }
   public void startGameThread(){
        gameThread = new Thread(this);
        gameThread.start();
    }
    public void run() {
        double GambarFPS = 1000/FPS; 
        long WaktuTrakhir= System.currentTimeMillis(); // 1 detik = 1000 mili
        long Waktuskrg;
        long timer = 0;
        int drawCount = 0;
        double delta = 0;
        while(gameThread != null) {
            Waktuskrg = System.currentTimeMillis();
            delta += (Waktuskrg - WaktuTrakhir) / GambarFPS;
            timer += (Waktuskrg- WaktuTrakhir);
            WaktuTrakhir = Waktuskrg;
        
          if(delta >= 1) {
              delta--;
              drawCount++;
              update(); // untk mengupdate 
              repaint(); // untuuk mnggambar ulang
             if(timer >= 1000) {
                System.out.println("FPS: " + drawCount);
                drawCount = 0; // 0 itu untk ngereset nilai
                timer = 0;
                LocalDateTime waktu = LocalDateTime.now();
                System.out.println(waktu);
                boxes.add(new FallingBox((int)(Math.random() * screenWidth), 0, 10, "./Explosion_5.png"));
                }
            }
        }
   }
   
    // menampilkan karakter
    public void update(){
        player.update(); // Memperbarui status pemain, seperti posisi
     

        Iterator<FallingBox> it = boxes.iterator();
        while (it.hasNext()) {
            FallingBox box = it.next();
            box.update();
            if (box.getY() > screenHeight) {
                it.remove();
            }
        }
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);     
        Graphics2D g2 = (Graphics2D)g;    
        mp.draw(g2);
        player.draw(g2); 
        for (FallingBox box : boxes) {
            box.draw(g2);
        }   
        g2.dispose();
        
    }


    public static void main(String[] args) {
        // tampilan window
        JFrame window = new JFrame(null, null);
        GamePanel gamePanel = new GamePanel();
        window.setResizable(false);
        window.setTitle("GAME Apa?");
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // jika kluar semua akan kluar tidak ada yg running
        window.add(gamePanel);
        window.pack();
        window.setLocationRelativeTo(null);
        gamePanel.startGameThread();
        window.setVisible(true);
    }
}
