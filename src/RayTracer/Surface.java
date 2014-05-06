package RayTracer;



public abstract class Surface {
	private Material material;
	private Point centerPoint;
	private int mat_idx;
	private int id;
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Surface(Material material, Point centerPoint, int mat_idx) {

		this.material = material;
		this.centerPoint = centerPoint;
		this.mat_idx = mat_idx;
	
	}
	
	public Surface(Material material, int mat_idx) {
		this.material = material;
		this.mat_idx = mat_idx;
	}

	public Material getMaterial() {
		return material;
	}

	public void setMaterial(Material material) {
		this.material = material;
	}

	public Point getCenterPoint() {
		return centerPoint;
	}

	public void setCenterPoint(Point centerPoint) {
		this.centerPoint = centerPoint;
	}

	public int getMat_idx() {
		return mat_idx;
	}

	public void setMat_idx(int mat_idx) {
		this.mat_idx = mat_idx;
	}

	public abstract Intersection findIntersection(Ray ray);

	public abstract Vector getNormal(Point pointOfIntersection);
	
	
	
}
