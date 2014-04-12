
public class Plane extends Surface {
	private int offset;

	public Plane(Material material, Point centerPoint, int mat_idx, int offset) {
		super(material, centerPoint, mat_idx);
		this.offset = offset;
	}

	public int getOffset() {
		return offset;
	}

	public void setOffset(int offset) {
		this.offset = offset;
	}

	@Override
	public String toString() {
		return "Plane [offset=" + offset + "]";
	}
	
	
}
