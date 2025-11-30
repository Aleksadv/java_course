public class StringBuilderBufferExample {
    public static void main(String[] args) {
        System.out.println("=== Практика 1 - Методы StringBuilder/StringBuffer ===\n");
        
        // StringBuilder (не потокобезопасный, быстрее)
        StringBuilder sb = new StringBuilder("Hello");
        
        // 1. append() - добавление в конец
        sb.append(" World");
        System.out.println("1. append(): " + sb);
        
        // 2. insert() - вставка в указанную позицию
        sb.insert(5, " Java");
        System.out.println("2. insert(): " + sb);
        
        // 3. delete() - удаление части строки
        sb.delete(5, 10);
        System.out.println("3. delete(): " + sb);
        
        // 4. reverse() - переворот строки
        System.out.println("4. reverse(): " + sb.reverse());
        sb.reverse(); // Возвращаем обратно
        
        // 5. replace() - замена части строки
        sb.replace(6, 11, "Universe");
        System.out.println("5. replace(): " + sb);
        
        // 6. charAt() - получение символа по индексу
        System.out.println("6. charAt(4): " + sb.charAt(4));
        
        // 7. setCharAt() - установка символа по индексу
        sb.setCharAt(0, 'h');
        System.out.println("7. setCharAt(): " + sb);
        
        // 8. length() - получение длины
        System.out.println("8. length(): " + sb.length());
        
        // 9. capacity() - получение текущей емкости
        System.out.println("9. capacity(): " + sb.capacity());
        
        // 10. substring() - извлечение подстроки
        System.out.println("10. substring(0, 5): " + sb.substring(0, 5));
        
        System.out.println("\n=== StringBuffer (потокобезопасный) ===\n");
        
        // StringBuffer (потокобезопасный, медленнее)
        StringBuffer sbf = new StringBuffer("Test");
        
        // Те же методы что у StringBuilder
        sbf.append(" Buffer");
        sbf.insert(4, " String");
        System.out.println("StringBuffer: " + sbf);
        
        System.out.println("\n=== Практика 2 - Преобразования между типами ===\n");
        
        // String -> StringBuilder
        String str = "Original String";
        StringBuilder sbFromString = new StringBuilder(str);
        System.out.println("String -> StringBuilder: " + sbFromString);
        
        // String -> StringBuffer  
        StringBuffer sbfFromString = new StringBuffer(str);
        System.out.println("String -> StringBuffer: " + sbfFromString);
        
        // StringBuilder -> String
        String strFromSB = sbFromString.toString();
        System.out.println("StringBuilder -> String: " + strFromSB);
        
        // StringBuffer -> String
        String strFromSBF = sbfFromString.toString();
        System.out.println("StringBuffer -> String: " + strFromSBF);
        
        // StringBuilder -> StringBuffer (через String)
        StringBuffer sbfFromSB = new StringBuffer(sbFromString.toString());
        System.out.println("StringBuilder -> StringBuffer: " + sbfFromSB);
        
        // StringBuffer -> StringBuilder (через String)
        StringBuilder sbFromSBF = new StringBuilder(sbfFromString.toString());
        System.out.println("StringBuffer -> StringBuilder: " + sbFromSBF);
        
        System.out.println("\n=== Дополнительные методы ===\n");
        
        StringBuilder demo = new StringBuilder("Demo Text");
        
        // indexOf() - поиск подстроки
        System.out.println("indexOf('Text'): " + demo.indexOf("Text"));
        
        // lastIndexOf() - поиск с конца
        System.out.println("lastIndexOf('e'): " + demo.lastIndexOf("e"));
        
        // ensureCapacity() - гарантия минимальной емкости
        demo.ensureCapacity(100);
        System.out.println("ensureCapacity(100): capacity = " + demo.capacity());
        
        // trimToSize() - уменьшение емкости до размера содержимого
        demo.trimToSize();
        System.out.println("trimToSize(): capacity = " + demo.capacity());
        
        // setLength() - установка длины
        demo.setLength(4);
        System.out.println("setLength(4): " + demo);
    }
}