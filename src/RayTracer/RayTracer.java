package RayTracer;

import java.awt.Transparency;
import java.awt.color.*;
import java.awt.image.*;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;

public class RayTracer {
	public static final boolean DEBUG=false;
	
	
	public int imageWidth;
	public int imageHeight;
	public Camera camera;
	public ArrayList<Surface> surfaceList;
	public ArrayList<Material> materialList;
	public ArrayList<Light> lightList;

	/**
	 * Runs the ray tracer. Takes scene file, output image file and image size as input.
	 */
	public static void main(String[] args) {
		//test comment
		//and another one
		try {

			RayTracer tracer = new RayTracer();
			
                        // Default values:	
			tracer.imageWidth = 500;
			tracer.imageHeight = 500;

			if (args.length < 2)
				throw new RayTracerException("Not enough arguments provided. Please specify an input scene file and an output image file for rendering.");

			String sceneFileName = args[0];
			String outputFileName = args[1];

			if (args.length > 3)
			{
				if (!DEBUG) tracer.imageWidth = Integer.parseInt(args[2]);
				if (!DEBUG) tracer.imageHeight = Integer.parseInt(args[3]);
			}


			// Parse scene file:
			tracer.parseScene(sceneFileName);

			// Render scene:
			if (DEBUG) System.out.println("starting renderScence");
			tracer.renderScene(outputFileName);

//		} catch (IOException e) {
//			System.out.println(e.getMessage());
		} catch (RayTracerException e) {
			System.out.println(e.getMessage());
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}


	}

