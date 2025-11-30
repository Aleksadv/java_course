# Практика 1 - Классы исключений и их назначение

## Throwable
- **Базовый класс** для всех исключений и ошибок
- Содержит методы: getMessage(), printStackTrace(), getCause()

## Error
- **Критические ошибки** системы, которые обычно не обрабатываются
- Примеры: OutOfMemoryError, StackOverflowError, VirtualMachineError
- **Не проверяемые** (unchecked) - не требуют обработки

## Exception
- **Исключения**, которые можно и нужно обрабатывать
- Делится на проверяемые (checked) и непроверяемые (unchecked)

### RuntimeException (unchecked)
- **Не проверяемые исключения** - ошибки программиста
- Не требуют объявления в throws
- Примеры: 
  - NullPointerException - обращение к null
  - ArrayIndexOutOfBoundsException - выход за границы массива  
  - IllegalArgumentException - неверный аргумент
  - ClassCastException - неверное приведение типа

### Checked Exceptions
- **Проверяемые исключения** - обязательны к обработке
- Должны быть объявлены в throws или обработаны в try-catch
- Примеры:
  - IOException - ошибки ввода/вывода
  - SQLException - ошибки базы данных
  - ClassNotFoundException - класс не найден

## Когда что использовать:
- **Error** - системные сбои (не обрабатывать)
- **RuntimeException** - ошибки программиста (исправлять код)
- **Checked Exception** - внешние ошибки (обрабатывать в коде)