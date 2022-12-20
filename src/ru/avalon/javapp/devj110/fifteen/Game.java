package ru.avalon.javapp.devj110.fifteen;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.Random;


public class Game extends JFrame {
    private final String[][] win = {{"1", "2", "3", "4"}, {"5", "6", "7", "8"},
            {"9", "10", "11", "12"}, {"13", "14", "15", ""}};
    private String[][] gameBoard = win;

    JPanel brd;

    public Game() {
        super("fifteen");
        newGame();

        setBounds(1000, 500, 400, 400);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        JPanel controlPanel = new JPanel(new FlowLayout());
        JButton newGame = new JButton("Новая игра");
        newGame.setFont(newGame.getFont().deriveFont(20.f));
        newGame.addActionListener(e -> refresh());
        controlPanel.add(newGame);

        add(controlPanel, BorderLayout.NORTH);

        brd = new JPanel(new GridLayout(4, 4));
        brd.add(createBone(gameBoard[0][0]));
        brd.add(createBone(gameBoard[0][1]));
        brd.add(createBone(gameBoard[0][2]));
        brd.add(createBone(gameBoard[0][3]));

        brd.add(createBone(gameBoard[1][0]));
        brd.add(createBone(gameBoard[1][1]));
        brd.add(createBone(gameBoard[1][2]));
        brd.add(createBone(gameBoard[1][3]));

        brd.add(createBone(gameBoard[2][0]));
        brd.add(createBone(gameBoard[2][1]));
        brd.add(createBone(gameBoard[2][2]));
        brd.add(createBone(gameBoard[2][3]));

        brd.add(createBone(gameBoard[3][0]));
        brd.add(createBone(gameBoard[3][1]));
        brd.add(createBone(gameBoard[3][2]));
        brd.add(createBone(gameBoard[3][3]));

        add(brd, BorderLayout.CENTER);
    }

    private JButton createBone(String text) {
        JButton btn = new JButton(text);
        btn.setFont(btn.getFont().deriveFont(36.f));
        return btn;
    }

    private void newGame() {
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

    private void refresh() {
        newGame();
        brd = new JPanel(new GridLayout(4, 4));
        brd.add(createBone(gameBoard[0][0]));
        brd.add(createBone(gameBoard[0][1]));
        brd.add(createBone(gameBoard[0][2]));
        brd.add(createBone(gameBoard[0][3]));

        brd.add(createBone(gameBoard[1][0]));
        brd.add(createBone(gameBoard[1][1]));
        brd.add(createBone(gameBoard[1][2]));
        brd.add(createBone(gameBoard[1][3]));

        brd.add(createBone(gameBoard[2][0]));
        brd.add(createBone(gameBoard[2][1]));
        brd.add(createBone(gameBoard[2][2]));
        brd.add(createBone(gameBoard[2][3]));

        brd.add(createBone(gameBoard[3][0]));
        brd.add(createBone(gameBoard[3][1]));
        brd.add(createBone(gameBoard[3][2]));
        brd.add(createBone(gameBoard[3][3]));

        add(brd, BorderLayout.CENTER);
        brd.updateUI();
    }


    public static void main(String[] args) {
        new Game().setVisible(true);
    }
}