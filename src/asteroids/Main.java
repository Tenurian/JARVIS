package asteroids;

import java.awt.*;
import java.awt.image.BufferStrategy;
import java.util.ArrayList;
import java.util.Random;
import java.util.Timer;

import javax.swing.*;

public class Main extends Canvas implements Runnable {

	private static final long serialVersionUID = 1L;
	public static final int WIDTH = 500, HEIGHT = 500, SCALE = 1, FPS = 60;
	private static ArrayList<Mob> world = new ArrayList<Mob>();
	public static Mob player;
	public static int score = 0;
	public static Screen currentScreen = Screen.START;
	public static boolean showMobs = false;
	public static Main main = null;
	public BufferStrategy bs = null;
	public static boolean running = false;
	public static HighscoreManager highscores = new HighscoreManager();
	public static boolean canBounce = true;
	public Thread gameThread;
	private Timer timer = new Timer("Refresh Timer");
	
	public static void init(){
		main = new Main();
		main.setPreferredSize(new Dimension(WIDTH * SCALE, HEIGHT * SCALE));
		main.setMinimumSize(new Dimension(WIDTH * SCALE, HEIGHT * SCALE));
		main.setMaximumSize(new Dimension(WIDTH * SCALE, HEIGHT * SCALE));
		
		JFrame frame = new JFrame ("Space Rocks");
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.setSize(WIDTH * SCALE, HEIGHT * SCALE);
		frame.setResizable(false);
		frame.addKeyListener(new Controller());
		frame.add(main);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);

		main.start();
	}
	
	public synchronized void start() {
		if(running) {
			return;
		} running = true;
		gameThread = new Thread(this);
		gameThread.start();
	}
	
	public synchronized void stop() {
		if(!running) {
			return;
		} try {
			gameThread.join();
		} catch(InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	@Override 
	public void run() {
		initialize();
		long lastTime = System.nanoTime();
		final double amountOfTicks = 60D;
		double ns = 1000000000 / amountOfTicks;
		double delta = 0;
		while(running) {
			long now = System.nanoTime();
			delta += (now - lastTime) / ns;
			lastTime = now;
			if(delta >= 1) {
				switch(currentScreen) {
				case GAME:
					generateAsteroid();
					if(player != null) {
						player.act();
					} @SuppressWarnings("unchecked")
					ArrayList<Mob> clone = (ArrayList<Mob>) world.clone();
					for(Mob m : clone) {
						m.act();
					} break;
				case GAMEOVER:
					break;
				case START:
					break;
				default:
					break;
				}//refresh();
				delta--;
			}
		} stop();
	}
	
	public static void generateAsteroid() {
		Random rand = new Random();
		if(rand.nextInt(1000)+1 >= 999) {
			int side = rand.nextInt(4);
			if(side == 0) {
				world.add(new Asteroid(rand.nextInt(WIDTH*SCALE), 0, rand.nextDouble()*360, 1));
			} else if(side == 1) {
				world.add(new Asteroid(WIDTH*SCALE, rand.nextInt(HEIGHT*SCALE), rand.nextDouble()*360, 1));
			} else if(side == 2) {
				world.add(new Asteroid(rand.nextInt(WIDTH*SCALE), HEIGHT*SCALE, rand.nextDouble()*360, 1));
			} else if(side == 3) {
				world.add(new Asteroid(0, rand.nextInt(HEIGHT*SCALE), rand.nextDouble()*360, 1));
			}
		}
	}
	
	public static void addMob(Mob m) {
		Main.world.add(m);
	}
	
	public static void removeMob(Mob m) {
		Main.world.remove(m);
	}
	
	public void refresh() {
		bs = this.getBufferStrategy();
		if(bs == null) {
			this.createBufferStrategy(3);
			return;
		} assert bs != null;
		Graphics g = bs.getDrawGraphics();
		g.fillRect(0, 0, WIDTH*SCALE, HEIGHT*SCALE);
		if(showMobs) {
			player.draw(g);
			@SuppressWarnings("unchecked")
			ArrayList<Mob> clone = (ArrayList<Mob>) world.clone();
			for(Mob m : clone) {
				m.draw(g);
			}
		} HUD.update(g);
		g.dispose();
		bs.show();
	}
	
	public static void restart() {
		world.removeAll(world);
		score = 0;
		Random rand = new Random();
		player = new Player();
		player.setX(50);
		player.setY(50);
		world.add(new Asteroid(rand.nextInt(WIDTH*SCALE), rand.nextInt(HEIGHT*SCALE), rand.nextDouble()*360, 1));
		world.add(new Asteroid(rand.nextInt(WIDTH*SCALE), rand.nextInt(HEIGHT*SCALE), rand.nextDouble()*360, 1));
		world.add(new Asteroid(rand.nextInt(WIDTH*SCALE), rand.nextInt(HEIGHT*SCALE), rand.nextDouble()*360, 1));
	}
	public void initialize() {
		Random rand = new Random();
		player = new Player();
		player.setX(50);
		player.setY(50);
		world.add(new Asteroid(rand.nextInt(WIDTH*SCALE), rand.nextInt(HEIGHT*SCALE), rand.nextDouble()*360, 1));
		world.add(new Asteroid(rand.nextInt(WIDTH*SCALE), rand.nextInt(HEIGHT*SCALE), rand.nextDouble()*360, 1));
		world.add(new Asteroid(rand.nextInt(WIDTH*SCALE), rand.nextInt(HEIGHT*SCALE), rand.nextDouble()*360, 1));
		highscores = highscores.loadHighscores();
		this.addKeyListener(new Controller());
		timer.scheduleAtFixedRate(new Actions(), 0, 1000/FPS);
	}

	public static Player getPlayer() {
		return (Player) player;
	}

	public static void setPlayer(Player player) {
		Main.player = player;
	}
	
	public static ArrayList<Mob> getWorld() {
		return world;
	}
}
