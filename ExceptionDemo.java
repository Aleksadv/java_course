public class ExceptionDemo {
    
    public static void processNumber(int number) {
        // Это исключение будет ПЕРЕХВАЧЕНО
        try {
            if (number < 0) {
                throw new IllegalArgumentException("Число не может быть отрицательным: " + number);
            }
            System.out.println("Число обработано: " + number);
        } catch (IllegalArgumentException e) {
            System.out.println("Исключение перехвачено: " + e.getMessage());
        }
    }
    
    public static void divideNumbers(int a, int b) {
        // Это исключение НЕ будет перехвачено и приведет к аварийной остановке
        int result = a / b; // ArithmeticException при b = 0
        System.out.println("Результат деления: " + result);
    }
    
    public static void main(String[] args) {
        System.out.println("=== Начало программы ===\n");
        
        // 1. Исключение, которое будет ПЕРЕХВАЧЕНО
        System.out.println("1. Вызов processNumber(-5):");
        processNumber(-5); // Исключение перехвачено внутри метода
        System.out.println("Программа продолжает работу\n");
        
        // 2. Исключение, которое приведет к АВАРИЙНОЙ ОСТАНОВКЕ
        System.out.println("2. Вызов divideNumbers(10, 0):");
        divideNumbers(10, 0); // ArithmeticException - не обрабатывается
        
        // Эта строка не выполнится из-за аварийной остановки
        System.out.println("Эта строка никогда не напечатается");
    }
}