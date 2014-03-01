// File: Sphere.java

// Author: Christopher Camargo
// CMPS 112: Winter 2014

public class Sphere {
	private Vec3 center; // Center of a sphere in 3D.
	private float radius, squaredRadius; // Radius information.
	private Vec3 ambient, diffuse, specular; // Material information.
	private float shininess; // Shine for specular term.

	// Main constructor for a Sphere.
	// Sphere(center x, center y, center z, radius, diffuse r, diffuse g, diffuse b, ambient, specular, shine)
	public Sphere(float c1, float c2, float c3, float r, float d1, float d2, float d3, float a, float s, float sh) {
		center = new Vec3(c1, c2, c3);
		radius = r;
		squaredRadius = r * r;

		ambient = new Vec3(a);
		diffuse = new Vec3(d1, d2, d3);
		specular = new Vec3(s);
		shininess = sh;
	}

	// Returns the normal of sphere at point p.
	public Vec3 normal(Vec3 p) {
		return (p.sub(center)).normalize();
	}

	// Tests if a ray intersects an instance of this Sphere.
	public boolean intersect(Ray ray) {
		Vec3 l = center.sub(ray.origin());

		float tca = l.dot(ray.direction());

		if (tca < 0)
			return false;

		float d2 = l.dot(l) - tca * tca;

		if (d2 > squaredRadius)
			return false;

		float thc = (float) Math.sqrt(squaredRadius - d2);

		ray.setT(tca - thc);

		return true;
	}

	// Returns ambient light of sphere.
	public Vec3 ambient() {
		return ambient;
	}

	// Returns diffuse light of sphere.
	public Vec3 diffuse() {
		return diffuse;
	}

	// Returns specular light of sphere.
	public Vec3 specular() {
		return specular;
	}
}