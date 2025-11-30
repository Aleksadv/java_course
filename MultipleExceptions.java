public class MultipleExceptions {
    
    // Практика 1 - несколько исключений с одинаковой обработкой
    public static void processData(String input, int index) {
        try {
            // Может вызвать разные исключения
            int number = Integer.parseInt(input); // NumberFormatException
            int[] array = {1, 2, 3};
            int value = array[index];             // ArrayIndexOutOfBoundsException
            int result = 10 / value;              // ArithmeticException
            
            System.out.println("Результат: " + result);
            
        } catch (NumberFormatException | ArrayIndexOutOfBoundsException | ArithmeticException e) {
            // Одинаковая обработка для разных исключений
            System.out.println("Ошибка ввода данных: " + e.getMessage());
        }
    }
    
    // Практика 1 - иерархия исключений
    static class BaseException extends Exception {}
    static class MiddleException extends BaseException {}
    static class SpecificException extends MiddleException {}
    
    public static void testHierarchy(int type) throws BaseException, MiddleException, SpecificException {
        if (type == 1) throw new BaseException();
        if (type == 2) throw new MiddleException();
        if (type == 3) throw new SpecificException();
    }
    
    public static void handleHierarchy(int type) {
        try {
            testHierarchy(type);
        } catch (SpecificException e) {
            System.out.println("Поймано SpecificException");
        } catch (MiddleException e) {
            System.out.println("Поймано MiddleException");
        } catch (BaseException e) {
            System.out.println("Поймано BaseException");
        }
    }
    
    // Практика 2 - final в catch
    public static void testFinalCatch(String text) {
        try {
            if (text == null) {
                throw new NullPointerException("Текст не может быть null");
            }
            System.out.println("Длина текста: " + text.length());
            
        } catch (final NullPointerException e) {
            // final предотвращает случайное изменение переменной e
            System.out.println("Ошибка: " + e.getMessage());
            // e = new NullPointerException("новая ошибка"); // ОШИБКА!
        }
    }
    
    // Вложенные try блоки
    public static void nestedTryExample(String[] data) {
        try {
            System.out.println("Внешний try блок");
            
            try {
                // Внутренний try блок
                String first = data[0]; // Может быть ArrayIndexOutOfBoundsException
                int number = Integer.parseInt(first); // Может быть NumberFormatException
                System.out.println("Число: " + number);
                
            } catch (NumberFormatException e) {
                System.out.println("Ошибка преобразования числа: " + e.getMessage());
            }
            // ArrayIndexOutOfBoundsException не обработано во внутреннем блоке
            // передается во внешний блок
            
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Ошибка доступа к массиву: " + e.getMessage());
        }
    }
    
    public static void main(String[] args) {
        System.out.println("=== Несколько исключений с одинаковой обработкой ===\n");
        
        processData("abc", 0);    // NumberFormatException
        processData("123", 5);    // ArrayIndexOutOfBoundsException  
        processData("0", 0);      // ArithmeticException
        processData("10", 1);     // Успешное выполнение
        
        System.out.println("\n=== Иерархия исключений ===\n");
        
        handleHierarchy(3);  // SpecificException
        handleHierarchy(2);  // MiddleException
        handleHierarchy(1);  // BaseException
        
        System.out.println("\n=== Final в catch и вложенные try ===\n");
        
        testFinalCatch(null);
        testFinalCatch("Hello");
        
        System.out.println("\n=== Вложенные try блоки ===\n");
        
        nestedTryExample(new String[]{});           // Пустой массив
        nestedTryExample(new String[]{"abc"});      // Не число
        nestedTryExample(new String[]{"123"});      // Успешно
    }
}