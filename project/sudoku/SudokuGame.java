package project.sudoku;
import javax.swing.*;
import javax.swing.event.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.Random;

public class SudokuGame extends JFrame {
    private JTextField[][] fields; // 스도쿠 보드의 각 셀에 대한 JTextField 배열
    private int[][] solution; // 완전한 스도쿠 솔루션을 저장하는 배열
    private int[][] puzzle; // 사용자가 풀어야 할 스도쿠 퍼즐을 저장하는 배열
    private JLabel[] countLabels; // 각 숫자 카운트를 표시하는 레이블 배열
    private static final int SIZE = 9; // 스도쿠 보드의 크기
    private static final int SMALL_SIZE = 3; // 작은 3x3 그룹의 크기
    private int difficulty; // 스도쿠의 난이도를 설정하는 변수
    private int rowDifficulty; // 같은 행에 삽입되는 숫자를 제한

    public SudokuGame() {

        JFrame startFrame;
        JButton startButton;
        JButton exitButton;

        startFrame = new JFrame("Sudoku Game");
        startFrame.setSize(600, 600);
        startFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        startFrame.setLayout(new GridLayout(2, 1));

        JLabel gameName = new JLabel("9x9 스도쿠 게임!");
        gameName.setFont(new Font("나눔", Font.BOLD, 50));
        gameName.setHorizontalAlignment(JLabel.CENTER);

        startButton = new JButton("게임 시작");
        startButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JFrame difficultyFrame = new JFrame("난이도 선택");
                difficultyFrame.setSize(400, 300);
                difficultyFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); // 창을 닫을 때 종료하지 않고 닫기만 함
                difficultyFrame.setLayout(new GridLayout(2, 1));
        
                JLabel difficultyLabel = new JLabel("난이도를 선택하세요!");
                difficultyLabel.setFont(new Font("나눔", Font.BOLD, 20));
                difficultyLabel.setHorizontalAlignment(JLabel.CENTER);
                difficultyFrame.add(difficultyLabel);
                
                JPanel difficultyPanel = new JPanel(new FlowLayout());
                difficultyFrame.add(difficultyPanel);

