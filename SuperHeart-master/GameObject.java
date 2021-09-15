package superHeart;

import java.awt.*;
import java.awt.geom.AffineTransform;

import javax.swing.*;

public abstract class GameObject{
	//Varibales: x, y are exact position. aimX, aimY are centers of objects. drawX, drawY are rounded x and y for drawing.
	protected double x, y;
	protected int aimX, aimY, drawX, drawY, width, height;
	protected Image sprite;
	protected String objectType;
	protected Handler handler;
	protected double rotation;
	
	//Constructor
	public GameObject(String objectType, double x, double y, int width, int height, double rotation, Image sprite, Handler handler) {
		this.objectType = objectType;
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.rotation = rotation;
		this.sprite = sprite;
		drawX = (int) Math.round(x);
		drawY = (int) Math.round(y);
		aimX = (int) Math.round(this.getBounds().getCenterX());
		aimY = (int) Math.round(this.getBounds().getCenterY());	
		this.handler = handler;
		handler.addGameObject(this);
	}
	
	//Subclasses will have update method
	public abstract void update(double gameSpeed);
	
	//Parameters for gameSpeed, xVel, and yVel
	//No return
	//Changes x and y position based on velocity and gameSpeed. Also updates drawX/drawY/aimX/aimY.
	public void move(double gameSpeed, double xVel, double yVel) {
		x += xVel * gameSpeed;
		y += yVel * gameSpeed;
		drawX = (int) Math.round(x);
		drawY = (int) Math.round(y);
		aimX = (int) Math.round(getBounds().getCenterX());
		aimY = (int) Math.round(getBounds().getCenterY());
	}
	
	
	//Parameter for graphics
	//No return
	//Draws objects with rotation by first rotating the graphics then drawing then rotating back to original rotation.
	public void render(Graphics g) {
        Graphics2D g2d = (Graphics2D)g;
        AffineTransform old = g2d.getTransform();        
        g2d.rotate(rotation, aimX, aimY);
        g2d.drawImage(sprite, drawX, drawY, width, height, null);
        g2d.setTransform(old);

	
	}
	
	//No parameters
	//Returns rectangle
	//Returns a rectangle for collision with same dimensions and position as object.
	public Rectangle getBounds() {
		return new Rectangle(drawX, drawY, width, height);
	}
}
