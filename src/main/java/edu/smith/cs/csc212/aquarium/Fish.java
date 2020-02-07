package edu.smith.cs.csc212.aquarium;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Shape;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;

public class Fish {
	// Every fish has a xCoor which is an int.
	int xCoor;
	int yCoor;
	Color color;
	boolean facingLeft;
	boolean isLittle;
	int destX;
	int destY;
	
	public Fish(int startX, int startY, Color c, boolean isLittle, boolean facingLeft) {
		this.xCoor = startX;
		this.yCoor = startY;
		this.color = c;
		this.isLittle = isLittle;
		this.facingLeft = facingLeft;
		
		this.destX = 450;
		this.destY = 450;
		
	}
	
	public void swim() {
		if (this.yCoor < this.destY) {
			this.yCoor +=1;
		}
	}
	
	public void draw(Graphics2D g) {
		this.swim();
		//fish face left and are small
		//smallFacingRight, facingLeft, and facingRight
		
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
