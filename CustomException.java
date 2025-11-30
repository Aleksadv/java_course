// Практика 1 - собственный класс исключения
class InvalidEmailException extends Exception {
    
    // Конструктор по умолчанию
    public InvalidEmailException() {
        super("Некорректный email адрес");
    }
    
    // Конструктор с сообщением об ошибке
    public InvalidEmailException(String message) {
        super(message);
    }
    
    // Конструктор с сообщением и причиной
    public InvalidEmailException(String message, Throwable cause) {
        super(message, cause);
    }
}

// Класс для демонстрации использования
public class CustomException {
    
    public static void validateEmail(String email) throws InvalidEmailException {
        if (email == null || email.isEmpty()) {
            throw new InvalidEmailException("Email не может быть пустым");
        }
        
        if (!email.contains("@")) {
            throw new InvalidEmailException("Email должен содержать символ @: " + email);
        }
        
        if (!email.contains(".")) {
            throw new InvalidEmailException("Email должен содержать точку: " + email);
        }
        
        System.out.println("Email корректен: " + email);
    }
    
    public static void main(String[] args) {
        String[] emails = {
            "user@example.com",
            "invalid-email",
            "",
            null,
            "user@domain"
        };
        
        for (String email : emails) {
            try {
                validateEmail(email);
            } catch (InvalidEmailException e) {
                System.out.println("Ошибка: " + e.getMessage());
            }
        }
        
        // Пример с причиной исключения
        try {
            String email = "test@example.com";
            // Имитируем проблему с подключением к базе данных
            throw new InvalidEmailException("Ошибка при сохранении email", 
                                          new RuntimeException("Connection timeout"));
        } catch (InvalidEmailException e) {
            System.out.println("\nИсключение с причиной:");
            System.out.println("Сообщение: " + e.getMessage());
            System.out.println("Причина: " + e.getCause().getMessage());
        }
    }
}