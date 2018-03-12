package spaceshooter.game;
import java.util.ArrayList;
import java.util.Random;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.Rectangle;

public class MyGdxGame extends Game implements InputProcessor {
	SpriteBatch batch;
	Texture img;
	Player player;
	Bullet bullet;
	Enemy enemy;
	public double enemyTimer = 0;
	public float spawnSpacer = 3;
	public int enemyCount = 0;
	ArrayList<Entity> entities = new ArrayList<Entity>();
	public static float timer;
	public float timeLastSpawn = 0;
	public float timeLastObjectSpawn = 0;
	public float timeBetweenFired = 1.5f;
	public float timeBetweenSpawn = 1f;
	Random r = new Random();
	public float timeBetweenObjectSpawn = 2.5f;
	public float spawnX = 1600;
	public float timeNextFired = 0;
	public float bossSecondBullets = 0;
	public float enemiesDead = 0;
	public float respawnTimer = 0;
	public static float bossTimer;
	public Texture background;
	public Texture gameOver;
	public boolean gameOverBool;
	public boolean bossDead;
	public int playerLives = 3;
	private float timeWin = 0;
	
	
	@Override
	public void create () {
		batch = new SpriteBatch();
		batch.begin();
		background = new Texture("background.png");
		batch.draw(background, 0,0,1600,900);
		batch.end();
		player = new Player("ShipSprite.png");
		Gdx.input.setInputProcessor(this);
		entities.add(player);
	}
	
