import java.util.StringJoiner;

public class StringExamples {
    public static void main(String[] args) {
        System.out.println("=== Практика 1 - Методы String ===\n");
        
        String text = " Hello Java World ";
        String empty = "";
        String spaces = "   ";
        
        // 1. length() - возвращает длину строки
        System.out.println("1. length(): " + text.length() + " символов");
        
        // 2. trim() - удаляет пробелы в начале и конце
        System.out.println("2. trim(): '" + text.trim() + "'");
        
        // 3. toUpperCase() / toLowerCase() - изменение регистра
        System.out.println("3. toUpperCase(): " + text.toUpperCase());
        System.out.println("   toLowerCase(): " + text.toLowerCase());
        
        // 4. substring() - извлечение подстроки
        System.out.println("4. substring(7, 11): " + text.substring(7, 11));
        
        // 5. replace() - замена символов/подстрок
        System.out.println("5. replace('a', 'X'): " + text.replace('a', 'X'));
        
        // 6. contains() - проверка наличия подстроки
        System.out.println("6. contains(\"Java\"): " + text.contains("Java"));
        
        // 7. startsWith() / endsWith() - проверка начала/конца
        System.out.println("7. startsWith(\" Hello\"): " + text.startsWith(" Hello"));
        System.out.println("   endsWith(\"World \"): " + text.endsWith("World "));
        
        // 8. isEmpty() / isBlank() - проверка пустой строки
        System.out.println("8. isEmpty(): '" + empty + "' - " + empty.isEmpty());
        System.out.println("   isBlank(): '" + spaces + "' - " + spaces.isBlank());
        
        // 9. split() - разделение строки на массив
        String[] words = text.trim().split(" ");
        System.out.println("9. split(): " + String.join("|", words));
        
        // 10. repeat() - повторение строки (Java 11+)
        System.out.println("10. repeat(3): " + "Java ".repeat(3));
        
        System.out.println("\n=== Практика 2 - StringJoiner ===\n");
        
        // StringJoiner с разделителем
        StringJoiner joiner = new StringJoiner(", ", "[", "]");
        joiner.add("Apple");
        joiner.add("Banana");
        joiner.add("Orange");
        System.out.println("Fruits: " + joiner.toString());
        
        // StringJoiner для SQL запроса
        StringJoiner sqlJoiner = new StringJoiner(" AND ");
        sqlJoiner.add("age > 18");
        sqlJoiner.add("status = 'active'");
        sqlJoiner.add("city = 'Moscow'");
        System.out.println("SQL WHERE: " + sqlJoiner.toString());
        
        // StringJoiner с префиксом и суффиксом
        StringJoiner names = new StringJoiner(" | ", "Students: ", " <<end");
        names.add("Ivan");
        names.add("Maria");
        names.add("Petr");
        System.out.println(names.toString());
        
        System.out.println("\n=== Практика 3 - Text Blocks ===\n");
        
        // Текст блоки (Java 15+) - три двойные кавычки
        String html = """
            <html>
                <body>
                    <h1>Welcome</h1>
                    <p>This is a paragraph</p>
                </body>
            </html>
            """;
        System.out.println("HTML:\n" + html);
        
        // JSON пример
        String json = """
            {
                "name": "John",
                "age": 30,
                "city": "New York"
            }
            """;
        System.out.println("JSON:\n" + json);
        
        // SQL запрос
        String sql = """
            SELECT id, name, email
            FROM users
            WHERE age > 18
            AND status = 'active'
            ORDER BY name
            """;
        System.out.println("SQL:\n" + sql);
    }
}