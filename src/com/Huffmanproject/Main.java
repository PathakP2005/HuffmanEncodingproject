package com.Huffmanproject;


import com.Huffmanproject.encoding.HuffmanEncoder;
import com.Huffmanproject.decoding.HuffmanDecoder;

import java.io.File;

public class Main {
    public static void main(String[] args) {

        HuffmanEncoder encoder = new HuffmanEncoder();
        HuffmanDecoder decoder = new HuffmanDecoder();

        File input = new File("input.txt");
        File compressed = new File("compressed.bin");
        File output = new File("output.txt");

        encoder.encodeFile(input, compressed);
        System.out.println("Encoding Complete!");

        decoder.decodeFile(compressed, output);
        System.out.println("Decoding Complete!");
    }
}