	@Override
	public void render()
	{
		timer+=Gdx.graphics.getDeltaTime();
		bossTimer+=Gdx.graphics.getDeltaTime();
		Gdx.gl.glClearColor(0, 0, 0, 0);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		for(int l = 0; l < entities.size(); l++)
		{
			if(entities.get(l).isPlayer && entities.get(l).isDead)
			{
				
			}
		}
		if(timeLastSpawn+timeBetweenSpawn < timer)
		{
			timeLastSpawn = timer;
			if(enemiesDead < 20)
			{
				SpawnEnemy();
			}
			else if(enemiesDead >= 20 && enemiesDead < 30)
			{
				SpawnEnemy2();
			}
			else if(enemiesDead >= 30)
			{
				SpawnBoss();
				timeBetweenSpawn = Float.MAX_VALUE;
			}
		}
		if(timeLastObjectSpawn+timeBetweenObjectSpawn < timer)
		{
			timeLastObjectSpawn = timer;
			SpawnObstacle();
		}
		
		for(int i=0; i< entities.size(); i++)
		{
			entities.get(i).updatePosition(0,0);
			entities.get(i).updateTimers();
			if(entities.get(i).isShooting==true)
			{
				if(entities.get(i).powerUpCount==0)
				{
					Bullet bullet1 = new Bullet("shot.png", false, false, false, true, entities.get(i).posX, entities.get(i).posY);
					bullet1.useTimers = true;
					bullet1.timeSpawned = timer;
					bullet1.timeToDie = 7;
					bullet1.width = 32;
					bullet1.height = 32;
					bullet1.boundingBox = new Rectangle(bullet1.posX, bullet1.posY, bullet1.width, bullet1.height);  
					entities.add(bullet1);
					entities.get(i).isShooting = false;
				}
				else if(entities.get(i).powerUpCount==1)
				{
					Bullet bullet1 = new Bullet("shot.png", false, false, false, true, entities.get(i).posX, entities.get(i).posY+40);
					Bullet bullet2 = new Bullet("shot.png", false, false, false, true, entities.get(i).posX, entities.get(i).posY-40);
					bullet1.useTimers = true;
					bullet1.timeSpawned = timer;
					bullet1.timeToDie = 7;
					bullet1.width = 32;
					bullet1.height = 32;
					bullet1.boundingBox = new Rectangle(bullet1.posX, bullet1.posY, bullet1.width, bullet1.height);
					bullet2.useTimers = true;
					bullet2.timeSpawned = timer;
					bullet2.timeToDie = 7;
					bullet2.width = 32;
					bullet2.height = 32;
					bullet2.boundingBox = new Rectangle(bullet1.posX, bullet1.posY, bullet1.width, bullet1.height);
					entities.add(bullet1);
					entities.add(bullet2);
					entities.get(i).isShooting = false;
				}
				else if (entities.get(i).powerUpCount>=2)
				{
					Bullet bullet1 = new Bullet("shot.png", true, false, false, true, entities.get(i).posX, entities.get(i).posY);
					Bullet bullet2 = new Bullet("shot.png", false, false, false, true, entities.get(i).posX, entities.get(i).posY);
					Bullet bullet3 = new Bullet("shot.png", false, true, false, true, entities.get(i).posX, entities.get(i).posY);
					Bullet bullet4 = new Bullet ("shot.png", false, false, false, true, entities.get(i).posX, entities.get(i).posY+35);
					Bullet bullet5 = new Bullet ("shot.png", false, false, false, true, entities.get(i).posX, entities.get(i).posY-35);
					bullet1.useTimers = true;
					bullet1.timeSpawned = timer;
					bullet1.timeToDie = 7;
					bullet1.width = 32;
					bullet1.height = 32;
					bullet1.boundingBox = new Rectangle(bullet1.posX, bullet1.posY, bullet1.width, bullet1.height);
					bullet2.useTimers = true;
					bullet2.timeSpawned = timer;
					bullet2.timeToDie = 7;
					bullet2.width = 32;
					bullet2.height = 32;
					bullet2.boundingBox = new Rectangle(bullet1.posX, bullet1.posY, bullet1.width, bullet1.height);
					bullet3.useTimers = true;
					bullet3.timeSpawned = timer;
					bullet3.timeToDie = 7;
					bullet3.width = 32;
					bullet3.height = 32;
					bullet3.boundingBox = new Rectangle(bullet1.posX, bullet1.posY, bullet1.width, bullet1.height);
					bullet4.useTimers = true;
					bullet4.timeSpawned = timer;
					bullet4.timeToDie = 7;
					bullet4.width = 32;
					bullet4.height =32;
					bullet4.boundingBox = new Rectangle(bullet1.posX, bullet1.posY, bullet1.width, bullet1.height);
					bullet5.useTimers = true;
					bullet5.timeSpawned = timer;
					bullet5.timeToDie = 7;
					bullet5.width = 32;
					bullet5.height = 32;
					bullet5.boundingBox = new Rectangle(bullet1.posX, bullet1.posY, bullet1.width, bullet1.height);
					entities.add(bullet1);
					entities.add(bullet2);
					entities.add(bullet3);
					entities.add(bullet4);
					entities.add(bullet5);
					entities.get(i).isShooting = false;
				}
								
			}
			
			if(entities.get(i).isEnemy)
			{	
				if(entities.get(i).isBoss)
				{
					if(bossTimer-.75>entities.get(i).bossTimeLastFired)
					{
						entities.get(i).bossTimeLastFired = bossTimer;
						Bullet bullet1 = new Bullet("shot.png", false, false, true, false, entities.get(i).posX, entities.get(i).posY);
						Bullet bullet2 = new Bullet("shot.png", false, false, true, false, entities.get(i).posX, entities.get(i).posY+120);
						Bullet bullet3 = new Bullet("shot.png", false, false, true, false, entities.get(i).posX, entities.get(i).posY+240);
						bullet1.useTimers = true;
						bullet2.useTimers = true;
						bullet3.useTimers = true;
						bullet1.isEnemyBullet = true;
						bullet2.isEnemyBullet = true;
						bullet3.isEnemyBullet = true;
						bullet1.timeSpawned = timer;
						bullet2.timeSpawned = timer;
						bullet3.timeSpawned = timer;
						bullet1.timeToDie = 4;
						bullet2.timeToDie = 4;
						bullet3.timeToDie = 4;
						bullet1.width = 32;
						bullet1.height = 32;
						bullet2.width = 32;
						bullet2.height = 32;
						bullet3.width = 32;
						bullet3.height = 32;
						bullet1.boundingBox = new Rectangle(bullet1.posX, bullet1.posY, bullet1.width, bullet1.height);
						bullet2.boundingBox = new Rectangle(bullet2.posX, bullet2.posY, bullet2.width, bullet2.height);
						bullet3.boundingBox = new Rectangle(bullet3.posX, bullet3.posY, bullet3.width, bullet3.height);
						entities.add(bullet1);
						entities.add(bullet2);
						entities.add(bullet3);
					}
					if(entities.get(i).posY >= entities.get(i).initialPosY+620)
					{
						entities.get(i).goDown = true;
						entities.get(i).goUp = false;
						System.out.println("going up");
					}
					else if(entities.get(i).posY <= entities.get(i).initialPosY-15)
					{
						entities.get(i).goUp = true;
						entities.get(i).goDown = false;
					}
					
				}
				if(timer-timeBetweenFired > entities.get(i).timeLastFired)
				{
					if(entities.get(i).isEnemy2)
					{
						entities.get(i).timeLastFired = timer;
						Bullet bullet1 = new Bullet("shot.png", false, false, true, false, entities.get(i).posX, entities.get(i).posY);
						Bullet bullet2 = new Bullet("shot.png", false, false, true, false, entities.get(i).posX, entities.get(i).posY-50);
						Bullet bullet3 = new Bullet("shot.png", false, false, true, false, entities.get(i).posX, entities.get(i).posY+50);
						bullet1.useTimers = true;
						bullet1.isEnemyBullet = true;
						bullet1.timeSpawned = timer;
						bullet1.timeToDie = 7;
						bullet1.width = 32;
						bullet1.height = 32;
						bullet1.boundingBox = new Rectangle(bullet1.posX, bullet1.posY, bullet1.width, bullet1.height);
						bullet2.useTimers = true;
						bullet2.isEnemyBullet = true;
						bullet2.timeSpawned = timer;
						bullet2.timeToDie = 7;
						bullet2.width = 32;
						bullet2.height = 32;
						bullet2.boundingBox = new Rectangle(bullet2.posX, bullet2.posY, bullet2.width, bullet2.height);
						bullet3.useTimers = true;
						bullet3.isEnemyBullet = true;
						bullet3.timeSpawned = timer;
						bullet3.timeToDie = 7;
						bullet3.width = 32;
						bullet3.height = 32;
						bullet3.boundingBox = new Rectangle(bullet3.posX, bullet3.posY, bullet3.width, bullet3.height);
						entities.add(bullet1);
						entities.add(bullet2);
						entities.add(bullet3);
						
					}
					else if (!entities.get(i).isBoss)
					{
						entities.get(i).timeLastFired = timer;
						Bullet bullet1 = new Bullet("shot.png", false, false, true, false, entities.get(i).posX, entities.get(i).posY);
						bullet1.useTimers = true;
						bullet1.isEnemyBullet = true;
						bullet1.timeSpawned = timer;
						bullet1.timeToDie = 7;
						bullet1.width = 32;
						bullet1.height = 32;
						bullet1.boundingBox = new Rectangle(bullet1.posX, bullet1.posY, bullet1.width, bullet1.height);
						entities.add(bullet1);
					}
					else if (entities.get(i).isBoss)
					{	
							entities.get(i).timeLastFired = timer;
							Bullet bullet1 = new Bullet("shot.png", false, false, true, false, entities.get(i).posX, entities.get(i).posY);
							Bullet bullet2 = new Bullet("shot.png", false, false, true, false, entities.get(i).posX, entities.get(i).posY+120);
							Bullet bullet3 = new Bullet("shot.png", false, false, true, false, entities.get(i).posX, entities.get(i).posY+240);
							bullet1.useTimers = true;
							bullet2.useTimers = true;
							bullet3.useTimers = true;
							bullet1.isEnemyBullet = true;
							bullet2.isEnemyBullet = true;
							bullet3.isEnemyBullet = true;
							bullet1.timeSpawned = timer;
							bullet2.timeSpawned = timer;
							bullet3.timeSpawned = timer;
							bullet1.timeToDie = 4;
							bullet2.timeToDie = 4;
							bullet3.timeToDie = 4;
							bullet1.width = 32;
							bullet1.height = 32;
							bullet2.width = 32;
							bullet2.height = 32;
							bullet3.width = 32;
							bullet3.height = 32;
							bullet1.boundingBox = new Rectangle(bullet1.posX, bullet1.posY, bullet1.width, bullet1.height);
							bullet2.boundingBox = new Rectangle(bullet2.posX, bullet2.posY, bullet2.width, bullet2.height);
							bullet3.boundingBox = new Rectangle(bullet3.posX, bullet3.posY, bullet3.width, bullet3.height);
							entities.add(bullet1);
							entities.add(bullet2);
							entities.add(bullet3);
						
					}
				}
				for(int j = 0; j<entities.size(); j++)
				{
					if(entities.get(j).isPlayer)
					{
						if(entities.get(i).boundingBox.overlaps(entities.get(j).boundingBox))
						{
							entities.get(j).isDead=true;
							System.out.println("crashed");
						}
					}
				}
			}
			if(entities.get(i).isPowerUp)
			{
				for(int j = 0; j<entities.size(); j++)
				{
					if(entities.get(j).isPlayer)
					{
						if(entities.get(i).boundingBox.overlaps(entities.get(j).boundingBox))
						{
							entities.get(j).powerUpCount++;
							System.out.println("Got it!");
							entities.get(i).isDead=true;
						}
					}
				}
			}
			if(entities.get(i).isBullet || entities.get(i).isObstacle)
			{
				for(int j = 0; j<entities.size();j++)
				{
					if(entities.get(i).isBullet)
					{
						if(entities.get(j).isObstacle)
						{
							if(entities.get(j).boundingBox.overlaps(entities.get(i).boundingBox))
							{
								entities.get(i).isDead = true;
							}
						}
					}
					if((entities.get(j).isEnemy && !entities.get(i).isEnemyBullet) || (entities.get(j).isEnemy && entities.get(i).isObstacle))
					{
						if(entities.get(j).boundingBox.overlaps(entities.get(i).boundingBox))
						{
							if(entities.get(i).isBullet)
							{
								if(entities.get(j).isBoss)
								{
									entities.get(j).health--;
									entities.get(i).isDead = true;
									SpawnBossFlash(entities.get(j).posX, entities.get(j).posY, entities.get(j).goUp, entities.get(j).goDown);
									
									if(entities.get(j).health<=0)
									{
										bossDead = true;
										entities.get(j).isDead = true;
										timeWin = timer;
									}
								}
								if(!entities.get(j).isEnemy2 && !entities.get(j).isObstacle && !entities.get(j).isBoss)
								{
									entities.get(j).isDead=true;
									enemiesDead++;

								}
							}
							if(entities.get(i).isObstacle && entities.get(j).isEnemy && !entities.get(j).isBoss)
							{
								entities.get(j).isDead = true;
							}
							if(entities.get(i).isObstacle && entities.get(j).isBoss)
							{
								entities.get(i).isDead = true;
								SpawnExplosion(entities.get(i).posX, entities.get(i).posY);
							}
							if(entities.get(j).isEnemy)
							{
								if(entities.get(j).isEnemy2 || entities.get(j).isBoss)
								{
									if(entities.get(j).health>0 )
									{
										if(!entities.get(j).isBoss)
										{
											SpawnEnemyFlash(entities.get(j).posX, entities.get(j).posY);
											entities.get(j).health--;
										}
									}
									else if(entities.get(j).health<=0)
									{
										entities.get(j).isDead = true;
										enemiesDead++;
									}
								}
															}
							if(entities.get(i).isEnemy)
							{
								entities.get(i).isDead = true;
							}
							if(entities.get(i).isBullet)
							{
								entities.get(i).isDead = true;
							}
						}						
					}
					else if(entities.get(j).isPlayer)
					{
						if(entities.get(i).isEnemyBullet || entities.get(i).isObstacle)
						{
							if(entities.get(j).boundingBox.overlaps(entities.get(i).boundingBox))
							{
								entities.get(j).isDead=true;
								if(!entities.get(i).isObstacle)
								{
									entities.get(i).isDead=true;
								}
							}
						}
					}
				}
				
			}
			//
			if(entities.get(i).isDead)
			{
				if(entities.get(i).isEnemy)
				{ 
					SpawnExplosion((entities.get(i).posX),(entities.get(i).posY));
					int random = r.nextInt(100-1)+1;
					if(random<15)
					{
						SpawnPowerUp((entities.get(i).posX),(entities.get(i).posY));
					}
				}
				if(entities.get(i).isPlayer)
				{
					playerLives--;
					SpawnExplosion(entities.get(i).posX, entities.get(i).posY);
					if(playerLives>0)
					{
						player = new Player("ShipSprite.png");
						entities.add(player);
					}
					else
					{
						gameOverBool = true;
					}
				}
				entities.remove(i);	
			}
			
		}
		
		if(!gameOverBool)
		{
			batch.begin();
			batch.draw(background,0,0,1600,900);
			for(int i=0; i< entities.size(); i++)
			{
				entities.get(i).updatePosition(0,0);
				batch.draw(entities.get(i).ship,entities.get(i).posX,entities.get(i).posY);
			}
			batch.end();
		}
		else if (gameOverBool)
		{
			batch.begin();
			batch.draw(new Texture("gameOver.png"),0,0,1600,900);
			batch.end();
		}
		if(bossDead)
		{
			if(timeWin+2 < timer)
			{
				batch.begin();
				batch.draw(new Texture("victory.png"),0,0,1600,900);
				batch.end();
			}
		}
	}
	
