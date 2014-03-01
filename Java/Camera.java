// File: Camera.java

// Author: Christopher Camargo
// CMPS 112: Winter 2014

import java.io.*;

public class Camera {
	private int width; // Width of final image.
	private int height; // Height of final image.

	private Vec3[] pixels; // Pixels in final image.

	private float fieldOfView; // Cameras field of view.
	private float aspect; // Aspect ratio of Camera.
	private float viewAngle; // Angle of viewer.

	private float invWidth; // Inverse of film width.
	private float invHeight; // Inverse of film height.

	// Main Camera constructor.
	public Camera(int w, int h, float fov) {
		width = w;
		height = h;

		pixels = new Vec3[width * height];

		fieldOfView = fov;
		aspect = (float) width / (float) height;
		viewAngle = (float) Math.tan(Math.PI * 0.5f * fieldOfView / 180.0f);

		invWidth = (1.0f / (float) width);
		invHeight = (1.0f / (float) height);
	}

	// Static method for getting a Ray from the Camera through each pixel.
	public Ray shootRay(int x, int y) {
		float xDir = (float) (2.0f * ((x + 0.5f) * invWidth) -1.0f) * viewAngle * aspect; 
		float yDir = (float) (1.0f - 2.0f * ((y + 0.5f) * invHeight)) * viewAngle;

		return new Ray(new Vec3(0.0f), new Vec3(xDir, yDir, -1.0f).normalize());
	}

	// Returns an int representation of a pixel color.
	private int getVal(float pixel) {
		return ((int) (Math.min(1.0f, pixel) * 255)) & 0xFF;
	}

	// Private method for writing the final image to a file.
	public void writePPM() {
		try {
			PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("out.ppm")));
			out.write("P3\n" + width + " " + height + "\n" + "255" + "\n");
			for (int i = 0; i < (width * height); i++) {
				out.write(getVal(pixels[i].x) + " " + getVal(pixels[i].y) + " " + getVal(pixels[i].z) + " \n");
			}

			out.close();
		} catch (IOException e) {
			System.exit(1);
		}
	}

	// Sets a pixel value on the film plane.
	public void setPixel(int i, Vec3 v) {
		pixels[i] = v;
	}

	// Returns the width of the film.
	public int width() {
		return width;
	}

	// Returns the height of the film.
	public int height() {
		return height;
	}
}