# Метод flush() - зачем нужен

## Назначение:
`flush()` принудительно записывает данные из буфера в выходной поток.

## Когда используется:

### 1. С буферизованными потоками:
```java
FileWriter writer = new FileWriter("file.txt");
writer.write("data");
writer.flush(); // Гарантированная запись на диск
```

### 2. С сетевыми соединениями:
```java
Socket socket = new Socket("host", 80);
OutputStream out = socket.getOutputStream();
out.write(data);
out.flush(); // Отправка данных немедленно
```
### 3. С Formatter:
```java
Formatter formatter = new Formatter(new FileOutputStream("file.txt"));
formatter.format("text");
formatter.flush(); // Принудительная запись
```
**Почему важен:**
- Буферизация улучшает производительность
- flush() гарантирует, что данные действительно отправлены/записаны
- Без flush() данные могут оставаться в буфере до его заполнения

**Автоматический flush:**
Некоторые классы (как PrintWriter) могут автоматически вызывать flush():

```java
PrintWriter pw = new PrintWriter(System.out, true); // autoflush
pw.println("text"); // Автоматически flush()
```