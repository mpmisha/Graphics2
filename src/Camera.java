import java.awt.Image;


/**
 * @author Jenia
 *
 */
public class Camera {
	private Point position;
	private Point lookAtPoint;
	private Vector upVector; 
	private double screenDistance;
	private double screenWidth;
	private double screenHeight;
	private Color backgroundColor;
	private int shadowRays;
	private int recursionLevel;
	
	public Camera(Point position, Point lookAtPoint, Vector upVector,
			double screenDistance, double screenWidth) {
		
		this.position = position;
		this.lookAtPoint = lookAtPoint;
		this.upVector = upVector;
		this.screenDistance = screenDistance;
		this.screenWidth = screenWidth;
		this.screenHeight= screenWidth;
		this.backgroundColor = null;
		this.shadowRays = 0;
		this.recursionLevel = 0;
	}
	public Camera(Point position, Point lookAtPoint, Vector upVector,
			double screenDistance, double screenWidth, Color backgroundColor,
			int shadowRays, int recursionLevel) {
		
		this.position = position;
		this.lookAtPoint = lookAtPoint;
		this.upVector = upVector;
		this.screenDistance = screenDistance;
		this.screenWidth = screenWidth;
		this.screenHeight= screenWidth;
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
	public Vector getUpVector() {
		return upVector;
	}
	public void setUpVector(Vector upVector) {
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
	public double getScreenHeight() {
		return screenHeight;
	}
	public void setScreenHeight(double screenHeight) {
		this.screenHeight = screenHeight;
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
		this.recursionLevel = recursionLevel;
	}
	/*
	 * by default camera position is set to be the (0,0,0) of the whole "world"
	 * so we want to cast a ray from the eye to the position x,y of the window
	 * which is defined by:
	 * the distance from the camera eye
	 * the width 
	 * lookAt point
	 * and up vector
	 * 
	 * */
	public Ray constructRayFomPixel(double x, double y) {
		Point P0 = this.position;
		Vector towards = new Vector(this.lookAtPoint);
		Vector up = this.upVector;
		Vector right = towards.crossProd(up);
		
		towards.scale(this.screenDistance); 		
		right.scale(x-(double) this.screenWidth/2);	
		up.scale(y-(double) this.screenHeight/2);		
		
		lookAtPoint.add(towards);	//move to screen
		lookAtPoint.add(up);		//move up
		lookAtPoint.add(right);		//move right
		
									//now were at the x,y point on the screen
		Vector rayVector = new Vector(lookAtPoint,P0); 
		return new Ray(P0,rayVector);
	}
}
