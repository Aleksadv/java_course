import java.io.*;
import java.util.*;

/**
 * Класс, представляющий узел в дереве Хаффмана.
 * Используется для построения оптимального префиксного кода.
 */
class HuffmanNode implements Comparable<HuffmanNode> {
    /** Исходный символ (байт). Для внутренних узлов значение -1 */
    byte symbol;
    
    /** Частота встречаемости символа в исходных данных */
    int frequency;
    
    /** Левый потомок узла (соответствует биту 0) */
    HuffmanNode left;
    
    /** Правый потомок узла (соответствует биту 1) */
    HuffmanNode right;
    
    /**
     * Конструктор для листового узла (символ с частотой)
     * 
     * @param symbol исходный символ (байт)
     * @param frequency частота встречаемости символа
     */
    public HuffmanNode(byte symbol, int frequency) {
        this.symbol = symbol;
        this.frequency = frequency;
        this.left = null;
        this.right = null;
    }
    
    /**
     * Конструктор для внутреннего узла (объединяет два поддерева)
     * 
     * @param frequency суммарная частота потомков
     * @param left левый потомок (поддерево)
     * @param right правый потомок (поддерево)
     */
    public HuffmanNode(int frequency, HuffmanNode left, HuffmanNode right) {
        this.symbol = -1;
        this.frequency = frequency;
        this.left = left;
        this.right = right;
    }
    
    /**
     * Проверяет, является ли узел листом (не имеет потомков)
     * 
     * @return true если узел листовой, иначе false
     */
    public boolean isLeaf() {
        return left == null && right == null;
    }
    
    /**
     * Сравнение узлов по частоте для приоритетной очереди
     * Узлы с меньшей частотой имеют более высокий приоритет
     * 
     * @param other другой узел для сравнения
     * @return результат сравнения частот (отрицательное, если текущий меньше)
     */
    @Override
    public int compareTo(HuffmanNode other) {
        return this.frequency - other.frequency;
    }
}

/**
 * Главный класс, реализующий алгоритм кодирования и декодирования Хаффмана.
 * Поддерживает сжатие и распаковку файлов через командную строку.
 * Использует компактный формат хранения: только таблица кодов и сжатые данные.
 */
public class HuffmanCoding {
    
    // =================== ПУБЛИЧНЫЕ МЕТОДЫ ===================
    
    /**
     * Кодирует исходный файл используя алгоритм Хаффмана.
     * Создает сжатый файл с таблицей кодирования в компактном формате.
     * 
     * @param inputFile путь к исходному файлу
     * @param outputFile путь для сохранения закодированного файла
     * @throws IOException если произошла ошибка ввода-вывода
     */
    public static void encode(String inputFile, String outputFile) throws IOException {
        byte[] data = readFile(inputFile);
        Map<Byte, Integer> frequencies = countFrequencies(data);
        HuffmanNode root = buildHuffmanTree(frequencies);
        Map<Byte, String> codes = generateCodes(root);
        String encodedBits = encodeData(data, codes);
        writeCompactFile(outputFile, codes, encodedBits);
        printStats(inputFile, outputFile, data.length, codes);
    }
    
    /**
     * Декодирует файл, закодированный алгоритмом Хаффмана.
     * Восстанавливает исходные данные используя таблицу кодирования.
     * 
     * @param inputFile путь к закодированному файлу
     * @param outputFile путь для сохранения раскодированного файла
     * @throws IOException если произошла ошибка ввода-вывода
     */
    public static void decode(String inputFile, String outputFile) throws IOException {
        DataInputStream dis = new DataInputStream(new FileInputStream(inputFile));
        Map<Byte, String> codes = readCodeTable(dis);
        String encodedBits = readEncodedBits(dis);
        dis.close();
        
        HuffmanNode root = rebuildTreeFromCodes(codes);
        byte[] decodedData = decodeBits(encodedBits, root);
        
        FileOutputStream fos = new FileOutputStream(outputFile);
        fos.write(decodedData);
        fos.close();
        
        System.out.println("Файл декодирован: " + inputFile + " -> " + outputFile);
        System.out.println("Размер: " + decodedData.length + " байт");
    }
    
