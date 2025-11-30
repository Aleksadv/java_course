**Практика #1**
Объясните, как нужно использовать ключевые слова this и super в подклассах?

**Практика #2**
Объясните как спецификаторы private, protected, public и спецификатор по умолчанию, меняются при наследовании на следующем примере.
```java
class A {
  int a1;
  public int a2;
  protected int a3;
  private int a4;

  void method1() {
    ...
  }

  public void method2() {
    ...
  }

  protected void method3() {
    ...
  }
  
  private void method4() {
    ...
  }

}

class B extends A {
  ...
}

class C extends B {
  ...
}
```