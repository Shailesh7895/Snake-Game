package com.cobra;

import javax.swing.JFrame;

public class GameFrame extends JFrame{

    public GameFrame() {
        this.add(new Gameplay());
        this.setTitle("Cobra Game");
        this.setResizable(false);
        this.pack();
        this.setVisible(true);
        this.setLocationRelativeTo(null);
    }
    
}
