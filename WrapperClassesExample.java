/**
 * Класс для демонстрации работы с классами-оболочками в Java
 * 
 * @author Александр
 * @version 1.0
 * @since 2025
 */
public class WrapperClassesExample {

    /**
     * Практика #1: Демонстрация метода decode()
     */
    public static void demonstrateDecode() {
        System.out.println("=== ПРАКТИКА #1: МЕТОД DECODE() ===");
        
        // Декодирование различных форматов чисел
        Integer decimal = Integer.decode("42");
        Integer hex = Integer.decode("0x2A");     // Шестнадцатеричный формат
        Integer hex2 = Integer.decode("#2A");     // Альтернативный шестнадцатеричный
        Integer octal = Integer.decode("052");    // Восьмеричный формат
        
        System.out.println("Integer.decode(\"42\") = " + decimal);     // 42
        System.out.println("Integer.decode(\"0x2A\") = " + hex);       // 42
        System.out.println("Integer.decode(\"#2A\") = " + hex2);       // 42
        System.out.println("Integer.decode(\"052\") = " + octal);      // 42
        
        // Для других типов
        Long longHex = Long.decode("0x2A");
        Byte byteOctal = Byte.decode("052");
        
        System.out.println("Long.decode(\"0x2A\") = " + longHex);      // 42
        System.out.println("Byte.decode(\"052\") = " + byteOctal);     // 42
        
        // Отрицательные числа
        Integer negativeHex = Integer.decode("-0x2A");
        System.out.println("Integer.decode(\"-0x2A\") = " + negativeHex); // -42
    }

    /**
     * Практика #2: Способы создания экземпляра Boolean
     */
    public static void demonstrateBooleanCreation() {
        System.out.println("\n=== ПРАКТИКА #2: СОЗДАНИЕ BOOLEAN ===");
        
        // Способ 1: Конструктор (устаревший, не рекомендуется)
        @SuppressWarnings("deprecation")
        Boolean bool1 = new Boolean(true);
        @SuppressWarnings("deprecation")
        Boolean bool2 = new Boolean("true");
        
        // Способ 2: valueOf с примитивом
        Boolean bool3 = Boolean.valueOf(true);
        Boolean bool4 = Boolean.valueOf(false);
        
        // Способ 3: valueOf со строкой
        Boolean bool5 = Boolean.valueOf("true");
        Boolean bool6 = Boolean.valueOf("false");
        Boolean bool7 = Boolean.valueOf("TRUE");
        Boolean bool8 = Boolean.valueOf("anything"); // false
        
        // Способ 4: parseBoolean
        Boolean bool9 = Boolean.parseBoolean("true");
        Boolean bool10 = Boolean.parseBoolean("false");
        
        // Способ 5: Автоупаковка
        Boolean bool11 = true;
        Boolean bool12 = false;
        
        // Способ 6: Константы
        Boolean bool13 = Boolean.TRUE;
        Boolean bool14 = Boolean.FALSE;
        
        System.out.println("new Boolean(true): " + bool1);
        System.out.println("new Boolean(\"true\"): " + bool2);
        System.out.println("Boolean.valueOf(true): " + bool3);
        System.out.println("Boolean.valueOf(\"true\"): " + bool5);
        System.out.println("Boolean.valueOf(\"anything\"): " + bool8);
        System.out.println("Boolean.parseBoolean(\"true\"): " + bool9);
        System.out.println("Автоупаковка true: " + bool11);
        System.out.println("Boolean.TRUE: " + bool13);
        
        // Сравнение различных способов
        System.out.println("\nСравнение созданных объектов:");
        System.out.println("Boolean.TRUE == Boolean.valueOf(true): " + (Boolean.TRUE == Boolean.valueOf(true)));
        System.out.println("new Boolean(true) == Boolean.valueOf(true): " + (bool1 == Boolean.valueOf(true)));
    }

    /**
     * Практика #3: NullPointerException при автоупаковке/распаковке
     */
    public static void demonstrateNullPointerException() {
        System.out.println("\n=== ПРАКТИКА #3: NULLPOINTEREXCEPTION ===");
        
        Integer nullInteger = null;
        Double nullDouble = null;
        Boolean nullBoolean = null;
        
        System.out.println("Созданы null-объекты:");
        System.out.println("Integer nullInteger = null");
        System.out.println("Double nullDouble = null");
        System.out.println("Boolean nullBoolean = null");
        
        try {
            // Попытка автораспаковки null-объекта
            System.out.println("\nПопытка автораспаковки null Integer:");
            int i = nullInteger; // NullPointerException!
        } catch (NullPointerException e) {
            System.out.println("Поймано NullPointerException: " + e.getMessage());
        }
        
        try {
            // Использование в арифметической операции
            System.out.println("\nПопытка использовать null Double в операции:");
            double result = nullDouble + 5.0; // NullPointerException!
        } catch (NullPointerException e) {
            System.out.println("Поймано NullPointerException: " + e.getMessage());
        }
        
        try {
            // Использование в условии
            System.out.println("\nПопытка использовать null Boolean в условии:");
            if (nullBoolean) { // NullPointerException!
                System.out.println("Это не выполнится");
            }
        } catch (NullPointerException e) {
            System.out.println("Поймано NullPointerException: " + e.getMessage());
        }
        
        try {
            // Автораспаковка в вызове метода
            System.out.println("\nПопытка передать null Integer в метод:");
            processInt(nullInteger); // NullPointerException!
        } catch (NullPointerException e) {
            System.out.println("Поймано NullPointerException: " + e.getMessage());
        }
    }
    
    private static void processInt(int number) {
        System.out.println("Обработка числа: " + number);
    }

