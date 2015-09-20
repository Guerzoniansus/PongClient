import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JFrame;

public class Game extends Canvas implements Runnable {

	// PONG

	Game game;

	Graphics graphics;

	public static int roomWidth;
	public static int roomHeight;

	int batHeight = 64;
	int batWidth = 16;
	
	int ballX;
	int ballY;
	
	int bat_leftX;
	int bat_leftY;
	int bat_rightX;
	int bat_rightY;


	boolean isRunning;
	boolean inGame;
	
	int FPS = 1000 / 60;

	public Game(int roomWidth, int roomHeight) {

		Game.roomWidth = roomWidth;
		Game.roomHeight = roomHeight;
		
		bat_leftX = 70;
		bat_rightX = roomWidth - 70 - batWidth;

		this.setSize(roomWidth, roomHeight);
		this.requestFocus();

		game = this;

		game.addMouseMotionListener(new MouseListener());

		isRunning = true;
	}
	
	
	@Override
	public void run() {

		while (isRunning) {
						
			gameLoop();
			
			try {
				Thread.sleep(FPS);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		
	}
	
	public void gameLoop() {
		
		if (!inGame) {
			// Make player type IP to connect
		}
		
		repaint();
	}
	

	public void start() {
		new Thread(this).start();
	}

	@Override
	public void paint(Graphics g) {
		
		g.setColor(Color.WHITE);
		
		drawBall(g);
		drawBats(g);
		
		// Draw walls
		g.fillRect(0, 0, roomWidth, 16);
		g.fillRect(0, roomHeight - 45, roomWidth, 16);

	}
	
	public void drawBats(Graphics g) {
		g.fillRect(bat_leftX, bat_leftY, batWidth, batHeight);
		g.fillRect(bat_rightX, bat_rightY, batWidth, batHeight);
	}
	
	public void drawBall(Graphics g) {
		g.fillOval(ballX, ballY, 16, 16);
	}


	public static void main(String[] args) {

		Game g = new Game(720, 480);
		g.setBackground(Color.BLACK);

		JFrame frame = new JFrame("Pong");

		frame.add(g);
		frame.setSize(720, 480);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);
		frame.setVisible(true);
		frame.requestFocus();

		g.start();

	}

	public class MouseListener extends MouseAdapter {

		@Override
		public void mouseMoved(MouseEvent e) {

			double newy = e.getY();

			// Send newy to server

		}

	}

	// dbing
	public void update(Graphics g) {
		Graphics offgc;
		Image offscreen = null;
		Dimension d = size();

		// create the offscreen buffer and associated Graphics
		offscreen = createImage(d.width, d.height);
		offgc = offscreen.getGraphics();
		// clear the exposed area
		offgc.setColor(getBackground());
		offgc.fillRect(0, 0, d.width, d.height);
		offgc.setColor(getForeground());
		// do normal redraw
		paint(offgc);
		// transfer offscreen to window
		g.drawImage(offscreen, 0, 0, this);
	}

}
