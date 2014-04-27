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
		this.length = Math.sqrt(x*x + y*y + z*z);
	}
	
	public Vector(Point dest, Point origin) {
    	x = dest.getX() - origin.getX();
    	y = dest.getY() - origin.getY();
    	z = dest.getZ() - origin.getZ();
    	this.length = Math.sqrt(x*x + y*y + z*z);
    }
	
	public Vector(Point dest, Vector direction) {
    	x = dest.getX() - direction.getX();
    	y = dest.getY() - direction.getY();
    	z = dest.getZ() - direction.getZ();
    	this.length = Math.sqrt(x*x + y*y + z*z);
    }
	
	
	public Vector(Point p){
		x =p.getX();
		y= p.getY();
		z=p.getZ();
	}
	
	public Vector(Vector other){
		this.x = other.x;
		this.y = other.y;
		this.z = other.z;
		this.length = Math.sqrt(x*x + y*y + z*z);
	}
	
	
	public void multiplyByScalar(double scalar){
		this.x*=scalar;
		this.y*=scalar;
		this.z*=scalar;
	}

	public double dotProdcut(Vector other) {
		return x * other.x + y * other.y + z * other.z;
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
	
	public void scale(double d){		
		this.x *= d;
		this.y *= d;
		this.z *= d;
	}
	
	public void scale(Vector v){
		this.x *=v.getX();
		this.y *=v.getY();
		this.z *=v.getZ();		
	}
	public void scale(Point p){
		this.x *=p.getX();
		this.y *=p.getY();
		this.z *=p.getZ();		
	}
	
	public Vector crossProd(Vector other) {	
		return new Vector(
                this.y * other.z - this.z * other.y,
                this.z * other.x - this.x * other.z,
                this.x * other.y - this.y * other.x);
	}
}
