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
	public Ray constructRayFomPixel(int x, int y) {
		Vector scaled_x_y;
		double xMove,yMove;
		Ray result = new Ray();
		//center of window
		Point lookAt_x_y = new Point(lookAtPoint.getX(),lookAtPoint.getY(),lookAtPoint.getZ());
		//find coordiantes of x,y
		
		xMove = lookAt_x_y.getX()-(screenWidth/2-x);
		yMove = lookAt_x_y.getY()-(screenWidth/2-y);
		
		scaled_x_y = new Vector(xMove,yMove,0.0);
		result.setOrigin(position);
		result.setDiraction(scaled_x_y);
		
		return result;
	}
	
	
	
	
	
	
}