	public void SpawnEnemy()
	{
		Enemy enemy1 = new Enemy("bugship.png", false, false, true, false, spawnX, (int)(Math.random()*900));
		enemy1.useTimers = true;
		enemy1.timeToDie = 9;
		enemy1.timeSpawned = timer;
		enemy1.width = 65f;
		enemy1.height = 57f;
		enemy1.boundingBox = new Rectangle(enemy1.posX, enemy1.posY, enemy1.width, enemy1.height);
		entities.add(enemy1);
	}
	
	public void SpawnEnemy2()
	{
		Enemy enemy1 = new Enemy("bugship2.png", false, false, true, false, spawnX, (int)(Math.random()*900));
		enemy1.useTimers = true;
		enemy1.timeToDie = 9;
		enemy1.timeSpawned = timer;
		enemy1.width = 65f;
		enemy1.height = 65f;
		enemy1.isEnemy = true;
		enemy1.isEnemy2 = true;
		enemy1.health = 1;
		enemy1.boundingBox = new Rectangle(enemy1.posX, enemy1.posY, enemy1.width, enemy1.height);
		entities.add(enemy1);
	}
	
	public void SpawnEnemyFlash(float currentX, float currentY)
	{
		Enemy enemy1 = new Enemy("bugship2flash.png", false, false, true, false, currentX, currentY);
		enemy1.useTimers = true;
		enemy1.timeToDie = .05f;
		enemy1.timeSpawned = timer;
		enemy1.width = 0;
		enemy1.height = 0;
		enemy1.isEnemy = false;
	  	enemy1.isEnemy2 = false;
		entities.add(enemy1);
	}
	
