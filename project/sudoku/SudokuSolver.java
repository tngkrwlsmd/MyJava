package project.sudoku;
import java.io.*;
import java.util.Arrays;

public class SudokuSolver {
    public static boolean isValid(int[][] puzzle, int row, int col, int num) {
        for (int i = 0; i < 9; i++) {
            if (puzzle[row][i] == num) {
                return false;
            }
        }
        
        for (int i = 0; i < 9; i++) {
            if (puzzle[i][col] == num) {
                return false;
            }
        }

        int startRow = 3 * (row / 3);
        int startCol = 3 * (col / 3);
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (puzzle[startRow + i][startCol + j] == num) {
                    return false;
                }
            }
        }
        
        return true;
    }

    public static boolean solveSudoku(int[][] puzzle) {
        int[] emptyCell = findEmptyCell(puzzle);
        if (emptyCell == null) {
            return true;
        }
        
        int row = emptyCell[0];
        int col = emptyCell[1];
        for (int num = 1; num <= 9; num++) {
            if (isValid(puzzle, row, col, num)) {
                puzzle[row][col] = num;
                if (solveSudoku(puzzle)) {
                    return true;
                }
                puzzle[row][col] = 0;
            }
        }
        
        return false;
    }

    public static int[] findEmptyCell(int[][] puzzle) {
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if (puzzle[i][j] == 0) {
                    return new int[] { i, j };
                }
            }
        }
        return null;
    }

    public static void printSudoku(int[][] puzzle) {
        for (int[] row : puzzle) {
            System.out.println(Arrays.toString(row));
        }
    }

    public static void main(String[] args) {
        int[][] sudokuPuzzle = readSudokuFromFile("inputSudoku.sgd");
        
        if (sudokuPuzzle == null) {
            System.out.println("Failed to read Sudoku from file.");
            return;
        }
        
        //System.out.println("Original Sudoku:");
        //printSudoku(sudokuPuzzle);
        System.out.println("\nSolving...\n");
        
        if (solveSudoku(sudokuPuzzle)) {
            //System.out.println("Solved Sudoku:");
            //printSudoku(sudokuPuzzle);
            saveSudokuToFile(sudokuPuzzle, "solveSudoku.sgd");
        } else {
            System.out.println("No solution exists.");
        }
    }
    
    public static int[][] readSudokuFromFile(String filename) {
        int[][] puzzle = new int[9][9];
        
        String filePath = System.getProperty("user.home") + "/Documents/SaveSudoku/GameData/";
        File directory = new File(filePath);
        if (!directory.exists()) directory.mkdirs();
        File input = new File(filePath + filename);
        try (FileInputStream inputStream = new FileInputStream(input)) {            
            int n;
            for (int row = 0; row < 9; row ++) {
                for (int col = 0; col < 9; col++) {
                    while ((n = inputStream.read()) != -1 && (n == '\n' || n == '\r')) {
                        //무시함
                    }
                    if (n != -1) puzzle[row][col] = (byte)n;
                }
            }
        } catch (IOException e) {
            System.err.println("Error reading file: " + e.getMessage());
            return null;
        }
        
        return puzzle;
    }

    public static void saveSudokuToFile(int[][] puzzle, String filename) {

        String filePath = System.getProperty("user.home") + "/Documents/SaveSudoku/GameData/";
        File directory = new File(filePath);
        if (!directory.exists()) directory.mkdirs();
        File input = new File(filePath + filename);
        try (FileOutputStream outputStream = new FileOutputStream(input)) {
            for (int i = 0; i < 9; i++) {
                for (int j = 0; j < 9; j++) {
                    outputStream.write(puzzle[i][j]);
                }
                outputStream.write(System.lineSeparator().getBytes());
            }
            System.out.println("Sudoku puzzle solved successfully and saved to " + filename);
        } catch (IOException e) {
            System.err.println("Error writing to file: " + e.getMessage());
        }
    }
}
