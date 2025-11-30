# Соглашения для метода equals():

1. **Рефлексивность**: x.equals(x) всегда true
2. **Симметричность**: если x.equals(y) = true, то y.equals(x) = true  
3. **Транзитивность**: если x.equals(y) = true и y.equals(z) = true, то x.equals(z) = true
4. **Непротиворечивость**: многократные вызовы x.equals(y) возвращают одинаковый результат
5. **Сравнение с null**: x.equals(null) всегда false
6. **Согласованность с hashCode()**: если x.equals(y) = true, то x.hashCode() = y.hashCode()

# Правильная реализация equals():

1. Проверить this == obj
2. Проверить obj == null  
3. Проверить getClass() == obj.getClass()
4. Привести тип и сравнить значимые поля
5. Переопределить hashCode() для согласованности