    // =================== КОМПАКТНЫЙ ФОРМАТ ФАЙЛА ===================
    
    /**
     * Записывает закодированные данные в компактном формате.
     * Формат: [1 байт: кол-во символов] + [таблица кодов] + [4 байта: длина данных] + [данные]
     * 
     * @param filename имя выходного файла
     * @param codes таблица кодов Хаффмана (символ -> код)
     * @param encodedBits закодированные данные в виде строки битов
     * @throws IOException если произошла ошибка записи
     */
    private static void writeCompactFile(String filename, Map<Byte, String> codes, 
                                        String encodedBits) throws IOException {
        DataOutputStream dos = new DataOutputStream(new FileOutputStream(filename));
        dos.writeByte(codes.size());
        
        for (Map.Entry<Byte, String> entry : codes.entrySet()) {
            dos.writeByte(entry.getKey());
            dos.writeByte(entry.getValue().length());
            writeBitString(dos, entry.getValue());
        }
        
        // Длина данных в битах (4 байта)
        dos.writeInt(encodedBits.length());
        writeBitString(dos, encodedBits);
        dos.close();
    }
    
    /**
     * Читает таблицу кодов из закодированного файла.
     * 
     * @param dis поток для чтения данных
     * @return таблица кодов Хаффмана (символ -> код)
     * @throws IOException если произошла ошибка чтения или файл поврежден
     */
    private static Map<Byte, String> readCodeTable(DataInputStream dis) throws IOException {
        Map<Byte, String> codes = new HashMap<>();
        int count = dis.readByte() & 0xFF;
        
        for (int i = 0; i < count; i++) {
            byte symbol = dis.readByte();
            int codeLength = dis.readByte() & 0xFF;
            String code = readBitString(dis, codeLength);
            codes.put(symbol, code);
        }
        
        return codes;
    }
    
    /**
     * Читает закодированные данные из файла.
     * 
     * @param dis поток для чтения данных
     * @return строка битов, представляющая закодированные данные
     * @throws IOException если произошла ошибка чтения
     */
    private static String readEncodedBits(DataInputStream dis) throws IOException {
        int bitLength = dis.readInt();
        return readBitString(dis, bitLength);
    }
    
    // =================== ОСНОВНЫЕ МЕТОДЫ АЛГОРИТМА ===================
    
    /**
     * Читает весь файл в массив байтов.
     * 
     * @param filename путь к файлу
     * @return массив байтов содержимого файла
     * @throws IOException если файл не существует или недоступен для чтения
     */
    private static byte[] readFile(String filename) throws IOException {
        File file = new File(filename);
        byte[] data = new byte[(int)file.length()];
        FileInputStream fis = new FileInputStream(file);
        fis.read(data);
        fis.close();
        return data;
    }
    
    /**
     * Подсчитывает частоту встречаемости каждого байта в данных.
     * 
     * @param data входные данные
     * @return отображение байт -> частота встречаемости
     */
    private static Map<Byte, Integer> countFrequencies(byte[] data) {
        Map<Byte, Integer> frequencies = new HashMap<>();
        for (byte b : data) {
            frequencies.put(b, frequencies.getOrDefault(b, 0) + 1);
        }
        return frequencies;
    }
    
    /**
     * Строит дерево Хаффмана на основе частот символов.
     * Использует приоритетную очередь для построения оптимального дерева.
     * 
     * @param frequencyMap отображение символов в их частоты
     * @return корень построенного дерева Хаффмана
     */
    private static HuffmanNode buildHuffmanTree(Map<Byte, Integer> frequencyMap) {
        PriorityQueue<HuffmanNode> pq = new PriorityQueue<>();
        
        // Создаем листья для каждого символа
        for (Map.Entry<Byte, Integer> entry : frequencyMap.entrySet()) {
            pq.offer(new HuffmanNode(entry.getKey(), entry.getValue()));
        }
        
        // Строим дерево объединяя узлы с наименьшими частотами
        while (pq.size() > 1) {
            HuffmanNode left = pq.poll();
            HuffmanNode right = pq.poll();
            HuffmanNode parent = new HuffmanNode(left.frequency + right.frequency, left, right);
            pq.offer(parent);
        }
        
        return pq.poll(); // Корень дерева
    }
    
