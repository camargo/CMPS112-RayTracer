// File: RayTracer.java

// Author: Christopher Camargo
// CMPS 112: Winter 2014

import java.util.ArrayList;

public class RayTracer {
	private ArrayList<Sphere> spheres; // Spheres in scene.
	private Light light; // Light in scene.
	private Camera camera; // Camera in scene.

	// Constructor populates scene with Spheres, Lights, and Cameras.
	public RayTracer() {
		spheres = new ArrayList<Sphere>();
		spheres.add(new Sphere(-2.0f, 0.0f, -15.0f, 4.0f, 1.00f, 0.32f, 0.36f, 0.1f, 0.8f, 100.0f));
		spheres.add(new Sphere(5.0f, 5.0f, -15.0f, 2.0f, 0.00f, 0.92f, 0.36f, 0.1f, 0.8f, 100.0f));
		spheres.add(new Sphere(10.0f, -8.0f, -30.0f, 6.0f, 0.36f, 0.32f, 1.00f, 0.1f, 0.8f, 100.0f));

		light = new Light(5.0f, 10.0f, 10.0f, 1.0f); // (x, y, z, intensity).
		camera = new Camera(512, 512, 50.0f); // (width, height, fov).
	}

	// Trace determines Ray-Sphere intersections and returns a color based on the Spheres material.
	private Vec3 trace(Ray ray) {
		Sphere sphere = null;

		for (int i = 0; i < spheres.size(); i++) {
			if (spheres.get(i).intersect(ray)) {
				sphere = spheres.get(i);
			}
		}

		if (sphere == null) {
			return new Vec3(0.0f); // Background color.
		}

		Vec3 p = ray.getIntersection(); // Intersection point.
		Vec3 n = sphere.normal(p); // Normal at intersection.

		return light.phong(sphere, p, n);
	}

	// Start the Ray Tracer and find the pixels for the final image.
	public void start() {
		int pixelNum = 0;

		for (int y = 0; y < camera.height(); y++) {
			for (int x = 0; x < camera.width(); x++) {
				camera.setPixel(pixelNum++, trace(camera.shootRay(x, y)));
			}
		}

		camera.writePPM();
	}

	// Main.
	public static void main(String[] args) {
		RayTracer rayTracer = new RayTracer();
		rayTracer.start();
	}
}