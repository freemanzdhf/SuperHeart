package superHeart;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;

//Handler controls everything

public class Handler {
	int enemies;
	ArrayList<GameObject> gameObjects = new ArrayList<GameObject> ();
	ArrayList<Tile> tiles = new ArrayList<Tile> ();
	ArrayList<Tile> walls = new ArrayList<Tile> ();
	ArrayList<ArrowPickup> arrowPickups = new ArrayList<ArrowPickup> ();
	Player player;
	boolean reset = false;
	
	//Parameter for gameSpeed
	//No return
	//Calls update method on every GameObject. Clones ArrayList so that the ArrayList is not modified while being accessed.
	public void update(double gameSpeed) {
		ArrayList<GameObject> gameObjects = (ArrayList<GameObject>) this.gameObjects.clone();
		for(GameObject tempObject: gameObjects) {
			tempObject.update(gameSpeed);
		}
	}
	
	//Adds GameObjects to arraylist
	public void addGameObject(GameObject gameObject) {
		gameObjects.add(gameObject);
	}
	
	//Removes GameObjects from arraylist.
	public void removeGameObject(GameObject gameObject) {
		gameObjects.remove(gameObject);

	}
	
	//Parameter for gameSpeed
	//No return
	//Calls render method on every GameObject, tile, and arrow pickup. Clones ArrayLists so that the ArrayLists are not modified while being accessed.
	public void render(Graphics g) {
		ArrayList<Tile> tiles = (ArrayList<Tile>) this.tiles.clone();
		for(Tile tile: tiles) {
			tile.render(g);
		}
		ArrayList<ArrowPickup> arrowPickups = (ArrayList<ArrowPickup>) this.arrowPickups.clone();
		for(ArrowPickup arrowPickup: arrowPickups) {
			arrowPickup.render(g);
		}
		ArrayList<GameObject> gameObjects = (ArrayList<GameObject>) this.gameObjects.clone();
		for(GameObject tempObject: gameObjects) {
			tempObject.render(g);
		}
		
	}
	
	
	//Parameters for gameSpeed, xVel, yVel
	//No return
	//Moves everything to simulate camera movement. Is called with opposite values to player movement. 
	public void cameraMove(double gameSpeed, double xVel, double yVel) {
		ArrayList<Tile> tiles = (ArrayList<Tile>) this.tiles.clone();
		for(Tile tile: tiles) {
			tile.move(gameSpeed, xVel, yVel);
			
		}	
		
		ArrayList<ArrowPickup> arrowPickups = (ArrayList<ArrowPickup>) this.arrowPickups.clone();
		for(ArrowPickup arrowPickup: arrowPickups) {
			arrowPickup.move(gameSpeed, xVel, yVel);
		}
		
		ArrayList<GameObject> gameObjects = (ArrayList<GameObject>) this.gameObjects.clone();
		for(GameObject tempObject: gameObjects) {
			tempObject.move(gameSpeed, xVel, yVel);
		}	
		
	}
	

}
