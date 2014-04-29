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
		this.towards = new Vector(this.lookAtPoint).vectorSubsract(new Vector(this.position));
		this.towards.Normalize();
		
		Vector tempUp = this.upVector.crossProd(towards);
		tempUp.Normalize();
		
		Vector tempRight = tempUp.crossProd(towards);
		tempRight.Normalize();
		
		this.upVector=tempRight;
		this.right=tempUp;
		
		Vector positionVector = new Vector(position);
		
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
	public Ray constructRayFomPixel(float x, float y) { //TODO: Michael Need to Complete according to Barak's code
		
		float rightCoeff = (float)(((float)(x+0.5f) / (float)this.imageWidth) * 2 * screenDistance * (float)Math.tan(angle));
		float upCoeff = (float)(((float)(y+0.5f) / (float)this.imageWidth) * 2 * screenDistance * (float)Math.tan(angle));
		
		Vector p  = P1.vectorAdd(right.multiplyByScalar(rightCoeff));
		p=this.P1.vectorAdd(upVector.multiplyByScalar(upCoeff));
		
//		Vector p  = this.P1.vectorAdd(upVector.multiplyByScalar(upCoeff));
//		p=this.P1.vectorAdd(right.multiplyByScalar(rightCoeff));
		
		
		
		Vector v = p.vectorSubsract(new Vector(position));
		v.Normalize();
		return new Ray(position, v, p);  //TODO:until here all is good!
	}
}
