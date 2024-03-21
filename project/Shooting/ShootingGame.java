package project.Shooting;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;

public class ShootingGame extends JPanel implements KeyListener {

    private static final int WIDTH = 800;
    private static final int HEIGHT = 600;
    private static final int GO = 10;

    private int playerX = WIDTH / 2;
    private int playerY = HEIGHT / 2;

    private boolean shooting = false;
    private List<Bullet> bullets = new ArrayList<>();
    private List<Enemy> enemies = new ArrayList<>();

    private class Bullet {
        int x;
        int y;

        Bullet(int x, int y) {
            this.x = x;
            this.y = y;
        }

        void move() {
            y -= 5;
        }

        void draw(Graphics g) {
            g.setColor(Color.RED);
            g.fillRect(x, y, 5, 10);
        }
    }

    private class Enemy {
        int x;
        int y;

        Enemy(int x, int y) {
            this.x = x;
            this.y = y;
        }

        void move() {
            y += 2;
        }

        void draw(Graphics g) {
            g.setColor(Color.GREEN);
            g.fillRect(x, y, 20, 20);
        }
    }

    public ShootingGame() {
        setFocusable(true);
        addKeyListener(this);
        spawnEnemies();
        startGameLoop();
    }

    private void spawnEnemies() {
        Random random = new Random();
        for (int i = 0; i < 5; i++) {
            int x = random.nextInt(WIDTH);
            int y = random.nextInt(HEIGHT / 2);
            enemies.add(new Enemy(x, y));
        }
    }

    private void startGameLoop() {
      Thread gameLoop = new Thread(() -> {
          while (true) {
              update();
              repaint();
              try {
                  Thread.sleep(10);
              } catch (InterruptedException e) {
                  e.printStackTrace();
              }
          }
      });
      gameLoop.start();
  }
  
  private void update() {
      if (shooting) {
          bullets.add(new Bullet(playerX + 18, playerY));
          shooting = false;
      }
  
      for (Iterator<Bullet> iterator = bullets.iterator(); iterator.hasNext();) {
          Bullet bullet = iterator.next();
          bullet.move();
          if (bullet.y < 0) {
              iterator.remove();
          }
      }
  
      for (Iterator<Enemy> iterator = enemies.iterator(); iterator.hasNext();) {
          Enemy enemy = iterator.next();
          enemy.move();
          if (enemy.y > HEIGHT) {
              iterator.remove();
          }
      }
  
      // 새로운 적 생성
      Random random = new Random();
      if (random.nextDouble() < 0.01) { // 적이 생성될 확률
          int x = random.nextInt(WIDTH);
          int y = -20; // 적이 화면 위에서 생성되도록 y 좌표 설정
          enemies.add(new Enemy(x, y));
      }
  
      // 충돌 검사
      for (Iterator<Bullet> bulletIterator = bullets.iterator(); bulletIterator.hasNext();) {
          Bullet bullet = bulletIterator.next();
          for (Iterator<Enemy> enemyIterator = enemies.iterator(); enemyIterator.hasNext();) {
              Enemy enemy = enemyIterator.next();
              if (bullet.x >= enemy.x && bullet.x <= enemy.x + 20 &&
                      bullet.y >= enemy.y && bullet.y <= enemy.y + 20) {
                  bulletIterator.remove();
                  enemyIterator.remove();
              }
          }
      }
  }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        // 그리기
        g.setColor(Color.BLUE);
        g.fillRect(playerX, playerY, 40, 20);

        for (Bullet bullet : bullets) {
            bullet.draw(g);
        }

        for (Enemy enemy : enemies) {
            enemy.draw(g);
        }
    }

    @Override
    public void keyPressed(KeyEvent e) {
        int keyCode = e.getKeyCode();
        if (keyCode == KeyEvent.VK_LEFT) {
            if (playerX > 0) {
                playerX -= GO;
            }
        } else if (keyCode == KeyEvent.VK_RIGHT) {
            if (playerX < WIDTH - 40) {
                playerX += GO;
            }
        } else if (keyCode == KeyEvent.VK_UP) {
            if (playerY > 0) {
                playerY -= GO;
            }
        } else if (keyCode == KeyEvent.VK_DOWN) {
            if (playerY < HEIGHT - 20) {
                playerY += GO;
            }
        } else if (keyCode == KeyEvent.VK_SPACE) {
            shooting = true;
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {}

    @Override
    public void keyReleased(KeyEvent e) {}

    public static void main(String[] args) {
        JFrame frame = new JFrame("Simple Shooting Game");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(WIDTH, HEIGHT);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
        frame.add(new ShootingGame());
        frame.setVisible(true);
    }
}