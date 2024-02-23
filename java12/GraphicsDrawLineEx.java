package java12;
import javax.swing.*;
import java.awt.*;
import java.util.*;
import java.awt.event.*;

public class GraphicsDrawLineEx extends JFrame {
  private MyPanel panel = new MyPanel();

  public GraphicsDrawLineEx() {
    setTitle("drawing Line by Mouse 예제");
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setContentPane(panel);
    
    setSize(300, 300);
    setVisible(true);
  }

  private class MyPanel extends JPanel {
    private Vector<Point> vStart = new Vector<>(); //시작점
    private Vector<Point> vEnd = new Vector<>(); //끝점

    private MyPanel() {
      addMouseListener(new MouseAdapter() {
        public void mousePressed(MouseEvent e) {
          Point startP = e.getPoint();
          vStart.add(startP);
        }

        public void mouseReleased(MouseEvent e) {
          Point endP = e.getPoint();
          vEnd.add(endP);

          repaint(); //MyPanel의 paintComponent()가 다시 실행
        }
      });
    }

    public void paintComponent(Graphics g) {
      super.paintComponent(g);
      g.setColor(Color.BLUE);

      for (int i = 0; i < vStart.size(); i++) {
        Point s = vStart.elementAt(i);
        Point e = vEnd.elementAt(i);

        g.drawLine((int)s.getX(), (int)s.getY(), (int)e.getX(), (int)e.getY());
      }
    }
  }

  public static void main(String[] args) {
    new GraphicsDrawLineEx();
  }
}
