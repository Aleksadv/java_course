# Конфликты default методов

## Что происходит при конфликте:
Если класс реализует два интерфейса с одинаковыми default методами - возникает **ошибка компиляции**.

## Пример конфликта:
```java
interface A {
    default void method() { System.out.println("A"); }
}

interface B {
    default void method() { System.out.println("B"); }
}

class C implements A, B { // ОШИБКА КОМПИЛЯЦИИ!
    // Конфликт default методов method()
}
```
**Способы решения:**
1. **Переопределение метода в классе**
```java
class C implements A, B {
    @Override
    public void method() {
        System.out.println("Собственная реализация");
    }
}
```
2. **Вызов конкретного default метода**
```java
class C implements A, B {
    @Override
    public void method() {
        A.super.method(); // Вызов метода из A
        // или
        B.super.method(); // Вызов метода из B
    }
}
```
3. **Комбинация вызовов**
```java
class C implements A, B {
    @Override
    public void method() {
        A.super.method();
        B.super.method();
        System.out.println("Дополнительная логика");
    }
}
```
**Правила разрешения конфликтов:**
- Класс всегда имеет приоритет над интерфейсами
- Более специфичный интерфейс имеет приоритет над более общим
- Если приоритет не ясен - требуется явное разрешение