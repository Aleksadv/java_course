public class ExceptionExamples {
    
    // Практика 2 - примеры исключений
    
    // 1. ArithmeticException - арифметическая ошибка
    public static void divideNumbers(int a, int b) {
        try {
            int result = a / b; // Деление на ноль
            System.out.println("Результат: " + result);
        } catch (ArithmeticException e) {
            System.out.println("ArithmeticException: " + e.getMessage());
        }
    }
    
    // 2. ArrayIndexOutOfBoundsException - выход за границы массива
    public static void accessArray() {
        try {
            int[] numbers = {1, 2, 3};
            System.out.println(numbers[5]); // Несуществующий индекс
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("ArrayIndexOutOfBoundsException: " + e.getMessage());
        }
    }
    
    // 3. IllegalArgumentException - неверный аргумент
    public static void setAge(int age) {
        try {
            if (age < 0 || age > 150) {
                throw new IllegalArgumentException("Возраст должен быть от 0 до 150");
            }
            System.out.println("Возраст установлен: " + age);
        } catch (IllegalArgumentException e) {
            System.out.println("IllegalArgumentException: " + e.getMessage());
        }
    }
    
    // 4. ClassCastException - неверное приведение типа
    public static void castObject() {
        try {
            Object obj = "Это строка";
            Integer number = (Integer) obj; // Неверное приведение
            System.out.println("Число: " + number);
        } catch (ClassCastException e) {
            System.out.println("ClassCastException: " + e.getMessage());
        }
    }
    
    // 5. NullPointerException - обращение к null
    public static void processString(String text) {
        try {
            int length = text.length(); // text может быть null
            System.out.println("Длина: " + length);
        } catch (NullPointerException e) {
            System.out.println("NullPointerException: " + e.getMessage());
        }
    }
    
    public static void main(String[] args) {
        System.out.println("=== Практика 2 - Примеры исключений ===\n");
        
        // 1. ArithmeticException
        System.out.println("1. ArithmeticException:");
        divideNumbers(10, 0);
        
        // 2. ArrayIndexOutOfBoundsException  
        System.out.println("\n2. ArrayIndexOutOfBoundsException:");
        accessArray();
        
        // 3. IllegalArgumentException
        System.out.println("\n3. IllegalArgumentException:");
        setAge(-5);
        setAge(25);
        
        // 4. ClassCastException
        System.out.println("\n4. ClassCastException:");
        castObject();
        
        // 5. NullPointerException
        System.out.println("\n5. NullPointerException:");
        processString(null);
        processString("Valid string");
        
        System.out.println("\n=== Множественные исключения ===");
        
        // Обработка нескольких типов исключений
        try {
            String str = null;
            int num = Integer.parseInt("abc"); // NumberFormatException
            System.out.println(str.length());
        } catch (NullPointerException e) {
            System.out.println("Пойман NullPointerException");
        } catch (NumberFormatException e) {
            System.out.println("Пойман NumberFormatException: " + e.getMessage());
        } finally {
            System.out.println("Блок finally выполняется всегда");
        }
    }
}