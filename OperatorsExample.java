/**
 * Класс для демонстрации использования различных операторов в Java
 * 
 * @author Александр
 * @version 1.0
 * @since 2025
 */
public class OperatorsExample {

    /**
     * Демонстрация арифметических операторов
     */
    public static void demonstrateArithmeticOperators() {
        System.out.println("=== АРИФМЕТИЧЕСКИЕ ОПЕРАТОРЫ ===");
        
        int a = 15;
        int b = 4;
        
        // Базовые арифметические операции
        System.out.println(a + " + " + b + " = " + (a + b));
        System.out.println(a + " - " + b + " = " + (a - b));
        System.out.println(a + " * " + b + " = " + (a * b));
        System.out.println(a + " / " + b + " = " + (a / b));
        System.out.println(a + " % " + b + " = " + (a % b));
        
        // Операторы ++ и -- (префиксная и постфиксная форма)
        int x = 5;
        System.out.println("\nОператоры ++ и --:");
        System.out.println("Исходное значение x = " + x);
        System.out.println("x++ = " + (x++)); // Постфиксная: сначала используем, потом увеличиваем
        System.out.println("После x++: x = " + x);
        System.out.println("++x = " + (++x)); // Префиксная: сначала увеличиваем, потом используем
        System.out.println("После ++x: x = " + x);
        
        int y = 8;
        System.out.println("Исходное значение y = " + y);
        System.out.println("y-- = " + (y--)); // Постфиксная
        System.out.println("После y--: y = " + y);
        System.out.println("--y = " + (--y)); // Префиксная
        System.out.println("После --y: y = " + y);
    }

    /**
     * Демонстрация операторов сравнения
     */
    public static void demonstrateComparisonOperators() {
        System.out.println("\n=== ОПЕРАТОРЫ СРАВНЕНИЯ ===");
        
        int a = 10;
        int b = 20;
        int c = 10;
        
        System.out.println(a + " == " + b + " : " + (a == b));
        System.out.println(a + " == " + c + " : " + (a == c));
        System.out.println(a + " != " + b + " : " + (a != b));
        System.out.println(a + " < " + b + " : " + (a < b));
        System.out.println(a + " > " + b + " : " + (a > b));
        System.out.println(a + " <= " + c + " : " + (a <= c));
        System.out.println(a + " >= " + b + " : " + (a >= b));
        
        // Сравнение объектов String
        String str1 = "Hello";
        String str2 = "Hello";
        String str3 = new String("Hello");
        
        System.out.println("\nСравнение строк:");
        System.out.println("str1 = \"Hello\", str2 = \"Hello\", str3 = new String(\"Hello\")");
        System.out.println("str1 == str2 : " + (str1 == str2)); // true - ссылки на один литерал
        System.out.println("str1 == str3 : " + (str1 == str3)); // false - разные объекты
        System.out.println("str1.equals(str3) : " + str1.equals(str3)); // true - одинаковое содержимое
    }

    /**
     * Демонстрация логических операторов
     */
    public static void demonstrateLogicalOperators() {
        System.out.println("\n=== ЛОГИЧЕСКИЕ ОПЕРАТОРЫ ===");
        
        boolean p = true;
        boolean q = false;
        
        System.out.println("p = " + p + ", q = " + q);
        System.out.println("p && q : " + (p && q)); // Логическое И
        System.out.println("p || q : " + (p || q)); // Логическое ИЛИ
        System.out.println("!p : " + (!p));         // Логическое НЕ
        System.out.println("p ^ q : " + (p ^ q));   // Логическое исключающее ИЛИ
        
        // Побитовые операторы
        int x = 5;  // 0101 в двоичной
        int y = 3;  // 0011 в двоичной
        
        System.out.println("\nПобитовые операторы:");
        System.out.println(x + " & " + y + " = " + (x & y));  // 0101 & 0011 = 0001 (1)
        System.out.println(x + " | " + y + " = " + (x | y));  // 0101 | 0011 = 0111 (7)
        System.out.println(x + " ^ " + y + " = " + (x ^ y));  // 0101 ^ 0011 = 0110 (6)
        System.out.println("~" + x + " = " + (~x));           // ~0101 = 1010 (-6 в дополнении до 2)
    }

