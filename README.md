**Практика #1**
Приведите пример кода, демонстрирующего все три варианта использования ключевого слова super.

**Практика #2**
Что случится при вызове метода method() объекта класса C, если переменная a и метод method() не будут определены в классе B, а будут определены только в классе A?
```java
class A {
  int a;
  void method() {
    ...
  }

}

class B extends A {
  ...
}

class C extends B {
  ...
  void method() {
    ...
    int a = super.a;
    super.method();
  }
  ...
}
```


**Практика #3**
Перепешите следующий код с использованием конструкции this(). Помните, что в каждом конструкторе вызов this() должен быть единственным и первым среди всех операций.
```java
class A {
  int a;
  int b;
  int c;
  int z;

  public A() {
    z = 1;
  }

  public A(int a) {
    this.a = a;
    z = 1;
  }

  public A(int a, int b) {
    this.a = a;
    this.b = b;
    z = 1;
  }

  public A(int a, int b, int c) {
    this.a = a;
    this.b = b;
    this.c = c;
    z = 1;
  }
  ...
}
```