	/**
	 * Parses the scene file and creates the scene. Change this function so it generates the required objects.
	 */
	public void parseScene(String sceneFileName) throws IOException, RayTracerException
	{
		FileReader fr = new FileReader(sceneFileName);
		
		BufferedReader r = new BufferedReader(fr);
		String line = null;
		int lineNum = 0,surfaceCount=0;
		System.out.println("Started parsing scene file " + sceneFileName);
		surfaceList= new ArrayList<Surface>();
		materialList= new ArrayList<Material>();
		lightList = new ArrayList<Light>();

		while ((line = r.readLine()) != null)
		{
			line = line.trim();
			++lineNum;

			if (line.isEmpty() || (line.charAt(0) == '#'))
			{  // This line in the scene file is a comment
				continue;
			}
			else
			{
				String code = line.substring(0, 3).toLowerCase();
				// Split according to white space characters:
				String[] params = line.substring(3).trim().toLowerCase().split("\\s+");

				if (code.equals("cam"))
				{
					Point positionPoint = new Point(Float.parseFloat(params[0]),Float.parseFloat(params[1]),Float.parseFloat(params[2]));
					Point lookAtPoint = new Point(Float.parseFloat(params[3]),Float.parseFloat(params[4]),Float.parseFloat(params[5]));
					Vector upVector = new Vector(Float.parseFloat(params[6]),Float.parseFloat(params[7]),Float.parseFloat(params[8]));
					float screenDist = Float.parseFloat(params[9]);
					float screenWidth = Float.parseFloat(params[10]);
					
					camera = new Camera(positionPoint,lookAtPoint,upVector,screenDist,screenWidth);
					
                                        // Add code here to parse camera parameters

					System.out.println(String.format("Parsed camera parameters (line %d)", lineNum));
				}
				else if (code.equals("set"))
				{
                                        // Add code here to parse general settings parameters
					Color bgColor = new Color(Float.parseFloat(params[0]),Float.parseFloat(params[1]),Float.parseFloat(params[2]));
					int shadowRays = Integer.parseInt(params[3]);
					int recLevel = Integer.parseInt(params[4]);
					camera.setBackgroundColor(bgColor);
					camera.setShadowRays(shadowRays);
					camera.setRecursionLevel(recLevel);
					if (DEBUG) System.out.println(camera.toString());
					System.out.println(String.format("Parsed general settings (line %d)", lineNum));
				}
				else if (code.equals("mtl"))
				{
					
					Color diffuseColor = new Color(Float.parseFloat(params[0]),Float.parseFloat(params[1]),Float.parseFloat(params[2]));
					Color specularColor = new Color(Float.parseFloat(params[3]),Float.parseFloat(params[4]),Float.parseFloat(params[5]));
					Color refColor = new Color(Float.parseFloat(params[6]),Float.parseFloat(params[7]),Float.parseFloat(params[8]));
					float phongCoeffticient = Float.parseFloat(params[9]);
					float trans = Float.parseFloat(params[10]);
					
					Material material = new Material(diffuseColor,specularColor,phongCoeffticient,refColor,trans);
					materialList.add(material);
                                       
					if (DEBUG)System.out.println(material.toString());
					System.out.println(String.format("Parsed material (line %d)", lineNum));
				}
				else if (code.equals("sph"))
				{
					
					Point centerPoint = new Point(Float.parseFloat(params[0]),Float.parseFloat(params[1]),Float.parseFloat(params[2]));
					float radius = Float.parseFloat(params[3]);
					int mat_idx = Integer.parseInt(params[4]);
					//new sphere - add material info later (maybe!)
					Sphere sphere = new Sphere(null, centerPoint, mat_idx, radius);
					sphere.setId(surfaceCount++);
					
					surfaceList.add(sphere);
					if (DEBUG) System.out.println(sphere.toString());
					System.out.println(String.format("Parsed sphere (line %d)", lineNum));
				}
				else if (code.equals("pln"))
				{
					Vector normal = new Vector(Float.parseFloat(params[0]),Float.parseFloat(params[1]),Float.parseFloat(params[2]));
					int offset = Integer.parseInt(params[3]);
					int mat_idx = Integer.parseInt(params[4]);
					Plane plane = new Plane(null, normal, mat_idx, offset);
					plane.setId(surfaceCount++);
					surfaceList.add(plane);
                                        
					if (DEBUG) System.out.println(plane.toString());
					System.out.println(String.format("Parsed plane (line %d)", lineNum));
				}
				else if (code.equals("elp"))
				{
					Matrix rotationMatrix = new Matrix(3,3);
					Point centerPoint = new Point(Float.parseFloat(params[0]),Float.parseFloat(params[1]),Float.parseFloat(params[2]));
					for (int i = 0; i < rotationMatrix.getNcols(); i++) {
						for (int j = 0; j < rotationMatrix.getNrows(); j++) {
							rotationMatrix.setValueAt(i,j, Float.parseFloat(params[3+3*i+j]));
						}
					}
					int mat_idx = Integer.parseInt(params[12]);
					Ellipsoid ellipse = new Ellipsoid(null, centerPoint, rotationMatrix, mat_idx);
					ellipse.setId(surfaceCount++);
					surfaceList.add(ellipse);
					if (DEBUG) System.out.println(ellipse.toString());
					System.out.println(String.format("Parsed ellipsoid (line %d)", lineNum));
				}
				else if (code.equals("lgt"))
				{
					Point position = new Point(Float.parseFloat(params[0]),Float.parseFloat(params[1]),Float.parseFloat(params[2]));
					Color color =new Color(Float.parseFloat(params[3]),Float.parseFloat(params[4]),Float.parseFloat(params[5]));
					float specularIntensity = Float.parseFloat(params[6]);
					float shadowIntensity = Float.parseFloat(params[7]);
					float radius = Float.parseFloat(params[8]);
					
					Light light = new Light(position, color, specularIntensity, shadowIntensity, radius);
					
					lightList.add(light);
					if (DEBUG) System.out.println(light.toString());
					System.out.println(String.format("Parsed light (line %d)", lineNum));
				}
				else
				{
					System.out.println(String.format("ERROR: Did not recognize object: %s (line %d)", code, lineNum));
				}
			}
		}

                // It is recommended that you check here that the scene is valid,
                // for example camera settings and all necessary materials were defined.
		r.close();
		
		//merge each surface with its material
		for (int i = 0; i < surfaceList.size(); i++) {
			surfaceList.get(i).setMaterial(materialList.get(surfaceList.get(i).getMat_idx()-1));
		}
		
		System.out.println("Finished parsing scene file " + sceneFileName);
	}

