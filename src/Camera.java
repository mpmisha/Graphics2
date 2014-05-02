import java.awt.Image;


/**
 * @author Jenia
 *
 */
public class Camera {
	private Point position;
	private Point lookAtPoint;
	private Vector upVector,towards,right; 
	private float screenDistance;
	private float screenWidth;
	private float screenHeight;
	private Color backgroundColor;
	private int shadowRays;
	private int recursionLevel;
	private float angle;
	public float getAngle() {
		return angle;
	}
	public void setAngle(float angle) {
		this.angle = angle;
	}
	private Vector P1;
	private int imageWidth;
	
	public Camera(Point position, Point lookAtPoint, Vector upVector,
			float screenDistance, float screenWidth) {
		this.position = position;
		this.lookAtPoint = lookAtPoint;
		this.upVector = upVector;
		this.screenDistance = screenDistance;
		this.screenWidth = screenWidth;
		this.screenHeight= screenWidth;
		this.backgroundColor = null;
		this.shadowRays = 0;
		this.recursionLevel = 0;
		this.imageWidth=500;
		this.angle = (float)Math.atan((0.5f * screenWidth) / screenDistance);
		
		calcCameraVectors();
		
	}
	public Camera(Point position, Point lookAtPoint, Vector upVector,
			float screenDistance, float screenWidth, Color backgroundColor,
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
		this.angle = (float)Math.atan((0.5f * screenWidth) / screenDistance);
		
		calcCameraVectors();
	}
	private void calcCameraVectors(){
		Vector tempUp,tempRight,positionVector;
		
		this.towards = new Vector(this.lookAtPoint).vectorSubsract(new Vector(this.position));
		this.towards.Normalize();
		
		tempRight = this.upVector.crossProd(towards);
		tempRight.Normalize();
		
		tempUp = tempRight.crossProd(towards);
		tempUp.Normalize();
		
		this.upVector=tempUp;
		this.right=tempRight;
		
		positionVector = new Vector(position);
		
		P1 = positionVector.vectorAdd(towards.multiplyByScalar(screenDistance));
		P1 = P1.vectorSubsract(right.multiplyByScalar(screenDistance*((float)Math.tan(angle))));
		P1 = P1.vectorSubsract(upVector.multiplyByScalar(screenDistance*((float)Math.tan(angle))));
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
	public float getScreenDistance() {
		return screenDistance;
	}
	public void setScreenDistance(float screenDistance) {
		this.screenDistance = screenDistance;
	}
	public float getScreenWidth() {
		return screenWidth;
	}
	public void setScreenWidth(float screenWidth) {
		this.screenWidth = screenWidth;
	}
	public float getScreenHeight() {
		return screenHeight;
	}
	public void setScreenHeight(float screenHeight) {
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
	
	
	public Ray constructRayFomPixel(float x, float y) 
	{ 
		Vector p,v;
		
		float horizontalFactor = (float)(((float)(x+0.5f) / (float)this.imageWidth) * 2 * screenDistance * (float)Math.tan(angle));
		//align ray horizontally
		p  = P1.vectorAdd(right.multiplyByScalar(horizontalFactor));
		
		float verticalFactor = (float)(((float)(y+0.5f) / (float)this.imageWidth) * 2 * screenDistance * (float)Math.tan(angle));
		//align ray vertically
		p=p.vectorAdd(upVector.multiplyByScalar(verticalFactor));
		
		v = p.vectorSubsract(new Vector(position));
		v.Normalize();
			
		return new Ray(position, v);  
	}
}
