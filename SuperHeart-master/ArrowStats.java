package superHeart;

import java.awt.Image;
import java.awt.image.BufferedImage;


//Structure for holding arrow stats
public class ArrowStats {

	public String arrowType;
	public int speed;
	public int lifetime;
	public int width;
	public int height;
	public int pierce;
	public Image sprite;
	
	public ArrowStats(String arrowType, int speed, int lifetime, int width, int height, int pierce, Image sprite) {
		this.arrowType = arrowType;
		this.speed = speed;
		this.lifetime = lifetime;
		this.width = width;
		this.height = height;
		this.pierce = pierce;
		this.sprite = sprite;
	}
	
}
