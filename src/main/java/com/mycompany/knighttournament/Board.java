/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.knighttournament;

/**
 *G
 * @author 12251
 */

import java.awt.*;
import java.util.Arrays;

public class Board {

    public enum Cell { GREY, WHITE, BLACK }

    private int n;
    private Cell[][] board;
    private Point[] whiteKnights;
    private Point[] blackKnights;
    private boolean whiteTurn;

    public Board(int size) {
        init(size);
    }
    /**
    * Set every Cell in the matrix to Grey, two white and two black knights are placed at the corners of the board.
    * And the whiteTurn = true so white knigts can move first.
    */
    public void init(int size) {
        this.n = size;
        board = new Cell[n][n];
        for (Cell[] row : board){
            for(int i = 0; i < n; i++){
                row[i] = Cell.GREY;
            }
        };

        whiteKnights = new Point[]{ new Point(0, 0), new Point(n - 1, n - 1)};
        blackKnights = new Point[]{ new Point(0, n - 1), new Point(n - 1, 0) };

        board[0][0] = Cell.WHITE;
        board[n - 1][n - 1] = Cell.WHITE;
        board[0][n - 1] = Cell.BLACK;
        board[n - 1][0] = Cell.BLACK;

        whiteTurn = true;
    }
    /**
    * Get the number of horizontally and vertically moved steps, if that correspond to the L shape, return true.
    */
    public boolean move(Point from, Point to) {
        int mx = Math.abs(from.x - to.x);
        int my = Math.abs(from.y - to.y);
        return (mx == 1 && my == 2) || (mx == 2 && my == 1);
    }
    /**
    * Return true if the point is occupied by a knight.
    */
    public boolean occupied(Point p) {
        for (Point pt : whiteKnights){
            if(pt.equals(p)){
                return true;
            }
        }
        
        for (Point pt : blackKnights){
            if(pt.equals(p)){
                return true;
            }
        }
        return false;
    }
    /**
    * If whiteTurn = true, we control the white knights and vice versa. If the target point is occupied or the movement isn't like L shape, return false. If it's okay, move the knight to the target point and paint the point to corresponding color, continue to next turn.
    */
    public boolean moveKnight(int index, Point to) {
        Point[] knights;
        
        if (whiteTurn){
            knights = whiteKnights;
        }
        else{
            knights = blackKnights;
        }
        
        Point from = knights[index];
    
        if (move(from, to) == false || occupied(to)){
            return false;
        }

        knights[index] = to;

        if(whiteTurn){
            board[to.x][to.y] = Cell.WHITE;
            whiteTurn = false;
        }
        else{
            board[to.x][to.y] = Cell.BLACK;
            whiteTurn = true;
        }

        return true;
    }
    /**
    * If there are 4 adjacent fields (horizontally, vertically, or diagonally) which colored to white or black, return true.
    */
    public boolean checkWin(Cell color) {
        int[][] directios = {{0, 1}, {1, 0}, {1, 1}, {1, -1}};
        for (int r = 0; r < n; r++) {
            for (int c = 0; c < n; c++) {
                if (board[r][c] != color) continue;
                for (int[] d : directios) {
                    int cnt = 1;
                    int rr = r + d[0];
                    int cc = c + d[1];
                    while (rr >= 0 && cc >= 0 && rr < n && cc < n && board[rr][cc] == color) {
                        cnt++; 
                        rr += d[0]; 
                        cc += d[1];
                        if (cnt >= 4) return true;
                    }
                }
            }
        }
        return false;
    }
    /**
    * If every cell is white or black, return true.
    */
    public boolean full() {
        for (int r = 0; r < n; r++)
            for (int c = 0; c < n; c++)
                if (board[r][c] == Cell.GREY)
                    return false;
        return true;
    }
    
    public int getSize() {
        return this.n; 
    }
    
    public Cell[][] getBoard() {
        return this.board; 
    }
    
    public Point[] getWhiteKnights(){ 
        return this.whiteKnights; 
    }
    
    public Point[] getBlackKnights(){ 
        return this.blackKnights; 
    }
    
    public boolean getWhiteTurn() { 
        return this.whiteTurn; 
    }
    
}
