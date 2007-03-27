package gui;

import gui.sprite.entities.BusEntity;
import gui.sprite.entities.Entity;
import gui.sprite.entities.MapEntity;

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

	public static final int X_WINDOW = 904;

	public static final int Y_WINDOW = 376;

	private BufferStrategy strategy;

	private boolean isRunning = true;

	private ArrayList<Entity> entities = new ArrayList<Entity>();

	private ArrayList<Entity> removeList = new ArrayList<Entity>();

	public static BusDisplayerFrame getInstance() {
		if (busDisplayerFrame == null)
			busDisplayerFrame = new BusDisplayerFrame();
		return busDisplayerFrame;
	}

	private BusDisplayerFrame() {
		JFrame container = new JFrame("ISI Bus Navigator - R\u00e9seau urbain");

		JPanel panel = (JPanel) container.getContentPane();
		panel.setPreferredSize(new Dimension(X_WINDOW, Y_WINDOW));
		panel.setLayout(null);

		setBounds(0, 0, X_WINDOW, Y_WINDOW);
		panel.add(this);

		setIgnoreRepaint(true);

		// finally make the window visible 
		container.pack();
		container.setResizable(false);
		container.setVisible(true);
		container
				.setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

		container.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}
		});

		createBufferStrategy(2);
		strategy = getBufferStrategy();

		initEntities();
	}

	@SuppressWarnings("unused")
	private void reInit() {
		entities.clear();
		initEntities();
	}

	
	private void initEntities() {
		MapEntity me = new MapEntity("resources/images/plan_bus.png",0,0);
		entities.add(me);
	}

	
	public void removeEntity(Entity entity) {
		removeList.add(entity);
	}

	
	public void mainLoop() {
		long lastLoopTime = System.currentTimeMillis();

		while (isRunning) {
			lastLoopTime = System.currentTimeMillis();

			Graphics2D g = (Graphics2D) strategy.getDrawGraphics();
			g.setColor(Color.white);
			g.fillRect(0, 0, 800, 600);

			for (int i = 0; i < entities.size(); i++) {
				Entity entity = (Entity) entities.get(i);
				//entity.move(delta);
				if (entity instanceof BusEntity && entity.getIsRunning()) entity.draw(g);
				else entity.draw(g);
			}

			
			entities.removeAll(removeList);
			removeList.clear();

			g.dispose();
			strategy.show();

			try {
				Thread.sleep(REFRESH_TIME);
			} catch (Exception e) {
			}
		}
	}
	
	/**
	 * @return the entities
	 */
	public ArrayList<Entity> getEntities() {
		return entities;
	}

	/**
	 * @param entities the entities to set
	 */
	public void setEntities(ArrayList<Entity> entities) {
		this.entities = entities;
	}

}
