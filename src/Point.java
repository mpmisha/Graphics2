

public class Point {
	private float x;
	private float y;
	private float z;
	
	public Point(float x, float y, float z){
		this.x=x;
		this.y=y;
		this.z=z;
	}
	
	public Point(Point other){
		this.x = other.getX();
		this.y = other.getY();
		this.z = other.getZ();
	}
	
	public Point(Vector other){
		this.x = other.getX();
		this.y = other.getY();
		this.z = other.getZ();
	}
	
	
	
	public void mac(float s, Vector a) {
		x += s * a.getX();
        y += s * a.getY();
        z += s * a.getZ();
	}

	
	
	
	public Point(){
		this(0.0f,0.0f,0.0f);
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
	public void add(float d){
		this.x +=d;
		this.y +=d;
		this.z +=d;
	}
	
	
	@Override
	public String toString() {
		return "{" + x + "," + y + "," + z + "}";
	}

	public Vector Subsract(Point other) {
		return new Vector(this.getX()-other.getX(),this.getY()-other.getY(),this.getZ()-other.getZ());
	}

	
	public Point multiplyByMatrix(Matrix rotationMatrix) {
		int m = rotationMatrix.getNrows();
        int n = rotationMatrix.getNcols();
        float[] x = {this.getX(),this.getY(),this.getZ()};
        float[] y = new float[m];
        for (int i = 0; i < m; i++)
        	for (int j = 0; j < n; j++)
        		y[i] += ((float)rotationMatrix.getValueAt(i,j) * x[j]);
        return new Point(y[0],y[1],y[2]);
    }
	
	
	
	
}
