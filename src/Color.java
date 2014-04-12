
public class Color {
	private double R;
	private double G;
	private double B;
	
	public Color(double r, double g, double b) {
		R = r;
		G = g;
		B = b;
	}
	public Color(){
		this(0,0,0);
	}
	public double getR() {
		return R;
	}
	public void setR(double r) {
		R = r;
	}
	public double getG() {
		return G;
	}
	public void setG(double g) {
		G = g;
	}
	public double getB() {
		return B;
	}
	public void setB(double b) {
		B = b;
	}
	@Override
	public String toString() {
		return "Color [" + R + "," + G + "," + B + "]";
	}
	
	
	
	
}
