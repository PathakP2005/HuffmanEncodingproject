package com.Huffmanproject.decoding;


import com.Huffmanproject.encoding.HuffmanNode;
import com.Huffmanproject.utils.BitInputStream;

import java.io.*;
import java.util.HashMap;
import java.util.Map;


public class HuffmanDecoder {
    public void decodeFile(File compressed, File output) {
        try {
            BitInputStream in = new BitInputStream(new FileInputStream(compressed));
            int size = in.readInt();

            Map<Character, Integer> freq = new HashMap<>();
            for (int i = 0; i < size; i++) {
                char c = in.readChar();
                int f = in.readInt();
                freq.put(c, f);
            }

            HuffmanNode root = buildHuffmanTree(freq);
            FileWriter writer = new FileWriter(output);

            HuffmanNode current = root;
            int bit;
            while ((bit = in.readBit()) != -1) {
                current = bit == 0 ? current.left : current.right;

                if (current.left == null && current.right == null) {
                    writer.write(current.ch);
                    current = root;
                }
            }
            writer.close();
            in.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private HuffmanNode buildHuffmanTree(Map<Character, Integer> freq) {
        java.util.PriorityQueue<HuffmanNode> pq = new java.util.PriorityQueue<>();
        for (var entry : freq.entrySet())
            pq.add(new HuffmanNode(entry.getKey(), entry.getValue()));

        while (pq.size() > 1) {
            HuffmanNode left = pq.poll();
            HuffmanNode right = pq.poll();
            pq.add(new HuffmanNode(left.freq + right.freq, left, right));
        }
        return pq.poll();
    }
}
