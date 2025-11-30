# Объяснение Generic ограничений

## <? extends T> (Upper Bounded Wildcard)
**Что означает:** Может быть любой тип, который наследуется от T или сам T

**Когда использовать:** Когда нужно читать данные из коллекции
- **Можно:** Читать элементы как тип T
- **Нельзя:** Добавлять элементы (кроме null)

**Пример:**
```java
void processNumbers(List<? extends Number> numbers) {
    for (Number n : numbers) {
        System.out.println(n.doubleValue());
    }
    // numbers.add(new Integer(10)); // ОШИБКА!
}
```

**<? super T> (Lower Bounded Wildcard)**
Что означает: Может быть любой тип, который является родителем T или сам T

Когда использовать: Когда нужно добавлять данные в коллекцию

Можно: Добавлять элементы типа T

Нельзя: Читать как конкретный тип (только как Object)

**Пример:**

```java
void fillList(List<? super Integer> list) {
    list.add(1);
    list.add(2);
    // Integer num = list.get(0); // ОШИБКА!
    Object obj = list.get(0); // Можно читать как Object
}
```

**PECS принцип (Producer-Extends, Consumer-Super)**
Producer (источник данных) → используйте extends

Consumer (приемник данных) → используйте super

```java
void copy(List<? extends T> source, List<? super T> destination) {
    for (T item : source) {
        destination.add(item);
    }
}
```