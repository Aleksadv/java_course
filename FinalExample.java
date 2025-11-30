// Практика 1 - final метод
class Parent {
    public final void finalMethod() {
        System.out.println("Этот метод нельзя переопределить");
    }
    
    public void normalMethod() {
        System.out.println("Этот метод можно переопределить");
    }
}

class Child extends Parent {
    // НОРМАЛЬНО - переопределение обычного метода
    @Override
    public void normalMethod() {
        System.out.println("Переопределенный метод");
    }
    
    // ОШИБКА КОМПИЛЯЦИИ - нельзя переопределить final метод
    /*
    @Override
    public void finalMethod() {
        System.out.println("Попытка переопределить");
    }
    */
}

// Практика 2 - final класс
final class FinalClass {
    public void show() {
        System.out.println("Final класс");
    }
}

// ОШИБКА КОМПИЛЯЦИИ - нельзя наследовать от final класса
/*
class ExtendedClass extends FinalClass {
    public void extraMethod() {
        System.out.println("Дополнительный метод");
    }
}
*/

public class FinalExample {
    public static void main(String[] args) {
        Parent parent = new Parent();
        parent.finalMethod();    // Работает
        parent.normalMethod();   // Работает
        
        Child child = new Child();
        child.finalMethod();     // Работает - наследуется как есть
        child.normalMethod();    // Работает - переопределенная версия
        
        FinalClass obj = new FinalClass();
        obj.show();              // Работает
    }
}