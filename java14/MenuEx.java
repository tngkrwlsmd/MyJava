package java14;
import javax.swing.*;

public class MenuEx extends JFrame {
  public MenuEx() {
    setTitle("Menu 만들기 예제");
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    createMenu();
    setSize(250, 200);
    setVisible(true);
  }

  private void createMenu() {
    JMenuBar mb = new JMenuBar();
    JMenu screenMenu = new JMenu("Screen");

    screenMenu.add(new JMenuItem("Load"));
    screenMenu.add(new JMenuItem("Hide"));
    screenMenu.add(new JMenuItem("Reshow"));
    screenMenu.addSeparator(); //분리선
    screenMenu.add(new JMenuItem("Exit"));

    mb.add(screenMenu);
    mb.add(new JMenu("Edit"));
    mb.add(new JMenu("Source"));
    mb.add(new JMenu("Project"));
    mb.add(new JMenu("Run"));

    setJMenuBar(mb);
  }

  public static void main(String[] args) {
    new MenuEx();
  }
}