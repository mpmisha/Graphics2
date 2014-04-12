
public class Vector {

	private double x,y,z;
	private double length;
	
	public Vector(double x, double y, double z) {
		super();
		this.x = x;
		this.y = y;
		this.z = z;
		this.length = Math.sqrt(x*x + y*y + z*z);
	}
	public Vector(){
		this(0.0,0.0,0.0);
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

	public double getLength() {
		return length;
	}

	public void setLength(double length) {
		this.length = length;
	}
	
	
	
	
	
	
	
	
}
