package com.falling.fallinggame;

import java.util.ArrayList;
import java.util.concurrent.ConcurrentHashMap;

import com.falling.object.Balloon;
import com.falling.object.Bird;
import com.falling.object.GameObject;
import com.falling.object.NormalMissile;
import com.falling.object.Plane;
import com.falling.object.UFO;

/**
 * Creates a Handler class for the Falling Game that handles game interaction
 * between all the {@link GameObject} objects in the game.
 * 
 * @author andrew_huber, evan_hanger, mark_judy
 *
 */
public class GameHandler {
	
	/** 
	 * Stores a key-value pair of Strings containing the name of the
	 * class ({@link Class#getName()}) and an {@link ArrayList} containing
	 * all of the {@link GameObject GameObjects} associated with that key.
	 * <br><br>
	 * For example, all the {@link Balloon} objects will have a key of
	 * {@code Balloon.class.getName()} and its value is an 
	 * {@link ArrayList} containing all the {@link Balloon Balloons} in
	 * the game.
	 */
	private ConcurrentHashMap<String, ArrayList<GameObject>> objects;
	
	/** Creates a GameHandler */
	public GameHandler() {
		objects = new ConcurrentHashMap<String, ArrayList<GameObject>>();
		
		objects.put(Balloon.class.getName(), new ArrayList<GameObject>());
		objects.put(Bird.class.getName(), new ArrayList<GameObject>());
		objects.put(NormalMissile.class.getName(), new ArrayList<GameObject>());
		objects.put(Plane.class.getName(), new ArrayList<GameObject>());
		objects.put(UFO.class.getName(), new ArrayList<GameObject>());
	}
	
	/**
	 * Spawns a new {@link GameObject} of a certain type.
	 * @param type The type of {@link GameObject} to spawn; call
	 * {@link Class#getName()} to provide the proper value. For example,
	 * if you want to create a balloon, call, {@code Balloon.class.getName()}. 
	 * @return the {@link GameObject} that is either being respawned or that was
	 * created as a result of having too little {@link GameObject GameObjects} of
	 * a particular type.
	 */
	public GameObject spawn(String type) {
		ArrayList<GameObject> obj = objects.get(type);
		
		if(obj == null)
			throw new IllegalArgumentException("Invalid type");
		
		int i = 0;
		int pos = -1;
		
		// Find a "dead" object
		for(GameObject o : obj) {
			if(o.alive() == false) {
				pos = i;
				break;
			}
			else
				i++;
		}
		
		// If all the objects are "alive"
		if(pos == -1) {
			
			// Create a new GameObject and add it to the map appropriately
			
			if(Balloon.class.getName().equals(type))
				obj.add(new Balloon());
			else if(Bird.class.getName().equals(type))
				obj.add(new Bird());
			else if(NormalMissile.class.getName().equals(type))
				obj.add(new NormalMissile());
			else if(Plane.class.getName().equals(type))
				obj.add(new Plane());
			else
				obj.add(new UFO());
			
			pos = obj.size() - 1;
		}
		
		obj.get(pos).respawn();
		return obj.get(pos);
	}
}
