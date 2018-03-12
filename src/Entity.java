package spaceshooter.game;

import org.w3c.dom.css.Rect;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Rectangle;

public class Entity {
	public Texture shipTexture;
	public Sprite ship;
	public Rectangle boundingBox;
	public float posX;
	public float posY;
	public float width;
	public float height;
	public float speedX;
	public float speedY;
	public float timeSpawned;
	public float timeToDie;
	public float baseSpeed = 5;
	public float timeLastFired = 0;
	public float bossTimeLastFired = 0;
	public float health = 0;
	public float lives = 3;
	public boolean isDead;
	public boolean isEnemy;
	boolean goLeft;
	boolean goRight;
	boolean goUp;
	boolean goDown;
	boolean isShooting;
	boolean useTimers;
	public boolean isBullet;
	public boolean isEnemyBullet;
	public boolean isPlayer;
	public boolean isBoss;
	public boolean isObstacle;
	public boolean isPowerUp;
	public boolean isEnemy2;
	public float initialPosX = 0;
	public float initialPosY = 0;
	public int powerUpCount = 0;
	
	
	public void updatePosition(float inputX, float inputY)
	{
		
		if(goLeft)
		{
			posX = posX-baseSpeed;
		}
		if(goRight)
		{
			posX = posX+baseSpeed;
		}
		if(goDown)
		{
			posY= posY-baseSpeed;
		}
		if(goUp)
		{
			posY = posY+baseSpeed;
		}
		
		boundingBox = new Rectangle(posX, posY ,width, height);
	}
	
	public void updateTimers()
	{
		if(useTimers)
		{
			if(MyGdxGame.timer - timeSpawned > timeToDie)
			{
				isDead=true;
			}
		}
	}

}
