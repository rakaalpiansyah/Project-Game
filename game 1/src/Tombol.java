
import java.awt.event.*;
// implements khusus untuk pemograman kata kunci menggunakan keyboard
public class Tombol implements KeyListener {
    public boolean TekanLoncat, TekanBawah, TekanKanan, TekanKiri;

    // dapat langsung menampilkan method dari keylistener secara otomatis
   
    public void keyTyped(KeyEvent e) { 
    }
 // untuk gerak dimulai ketika tombol di tekan
    public void keyPressed(KeyEvent e) {
    
       int code = e.getKeyCode();

       if(code == KeyEvent.VK_UP) {
        TekanLoncat = true;
       }
       if(code == KeyEvent.VK_DOWN) {
        TekanBawah = true;
       }
       if(code == KeyEvent.VK_RIGHT) {
        TekanKanan = true;
       }
       if(code == KeyEvent.VK_LEFT) {
        TekanKiri = true;
       }
    }

 // biar stop gerak saat melepaskan tombol akan berhenti
   public void keyReleased(KeyEvent e) {
        
      int code = e.getKeyCode();
       if(code == KeyEvent.VK_UP) {
        TekanLoncat = false;
       }
       if(code == KeyEvent.VK_DOWN) {
        TekanBawah = false;
       }
       if(code == KeyEvent.VK_RIGHT) {
        TekanKanan = false;
       }
       if(code == KeyEvent.VK_LEFT) {
        TekanKiri = false;
      }

    }

}
