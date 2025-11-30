public class InnerClassesExample {
    private String outerPrivate = "outer private";
    protected String outerProtected = "outer protected";
    public String outerPublic = "outer public";
    String outerPackage = "outer package";
    
    // Практика 1 - внутренние классы с разными спецификаторами
    public class PublicInner {
        public void showAccess() {
            System.out.println("PublicInner имеет доступ ко всем полям внешнего класса:");
            System.out.println(outerPrivate);
            System.out.println(outerProtected);
            System.out.println(outerPublic);
            System.out.println(outerPackage);
        }
    }
    
    protected class ProtectedInner {
        protected void showAccess() {
            System.out.println("ProtectedInner также имеет полный доступ");
        }
    }
    
    class PackageInner {
        void showAccess() {
            System.out.println("PackageInner тоже видит все поля внешнего");
        }
    }
    
    private class PrivateInner {
        private void showAccess() {
            System.out.println("PrivateInner тоже имеет доступ ко всем полям");
        }
    }
    
    // Практика 2 и 3 - доступ между внешним и внутренним классами
    public void testInnerClassAccess() {
        PublicInner publicInner = new PublicInner();
        publicInner.showAccess();
        
        // Внешний класс имеет доступ к public методам внутреннего
        publicInner.showAccess();
        
        // Для доступа к private полям внутреннего класса нужны геттеры
        // System.out.println(publicInner.somePrivateField); // Ошибка
    }
    
    // Практика 1 - использование извне
    public static void main(String[] args) {
        InnerClassesExample outer = new InnerClassesExample();
        
        // Public внутренний класс - доступен отовсюду
        PublicInner publicInner = outer.new PublicInner();
        publicInner.showAccess();
        
        // Protected внутренний класс - доступен в пакете и наследникам
        ProtectedInner protectedInner = outer.new ProtectedInner();
        protectedInner.showAccess();
        
        // Package-private внутренний класс - доступен только в пакете
        PackageInner packageInner = outer.new PackageInner();
        packageInner.showAccess();
        
        // Private внутренний класс - доступен только во внешнем классе
        // PrivateInner privateInner = outer.new PrivateInner(); // Ошибка!
    }
}

// Класс в другом пакете 
/*
package other;

import InnerClassesExample;

class OtherPackageClass {
    void test() {
        InnerClassesExample outer = new InnerClassesExample();
        
        // Только public внутренний класс доступен
        InnerClassesExample.PublicInner publicInner = outer.new PublicInner();
        
        // Остальные не доступны из другого пакета
        // InnerClassesExample.ProtectedInner protectedInner = outer.new ProtectedInner(); // Ошибка
        // InnerClassesExample.PackageInner packageInner = outer.new PackageInner(); // Ошибка
    }
}
*/

class SubClass extends InnerClassesExample {
    void testInheritance() {
        InnerClassesExample outer = new InnerClassesExample();
        
        // Public и protected внутренние классы доступны наследнику
        PublicInner publicInner = outer.new PublicInner();
        ProtectedInner protectedInner = outer.new ProtectedInner();
        
        // Package-private не доступен из другого пакета
        // PackageInner packageInner = outer.new PackageInner(); // Ошибка в другом пакете
    }
}