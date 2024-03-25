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
        int[][] sudokuPuzzle = readSudokuFromFile("inputSudoku.txt");
        
        if (sudokuPuzzle == null) {
            System.out.println("Failed to read Sudoku from file.");
            return;
        }
        
        System.out.println("Original Sudoku:");
        printSudoku(sudokuPuzzle);
        System.out.println("\nSolving...\n");
        
        if (solveSudoku(sudokuPuzzle)) {
            System.out.println("Solved Sudoku:");
            printSudoku(sudokuPuzzle);
            saveSudokuToFile(sudokuPuzzle, "solveSudoku.txt");
        } else {
            System.out.println("No solution exists.");
        }
    }
    
    public static int[][] readSudokuFromFile(String filename) {
        int[][] puzzle = new int[9][9];
        
        String filePath = "project/sudoku/" + filename;
        try (BufferedReader reader = new BufferedReader(new BufferedReader(new FileReader(filePath)))) {
            
            String line;
            int row = 0;
            while ((line = reader.readLine()) != null) {
                if (line.length() != 9) {
                    System.err.println("Invalid Sudoku format.");
                    return null;
                }
                for (int col = 0; col < 9; col++) {
                    puzzle[row][col] = Character.getNumericValue(line.charAt(col));
                }
                row++;
            }
        } catch (IOException e) {
            System.err.println("Error reading file: " + e.getMessage());
            return null;
        }
        
        return puzzle;
    }

    public static void saveSudokuToFile(int[][] puzzle, String filename) {

        String filePath = "project/sudoku/" + filename;
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            for (int i = 0; i < 9; i++) {
                for (int j = 0; j < 9; j++) {
                    writer.write(puzzle[i][j] + '0');
                }
                writer.newLine();
            }
            System.out.println("Sudoku puzzle solved successfully and saved to " + filename);
        } catch (IOException e) {
            System.err.println("Error writing to file: " + e.getMessage());
        }
    }
}
