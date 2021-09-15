package superHeart;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.util.ArrayList;
import java.util.LinkedList;

import javax.swing.*;

import javax.swing.JComponent;


public class Arrow extends GameObject{
	//Variables
	protected ArrowStats stats;
	protected int pierce;
	private double xVel, yVel;
	private double currentTime;
	private double pastTime;
	private double deltaTime;
	private String arrowType;
	private double angle;
	private double timer;


	//Constructor. Sets stats of arrow
	public Arrow(String arrowType, ArrowStats stats, double x, double y, double angle, Handler handler) {
		super("Arrow", x - stats.width / 2, y - stats.height / 2, stats.width, stats.height, angle, stats.sprite, handler);
		xVel = Math.cos(angle) * stats.speed;
		yVel = Math.sin(angle) * stats.speed;
		this.arrowType = arrowType;
		this.stats = stats;
		this.pierce = stats.pierce;
		this.angle = angle;
		currentTime = System.currentTimeMillis();

	}

	
	//Parameter for gameSpeed
	//No return
	//Updates arrow. Adds to timer and removes if past lifetime. Moves arrow. Checks collision with enemy/player/wall and reacts appropriately. 
	public void update(double gameSpeed) {
		//Destroy if traveled too far
		pastTime = currentTime;
		currentTime = System.currentTimeMillis();
		deltaTime = (currentTime - pastTime) * gameSpeed;
		timer += deltaTime;
		if(timer > stats.lifetime) {
			handler.removeGameObject(this);
		}

		//Move
		move(gameSpeed, xVel, yVel);
		//Collision with enemies
		ArrayList<GameObject> gameObjects = (ArrayList<GameObject>) handler.gameObjects.clone();
		if(arrowType.equals("player")) {
			for(GameObject enemy: gameObjects) {
				if(enemy.objectType.equals("enemy")) {
					if(getBounds().intersects(enemy.getBounds()) && pierce >= 0) {
						pierce --;
						handler.enemies --;
						handler.removeGameObject(enemy);
						if(stats.arrowType.equals("ricochet")){
							double closest = 999999999;
							for(GameObject nextEnemy: gameObjects) {
								if(nextEnemy.objectType.equals("enemy") && nextEnemy != enemy) {
									double distance = Math.pow(aimX - nextEnemy.aimX, 2) + Math.pow(aimY - nextEnemy.aimY, 2);
									if (distance <= closest) {
										closest = distance;
										angle = Math.atan2(nextEnemy.aimY - aimY, nextEnemy.aimX - aimX);
									}
								}
							}
							rotation = angle;
							xVel = Math.cos(angle) * stats.speed;
							yVel = Math.sin(angle) * stats.speed;
						}
					}
				}
			}
			
		//Collision with player
		}else {
			if(getBounds().intersects(handler.player.getBounds())) {
				handler.removeGameObject(this);
				handler.reset = true;
			}
		}

		//Collision with walls
		for(Tile wall: handler.walls) {
			if(getBounds().intersects(wall.getBounds())) {
				pierce = -1;
			}
		}
		
		//Remove if out of pierce
		if(pierce < 0) {
			handler.removeGameObject(this);
		}
	}
}



