# Область видимости интерфейсов

## Практика 1: interface Interface (без спецификатора)
**Область видимости:** package-private (доступ в пределах пакета)

**Объяснение:**
- Интерфейс доступен только классам в том же пакете
- Не может быть реализован классами из других пакетов
- Не может быть унаследован интерфейсами из других пакетов

**Пример:**
```java
// Файл в пакете com.example
interface PackageInterface {
    void method();
}

// Доступно только в com.example
class SamePackageClass implements PackageInterface {
    public void method() { }
}
```

## Практика 2: public interface Interface
**Область видимости:** public (доступ везде)

**Объяснение:**

- Интерфейс доступен из любого пакета
- Может быть реализован любым классом
- Может быть унаследован любым интерфейсом

**Пример:**
```java
public interface PublicInterface {
    void method();
}

// Доступно отовсюду
class AnyClass implements PublicInterface {
    public void method() { }
}
```

## Практика 3: protected interface Interface
**Область видимости:** НЕДОПУСТИМО - ошибка компиляции

**Объяснение:**
- Спецификатор protected не может применяться к интерфейсам
- Интерфейсы могут быть только public или package-private

**Ошибка:**
```java
protected interface ProtectedInterface { // ОШИБКА КОМПИЛЯЦИИ!
    void method();
}
```

## Практика 4: private interface Interface
**Область видимости:** НЕДОПУСТИМО - ошибка компиляции

**Объяснение:**

- Спецификатор private не может применяться к интерфейсам верхнего уровня
- private интерфейсы возможны только как вложенные в другие классы/интерфейсы

**Ошибка:**
```java
private interface PrivateInterface { // ОШИБКА КОМПИЛЯЦИИ!
    void method();
}
```
**Допустимые варианты для вложенных интерфейсов:**
```java
class OuterClass {
    // Вложенные интерфейсы могут иметь разные спецификаторы
    public interface PublicNested { }
    protected interface ProtectedNested { }
    private interface PrivateNested { }
    interface PackageNested { }
}
```

## Итог:
- Топ-уровень интерфейсы: только public или package-private**
- Вложенные интерфейсы: любые спецификаторы доступа
- protected и private НЕ допустимы для интерфейсов верхнего уровня