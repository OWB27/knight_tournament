/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.knighttournament;

/**
 *
 * @author 12251
 */

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class GUI extends JPanel {
    private Board game;
    private KnightTournament kn;
    private JButton[][] cells;
    private Point selectedKnight = null;
    private int selectedIndex = -1;

    public GUI(Board game, KnightTournament kn) {
        this.game = game;
        this.kn = kn;
        init();
    }
    /**
    * Make a Jbutton matrix. And for each place, assign a button, add a handler for buttons which controls what is going to happend after click. add() every button so they can be visible.
    */
    public void init() {
        this.removeAll();
        int n = game.getSize();
        setLayout(new GridLayout(n, n));
        cells = new JButton[n][n];

        for (int r = 0; r < n; r++) {
            for (int c = 0; c < n; c++) {
                JButton btn = new JButton();
                final int row = r;
                final int col = c;
                btn.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                    Click(row, col);
                    }
                });
                cells[r][c] = btn;
                this.add(btn);
            }
        }
        update();
    }
    /**
    * If we did not select a knight, based on given point we select the knight on that point. If we already selected a knight, so we suppose to move it for the next click, If the movement is like L shape then it'll be executed, otherwise we don't do anything. And after every successful movement we check if a color wins or not or it's a draw.
    */
    public void Click(int r, int c) {
        Point clicked = new Point(r, c);

        if (selectedKnight == null) {
            
            Point[] knights;
            if (game.getWhiteTurn()){
                knights = game.getWhiteKnights();
            }
            else{
                knights = game.getBlackKnights();
            }
            
            for (int i = 0; i < knights.length; i++) {
                if (knights[i].equals(clicked)) {
                    selectedKnight = knights[i];
                    selectedIndex = i;
                    update();
                    cells[r][c].setBackground(Color.GREEN);
                    return;
                }
            }
        } 
        else {
            boolean moved = game.moveKnight(selectedIndex, clicked);
            selectedKnight = null;
            selectedIndex = -1;
            update();

            if (moved) {
                if (game.checkWin(Board.Cell.WHITE)) {
                    JOptionPane.showMessageDialog(this, "White wins.");
                    kn.reset();
                } 
                else if (game.checkWin(Board.Cell.BLACK)) {
                    JOptionPane.showMessageDialog(this, "Black wins.");
                    kn.reset();
                } 
                else if (game.full()) {
                    JOptionPane.showMessageDialog(this, "Draw, since the Board is already full.");
                    kn.reset();
                }
            }
        }
    }
    /**
    * For each button that is occupied we set it to black or white, if it's not occupied then grey. And we use letters to represent knights.
    */
    public void update() {
        int n = game.getSize();
        Board.Cell[][] board = game.getBoard();

        for (int r = 0; r < n; r++) {
            for (int c = 0; c < n; c++) {
                JButton btn = cells[r][c];
                switch (board[r][c]) {
                    case GREY -> btn.setBackground(Color.GRAY);
                    case WHITE -> btn.setBackground(Color.WHITE);
                    case BLACK -> btn.setBackground(Color.BLACK);
                }
                btn.setText("");
            }
        }

        for (Point p : game.getWhiteKnights()) {
            cells[p.x][p.y].setText("W");
        }
        for (Point p : game.getBlackKnights()) {
            cells[p.x][p.y].setText("B");
            cells[p.x][p.y].setForeground(Color.RED);
        }
    }
}

