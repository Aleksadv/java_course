import thispackage.AccessDemo;
import thispackage.SamePackageTest;
import otherpackage.OtherPackageTest;

public class TestMain {
    public static void main(String[] args) {
        System.out.println("ДЕМОНСТРАЦИЯ СПЕЦИФИКАТОРОВ ДОСТУПА\n");
        
        // Тест в том же пакете
        SamePackageTest sameTest = new SamePackageTest();
        sameTest.test();
        
        System.out.println();
        
        // Тест в другом пакете
        OtherPackageTest otherTest = new OtherPackageTest();
        otherTest.test();
        
        System.out.println("\n=== ИТОГ ===");
        System.out.println("public - доступно везде");
        System.out.println("protected - доступно в пакете + наследникам");
        System.out.println("(без спецификатора) - доступно только в пакете");
        System.out.println("private - доступно только в классе");
    }
}