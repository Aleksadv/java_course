Ключевые слова this и super в подклассах:

this - ссылка на текущий объект подкласса
super - ссылка на родительскую часть объекта

Использование this:
1. Для обращения к полям и методам текущего класса
   this.fieldName
   this.methodName()

2. Для вызова другого конструктора того же класса
   this(параметры)

3. Чтобы избежать конфликта имен между параметрами и полями

Использование super:
1. Для вызова конструктора родительского класса
   super(параметры) - должна быть первой строкой в конструкторе

2. Для обращения к методам родительского класса
   super.methodName()

3. Для обращения к полям родительского класса
   super.fieldName

4. При переопределении методов для вызова родительской реализации

Пример:
```java
class Parent {
    String name = "Parent";
    
    void show() {
        System.out.println("Parent method");
    }
}

class Child extends Parent {
    String name = "Child";
    
    void show() {
        super.show(); // Вызов метода родителя
        System.out.println("Child method");
        System.out.println("Parent name: " + super.name); // Поле родителя
        System.out.println("Child name: " + this.name);   // Поле ребенка
    }
}
```