	public void SpawnBossFlash(float currentX, float currentY, boolean getUp, boolean getDown)
	{
		Boss boss1 = new Boss("bossflash.png", getUp, getDown, false, false, currentX, currentY);
		boss1.useTimers = true;
		boss1.timeToDie = .05f;
		boss1.timeSpawned = timer;
		boss1.width = 0;
		boss1.height= 0;
		boss1.isEnemy = false;
		boss1.isEnemy2 = false;
		entities.add(boss1);
	}
	
	public void SpawnObstacle()
	{
		Obstacle obstacle1 = new Obstacle("lavarock.png", false, false, true, false, spawnX, (int)(Math.random()*901));
		obstacle1.useTimers = true;
		obstacle1.timeToDie = 9;
		obstacle1.timeSpawned = timer;
		obstacle1.width = 96f;
		obstacle1.height = 96f;
		obstacle1.boundingBox = new Rectangle(obstacle1.posX, obstacle1.posY, obstacle1.width, obstacle1.height);
		entities.add(obstacle1);
	}
	
	public void SpawnPowerUp(float deadX, float deadY)
	{
		float posX = deadX;
		float posY = deadY;
		PowerUp powerUp1 = new PowerUp("powerUp.png",posX,posY);
		powerUp1.useTimers = true;
		powerUp1.timeToDie = 8;
		powerUp1.timeSpawned = timer;
		powerUp1.width = 32f;
		powerUp1.height = 32f;
		powerUp1.boundingBox = new Rectangle(powerUp1.posX, powerUp1.posY, powerUp1.width, powerUp1.height);
		entities.add(powerUp1);
		
	}
	
