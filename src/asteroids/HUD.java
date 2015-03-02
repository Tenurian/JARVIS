package asteroids;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.LinkedList;

public class HUD {
	
	private static final Font LARGE_FONT = new Font("Verdana", Font.BOLD, 36),
			MEDIUM_FONT = new Font("Verdana", Font.ITALIC, 24),
			SMALL_FONT = new Font("Verdana", Font.BOLD, 16);
	
	public static void update(Graphics g) {
		Graphics2D g2d = (Graphics2D) g;
		g2d.setPaint(Color.white);
		g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1f));
		LinkedList<Highscore> highscores = Main.highscores.getHighscores();
		switch(Main.currentScreen) {
			case GAME:
				g2d.setFont(SMALL_FONT);
				g2d.drawString("Score: " + Main.score, (Main.WIDTH*Main.SCALE)/16, (Main.HEIGHT*Main.SCALE)/16);
				Highscore highestScore = new Highscore(0);
				if(!highscores.isEmpty()) {
					highestScore = highscores.getFirst();
				} g2d.drawString("Hi-Score: " + highestScore.getValue(), 3*(Main.WIDTH*Main.SCALE)/4, (Main.HEIGHT*Main.SCALE)/16);
				break;
			case GAMEOVER:
				g2d.setFont(LARGE_FONT);
				drawCenteredText(g2d, "Game Over!", (Main.WIDTH*Main.SCALE)/2, (Main.HEIGHT*Main.SCALE)/5);
				g2d.setFont(MEDIUM_FONT);
				FontMetrics fm = g2d.getFontMetrics();
				drawCenteredText(g2d, "Highscores", (Main.WIDTH*Main.SCALE)/2, (Main.HEIGHT*Main.SCALE)/4 + fm.getAscent());
				g2d.setFont(SMALL_FONT);
				fm = g2d.getFontMetrics();
				for(int i = 0; i < 10; i++) {
					if(i < highscores.size()) {
						drawCenteredText(g2d, (i+1) + ". " + highscores.get(i).getValue(), (Main.WIDTH*Main.SCALE)/2, (Main.HEIGHT*Main.SCALE)/4 + (fm.getAscent()*(i+3)));
					}
				}
				g2d.setFont(MEDIUM_FONT);
				drawCenteredText(g2d, "Press Restart to Restart", (Main.WIDTH*Main.SCALE)/2, 4*(Main.HEIGHT*Main.SCALE)/5);
				break;
			case START:
				g2d.setFont(LARGE_FONT);
				drawCenteredText(g2d, "Space Rocks", (Main.WIDTH*Main.SCALE)/2, (Main.HEIGHT*Main.SCALE)/2);
				g2d.setFont(MEDIUM_FONT);
				drawCenteredText(g2d, "Press Start to Start", (Main.WIDTH*Main.SCALE)/2, 3*(Main.HEIGHT*Main.SCALE)/4);
				break;
			case PAUSE:
				g2d.setFont(LARGE_FONT);
				drawCenteredText(g2d, "Paused", (Main.WIDTH*Main.SCALE)/2, (Main.HEIGHT*Main.SCALE)/5);
				g2d.setFont(MEDIUM_FONT);
				if(Main.canBounce) {
					drawCenteredText(g2d, "Bouncing: On", (Main.WIDTH*Main.SCALE)/2, (Main.HEIGHT*Main.SCALE)/2);
				} else {
					drawCenteredText(g2d, "Bouncing: Off", (Main.WIDTH*Main.SCALE)/2, (Main.HEIGHT*Main.SCALE)/2);
				}
				break;
			default:
				break;
		}
	}
	
	public static void drawCenteredText(Graphics2D g2d, String msg, int x, int y) {
		FontMetrics fm = g2d.getFontMetrics();
		int x_offset = fm.stringWidth(msg)/2;
		int y_offset = fm.getAscent()/2;
		g2d.drawString(msg, x - x_offset, y - y_offset);
	}
}