    /**
     * Практика #4: Сравнение Integer и кэширование
     */
    public static void demonstrateIntegerComparison() {
        System.out.println("\n=== ПРАКТИКА #4: СРАВНЕНИЕ INTEGER ===");
        
        // Часть 1: значение 128 (вне диапазона кэша)
        System.out.println("--- Часть 1: значение 128 ---");
        int i1 = 128;
        Integer a1 = i1;
        Integer b1 = i1;
        
        System.out.println("int i1 = 128;");
        System.out.println("Integer a1 = i1;");
        System.out.println("Integer b1 = i1;");
        
        System.out.println("a1 == i1: " + (a1 == i1));           // true - автораспаковка
        System.out.println("b1 == i1: " + (b1 == i1));           // true - автораспаковка
        System.out.println("a1 == b1: " + (a1 == b1));           // false - разные объекты!
        System.out.println("a1.equals(i1): " + a1.equals(i1));   // true - сравнение значений
        System.out.println("b1.equals(i1): " + b1.equals(i1));   // true - сравнение значений
        System.out.println("a1.equals(b1): " + a1.equals(b1));   // true - сравнение значений
        
        // Часть 2: значение 127 (в диапазоне кэша)
        System.out.println("\n--- Часть 2: значение 127 ---");
        int i2 = 127;
        Integer a2 = i2;
        Integer b2 = i2;
        
        System.out.println("int i2 = 127;");
        System.out.println("Integer a2 = i2;");
        System.out.println("Integer b2 = i2;");
        
        System.out.println("a2 == i2: " + (a2 == i2));           // true - автораспаковка
        System.out.println("b2 == i2: " + (b2 == i2));           // true - автораспаковка
        System.out.println("a2 == b2: " + (a2 == b2));           // true - один объект из кэша!
        System.out.println("a2.equals(i2): " + a2.equals(i2));   // true - сравнение значений
        System.out.println("b2.equals(i2): " + b2.equals(i2));   // true - сравнение значений
        System.out.println("a2.equals(b2): " + a2.equals(b2));   // true - сравнение значений
        
        // Демонстрация IntegerCache
        System.out.println("\n--- Демонстрация IntegerCache ---");
        System.out.println("Диапазон кэширования Integer: от " + 
            java.lang.Integer.IntegerCache.low + " до " + 
            java.lang.Integer.IntegerCache.high);
            
        // Проверка граничных значений
        Integer cached1 = 127;
        Integer cached2 = 127;
        Integer notCached1 = 128;
        Integer notCached2 = 128;
        
        System.out.println("Integer cached1 = 127; Integer cached2 = 127;");
        System.out.println("cached1 == cached2: " + (cached1 == cached2)); // true
        
        System.out.println("Integer notCached1 = 128; Integer notCached2 = 128;");
        System.out.println("notCached1 == notCached2: " + (notCached1 == notCached2)); // false
    }

    /**
     * Дополнительные примеры с классами-оболочками
     */
    public static void demonstrateAdditionalExamples() {
        System.out.println("\n=== ДОПОЛНИТЕЛЬНЫЕ ПРИМЕРЫ ===");
        
        // Сравнение через compareTo
        System.out.println("--- Сравнение через compareTo ---");
        Integer num1 = 10;
        Integer num2 = 20;
        Integer num3 = 10;
        
        System.out.println(num1 + ".compareTo(" + num2 + "): " + num1.compareTo(num2)); // -1
        System.out.println(num2 + ".compareTo(" + num1 + "): " + num2.compareTo(num1)); // 1
        System.out.println(num1 + ".compareTo(" + num3 + "): " + num1.compareTo(num3)); // 0
        
        // Преобразование в строку
        System.out.println("\n--- Преобразование в строку ---");
        int value = 123;
        String s1 = Integer.toString(value);
        String s2 = String.valueOf(value);
        String s3 = value + ""; // Конкатенация с пустой строкой
        
        System.out.println("int value = 123;");
        System.out.println("Integer.toString(value): \"" + s1 + "\"");
        System.out.println("String.valueOf(value): \"" + s2 + "\"");
        System.out.println("value + \"\": \"" + s3 + "\"");
        
        // Автоупаковка с явным указанием типа
        System.out.println("\n--- Автоупаковка с явным указанием типа ---");
        // Float f1 = 12; // Ошибка компиляции!
        Float f2 = (float) 12;
        Float f3 = 12F;
        Float f4 = 12.0f;
        
        System.out.println("Float f2 = (float) 12: " + f2);
        System.out.println("Float f3 = 12F: " + f3);
        System.out.println("Float f4 = 12.0f: " + f4);
        
        // Двойная автоупаковка/распаковка
        System.out.println("\n--- Двойная автоупаковка/распаковка ---");
        Integer boxed = 42;
        int unboxed = boxed; // Автораспаковка
        Integer reboxed = unboxed; // Автоупаковка
        
        System.out.println("Integer boxed = 42;");
        System.out.println("int unboxed = boxed;");
        System.out.println("Integer reboxed = unboxed;");
        System.out.println("reboxed == boxed: " + (reboxed == boxed)); // true (кэш)
    }

    /**
     * Основной метод программы
     */
    public static void main(String[] args) {
        System.out.println("ДЕМОНСТРАЦИЯ КЛАССОВ-ОБОЛОЧЕК В JAVA\n");
        
        demonstrateDecode();
        demonstrateBooleanCreation();
        demonstrateNullPointerException();
        demonstrateIntegerComparison();
        demonstrateAdditionalExamples();
        
        System.out.println("\n=== ЗАКЛЮЧЕНИЕ ===");
        System.out.println("IntegerCache кэширует значения от -128 до 127 для экономии памяти");
        System.out.println("Всегда используйте equals() для сравнения объектов-оболочек");
        System.out.println("Будьте осторожны с null при автораспаковке");
    }
}