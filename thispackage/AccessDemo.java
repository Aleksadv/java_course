package thispackage;

public class AccessDemo {
    public String publicField = "public поле";
    protected String protectedField = "protected поле";
    String packageField = "package-private поле";
    private String privateField = "private поле";
    
    public void publicMethod() {
        System.out.println("public метод");
    }
    
    protected void protectedMethod() {
        System.out.println("protected метод");
    }
    
    void packageMethod() {
        System.out.println("package-private метод");
    }
    
    private void privateMethod() {
        System.out.println("private метод");
    }
    
    // Геттер для private поля
    public String getPrivateField() {
        return privateField;
    }
}