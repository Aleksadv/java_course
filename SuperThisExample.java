// Практика 1 - использование super
class Parent {
    String name = "Родитель";
    
    Parent(String name) {
        this.name = name;
    }
    
    void showInfo() {
        System.out.println("Метод родителя: " + name);
    }
}

class Child extends Parent {
    String name = "Ребенок";
    
    Child(String name) {
        super(name); // 1. Вызов конструктора суперкласса
    }
    
    void display() {
        System.out.println("Поле родителя: " + super.name); // 2. Доступ к полю суперкласса
        System.out.println("Поле ребенка: " + this.name);
        super.showInfo(); // 3. Доступ к методу суперкласса
    }
}

// Практика 2 - наследование через несколько уровней
class A {
    int a = 10;
    
    void method() {
        System.out.println("Метод класса A, a = " + a);
    }
}

class B extends A {
    // Нет переопределения поля a и метода method()
}

class C extends B {
    void method() {
        int a = super.a;        // Обращение к полю из класса A
        super.method();         // Вызов метода из класса A
        System.out.println("Метод класса C, унаследованное a = " + a);
    }
}

// Практика 3 - использование this()
class Example {
    int a, b, c, z;
    
    public Example() {
        this(0, 0, 0); // Вызов конструктора с тремя параметрами
    }
    
    public Example(int a) {
        this(a, 0, 0); // Вызов конструктора с тремя параметрами
    }
    
    public Example(int a, int b) {
        this(a, b, 0); // Вызов конструктора с тремя параметрами
    }
    
    public Example(int a, int b, int c) {
        this.a = a;
        this.b = b;
        this.c = c;
        this.z = 1;
    }
    
    void showValues() {
        System.out.println("a=" + a + ", b=" + b + ", c=" + c + ", z=" + z);
    }
}

public class SuperThisExample {
    public static void main(String[] args) {
        // Практика 1
        System.out.println("=== Практика 1 ===");
        Child child = new Child("Тестовое имя");
        child.display();
        
        // Практика 2
        System.out.println("\n=== Практика 2 ===");
        C obj = new C();
        obj.method();
        
        // Практика 3
        System.out.println("\n=== Практика 3 ===");
        Example ex1 = new Example();
        Example ex2 = new Example(5);
        Example ex3 = new Example(5, 10);
        Example ex4 = new Example(5, 10, 15);
        
        ex1.showValues();
        ex2.showValues();
        ex3.showValues();
        ex4.showValues();
    }
}