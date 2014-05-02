public class Matrix {

	private int nrows;
	private int ncols;
	private float[][] data;

	public Matrix(float[][] data) {
		this.data = data;
		this.nrows = data.length;
		this.ncols = data[0].length;
	}

	public Matrix(int rows, int cols) {
		this.nrows = rows;
		this.ncols = cols;
		data = new float[nrows][ncols];
	}

	public Matrix(Vector a, Vector b, Vector c) 
	{
		this.data = new float[3][a.AsArray().length];
		this.Set_row(0,a);
		this.Set_row(1,b);
		this.Set_row(2,c);
		this.nrows = data.length;
		this.ncols = data[0].length;
	}
	

	private void Set_row(int i, Vector b) {
		this.data[i] = b.AsArray();
	}

	public void setValueAt(int i, int j, float valueAt) {
		this.data[i][j] = valueAt;
	}

	public float getValueAt(int i, int j) {
		return this.data[i][j];
	}

	public int getNrows() {
		return nrows;
	}

	public void setNrows(int nrows) {
		this.nrows = nrows;
	}

	public int getNcols() {
		return ncols;
	}

	public void setNcols(int ncols) {
		this.ncols = ncols;
	}

	public float[][] getData() {
		return data;
	}

	public void setData(float[][] data) {
		this.data = data;
	}

	public static Matrix transpose(Matrix matrix) {
		Matrix traspose = new Matrix(matrix.getNcols(), matrix.getNrows());
		for (int i = 0; i < traspose.getNrows(); i++) {
			for (int j = 0; j < traspose.getNcols(); j++) {
				traspose.setValueAt(i, j, matrix.getValueAt(j, i));
			}
		}
		return traspose;
	}
	
	//under the assumption that the matrix is always square
	public static float determinant(Matrix matrix) {
		if (matrix.data.length == 2) {
			return (matrix.getValueAt(0, 0) * matrix.getValueAt(1, 1))
					- (matrix.getValueAt(0, 1) * matrix.getValueAt(1, 0));
		}
		float sum = 0.0f;
		for (int i = 0; i < matrix.getNcols(); i++) {
			sum +=Math.pow(-1, i) * matrix.getValueAt(0, i)
					* determinant(createSubMatrix(matrix, 0, i));
		}
		return sum;
	}

	public static Matrix createSubMatrix(Matrix matrix, int excluding_row,
			int excluding_col) {
		Matrix mat = new Matrix(matrix.getNrows() - 1, matrix.getNcols() - 1);
		int r = -1;
		for (int i = 0; i < matrix.getNrows(); i++) {
			if (i == excluding_row)
				continue;
			r++;
			int c = -1;
			for (int j = 0; j < matrix.getNcols(); j++) {
				if (j == excluding_col)
					continue;
				mat.setValueAt(r, ++c, matrix.getValueAt(i, j));
			}
		}
		return mat;
	}

//	public static Matrix cofactor(Matrix matrix) {
//		Matrix mat = new Matrix(matrix.getNrows(), matrix.getNcols());
//		for (int i = 0; i < matrix.getNrows(); i++) {
//			for (int j = 0; j < matrix.getNcols(); j++) {
//				mat.setValueAt( i , j, sign(i) * (float)Math.pow(-1, j) * determinant(createSubMatrix(matrix, i, j)));
//			}
//		}
//		return mat;
//	}
//	
//	private static float sign(int i) {
//		return (i==0) ? 1 : (i/Math.abs(i));
//	}

	public static Matrix Inverse(Matrix A) {
		/*
		 * This Function computes the inverse of a given Matrix under the assumption that the matrix is 3x3 and his determinant differs from 0  
		 * */
		double a = A.getValueAt(0, 0)*( A.getValueAt(1, 1)*A.getValueAt(2, 2) - A.getValueAt(2, 1)*A.getValueAt(1, 2) );
		double b = A.getValueAt(0, 1)*( A.getValueAt(1, 0)*A.getValueAt(2, 2) - A.getValueAt(2, 0)*A.getValueAt(1, 2) );
		double c = A.getValueAt(0, 2)*( A.getValueAt(1, 0)*A.getValueAt(2, 1) - A.getValueAt(2, 0)*A.getValueAt(1, 1) );
		float det = (float)(a - b + c);

		Matrix B = Matrix.transpose(A);
		
		
		Vector a1 = new Vector(B.getValueAt(1, 1)*B.getValueAt(2, 2) - B.getValueAt(2, 1)*B.getValueAt(1, 2),
				-(B.getValueAt(1, 0)*B.getValueAt(2, 2) - B.getValueAt(2, 0)*B.getValueAt(1, 2)),
				 B.getValueAt(1, 0)*B.getValueAt(2, 1) - B.getValueAt(2, 0)*B.getValueAt(1, 1));
		
		Vector a2 = new Vector(-(B.getValueAt(0, 1)*B.getValueAt(2, 2) - B.getValueAt(2, 1)*B.getValueAt(0, 2)),
				B.getValueAt(0, 0)*B.getValueAt(2, 2) - B.getValueAt(2, 0)*B.getValueAt(0, 2),
				-(B.getValueAt(0, 0)*B.getValueAt(2, 1) - B.getValueAt(2, 0)*B.getValueAt(0, 1)));
		
		Vector a3 = new Vector(B.getValueAt(0, 1)*B.getValueAt(1, 2) - B.getValueAt(1, 1)*B.getValueAt(0, 2),
				 -(B.getValueAt(0, 0)*B.getValueAt(1, 2) - B.getValueAt(1, 0)*B.getValueAt(1, 2)),
				 B.getValueAt(0, 0)*B.getValueAt(1, 1) - B.getValueAt(1, 0)*B.getValueAt(0, 1));

		Matrix C = new Matrix(a1.multiplyByScalar(1/det), a2.multiplyByScalar(1/det), a3.multiplyByScalar(1/det));
		return C;

	}
	

	public Matrix multiplyByConstant(float d) {
		Matrix newMatrix = new Matrix(this.data);
		for (int i = 0; i < this.data.length; i++) {
			for (int j = 0; j < data[0].length; j++) {
				newMatrix.data[i][j]=newMatrix.getValueAt(i, j)*d;
			}
		}
		return newMatrix;
	}
	
	

	

}
