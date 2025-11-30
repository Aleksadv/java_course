// Практика 1 - перегруженные методы
class Calculator {
    // Перегрузка - одинаковое имя, разные параметры
    public int add(int a, int b) {
        return a + b;
    }
    
    public double add(double a, double b) {
        return a + b;
    }
    
    public int add(int a, int b, int c) {
        return a + b + c;
    }
    
    public String add(String a, String b) {
        return a + b;
    }
}

// Практика 2 и 3 - переопределение методов
class Animal {
    public void makeSound() {
        System.out.println("Животное издает звук");
    }
    
    public String getName() {
        return "Животное";
    }
}

class Dog extends Animal {
    // Правильное переопределение
    @Override
    public void makeSound() {
        System.out.println("Собака лает: Гав-гав!");
    }
    
    // ОШИБКА КОМПИЛЯЦИИ - несовпадение типа возвращаемого значения
    /*
    @Override
    public int getName() {  // Должен возвращать String
        return 1;
    }
    */
    
    // Пример когда @Override помогает найти ошибку
    /*
    @Override
    public void makeSoud() {  // Опечатка в имени метода
        System.out.println("Не сработает");
    }
    */
}

class Cat extends Animal {
    // Правильное переопределение с @Override
    @Override
    public void makeSound() {
        System.out.println("Кошка мяукает: Мяу!");
    }
    
    @Override
    public String getName() {
        return "Кот";
    }
}

public class OverloadingOverriding {
    public static void main(String[] args) {
        // Практика 1 - перегрузка
        System.out.println("=== Перегруженные методы ===");
        Calculator calc = new Calculator();
        System.out.println("add(2, 3): " + calc.add(2, 3));
        System.out.println("add(2.5, 3.5): " + calc.add(2.5, 3.5));
        System.out.println("add(1, 2, 3): " + calc.add(1, 2, 3));
        System.out.println("add(\"Hello\", \"World\"): " + calc.add("Hello", "World"));
        
        // Практика 2 - переопределение
        System.out.println("\n=== Переопределенные методы ===");
        Animal animal = new Animal();
        Animal dog = new Dog();
        Animal cat = new Cat();
        
        animal.makeSound();  // Метод Animal
        dog.makeSound();     // Переопределенный метод Dog
        cat.makeSound();     // Переопределенный метод Cat
        
        System.out.println("\n=== Полиморфизм ===");
        Animal[] animals = {new Animal(), new Dog(), new Cat()};
        for (Animal a : animals) {
            a.makeSound();  // Динамическое связывание
        }
    }
}