package superHeart;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;

public class ArrowPickup {
	//Variables
	double x, y;
	int drawX, drawY;
	Image sprite;
	Handler handler;
	ArrowStats arrowStats;

	//Constructor
	public ArrowPickup(ArrowStats arrowStats, double x, double y, Handler handler) {
		this.arrowStats = arrowStats;
		this.x = x;
		this.y = y;
		drawX = (int) Math.round(x);
		drawY = (int) Math.round(y);
		this.sprite = arrowStats.sprite;
		handler.arrowPickups.add(this);

	}

	//Parameter for graphics
	//No return
	//Draws image to graphics.
	public void render(Graphics g) {
		g.drawImage(sprite, drawX, drawY, 50, 25, null);
			
	}
	
	
	//No parameters
	//Returns Rectangle
	//Returns rectangle with same dimensions and position as image for collision.
	public Rectangle getBounds() {
		drawX = (int) Math.round(x);
		drawY = (int) Math.round(y);
		return new Rectangle(drawX, drawY, 50, 25);
	}
	
	
	//Parameters for gameSpeed, xVel, yVel
		//No return
		//Moves tiles based on gameSpeed, xVel, and yVel. Called by cameraMove
	public void move(double gameSpeed, double xVel, double yVel) {
		x += xVel * gameSpeed;
		y += yVel * gameSpeed;
		drawX = (int) Math.round(x);
		drawY = (int) Math.round(y);
	}

}
