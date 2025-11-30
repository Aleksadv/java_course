// Практика 1 - default и static методы в интерфейсе
interface Vehicle {
    // Абстрактный метод
    void start();
    
    // Default метод (неабстрактный)
    default void honk() {
        System.out.println("Бип-бип!");
    }
    
    // Статический метод
    static void showInfo() {
        System.out.println("Это интерфейс Vehicle");
    }
    
    // Еще один default метод
    default void stop() {
        System.out.println("Транспорт остановился");
    }
}

// Класс, реализующий интерфейс
class Car implements Vehicle {
    @Override
    public void start() {
        System.out.println("Машина завелась");
    }
    
    // Можно переопределить default метод
    @Override
    public void honk() {
        System.out.println("Машина сигналит: БИИП!");
    }
}

// Другой класс без переопределения default метода
class Bike implements Vehicle {
    @Override
    public void start() {
        System.out.println("Мотоцикл завелся");
    }
}

// Практика 2 - конфликт default методов
interface Engine {
    default void start() {
        System.out.println("Двигатель запущен");
    }
    
    default void maintenance() {
        System.out.println("Обслуживание двигателя");
    }
}

interface Electric {
    default void start() {
        System.out.println("Электромотор запущен");
    }
    
    default void charge() {
        System.out.println("Зарядка батареи");
    }
}

// Класс с конфликтом default методов
class HybridCar implements Vehicle, Engine, Electric {
    @Override
    public void start() {
        // Обязаны переопределить из-за конфликта
        System.out.println("Гибридный автомобиль запущен");
        // Можно вызвать конкретный default метод
        Engine.super.start(); // Вызов default метода из Engine
        Electric.super.start(); // Вызов default метода из Electric
    }
    
    // Нет конфликта - используем default из Engine
    public void maintenance() {
        Engine.super.maintenance(); // Явный вызов
    }
    
    // Нет конфликта - используем default из Electric  
    public void charge() {
        Electric.super.charge(); // Явный вызов
    }
}

public class InterfaceMethods {
    public static void main(String[] args) {
        System.out.println("=== Практика 1 ===");
        
        Car car = new Car();
        car.start();        // Переопределенный абстрактный метод
        car.honk();         // Переопределенный default метод
        car.stop();         // Унаследованный default метод
        
        Bike bike = new Bike();
        bike.start();       // Переопределенный абстрактный метод  
        bike.honk();        // Унаследованный default метод
        
        // Вызов статического метода интерфейса
        Vehicle.showInfo(); // Через имя интерфейса
        
        System.out.println("\n=== Практика 2 ===");
        
        HybridCar hybrid = new HybridCar();
        hybrid.start();     // Переопределенный метод из-за конфликта
        hybrid.maintenance(); // Default метод из Engine
        hybrid.charge();    // Default метод из Electric
        hybrid.honk();      // Default метод из Vehicle (без конфликта)
    }
}