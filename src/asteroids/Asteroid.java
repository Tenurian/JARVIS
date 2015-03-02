package asteroids;

public class Asteroid extends Mob{
	
	private int stage = 0;
	
	public Asteroid(double x, double y, double direction, int stage) {
		this.x = x;
		this.y = y;
		this.dir_vel = direction;
		this.vel = 1;
		this.stage = stage;
		if(this.stage == 1) {
			this.imageFile = "/AsteroidStg1.png";
		} else if(this.stage == 2) {
			this.imageFile = "/AsteroidStg2.png";
		} else if(this.stage == 3) {
			this.imageFile = "/AsteroidStg3.png";
		}
	}
	
	public int getStage() {
		return stage;
	}
	@Override
	public void act() {
		x += this.calculateXComp(dir_vel);
		y += this.calculateYComp(dir_vel);
		if(this.x > Main.WIDTH*Main.SCALE) {
			if(Main.canBounce) {
				this.x = Main.WIDTH*Main.SCALE;
				dir_vel = -dir_vel;
				//this.changeVelocity(rotation, -lastVel*2);
			} else {
				x = 0;
			}
		} else if(this.x < 0) {
			if(Main.canBounce) {
				this.x = 0;
				dir_vel = -dir_vel;
				//this.changeVelocity(rotation, -lastVel*2);
			} else {
				x = Main.WIDTH*Main.SCALE;
			}
		} else if(this.y > Main.HEIGHT*Main.SCALE) {
			if(Main.canBounce) {
				this.y = Main.HEIGHT*Main.SCALE;
				vel = -vel;
				dir_vel = -dir_vel;
				//this.changeVelocity(rotation, -lastVel*2);
			} else {
				y = 0;
			}
		} else if(this.y < 0) {
			if(Main.canBounce) {
				this.y = 0;
				vel = -vel;
				dir_vel = -dir_vel;
				//this.changeVelocity(rotation, -lastVel*2);
			} else {
				y = Main.HEIGHT*Main.SCALE;
			}
		}
	}

}
