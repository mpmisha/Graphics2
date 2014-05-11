package RayTracing;

public class Ray {


	private Point origin;
	private Vector diraction;
	
	
	public Ray(Point origin, Vector diraction) {
		super();
		this.origin = origin;
		this.diraction = diraction.Normalize();
	}
		
	public Ray(){
		this(new Point(),new Vector());
	}

	public Point getOrigin() {
		return origin;
	}

	public void setOrigin(Point origin) {
		this.origin = origin;
	}

	public Vector getDiraction() {
		return diraction;
	}

	public void setDiraction(Vector diraction) {
		this.diraction = diraction;
	}
	
	
	@Override
	public String toString() {
		return "[o:"+origin+" "+"d:"+diraction+"]";
	}
	
	
}
