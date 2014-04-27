

public class Point {
	private double x;
	private double y;
	private double z;
	
	public Point(double x, double y, double z){
		this.x=x;
		this.y=y;
		this.z=z;
	}
	
	public Point(Point other){
		this.x = other.x;
		this.y = other.y;
		this.z = other.z;
	}
	
	
	public void mac(double s, Vector a) {
		x += s * a.getX();
        y += s * a.getY();
        z += s * a.getZ();
	}

	
	
	
	public Point(){
		this(0,0,0);
	}

	
	
	public double getX() {
		return x;
	}

	public void setX(double x) {
		this.x = x;
	}

	public double getY() {
		return y;
	}

	public void setY(double y) {
		this.y = y;
	}

	public double getZ() {
		return z;
	}

	public void setZ(double z) {
		this.z = z;
	}

	public void add(Vector v){
		this.x +=v.getX();
		this.y +=v.getY();
		this.z +=v.getZ();
	}
	
	public void add(Point p){
		this.x +=p.getX();
		this.y +=p.getY();
		this.z +=p.getZ();
	}
	public void add(double d){
		this.x +=d;
		this.y +=d;
		this.z +=d;
	}
	
	
	@Override
	public String toString() {
		return "Point [" + x + "," + y + "," + z + "]";
	}
	
	
	
	
	
}
