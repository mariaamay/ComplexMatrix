# Класс для работы с матрицами комплексных чисел
## Содержание
[Класс комплексных чисел (ComplexNumber)](src/ComplexNumber.java)  
[Класс матриц компексных чисел (ComplexMatrix)](src/ComplexMatrix.java)  
[Тестирование](src/Main.java)

  
## Класс комплексных чисел (ComplexNumber)  
Доступ к Re и Im частям осуществляется через геттеры и сеттеры. Переопределен строковый вывод комплексного числа.
### 1. Конструкторы  
```bash
- Без параметров: создает комплексное число с нулевыми Re и Im частями
- (double): Устанавливает Re часть равной входному параметру, а Im = 0
- (double, double): Устанавливает Re и Im части равными входным параметрам соответственно 
```

### 2. Сложение (add)  
```bash
- ComplexNumber add(ComplexNumber other): добавляет к числу, чей метод был вызван, комплексное число other
```

### 3. Вычитание (subtract)
```bash
- ComplexNumber subtract(ComplexNumber other): вычитает из числа, чей метод был вызван число other
```

### 4. Умножение (multiply)
```bash
- ComplexNumber multiply(ComplexNumber other): умножает на число, чей метод был вызван число other в соответствии с формулой
```

### 5. Деление (divide)
```bash
- ComplexNumber divide(ComplexNumber other): делит число, с которым был вызван, на число other, выполняется по формуле
```

  
## Класс матриц комплексных чисел (ComplexMatrix)
Каждая матрица имеет количество строк (n) и количество столбцов (m). Элементы хранятся в двумерном массиве matrix. Доступ к элементам осуществляется через геттеры и сеттеры.

### 1. Конструкторы  
```bash
- Без параметров: создает матрицу 1:1 с элементом 0
- (int n, int m): Создает матрицу размером n:m заполненную нулями
- (ComplexNumber[][] matrix): Принимает на вход двумерный массив данных, создает в соответствии с ними матрицу
```

### 2. Геттеры и сеттеры (getCell, setCell, setSize)
```bash
- ComplexNumber getCell(int i, int j): возвращает элемент матрицы по индексу (i, j). Если индекс находится за пределами границ матрицы - выбрасывает исключение "Index is out of bounds"
- void setCell(ComplexNumber value, int i, int j): устаналивает элемент матрицы по индексу (i, j) равным значению value. Если индекс находится за пределами границ матрицы - выбрасывает исключение "Index is out of bounds"
- void setSize(int n, int m): если хотя бы один из размером матрицы меньше или равен 0, выбрасывает исключение "n and m must be positive", копирует все элементы прошлой матрицы на те же самые места, если в изначальной матрице элементы отсутствовали - заполняет ячейки новой матрицы нулями
``` 

### 3. Сложение двух матриц (add) 
Проверяет совпадают ли размеры матриц, если нет - выбрасывает исключение "Сannot add complex matrices with different dimensions"  
```bash
- ComplexMatrix add(ComplexMatrix other): складывает матрицы поэлементно
```
### 4. Вычитание матриц (subtract)
Проверяет совпадают ли размеры матриц, если нет - выбрасывает исключение "Сannot add complex matrices with different dimensions"  
```bash
- ComplexMatrix subtract(ComplexMatrix other): вычитает матрицы поэлементно
```

### 5. Умножение на матрицу (multiply)
Проверяет равенство количества столбцов первой матрицы количеству столбцов второй матрицы, если не равны - выбрасывает исключение "The number of columns of the first matrix must equal the number of rows of the second one"  
```bash
- ComplexNumber multiply(ComplexNumber other): умножает матрицу на матрицу в соответствии с правилом умножения матриц
```

### 6. Умножение матрицы на число (multiplyByNumber)
```bash
- ComplexMatrix multiplyByNumber(ComplexNumber num): умножает матрицу на число num поэлементно
``` 

### 7. Транспонирование (transpose)
```bash
- ComplexMatrix transpose(): транспонирует матрицу в соответствии с правилом транспонирования матриц
```

### 8. Поиск детерминанта (determinant)
Проверяет квадратная ли матрица (n == m), если нет - выбрасывает исключение "The matrix must be square to calculate determinant"
```bash
- ComplexNumber determinant(): вызывает функцию, которая рекурсивно подсчитывает детерминант
- ComplexNumber calculateDeterminant(ComplexNumber[][] matrix): рекурсивно подсчитывает детерминант
- ComplexNumber cofactor(ComplexNumber[][] matrix, int i, int j): ищет алгебраическое дополнение элемента по индексу (i, j)
```

 ### 9. Поиск обратной матрицы (inverse)
 Если матрица не квадратная - выбрасывает исключение "A matrix must be square to have an inverse matrix."    
 Если матрица вырожденная (с нулевым детерминантом) - выбрасывает исключение "A matrix must be non-singular to have an inverse matrix." 
```bash
 - ComplexMatrix inverse(): рекурсивно подсчитывает матрицу алгебраических дополнений с помощью метода cofactor, транспонирует её с помощью метода transpose и умножает с помощью метода multiplyByNumber на обратное к детерминанту число  
```

  ### 10. Деление матрицы на матрицу (умножение на обратную к заданной матрице) (divide)
```bash
 - ComplexMatrix divide(ComplexMatrix matrix): умножает матрицу на обратную матрицу к заданной, подсчитанную с помощью метода inverse
```

### 11. Вывод (printMatrix)
```bash
- void printMatrix(): проходится циклом по всем элементам матрицы и выводит в консоль
```

## Тестирование
В классе Main реализована проверка работоспособности кода.
  
 

