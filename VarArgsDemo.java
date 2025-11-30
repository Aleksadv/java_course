public class VarArgsDemo {
    
    public static void displayItems(String... items) {
        System.out.print("Строки: ");
        for (String item : items) {
            System.out.print(item + " ");
        }
        System.out.println();
    }
    
    public static void displayItems(int... numbers) {
        System.out.print("Числа: ");
        for (int num : numbers) {
            System.out.print(num + " ");
        }
        System.out.println();
    }
    
    public static void displayItems(String label, int... values) {
        System.out.print(label + ": ");
        for (int val : values) {
            System.out.print(val + " ");
        }
        System.out.println();
    }
    
    public static void displayItems(String label, String delimiter, int... values) {
        System.out.print(label + ": ");
        for (int i = 0; i < values.length; i++) {
            System.out.print(values[i]);
            if (i < values.length - 1) {
                System.out.print(delimiter);
            }
        }
        System.out.println();
    }
    
    public static void displayItems() {
        System.out.println("Пустой вызов");
    }

    public static void main(String[] args) {
        displayItems();
        displayItems("X", "Y", "Z");
        displayItems(5, 10, 15);
        displayItems("Итог", 25, 35, 45);
        displayItems("Данные", " | ", 100, 200, 300);
        displayItems(new String[0]);
        displayItems(new int[0]);
    }
}

/*
Вывод:
Пустой вызов
Строки: X Y Z 
Числа: 5 10 15 
Итог: 25 35 45 
Данные: 100 | 200 | 300 
Строки: 
Числа: 
*/