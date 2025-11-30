public enum Calculator {
    ADD("+") {
        public double calculate(double a, double b) {
            return a + b;
        }
    },
    SUBTRACT("-") {
        public double calculate(double a, double b) {
            return a - b;
        }
    },
    MULTIPLY("*") {
        public double calculate(double a, double b) {
            return a * b;
        }
    },
    DIVIDE("/") {
        public double calculate(double a, double b) {
            if (b == 0) throw new ArithmeticException("Деление на ноль");
            return a / b;
        }
    };
    
    private final String symbol;
    
    Calculator(String symbol) {
        this.symbol = symbol;
    }
    
    public String getSymbol() {
        return symbol;
    }
    
    public abstract double calculate(double a, double b);
    
    public static void performOperation(double a, double b, Calculator operation) {
        System.out.println(a + " " + operation.symbol + " " + b + " = " + operation.calculate(a, b));
    }
    
    public static void main(String[] args) {
        // Практика 1 - использование перечисления с инициализацией
        Status status = Status.PROCESSING;
        System.out.println("Статус: " + status.getDescription() + " (код: " + status.getCode() + ")");
        
        Status foundStatus = Status.getByCode(3);
        System.out.println("Найден по коду 3: " + foundStatus);
        
        // Практика 2 - перечисление с собственным методом
        performOperation(10, 5, Calculator.ADD);
        performOperation(10, 5, Calculator.SUBTRACT);
        performOperation(10, 5, Calculator.MULTIPLY);
        performOperation(10, 5, Calculator.DIVIDE);
    }
}