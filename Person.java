public class Person {
    private String name;
    private int age;
    private String id;
    
    public Person(String name, int age, String id) {
        this.name = name;
        this.age = age;
        this.id = id;
    }
    
    @Override
    public boolean equals(Object obj) {
        // 1. Проверка на ссылку на тот же объект
        if (this == obj) {
            return true;
        }
        
        // 2. Проверка на null
        if (obj == null) {
            return false;
        }
        
        // 3. Проверка на тот же класс
        if (this.getClass() != obj.getClass()) {
            return false;
        }
        
        // 4. Приведение типа и сравнение полей
        Person other = (Person) obj;
        return this.age == other.age &&
               this.name.equals(other.name) &&
               this.id.equals(other.id);
    }
    
    @Override
    public int hashCode() {
        // Должен быть согласован с equals()
        int result = name != null ? name.hashCode() : 0;
        result = 31 * result + age;
        result = 31 * result + (id != null ? id.hashCode() : 0);
        return result;
    }
    
    @Override
    public String toString() {
        return "Person{name='" + name + "', age=" + age + ", id='" + id + "'}";
    }
    
    public static void main(String[] args) {
        Person person1 = new Person("Иван", 25, "12345");
        Person person2 = new Person("Иван", 25, "12345");
        Person person3 = new Person("Мария", 30, "67890");
        
        System.out.println("person1.equals(person2): " + person1.equals(person2)); // true
        System.out.println("person1.equals(person3): " + person1.equals(person3)); // false
        System.out.println("person1.equals(null): " + person1.equals(null));       // false
        System.out.println("person1.equals(\"строка\"): " + person1.equals("строка")); // false
    }
}