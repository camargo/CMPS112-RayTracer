// File: Light.java

// Author: Christopher Camargo
// CMPS 112: Winter 2014

public class Light {
	private Vec3 position; // 3D Position.
	private Vec3 intensity; // RGB Intensity.

	// Main constructor for a Light.
	public Light(float x, float y, float z, float i) {
		position = new Vec3(x, y, z);
		intensity = new Vec3(i);
	}

	// Calculates the Phong lighting model at intersection point (ambient + diffuse + specular).
	public Vec3 phong(Sphere sp, Vec3 p, Vec3 n) {
		Vec3 outColor = new Vec3(0.0f);

		Vec3 s = toLight(p);
		Vec3 v = toCam(p);
		Vec3 r = reflect(p, n);

		Vec3 ambient = sp.ambient();
		
		Vec3 diffuse = sp.diffuse().mult(Math.max(n.dot(s), 0.0f));

		float f = (float) Math.pow( Math.max(r.dot(v), 0.0f), 100);
		Vec3 specular = sp.specular().mult(f);

		return outColor = Vec3.multv(intensity, ambient.add(diffuse.add(specular)));
	}

	// Calculates the reflection from light about a normal at point p.
	public Vec3 reflect(Vec3 p, Vec3 n) {
		Vec3 s = position.sub(p).normalize();
		return (s.invert()).add(n.mult(n.dot(s) * 2));
	}

	// Calculate a vector from a point to the Light.
	public Vec3 toLight(Vec3 p) {
		return position.sub(p).normalize();
	}

	// Calculate a vector from a point to the Camera.
	public Vec3 toCam(Vec3 p) {
		return p.invert().normalize();
	}
}