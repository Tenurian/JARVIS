package asteroids;

import java.util.Random;


public class Shot extends Mob{
	
	//private Mob origin;
	
	public Shot(Mob origin) {
		//this.origin = origin;
		this.vel = 8;
		this.x = origin.x;
		this.y = origin.y;
		this.rotation = origin.rotation;
		this.imageFile = "/Shot.png";
	}

	public void act() {
		x += this.calculateXComp(rotation);
		y += this.calculateYComp(rotation);
		if(checkCollision(Asteroid.class)) {
			Asteroid asteroid = (Asteroid) this.getCollided(Asteroid.class);
			Main.score++;
			if(asteroid.getStage() < 3) {
				Random rand = new Random();
				Main.addMob(new Asteroid(asteroid.getX(), asteroid.getY(), rand.nextDouble() * 360, asteroid.getStage() + 1));
				Main.addMob(new Asteroid(asteroid.getX(), asteroid.getY(), rand.nextDouble() * 360, asteroid.getStage() + 1));
				Main.removeMob(asteroid);
				Main.removeMob(this);
			} else {
				Main.removeMob(asteroid);
				Main.removeMob(this);
			}
		} if(this.x > Main.WIDTH*Main.SCALE) {
			Main.removeMob(this);
		} else if(this.x < 0) {
			Main.removeMob(this);
		} else if(this.y > Main.HEIGHT*Main.SCALE) {
			Main.removeMob(this);
		} else if(this.y < 0) {
			Main.removeMob(this);
		}
	}

}
