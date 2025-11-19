package com.Huffmanproject.encoding;

public class HuffmanNode implements Comparable<HuffmanNode> {

    public char ch;
    public int freq;
    public HuffmanNode left;
    public HuffmanNode right;

    // Leaf node
    public HuffmanNode(char ch, int freq) {
        this.ch = ch;
        this.freq = freq;
        this.left = null;
        this.right = null;
    }

    // Internal node
    public HuffmanNode(int freq, HuffmanNode left, HuffmanNode right) {
        this.ch = '\0';  // no character for internal nodes
        this.freq = freq;
        this.left = left;
        this.right = right;
    }

    @Override
    public int compareTo(HuffmanNode o) {
        return this.freq - o.freq;
    }
}
