
public class Ray {


	private Point origin;
	private Vector diraction;
	private Vector imagePoint;
	
	public Ray(Point origin, Vector diraction) {
		super();
		this.origin = origin;
		this.diraction = diraction;
	}
	
	public Ray(Point origin, Vector diraction,Vector imagePoint) {
		super();
		this.origin = origin;
		this.diraction = diraction;
		this.imagePoint = imagePoint;
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
	
	
	
}
