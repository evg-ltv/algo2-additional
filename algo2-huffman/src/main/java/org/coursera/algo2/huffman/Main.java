package org.coursera.algo2.huffman;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * Creates Huffman Codes for English ABC
 */
public class Main {

    /**
     * Reads file with probabilities
     * File format:
     * [Symbol_1] [Probability_Of_Symbol_1]
     * [Symbol_2] [Probability_Of_Symbol_2]
     * ...
     */
    public static Map<String, Double> readFile(String fileName) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader(fileName));
        Map<String, Double> codes = new HashMap<String, Double>();
        String line;
        while ((line = br.readLine()) != null) {
            String[] splitLine = line.split(" ");
            codes.put(splitLine[0], Double.parseDouble(splitLine[1]));
        }
        return codes;
    }

    public static void printCodes(Map<String, String> codes) {
        for (Map.Entry<String, String> entry : codes.entrySet()) {
            System.out.printf("%s = %s%n", entry.getKey(), entry.getValue());
        }
    }

    public static void printAverageLength(Map<String, Double> symbols,
                                          Map<String, String> codes,
                                          int textLength) {
        System.out.printf("For text with %d symbols: %n", textLength);
        System.out.printf("   Number of bits without compression = %d%n",
                          averageWithoutCompression(symbols, textLength));
        System.out.printf("   Number of bits using Huffman coding = %d%n",
                          averageWithCompression(symbols, codes, textLength));
    }

    private static int averageWithoutCompression(Map<String, Double> symbols, int textLength) {
        // it's just log base 2 of number of symbols rounded up
        int numOfBitsPerSymbol = (int) Math.ceil(Math.log(symbols.size()) / Math.log(2));
        return numOfBitsPerSymbol * textLength;
    }

    private static int averageWithCompression(Map<String, Double> symbols, Map<String, String> codes, int textLength) {
        int total = 0;
        for (Map.Entry<String, Double> symbol : symbols.entrySet()) {
            int numOfBitsPerSymbol = codes.get(symbol.getKey()).length();
            int numOfSymbols = (int) (symbol.getValue() * textLength);
            total += numOfBitsPerSymbol * numOfSymbols;
        }
        return total;
    }

    public static void main(String[] args) {
        final int TEXT_LENGTH = 1000;
        try {
            Map<String, Double> symbols = readFile("data/abc_probabilities.txt");
            Map<String, String> codes = HuffmanCoding.calculateCodes(symbols);
            printCodes(codes);
            printAverageLength(symbols, codes, TEXT_LENGTH);
        }
        catch(IOException e) {
            e.printStackTrace();
        }
    }

}
