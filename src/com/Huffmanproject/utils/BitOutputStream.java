package com.Huffmanproject.utils;

import java.io.IOException;
import java.io.OutputStream;

public class BitOutputStream {
    private OutputStream output;
    private int currentByte;
    private int numBitsFilled;

    public BitOutputStream(OutputStream out) {
        output = out;
        currentByte = 0;
        numBitsFilled = 0;
    }

    public void writeBit(int bit) throws IOException {
        if (bit != 0 && bit != 1)
            throw new IllegalArgumentException("Bit must be 0 or 1");

        currentByte = (currentByte << 1) | bit;
        numBitsFilled++;

        if (numBitsFilled == 8) {
            output.write(currentByte);
            numBitsFilled = 0;
            currentByte = 0;
        }
    }

    public void writeInt(int value) throws IOException {
        for (int i = 24; i >= 0; i -= 8) {
            output.write((value >>> i) & 0xFF);
        }
    }

    public void writeChar(char value) throws IOException {
        output.write((value >>> 8) & 0xFF);
        output.write(value & 0xFF);
    }

    public void close() throws IOException {
        while (numBitsFilled != 0)
            writeBit(0);
        output.close();
    }
}

