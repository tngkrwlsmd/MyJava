package sudoku;
import javax.swing.*;
import javax.swing.Timer;
import javax.swing.event.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.*;

public class SudokuGame extends JFrame {
    private JTextField[][] fields; // 스도쿠 보드의 각 셀에 대한 JTextField 배열
    private int[][] solution; // 완전한 스도쿠 솔루션을 저장하는 배열
    private int[][] puzzle; // 사용자가 풀어야 할 스도쿠 퍼즐을 저장하는 배열
    private int[][] first; // 초기 입력된 스도쿠
    private JLabel[] countLabels; // 각 숫자 카운트를 표시하는 레이블 배열
    private int[][] userInput; // 사용자가 입력한 숫자를 저장하는 배열

    private JLabel[][][] memoFields; // 각 셀의 메모를 저장하는 필드
    private boolean[][][] memoVisible;
    private boolean memoModeEnabled;

    private int totalEmptyCells; // 스도쿠의 난이도를 설정하는 변수
    private int rowEmptyCellLimit; // 같은 행에 삽입되는 숫자를 제한

    private JLabel timeLabel; // 시간을 나타낼 레이블
    private Timer timer; // 타이머 변수
    private int secondsPassed; // 경과된 시간 (초)

    private int hintCount; // 사용한 힌트 개수(최대 10개)

    public SudokuGame() {

        JFrame startFrame;
        JButton startButton;
        JButton loadButton;
        JButton exitButton;

        startFrame = new JFrame("Sudoku Game");
        startFrame.setSize(600, 600);
        startFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        startFrame.setLayout(new GridLayout(2, 1));
        startFrame.setResizable(false);

        JLabel gameName = new JLabel("9x9 스도쿠 게임!");
        gameName.setFont(new Font("나눔", Font.BOLD, 50));
        gameName.setHorizontalAlignment(JLabel.CENTER);

        startButton = new JButton("게임 시작");
        startButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JFrame totalEmptyCellsFrame = new JFrame("난이도 선택");
                totalEmptyCellsFrame.setSize(400, 400);
                totalEmptyCellsFrame.setResizable(false);
                totalEmptyCellsFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); // 창을 닫을 때 종료하지 않고 닫기만 함
                totalEmptyCellsFrame.setLayout(new GridLayout(2, 1));
        
                JLabel totalEmptyCellsLabel = new JLabel("난이도를 선택하세요!");
                totalEmptyCellsLabel.setFont(new Font("나눔", Font.BOLD, 20));
                totalEmptyCellsLabel.setHorizontalAlignment(JLabel.CENTER);
                totalEmptyCellsFrame.add(totalEmptyCellsLabel);
                
