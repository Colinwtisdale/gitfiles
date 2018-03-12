package spaceshooter.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;

public class PowerUp extends Entity{
	
	public PowerUp(String imageFileName, float spawnX, float spawnY)
	{
		this.posX = spawnX;
		this.posY = spawnY;
		this.goLeft = true;
		this.baseSpeed = 3f;
		this.goRight = false;
		this.goUp = false;
		this.goDown = false;
		this.shipTexture = new Texture(imageFileName);
		this.ship = new Sprite(this.shipTexture, 32, 32);
		this.isPowerUp = true;
		initialPosX = this.posX;
		initialPosY = this.posY;
	}

}
