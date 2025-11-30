class MathOperations {
    // Перегрузка метода multiply
    public int multiply(int a, int b) {
        return a * b;
    }
    
    public double multiply(double a, double b) {
        return a * b;
    }
    
    public int multiply(int a, int b, int c) {
        return a * b * c;
    }
    
    public String multiply(String text, int times) {
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < times; i++) {
            result.append(text);
        }
        return result.toString();
    }
}

class Vehicle {
    public void startEngine() {
        System.out.println("Двигатель транспортного средства запущен");
    }
    
    public void stop() {
        System.out.println("Транспортное средство остановилось");
    }
}

class Car extends Vehicle {
    // Переопределение метода
    @Override
    public void startEngine() {
        System.out.println("Двигатель автомобиля завелся с ключа");
    }
}

class Motorcycle extends Vehicle {
    // Переопределение метода
    @Override
    public void startEngine() {
        System.out.println("Мотоцикл завелся с кнопки");
    }
    
    // Новый метод только для мотоцикла
    public void wheelie() {
        System.out.println("Мотоцикл делает вилли!");
    }
}

class Truck extends Vehicle {
    // Переопределение метода с вызовом родительского
    @Override
    public void startEngine() {
        super.startEngine();
        System.out.println("Грузовик прогревается перед поездкой");
    }
}

public class MethodExamples {
    
    // Перегруженные статические методы
    public static void printInfo(String message) {
        System.out.println("Информация: " + message);
    }
    
    public static void printInfo(int number) {
        System.out.println("Число: " + number);
    }
    
    public static void printInfo(String message, int count) {
        for (int i = 0; i < count; i++) {
            System.out.println((i + 1) + ". " + message);
        }
    }
    
    // Метод без возвращаемого значения
    public static void showResult(int result) {
        System.out.println("Результат вычисления: " + result);
        return; // Можно не писать, но можно и так
    }
    
    // Метод с возвращаемым значением
    public static int calculateSum(int a, int b) {
        int sum = a + b;
        return sum; // Обязательно нужно return
    }
    
    public static void main(String[] args) {
        System.out.println("=== ДЕМОНСТРАЦИЯ ПЕРЕГРУЗКИ МЕТОДОВ ===\n");
        
        MathOperations math = new MathOperations();
        
        // Вызов перегруженных методов multiply
        System.out.println("Умножение целых: " + math.multiply(5, 4));
        System.out.println("Умножение дробных: " + math.multiply(2.5, 3.0));
        System.out.println("Умножение трех чисел: " + math.multiply(2, 3, 4));
        System.out.println("Повторение строки: " + math.multiply("Java ", 3));
        
        System.out.println("\n=== ДЕМОНСТРАЦИЯ СТАТИЧЕСКИХ МЕТОДОВ ===");
        printInfo("Привет мир!");
        printInfo(42);
        printInfo("Повтор", 3);
        
        System.out.println("\n=== ДЕМОНСТРАЦИЯ ПЕРЕОПРЕДЕЛЕНИЯ МЕТОДОВ ===\n");
        
        Vehicle vehicle = new Vehicle();
        Vehicle car = new Car();
        Vehicle motorcycle = new Motorcycle();
        Vehicle truck = new Truck();
        
        // Вызов переопределенных методов
        System.out.println("Обычное транспортное средство:");
        vehicle.startEngine();
        
        System.out.println("\nАвтомобиль:");
        car.startEngine();
        
        System.out.println("\nМотоцикл:");
        motorcycle.startEngine();
        
        System.out.println("\nГрузовик:");
        truck.startEngine();
        
        // Вызов метода без переопределения
        System.out.println("\nОстановка всех транспортных средств:");
        vehicle.stop();
        car.stop();
        motorcycle.stop();
        truck.stop();
        
        System.out.println("\n=== ДОПОЛНИТЕЛЬНЫЕ ПРИМЕРЫ ===");
        
        // Методы с возвращаемым значением и без
        int sum = calculateSum(15, 25);
        showResult(sum);
        
        // Вызов уникального метода мотоцикла
        Motorcycle moto = new Motorcycle();
        moto.wheelie();
        
        System.out.println("\n=== ПРОВЕРКА ТИПОВ ВОЗВРАЩАЕМЫХ ЗНАЧЕНИЙ ===");
        
        // Методы с разными возвращаемыми типами
        String textResult = math.multiply("Test", 2);
        int numberResult = math.multiply(10, 20);
        
        System.out.println("Текстовый результат: " + textResult);
        System.out.println("Числовой результат: " + numberResult);
    }
}