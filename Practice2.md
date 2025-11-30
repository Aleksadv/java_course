## Пример использования break и continue

### Пример с while
```java
int count = 0;
while (count < 12) {
    count++;
    
    if (count == 2) {
        continue; // пропускаем число 3
    }
    
    if (count == 9) {
        break; // выходим при достижении 9
    }
    
    System.out.println("Обрабатываем: " + count);
}
// Вывод: 1, 3, 4, 5, 6, 7, 8
```