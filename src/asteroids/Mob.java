package asteroids;

import java.awt.AlphaComposite;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;

public abstract class Mob {
	protected double rotation = 0, dir_vel = 0, vel = 0;
	protected double x = 0, y = 0, x_vel = 0, y_vel = 0;
	protected int width = 0, height = 0;
	protected String imageFile = "";
	protected static boolean canAct = false;
	public static boolean canDraw = false;
	
	public abstract void act();

	@SuppressWarnings("rawtypes")
	public boolean checkCollision(java.lang.Class cls) {
		@SuppressWarnings("unchecked")
		ArrayList<Mob> clone = (ArrayList<Mob>) Main.getWorld().clone();
		boolean b = false;
		for(Mob m : clone) {
			if(m.getClass().equals(cls)) {
				if((this.x - (this.width/2)) < m.x + (m.width/2) && (this.x + (this.width/2)) > m.x - (m.width/2) && (this.y - (this.height/2)) < m.y + (m.height/2) && (this.y + (this.height/2)) > m.y - (m.height/2)) {
					b = true;
				}
			}
		} return b;
	}
	
	@SuppressWarnings("rawtypes")
	public Mob getCollided(java.lang.Class cls) {
		@SuppressWarnings("unchecked")
		ArrayList<Mob> clone = (ArrayList<Mob>) Main.getWorld().clone();
		for(Mob m : clone) {
			if(m.getClass().equals(cls)) {
				if((this.x - (this.width/2)) < m.x + (m.width/2) && (this.x + (this.width/2)) > m.x - (m.width/2) && (this.y - (this.height/2)) < m.y + (m.height/2) && (this.y + (this.height/2)) > m.y - (m.height/2)) {
					return m;
				}
			}
		} return null;
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
			g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1f));
			g2d.drawImage(i, at, null);
		} catch(IOException e) {
			e.printStackTrace();
		}
	}

	public double getRotation() {
		return rotation;
	}

	public double getX() {
		return x;
	}

	public double getY() {
		return y;
	}

	public void setRotation(int rotation) {
		this.rotation = rotation;
	}

	public void setX(int x) {
		this.x = x;
	}

	public void setY(int y) {
		this.y = y;
	}
	
	public void changeVelocity(double direction, double vel_Offset) {
		double pastX_vel = calculateXComp(direction);
		double pastY_vel = calculateYComp(direction);
		vel += vel_Offset;
		x_vel += calculateXComp(direction) - pastX_vel;
		y_vel += calculateYComp(direction) - pastY_vel;
	}
	
	public double calculateXComp(double direction) {
		return (Math.sin(direction*(Math.PI/180)) * vel);
	}
	
	public double calculateYComp(double direction) {
		return -(Math.cos(direction*(Math.PI/180)) * vel);
	}
}
