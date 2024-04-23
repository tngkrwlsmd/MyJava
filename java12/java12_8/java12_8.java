package java12.java12_8;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.Vector;

public class java12_8 extends JFrame {
  Point p1, p2;
  Vector<Point> v1 = new Vector<Point>();
  Vector<Point> v2 = new Vector<Point>();
  Vector<Circle> c = new Vector<Circle>();
  Circle circle;
  Pan pan = new Pan();

  public java12_8() {
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setContentPane(pan);

    pan.addMouseListener(new MouseAdapter() {
      @Override
      public void mousePressed(MouseEvent e) {
        p1 = e.getPoint();
        v1.add(p1);
      }
      @Override
      public void mouseReleased(MouseEvent e) {
				c.add(circle);
			}
    });

    pan.addMouseMotionListener(new MouseMotionAdapter() {
      @Override
      public void mouseDragged(MouseEvent e) {
        p2 = e.getPoint();
        v2.add(p2);
        repaint();
      }
    });
    setFocusable(true);
    requestFocus();

    setSize(400, 400);
    setVisible(true);
  }

  private class Pan extends JPanel {
    @Override
    public void paintComponent(Graphics g) {
      super.paintComponent(g);
      g.setColor(Color.MAGENTA);

      for (int i = 0; i < v1.size(); i++) {
        int diamater = p2.x - p1.x;
        g.drawOval(p1.x, p1.y, diamater, diamater);
        circle = new Circle(p1.x, p1.y, diamater);
      }

      for (int i = 0; i < c.size(); i++) {
        g.drawOval(c.get(i).x, c.get(i).y, c.get(i).diamater, c.get(i).diamater);
      }
    }
  }

  private class Circle {
    int x, y, diamater;
    
    public Circle(int x, int y, int diamater) {
      this.x = x;
      this.y = y;
      this.diamater = diamater;
    }

  }

  public static void main(String[] args) {
    new java12_8();
  }
}
