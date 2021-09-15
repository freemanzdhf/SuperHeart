package superHeart;
import java.util.ArrayList;

import javax.swing.ImageIcon;

public class Player extends GameObject{
	//Variables
	boolean[] playerDirection = new boolean[4];
	boolean[] keysPressed = new boolean[4];
	boolean moving;
	ArrowStats arrowStats;
	
	//Constructor
	public Player(double x, double y, ArrowStats arrowStats, Handler handler) {
		super("player", x, y, 75, 65, 0, new ImageIcon("CupidIdle.png").getImage(), handler);
		handler.player = this;
		this.arrowStats = arrowStats;
	}

	//Parameter for gameSpeed
	//No return
	//Updates player. Takes keys pressed and sets directions. Calls move method.
	public void update(double gameSpeed) {

		for(int i = 0; i < 4; i ++) {
			playerDirection[i] = keysPressed[i];
		}
		move(playerDirection, 10, gameSpeed);
			
	}

	//Takes parameters for direction, speed, and gameSpeed
	//No return
	//Moves player based on directions, speed, and gameSpeed. If opposite keys are pressed, cancel them out. Rotate player towards motion. 
	private void move(boolean[] dir, double speed, double gameSpeed) {
		double xVel = 0;
		double yVel = 0;
		boolean rotated = false;
		
		//Cancel opposites
		if(dir[0] == true && dir[2] == true) {
			dir[0] = false;
			dir[2] = false;
		}
		if(dir[1] == true && dir[3] == true) {
			dir[1] = false;
			dir[3] = false;
		}
		
		//Set speed and rotation
		if(dir[0] == true) {
			yVel = -speed;
			rotation = Math.toRadians(0);
			rotated = true;
		}
		if(dir[1] == true) {
			xVel = speed;
			if(rotated == true) {
				rotation = (rotation + Math.toRadians(90)) / 2.0;
			}
			else {
				rotation = Math.toRadians(90);
				rotated = true;
			}
		}
		if(dir[2] == true) {
			yVel = speed;
			if(rotated == true) {
				rotation = (rotation + Math.toRadians(180)) / 2.0;
			}
			else {
				rotation = Math.toRadians(180);
				rotated = true;
			}
		}
		if(dir[3] == true) {
			xVel = -speed;
			if(rotated == true) {
				if(rotation == 0) {
					rotation = Math.toRadians(315);
				}
				else {
					rotation = Math.toRadians(225);
				}
			}
			else {
				rotation = Math.toRadians(270);
			}
		}		
		
		//Modify speed if moving diagonally
		if(Math.abs(xVel) == Math.abs(yVel)) {
			xVel /= Math.sqrt(2);
			yVel /= Math.sqrt(2);
		}
		
		//Set moving so that gameSpeed can be modified.
		if(Math.abs(xVel) > 0 || Math.abs(yVel) > 0) {
			moving = true;
				
			//Call move method from superclass GameObject and move camera.
			//Move x and y separately to allow movement against a wall.
			move(gameSpeed, xVel, 0);
			handler.cameraMove(gameSpeed, -xVel, 0);

			//Wall collision
			for(Tile wall: handler.walls) {
				if(getBounds().intersects(wall.getBounds())) {
					move(gameSpeed, -xVel, 0);
					handler.cameraMove(gameSpeed, xVel, 0);
				}
			}
			
			move(gameSpeed, 0, yVel);
			handler.cameraMove(gameSpeed, 0, -yVel);

			//Wall collision
			for(Tile wall: handler.walls) {
				if(getBounds().intersects(wall.getBounds())) {
					move(gameSpeed, 0, -yVel);
					handler.cameraMove(gameSpeed, 0, yVel);
				}
			}
			
			//Arrow pickup collision
			for(ArrowPickup arrowPickup: (ArrayList<ArrowPickup>) handler.arrowPickups.clone()) {
				if(getBounds().intersects(arrowPickup.getBounds())) {
					handler.arrowPickups.remove(arrowPickup);
					arrowStats = arrowPickup.arrowStats;
				}
			}
		}

		else {
			moving = false;
		}
	}
}
