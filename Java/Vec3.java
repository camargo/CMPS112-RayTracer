// File: Vec3.java

// Author: Christopher Camargo
// CMPS 112: Winter 2014

public class Vec3 {
	// Main members of a 3D vector.
	public float x, y, z; // Made public for simplicity.

	// Empty constructor makes zero vector.
	public Vec3() {
		this.x = 0.0f; this.y = 0.0f; this.z = 0.0f;
	}

	// Main constructor to build a vector.
	public Vec3(float x, float y, float z) {
		this.x = x; this.y = y; this.z = z;
	}

	// Build a vector from another vector.
	public Vec3(Vec3 v) {
		this.x = v.x; this.y = v.y; this.z = v.z;
	}

	// Build a vector with the same value for all elements.
	public Vec3(float a) {
		this.x = this.y = this.z = a;
	}

	// Computes the dot (aka scalar) product.
	public float dot(Vec3 v) {
		return (x*v.x + y*v.y + z*v.z);
	}

	// Static version of dot.
	public static float dot(Vec3 v1, Vec3 v2) {
		return (v1.x * v2.x + v1.y * v2.y + v1.z * v2.z);
	}

	// Multiplies vector by scaler and returns a new vector.
	public Vec3 mult(float value) {
		return new Vec3(x*value, y*value, z*value);
	}

	// Multiplies vector by another vector and returns a new vector.
	public static Vec3 multv(Vec3 v1, Vec3 v2) {
		return new Vec3(v1.x*v2.x, v1.y*v2.y, v1.z*v2.z);
	}

	// Add this vector to another and return a new vector.
	public Vec3 add(Vec3 v) {
		return new Vec3(x + v.x, y + v.y, z + v.z);
	}

	// Subtract this vector from another and return a new vector.
	public Vec3 sub(Vec3 v) {
		return new Vec3(x - v.x, y - v.y, z - v.z);
	}

	// Normalizes this vector.
	public Vec3 normalize() {
		float length = squaredMag();

		if (length > 0) {
			double invLen = 1 / Math.sqrt(length);
			x *= invLen; y *= invLen; z *= invLen;
		}

		return this;
	}

	// Returns the squared magnitude (length) of this vector.
	public float squaredMag() {
		return (x*x + y*y + z*z);
	}

	// Returns the magnitude of this vector.
	public float mag() {
		return (float) Math.sqrt(x*x + y*y + z*z);
	}

	// Returns a new vector with all elements negated.
	public Vec3 invert() {
		return new Vec3(-x, -y, -z);
	}

	// Gets string representation of vector for testing.
	public String toString() {
		return "(" + x + ", " + y + ", " + z + ")";
	}
}