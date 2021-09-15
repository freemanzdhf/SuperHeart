package superHeart;

import java.awt.Image;

//Structure for holding enemyStats.
public class EnemyStats {
	public ArrowStats arrowStats;
	public int speed;
	public int width;
	public int height;
	public int prefDistance;
	public double fireRate;
	public Image sprite;
	public EnemyStats(ArrowStats arrowStats, int speed, int width, int height, int prefDistance, double fireRate, Image sprite) {
		this.arrowStats = arrowStats;
		this.speed = speed;
		this.width = width;
		this.height = height;
		this.prefDistance = prefDistance;
		this.fireRate = fireRate;
		this.sprite = sprite;
	}
	
}
