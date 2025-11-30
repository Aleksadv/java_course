# Объяснение сравнения Integer и работы IntegerCache

## Код для анализа:
```java
int i1 = 128;
Integer a1 = i1;
Integer b1 = i1;
System.out.println("a1==i1 " + (a1 == i1));
System.out.println("b1==i1 " + (b1 == i1));
System.out.println("a1==b1 " + (a1 == b1));
System.out.println("a1.equals(i1) -> " + a1.equals(i1));
System.out.println("b1.equals(i1) -> " + b1.equals(i1));
System.out.println("a1.equals(b1) -> " + a1.equals(b1));

int i2 = 127;
Integer a2 = i2;
Integer b2 = i2;
System.out.println("a2==i2 " + (a2 == i2));
System.out.println("b2==i2 " + (b2 == i2));
System.out.println("a2==b2 " + (a2 == b2));
System.out.println("a2.equals(i2) -> " + a2.equals(i2));
System.out.println("b2.equals(i2) -> " + b2.equals(i2));
System.out.println("a2.equals(b2) -> " + a2.equals(b2));


Результат выполнения:
a1==i1 true
b1==i1 true
a1==b1 false
a1.equals(i1) -> true
b1.equals(i1) -> true
a1.equals(b1) -> true

a2==i2 true
b2==i2 true
a2==b2 true
a2.equals(i2) -> true
b2.equals(i2) -> true
a2.equals(b2) -> true

Подробное объяснение:
1. Сравнение Integer == int (автораспаковка)
a1 == i1 → true и b1 == i1 → true

a2 == i2 → true и b2 == i2 → true

Причина: Когда сравниваются Integer и int, происходит автораспаковка - объект Integer автоматически преобразуется в примитив int, и сравниваются числовые значения.

2. Сравнение Integer == Integer (влияние кэширования)
a1 == b1 → false (для значения 128)

a2 == b2 → true (для значения 127)

Причина: Оператор == сравнивает ссылки на объекты, а не их значения. Разный результат обусловлен работой IntegerCache.

3. Что такое IntegerCache?
IntegerCache - это внутренний класс в Java, который кэширует часто используемые объекты Integer для оптимизации памяти и производительности.

Характеристики IntegerCache:

Диапазон по умолчанию: от -128 до 127

Для значений в этом диапазоне: Integer.valueOf() возвращает один и тот же объект из кэша

Для значений вне диапазона: создаются новые объекты

4. Почему разные результаты для 127 и 128?
Для i2 = 127:

Значение 127 находится в диапазоне кэша (-128 до 127)

a2 и b2 ссылаются на один и тот же объект из кэша

Поэтому a2 == b2 возвращает true

Для i1 = 128:

Значение 128 выходит за пределы диапазона кэша

a1 и b1 - это разные объекты в памяти

Поэтому a1 == b1 возвращает false

5. Метод equals() - правильное сравнение
Все вызовы a1.equals(i1), b1.equals(i1), a1.equals(b1) и аналогичные для 127 возвращают true.

Причина: Метод equals() сравнивает значения объектов, а не ссылки на них, поэтому он всегда корректно определяет равенство числовых значений.