// File: Ray.java

// Author: Christopher Camargo
// CMPS 112: Winter 2014

public class Ray {
	private Vec3 origin; // Ray point of origin.
	private Vec3 direction; // Direction of Ray.
	private float t; // Parameter for this Ray.

	// Main constructor for a Ray.
	public Ray(Vec3 o, Vec3 d) {
		origin = o; 
		direction = d;
	}

	// Returns a vector representing an intersection point with a shape.
	public Vec3 getIntersection() {
		return origin.add(direction.mult(t));
	}

	// Sets the parameter t of a Ray.
	public void setT(float t) {
		this.t = t;
	}

	// Returns the origin of a Ray.
	public Vec3 origin() {
		return origin;
	}

	// Returns the direction of a Ray.
	public Vec3 direction() {
		return direction;
	}

	// Returns the parameter t of a Ray.
	public float t() {
		return t;
	}
}