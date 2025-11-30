package otherpackage;

import thispackage.AccessDemo;

public class OtherPackageTest {
    public void test() {
        AccessDemo demo = new AccessDemo();
        
        System.out.println("=== ТЕСТ В ДРУГОМ ПАКЕТЕ ===");
        System.out.println(demo.publicField);      // доступно
        // System.out.println(demo.protectedField); // ОШИБКА!
        // System.out.println(demo.packageField);   // ОШИБКА!
        // System.out.println(demo.privateField);   // ОШИБКА!
        
        demo.publicMethod();      // доступно
        // demo.protectedMethod(); // ОШИБКА!
        // demo.packageMethod();   // ОШИБКА!
        // demo.privateMethod();   // ОШИБКА!
    }
}