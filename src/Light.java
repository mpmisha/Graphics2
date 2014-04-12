
public class Light {
	private Point position;
	private Color color;
	private double SpecularIntensity;
	private double ShadowIntensity;
	private double radius;

	
	public Light(Point position, Color color, double specularIntensity,
			double shadowIntensity, double radius) {

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


	public double getSpecularIntensity() {
		return SpecularIntensity;
	}


	public void setSpecularIntensity(double specularIntensity) {
		SpecularIntensity = specularIntensity;
	}


	public double getShadowIntensity() {
		return ShadowIntensity;
	}


	public void setShadowIntensity(double shadowIntensity) {
		ShadowIntensity = shadowIntensity;
	}


	public double getRadius() {
		return radius;
	}


	public void setRadius(double radius) {
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

