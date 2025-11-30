Изменение доступа при наследовании:
```java
class A {
  int a1;           // package-private - доступ в том же пакете
  public int a2;    // public - доступ везде
  protected int a3; // protected - доступ в пакете + наследникам
  private int a4;   // private - доступ только в классе A

  void method1() { ... }       // package-private
  public void method2() { ... } // public  
  protected void method3() { ... } // protected
  private void method4() { ... } // private
}
```
class B extends A (в том же пакете):
- a1: ДОСТУПЕН (тот же пакет)
- a2: ДОСТУПЕН (public)
- a3: ДОСТУПЕН (protected)
- a4: НЕДОСТУПЕН (private)
- method1(): ДОСТУПЕН (тот же пакет)
- method2(): ДОСТУПЕН (public)
- method3(): ДОСТУПЕН (protected)  
- method4(): НЕДОСТУПЕН (private)

class C extends B (в том же пакете):
- Все то же самое что и для B

class B extends A (в другом пакете):
- a1: НЕДОСТУПЕН (разные пакеты)
- a2: ДОСТУПЕН (public)
- a3: ДОСТУПЕН (protected - наследник)
- a4: НЕДОСТУПЕН (private)
- method1(): НЕДОСТУПЕН (разные пакеты)
- method2(): ДОСТУПЕН (public)
- method3(): ДОСТУПЕН (protected - наследник)
- method4(): НЕДОСТУПЕН (private)

**Правила наследования:**
1. private - не наследуются
2. package-private - наследуются только в том же пакете  
3. protected - наследуются везде
4. public - наследуются везде