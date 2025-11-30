import java.util.*;

// Практика #1: instanceof с параметризованными классами
class Container<T> {
    private T content;
    
    public Container(T content) {
        this.content = content;
    }
    
    public T getContent() {
        return content;
    }
    
    public void checkType() {
        // instanceof с generic типом
        if (content instanceof String) {
            System.out.println("Содержимое является строкой: " + content);
        } else if (content instanceof Integer) {
            System.out.println("Содержимое является числом: " + content);
        } else if (content instanceof List) {
            System.out.println("Содержимое является списком");
        }
    }
}

// Практика #2: extends и super ограничения
class NumberBox<T extends Number> {
    private T number;
    
    public NumberBox(T number) {
        this.number = number;
    }
    
    public double getSquare() {
        return number.doubleValue() * number.doubleValue();
    }
}

class Storage<T> {
    private List<T> items = new ArrayList<>();
    
    public void addItem(T item) {
        items.add(item);
    }
    
    // <? extends T> - можно читать, но нельзя добавлять
    public void copyFrom(Collection<? extends T> source) {
        for (T item : source) {
            items.add(item);
        }
    }
    
    // <? super T> - можно добавлять, но читать только как Object
    public void copyTo(Collection<? super T> destination) {
        destination.addAll(items);
    }
    
    public void printItems() {
        System.out.println("Элементы: " + items);
    }
}

public class GenericExamples {
    public static void main(String[] args) {
        System.out.println("=== Практика #1: instanceof с generic классами ===\n");
        
        // Создаем контейнеры разных типов
        Container<String> stringContainer = new Container<>("Hello");
        Container<Integer> intContainer = new Container<>(42);
        Container<List<String>> listContainer = new Container<>(Arrays.asList("A", "B"));
        
        // Проверяем типы с помощью instanceof
        stringContainer.checkType();
        intContainer.checkType();
        listContainer.checkType();
        
        System.out.println("\n=== Практика #2: extends и super ограничения ===\n");
        
        // <? extends T> пример
        List<Integer> integers = Arrays.asList(1, 2, 3);
        List<Double> doubles = Arrays.asList(1.1, 2.2, 3.3);
        
        Storage<Number> numberStorage = new Storage<>();
        
        // Можно копировать из List<Integer> и List<Double> в Storage<Number>
        // потому что Integer и Double наследуются от Number
        numberStorage.copyFrom(integers);
        numberStorage.copyFrom(doubles);
        
        System.out.println("После копирования с <? extends Number>:");
        numberStorage.printItems();
        
        // <? super T> пример
        List<Object> objects = new ArrayList<>();
        List<Number> numbers = new ArrayList<>();
        
        // Можно копировать из Storage<Integer> в List<Object> и List<Number>
        // потому что Object и Number являются супертипами для Integer
        Storage<Integer> intStorage = new Storage<>();
        intStorage.addItem(10);
        intStorage.addItem(20);
        
        intStorage.copyTo(objects);
        intStorage.copyTo(numbers);
        
        System.out.println("List<Object> после копирования: " + objects);
        System.out.println("List<Number> после копирования: " + numbers);
        
        System.out.println("\n=== NumberBox с ограничением extends ===");
        
        NumberBox<Integer> intBox = new NumberBox<>(5);
        NumberBox<Double> doubleBox = new NumberBox<>(3.14);
        
        System.out.println("Квадрат целого: " + intBox.getSquare());
        System.out.println("Квадрат дробного: " + doubleBox.getSquare());
        
        // Ошибка компиляции - String не наследуется от Number
        // NumberBox<String> stringBox = new NumberBox<>("text");
    }
}