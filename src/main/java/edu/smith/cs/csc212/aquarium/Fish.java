package edu.smith.cs.csc212.aquarium;

import java.awt.Color;
import java.util.Random;
import java.awt.Graphics2D;


public class Fish {
	int xCoor;
	int yCoor;
	Color color;
	boolean facingLeft;
	boolean isLittle;
	int destX;
	int destY;
	Random rand = new Random();
	
	public Fish(int startX, int startY, Color c, boolean isLittle, boolean facingLeft) {
		this.xCoor = startX;
		this.yCoor = startY;
		this.color = c;
		this.isLittle = isLittle;
		
		this.destX = rand.nextInt(400) + 50;
		this.destY = rand.nextInt(400) + 50;
		
		this.facingLeft = this.xCoor > destX;
		
	}
	
	//changes the value of the x coordinate based on current position relative to destination
	public void swim() {
		if (this.yCoor < this.destY) {
			this.yCoor +=1;
			if (this.xCoor<this.destX) {
				this.xCoor +=1;
			} 
			
			if (this.xCoor>this.destX) {
				this.xCoor -=1;
			}
		}
		
		if (this.yCoor > this.destY) {
			this.yCoor -=1;
			if (this.xCoor<this.destX) {
				this.xCoor +=1;
			} 
			
			if (this.xCoor>this.destX) {
				this.xCoor -=1;
			}
		}
		
		if (this.yCoor == this.destY) {
			if (this.xCoor<this.destX) {
				this.xCoor +=1;
			} 
			
			if (this.xCoor>this.destX) {
				this.xCoor -=1;
			}
		}
			
	}
	
	//generates a new destination for a fish
	public void newDest() {
		this.destX = rand.nextInt(400) + 50;
		this.destY = rand.nextInt(400) + 50;
		this.facingLeft = this.xCoor > destX;
	}
	
	public void draw(Graphics2D g) {
		this.swim();
		
		//draws the fish facing the correct direction depending on current position relative to destination
		if (this.isLittle) {
			if (this.facingLeft) {
				DrawFish.smallFacingLeft(g, this.color, this.xCoor, this.yCoor);
			}else {
				DrawFish.smallFacingRight(g, this.color, this.xCoor, this.yCoor);
			}
		}else {
			if (this.facingLeft) {
				DrawFish.facingLeft(g, this.color, this.xCoor, this.yCoor);
			}else {
				DrawFish.facingRight(g, this.color, this.xCoor, this.yCoor);
			}
		}
		
	}
}
