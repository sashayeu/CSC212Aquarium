package edu.smith.cs.csc212.aquarium;

import java.awt.Color;
import java.util.Random;
import java.awt.Graphics2D;


public class Bubble {
	
	int xCoor;
	int yCoor;
	Color color;
	int size;
	Random rand = new Random();
	
	//assigns variables to the bubble
	public Bubble(int startX, int startY, Color color, int size) {
		this.xCoor = startX;
		this.yCoor = startY;
		this.color = color;
		this.size = rand.nextInt(7)+5;
		
	}
	
	//moves the coordinates of the bubble
	public void floatUp() {
		
		if (this.yCoor == -5) {
			//if bubble floats off screen, chooses a new random position at the bottom
			this.yCoor = 510;
			this.xCoor = rand.nextInt(10)+400;
		} else {
			//randomly decides whether it floats to the left or right
			this.yCoor -=1;
			this.xCoor += rand.nextInt(2)-1;
		}	
	}
	
	//draws bubble
	public void draw(Graphics2D g) {
		this.floatUp();
		DrawBubble.draw(g, this.color, this.xCoor, this.yCoor, this.size);
	}

}