    /**
     * Генерирует коды Хаффмана для каждого символа обходя дерево.
     * Гарантирует префиксность кодов.
     * 
     * @param root корень дерева Хаффмана
     * @return таблица кодирования (символ -> код)
     */
    private static Map<Byte, String> generateCodes(HuffmanNode root) {
        Map<Byte, String> codes = new HashMap<>();
        if (root.isLeaf()) {
            // Особый случай: только один символ
            codes.put(root.symbol, "0");
        } else {
            generateCodesRecursive(root, "", codes);
        }
        return codes;
    }
    
    /**
     * Рекурсивно обходит дерево Хаффмана для генерации кодов.
     * 
     * @param node текущий узел дерева
     * @param code текущий накопленный код
     * @param codes отображение для сохранения кодов
     */
    private static void generateCodesRecursive(HuffmanNode node, String code, Map<Byte, String> codes) {
        if (node == null) return;
        
        if (node.isLeaf()) {
            codes.put(node.symbol, code.isEmpty() ? "0" : code);
        } else {
            // Рекурсивно обходим левое и правое поддерево
            generateCodesRecursive(node.left, code + "0", codes);
            generateCodesRecursive(node.right, code + "1", codes);
        }
    }
    
    /**
     * Кодирует исходные данные используя таблицу кодов Хаффмана.
     * 
     * @param data исходные данные
     * @param huffmanCodes таблица кодирования
     * @return строка битов ('0' и '1') представляющая закодированные данные
     */
    private static String encodeData(byte[] data, Map<Byte, String> huffmanCodes) {
        StringBuilder encodedBits = new StringBuilder();
        for (byte b : data) {
            encodedBits.append(huffmanCodes.get(b));
        }
        return encodedBits.toString();
    }
    
    /**
     * Декодирует данные используя дерево Хаффмана.
     * 
     * @param encodedBits закодированные данные в виде строки битов
     * @param root корень дерева Хаффмана
     * @return раскодированные данные
     */
    private static byte[] decodeBits(String encodedBits, HuffmanNode root) {
        List<Byte> result = new ArrayList<>();
        HuffmanNode current = root;
        
        // Особый случай: дерево состоит только из одного символа
        if (root.isLeaf()) {
            byte symbol = root.symbol;
            for (int i = 0; i < encodedBits.length(); i++) {
                result.add(symbol);
            }
            return toByteArray(result);
        }
        
        // Стандартное декодирование
        for (int i = 0; i < encodedBits.length(); i++) {
            char bit = encodedBits.charAt(i);
            
            if (bit == '0') {
                if (current.left == null) {
                    throw new RuntimeException("Ошибка декодирования: неверный бит " + i);
                }
                current = current.left;
            } else {
                if (current.right == null) {
                    throw new RuntimeException("Ошибка декодирования: неверный бит " + i);
                }
                current = current.right;
            }
            
            if (current.isLeaf()) {
                result.add(current.symbol);
                current = root;
            }
        }
        
        return toByteArray(result);
    }
    
    /**
     * Преобразует список Byte в массив byte.
     * 
     * @param list список байтов
     * @return массив байтов
     */
    private static byte[] toByteArray(List<Byte> list) {
        byte[] array = new byte[list.size()];
        for (int i = 0; i < list.size(); i++) {
            array[i] = list.get(i);
        }
        return array;
    }
    
    // =================== РАБОТА С БИТОВЫМИ СТРОКАМИ ===================
    
    /**
     * Записывает битовую строку в поток данных.
     * Биты упаковываются в байты (8 бит в байте).
     * 
     * @param dos поток для записи данных
     * @param bits строка битов ('0' и '1')
     * @throws IOException если произошла ошибка записи
     */
    private static void writeBitString(DataOutputStream dos, String bits) throws IOException {
        int bitPos = 0;
        byte currentByte = 0;
        
        for (int i = 0; i < bits.length(); i++) {
            char bit = bits.charAt(i);
            currentByte <<= 1;
            if (bit == '1') currentByte |= 1;
            bitPos++;
            
            if (bitPos == 8) {
                dos.writeByte(currentByte);
                currentByte = 0;
                bitPos = 0;
            }
        }
        
        // Дописываем остаток, если есть (дополняем нулями справа)
        if (bitPos > 0) {
            currentByte <<= (8 - bitPos);
            dos.writeByte(currentByte);
        }
    }
    
