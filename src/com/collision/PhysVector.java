package com.collision;

/**
 * 
 * @author Evan Hanger, Andrew Huber, Mark Judy
 * 
 * 5/13/2014, 10:50pm
 * 
 * This class is used to represent a 2-D vector that can store an x and y
 * coordinate. It has a variety of methods, static and non static,
 * which perform vector math. This class is most heavily used in missile
 * motion, missile tracking, and rotation of the missile sprite.  
 *
 */
public class PhysVector {

	/** The two dimensions of the vector */
	public float x, y;
	
	/** 
	 * Constructor for PhysVector
	 * @param x dimension 1
	 * @param y dimension 2
	 */
	public PhysVector(float x, float y){
		
		this.x = x;
		this.y = y;
	}
	
	/**
	 * Uses pythagorean theorem to find magnitude of the vector
	 * @return the magnitude of this vector
	 */
	public float magnitude(){
		
		return (float)Math.sqrt(Math.pow((double)x, 2) + Math.pow((double)y,2));
	}
	
	/**
	 * Causes the vector to become a unit vector (preserves its original direction
	 * but has a magnitude of 1)
	 */
	public void becomeUnitVector(){
		
		float oldMag = this.magnitude();
		
		x /= oldMag;
		y /= oldMag;
	}
	
	/**
	 * Scales vector by mutliplying both of its elements by n
	 * @param n the amount by which to scale the elements by
	 */
	public void scale(float n){
		
		x *= n;
		y *= n;
	}
	
	/**
	 * Adds two vectors and returns the sum
	 * @param v1 first vector
	 * @param v2 second vector
	 * @return The resultant vector sum
	 */
	public static PhysVector add(PhysVector v1, PhysVector v2){
		
		return new PhysVector(v1.x + v2.x, v1.y + v2.y);
	}
	
	
	/**
	 * 
	 * Subtracts second vector from first
	 * 
	 * @param v1 the vector that is subtracted from
	 * @param v2 the vector that is subtracted
	 * @return difference between the vectors
	 */
	public static PhysVector subtract(PhysVector v1, PhysVector v2){
		
		return new PhysVector(v1.x - v2.x, v1.y - v2.y);
	}
	
	/**
	 * Return a unit vector pointing in the same direction as the given vector
	 * @param v 
	 * @return a unit vector with same direction as v
	 */
	public static PhysVector unitVector(PhysVector v){
		
		return new PhysVector(v.x / v.magnitude(), v.y / v.magnitude());
	}
	
	/**
	 * Finds the distance between two position vectors. First subtracts, then
	 * takes the magnitude of the difference
	 * @param v1 the first vector
	 * @param v2 the second vector
	 * @return the magnitude of the difference
	 */
	public static float distance(PhysVector v1, PhysVector v2){
		
		return PhysVector.subtract(v1, v2).magnitude();
	}
	
	/**
	 * Calculates the dot product of the two given vectors
	 * @param a vector 1
	 * @param b vector 2
	 * @return dot product (a DOT b)
	 */
	public static float dotProduct(PhysVector a, PhysVector b){
		
		return ((a.x * b.x) + (a.y * b.y));
	}
}
