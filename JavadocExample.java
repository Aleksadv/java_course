/**
 * Класс для демонстрации различных типов комментариев в Java
 * и использования Javadoc для документирования кода
 * 
 * @author Александр
 * @version 1.0
 * @since 2024
 */
public class JavadocExample {
    
    /**
     * Константа для максимального количества попыток
     */
    public static final int MAX_ATTEMPTS = 3;
    
    /**
     * Поле для хранения имени пользователя
     */
    private String userName;
    
    /**
     * Поле для хранения возраста пользователя
     */
    private int userAge;
    
    /**
     * Конструктор по умолчанию
     * Инициализирует поля значениями по умолчанию
     */
    public JavadocExample() {
        // Это однострочный комментарий
        this.userName = "Гость";
        this.userAge = 0;
    }
    
    /**
     * Параметризованный конструктор
     * 
     * @param name имя пользователя
     * @param age возраст пользователя
     * @throws IllegalArgumentException если имя равно null или возраст отрицательный
     */
    public JavadocExample(String name, int age) {
        if (name == null) {
            throw new IllegalArgumentException("Имя не может быть null");
        }
        if (age < 0) {
            throw new IllegalArgumentException("Возраст не может быть отрицательным");
        }
        this.userName = name;
        this.userAge = age;
    }
    
    /**
     * Вычисляет сумму двух чисел
     * 
     * @param a первое число (целое)
     * @param b второе число (целое)
     * @return сумма a и b
     * @see #multiply(int, int)
     */
    public int add(int a, int b) {
        /*
          Это блочный комментарий
          Он может занимать несколько строк
          Полезен для сложных объяснений
        */
        return a + b; // Это концевой комментарий
    }
    
    /**
     * Умножает два числа
     * 
     * @param a первый множитель
     * @param b второй множитель
     * @return произведение a и b
     */
    public int multiply(int a, int b) {
        // Однострочный комментарий для простых объяснений
        return a * b;
    }
    
    /**
     * Проверяет, является ли пользователь совершеннолетним
     * 
     * @return true если возраст >= 18, false в противном случае
     */
    public boolean isAdult() {
        return this.userAge >= 18;
    }
    
    /**
     * Возвращает приветственное сообщение
     * 
     * @return строка с приветствием пользователя
     */
    public String getGreeting() {
        return "Здравствуйте, " + userName + "!";
    }
    
    /**
     * Геттер для имени пользователя
     * 
     * @return текущее имя пользователя
     */
    public String getUserName() {
        return this.userName;
    }
    
    /**
     * Сеттер для имени пользователя
     * 
     * @param userName новое имя пользователя
     * @throws IllegalArgumentException если userName равен null или пустой
     */
    public void setUserName(String userName) {
        if (userName == null || userName.trim().isEmpty()) {
            throw new IllegalArgumentException("Имя пользователя не может быть null или пустым");
        }
        this.userName = userName;
    }
    
    /**
     * Геттер для возраста пользователя
     * 
     * @return текущий возраст пользователя
     */
    public int getUserAge() {
        return this.userAge;
    }
    
    /**
     * Сеттер для возраста пользователя
     * 
     * @param userAge новый возраст пользователя
     * @throws IllegalArgumentException если возраст отрицательный
     */
    public void setUserAge(int userAge) {
        if (userAge < 0) {
            throw new IllegalArgumentException("Возраст не может быть отрицательным");
        }
        this.userAge = userAge;
    }
    
    /**
     * Устаревший метод вычисления. Используйте {@link #add(int, int)} вместо него.
     * 
     * @param value1 первое значение
     * @param value2 второе значение
     * @return результат вычисления
     * @deprecated Этот метод больше не поддерживается
     */
    @Deprecated
    public int oldCalculationMethod(int value1, int value2) {
        return value1 + value2 * 2;
    }
    
    /**
     * Вычисляет факториал числа
     * 
     * @param n число для вычисления факториала
     * @return факториал числа n
     * @throws IllegalArgumentException если n отрицательное
     */
    public long factorial(int n) {
        if (n < 0) {
            throw new IllegalArgumentException("Число не может быть отрицательным");
        }
        long result = 1;
        for (int i = 1; i <= n; i++) {
            result *= i;
        }
        return result;
    }
    
    /**
     * Основной метод программы для демонстрации работы класса
     * 
     * @param args аргументы командной строки (не используются)
     */
    public static void main(String[] args) {
        // Создаем экземпляр класса с параметрами
        JavadocExample example = new JavadocExample("Александр", 25);
        
        // Демонстрируем сложение
        int sum = example.add(15, 7);
        System.out.println("15 + 7 = " + sum);
        
        // Демонстрируем умножение
        int product = example.multiply(6, 8);
        System.out.println("6 * 8 = " + product);
        
        // Показываем приветствие
        System.out.println(example.getGreeting());
        
        // Проверяем совершеннолетие
        System.out.println("Пользователь совершеннолетний: " + example.isAdult());
        
        // Демонстрируем устаревший метод
        int oldResult = example.oldCalculationMethod(5, 3);
        System.out.println("Результат устаревшего метода: " + oldResult);
        
        // Вычисляем факториал
        long fact = example.factorial(5);
        System.out.println("Факториал 5 = " + fact);
        
        /*
          Еще один пример блочного комментария
          показывающего различные стили комментирования
        */
        
        // Работа с геттерами и сеттерами
        example.setUserAge(30);
        System.out.println("Новый возраст: " + example.getUserAge());
    }
}