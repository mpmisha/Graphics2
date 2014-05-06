package RayTracing;
/**
 * This Class represents a point of intersection between a ray and a surface in the scene
 */
public class Intersection {
	
	float distance;
	Point pointOfIntersection;
	Surface surface;
	
	public float getDistance() {
		return distance;
	}


	public void setDistance(float distance) {
		this.distance = distance;
	}


	public Point getPointOfIntersection() {
		return pointOfIntersection;
	}


	public void setPointOfIntersection(Point pointOfIntersection) {
		this.pointOfIntersection = pointOfIntersection;
	}


	public Surface getSurface() {
		return surface;
	}


	public void setSurface(Surface surface) {
		this.surface = surface;
	}



	
	public Intersection(float distance, Point pointOfIntersection, Surface surface) 
	{
		this.distance = distance;
		this.pointOfIntersection = pointOfIntersection;
		this.surface = surface;
	}
	
	
	@Override
	public String toString() {
		return "[D:"+distance+" "+"P:"+pointOfIntersection+" S:"+surface+"]";
	}
}
