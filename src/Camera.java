
public class Camera {
	private Point position;
	private Point lookAtPoint;
	private Point upVector; 
	private double screenDistance;
	private double screenWidth;
	private Color backgroundColor;
	private int shadowRays;
	private int recursionLevel;
	
	public Camera(Point position, Point lookAtPoint, Point upVector,
			double screenDistance, double screenWidth) {
		
		this.position = position;
		this.lookAtPoint = lookAtPoint;
		this.upVector = upVector;
		this.screenDistance = screenDistance;
		this.screenWidth = screenWidth;
		this.backgroundColor = null;
		this.shadowRays = 0;
		this.recursionLevel = 0;
	}

	public Camera(Point position, Point lookAtPoint, Point upVector,
			double screenDistance, double screenWidth, Color backgroundColor,
			int shadowRays, int recursionLevel) {
		
		this.position = position;
		this.lookAtPoint = lookAtPoint;
		this.upVector = upVector;
		this.screenDistance = screenDistance;
		this.screenWidth = screenWidth;
		this.backgroundColor = backgroundColor;
		this.shadowRays = shadowRays;
		this.recursionLevel = recursionLevel;
	}

	public Point getPosition() {
		return position;
	}

	public void setPosition(Point position) {
		this.position = position;
	}

	public Point getLookAtPoint() {
		return lookAtPoint;
	}

	public void setLookAtPoint(Point lookAtPoint) {
		this.lookAtPoint = lookAtPoint;
	}

	@Override
	public String toString() {
		return "Camera [position=" + position + ", lookAtPoint=" + lookAtPoint
				+ ", upVector=" + upVector + ", screenDistance="
				+ screenDistance + ", screenWidth=" + screenWidth
				+ ", backgroundColor=" + backgroundColor + ", shadowRays="
				+ shadowRays + ", recursionLevel=" + recursionLevel + "]";
	}

	public Point getUpVector() {
		return upVector;
	}

	public void setUpVector(Point upVector) {
		this.upVector = upVector;
	}

	public double getScreenDistance() {
		return screenDistance;
	}

	public void setScreenDistance(double screenDistance) {
		this.screenDistance = screenDistance;
	}

	public double getScreenWidth() {
		return screenWidth;
	}

	public void setScreenWidth(double screenWidth) {
		this.screenWidth = screenWidth;
	}

	public Color getBackgroundColor() {
		return backgroundColor;
	}

	public void setBackgroundColor(Color backgroundColor) {
		this.backgroundColor = backgroundColor;
	}

	public int getShadowRays() {
		return shadowRays;
	}

	public void setShadowRays(int shadowRays) {
		this.shadowRays = shadowRays;
	}

	public int getRecursionLevel() {
		return recursionLevel;
	}

	public void setRecursionLevel(int recursionLevel) {
		recursionLevel = recursionLevel;
	}
	
	
	
	
	
	
}