                JButton veryEasyButton = new JButton("Very Esay");
                veryEasyButton.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        startFrame.setVisible(false);
                        difficultyFrame.dispose(); // 다이얼로그 닫기
                        difficulty = 27;
                        rowDifficulty = 3;
                        initializeGame();
                    }
                });
                difficultyPanel.add(veryEasyButton);
        
                JButton easyButton = new JButton("Esay");
                easyButton.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        startFrame.setVisible(false);
                        difficultyFrame.dispose();
                        difficulty = 36;
                        rowDifficulty = 4;
                        initializeGame();
                    }
                });
                difficultyPanel.add(easyButton);
        
                JButton normalButton = new JButton("Normal");
                normalButton.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        startFrame.setVisible(false);
                        difficultyFrame.dispose();
                        difficulty = 45;
                        rowDifficulty = 5;
                        initializeGame();
                    }
                });
                difficultyPanel.add(normalButton);

                JButton hardButton = new JButton("Hard");
                hardButton.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        startFrame.setVisible(false);
                        difficultyFrame.dispose();
                        difficulty = 54;
                        rowDifficulty = 6;
                        initializeGame();
                    }
                });
                difficultyPanel.add(hardButton);
        
                // 다이얼로그를 화면 중앙에 표시
                difficultyFrame.setLocationRelativeTo(null);
                difficultyFrame.setVisible(true);
            }
        });

        exitButton = new JButton("게임 종료");
        exitButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

        JPanel buttonSet = new JPanel(new FlowLayout());
        buttonSet.add(startButton);
        buttonSet.add(exitButton);

        JPanel gameNamePanel = new JPanel(new BorderLayout());
        gameNamePanel.add(gameName, BorderLayout.CENTER);
        startFrame.add(gameNamePanel);

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(buttonSet, BorderLayout.CENTER);
        startFrame.add(buttonSet);

        // 시작 화면을 화면 중앙에 표시
        startFrame.setLocationRelativeTo(null);
        startFrame.setVisible(true);
    }

    // 스도쿠 게임 초기화 메소드
    private void initializeGame() {
        setTitle("Sudoku Game");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(600, 600);

        JMenuBar menuBar = new JMenuBar();
        setJMenuBar(menuBar);

        JMenu fileMenu = new JMenu("메뉴");
        menuBar.add(fileMenu); //프레임 상단에 메뉴 바

        JMenuItem goToStartFrame = new JMenuItem("처음으로");
        goToStartFrame.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
                new SudokuGame();
            }
        });
        fileMenu.add(goToStartFrame);
    
        JMenuItem newGameMenuItem = new JMenuItem("새 게임 시작(F5)");
        newGameMenuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                resetGame();
            }
        });
        
        addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_F5) {
                    resetGame();
                }
            }
        });
        fileMenu.add(newGameMenuItem);

        JMenuItem exitMenuItem = new JMenuItem("게임 종료");
        exitMenuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
        fileMenu.add(exitMenuItem);

        JMenu helpMenu = new JMenu("도움말");
        menuBar.add(helpMenu);

        JMenuItem aboutMenuItem = new JMenuItem("도움말");
        aboutMenuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(SudokuGame.this, "이곳에 도움말을 표시합니다.", "도움말", JOptionPane.INFORMATION_MESSAGE);
            }
        });
        helpMenu.add(aboutMenuItem);

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
                fields[i][j].setEnabled(false);
                fields[i][j].setBackground(new Color(220, 220, 220));
                fields[i][j].setDisabledTextColor(Color.BLACK); // 비활성 상태일 때, 글자 색 설정
            }
            fields[i][j].setHorizontalAlignment(JTextField.CENTER); // 텍스트 중앙 정렬
            fields[i][j].setFont(new Font("나눔", Font.BOLD, 20));
            fields[i][j].getDocument().addDocumentListener(new DocumentListener() {
                // 1. 문자를 삽입하거나 지웠을 경우
                @Override
                public void insertUpdate(DocumentEvent e) {
                    // 문자가 삽입되었을 때
                    String input = fields[ROW][COL].getText();
                    char c = input.charAt(0);
                    if (input.length() > 0 && ( c >= '0' && c <= '9')) {
                        puzzle[ROW][COL] = Integer.parseInt(input);
                        updateCountLabels();
                    } else {
                        fields[ROW][COL].setText("");
                    }
                }

                @Override
                public void removeUpdate(DocumentEvent e) {
                    // 문자가 삭제되었을 때
                    puzzle[ROW][COL] = 0;
                    updateCountLabels();
                }

                @Override
                public void changedUpdate(DocumentEvent e) {
                    // 스타일 등이 변경되었을 때, 사용하지 않음.
                }
            });

            fields[i][j].addKeyListener(new KeyAdapter() {
                // 2. 공란에 BackSpace키를 입력했을 경우
                @Override
                public void keyTyped(KeyEvent e) {
                    char c = e.getKeyChar();
                    String input = String.valueOf(c); 
                    if (!(c >= '1' && c <= '9') || fields[ROW][COL].getText().length() >= 1 ) {
                        e.consume();
                    } else {
                        // 9번 모두 입력했을 경우, 입력을 취소
                        if (isNumberUsed(Integer.valueOf(input))) e.consume();
                        else {
                            puzzle[ROW][COL] = Integer.valueOf(input);
                            updateCountLabels();
                        }
                    }
                }

                // 3. 텍스트 필드에 포커스가 가 있어도, F5를 누르면 재시작
                @Override
                public void keyPressed(KeyEvent e) {
                    if (e.getKeyCode() == KeyEvent.VK_F5) {
                        resetGame();
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

        JLabel[] number = new JLabel[10];
        JPanel numberPanel = new JPanel(new GridLayout(1, 10));
        JLabel label1 = new JLabel("숫자");
        label1.setHorizontalAlignment(JLabel.CENTER);
        label1.setFont(new Font("나눔", Font.BOLD, 20));
        numberPanel.add(label1);
        for (int i = 0; i < 9; i++) {
            number[i] = new JLabel(String.valueOf(i + 1));
            number[i].setHorizontalAlignment(JLabel.CENTER);
            number[i].setFont(new Font("나눔", Font.BOLD, 20));
            numberPanel.add(number[i]);
        }
        
        countLabels = new JLabel[10];
        JLabel label2 = new JLabel("사용");
        label2.setHorizontalAlignment(JLabel.CENTER);
        label2.setFont(new Font("나눔", Font.BOLD, 20));
        JPanel countPanel = new JPanel(new GridLayout(1, 10));
        countPanel.add(label2);
        for (int i = 0; i < 9; i++) {
            countLabels[i] = new JLabel("0");
            countLabels[i].setHorizontalAlignment(JLabel.CENTER);
            countLabels[i].setFont(new Font("나눔", Font.PLAIN, 20));
            countPanel.add(countLabels[i]);
        }
        
        JPanel panelSouth = new JPanel(new GridLayout(2, 1));
        panelSouth.add(numberPanel);
        panelSouth.add(countPanel);
        add(panelSouth, BorderLayout.SOUTH);

        updateCountLabels();

        add(panel);
        setLocationRelativeTo(null);
        setVisible(true);
    }
    
    private void generateSudoku() {
        solution = new int[SIZE][SIZE];
        solveSudoku(solution);
    
        puzzle = new int[SIZE][SIZE];
        Random random = new Random();
 
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                puzzle[i][j] = solution[i][j];
            }
        }
    
        // count : 빈 셀의 개수
        int count = 0;
        int[] rowEmpty = new int[9];
        while (count < difficulty) {
            int row = random.nextInt(SIZE);
            int col = random.nextInt(SIZE);
            if (puzzle[row][col] != 0 && rowEmpty[row] < rowDifficulty) {
                puzzle[row][col] = 0; // 빈 셀로 변경
                count++;
                rowEmpty[row]++;
            }
        }
        saveSudokuToFile("C:/java/project/sudoku/inputSudoku.txt");
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

    private void resetGame() {
        generateSudoku(); // 새로운 스도쿠 퍼즐 생성
        updateFields(); // 퍼즐을 보드에 업데이트
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                fields[i][j].setBackground(Color.WHITE); // 배경색을 모두 흰색으로 초기화
                if (puzzle[i][j] != 0) fields[i][j].setBackground(new Color(220, 220, 220)); // 그 중 비어있지 않은 부분은 다시 회색으로 색칠
            }
        }
    }
    
    private void updateFields() {
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                if (puzzle[i][j] != 0) {
                    fields[i][j].setText(String.valueOf(puzzle[i][j])); // 보드에 숫자 업데이트
                    fields[i][j].setEnabled(false);
                    fields[i][j].setDisabledTextColor(Color.BLACK); // 비활성 상태일 때, 글자 색 설정
                } else {
                    fields[i][j].setText(""); // 빈 칸으로 초기화
                    fields[i][j].setEnabled(true);
                }
            }
        }
        updateCountLabels(); // 숫자 카운트 업데이트
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
            if (counts[i] >= 8) countLabels[i].setForeground(Color.RED);
            else countLabels[i].setForeground(Color.BLACK);
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

    private void saveSudokuToFile(String filename) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename))) {
            for (int i = 0; i < SIZE; i++) {
                for (int j = 0; j < SIZE; j++) {
                    writer.write(puzzle[i][j] + '0');
                }
                writer.newLine();
            }
        } catch (IOException e) {
            System.err.println("Error writing to file: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        new SudokuGame();
    }
}