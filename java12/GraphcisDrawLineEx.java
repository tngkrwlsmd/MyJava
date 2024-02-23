package java12;
import javax.swing.*;
import java.awt.*;

public class GraphcisDrawLineEx extends JFrame {
  private MyPanel panel = new MyPanel();

  public GraphcisDrawLineEx() {
    setTitle("drawLine 사용 예제");
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setContentPane(panel);

    setSize(200, 170);
    setVisible(true);
  }

  private class MyPanel extends JPanel {
    public void paintComponent(Graphics g) {
      super.paintComponent(g);
      g.setColor(Color.RED);
      g.drawLine(20, 20, 100, 100);
    }
  }

  public static void main(String[] args) {
    new GraphcisDrawLineEx();
  }
}
