class Employee {
    public void work(){
        System.out.println("Employee works");
    }
}

class Developer extends Employee {
    @Override
    public void work(){
        System.out.println("Developer writes code");
    }
}

class Manager extends Employee {
    @Override
    public void work(){
        System.out.println("Manager coordinates work");
    }
}

public class LiskovExample{
    public static void main(String[] args){
        Employee employee_developer = new Developer();
        Employee employee_manager = new Manager();

        employee_developer.work();
        employee_manager.work();
        
        System.out.println("Liskov principle demonstration");
    }
}