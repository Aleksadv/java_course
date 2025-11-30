import java.util.Arrays;

public class ArraysExample {
    public static void main(String[] args) {
        // Создаем массивы для демонстрации
        int[] numbers = {7, 3, 9, 1, 6};
        int[] sortedArray = {1, 3, 6, 7, 9};
        int[] copyArray = {1, 3, 6, 7, 9};
        int[] smallerArray = {1, 3, 5};
        int[] largerArray = {1, 3, 8};
        
        System.out.println("=== ДЕМОНСТРАЦИЯ МЕТОДОВ КЛАССА Arrays ===\n");
        
        // 1. Метод toString() - преобразование массива в строку
        System.out.println("1. Метод toString():");
        System.out.println("Исходный массив: " + Arrays.toString(numbers));
        System.out.println("Отсортированный массив: " + Arrays.toString(sortedArray));
        System.out.println();
        
        // 2. Метод sort() - сортировка массива
        System.out.println("2. Метод sort():");
        System.out.println("До сортировки: " + Arrays.toString(numbers));
        Arrays.sort(numbers);
        System.out.println("После сортировки: " + Arrays.toString(numbers));
        System.out.println();
        
        // 3. Метод binarySearch() - бинарный поиск
        System.out.println("3. Метод binarySearch():");
        int searchValue = 7;
        int position = Arrays.binarySearch(numbers, searchValue);
        System.out.println("Поиск числа " + searchValue + " в массиве " + Arrays.toString(numbers));
        System.out.println("Найден на позиции: " + position);
        
        int notFoundValue = 5;
        int notFoundPosition = Arrays.binarySearch(numbers, notFoundValue);
        System.out.println("Поиск числа " + notFoundValue + " в массиве " + Arrays.toString(numbers));
        System.out.println("Результат: " + notFoundPosition + " (отрицательное значение - элемент не найден)");
        System.out.println();
        
        // 4. Метод equals() - сравнение массивов
        System.out.println("4. Метод equals():");
        System.out.println("Массив 1: " + Arrays.toString(sortedArray));
        System.out.println("Массив 2: " + Arrays.toString(copyArray));
        boolean arraysEqual = Arrays.equals(sortedArray, copyArray);
        System.out.println("Массивы равны? " + arraysEqual);
        
        System.out.println("Массив 1: " + Arrays.toString(sortedArray));
        System.out.println("Массив 3: " + Arrays.toString(smallerArray));
        boolean arraysNotEqual = Arrays.equals(sortedArray, smallerArray);
        System.out.println("Массивы равны? " + arraysNotEqual);
        System.out.println();
        
        // 5. Метод compare() - сравнение массивов
        System.out.println("5. Метод compare():");
        System.out.println("Сравнение одинаковых массивов:");
        System.out.println("Массив A: " + Arrays.toString(sortedArray));
        System.out.println("Массив B: " + Arrays.toString(copyArray));
        int compareResult1 = Arrays.compare(sortedArray, copyArray);
        System.out.println("Результат compare: " + compareResult1 + " (0 - массивы равны)");
        
        System.out.println("\nСравнение разных массивов:");
        System.out.println("Массив A: " + Arrays.toString(smallerArray));
        System.out.println("Массив B: " + Arrays.toString(largerArray));
        int compareResult2 = Arrays.compare(smallerArray, largerArray);
        System.out.println("Результат compare: " + compareResult2 + " (отрицательное - массив A меньше)");
        
        System.out.println("\nСравнение массивов разной длины:");
        int[] shortArray = {1, 2};
        int[] longArray = {1, 2, 3};
        System.out.println("Массив A: " + Arrays.toString(shortArray));
        System.out.println("Массив B: " + Arrays.toString(longArray));
        int compareResult3 = Arrays.compare(shortArray, longArray);
        System.out.println("Результат compare: " + compareResult3 + " (отрицательное - массив A короче)");
        
        System.out.println("\n=== КРАТКОЕ ОПИСАНИЕ МЕТОДОВ ===");
        System.out.println("• toString() - преобразует массив в читаемую строку формата [элемент1, элемент2, ...]");
        System.out.println("• sort() - сортирует массив по возрастанию (для чисел) или лексикографически (для строк)");
        System.out.println("• binarySearch() - ищет элемент в ОТСОРТИРОВАННОМ массиве, возвращает индекс или отрицательное число если не найден");
        System.out.println("• equals() - проверяет идентичность массивов (длина и все элементы)");
        System.out.println("• compare() - лексикографически сравнивает массивы, возвращает 0 (равны), отрицательное (первый меньше), положительное (первый больше)");
    }
}

// Результат выполнения программы:
// === ДЕМОНСТРАЦИЯ МЕТОДОВ КЛАССА Arrays ===

// 1. Метод toString():
// Исходный массив: [7, 3, 9, 1, 6]
// Отсортированный массив: [1, 3, 6, 7, 9]

// 2. Метод sort():
// До сортировки: [7, 3, 9, 1, 6]
// После сортировки: [1, 3, 6, 7, 9]

// 3. Метод binarySearch():
// Поиск числа 7 в массиве [1, 3, 6, 7, 9]
// Найден на позиции: 3
// Поиск числа 5 в массиве [1, 3, 6, 7, 9]
// Результат: -3 (отрицательное значение - элемент не найден)

// 4. Метод equals():
// Массив 1: [1, 3, 6, 7, 9]
// Массив 2: [1, 3, 6, 7, 9]
// Массивы равны? true
// Массив 1: [1, 3, 6, 7, 9]
// Массив 3: [1, 3, 5]
// Массивы равны? false

// 5. Метод compare():
// Сравнение одинаковых массивов:
// Массив A: [1, 3, 6, 7, 9]
// Массив B: [1, 3, 6, 7, 9]
// Результат compare: 0 (0 - массивы равны)

// Сравнение разных массивов:
// Массив A: [1, 3, 5]
// Массив B: [1, 3, 8]
// Результат compare: -3 (отрицательное - массив A меньше)

// Сравнение массивов разной длины:
// Массив A: [1, 2]
// Массив B: [1, 2, 3]
// Результат compare: -1 (отрицательное - массив A короче)

// === КРАТКОЕ ОПИСАНИЕ МЕТОДОВ ===
// • toString() - преобразует массив в читаемую строку формата [элемент1, элемент2, ...]
// • sort() - сортирует массив по возрастанию (для чисел) или лексикографически (для строк)
// • binarySearch() - ищет элемент в ОТСОРТИРОВАННОМ массиве, возвращает индекс или отрицательное число если не найден
// • equals() - проверяет идентичность массивов (длина и все элементы)
// • compare() - лексикографически сравнивает массивы, возвращает 0 (равны), отрицательное (первый меньше), положительное (первый больше)