package edu.smith.cs.csc212.aquarium;

import java.awt.Color;
import java.awt.Graphics2D;
import java.util.Random;

import me.jjfoley.gfx.GFX;

/**
 * Aquarium is a graphical "application" that uses some code I built and have
 * shared with you that takes care of opening a window and communicating with
 * the user in a simple way.
 * 
 * The method draw is called 50 times per second, so we make an animation by
 * drawing our fish in one place, and moving that place slightly. The next time
 * draw gets called, our fish looks like it moved!
 * 
 * @author jfoley
 *
 */
public class Aquarium extends GFX {
	/**
	 * This is a static variable that tells us how wide the aquarium is.
	 */
	public static int WIDTH = 500;
	/**
	 * This is a static variable that tells us how tall the aquarium is.
	 */
	public static int HEIGHT = 500;
	
	//this variable controls the color of the water and whether the snail is awake 
	public boolean algae = false;

	/**
	 * Put a snail on the top of the tank.
	 */
	Snail algorithm = new Snail(177, Snail.HEIGHT + 1, "top", algae);

	/**
	 * This is a constructor, code that runs when we make a new Aquarium.
	 */
	
	//empty lists of fish and bubbles are created
	Fish [] fishes = new Fish [20];
	Bubble [] bubbles = new Bubble [10];
	
	//creates a shark
	Fish shark = new Fish(250, 250, Color.DARK_GRAY,false,true);
	
	//brown color for the treasure chest
	public static final Color LIGHT_BROWN= new Color(153,102,0);
	public static final Color KELP_GREEN= new Color(54, 125, 69);

	//color from http://teaching.csse.uwa.edu.au/units/CITS1001/colorinfo.html
	
	public Aquarium() {
		// Here we ask GFX to make our window of size WIDTH and HEIGHT.
		// Don't change this here, edit the variables instead.
		super(WIDTH, HEIGHT);
		
		Random rand = new Random();
		
		//list of fish is filled with fish of random color
		//this code is taken from your powerpoint
		for (int i = 0; i<this.fishes.length; i++) {
			Color rcolor = Color.getHSBColor(
					rand.nextFloat(),0.8f,0.8f);
			boolean isSmall = true;
			boolean isRight = rand.nextBoolean();
			int x = 50+ (i*90)% (WIDTH-100);
			int y = 50 + (i*40)% (HEIGHT-100);
			this.fishes[i] = new Fish(x,y,rcolor,isSmall,isRight);
		}
		
		
		//list of bubbles is filled with bubbles of random size at random coordinates
		for (int i = 0; i<this.bubbles.length; i++) {
			int xCoor = rand.nextInt(50) + 425;
			int yCoor = 510 + rand.nextInt(200);
			this.bubbles[i] = new Bubble(xCoor, yCoor, Color.white,10);
		}
		
	}
	
	//these are the RGB beginning values of the water color
	int waterColorR = 43;
	int waterColorG = 255;
	int waterColorB = 245;
	
	
	@Override
	public void draw(Graphics2D g) {
		// Draw the "ocean" background.
		
		Color waterColor = new Color(waterColorR, waterColorG, waterColorB);
		
		g.setColor(waterColor);
		g.fillRect(0, 0, getWidth(), getHeight());
		
		//changes the B value of the water color 
		//if reaches a certain level, the water is green and switches algae value to true
		if (algae == false) {
			waterColorB -=1;
			if (waterColorB == 100) {
				algae = true;
			}
		} 
		
		//for every fish in the list, checks if it has reached the destination
		//if it reached destination, generates a new destination
		//if hasn't reached destination, draws fish moving towards current destination
		for (Fish f : this.fishes) {
			if ((f.yCoor==f.destY) && (f.xCoor==f.destX)) {
				f.newDest();
			}
			f.draw(g);
		}
		
		//draws bubbles
		for (Bubble b : this.bubbles) {
			b.draw(g);
		}

		//Draw the treasure chest
		g.setColor(LIGHT_BROWN);
		g.fillRect(300, 450, 150, 50);
		
		//drawing the kelp
		g.setColor(KELP_GREEN);
		g.fillRect(100, 400, 50, 150);
		
		//controls the movement of the snail
		//determines whether to change x or y position variable based on coordinates
		if (algae == true) {
			if (algorithm.x <450 && algorithm.y >-5 && algorithm.y <52 ) {
				algorithm.setSide("top");
				algorithm.x +=4;
			} else if (algorithm.x>=450 && algorithm.y<450) {
				algorithm.setSide("right");
				algorithm.y +=4;
			} else if (algorithm.x<501 && algorithm.x >52 && algorithm.y >= 450) {
				algorithm.x -=4;
				algorithm.setSide("bottom");
			} else if (algorithm.x < 450 && algorithm.y <501 && algorithm.y >=52) {
				algorithm.y -=4;
				algorithm.setSide("left");
			}
			
			
			//once algae is eaten, deactivates snail movement
			waterColorB +=1;
			if (waterColorB == 255) {
				algae = false;
			}
		}
		
		
		// Draw our snail!
		algorithm.draw(g, algae);
		shark.draw(g);
		
	}

	public static void main(String[] args) {
		// Uncomment this to make it go slower!
		// GFX.FPS = 10;
		// This is potentially helpful for debugging movement if there are too many print statements!

		// Note that we can store an Aquarium in a variable of type GFX because Aquarium
		// is a very specific GFX, much like 7 can be stored in a variable of type int!
		GFX app = new Aquarium();
		app.start();
	}

}