                JPanel totalEmptyCellsPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 400, 10));
                totalEmptyCellsFrame.add(totalEmptyCellsPanel);

                JButton veryEasyButton = new JButton("Very Esay");
                veryEasyButton.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        startFrame.setVisible(false);
                        totalEmptyCellsFrame.dispose(); // 다이얼로그 닫기
                        totalEmptyCells = 27;
                        rowEmptyCellLimit = 3;
                        initializeGame();
                    }
                });
                totalEmptyCellsPanel.add(veryEasyButton);
        
                JButton easyButton = new JButton("Esay");
                easyButton.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        startFrame.setVisible(false);
                        totalEmptyCellsFrame.dispose();
                        totalEmptyCells = 36;
                        rowEmptyCellLimit = 4;
                        initializeGame();
                    }
                });
                totalEmptyCellsPanel.add(easyButton);
        
                JButton normalButton = new JButton("Normal");
                normalButton.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        startFrame.setVisible(false);
                        totalEmptyCellsFrame.dispose();
                        totalEmptyCells = 45;
                        rowEmptyCellLimit = 5;
                        initializeGame();
                    }
                });
                totalEmptyCellsPanel.add(normalButton);

                JButton hardButton = new JButton("Hard");
                hardButton.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        startFrame.setVisible(false);
                        totalEmptyCellsFrame.dispose();
                        totalEmptyCells = 54;
                        rowEmptyCellLimit = 6;
                        initializeGame();
                    }
                });
                totalEmptyCellsPanel.add(hardButton);
        
                // 다이얼로그를 화면 중앙에 표시
                totalEmptyCellsFrame.setLocationRelativeTo(null);
                totalEmptyCellsFrame.setVisible(true);
            }
        });

        loadButton = new JButton("불러오기");
        loadButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                File autoSaveFile = new File(System.getProperty("user.home") + "/Documents/SaveSudoku/autoSave.sgd");
                if (autoSaveFile.exists()) {                   
                    startFrame.setVisible(false);
                    initializeSaveGame();
                } else JOptionPane.showMessageDialog(SudokuGame.this, "저장된 게임 데이터가 없습니다.", "알림", JOptionPane.INFORMATION_MESSAGE);           
            }
        });

        exitButton = new JButton("게임 종료");
        exitButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

        JPanel buttonSet = new JPanel(new FlowLayout(FlowLayout.CENTER, 600, 20));
        buttonSet.add(startButton);
        buttonSet.add(loadButton);
        buttonSet.add(exitButton);

        JLabel developer = new JLabel("제작자 : ㅇㅇ");
        developer.setFont(new Font("나눔", Font.BOLD, 20));
        developer.setHorizontalAlignment(JLabel.CENTER);

        JPanel gameNamePanel = new JPanel(new BorderLayout());
        gameNamePanel.add(gameName, BorderLayout.CENTER);
        gameNamePanel.add(developer, BorderLayout.SOUTH);
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
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE); // 기본 종료 동작 무시
        setSize(800, 800);
        setResizable(false);
        Container c = getContentPane();

        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                int result = JOptionPane.showConfirmDialog(SudokuGame.this, "정말로 종료하시겠습니까?\n종료시 게임은 자동 저장됩니다.", "종료 확인", JOptionPane.YES_NO_OPTION);
                if (result == JOptionPane.YES_OPTION) {
                    saveGameDataFile("gameData.sgd");
                    saveSudokuToFile("firstSudoku.sgd", first);
                    saveSudokuToFile("userInput.sgd", userInput);
                    saveSudokuToFile("autoSave.sgd", puzzle); // 게임이 종료될 때 자동으로 저장
                    saveSudokuMemoFile("memoFile.sgd");
                    System.exit(0);
                }
            }
        });

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
        
        c.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_F5) {
                    resetGame();
                }
            }
        });
        fileMenu.add(newGameMenuItem);

        JMenu helpMenu = new JMenu("도움말");
        menuBar.add(helpMenu);

        JMenuItem aboutMenuItem = new JMenuItem("도움말");
        aboutMenuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String message = "1. 같은 줄과 작은 3x3 격자판 안에 똑같은 숫자가 없게 배치하세요. \n2. 너무 어렵다 싶으면 힌트를 사용하세요! 최대 10번까지 사용이 가능합니다." + 
                "\n3. 메모 버튼을 클릭 후, 1 ~ 9 까지의 숫자를 메모할 수 있습니다. 메모를 지우려면 똑같은 숫자를 다시 누르세요.";
                JOptionPane.showMessageDialog(SudokuGame.this, message, "도움말", JOptionPane.INFORMATION_MESSAGE);
            }
        });
        helpMenu.add(aboutMenuItem);

        JPanel panel = new JPanel(new GridLayout(9, 9));
        JPanel[][] memoPanel = new JPanel[9][9];
        KeyListener[][] keyListeners = new KeyListener[9][9];
        KeyListener[][] memoKeyListeners = new KeyListener[9][9];
        
        fields = new JTextField[9][9];
        userInput = new int[9][9];
        memoFields = new JLabel[9][9][9];
        memoVisible = new boolean[9][9][9];
        generateSudoku(); // 스도쿠 퍼즐 생성

        for (int i = 0; i < 9; i++) {
          for (int j = 0; j < 9; j++) {
            final int ROW = i;
            final int COL = j;

            fields[i][j] = new JTextField();
            memoPanel[i][j] = new JPanel(new GridLayout(3, 3));
            memoPanel[i][j].setOpaque(false);

            for (int k = 0; k < 9; k++) {
                memoFields[i][j][k] = new JLabel();
                memoFields[i][j][k].setFont(new Font("나눔", Font.PLAIN, 12));
                memoFields[i][j][k].setForeground(Color.GRAY);
                memoFields[i][j][k].setText(" " + Integer.toString(k + 1));
                memoFields[i][j][k].setVisible(false);
                memoPanel[i][j].add(memoFields[i][j][k]);
            }

            JPanel overlayPanel = new JPanel();
            overlayPanel.setLayout(new OverlayLayout(overlayPanel));
            overlayPanel.add(memoPanel[i][j]);
            overlayPanel.add(fields[i][j]);

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
                        updateInputArray(ROW, COL);
                        updateCountLabels();
                    } else {
                        fields[ROW][COL].setText("");
                    }
                }

                @Override
                public void removeUpdate(DocumentEvent e) {
                    // 문자가 삭제되었을 때
                    puzzle[ROW][COL] = 0;
                    updateInputArray(ROW, COL);
                    updateCountLabels();
                }

                @Override
                public void changedUpdate(DocumentEvent e) {
                    // 스타일 등이 변경되었을 때, 사용하지 않음.
                }
            });

            keyListeners[i][j] = new KeyAdapter() {
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
                        else if (memoModeEnabled) e.consume(); // 메모 상태일 때는 입력하면 안 됨
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
            };
            fields[i][j].addKeyListener(keyListeners[i][j]);

            panel.add(overlayPanel);
 
            // 3x3 작은 스도쿠 경계에 진한 테두리 추가
            if ((i + 1) % 3 == 0 && (j + 1) % 3 == 0) {
                fields[i][j].setBorder(BorderFactory.createMatteBorder(1, 1, 3, 3, Color.BLACK));
            } else if ((i + 1) % 3 == 0) {
                fields[i][j].setBorder(BorderFactory.createMatteBorder(1, 1, 3, 1, Color.BLACK));
            } else if ((j + 1) % 3 == 0) {
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

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 100, 10)); 
        JButton hintButton = new JButton("힌트");
        hintButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (hintCount >= 0 && hintCount < 10) {
                    int hint = JOptionPane.showConfirmDialog(SudokuGame.this, "힌트를 사용하시겠습니까?\n사용 횟수: " + hintCount + " / 10", "힌트", JOptionPane.YES_NO_OPTION);
                    if (hint == JOptionPane.YES_OPTION) applyHint();
                } else {
                    JOptionPane.showMessageDialog(SudokuGame.this, "힌트를 모두 사용하셨습니다!", "힌트", JOptionPane.INFORMATION_MESSAGE);
                }
            }
        });

        JButton memoButton = new JButton("메모");
        memoButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (memoButton.getText().equals("메모")) {
                    memoModeEnabled = true;
                    memoButton.setText("해제");
                }
                else {
                    memoModeEnabled = false;
                    memoButton.setText("메모");
                }
                updateMemoField(memoKeyListeners);
            }
        });

        JButton checkSudokuButton = new JButton("검사");
        checkSudokuButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (pull()) {
                    boolean success = true;
                    for (int i = 0; i < 9; i++) {
                        for (int j = 0; j < 9; j++) {
                            int num = puzzle[i][j];
                            if (num != 0 && !isValid(puzzle, i, j, num)) {
                                success = false;
                                break;
                            }
                        }
                        if (!success) break;
                    }
                    if (success) JOptionPane.showMessageDialog(SudokuGame.this, "스도쿠가 알맞게 작성되었습니다!", "성공!", JOptionPane.INFORMATION_MESSAGE);
                    else JOptionPane.showMessageDialog(SudokuGame.this, "스도쿠가 알맞게 작성되지 않았습니다!\n다시 작성해주세요!", "실패!", JOptionPane.INFORMATION_MESSAGE);
                } else {
                    JOptionPane.showMessageDialog(SudokuGame.this, "스도쿠가 모두 작성되지 않았습니다!\n모두 채우고나서 검사해주세요!", "실패!", JOptionPane.INFORMATION_MESSAGE);
                }
            }
        });

        JButton exitButton = new JButton("종료");
        exitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int exit = JOptionPane.showConfirmDialog(SudokuGame.this, "정말로 종료하시겠습니까?\n종료시 게임은 자동 저장됩니다.", "종료 확인", JOptionPane.YES_NO_OPTION);
                if (exit == JOptionPane.YES_OPTION) {
                    saveGameDataFile("gameData.sgd");
                    saveSudokuToFile("firstSudoku.sgd", first);
                    saveSudokuToFile("userInput.sgd", userInput);
                    saveSudokuToFile("autoSave.sgd", puzzle);
                    saveSudokuMemoFile("memoFile.sgd");
                    System.exit(0);
                }
            }
        });

        timeLabel = new JLabel("00:00");
        timeLabel.setHorizontalAlignment(SwingConstants.CENTER);
        timeLabel.setFont(new Font("나눔", Font.BOLD, 20));
    
        secondsPassed = 0;
        timer = new Timer(1000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                secondsPassed++;
                updateTimeLabel();
            }
        });
        timer.start();

        buttonPanel.add(timeLabel);
        buttonPanel.add(hintButton);
        buttonPanel.add(memoButton);
        buttonPanel.add(checkSudokuButton);
        buttonPanel.add(exitButton);

        // 가끔 F5 키를 눌러도 재시작이 안 되는 오류 수정
        JButton[] buttons = {hintButton, memoButton, checkSudokuButton, exitButton};

        for (JButton button : buttons) {
            button.addFocusListener(new FocusAdapter() {
                @Override
                public void focusGained(FocusEvent e) {
                    button.transferFocus(); // 포커스를 잃도록 설정
                }
            });
        }

        JPanel UI_Panel = new JPanel(new GridLayout(2, 1));
        UI_Panel.add(panelSouth);
        UI_Panel.add(buttonPanel);

        //패널 부착
        c.add(panel);
        c.add(UI_Panel, BorderLayout.SOUTH);

        c.setFocusable(true);
        c.requestFocus();

        updateCountLabels();
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private void initializeSaveGame() {
        setTitle("Sudoku Game");
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE); // 기본 종료 동작 무시
        setSize(800, 800);
        setResizable(false);
        Container c = getContentPane();

        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                int result = JOptionPane.showConfirmDialog(SudokuGame.this, "정말로 종료하시겠습니까?\n종료시 게임은 자동 저장됩니다.", "종료 확인", JOptionPane.YES_NO_OPTION);
                if (result == JOptionPane.YES_OPTION) {
                    saveGameDataFile("gameData.sgd");
                    saveSudokuToFile("firstSudoku.sgd", first);
                    saveSudokuToFile("userInput.sgd", userInput);
                    saveSudokuToFile("autoSave.sgd", puzzle); // 게임이 종료될 때 자동으로 저장
                    saveSudokuMemoFile("memoFile.sgd");
                    System.exit(0);
                }
            }
        });

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
        
        c.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_F5) {
                    resetGame();
                }
            }
        });
        fileMenu.add(newGameMenuItem);

        JMenu helpMenu = new JMenu("도움말");
        menuBar.add(helpMenu);

        JMenuItem aboutMenuItem = new JMenuItem("도움말");
        aboutMenuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String message = "1. 같은 줄과 작은 3x3 격자판 안에 똑같은 숫자가 없게 배치하세요. \n2. 너무 어렵다 싶으면 힌트를 사용하세요! 최대 10번까지 사용이 가능합니다." + 
                "\n3. 메모 버튼을 클릭 후, 1 ~ 9 까지의 숫자를 메모할 수 있습니다. 메모를 지우려면 똑같은 숫자를 다시 누르세요.";
                JOptionPane.showMessageDialog(SudokuGame.this, message, "도움말", JOptionPane.INFORMATION_MESSAGE);
            }
        });
        helpMenu.add(aboutMenuItem);
        
        JPanel panel = new JPanel(new GridLayout(9, 9));
        JPanel[][] memoPanel = new JPanel[9][9];
        KeyListener[][] keyListeners = new KeyListener[9][9];
        KeyListener[][] memoKeyListeners = new KeyListener[9][9];

        fields = new JTextField[9][9];
        userInput = new int[9][9];
        memoFields = new JLabel[9][9][9];
        memoVisible = new boolean[9][9][9];
        // '불러오기'에서 새로 추가된 부분 ...까지
        puzzle = new int[9][9];
        first = new int[9][9];
        solution = new int[9][9];

        solution = readSaveSudokuFile("solveSudoku.sgd");
        first = readSaveSudokuFile("firstSudoku.sgd");
        userInput = readSaveSudokuFile("userInput.sgd");
        puzzle = readSaveSudokuFile("autoSave.sgd");
        if (readSudokuMemoFile("memoFile.sgd") != null) memoVisible = readSudokuMemoFile("memoFile.sgd");

        int[] data = readSaveGameData("gameData.sgd");
        totalEmptyCells = data[0];
        rowEmptyCellLimit = data[1];
        secondsPassed = data[2];
        hintCount = data[3];
        // ...

        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
              final int ROW = i;
              final int COL = j;
  
              fields[i][j] = new JTextField();
              memoPanel[i][j] = new JPanel(new GridLayout(3, 3));
              memoPanel[i][j].setOpaque(false);

              for (int k = 0; k < 9; k++) {
                memoFields[i][j][k] = new JLabel();
                memoFields[i][j][k].setFont(new Font("나눔", Font.PLAIN, 12));
                memoFields[i][j][k].setForeground(Color.GRAY);
                memoFields[i][j][k].setText(" " + Integer.toString(k + 1));
                if (!memoVisible[i][j][k] || first[i][j] != 0) memoFields[i][j][k].setVisible(false);
                memoPanel[i][j].add(memoFields[i][j][k]);
              }

              JPanel overlayPanel = new JPanel();
              overlayPanel.setLayout(new OverlayLayout(overlayPanel));
              overlayPanel.add(memoPanel[i][j]);
              overlayPanel.add(fields[i][j]);

              if (first[i][j] != 0) {
                  fields[i][j].setText(String.valueOf(first[i][j])); // 초기에 표시되는 숫자 설정
                  fields[i][j].setEnabled(false);
                  fields[i][j].setBackground(new Color(220, 220, 220));
                  fields[i][j].setDisabledTextColor(Color.BLACK); // 비활성 상태일 때, 글자 색 설정
              } else if (first[i][j] == 0 && puzzle[i][j] != 0) {
                fields[i][j].setText(String.valueOf(puzzle[i][j]));
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
                          updateInputArray(ROW, COL);
                          updateCountLabels();
                      } else {
                          fields[ROW][COL].setText("");
                      }
                  }
  
                  @Override
                  public void removeUpdate(DocumentEvent e) {
                      // 문자가 삭제되었을 때
                      puzzle[ROW][COL] = 0;
                      updateInputArray(ROW, COL);
                      updateCountLabels();
                  }
  
                  @Override
                  public void changedUpdate(DocumentEvent e) {
                      // 스타일 등이 변경되었을 때, 사용하지 않음.
                  }
              });

              keyListeners[i][j] = new KeyAdapter() {
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
                        else if (memoModeEnabled) e.consume(); // 메모 상태일 때는 입력하면 안 됨
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
              };
              fields[i][j].addKeyListener(keyListeners[i][j]);
                  
              panel.add(overlayPanel);
  
              // 3x3 작은 스도쿠 경계에 진한 테두리 추가
              if ((i + 1) % 3 == 0 && (j + 1) % 3 == 0) {
                  fields[i][j].setBorder(BorderFactory.createMatteBorder(1, 1, 3, 3, Color.BLACK));
              } else if ((i + 1) % 3 == 0) {
                  fields[i][j].setBorder(BorderFactory.createMatteBorder(1, 1, 3, 1, Color.BLACK));
              } else if ((j + 1) % 3 == 0) {
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

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 100, 10)); 
        JButton hintButton = new JButton("힌트");
        hintButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (hintCount >=0 && hintCount < 10) {
                    int hint = JOptionPane.showConfirmDialog(SudokuGame.this, "힌트를 사용하시겠습니까?\n사용 횟수: " + hintCount + " / 10", "힌트", JOptionPane.YES_NO_OPTION);
                    if (hint == JOptionPane.YES_OPTION) applyHint();
                } else {
                    JOptionPane.showMessageDialog(SudokuGame.this, "힌트를 모두 사용하셨습니다!", "힌트", JOptionPane.INFORMATION_MESSAGE);
                }
            }
        });

        JButton memoButton = new JButton("메모");
        memoButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (memoButton.getText().equals("메모")) {
                    memoModeEnabled = true;
                    memoButton.setText("해제");
                }
                else {
                    memoModeEnabled = false;
                    memoButton.setText("메모");
                }
                updateMemoField(memoKeyListeners);
            }
        });

        JButton checkSudokuButton = new JButton("검사");
        checkSudokuButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (pull()) {
                    boolean success = true;
                    for (int i = 0; i < 9; i++) {
                        for (int j = 0; j < 9; j++) {
                            int num = puzzle[i][j];
                            if (num != 0 && !isValid(puzzle, i, j, num)) {
                                success = false;
                                break;
                            }
                        }
                        if (!success) break;
                    }
                    if (success) JOptionPane.showMessageDialog(SudokuGame.this, "스도쿠가 알맞게 작성되었습니다!", "성공!", JOptionPane.INFORMATION_MESSAGE);
                    else JOptionPane.showMessageDialog(SudokuGame.this, "스도쿠가 알맞게 작성되지 않았습니다!\n다시 작성해주세요!", "실패!", JOptionPane.INFORMATION_MESSAGE);
                } else {
                    JOptionPane.showMessageDialog(SudokuGame.this, "스도쿠가 모두 작성되지 않았습니다!\n모두 채우고나서 검사해주세요!", "실패!", JOptionPane.INFORMATION_MESSAGE);
                }
            }
        });

        JButton exitButton = new JButton("종료");
        exitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int exit = JOptionPane.showConfirmDialog(SudokuGame.this, "정말로 종료하시겠습니까?\n종료시 게임은 자동 저장됩니다.", "종료 확인", JOptionPane.YES_NO_OPTION);
                if (exit == JOptionPane.YES_OPTION) {
                    saveGameDataFile("gameData.sgd");
                    saveSudokuToFile("firstSudoku.sgd", first);
                    saveSudokuToFile("userInput.sgd", userInput);
                    saveSudokuToFile("autoSave.sgd", puzzle);
                    saveSudokuMemoFile("memoFile.sgd");
                    System.exit(0);
                }
            }
        });

        timeLabel = new JLabel("00:00");
        timeLabel.setHorizontalAlignment(SwingConstants.CENTER);
        timeLabel.setFont(new Font("나눔", Font.BOLD, 20));
    
        secondsPassed = 0;
        timer = new Timer(1000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                secondsPassed++;
                updateTimeLabel();
            }
        });
        timer.start();

        buttonPanel.add(timeLabel);
        buttonPanel.add(hintButton);
        buttonPanel.add(memoButton);
        buttonPanel.add(checkSudokuButton);
        buttonPanel.add(exitButton);

        // 가끔 F5 키를 눌러도 재시작이 안 되는 오류 수정
        JButton[] buttons = {hintButton, memoButton, checkSudokuButton, exitButton};

        for (JButton button : buttons) {
            button.addFocusListener(new FocusAdapter() {
                @Override
                public void focusGained(FocusEvent e) {
                    button.transferFocus(); // 포커스를 잃도록 설정
                }
            });
        }

        JPanel UI_Panel = new JPanel(new GridLayout(2, 1));
        UI_Panel.add(panelSouth);
        UI_Panel.add(buttonPanel);

        //패널 부착
        c.add(panel);
        c.add(UI_Panel, BorderLayout.SOUTH);

        if (pull()) {
            boolean success = true;
            for (int i = 0; i < 9; i++) {
                for (int j = 0; j < 9; j++) {
                    int num = puzzle[i][j];
                    if (num != 0 && isValid(puzzle, i, j, num)) {
                        success = false;
                        break;
                    }
                }
                if (!success) break;
            }
            if (success) JOptionPane.showMessageDialog(SudokuGame.this, "스도쿠가 알맞게 작성되었습니다!", "성공!", JOptionPane.INFORMATION_MESSAGE);
            else JOptionPane.showMessageDialog(SudokuGame.this, "스도쿠가 알맞게 작성되지않았습니다!\n다시 작성해주세요!", "실패!", JOptionPane.INFORMATION_MESSAGE);
        }

        c.setFocusable(true);
        c.requestFocus();

        updateCountLabels();
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private void applyHint() {
        Random random = new Random();
        ArrayList<Point> hintCells = new ArrayList<>(); // Point: GridLayout에서 셀의 인덱스에 대한 정보를 가짐

        for (int row = 0; row < 9; row++) {
            for (int col = 0; col < 9; col++) {
                if (puzzle[row][col] == 0 || userInput[row][col] != solution[row][col]) {
                    hintCells.add(new Point(row, col));
                }
            }
        }

        int hintCellCount = hintCells.size();
        int randomIndex = random.nextInt(hintCellCount);
        Point selectedCell = hintCells.get(randomIndex);
        int row = selectedCell.x;
        int col = selectedCell.y;

        puzzle[row][col] = solution[row][col];
        first[row][col] = solution[row][col]; // 불러올 때, 필드를 비활성화 하기 위함
        hintCount++;
        updateFields();
        fields[row][col].setBackground(new Color(220, 220, 220));
    }

    private void generateSudoku() {
        solution = new int[9][9];
        solveSudoku(solution);
    
        puzzle = new int[9][9];
        first = new int[9][9];
        Random random = new Random();
 
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                first[i][j] = solution[i][j];
                puzzle[i][j] = solution[i][j];
            }
        }
    
        // count : 빈 셀의 개수
        int count = 0;
        int[] rowEmpty = new int[9];
        while (count < totalEmptyCells) {
            int row = random.nextInt(9);
            int col = random.nextInt(9);
            if (puzzle[row][col] != 0 && rowEmpty[row] < rowEmptyCellLimit) {
                first[row][col] = 0;
                puzzle[row][col] = 0; // 빈 셀로 변경
                count++;
                rowEmpty[row]++;
            }
        }
        saveSudokuToFile("inputSudoku.sgd", puzzle); // 더미 저장 데이터
        saveSudokuToFile("solveSudoku.sgd", solution); // 게임을 새로 생성할 때만 생성
    }

    private boolean pull() {
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if (puzzle[i][j] == 0) return false;
            }
        }
        return true;
    }

    private boolean isNumberUsed(int number) {
        // 해당 숫자가 이미 9번 사용되었는지 확인
        int count = 0;
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if (puzzle[i][j] == number) {
                    count++;
                }
            }
        }
        return count >= 9;
    }

    private boolean isValid(int[][] board, int row, int col, int num) {
        // 행과 열 검사
        for (int i = 0; i < 9; i++) {
            if (board[row][i] == num || board[i][col] == num) {
                return false;
            }
        }
        // 작은 3x3 그룹 검사
        int startRow = row - row % 3;
        int startCol = col - col % 3;
        for (int i = startRow; i < startRow + 3; i++) {
            for (int j = startCol; j < startCol + 3; j++) {
                if (board[i][j] == num) {
                    return false;
                }
            }
        }
        return true;
    }

    private boolean solveSudoku(int[][] board) {
        Random random = new Random();
        for (int row = 0; row < 9; row++) {
            for (int col = 0; col < 9; col++) {
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

    private int[] readSaveGameData(String fileName) {
        int[] data = new int[4];

        String filePath = System.getProperty("user.home") + "/Documents/SaveSudoku/";
        File input = new File(filePath + fileName);
        try (FileInputStream inputStream = new FileInputStream(input)) {            
            int n;
            for (int i = 0; i < 4; i++) {
                while ((n = inputStream.read()) != -1 && (n == '\n' || n == '\r')) {
                    //무시함
                }
                data[i] = (byte)n;
            }
        } catch (IOException e) {
            System.err.println("Error reading file: " + e.getMessage());
            return null;
        }
        return data;
    }

    private int[][] readSaveSudokuFile(String fileName) {
        int board[][] =  new int[9][9];

        String filePath = System.getProperty("user.home") + "/Documents/SaveSudoku/";
        File input = new File(filePath + fileName);
        try (FileInputStream inputStream = new FileInputStream(input)) {            
            int n;
            for (int row = 0; row < 9; row ++) {
                for (int col = 0; col < 9; col++) {
                    while ((n = inputStream.read()) != -1 && (n == '\n' || n == '\r')) {
                        //무시함
                    }
                    if (n != -1) board[row][col] = (byte)n;
                }
            }
        } catch (IOException e) {
            System.err.println("Error reading file: " + e.getMessage());
            return null;
        }
        return board;
    }

    private boolean[][][] readSudokuMemoFile(String fileName) {
        String filePath = System.getProperty("user.home") + "/Documents/SaveSudoku/";
        File inputFile = new File(filePath + fileName);
        memoVisible = new boolean[9][9][9];

        try (FileInputStream inputStream = new FileInputStream(inputFile);
            DataInputStream dataInputStream = new DataInputStream(inputStream)) {

            for (int i = 0; i < 9; i++) {
                for (int j = 0; j < 9; j++) {
                    for (int k = 0; k < 9; k++) {
                        memoVisible[i][j][k] = dataInputStream.readBoolean();
                    }
                }
            }
        } catch (IOException e) {
            return null;
        }
        return memoVisible;
    }


    private void resetGame() {
        generateSudoku();
        updateFields();
        secondsPassed = 0;
        hintCount = 0;
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                fields[i][j].setBackground(Color.WHITE); // 배경색을 모두 흰색으로 초기화
                if (puzzle[i][j] != 0) fields[i][j].setBackground(new Color(220, 220, 220)); // 그 중 비어있지 않은 부분은 다시 회색으로 색칠
                for (int k = 0; k < 9; k++) {
                    memoFields[i][j][k].setVisible(false);
                    memoVisible[i][j][k] = false;
                }
            }
        }
        repaint();
    }

    private void saveGameDataFile(String fileName) {
        String filePath = System.getProperty("user.home") + "/Documents/SaveSudoku/";
        File directory = new File(filePath);
        if (!directory.exists()) directory.mkdirs();
        File input = new File(filePath + fileName);
        try (FileOutputStream outputStream = new FileOutputStream(input)) {
            outputStream.write(totalEmptyCells);
            outputStream.write(rowEmptyCellLimit);
            outputStream.write(secondsPassed);
            outputStream.write(hintCount);
        } catch (Exception e) {
            System.err.println("Error writing to file: " + e.getMessage());
        }
    }

    private void saveSudokuToFile(String fileName, int[][] board) {
        String filePath = System.getProperty("user.home") + "/Documents/SaveSudoku/";
        File directory = new File(filePath);
        if (!directory.exists()) directory.mkdirs();
        File input = new File(filePath + fileName);
        try (FileOutputStream outputStream = new FileOutputStream(input)) {
            for (int i = 0; i < 9; i++) {
                for (int j = 0; j < 9; j++) {
                    outputStream.write(board[i][j]);
                }
            }
        } catch (IOException e) {
            System.err.println("Error writing to file: " + e.getMessage());
        }
    }

    private void saveSudokuMemoFile(String fileName) {
        String filePath = System.getProperty("user.home") + "/Documents/SaveSudoku/";
        File directory = new File(filePath);
        if (!directory.exists()) directory.mkdirs();
        File input = new File(filePath + fileName);
        try (FileOutputStream outputStream = new FileOutputStream(input);
             DataOutputStream dataOutputStream = new DataOutputStream(outputStream)) {
            for (int i = 0; i < 9; i ++) {
                for (int j = 0; j < 9; j++) {
                    for (int k = 0; k < 9; k++) {
                        dataOutputStream.writeBoolean(memoVisible[i][j][k]);
                    }
                }
            }
        } catch (IOException e) {
            System.err.println("Error writing to file: " + e.getMessage());
        }
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

    private void updateCountLabels() {
        int[] counts = new int[9];
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
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

    private void updateFields() {
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if (puzzle[i][j] != 0) {
                    fields[i][j].setText(String.valueOf(puzzle[i][j]));
                    fields[i][j].setEnabled(false);
                    fields[i][j].setDisabledTextColor(Color.BLACK);
                } else {
                    fields[i][j].setText(""); // 빈 칸으로 초기화
                    fields[i][j].setEnabled(true);
                }
            }
        }
        updateCountLabels(); // 숫자 카운트 업데이트
    }

    private void updateMemoField(KeyListener[][] memoKeyListeners) {
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                fields[i][j].setEditable(false);
                if (first[i][j] == 0) fields[i][j].setBackground(Color.WHITE);
                final int ROW = i;
                final int COL = j;
                if (memoModeEnabled) {
                    // 메모 모드가 활성화되었을 때 키 리스너 설정
                    if (memoKeyListeners[i][j] == null) {
                        KeyAdapter memoKeyListener = new KeyAdapter() {
                            @Override
                            public void keyTyped(KeyEvent e) {
                                char c = e.getKeyChar();
                                if (Character.isDigit(c)) {
                                    int depth = Integer.valueOf(Character.toString(c)) - 1;
                                    if (depth == -1) return; // 0을 입력했을 경우
                                    JLabel memoField = memoFields[ROW][COL][depth];
                                    if ((c >= '1' && c <= '9') && fields[ROW][COL].getText().equals("")) {
                                        if (memoField.isVisible()) {
                                            memoField.setVisible(false);
                                            memoVisible[ROW][COL][depth] = false;
                                        }
                                        else {
                                            memoField.setVisible(true);
                                            memoVisible[ROW][COL][depth] = true;
                                        }
                                    }
                                }
                            }
                        };
                        memoKeyListeners[i][j] = memoKeyListener;
                    }
                fields[i][j].addKeyListener(memoKeyListeners[i][j]);

                for (int k = 0; k < 9; k++) {
                    if (memoVisible[i][j][k]) {
                        if (!fields[i][j].getText().equals("")) {
                            memoFields[i][j][k].setVisible(false);
                        } else memoFields[i][j][k].setVisible(true);
                    }
                }
                repaint();
                } else {
                    fields[i][j].removeKeyListener(memoKeyListeners[i][j]);
                    fields[i][j].setEditable(true);
                    repaint();
                }
            }
        }
    }

    private void updateInputArray(int row, int col) {
        String input = fields[row][col].getText();
        if (!input.isEmpty()) {
            userInput[row][col] = Integer.parseInt(input);
        } else {
            userInput[row][col] = 0; // 빈 칸인 경우 0으로 설정
        }
    }

    private void updateTimeLabel() {
        int minutes = secondsPassed / 60;
        int seconds = secondsPassed % 60;
        String timeString = String.format("%02d:%02d", minutes, seconds);
        timeLabel.setText(timeString);
    }

    public static void main(String[] args) {
        new SudokuGame();
    }
}