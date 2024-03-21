package project.sudoku;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Random;

public class SudokuGame extends JFrame {
    private JTextField[][] fields; // 스도쿠 보드의 각 셀에 대한 JTextField 배열
    private int[][] solution; // 완전한 스도쿠 솔루션을 저장하는 배열
    private int[][] puzzle; // 사용자가 풀어야 할 스도쿠 퍼즐을 저장하는 배열
    private JLabel[] countLabels; // 각 숫자 카운트를 표시하는 레이블 배열
    private static final int SIZE = 9; // 스도쿠 보드의 크기
    private static final int SMALL_SIZE = 3; // 작은 3x3 그룹의 크기

    public SudokuGame() {
        setTitle("Sudoku Game");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(600, 600);
        JPanel panel = new JPanel(new GridLayout(SIZE, SIZE));
        fields = new JTextField[SIZE][SIZE];

        generateSudoku(); // 스도쿠 퍼즐 생성

        for (int i = 0; i < SIZE; i++) {
          for (int j = 0; j < SIZE; j++) {
            final int ROW = i;
            final int COL = j;

            fields[i][j] = new JTextField();
            if (puzzle[i][j] != 0) {
                fields[i][j].setText(String.valueOf(puzzle[i][j])); // 초기에 표시되는 숫자 설정
                fields[i][j].setEditable(false);
            }
            fields[i][j].setHorizontalAlignment(JTextField.CENTER); // 텍스트 중앙 정렬
            fields[i][j].setFont(new Font("Arial", Font.BOLD, 20));
            fields[i][j].addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                char c = e.getKeyChar();
                String input = String.valueOf(c); 
                if (!(c >= '1' && c <= '9') || fields[ROW][COL].getText().length() >= 1 ) {
                    e.consume(); // 1에서 9 사이의 숫자가 아닌 경우 입력을 취소
                } else { //숫자를 입력했을 경우
                    puzzle[ROW][COL] = Integer.valueOf(input);
                    // 9번 모두 입력했을 경우, 입력을 취소
                    if (isNumberUsed(Integer.valueOf(input))) e.consume();
                    updateCountLabels();
                }
            }

            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_BACK_SPACE) {
                    puzzle[ROW][COL] = 0;
                    updateCountLabels();
                }
            }
            });
            
            panel.add(fields[i][j]);

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

        JLabel[] number = new JLabel[9];
        JPanel numberPanel = new JPanel(new GridLayout(1, 9));
        for (int i = 0; i < 9; i++) {
            number[i] = new JLabel(String.valueOf(i + 1));
            number[i].setHorizontalAlignment(JLabel.CENTER);
            number[i].setFont(new Font("Arial", Font.BOLD, 20));
            numberPanel.add(number[i]);
        }

        countLabels = new JLabel[9];
        JPanel countPanel = new JPanel(new GridLayout(1, 9));
        for (int i = 0; i < 9; i++) {
            countLabels[i] = new JLabel("0");
            countLabels[i].setHorizontalAlignment(JLabel.CENTER);
            countLabels[i].setFont(new Font("Arial", Font.PLAIN, 15));
            countPanel.add(countLabels[i]);
        }

        JPanel panelSouth = new JPanel(new GridLayout(2, 1));
        panelSouth.add(numberPanel);
        panelSouth.add(countPanel);
        add(panelSouth, BorderLayout.SOUTH);

        updateCountLabels();

        add(panel);
        setVisible(true);
    }

    private void generateSudoku() {
        solution = new int[SIZE][SIZE];
        solveSudoku(solution);

        puzzle = new int[SIZE][SIZE];
        int count = 0; //전체 표시된 숫자
        Random random = new Random();
        for (int i = 0; i < SIZE; i++) {
            int rowCount = 0; //행에 표시된 숫자
            for (int j = 0; j < SIZE; j++) {
                // 난이도 조절을 위한 확률 설정
                if (random.nextDouble() > 0.6 && (rowCount < 3 && count < 27)) {
                    puzzle[i][j] = solution[i][j];
                    rowCount++;
                    count++;
                }
            }
        }
    }

    private boolean solveSudoku(int[][] board) {
        Random random = new Random();
        for (int row = 0; row < SIZE; row++) {
            for (int col = 0; col < SIZE; col++) {
                // numbers를 랜덤으로 섞어 백트래킹
                if (board[row][col] == 0) {
                    int[] numbers = {1, 2, 3, 4, 5, 6, 7, 8, 9};
                    shuffleArray(numbers, random);
                    for (int num : numbers) {
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
    
    // 배열을 무작위로 섞는 메소드
    private void shuffleArray(int[] array, Random random) {
        for (int i = array.length - 1; i > 0; i--) {
            int index = random.nextInt(i + 1);
            int temp = array[index];
            array[index] = array[i];
            array[i] = temp;
        }
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

    private void updateCountLabels() {
        int[] counts = new int[9];
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                int number = puzzle[i][j];
                if (number != 0 && counts[number - 1] < 9) { //각 숫자는 9개를 초과할 수 없음
                    counts[number - 1]++;
                }
            }
        }
        for (int i = 0; i < 9; i++) {
            countLabels[i].setText(String.valueOf(counts[i]));
        }
    }

    private boolean isNumberUsed(int number) {
        // 해당 숫자가 이미 9번 사용되었는지 확인
        int count = 0;
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                if (puzzle[i][j] == number) {
                    count++;
                }
            }
        }
        return count >= 9;
    }

    public static void main(String[] args) {
        new SudokuGame();
    }
}
