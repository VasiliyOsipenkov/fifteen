package ru.avalon.javapp.devj110.fifteen;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.Arrays;
import java.util.Random;


public class Game extends JFrame {
    private final String[][] win = {{"1", "2", "3", "4"}, {"5", "6", "7", "8"},
            {"9", "10", "11", "12"}, {"13", "14", "15", ""}};
    private final String[][] win2 = {{"1", "2", "3", "4"}, {"5", "6", "7", "8"},
            {"9", "10", "11", "12"}, {"13", "15", "14", ""}};
    private String[][] gameBoard;

    JPanel brd;

    public Game() {
        super("Fifteen");
        mixed();

        setBounds(1000, 500, 400, 400);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        JPanel controlPanel = new JPanel(new FlowLayout());
        JButton newGame = new JButton("Новая игра");
        newGame.setFont(newGame.getFont().deriveFont(20.f));
        newGame.addActionListener(e -> newGame());
        controlPanel.add(newGame);

        add(controlPanel, BorderLayout.NORTH);

        makeBoard();
    }

    private void moveBone(int x, int y) {
        int line = checkLine(x);
        int column = checkColumn(y);
        if (column != -1) {
            moveToColumn(x,column,y);
        }
        if (line != -1) {
            moveToLine(x,line,y);
        }
        remove(brd);
        makeBoard();
        brd.updateUI();
        checkWin();
    }

    private Boolean compareBoard(String[][] reference, String[][] board) {
        if (reference.length != board.length)
            return false;
        Boolean flag = true;
        for (int i = 0; i < reference.length; i++) {
            for (int j = 0; j < reference.length; j++) {
                if (!reference[i][j].equals(board[i][j]))
                    flag = false;
            }
        }
        return flag;
    }

    private void winMessage() {
        int result = JOptionPane.showConfirmDialog(this,"Победа!!!\nНачать новую игру?",
                "Победа",
                JOptionPane.YES_NO_OPTION);
        if (result == JOptionPane.YES_OPTION) {
            remove(brd);
            mixed();
            makeBoard();
            brd.updateUI();
        }
    }

    private void checkWin() {
        if (compareBoard(win, gameBoard) || compareBoard(win2, gameBoard))
            winMessage();
    }

    private void moveToLine(int x, int line, int y) {
        if (y < line) {
            for (int i = line; i > y; --i)
                gameBoard[x][i] = gameBoard[x][i-1];
            gameBoard[x][y] = "";
        } else if (line != y) {
            for (int i = line; i < y; ++i)
                gameBoard[x][i] = gameBoard[x][i+1];
            gameBoard[x][y] = "";
        }
    }

    private void moveToColumn(int x, int column, int y) {
        if (x < column) {
            for (int i = column; i > x; --i)
                gameBoard[i][y] = gameBoard[i-1][y];
            gameBoard[x][y] = "";
        } else if (column != x) {
            for (int i = column; i < x; ++i)
                gameBoard[i][y] = gameBoard[i+1][y];
            gameBoard[x][y] = "";
        }
    }

    private int checkLine(int x) {
        for (int i = 0; i < 4; i++) {
            if (gameBoard[x][i].equals(""))
                return i;
        }
        return -1;
    }

    private int checkColumn(int y) {
        for (int i = 0; i < 4; i++) {
            if (gameBoard[i][y].equals(""))
                return i;
        }
        return -1;
    }

    private void makeBoard() {
        brd = new JPanel(new GridLayout(4, 4));
        brd.add(createBone(gameBoard[0][0], e -> moveBone(0,0)));
        brd.add(createBone(gameBoard[0][1], e -> moveBone(0,1)));
        brd.add(createBone(gameBoard[0][2], e -> moveBone(0,2)));
        brd.add(createBone(gameBoard[0][3], e -> moveBone(0,3)));

        brd.add(createBone(gameBoard[1][0], e -> moveBone(1,0)));
        brd.add(createBone(gameBoard[1][1], e -> moveBone(1,1)));
        brd.add(createBone(gameBoard[1][2], e -> moveBone(1,2)));
        brd.add(createBone(gameBoard[1][3], e -> moveBone(1,3)));

        brd.add(createBone(gameBoard[2][0], e -> moveBone(2,0)));
        brd.add(createBone(gameBoard[2][1], e -> moveBone(2,1)));
        brd.add(createBone(gameBoard[2][2], e -> moveBone(2,2)));
        brd.add(createBone(gameBoard[2][3], e -> moveBone(2,3)));

        brd.add(createBone(gameBoard[3][0], e -> moveBone(3,0)));
        brd.add(createBone(gameBoard[3][1], e -> moveBone(3,1)));
        brd.add(createBone(gameBoard[3][2], e -> moveBone(3,2)));
        brd.add(createBone(gameBoard[3][3], e -> moveBone(3,3)));

        add(brd, BorderLayout.CENTER);
    }

    private JButton createBone(String text, ActionListener l) {
        JButton btn = new JButton(text);
        btn.setFont(btn.getFont().deriveFont(36.f));
        btn.addActionListener(l);
        return btn;
    }

    private void mixed() {
        gameBoard = new String[][]{{"1", "2", "3", "4"}, {"5", "6", "7", "8"},
                {"9", "10", "11", "12"}, {"13", "14", "15", ""}};
        Random rnd = new Random();
        for(int i = 0; i < gameBoard.length; i++) {
            for (int j = 0; j < gameBoard.length; j++) {
                int index1 = rnd.nextInt(i + 1);
                int index2 = rnd.nextInt(j + 1);
                String a = gameBoard[index1][index2];
                gameBoard[index1][index2] = gameBoard[i][j];
                gameBoard[i][j] = a;
            }
        }
    }

    private void newGame() {
        int result = JOptionPane.showConfirmDialog(this,"Начать новую игру?",
                "Новая игра",
                JOptionPane.YES_NO_OPTION);
        if (result == JOptionPane.YES_OPTION) {
            remove(brd);
            mixed();
            makeBoard();
            brd.updateUI();
        }
    }


    public static void main(String[] args) {
        new Game().setVisible(true);
    }
}