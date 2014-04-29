
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
	
	public Color ReturnColorBytes() {
		if(this.R > 1.0f) this.R = 1.0f;
		if(this.G > 1.0f) this.G = 1.0f;
		if(this.B > 1.0f) this.B = 1.0f;
		return new Color((float)Math.floor(this.R * 255), (float)Math.floor(this.G * 255), (float)Math.floor(this.B * 255));
	}
	
	public static Color getColor(Intersection hit){
		// a temporary imp of calculating the color.
		return hit.surface.getMaterial().getSpecularColor();
		
	}
	
	
	
	
}