    /**
     * Читает битовую строку заданной длины из потока данных.
     * 
     * @param dis поток для чтения данных
     * @param bitLength количество битов для чтения
     * @return строка битов ('0' и '1')
     * @throws IOException если произошла ошибка чтения
     */
    private static String readBitString(DataInputStream dis, int bitLength) throws IOException {
        StringBuilder bits = new StringBuilder();
        int bytesToRead = (bitLength + 7) / 8;
        
        for (int i = 0; i < bytesToRead; i++) {
            byte b = dis.readByte();
            String byteStr = String.format("%8s", Integer.toBinaryString(b & 0xFF)).replace(' ', '0');
            int bitsToTake = Math.min(8, bitLength - bits.length());
            
            if (bitsToTake > 0) {
                bits.append(byteStr.substring(0, bitsToTake));
            }
        }
        
        return bits.toString();
    }
    
    // =================== ВОССТАНОВЛЕНИЕ ДЕРЕВА ===================
    
    /**
     * Восстанавливает дерево Хаффмана из таблицы кодов.
     * 
     * @param codes таблица кодирования (символ -> код)
     * @return корень восстановленного дерева Хаффмана
     */
    private static HuffmanNode rebuildTreeFromCodes(Map<Byte, String> codes) {
        // Особый случай: только один символ
        if (codes.size() == 1) {
            return new HuffmanNode(codes.keySet().iterator().next(), 0);
        }
        
        HuffmanNode root = new HuffmanNode((byte)-1, 0);
        
        for (Map.Entry<Byte, String> entry : codes.entrySet()) {
            byte symbol = entry.getKey();
            String code = entry.getValue();
            HuffmanNode current = root;
            
            // Строим путь по коду
            for (int i = 0; i < code.length(); i++) {
                char bit = code.charAt(i);
                
                if (bit == '0') {
                    if (current.left == null) {
                        current.left = new HuffmanNode((byte)-1, 0);
                    }
                    current = current.left;
                } else {
                    if (current.right == null) {
                        current.right = new HuffmanNode((byte)-1, 0);
                    }
                    current = current.right;
                }
            }
            
            // В конце пути устанавливаем символ
            current.symbol = symbol;
        }
        
        return root;
    }
    
    // =================== ВСПОМОГАТЕЛЬНЫЕ МЕТОДЫ ===================
    
    /**
     * Выводит статистику кодирования.
     * 
     * @param inputFile исходный файл
     * @param outputFile закодированный файл
     * @param originalSize оригинальный размер в байтах
     * @param codes таблица кодов Хаффмана
     * @throws IOException если не удалось получить размер файла
     */
    private static void printStats(String inputFile, String outputFile, 
                                   int originalSize, Map<Byte, String> codes) throws IOException {
        File compressedFile = new File(outputFile);
        int compressedSize = (int)compressedFile.length();
        
        System.out.println("Файл закодирован: " + inputFile + " -> " + outputFile);
        System.out.println("Исходный размер: " + originalSize + " байт");
        System.out.println("Сжатый размер: " + compressedSize + " байт");
        System.out.println("Коэффициент сжатия: " + 
            String.format("%.2f", (double)compressedSize / originalSize));
        System.out.println("Экономия: " + 
            String.format("%.1f%%", (1 - (double)compressedSize / originalSize) * 100));
        
        // Выводим таблицу кодов только для небольших алфавитов
        if (codes.size() <= 10) {
            System.out.println("\nТаблица кодов Хаффмана:");
            for (Map.Entry<Byte, String> entry : codes.entrySet()) {
                byte b = entry.getKey();
                String charRep = getCharRepresentation(b);
                System.out.println("  " + charRep + " -> " + entry.getValue());
            }
        } else {
            System.out.println("\nТаблица кодов: " + codes.size() + " символов");
        }
    }
    
