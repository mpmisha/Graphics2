package RayTracing;

public class Material {
	
	private Color DiffuseColor;
	private Color SpecularColor;
	private float PhongCoeffticient;
	private Color ReflectionColor;
	private float Transparency;
	
	public Material(Color diffuseColor, Color specularColor,
			float phongCoeffticient, Color reflectionColor, float transparency) {
		DiffuseColor = diffuseColor;
		SpecularColor = specularColor;
		PhongCoeffticient = phongCoeffticient;
		ReflectionColor = reflectionColor;
		Transparency = transparency;
	}
	//constructor for first part
	public Material(Color diffuseColor, Color specularColor,
			float phongCoeffticient) {
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
	public float getPhongCoeffticient() {
		return PhongCoeffticient;
	}
	public void setPhongCoeffticient(float phongCoeffticient) {
		PhongCoeffticient = phongCoeffticient;
	}
	public Color getReflectionColor() {
		return ReflectionColor;
	}
	public void setReflectionColor(Color reflectionColor) {
		ReflectionColor = reflectionColor;
	}
	public float getTransparency() {
		return Transparency;
	}
	public void setTransparency(float transparency) {
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
