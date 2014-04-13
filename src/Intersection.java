/**
 * This Class represents a point of intersection between a ray and a surface in the scene
 */
public class Intersection {
	double distance;
	Point pointOfIntersection;
	Surface surface;
	
	
	public Intersection(double distance, Point pointOfIntersection, Surface surface) 
	{
		this.distance = distance;
		this.pointOfIntersection = pointOfIntersection;
		this.surface = surface;
	}
	
}
