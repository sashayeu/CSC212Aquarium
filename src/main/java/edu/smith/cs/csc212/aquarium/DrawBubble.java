package edu.smith.cs.csc212.aquarium;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Shape;
import java.awt.geom.Ellipse2D;

public class DrawBubble {
	//draws the bubble taking a random size value
	public static void draw(Graphics2D g, Color color, int x, int y, int size) {
		
		g.setColor(color);
		
		Shape body = new Ellipse2D.Double(x - size, y - size, size*2, size*2);
		g.fill(body);
		g.draw(body);	
	}

}
