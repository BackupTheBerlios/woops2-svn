package gui;

import gui.sprite.entities.BusEntity;
import gui.sprite.entities.Entity;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.BufferStrategy;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class BusDisplayerFrame extends Canvas {

	private static BusDisplayerFrame busDisplayerFrame;

	public static final int REFRESH_TIME = 300; // millisec

	public static final int X_WINDOW = 500;

	public static final int Y_WINDOW = 400;

	private BufferStrategy strategy;

	private boolean isRunning = true;

	private ArrayList<Entity> entities = new ArrayList<Entity>();

	private ArrayList removeList = new ArrayList();

	public static BusDisplayerFrame getInstance() {
		if (busDisplayerFrame == null)
			busDisplayerFrame = new BusDisplayerFrame();
		return busDisplayerFrame;
	}

	private BusDisplayerFrame() {
		// create a frame to contain our game
		JFrame container = new JFrame("ISI Bus Navigator - DISPLAY");

		// get hold the content of the frame and set up the resolution of the game
		JPanel panel = (JPanel) container.getContentPane();
		panel.setPreferredSize(new Dimension(X_WINDOW, Y_WINDOW));
		panel.setLayout(null);

		// setup our canvas size and put it into the content of the frame
		setBounds(0, 0, X_WINDOW, Y_WINDOW);
		panel.add(this);

		// Tell AWT not to bother repainting our canvas since we're
		// going to do that our self in accelerated mode
		setIgnoreRepaint(true);

		// finally make the window visible 
		container.pack();
		container.setResizable(false);
		container.setVisible(true);
		container
				.setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

		// add a listener to respond to the user closing the window. If they
		// do we'd like to exit the game
		container.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}
		});

		// create the buffering strategy which will allow AWT
		// to manage our accelerated graphics
		createBufferStrategy(2);
		strategy = getBufferStrategy();

		// initialise the entities in our game so there's something
		// to see at startup
		initEntities();
	}

	private void reInit() {
		// clear out any existing entities and intialise a new set
		entities.clear();
		initEntities();

	}

	/**
	 * Initialise the starting state of the entities (ship and aliens). Each
	 * entitiy will be added to the overall list of entities in the game.
	 */
	private void initEntities() {
		// create the player ship and place it roughly in the center of the screen
		//ShipEntity ship = new ShipEntity(this,"sprites/ship.gif",370,550);
		BusEntity be = new BusEntity("resources/images/iconBus_small.jpg", 100,
				100);

		entities.add(be);

	}

	/**
	 * Remove an entity from the game. The entity removed will
	 * no longer move or be drawn.
	 * 
	 * @param entity The entity that should be removed
	 */
	public void removeEntity(Entity entity) {
		removeList.add(entity);
	}

	/**
	 * The main game loop. This loop is running during all game
	 * play as is responsible for the following activities:
	 * <p>
	 * - Working out the speed of the game loop to update moves
	 * - Moving the game entities
	 * - Drawing the screen contents (entities, text)
	 * - Updating game events
	 * - Checking Input
	 * <p>
	 */
	public void mainLoop() {
		long lastLoopTime = System.currentTimeMillis();

		// keep looping round til the game ends
		while (isRunning) {
			// work out how long its been since the last update, this
			// will be used to calculate how far the entities should
			// move this loop
			long delta = System.currentTimeMillis() - lastLoopTime;
			lastLoopTime = System.currentTimeMillis();

			// Get hold of a graphics context for the accelerated 
			// surface and blank it out
			Graphics2D g = (Graphics2D) strategy.getDrawGraphics();
			g.setColor(Color.black);
			g.fillRect(0, 0, 800, 600);

			// cycle round drawing all the entities we have in the game
			for (int i = 0; i < entities.size(); i++) {
				Entity entity = (Entity) entities.get(i);
				entity.move(delta);
				entity.draw(g);
			}

			// brute force collisions, compare every entity against
			// every other entity. If any of them collide notify 
			// both entities that the collision has occured
			/*
			 for (int p=0;p<entities.size();p++) {
			 for (int s=p+1;s<entities.size();s++) {
			 Entity me = (Entity) entities.get(p);
			 Entity him = (Entity) entities.get(s);
			 
			 if (me.collidesWith(him)) {
			 me.collidedWith(him);
			 him.collidedWith(me);
			 }
			 }
			 }*/

			// remove any entity that has been marked for clear up
			entities.removeAll(removeList);
			removeList.clear();

			// finally, we've completed drawing so clear up the graphics
			// and flip the buffer over
			g.dispose();
			strategy.show();

			try {
				Thread.sleep(REFRESH_TIME);
			} catch (Exception e) {
			}
		}
	}

}
