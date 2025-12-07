/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.knighttournament;

/**
 *
 * @author 12251
 */
import javax.swing.*; 
import java.awt.*; 
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class KnightTournament extends JFrame { 
    private Board game; 
    private GUI panel; 
    private JComboBox<String> sizeSelector; 

    public KnightTournament() { 
        setTitle("Knight Tournament"); 
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
        setLayout(new BorderLayout()); 

        game = new Board(6); 
        panel = new GUI(game, this); 
        add(panel, BorderLayout.CENTER); 

        JPanel topPanel = new JPanel(); 
        sizeSelector = new JComboBox<>(new String[]{"4", "6", "8"}); 
        sizeSelector.setSelectedIndex(1); 
        sizeSelector.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            reset();
            }
        });
        topPanel.add(new JLabel("Board size:")); 
        topPanel.add(sizeSelector); 
        add(topPanel, BorderLayout.NORTH); 
        pack();
        setVisible(true); 
    } 
    /**
    * Restart the game with selected size.
    */
    public void reset() { 
        String selected = (String) sizeSelector.getSelectedItem(); 
        int size = Integer.parseInt(selected); 
        game.init(size);  
        panel.init(); 
    } 

    public static void main(String[] args) {
        new KnightTournament();
    }
}

