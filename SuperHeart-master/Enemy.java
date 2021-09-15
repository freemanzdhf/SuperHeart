package superHeart;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.geom.AffineTransform;
	
public class Enemy extends GameObject{
	//Variables
	protected EnemyStats stats;
	private double xVel, yVel;
	private double currentTime;
	private double pastTime;
	private double deltaTime;
	private double timer;
	
	//Constructor. Sets stats of enemy
	public Enemy(EnemyStats stats, double x, double y, double rotation, Handler handler) {
		super("enemy", x, y, stats.width, stats.height, rotation, stats.sprite, handler);
		this.stats = stats;
		currentTime = System.currentTimeMillis();
		handler.enemies ++;
		timer = Math.random() * stats.fireRate;
		
	}

	
	//Parameter for gameSpeed
	//no return
	//Updates enemy. sets rotation, shoots arrows, and moves enemy. Maintains a set distance from player. Collides with walls. Move x and y separately to allow movement while backed up against a wall.
	public void update(double gameSpeed) {
		//Rotation
		double angle = Math.atan2(handler.player.aimY - aimY , handler.player.aimX - aimX);
		rotation = angle + Math.toRadians(90);
		
		//Shooting
		pastTime = currentTime;
		currentTime = System.currentTimeMillis();
		deltaTime = (currentTime - pastTime) * gameSpeed;
		timer += deltaTime;
		if(timer > stats.fireRate) {
			if(stats.arrowStats.arrowType.equals("shotgun")) {
				for(int i = -15; i <= 15; i += 10) {
					Arrow arrow = new Arrow("enemy", stats.arrowStats, aimX, aimY,  angle + Math.toRadians(i), handler);
				}
			}else {
				Arrow arrow = new Arrow("enemy", stats.arrowStats, aimX, aimY,  angle, handler);
			}
			timer = 0;
		}

		//Maintain distance
		if(Math.pow(aimX - handler.player.aimX, 2) + Math.pow(aimY - handler.player.aimY, 2) <= Math.pow((stats.prefDistance), 2)) {
			xVel = - Math.cos(angle) * stats.speed;
			yVel = - Math.sin(angle) * stats.speed;
			rotation -= Math.toRadians(180);
			if(Math.pow(aimX - handler.player.aimX, 2) + Math.pow(aimY - handler.player.aimY, 2) - Math.pow(stats.prefDistance, 2) < 10) {
				rotation -= Math.toRadians(180);
			}
		}else {
			xVel = Math.cos(angle) * stats.speed;
			yVel = Math.sin(angle) * stats.speed;
		}
		
		//Move x
		move(gameSpeed, xVel, 0);

		//Collide with walls
		for(Tile wall: handler.walls) {
			if(getBounds().intersects(wall.getBounds())) {
				move(gameSpeed, -xVel, 0);
			}
		}

		//Move y
		move(gameSpeed, 0, yVel);

		for(Tile wall: handler.walls) {
				if(getBounds().intersects(wall.getBounds())) {
					move(gameSpeed, 0, -yVel);
			}
		}
		

	}
	

}
