public class Vector {

	private float x,y,z;
	private float length;
	
	public Vector(float x, float y, float z) {
		super();
		this.x = x;
		this.y = y;
		this.z = z;
		this.length =(float) Math.sqrt(x*x + y*y + z*z);
	}
	
	public Vector(){
		this(0.0f,0.0f,0.0f);
		this.length = (float)Math.sqrt(x*x + y*y + z*z);
	}
	
	public Vector(Point dest, Point origin) {
    	x = dest.getX() - origin.getX();
    	y = dest.getY() - origin.getY();
    	z = dest.getZ() - origin.getZ();
    	this.length =(float) Math.sqrt(x*x + y*y + z*z);
    }
	
	public Vector(Point dest, Vector direction) {
    	x = dest.getX() - direction.getX();
    	y = dest.getY() - direction.getY();
    	z = dest.getZ() - direction.getZ();
    	this.length =(float) Math.sqrt(x*x + y*y + z*z);
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
		this.length =(float) Math.sqrt(x*x + y*y + z*z);
	}
	
	
	public Vector multiplyByScalar(float scalar){
		return (new Vector(this.getX()*scalar,this.getY()*scalar,this.getZ()*scalar));
	}
	
	public Vector vectorSubsract(Vector other){
		return new Vector(this.getX()-other.getX(),this.getY()-other.getY(),this.getZ()-other.getZ());	
	}
	public Vector vectorAdd(Vector other){
		return new Vector(this.getX()+other.getX(),this.getY()+other.getY(),this.getZ()+other.getZ());	
	}
	
	public float dotProdcut(Vector other) {
		return (this.x * other.getX() + this.y * other.getY() + this.z * other.getZ());
	}

	public Vector crossProd(Vector other) {	
		return new Vector(
                this.y * other.z - this.z * other.y,
                this.z * other.x - this.x * other.z,
                this.x * other.y - this.y * other.x);
	}
	public float getX() {
		return x;
	}
	public void setX(float x) {
		this.x = x;
	}
	public float getY() {
		return y;
	}
	public void setY(float y) {
		this.y = y;
	}
	public float getZ() {
		return z;
	}
	public void setZ(float z) {
		this.z = z;
	}
	public float getLength() {
		return length;
	}
	public void setLength(float length) {
		this.length = length;
	}
	public void scale(float d){		
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
	public float GetMagnitude() {
		return (float) Math.sqrt(x*x + y*y + z*z);
	}
	public void Normalize() {
		if(GetMagnitude() == 0)
			return;
		this.setX(this.getX()*(1/GetMagnitude()));
		this.setY(this.getY()*(1/GetMagnitude()));
		this.setZ(this.getZ()*(1/GetMagnitude()));
	}
}
