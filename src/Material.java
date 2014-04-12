
public class Material {
	
	private Color DiffuseColor;
	private Color SpecularColor;
	private double PhongCoeffticient;
	private Color ReflectionColor;
	private double Transparency;
	
	public Material(Color diffuseColor, Color specularColor,
			double phongCoeffticient, Color reflectionColor, double transparency) {
		DiffuseColor = diffuseColor;
		SpecularColor = specularColor;
		PhongCoeffticient = phongCoeffticient;
		ReflectionColor = reflectionColor;
		Transparency = transparency;
	}
	//constructor for first part
	public Material(Color diffuseColor, Color specularColor,
			double phongCoeffticient) {
		this(diffuseColor,specularColor,phongCoeffticient,null,0);
		
	}
	public Color getDiffuseColor() {
		return DiffuseColor;
	}
	public void setDiffuseColor(Color diffuseColor) {
		DiffuseColor = diffuseColor;
	}
	public Color getSpecularColor() {
		return SpecularColor;
	}
	public void setSpecularColor(Color specularColor) {
		SpecularColor = specularColor;
	}
	public double getPhongCoeffticient() {
		return PhongCoeffticient;
	}
	public void setPhongCoeffticient(double phongCoeffticient) {
		PhongCoeffticient = phongCoeffticient;
	}
	public Color getReflectionColor() {
		return ReflectionColor;
	}
	public void setReflectionColor(Color reflectionColor) {
		ReflectionColor = reflectionColor;
	}
	public double getTransparency() {
		return Transparency;
	}
	public void setTransparency(double transparency) {
		Transparency = transparency;
	}
	@Override
	public String toString() {
		return "Material [DiffuseColor=" + DiffuseColor + ", SpecularColor="
				+ SpecularColor + ", PhongCoeffticient=" + PhongCoeffticient
				+ ", ReflectionColor=" + ReflectionColor + ", Transparency="
				+ Transparency + "]";
	}
	
	
}
