package com.Huffmanproject.encoding;

import com.Huffmanproject.utils.BitOutputStream;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

public class HuffmanEncoder {

    private Map<Character, String> huffmanCodes = new HashMap<>();

    public void encodeFile(File input, File output){
        try{
            String txt = readFile(input);
            Map<Character, Integer> freq = calculateFrequency(txt);

            HuffmanNode root = buildHuffmanTree(freq);
            generateCodes(root, "");


            BitOutputStream bos = new BitOutputStream(new FileOutputStream(output));
            writeHeader(bos, freq);

            for (char c : txt.toCharArray()) {
                String code = huffmanCodes.get(c);
                for (char bit : code.toCharArray()) {
                    bos.writeBit(bit == '1' ? 1 : 0);
                }
            }
            bos.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private String readFile(File f) throws Exception {
        BufferedReader br = new BufferedReader(new FileReader(f));
        StringBuilder sb = new StringBuilder();
        String line;

        while ((line = br.readLine()) != null)
            sb.append(line).append("\n");
        br.close();
        return sb.toString();
    }

    private Map<Character, Integer> calculateFrequency(String text) {
        Map<Character, Integer> freq = new HashMap<>();
        for (char c : text.toCharArray())
            freq.put(c, freq.getOrDefault(c, 0) + 1);
        return freq;
    }

    private HuffmanNode buildHuffmanTree(Map<Character, Integer> freq) {
        PriorityQueue<HuffmanNode> pq = new PriorityQueue<>();

        for (var entry : freq.entrySet())
            pq.add(new HuffmanNode(entry.getKey(), entry.getValue()));

        while (pq.size() > 1) {
            HuffmanNode left = pq.poll();
            HuffmanNode right = pq.poll();

            //New INTERNAL NODE
            HuffmanNode parent = new HuffmanNode(left.freq + right.freq,left,right);

            pq.add(parent);
        }
        return pq.poll();
    }

    private void generateCodes(HuffmanNode root, String code) {
        if (root == null) return;

        if (root.left == null && root.right == null) {
            huffmanCodes.put(root.ch, code);
            return;
        }

        generateCodes(root.left, code + "0");
        generateCodes(root.right, code + "1");
    }

    private void writeHeader(BitOutputStream out, Map<Character, Integer> freq) throws Exception {
        out.writeInt(freq.size());
        for (var entry : freq.entrySet()) {
            out.writeChar(entry.getKey());
            out.writeInt(entry.getValue());
        }
    }
}


