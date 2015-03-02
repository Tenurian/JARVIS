package asteroids;

import java.awt.AlphaComposite;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

public class ShipShard extends Mob {
	private float ticks = 0;
	
	public ShipShard(double x, double y, double rotation) {
		this.x = x;
		this.y = y;
		this.vel = 1;
		this.rotation = rotation;
		imageFile = "/Shot.png";
	}
	@Override
	public void act() {
		x += this.calculateXComp(rotation);
		y += this.calculateYComp(rotation);
		if(ticks < 0.98f) {
			ticks += 0.02f;
		} else {
			this.triggerGameover();
		} if(this.x > Main.WIDTH*Main.SCALE) {
			this.triggerGameover();
		} else if(this.x < 0) {
			this.triggerGameover();
		} else if(this.y > Main.HEIGHT*Main.SCALE) {
			this.triggerGameover();
		} else if(this.y < 0) {
			this.triggerGameover();
		}
	}

	private void triggerGameover() {
		Main.currentScreen = Screen.GAMEOVER;
		Main.showMobs = false;
		Main.removeMob(this);
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
			g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1f-ticks));
			g2d.drawImage(i, at, null);
		} catch(IOException e) {
			e.printStackTrace();
		}
	}
}
