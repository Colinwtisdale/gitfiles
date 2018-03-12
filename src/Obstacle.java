package spaceshooter.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;

public class Obstacle extends Entity {
	
	public Obstacle(String imageFileName, boolean up, boolean down, boolean left, boolean right, float spawnX, float spawnY)
	{
		this.posX = spawnX;
		this.posY = spawnY;
		this.baseSpeed = 3.25f;
		this.goLeft = left;
		this.goRight = right;
		this.goUp = up;
		this.goDown = down;
		this.shipTexture = new Texture(imageFileName);
		this.ship = new Sprite(this.shipTexture, 140, 149);
		this.isObstacle = true;
	}

}
