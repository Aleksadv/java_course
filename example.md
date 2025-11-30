```java
public class DataProcessor {
    public final int dataValue;
    
    // Способ 1: Инициализация в пустом конструкторе
    public DataProcessor() {
        this.dataValue = 100;
    }
    
    // Способ 2: Инициализация в конструкторе с параметром
    public DataProcessor(int inputValue) {
        this.dataValue = inputValue;
    }
    
    // Способ 3: Инициализация в конструкторе с логикой
    public DataProcessor(String text) {
        if (text != null) {
            this.dataValue = text.length();
        } else {
            this.dataValue = 0;
        }
    }
}

class SettingsManager {
    public final int defaultSetting;
    
    // Способ 4: Блок инициализации
    {
        defaultSetting = 50;
    }
    
    public SettingsManager() {
    }
}

class SystemConfig {
    // Способ 5: Инициализация при объявлении
    public final int maxLimit = 1000;
    
    // Способ 6: Инициализация в блоке с вычислением
    public final int calculatedValue;
    {
        calculatedValue = 42 * 2;
    }
    
    public SystemConfig() {
    }
}

class AppConstants {
    // Для статических final полей
    public final static int VERSION = 1;
    public final static int TIMEOUT;
    
    static {
        TIMEOUT = 30;
    }
}
```

Все способы инициализации final поля:
1. При объявлении:  
        public final int value = 10;

2. В конструкторе:        
        ```
         public DataClass() {
            this.value = 20;
        }
        ```

3. В параметризованном конструкторе:
```
        public DataClass(int num) {
            this.value = num;
        }
```
4. В блоке инициализации:
{
    value = 30;
}

5. С вычислениями в конструкторе:

``` 
public DataClass(boolean flag) {
    this.value = flag ? 1 : 0;
}
```