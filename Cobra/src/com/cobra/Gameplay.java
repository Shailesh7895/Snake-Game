package com.cobra;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.Random;
import javax.swing.*;

public class Gameplay extends JPanel implements ActionListener{
    private int height = 400;
    private int width = 600;
    private int unit = 10;
    int score = 0;
    int bodyParts = 2;
    char direction = 'R';
    boolean right = true;
    boolean left = true;
    boolean up = true;
    boolean down = true;
    private final int RANDOM_POSITION = 29;
    Random random;
    private final int GameUnit = (height*width)/unit;
    int x[] = new int[GameUnit];
    int y[] = new int[GameUnit];
    private boolean running = true;
    private Timer timer;
    static final int DELAY = 60;
    int foodx;
    int foody;
    Gameplay(){
    addKeyListener(new TAdapter());
    this.setPreferredSize(new Dimension(width,height));
    this.setBackground(Color.black);
    this.setFocusable(true);
    startGame();
    }
  public void startGame(){
                apple();
		running = true;
		timer = new Timer(DELAY,this);
		timer.start();
                
	}
    public void apple(){
        int a = (int)(Math.random() * width/unit); // range of Math.random = 0-0.9
        foodx = (a * unit); 
        int b = (int)(Math.random() * height/unit);
	foody = (b * unit); 
    }
    public void move(){
    for(int i=bodyParts;i>0;i--){
    x[i]=x[i-1];
    y[i]=y[i-1];
    }
   switch(direction) {
		case 'U':
			y[0] = y[0] - unit;
			break;
		case 'D':
			y[0] = y[0] + unit;
			break;
		case 'L':
			x[0] = x[0] - unit;
			break;
		case 'R':
			x[0] = x[0] + unit;
			break;
		}
    }
      public void checkApple(){
        if((x[0] == foodx) && (y[0] == foody)){
            bodyParts++;
            score++;
            apple();
        }  
    }
      public void checkCollision(){
      for(int i=bodyParts;i>0;i--){
      if((x[0]==x[i])&&(y[0]==y[i])){
      running = false;
      }
      if(x[0]<0){
      running = false;
      }
      if(x[0]>width){
      running = false;
      }
      if(y[0]<0){
      running = false;
      }
      if(y[0]>height){
      running = false;
      }
      }
      }
    public void paintComponent(Graphics g){
    super.paintComponent(g);
        draw(g);
    }
    public void draw(Graphics g){
    if(running){
    for(int i=0;i<width*height;i++){
    g.drawLine(0, i*unit, width, i*unit);
    g.drawLine(i*unit, 0, i*unit, height);
    }
    g.setColor(Color.red);
    g.fillOval(foodx, foody, unit, unit);
    			for(int i = 0; i< bodyParts;i++) {
				if(i == 0) {
					g.setColor(Color.green);
					g.fillRect(x[i], y[i], unit, unit);
				}
				else {
					g.setColor(new Color(45,180,0));
					g.fillRect(x[i], y[i], unit, unit);
				}			
			}
    }else{
    g.setColor(Color.red);
    g.setFont( new Font("Ink Free",Font.BOLD, 75));
    g.drawString("GAME OVER !!", 40, 190);
    g.setColor(Color.white);
    g.setFont( new Font("Ink Free",Font.BOLD, 40));
    g.drawString("SCORE: " +score, 200, 260);
    }
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        if(running) {
			move();
                        checkApple();
                        checkCollision();
		}
		repaint();
    }
    public class TAdapter extends KeyAdapter{
    @Override
    public void keyPressed(KeyEvent e){
           switch(e.getKeyCode()) {
			case KeyEvent.VK_LEFT:
				if(direction != 'R') {
					direction = 'L';
				}
				break;
			case KeyEvent.VK_RIGHT:
				if(direction != 'L') {
					direction = 'R';
				}
				break;
			case KeyEvent.VK_UP:
				if(direction != 'D') {
					direction = 'U';
				}
				break;
			case KeyEvent.VK_DOWN:
				if(direction != 'U') {
					direction = 'D';
				}
				break;
			}
    }
    }
}