	public void SpawnExplosion(float deadX, float deadY)
	{
		float posX = deadX;
		float posY = deadY;
		Explosion explosion1 = new Explosion("explode.png", posX, posY);
		explosion1.useTimers = true;
		explosion1.timeToDie = .25f;
		explosion1.timeSpawned = timer;
		entities.add(explosion1);
	}
	public void SpawnBoss()
	{
		float posX = 1300;
		float posY = 750;
		Boss boss1 = new Boss("boss.png", false, false, false, false, posX, posY);
		boss1.useTimers = true;
		boss1.timeToDie = Float.MAX_VALUE;
		boss1.width = 210;
		boss1.height = 270;
		boss1.boundingBox = new Rectangle(posX, posY, boss1.width, boss1.height);
		entities.add(boss1);
	}

	@Override
	public boolean keyDown(int keycode) {
		switch(keycode)
		{
		case Input.Keys.LEFT:
			player.goLeft = true;
			//player.updatePosition(-1, 0); 
			break;
		case Input.Keys.RIGHT:
			player.goRight = true;
			//player.updatePosition(1, 0); 
			break;
		case Input.Keys.UP:
			player.goUp = true;
			//player.updatePosition(0, 1); 
			break;
		case Input.Keys.DOWN:
			player.goDown = true;
			//player.updatePosition(0, -1); 
			break;
		case Input.Keys.SPACE:
			player.isShooting = true;
			break;
		}
		return false;
	}

	@Override
	public boolean keyUp(int keycode) {
		switch(keycode)
		{
		case Input.Keys.LEFT:
			player.goLeft = false;
			//player.updatePosition(-1, 0); 
			break;
		case Input.Keys.RIGHT:
			player.goRight = false;
			//player.updatePosition(1, 0); 
			break;
		case Input.Keys.UP:
			player.goUp = false;
			//player.updatePosition(0, 1); 
			break;
		case Input.Keys.DOWN:
			player.goDown = false;
			//player.updatePosition(0, -1); 
			break;
		case Input.Keys.SPACE:
			player.isShooting = false;
			break;
		}
		return false;
	}

	@Override
	public boolean keyTyped(char character) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean touchDown(int screenX, int screenY, int pointer, int button) {
		// TODO Auto-generated method stub
		return false;
	}
	

	@Override
	public boolean touchUp(int screenX, int screenY, int pointer, int button) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean touchDragged(int screenX, int screenY, int pointer) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean mouseMoved(int screenX, int screenY) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean scrolled(int amount) {
		// TODO Auto-generated method stub
		return false;
	}
	
}
