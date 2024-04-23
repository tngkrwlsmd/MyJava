package java12.java12_7;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.Vector;

public class java12_7 extends JFrame {
  Pan pan = new Pan();
  Point p;
  Vector<Point> v = new Vector<Point>();

  public java12_7() {
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setContentPane(pan);

    pan.addMouseListener(new MouseAdapter() {
      @Override
      public void mousePressed(MouseEvent e) {
        p = e.getPoint();
        v.add(p);
        repaint();
      }
    });
    setFocusable(true);
    requestFocus();
    
    setSize(400, 400);
    setVisible(true);
  }
  
  class Pan extends JPanel {
    @Override
    public void paintComponent(Graphics g) {
      super.paintComponent(g);
      if (v.size() == 2) g.drawLine(v.get(0).x, v.get(0).y, v.get(1).x, v.get(1).y);
      else {
        int x[] = new int[v.size()];
        int y[] = new int[v.size()];

        for (int i = 0; i < v.size(); i++) {
          x[i] = v.get(i).x;
          y[i] = v.get(i).y;
        }
        g.drawPolygon(x, y, v.size());
      }
    }
  }

  public static void main(String[] args) {
    new java12_7();
  }
}
