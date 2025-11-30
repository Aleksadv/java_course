# Преобразования между String, StringBuilder и StringBuffer

## String -> StringBuilder
```java
String str = "text";
StringBuilder sb = new StringBuilder(str);
```
## String -> StringBuffer
```java
String str = "text";
StringBuffer sbf = new StringBuffer(str);
```
## StringBuilder -> String
```java
StringBuilder sb = new StringBuilder("text");
String str = sb.toString();
```
## StringBuffer -> String
```java
StringBuffer sbf = new StringBuffer("text");
String str = sbf.toString();
```
## StringBuilder -> StringBuffer
```java
StringBuilder sb = new StringBuilder("text");
StringBuffer sbf = new StringBuffer(sb.toString());
```
## StringBuffer -> StringBuilder
```java
StringBuffer sbf = new StringBuffer("text");
StringBuilder sb = new StringBuilder(sbf.toString());
```
**Особенности:**

- String immutable (неизменяемый)

- StringBuilder mutable, не потокобезопасный, быстрый

- StringBuffer mutable, потокобезопасный, медленнее

- Все преобразования через String используют метод toString()