    /**
     * Демонстрация операторов присваивания
     */
    public static void demonstrateAssignmentOperators() {
        System.out.println("\n=== ОПЕРАТОРЫ ПРИСВАИВАНИЯ ===");
        
        int a = 10;
        System.out.println("Исходное значение a = " + a);
        
        a += 5;  // a = a + 5
        System.out.println("После a += 5: a = " + a);
        
        a -= 3;  // a = a - 3
        System.out.println("После a -= 3: a = " + a);
        
        a *= 2;  // a = a * 2
        System.out.println("После a *= 2: a = " + a);
        
        a /= 4;  // a = a / 4
        System.out.println("После a /= 4: a = " + a);
        
        a %= 3;  // a = a % 3
        System.out.println("После a %= 3: a = " + a);
        
        // Конкатенация строк с +=
        String text = "Hello";
        System.out.println("\nИсходная строка: " + text);
        text += " World";
        System.out.println("После text += \" World\": " + text);
        text += "! Число: " + 42;
        System.out.println("После text += \"! Число: \" + 42: " + text);
    }

    /**
     * Демонстрация операторов сдвига
     */
    public static void demonstrateShiftOperators() {
        System.out.println("\n=== ОПЕРАТОРЫ СДВИГА ===");
        
        int num = 16; // 10000 в двоичной
        
        System.out.println("Исходное число: " + num + " (двоичное: " + Integer.toBinaryString(num) + ")");
        
        // Левый сдвиг
        int leftShift = num << 2; // 1000000 (64)
        System.out.println(num + " << 2 = " + leftShift + " (двоичное: " + Integer.toBinaryString(leftShift) + ")");
        
        // Правый сдвиг (знаковый)
        int rightShift = num >> 2; // 100 (4)
        System.out.println(num + " >> 2 = " + rightShift + " (двоичное: " + Integer.toBinaryString(rightShift) + ")");
        
        // Правый сдвиг (беззнаковый)
        int unsignedRightShift = num >>> 2; // 100 (4)
        System.out.println(num + " >>> 2 = " + unsignedRightShift + " (двоичное: " + Integer.toBinaryString(unsignedRightShift) + ")");
        
        // С отрицательным числом
        int negativeNum = -16;
        System.out.println("\nОтрицательное число: " + negativeNum);
        System.out.println(negativeNum + " >> 2 = " + (negativeNum >> 2));
        System.out.println(negativeNum + " >>> 2 = " + (negativeNum >>> 2));
    }

    /**
     * Демонстрация тернарного оператора
     */
    public static void demonstrateTernaryOperator() {
        System.out.println("\n=== ТЕРНАРНЫЙ ОПЕРАТОР ?: ===");
        
        int age = 20;
        String status = (age >= 18) ? "совершеннолетний" : "несовершеннолетний";
        System.out.println("Возраст " + age + " - " + status);
        
        int score = 85;
        String grade = (score >= 90) ? "A" : (score >= 80) ? "B" : (score >= 70) ? "C" : "D";
        System.out.println("Балл " + score + " - оценка " + grade);
        
        // Использование с разными типами
        Object result = (score > 50) ? "Сдал" : false;
        System.out.println("Результат: " + result);
    }

