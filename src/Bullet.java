package spaceshooter.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;

public class Bullet extends Entity {

	
	public Bullet(String imageFileName, boolean up, boolean down, boolean left, boolean right, float spawnX, float spawnY)
	{
		this.posX = spawnX;
		this.posY = spawnY;
		this.baseSpeed = 10;
		this.goLeft = left;
		this.goRight = right;
		this.goUp = up;
		this.goDown = down;
		this.shipTexture = new Texture(imageFileName);
		this.ship = new Sprite(this.shipTexture, 32, 32);
		this.isBullet = true;
	}

}
