package asteroids;

import java.awt.AlphaComposite;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Player extends Mob {
	public boolean up = false, rt = false, dn = false, lt = false, spc = false, can_shoot = true;
	private int shoot_cd = 0;
	private boolean isDead = false;
	private static final double ROTATION_SPEED = 2.0, TERMINAL_VEL = 2.0;
	//private static final double NEGLIGABLE_VEL = 0.05;
	
	public Player() {
		imageFile = "/Spaceship.png";
	}
	
	public void draw(Graphics g) {
		try {
			BufferedImage i = ImageIO.read(getClass().getResource(this.imageFile));
			AffineTransform at = new AffineTransform();
			at.translate(this.x, this.y);
			at.rotate(this.getRotation()*(Math.PI/180));
			at.translate(-i.getWidth()/2, -i.getHeight()/2);
			width = i.getWidth();
			height = i.getHeight();
			Graphics2D g2d = (Graphics2D) g;
			if(isDead) {
				g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0f));
			} g2d.drawImage(i, at, null);
		} catch(IOException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public void act() {
		if(!can_shoot) {
			if(shoot_cd > 0) {
				shoot_cd--;
			} else {
				can_shoot = true;
			}
		}
		if(spc) {
			if(can_shoot && !isDead) {
				Main.addMob(new Shot(this));
				can_shoot = false;
				shoot_cd = 30;
			}
		} if(rt) {
			if(rotation < 360) {
				rotation += ROTATION_SPEED;
			} else {
				rotation = 0;
			}
		} if(lt) {
			if(rotation > 0) {
				rotation -= ROTATION_SPEED;
			} else {
				rotation = 360;
			}
		} if(up) {
			this.changeVelocity(rotation, 0.1);
			dir_vel = rotation;
		} if(dn) {
			this.changeVelocity(rotation, -0.1);
			dir_vel = -rotation;		
		} if(vel != 0) {
			if(x_vel > TERMINAL_VEL) {
				x_vel = TERMINAL_VEL;
			} if(y_vel > TERMINAL_VEL) {
				y_vel = TERMINAL_VEL;
			} if(x_vel < -TERMINAL_VEL) {
				x_vel = -TERMINAL_VEL;
			} if(y_vel < -TERMINAL_VEL) {
				y_vel = -TERMINAL_VEL;
			} /*if(vel < NEGLIGABLE_VEL && vel > -NEGLIGABLE_VEL) {
				vel = 0;
				x_vel = 0;
				y_vel = 0;
			} else if(vel > 0) {
				this.changeVelocity(dir_vel, -0.0005);
			} else if(vel < 0) {
				this.changeVelocity(dir_vel, 0.0005);
			}*/ x += x_vel;
			y += y_vel;
			if(this.checkCollision(Asteroid.class)) {
				if(!isDead) {
					Main.highscores.addScore(new Highscore(Main.score));
					Main.highscores.saveHighscores();
					isDead = true;
					Main.addMob(new ShipShard(this.x, this.y, 0));
					Main.addMob(new ShipShard(this.x, this.y, 45));
					Main.addMob(new ShipShard(this.x, this.y, 90));
					Main.addMob(new ShipShard(this.x, this.y, 135));
					Main.addMob(new ShipShard(this.x, this.y, 180));
					Main.addMob(new ShipShard(this.x, this.y, 225));
					Main.addMob(new ShipShard(this.x, this.y, 270));
					Main.addMob(new ShipShard(this.x, this.y, 315));
				}
			} if(this.x > Main.WIDTH*Main.SCALE) {
				if(Main.canBounce) {
					this.x = Main.WIDTH*Main.SCALE;
					x_vel = -(x_vel/2);
					//this.changeVelocity(rotation, -lastVel*2);
				} else {
					x = 0;
				}
			} else if(this.x < 0) {
				if(Main.canBounce) {
					this.x = 0;
					x_vel = -(x_vel/2);
					//this.changeVelocity(rotation, -lastVel*2);
				} else {
					x = Main.WIDTH*Main.SCALE;
				}
			} else if(this.y > Main.HEIGHT*Main.SCALE) {
				if(Main.canBounce) {
					this.y = Main.HEIGHT*Main.SCALE;
					y_vel = -(y_vel/2);
					//this.changeVelocity(rotation, -lastVel*2);
				} else {
					y = 0;
				}
			} else if(this.y < 0) {
				if(Main.canBounce) {
					this.y = 0;
					y_vel = -(y_vel/2);
//					System.out.println("test");
					//this.changeVelocity(rotation, -lastVel*2);
				} else {
					y = Main.HEIGHT*Main.SCALE;
				}
			}
		}
	}
	
}
