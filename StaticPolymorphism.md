# Почему статические методы не поддерживают динамический полиморфизм

## Код из задания:
```java
class A {
    public static void method() {
        System.out.println("Метод класса A");
    }
}

class B extends A {
    // ОШИБКА - нельзя переопределять статические методы
    // @Override
    public static void method() {
        System.out.println("Метод класса B");
    }
}
```
**Причина:**
Статические методы связываются на этапе компиляции (static binding), а нестатические методы - на этапе выполнения (dynamic binding).

**Пример демонстрации:**
```java
class Parent {
    public static void staticMethod() {
        System.out.println("Статический метод Parent");
    }
    
    public void instanceMethod() {
        System.out.println("Нестатический метод Parent");
    }
}

class Child extends Parent {
    public static void staticMethod() {
        System.out.println("Статический метод Child");
    }
    
    @Override
    public void instanceMethod() {
        System.out.println("Нестатический метод Child");
    }
}

public class Test {
    public static void main(String[] args) {
        Parent obj = new Child();
        
        obj.staticMethod();    // Выведет: "Статический метод Parent"
        obj.instanceMethod();  // Выведет: "Нестатический метод Child"
    }
}
```


**Объяснение:**
1. Статические методы принадлежат классу, а не объекту

2. Компилятор определяет какой статический метод вызвать на основе типа переменной (Parent), а не типа объекта (Child)

3. Нестатические методы определяются на основе фактического типа объекта во время выполнения

**Вывод:**
Динамический полиморфизм работает только с нестатическими методами, потому что статические методы разрешаются на этапе компиляции на основе типа ссылки, а не фактического типа объекта.