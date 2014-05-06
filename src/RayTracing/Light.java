package RayTracing;

public class Light {
	private Point position;
	private Color color;
	private float SpecularIntensity;
	private float ShadowIntensity;
	private float radius;

	
	public Light(Point position, Color color, float specularIntensity,
			float shadowIntensity, float radius) {

		this.position = position;
		this.color = color;
		SpecularIntensity = specularIntensity;
		ShadowIntensity = shadowIntensity;
		this.radius = radius;
	}


	public Point getPosition() {
		return position;
	}


	public void setPosition(Point position) {
		this.position = position;
	}


	public Color getColor() {
		return color;
	}


	public void setColor(Color color) {
		this.color = color;
	}


	public float getSpecularIntensity() {
		return SpecularIntensity;
	}


	public void setSpecularIntensity(float specularIntensity) {
		SpecularIntensity = specularIntensity;
	}


	public float getShadowIntensity() {
		return ShadowIntensity;
	}


	public void setShadowIntensity(float shadowIntensity) {
		ShadowIntensity = shadowIntensity;
	}


	public float getRadius() {
		return radius;
	}


	public void setRadius(float radius) {
		this.radius = radius;
	}


	@Override
	public String toString() {
		return "Light [position=" + position + ", color=" + color
				+ ", SpecularIntensity=" + SpecularIntensity
				+ ", ShadowIntensity=" + ShadowIntensity + ", radius=" + radius
				+ "]";
	}

	


}

