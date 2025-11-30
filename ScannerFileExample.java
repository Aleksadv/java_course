import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class ScannerFileExample {
    public static void main(String[] args) {
        try {
            // Создаем Scanner для чтения из файла
            Scanner fileScanner = new Scanner(new File("data.txt"));
            
            System.out.println("=== Чтение данных из файла ===");
            
            // Чтение построчно
            while (fileScanner.hasNextLine()) {
                String line = fileScanner.nextLine();
                System.out.println("Прочитана строка: " + line);
            }
            
            fileScanner.close();
            
        } catch (FileNotFoundException e) {
            System.out.println("Файл не найден: " + e.getMessage());
            
            // Создаем пример файла для демонстрации
            createDemoFile();
        }
    }
    
    public static void createDemoFile() {
        try {
            // Создаем временный файл с данными
            java.io.FileWriter writer = new java.io.FileWriter("data.txt");
            writer.write("Иван 25 85.5\n");
            writer.write("Мария 30 92.0\n");
            writer.write("Петр 22 78.5\n");
            writer.close();
            
            System.out.println("Создан демо-файл data.txt");
            
            // Теперь читаем созданный файл
            Scanner fileScanner = new Scanner(new File("data.txt"));
            
            System.out.println("\n=== Чтение разных типов данных ===");
            while (fileScanner.hasNext()) {
                String name = fileScanner.next();
                int age = fileScanner.nextInt();
                double score = fileScanner.nextDouble();
                
                System.out.printf("Имя: %s, Возраст: %d, Оценка: %.1f%n", 
                                 name, age, score);
            }
            
            fileScanner.close();
            
        } catch (Exception e) {
            System.out.println("Ошибка: " + e.getMessage());
        }
    }
} 