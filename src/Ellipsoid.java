
public class Ellipsoid extends Surface{
	private int[][] rotationMatrix = new int[3][3];

	public Ellipsoid(Material material, Point centerPoint, int mat_idx,
			int[][] rotationMatrix) {
		super(material, centerPoint, mat_idx);
		this.rotationMatrix = rotationMatrix;
	}

	public int[][] getRotationMatrix() {
		return rotationMatrix;
	}

	public void setRotationMatrix(int[][] rotationMatrix) {
		this.rotationMatrix = rotationMatrix;
	}

	@Override
	public Intersection findIntersection(Ray ray) {
		
		
		return null;
	}
	
	
	
}
