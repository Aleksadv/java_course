public class DataTypesPractice {

    // Практика #1: Примеры для всех типов данных
    static byte byteValue = 0;
    static short shortValue = 0;
    static int intValue = 0;
    static long longValue = 0L;
    static float floatValue = 0.0f;
    static double doubleValue = 0.0;
    static char charValue = '\u0000';
    static boolean boolValue = false;

    // Практика #2: Имя в формате Unicode
    public static void printNameInUnicode() {
        // Имя "Анна" в Unicode
        char a = '\u0410'; // А
        char n = '\u043D'; // н
        char n2 = '\u043D'; // н
        char a2 = '\u0430'; // а
        
        System.out.println("Мое имя в Unicode: " + a + n + n2 + a2);
    }

    // Практика #3: Область видимости переменных
    public static void scopeExample() {
        int a = 1;
        {
            int b = 2;
            // Переменная b видна только внутри этого блока
        }
        // int c = a + b; // ОШИБКА КОМПИЛЯЦИИ: переменная b не видна вне своего блока
        System.out.println("Практика #3: Переменная b не видна вне блока {}, поэтому код не скомпилируется");
    }

    // Практика #4: Перегруженный оператор + с String
    public static void stringConcatenation() {
        String name = "Анна";
        int age = 25;
        double height = 1.65;
        boolean isStudent = true;
        Object obj = new Object();
        
        String result1 = "Имя: " + name;
        String result2 = "Возраст: " + age;
        String result3 = "Рост: " + height + " м";
        String result4 = "Студент: " + isStudent;
        String result5 = "Объект: " + obj;
        
        System.out.println("Практика #4:");
        System.out.println(result1);
        System.out.println(result2);
        System.out.println(result3);
        System.out.println(result4);
        System.out.println(result5);
    }

    // Практика #5: Арифметические операции с разными типами
    public static void arithmeticOperations() {
        byte b = 10;
        short s = 20;
        int i = 30;
        long l = 40L;
        float f = 50.5f;
        double d = 60.6;
        
        System.out.println("Практика #5:");
        
        // byte + short = int
        int result1 = b + s;
        System.out.println("byte + short = int: " + b + " + " + s + " = " + result1);
        
        // int + long = long
        long result2 = i + l;
        System.out.println("int + long = long: " + i + " + " + l + " = " + result2);
        
        // long + float = float
        float result3 = l + f;
        System.out.println("long + float = float: " + l + " + " + f + " = " + result3);
        
        // float + double = double
        double result4 = f + d;
        System.out.println("float + double = double: " + f + " + " + d + " = " + result4);
        
        // char + int = int
        char ch = 'A'; // Unicode 65
        int result5 = ch + i;
        System.out.println("char + int = int: '" + ch + "' + " + i + " = " + result5);
    }

    // Практика #6: Сужающее преобразование типов
    public static void narrowingConversion() {
        System.out.println("Практика #6:");
        
        int largeInt = 1000;
        byte smallByte = (byte) largeInt; // Потеря данных!
        System.out.println("int " + largeInt + " -> byte: " + smallByte);
        
        double largeDouble = 123.456;
        int intFromDouble = (int) largeDouble; // Дробная часть отбрасывается
        System.out.println("double " + largeDouble + " -> int: " + intFromDouble);
        
        long bigLong = 3000000000L;
        int intFromLong = (int) bigLong; // Переполнение!
        System.out.println("long " + bigLong + " -> int: " + intFromLong);
    }

    // Практика #7: Преобразование типов в выражениях
    public static void typeConversionExample() {
        System.out.println("Практика #7:");
        
        int a = 120;
        
        // byte b = a + 10; // ОШИБКА КОМПИЛЯЦИИ: a + 10 дает int, требуется явное преобразование
        
        byte c = (byte)(a + 10); // Явное преобразование int -> byte
        System.out.println("byte c = (byte)(120 + 10) = " + c); // 130 в byte = -126 (переполнение)
        
        // byte d = a + 1; // ОШИБКА КОМПИЛЯЦИИ: та же причина
        
        System.out.println("Объяснение:");
        System.out.println("- b: ошибка компиляции - результат a+10 имеет тип int");
        System.out.println("- c: (byte)130 = -126 из-за переполнения byte (-128 до 127)");
        System.out.println("- d: ошибка компиляции - результат a+1 имеет тип int");
    }

    // Практика #8: Type inference с var
    public static void typeInferenceExample() {
        System.out.println("Пraктика #8:");
        
        // Type inference - компилятор сам определяет тип
        var name = "Анна"; // String
        var age = 25; // int
        var height = 1.65; // double
        var isStudent = true; // boolean
        var numbers = new int[]{1, 2, 3, 4, 5}; // int[]
        
        System.out.println("var name = \"Анна\"; // Тип: " + name.getClass().getSimpleName());
        System.out.println("var age = 25; // Тип: int");
        System.out.println("var height = 1.65; // Тип: double");
        System.out.println("var isStudent = true; // Тип: boolean");
        System.out.println("var numbers = new int[]{1, 2, 3, 4, 5}; // Тип: int[]");
        
        // var нельзя использовать без инициализации
        // var x; // ОШИБКА!
        
        // var нельзя использовать для null
        // var y = null; // ОШИБКА!
    }

    public static void main(String[] args) {
        // Практика #1: Вывод значений по умолчанию
        System.out.println("Практика #1 - Значения по умолчанию:");
        System.out.println("byte: " + byteValue);
        System.out.println("short: " + shortValue);
        System.out.println("int: " + intValue);
        System.out.println("long: " + longValue);
        System.out.println("float: " + floatValue);
        System.out.println("double: " + doubleValue);
        System.out.println("char: '" + charValue + "' (\\u0000)");
        System.out.println("boolean: " + boolValue);
        System.out.println();
        
        // Практика #2
        printNameInUnicode();
        System.out.println();
        
        // Практика #3
        scopeExample();
        System.out.println();
        
        // Практика #4
        stringConcatenation();
        System.out.println();
        
        // Практика #5
        arithmeticOperations();
        System.out.println();
        
        // Практика #6
        narrowingConversion();
        System.out.println();
        
        // Практика #7
        typeConversionExample();
        System.out.println();
        
        // Практика #8
        typeInferenceExample();
    }
}