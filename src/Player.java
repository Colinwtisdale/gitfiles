package spaceshooter.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Rectangle;

public class Player extends Entity {
	
	
	
	public Player(String imageFilePath)
	{
		this.shipTexture = new Texture(imageFilePath);
		this.posX = 0;
		this.posY = 450;
		this.ship = new Sprite(this.shipTexture, 56, 37);
		this.isPlayer = true;
		this.width = 32;
		this.height = 21;
		this.boundingBox = new Rectangle(this.posX, this.posY, this.width, this.height);
	}

}