//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        ComplexNumber[][] data1 = {
                {new ComplexNumber(2, 3), new ComplexNumber(-7, 3)},
                {new ComplexNumber(5, -4), new ComplexNumber(-3, -2)}
        };
        ComplexNumber[][] data2 = {
                {new ComplexNumber(4, -2), new ComplexNumber(7, -1)},
                {new ComplexNumber(5, 8), new ComplexNumber(-2, -1)}
        };
        ComplexNumber[][] data3 = {
                {new ComplexNumber(1, 7)},
                {new ComplexNumber(5, 1)}
        };
        ComplexNumber[][] data4 = {
                {new ComplexNumber(2, 3), new ComplexNumber(-7, 3), new ComplexNumber(7, -1)},
                {new ComplexNumber(5, -4), new ComplexNumber(-3, -2), new ComplexNumber(5, 8)},
                {new ComplexNumber(5, 1), new ComplexNumber(1, 1), new ComplexNumber(2, 1)},
        };

        ComplexMatrix matrix1 = new ComplexMatrix(data1);
        ComplexMatrix matrix2 = new ComplexMatrix(data2);
        ComplexMatrix matrix3 = new ComplexMatrix(data3);
        ComplexMatrix matrix4 = new ComplexMatrix(data4);

        System.out.println("Матрица 1:");
        matrix1.printMatrix();

        System.out.println("\nМатрица 2:");
        matrix2.printMatrix();

        System.out.println("\nМатрица 3:");
        matrix3.printMatrix();

        System.out.println("\nМатрица 4:");
        matrix4.printMatrix();

        System.out.println("\nСложение матриц 1 и 2:");
        ComplexMatrix sum_matrix12 = matrix1.add(matrix2);
        sum_matrix12.printMatrix();

        System.out.println("\nВычитание из матрицы 1 матрицы 2:");
        ComplexMatrix subtract_matrix12 = matrix1.subtract(matrix2);
        subtract_matrix12.printMatrix();

        System.out.println("\nУмножение матриц 1 и 3:");
        ComplexMatrix multiply_matrix13 = matrix1.multiply(matrix3);
        multiply_matrix13.printMatrix();

        System.out.println("\nУмножение первой матрицы на число 3 + 2i:");
        ComplexMatrix num_matrix1 = matrix1.multiplyByNumber(new ComplexNumber(3, 2));
        num_matrix1.printMatrix();

        System.out.println("\nТранспонирование первой матрицы:");
        ComplexMatrix transpose_matrix1 = matrix1.transpose();
        transpose_matrix1.printMatrix();

        System.out.println("\nДетерминант четвертой матрицы:");
        ComplexNumber determinant = matrix4.determinant();
        System.out.println(determinant);

        System.out.println("\nОбратная матрица первой матрицы:");
        ComplexMatrix inverse_matrix1 = matrix1.inverse();
        inverse_matrix1.printMatrix();

        System.out.println("\nВторая матрица, деленная на первую матрицу (умноженная на обратную к первой):");
        ComplexMatrix divide_matrix2by1 = matrix2.divide(matrix1);
        divide_matrix2by1.printMatrix();

        System.out.println("\nИзменим размер первой матрицы с 2:2 на 3:3 :");
        matrix1.setSize(3, 3);
        matrix1.printMatrix();
    }
}