package spaceshooter.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;

public class Enemy extends Entity {

	
		public Enemy(String imageFileName, boolean up, boolean down, boolean left, boolean right, float spawnX, float spawnY)
		{
			this.posX = spawnX;
			this.posY = spawnY;
			this.baseSpeed = 5.5f;
			this.goLeft = left;
			this.goRight = right;
			this.goUp = up;
			this.goDown = down;
			this.shipTexture = new Texture(imageFileName);
			this.ship = new Sprite(this.shipTexture, 65, 57);
			this.isEnemy = true;
			initialPosX = this.posX;
			initialPosY = this.posY;
		}
		
	}

