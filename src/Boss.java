package spaceshooter.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;

public class Boss extends Entity {
	
	public Boss(String imageFileName, boolean up, boolean down, boolean left, boolean right, float spawnX, float spawnY)
	{
		this.posX = spawnX;
		this.posY = spawnY;
		this.baseSpeed = 3.5f;
		this.goLeft = left;
		this.goRight = right;
		this.goUp = up;
		this.goDown = down;
		this.shipTexture = new Texture(imageFileName);
		this.ship = new Sprite(this.shipTexture, 210, 270);
		this.isEnemy = true;
		this.isBoss = true;
		this.health = 50;
	}
}
