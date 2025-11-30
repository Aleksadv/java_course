# Аннотации в Java

## @Override

**Назначение:** Указывает, что метод переопределяет метод суперкласса

**Зачем нужна:**
- Помогает компилятору проверить, что метод действительно переопределяет существующий
- Предотвращает ошибки при опечатках в имени метода или параметрах
- Улучшает читаемость кода

**Пример:**
```java
class Animal {
    public void makeSound() {
        System.out.println("Some sound");
    }
}

class Dog extends Animal {
    @Override
    public void makeSound() {
        System.out.println("Woof!");
    }
    
    // Ошибка компиляции - нет такого метода в родителе
    // @Override
    // public void makeSoud() { } // Опечатка в имени
}
```

## @Deprecated
**Назначение:** Помечает устаревшие методы/классы, которые не рекомендуется использовать

**Зачем нужна:**
- Предупреждает разработчиков об устаревшем API
- Компилятор показывает предупреждения при использовании
- Позволяет постепенно обновлять код

**Пример:**
```java
class Calculator {
    @Deprecated
    public int oldAdd(int a, int b) {
        return a + b;
    }
    
    public int add(int a, int b) {
        return a + b;
    }
    
    @Deprecated(since = "2.0", forRemoval = true)
    public void obsoleteMethod() {
        // Будет удален в будущей версии
    }
}

// Использование:
Calculator calc = new Calculator();
calc.oldAdd(2, 3); // Предупреждение компилятора

```
## @SuppressWarnings

**Назначение:** Отключает предупреждения компилятора для конкретного элемента

**Зачем нужна:**
- Когда разработчик уверен в своей логике, но компилятор выдает ложные предупреждения
- Для подавления известных и безопасных предупреждений
- Следует использовать осторожно и только когда необходимо

**Пример:**

```java
import java.util.*;

class Example {
    @SuppressWarnings("unchecked")
    public void processList() {
        List list = new ArrayList(); // Raw type - предупреждение
        list.add("test");
    }
    
    @SuppressWarnings({"deprecation", "unused"})
    public void multipleWarnings() {
        Calculator calc = new Calculator();
        calc.oldAdd(1, 2); // Игнорируем deprecation warning
        
        int unusedVar = 42; // Игнорируем unused warning
    }
    
    @SuppressWarnings("all")
    public void suppressAll() {
        // Подавляет все предупреждения (не рекомендуется)
    }
}
```
**Распространенные значения для @SuppressWarnings:**
- "unchecked" - подавляет предупреждения о raw types
- "deprecation" - подавляет предупреждения об использовании устаревших методов
- "unused" - подавляет предупреждения о неиспользуемых переменных/методах
- "all" - подавляет все предупреждения