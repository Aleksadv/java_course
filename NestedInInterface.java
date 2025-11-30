interface Database {  // Интерфейс
    void connect();
    
    // Вложенный static класс в интерфейсе
    static class ConnectionInfo {
        private String url;
        private String username;
        private String password;
        
        public ConnectionInfo(String url, String username, String password) {
            this.url = url;
            this.username = username;
            this.password = password;
        }
        
        public void displayInfo() {
            System.out.println("URL: " + url);
            System.out.println("User: " + username);
            // Пароль не выводим для безопасности
        }
        
        public static void showDefaultConfig() {
            System.out.println("Default configuration loaded");
        }
    }
    
    // Еще один вложенный класс - утилита
    static class Validator {
        public static boolean isValidUrl(String url) {
            return url != null && url.startsWith("jdbc:");
        }
        
        public static boolean isValidCredentials(String username, String password) {
            return username != null && !username.isEmpty() && 
                   password != null && !password.isEmpty();
        }
    }
}

// Реализация интерфейса
class MySQLDatabase implements Database {
    @Override
    public void connect() {
        System.out.println("MySQL connected");
    }
}

class PostgreSQLDatabase implements Database {
    @Override
    public void connect() {
        System.out.println("PostgreSQL connected");
    }
}

public class NestedInInterface {
    public static void main(String[] args) {
        // Создание вложенного класса из интерфейса
        Database.ConnectionInfo info = new Database.ConnectionInfo(
            "jdbc:mysql://localhost:3306/test", 
            "admin", 
            "password123"
        );
        
        // Вызов метода вложенного класса
        info.displayInfo();
        
        // Вызов статического метода вложенного класса
        Database.ConnectionInfo.showDefaultConfig();
        
        // Использование второго вложенного класса-утилиты
        boolean isValid = Database.Validator.isValidUrl("jdbc:mysql://localhost:3306/test");
        System.out.println("URL valid: " + isValid);
        
        // Создание объектов реализаций интерфейса
        Database mysql = new MySQLDatabase();
        Database postgres = new PostgreSQLDatabase();
        
        mysql.connect();
        postgres.connect();
        
        // Дополнительные примеры использования
        System.out.println("\n=== Дополнительные тесты ===");
        
        // Проверка валидации
        String testUrl = "invalid_url";
        boolean urlValid = Database.Validator.isValidUrl(testUrl);
        System.out.println("URL '" + testUrl + "' valid: " + urlValid);
        
        // Создание другого экземпляра ConnectionInfo
        Database.ConnectionInfo localInfo = new Database.ConnectionInfo(
            "jdbc:postgresql://localhost:5432/mydb",
            "user",
            "pass"
        );
        localInfo.displayInfo();
    }
}