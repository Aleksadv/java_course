import java.io.*;
import java.util.*;

class HuffmanNode implements Comparable<HuffmanNode> {
    byte symbol;
    int frequency;
    HuffmanNode left, right;
    
    public HuffmanNode(byte symbol, int frequency) {
        this.symbol = symbol;
        this.frequency = frequency;
        this.left = null;
        this.right = null;
    }
    
    public HuffmanNode(int frequency, HuffmanNode left, HuffmanNode right) {
        this.symbol = -1;
        this.frequency = frequency;
        this.left = left;
        this.right = right;
    }
    
    public boolean isLeaf() {
        return left == null && right == null;
    }
    
    @Override
    public int compareTo(HuffmanNode other) {
        return this.frequency - other.frequency;
    }
}

public class HuffmanCoding {
    
    // =================== КОДИРОВАНИЕ ===================
    
    public static void encode(String inputFile, String outputFile) throws IOException {
        byte[] data = readFile(inputFile);
        Map<Byte, Integer> frequencies = countFrequencies(data);
        HuffmanNode root = buildHuffmanTree(frequencies);
        Map<Byte, String> codes = generateCodes(root);
        String encodedBits = encodeData(data, codes);
        writeCompactFile(outputFile, codes, encodedBits);
        printStats(inputFile, outputFile, data.length, codes);
    }
    
    // =================== ДЕКОДИРОВАНИЕ ===================
    
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
    
    // =================== КОМПАКТНЫЙ ФОРМАТ ===================
    
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
    
    private static String readEncodedBits(DataInputStream dis) throws IOException {
        int bitLength = dis.readInt();
        return readBitString(dis, bitLength);
    }
    
    // =================== ВСПОМОГАТЕЛЬНЫЕ МЕТОДЫ ===================
    
    private static byte[] readFile(String filename) throws IOException {
        File file = new File(filename);
        byte[] data = new byte[(int)file.length()];
        FileInputStream fis = new FileInputStream(file);
        fis.read(data);
        fis.close();
        return data;
    }
    
    private static Map<Byte, Integer> countFrequencies(byte[] data) {
        Map<Byte, Integer> frequencies = new HashMap<>();
        for (byte b : data) {
            frequencies.put(b, frequencies.getOrDefault(b, 0) + 1);
        }
        return frequencies;
    }
    
    private static HuffmanNode buildHuffmanTree(Map<Byte, Integer> frequencies) {
        PriorityQueue<HuffmanNode> pq = new PriorityQueue<>();
        
        for (Map.Entry<Byte, Integer> entry : frequencies.entrySet()) {
            pq.offer(new HuffmanNode(entry.getKey(), entry.getValue()));
        }
        
        while (pq.size() > 1) {
            HuffmanNode left = pq.poll();
            HuffmanNode right = pq.poll();
            HuffmanNode parent = new HuffmanNode(left.frequency + right.frequency, left, right);
            pq.offer(parent);
        }
        
        return pq.poll();
    }
    
    private static Map<Byte, String> generateCodes(HuffmanNode root) {
        Map<Byte, String> codes = new HashMap<>();
        if (root.isLeaf()) {
            codes.put(root.symbol, "0");
        } else {
            generateCodesRecursive(root, "", codes);
        }
        return codes;
    }
    
    private static void generateCodesRecursive(HuffmanNode node, String code, Map<Byte, String> codes) {
        if (node == null) return;
        
        if (node.isLeaf()) {
            codes.put(node.symbol, code.isEmpty() ? "0" : code);
        } else {
            generateCodesRecursive(node.left, code + "0", codes);
            generateCodesRecursive(node.right, code + "1", codes);
        }
    }
    
    private static String encodeData(byte[] data, Map<Byte, String> codes) {
        StringBuilder encoded = new StringBuilder();
        for (byte b : data) {
            encoded.append(codes.get(b));
        }
        return encoded.toString();
    }
    
    private static byte[] decodeBits(String encodedBits, HuffmanNode root) {
        List<Byte> result = new ArrayList<>();
        HuffmanNode current = root;
        
        // Особый случай: дерево из одного узла
        if (root.isLeaf()) {
            byte symbol = root.symbol;
            for (int i = 0; i < encodedBits.length(); i++) {
                result.add(symbol);
            }
            return toByteArray(result);
        }
        
        // Обычное декодирование
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
    
    private static byte[] toByteArray(List<Byte> list) {
        byte[] array = new byte[list.size()];
        for (int i = 0; i < list.size(); i++) {
            array[i] = list.get(i);
        }
        return array;
    }
    
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
        
        if (bitPos > 0) {
            currentByte <<= (8 - bitPos);
            dos.writeByte(currentByte);
        }
    }
    
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
    
    private static HuffmanNode rebuildTreeFromCodes(Map<Byte, String> codes) {
        if (codes.size() == 1) {
            return new HuffmanNode(codes.keySet().iterator().next(), 0);
        }
        
        HuffmanNode root = new HuffmanNode((byte)-1, 0);
        
        for (Map.Entry<Byte, String> entry : codes.entrySet()) {
            byte symbol = entry.getKey();
            String code = entry.getValue();
            HuffmanNode current = root;
            
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
            
            current.symbol = symbol;
        }
        
        return root;
    }
    
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
    
    private static String getCharRepresentation(byte b) {
        int unsigned = b & 0xFF;
        if (b >= 32 && b <= 126) {
            char c = (char) b;
            if (c == '\\') return "'\\\\'";
            if (c == '\'') return "'\\''";
            return "'" + c + "'";
        }
        switch (unsigned) {
            case 0: return "NUL";
            case 9: return "TAB";
            case 10: return "LF";
            case 13: return "CR";
            case 32: return "SPACE";
        }
        return String.format("0x%02X", b);
    }
    
    // =================== ТЕСТОВЫЕ ФАЙЛЫ ===================
    
    public static void createTestFiles() throws IOException {
        FileOutputStream fos1 = new FileOutputStream("test1.txt");
        for (int i = 0; i < 10; i++) fos1.write('1');
        fos1.close();
        System.out.println("Создан test1.txt: 10 символов '1'");
        
        FileOutputStream fos2 = new FileOutputStream("test2.txt");
        for (int i = 0; i < 10; i++) fos2.write('1');
        for (int i = 0; i < 5; i++) fos2.write('2');
        for (int i = 0; i < 5; i++) fos2.write('3');
        fos2.close();
        System.out.println("Создан test2.txt: 10x'1', 5x'2', 5x'3'");
        
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