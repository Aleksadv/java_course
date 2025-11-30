package thispackage;

public class SamePackageTest {
    public void test() {
        AccessDemo demo = new AccessDemo();
        
        System.out.println("=== ТЕСТ В ТОМ ЖЕ ПАКЕТЕ ===");
        System.out.println(demo.publicField);      // доступно
        System.out.println(demo.protectedField);   // доступно  
        System.out.println(demo.packageField);     // доступно
        // System.out.println(demo.privateField);  // ОШИБКА!
        
        demo.publicMethod();      // доступно
        demo.protectedMethod();   // доступно
        demo.packageMethod();     // доступно
        // demo.privateMethod();  // ОШИБКА!
    }
}