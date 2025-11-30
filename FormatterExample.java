import java.util.Formatter;
import java.util.Date;

public class FormatterExample {
    public static void main(String[] args) {
        System.out.println("=== Практика 1 - Базовые спецификаторы ===\n");
        
        // 1. %s - строковое представление
        System.out.printf("Строка: %s%n", "Hello World");
        
        // 2. %d - целое число
        System.out.printf("Число: %d%n", 42);
        
        // 3. %f - число с плавающей точкой
        System.out.printf("Дробное: %.2f%n", 3.14159);
        
        // 4. %b - логическое значение
        System.out.printf("Логическое: %b%n", true);
        
        // 5. %c - символ
        System.out.printf("Символ: %c%n", 'A');
        
        System.out.println("\n=== Комбинированные примеры ===\n");
        
        String name = "Иван";
        int age = 25;
        double salary = 50000.50;
        boolean isActive = true;
        
        System.out.printf("Имя: %s, Возраст: %d, Зарплата: %.2f, Активен: %b%n", 
                         name, age, salary, isActive);
        
        // Форматирование с разной шириной и выравниванием
        System.out.printf("%-15s %10d %10.2f%n", "Петр", 30, 75000.75);
        System.out.printf("%-15s %10d %10.2f%n", "Мария", 28, 60000.00);
        
        System.out.println("\n=== Практика 2 - Formatter с flush() ===\n");
        
        // Создание Formatter с StringBuilder
        StringBuilder sb = new StringBuilder();
        Formatter formatter = new Formatter(sb);
        
        formatter.format("Текст 1: %s%n", "Первая строка");
        formatter.format("Текст 2: %d%n", 100);
        
        // flush() принудительно записывает данные
        // Для Formatter с StringBuilder не критичен
        formatter.flush();
        
        System.out.println("Результат Formatter:");
        System.out.println(sb.toString());
        formatter.close();
        
        System.out.println("=== Практика 3 - Форматирование даты и времени ===\n");
        
        Date currentDate = new Date();
        
        // 1. %tH - часы (00-23)
        System.out.printf("Часы (24ч): %tH%n", currentDate);
        
        // 2. %tM - минуты
        System.out.printf("Минуты: %tM%n", currentDate);
        
        // 3. %tS - секунды
        System.out.printf("Секунды: %tS%n", currentDate);
        
        // 4. %tY - год (4 цифры)
        System.out.printf("Год: %tY%n", currentDate);
        
        // 5. %tB - полное название месяца
        System.out.printf("Месяц: %tB%n", currentDate);
        
        System.out.println("\n=== Комбинированное форматирование даты ===\n");
        
        // Полная дата и время
        System.out.printf("Дата: %tH:%tM:%tS %td.%tm.%tY%n", 
                         currentDate, currentDate, currentDate,
                         currentDate, currentDate, currentDate);
        
        // Альтернативный синтаксис с индексом аргумента
        System.out.printf("Время: %1$tH:%1$tM:%1$tS%n", currentDate);
        
        System.out.println("\n=== Дополнительные спецификаторы ===\n");
        
        // %h - хэшкод
        System.out.printf("Хэшкод строки: %h%n", "test");
        
        // %e - экспоненциальная форма
        System.out.printf("Экспоненциальная: %e%n", 1234567.89);
        
        // %% - символ процента
        System.out.printf("Скидка: %d%%%n", 15);
        
        // Ширина и точность
        System.out.printf("|%10s|%n", "text");      // ширина 10
        System.out.printf("|%-10s|%n", "text");     // выравнивание влево
        System.out.printf("Число: %08d%n", 123);    // заполнение нулями
    }
}