	/**
	 * Renders the loaded scene and saves it to the specified file location.
	 */
	public void renderScene(String outputFileName)
	{
		long startTime = System.currentTimeMillis();
		Color colorVector;
		// Create a byte array to hold the pixel data:
		byte[] rgbData = new byte[this.imageWidth * this.imageHeight * 3];
		Color hitColor = new Color();	
			for (int x = 0; x < this.imageHeight; x++) {
				for (int y = 0; y < this.imageWidth; y++) {
					Ray ray = camera.constructRayFomPixel((float)x,(float)y);
					Intersection hit = findNearestIntersection(ray);
					if (hit == null) 
					{
						hitColor = camera.getBackgroundColor();
					}
					else
					{
						hitColor = Color.getColor(hit,lightList,ray);	
					}
					colorVector = hitColor.ReturnColorBytes();
					rgbData[y*this.imageWidth*3 + x*3 + 0] = (byte) Color.saturate(colorVector.getR());
					rgbData[y*this.imageWidth*3 + x*3 + 1] = (byte) Color.saturate(colorVector.getG());
					rgbData[y*this.imageWidth*3 + x*3 + 2] = (byte) Color.saturate(colorVector.getB());
				}
			}

		long endTime = System.currentTimeMillis();
		Long renderTime = endTime - startTime;

                // The time is measured for your own conveniece, rendering speed will not affect your score
                // unless it is exceptionally slow (more than a couple of minutes)
		System.out.println("Finished rendering scene in " + renderTime.toString() + " milliseconds.");

                // This is already implemented, and should work without adding any code.
		saveImage(this.imageWidth, rgbData, outputFileName);

		System.out.println("Saved file " + outputFileName);

	}
	
	public Intersection findNearestIntersection(Ray ray){
		
		Intersection tempInter, minInter = new Intersection(Float.MAX_VALUE, null, null);
		for (Surface surface :  surfaceList) {
			tempInter = surface.findIntersection(ray);
			if (tempInter != null)
			{
				if (tempInter.getDistance() < minInter.getDistance())
				{
					minInter=tempInter;
				}
			}
			
		}
		
		if (minInter.getPointOfIntersection() == null) return null;
		else return minInter;
	}





	//////////////////////// FUNCTIONS TO SAVE IMAGES IN PNG FORMAT //////////////////////////////////////////

	/*
	 * Saves RGB data as an image in png format to the specified location.
	 */
	public static void saveImage(int width, byte[] rgbData, String fileName)
	{
		try {

			BufferedImage image = bytes2RGB(width, rgbData);
			ImageIO.write(image, "png", new File(fileName));

		} catch (IOException e) {
			System.out.println("ERROR SAVING FILE: " + e.getMessage());
		}

	}

	/*
	 * Producing a BufferedImage that can be saved as png from a byte array of RGB values.
	 */
	public static BufferedImage bytes2RGB(int width, byte[] buffer) {
	    int height = buffer.length / width / 3;
	    ColorSpace cs = ColorSpace.getInstance(ColorSpace.CS_sRGB);
	    ColorModel cm = new ComponentColorModel(cs, false, false,
	            Transparency.OPAQUE, DataBuffer.TYPE_BYTE);
	    SampleModel sm = cm.createCompatibleSampleModel(width, height);
	    DataBufferByte db = new DataBufferByte(buffer, width * height);
	    WritableRaster raster = Raster.createWritableRaster(sm, db, null);
	    BufferedImage result = new BufferedImage(cm, raster, false, null);

	    return result;
	}

	public static class RayTracerException extends Exception {
		public RayTracerException(String msg) {  super(msg); }
	}
		
	
}
