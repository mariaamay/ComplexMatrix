public class ComplexMatrix {
    private int n, m;
    private ComplexNumber[][] matrix;

    public ComplexMatrix() {
        this.n = 1;
        this.m = 1;
        this.matrix = new ComplexNumber[n][m];
        matrix[0][0] = new ComplexNumber(0, 0);
    }

    public ComplexMatrix(int n, int m) {
        this.n = n;
        this.m = m;
        this.matrix = new ComplexNumber[n][m];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                matrix[i][j] = new ComplexNumber(0, 0);
            }
        }
    }

    public ComplexMatrix(ComplexNumber[][] matrix) {
        this.n = matrix.length;
        this.m = matrix[0].length;
        this.matrix = matrix;
    }

    public int getN() { return n; }
    public int getM() { return m; }

    public ComplexNumber getCell(int i, int j) {
        if (i < 0 || i >= n || j < 0 || j >= m) {
           throw new IndexOutOfBoundsException("Index is out of bounds");
        }
        return matrix[i][j];
    }

    public void setCell(ComplexNumber value, int i, int j) {
        if (i < 0 || i >= n || j < 0 || j >= m) {
            throw new IndexOutOfBoundsException("Index is out of bounds");
        }
        matrix[i][j] = value;
    }

    public void setSize(int n, int m) {
        if (n <= 0 || m <= 0) {
            throw new IllegalArgumentException("n and m must be positive");
        }
        ComplexNumber[][] newMatrix = new ComplexNumber[n][m];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (i < this.n && j < this.m) {
                    newMatrix[i][j] = this.matrix[i][j];
                }
                else {
                    newMatrix[i][j] = new ComplexNumber(0, 0);
                }
            }
        }
        this.n = n;
        this.m = m;
        this.matrix = newMatrix;
    }

    public ComplexMatrix add(ComplexMatrix other) {
        if (this.n != other.n || this.m != other.m) {
            throw new IllegalArgumentException("Cannot add complex matrices with different dimensions");
        }
        ComplexMatrix result = new ComplexMatrix(this.n, this.m);
        for (int i = 0; i < this.n; i++) {
            for (int j = 0; j < this.m; j++) {
                result.matrix[i][j] = this.matrix[i][j].add(other.matrix[i][j]);
            }
        }
        return result;
    }

    public ComplexMatrix subtract(ComplexMatrix other) {
        if (this.n != other.n || this.m != other.m) {
            throw new IllegalArgumentException("Cannot subtract complex matrices with different dimensions");
        }
        ComplexMatrix result = new ComplexMatrix(this.n, this.m);
        for (int i = 0; i < this.n; i++) {
            for (int j = 0; j < this.m; j++) {
                result.matrix[i][j] = this.matrix[i][j].subtract(other.matrix[i][j]);
            }
        }
        return result;
    }

    public ComplexMatrix multiply(ComplexMatrix other) {
        if (this.m != other.n) {
            throw new IllegalArgumentException("The number of columns of the first matrix must equal the number of rows of the second one");
        }
        ComplexMatrix result = new ComplexMatrix(this.n, other.m);
        for (int i = 0; i < this.n; i++) {
            for (int j = 0; j < other.m; j++) {
                ComplexNumber res = new ComplexNumber(0, 0);
                for (int k = 0; k < this.m; k++) {
                    res = res.add(this.matrix[i][k].multiply(other.matrix[k][j]));
                }
                result.matrix[i][j] = res;
            }
        }
        return result;
    }

    public ComplexMatrix multiplyByNumber(ComplexNumber num) {
        ComplexMatrix result = new ComplexMatrix(this.n, this.m);
        for (int i = 0; i < this.n; i++) {
            for (int j = 0; j < this.m; j++) {
                result.matrix[i][j] = this.matrix[i][j].multiply(num);
            }
        }
        return result;
    }

    public ComplexMatrix transpose() {
        ComplexMatrix result = new ComplexMatrix(this.m, this.n);
        for (int i = 0; i < this.n; i++) {
            for (int j = 0; j < this.m; j++) {
                result.matrix[j][i] = this.matrix[i][j];
            }
        }
        return result;
    }

    public ComplexNumber determinant() {
        if (this.n != this.m) {
            throw new IllegalArgumentException("The matrix must be square to calculate determinant");
        }
        return calculateDeterminant(this.matrix);

    }

    private ComplexNumber calculateDeterminant(ComplexNumber[][] matrix) {
        int size = matrix.length;
        if (size == 1) {
            return matrix[0][0];
        }
        if (size == 2) {
            return matrix[0][0].multiply(matrix[1][1]).subtract(matrix[0][1].multiply(matrix[1][0]));
        }
        ComplexNumber det = new ComplexNumber(0, 0);
        for (int i = 0; i < size; i++) {
            det = det.add(matrix[0][i].multiply(cofactor(matrix, 0, i)));
        }
        return det;
    }

    private ComplexNumber cofactor(ComplexNumber[][] matrix, int i, int j) {
        ComplexNumber[][] subMatrix = new ComplexNumber[matrix.length - 1][matrix[0].length - 1];
        int subRow = -1;
        for (int k = 0; k < matrix.length; k++) {
            if (k == i) continue;
            subRow++;
            int subCol = 0;
            for (int l = 0; l < matrix[0].length; l++) {
                if (j == l) continue;
                subMatrix[subRow][subCol++] = matrix[k][l];
            }
        }
        ComplexNumber cofactor = calculateDeterminant(subMatrix);
        return ((i + j) % 2 == 0) ? cofactor : cofactor.multiply(new ComplexNumber(-1, 0));
    }

    public ComplexMatrix divide(ComplexMatrix matrix) {
        return this.multiply(matrix.inverse());
    }

    public ComplexMatrix inverse() {
        if (this.n != this.m) {
            throw new IllegalArgumentException("A matrix must be square to have an inverse matrix.");
        }
        ComplexNumber determinant = calculateDeterminant(this.matrix);
        if (determinant.equals(new ComplexNumber(0, 0))) {
            throw new ArithmeticException("A matrix must be non-singular to have an inverse matrix.");
        }

        ComplexMatrix adjointMatrix = new ComplexMatrix(this.m, this.n);

        for (int i = 0; i < this.m; i++) {
            for (int j = 0; j < this.n; j++) {
                ComplexNumber cofactorTransposed = new ComplexNumber(0, 0);
                adjointMatrix.matrix[i][j] = cofactor(this.matrix, i, j);
            }
        }
        return adjointMatrix.transpose().multiplyByNumber(new ComplexNumber(1, 0).divide(determinant));
    }

    public void printMatrix() {
        for (int i = 0; i < this.n; i++) {
            for (int j = 0; j < this.m; j++) {
                System.out.print(this.matrix[i][j] + " ");
            }
            System.out.println();
        }
    }

}
