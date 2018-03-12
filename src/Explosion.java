package spaceshooter.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;

public class Explosion extends Entity {
	
	public Explosion(String imageFileName, float spawnX, float spawnY)
	{
		this.posX = spawnX;
		this.posY = spawnY;
		this.goLeft = false;
		this.goRight = false;
		this.goUp = false;
		this.goDown = false;
		this.shipTexture = new Texture(imageFileName);
		this.ship = new Sprite(this.shipTexture, 70, 68);
		this.isPowerUp = true;
		initialPosX = this.posX;
		initialPosY = this.posY;
	}

}
