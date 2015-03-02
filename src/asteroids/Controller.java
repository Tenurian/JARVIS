package asteroids;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Controller implements KeyListener{
	@Override
	public void keyPressed(KeyEvent e) {
		if(e.getKeyCode() == KeyEvent.VK_UP) {
			Main.getPlayer().up = true;
		} if(e.getKeyCode() == KeyEvent.VK_RIGHT) {
			Main.getPlayer().rt = true;
		} if(e.getKeyCode() == KeyEvent.VK_LEFT) {
			Main.getPlayer().lt = true;
		} if(e.getKeyCode() == KeyEvent.VK_DOWN) {
			Main.getPlayer().dn = true;
		} if(e.getKeyCode() == KeyEvent.VK_SPACE) {
			switch(Main.currentScreen) {
			case GAME:
				Main.getPlayer().spc = true;
				break;
			case GAMEOVER:
				Main.currentScreen = Screen.GAME;
				Main.showMobs = true;
				Main.restart();
				break;
			case START:
				Main.currentScreen = Screen.GAME;
				Main.showMobs = true;
				break;
			default:
				break;
			}
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		if(e.getKeyCode() == KeyEvent.VK_UP) {
			Main.getPlayer().up = false;
		} if(e.getKeyCode() == KeyEvent.VK_RIGHT) {
			Main.getPlayer().rt = false;
			if(Main.currentScreen == Screen.PAUSE) {
				Main.canBounce = (Main.canBounce) ? false : true;
			}
		} if(e.getKeyCode() == KeyEvent.VK_LEFT) {
			Main.getPlayer().lt = false;
			if(Main.currentScreen == Screen.PAUSE) {
				Main.canBounce = (Main.canBounce) ? false : true;
			}
		} if(e.getKeyCode() == KeyEvent.VK_DOWN) {
			Main.getPlayer().dn = false;
		} if(e.getKeyCode() == KeyEvent.VK_ENTER) {
			switch(Main.currentScreen) {
			case GAME:
				Main.currentScreen = Screen.PAUSE;
				Main.showMobs = false;
				break;
			case GAMEOVER:
				break;
			case PAUSE:
				Main.currentScreen = Screen.GAME;
				Main.showMobs = true;
				break;
			case START:
				break;
			default:
				break;
			}
		} if(e.getKeyCode() == KeyEvent.VK_SPACE) {
			Main.getPlayer().spc = false;
		}
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

}