    /**
     * Демонстрация оператора instanceof
     */
    public static void demonstrateInstanceofOperator() {
        System.out.println("\n=== ОПЕРАТОР INSTANCEOF ===");
        
        // Базовые примеры
        String text = "Hello";
        Integer number = 42;
        Object obj = new Object();
        
        System.out.println("text instanceof String: " + (text instanceof String));
        System.out.println("number instanceof Integer: " + (number instanceof Integer));
        System.out.println("number instanceof Number: " + (number instanceof Number));
        System.out.println("obj instanceof String: " + (obj instanceof String));
        
        // Наследование
        class Animal {}
        class Dog extends Animal {}
        class Cat extends Animal {}
        
        Animal myDog = new Dog();
        Animal myCat = new Cat();
        Animal animal = new Animal();
        
        System.out.println("\nНаследование:");
        System.out.println("myDog instanceof Dog: " + (myDog instanceof Dog));
        System.out.println("myDog instanceof Animal: " + (myDog instanceof Animal));
        System.out.println("myCat instanceof Cat: " + (myCat instanceof Cat));
        System.out.println("animal instanceof Dog: " + (animal instanceof Dog));
        
        // Особый случай с null
        System.out.println("\nРабота с null:");
        String nullString = null;
        System.out.println("nullString instanceof String: " + (nullString instanceof String));
        
        Integer nullInteger = null;
        System.out.println("nullInteger instanceof Integer: " + (nullInteger instanceof Integer));
        
        Object nullObject = null;
        System.out.println("nullObject instanceof Object: " + (nullObject instanceof Object));
        
        // Массивы
        int[] array = new int[5];
        String[] stringArray = new String[3];
        
        System.out.println("\nМассивы:");
        System.out.println("array instanceof int[]: " + (array instanceof int[]));
        System.out.println("stringArray instanceof String[]: " + (stringArray instanceof String[]));
        System.out.println("stringArray instanceof Object: " + (stringArray instanceof Object));
    }

    /**
     * Демонстрация приоритета операторов
     */
    public static void demonstrateOperatorPrecedence() {
        System.out.println("\n=== ПРИОРИТЕТ ОПЕРАТОРОВ ===");
        
        int a = 2, b = 3, c = 4;
        
        // Разные приоритеты
        int result1 = a + b * c;      // 2 + (3 * 4) = 14
        int result2 = (a + b) * c;    // (2 + 3) * 4 = 20
        
        System.out.println("a + b * c = " + result1 + " (умножение имеет высший приоритет)");
        System.out.println("(a + b) * c = " + result2 + " (скобки изменяют приоритет)");
        
        // Сложное выражение
        boolean complex = a > 1 && b < 4 || c == 5;
        System.out.println("a > 1 && b < 4 || c == 5 = " + complex);
        
        // Комбинация разных операторов
        int x = 5;
        int y = x++ * 2 + --x;
        System.out.println("x++ * 2 + --x при x=5: " + y);
    }

    /**
     * Основной метод программы
     */
    public static void main(String[] args) {
        System.out.println("ДЕМОНСТРАЦИЯ ОПЕРАТОРОВ В JAVA\n");
        
        demonstrateArithmeticOperators();
        demonstrateComparisonOperators();
        demonstrateLogicalOperators();
        demonstrateAssignmentOperators();
        demonstrateShiftOperators();
        demonstrateTernaryOperator();
        demonstrateInstanceofOperator();
        demonstrateOperatorPrecedence();
        
        System.out.println("\n=== ДОПОЛНИТЕЛЬНЫЕ ПРИМЕРЫ ===");
        
        // Конкатенация строк с разными типами
        String name = "Александр";
        int age = 25;
        double height = 1.75;
        boolean isStudent = true;
        
        String info = "Имя: " + name + ", Возраст: " + age + ", Рост: " + height + ", Студент: " + isStudent;
        System.out.println("Конкатенация строк: " + info);
        
        // Операторы с классами-оболочками
        Integer num1 = 10;
        Integer num2 = 20;
        System.out.println("Классы-оболочки: " + num1 + " + " + num2 + " = " + (num1 + num2));
        
        // Автораспаковка
        int result = num1 + num2; // Автораспаковка Integer в int
        System.out.println("Автораспаковка: " + result);
    }
}