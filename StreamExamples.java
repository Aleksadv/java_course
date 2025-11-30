import java.io.*;
import java.util.Arrays;

public class StreamExamples {
    
    // Практика 1 - InputStream и метод read()
    public static void demonstrateInputStream() throws IOException {
        byte[] data = {65, 66, 67, 68, 69}; // A, B, C, D, E
        InputStream input = new ByteArrayInputStream(data);
        
        System.out.println("=== InputStream read() ===");
        int byteRead;
        while ((byteRead = input.read()) != -1) {
            System.out.println("Прочитан байт: " + byteRead + " -> '" + (char)byteRead + "'");
        }
        input.close();
    }
    
    // Практика 2 - OutputStream и метод write(int)
    public static void demonstrateOutputStream() throws IOException {
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        
        System.out.println("\n=== OutputStream write(int) ===");
        output.write(72);  // H
        output.write(101); // e
        output.write(108); // l
        output.write(108); // l
        output.write(111); // o
        
        byte[] result = output.toByteArray();
        System.out.println("Записаны байты: " + Arrays.toString(result));
        System.out.println("Как строка: " + new String(result));
        output.close();
    }
    
    // Практика 4 - AutoCloseable
    static class Resource implements AutoCloseable {
        private String name;
        
        public Resource(String name) {
            this.name = name;
            System.out.println("Ресурс создан: " + name);
        }
        
        public void use() {
            System.out.println("Используем ресурс: " + name);
        }
        
        @Override
        public void close() {
            System.out.println("Ресурс закрыт: " + name);
        }
    }
    
    public static void demonstrateAutoCloseable() {
        System.out.println("\n=== AutoCloseable ===");
        
        // try-with-resources автоматически вызывает close()
        try (Resource res1 = new Resource("Первый");
             Resource res2 = new Resource("Второй")) {
            
            res1.use();
            res2.use();
            // close() вызовется автоматически при выходе из блока
        } catch (Exception e) {
            System.out.println("Ошибка: " + e.getMessage());
        }
    }
    
    public static void main(String[] args) {
        try {
            demonstrateInputStream();
            demonstrateOutputStream();
            demonstrateAutoCloseable();
        } catch (IOException e) {
            System.out.println("Ошибка ввода-вывода: " + e.getMessage());
        }
    }
}