    /**
     * Возвращает читаемое представление символа.
     * 
     * @param b байт-символ
     * @return строковое представление символа
     */
    private static String getCharRepresentation(byte b) {
        int unsigned = b & 0xFF;
        
        // Печатные ASCII символы
        if (b >= 32 && b <= 126) {
            char c = (char) b;
            if (c == '\\') return "'\\\\'";
            if (c == '\'') return "'\\''";
            return "'" + c + "'";
        }
        
        // Специальные символы
        switch (unsigned) {
            case 0: return "NUL";
            case 9: return "TAB";
            case 10: return "LF";
            case 13: return "CR";
            case 32: return "SPACE";
        }
        
        // Остальные символы в шестнадцатеричном формате
        return String.format("0x%02X", b);
    }
    
    // =================== ТЕСТОВЫЕ ФАЙЛЫ ===================
    
    /**
     * Создает тестовые файлы для проверки работы алгоритма.
     * Создает три файла, соответствующих требованиям задания.
     * 
     * @throws IOException если произошла ошибка создания файлов
     */
    public static void createTestFiles() throws IOException {
        // Тест 1: 10 одинаковых символов
        FileOutputStream fos1 = new FileOutputStream("test1.txt");
        for (int i = 0; i < 10; i++) fos1.write('1');
        fos1.close();
        System.out.println("Создан test1.txt: 10 символов '1'");
        
        // Тест 2: 20 символов с частотами 10, 5, 5
        FileOutputStream fos2 = new FileOutputStream("test2.txt");
        for (int i = 0; i < 10; i++) fos2.write('1');
        for (int i = 0; i < 5; i++) fos2.write('2');
        for (int i = 0; i < 5; i++) fos2.write('3');
        fos2.close();
        System.out.println("Создан test2.txt: 10x'1', 5x'2', 5x'3'");
        
        // Тест 3: бинарный файл (копия этого класса)
        FileInputStream fis = new FileInputStream("HuffmanCoding.java");
        FileOutputStream fos3 = new FileOutputStream("test3.bin");
        byte[] buffer = new byte[1024];
        int bytesRead;
        while ((bytesRead = fis.read(buffer)) != -1) {
            fos3.write(buffer, 0, bytesRead);
        }
        fis.close();
        fos3.close();
        System.out.println("Создан test3.bin: копия исходного кода");
    }
    
    // =================== ГЛАВНЫЙ МЕТОД ===================
    
    /**
     * Главный метод программы. Обрабатывает аргументы командной строки
     * и запускает соответствующие операции кодирования или декодирования.
     * 
     * @param args аргументы командной строки:
     *             -test для создания тестовых файлов
     *             -e inputFile outputFile для кодирования
     *             -d inputFile outputFile для декодирования
     */
    public static void main(String[] args) {
        if (args.length == 0) {
            System.out.println("Использование:");
            System.out.println("  Создание тестовых файлов: java HuffmanCoding -test");
            System.out.println("  Кодирование: java HuffmanCoding -e <input> <output>");
            System.out.println("  Декодирование: java HuffmanCoding -d <input> <output>");
            System.out.println("\nПримеры:");
            System.out.println("  java HuffmanCoding -test");
            System.out.println("  java HuffmanCoding -e test1.txt encoded1.huf");
            System.out.println("  java HuffmanCoding -d encoded1.huf decoded1.txt");
            return;
        }
        
        try {
            switch (args[0]) {
                case "-test":
                    createTestFiles();
                    break;
                case "-e":
                    if (args.length != 3) {
                        System.out.println("Ошибка: нужны входной и выходной файлы");
                        return;
                    }
                    encode(args[1], args[2]);
                    break;
                case "-d":
                    if (args.length != 3) {
                        System.out.println("Ошибка: нужны входной и выходной файлы");
                        return;
                    }
                    decode(args[1], args[2]);
                    break;
                default:
                    System.out.println("Неизвестная команда: " + args[0]);
            }
        } catch (Exception e) {
            System.out.println("Ошибка: " + e.getMessage());
            e.printStackTrace();
        }
    }
}