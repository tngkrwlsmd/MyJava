package sudoku;
import javax.swing.*;
import java.awt.*;
import java.util.Random;

public class SudokuGame extends JFrame {
    private JTextField[][] fields; // 스도쿠 보드의 각 셀에 대한 JTextField 배열
    private int[][] solution; // 완전한 스도쿠 솔루션을 저장하는 배열
    private int[][] puzzle; // 사용자가 풀어야 할 스도쿠 퍼즐을 저장하는 배열
    private static final int SIZE = 9; // 스도쿠 보드의 크기
    private static final int SMALL_SIZE = 3; // 작은 3x3 그룹의 크기

    public SudokuGame() {
        setTitle("Sudoku Game");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(600, 600);
        JPanel panel = new JPanel(new GridLayout(SIZE, SIZE));
        fields = new JTextField[SIZE][SIZE]; // 스도쿠 보드의 각 셀을 위한 JTextField 배열 생성

        generateSudoku(); // 스도쿠 퍼즐 생성

        for (int i = 0; i < SIZE; i++) {
          for (int j = 0; j < SIZE; j++) {
              fields[i][j] = new JTextField(); // 각 셀의 JTextField 생성
              if (puzzle[i][j] != 0) {
                  fields[i][j].setText(String.valueOf(puzzle[i][j])); // 초기에 표시되는 숫자 설정
                  fields[i][j].setEditable(false); // 사용자가 수정할 수 없도록 설정
              }
              fields[i][j].setHorizontalAlignment(JTextField.CENTER); // 텍스트 중앙 정렬
              fields[i][j].setFont(new Font("Arial", Font.BOLD, 20)); // 폰트 설정
              panel.add(fields[i][j]); // 패널에 JTextField 추가

              // 3x3 작은 스도쿠 경계에 진한 테두리 추가
              if ((i + 1) % SMALL_SIZE == 0 && (j + 1) % SMALL_SIZE == 0) {
                  fields[i][j].setBorder(BorderFactory.createMatteBorder(1, 1, 3, 3, Color.BLACK));
              } else if ((i + 1) % SMALL_SIZE == 0) {
                  fields[i][j].setBorder(BorderFactory.createMatteBorder(1, 1, 3, 1, Color.BLACK));
              } else if ((j + 1) % SMALL_SIZE == 0) {
                  fields[i][j].setBorder(BorderFactory.createMatteBorder(1, 1, 1, 3, Color.BLACK));
              } else {
                  fields[i][j].setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Color.BLACK));
              }
          }
      }

        add(panel);
        setVisible(true);
    }

    private void generateSudoku() {
        // 완전한 스도쿠 솔루션 생성
        solution = new int[SIZE][SIZE];
        solveSudoku(solution);

        // 일부 숫자를 제거하여 사용자가 풀 수 있는 퍼즐 생성
        puzzle = new int[SIZE][SIZE];
        Random random = new Random();
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                if (random.nextDouble() > 0.66) { // 난이도 조절을 위한 확률 설정
                    puzzle[i][j] = solution[i][j];
                }
            }
        }
    }

    private boolean solveSudoku(int[][] board) {
        for (int row = 0; row < SIZE; row++) {
            for (int col = 0; col < SIZE; col++) {
                if (board[row][col] == 0) {
                    for (int num = 1; num <= SIZE; num++) {
                        if (isValid(board, row, col, num)) {
                            board[row][col] = num;
                            if (solveSudoku(board)) {
                                return true;
                            }
                            board[row][col] = 0;
                        }
                    }
                    return false;
                }
            }
        }
        return true;
    }

    private boolean isValid(int[][] board, int row, int col, int num) {
        // 행과 열 검사
        for (int i = 0; i < SIZE; i++) {
            if (board[row][i] == num || board[i][col] == num) {
                return false;
            }
        }
        // 작은 3x3 그룹 검사
        int startRow = row - row % SMALL_SIZE;
        int startCol = col - col % SMALL_SIZE;
        for (int i = startRow; i < startRow + SMALL_SIZE; i++) {
            for (int j = startCol; j < startCol + SMALL_SIZE; j++) {
                if (board[i][j] == num) {
                    return false;
                }
            }
        }
        return true;
    }

    public static void main(String[] args) {
        new SudokuGame();
    }
}
