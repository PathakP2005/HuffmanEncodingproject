package com.Huffmanproject.utils;

import java.io.IOException;
import java.io.InputStream;

public class BitInputStream {
    private InputStream input;
    private int currentByte;
    private int numBitsRemaining;

    public BitInputStream(InputStream in) {
        input = in;
        currentByte = 0;
        numBitsRemaining = 0;
    }

    public int readBit() throws IOException {
        if (numBitsRemaining == 0) {
            currentByte = input.read();
            if (currentByte == -1)
                return -1;

            numBitsRemaining = 8;
        }

        numBitsRemaining--;
        return (currentByte >>> numBitsRemaining) & 1;
    }

    public int readInt() throws IOException {
        int value = 0;
        for (int i = 0; i < 4; i++) {
            int b = input.read();
            if (b == -1) throw new IOException("Unexpected end of file");
            value = (value << 8) | b;
        }
        return value;
    }

    public char readChar() throws IOException {
        int b1 = input.read();
        int b2 = input.read();
        if (b1 == -1 || b2 == -1)
            throw new IOException("Unexpected end of file");

        return (char) ((b1 << 8) | b2);
    }

    public void close() throws IOException {
        input.close();
    }
}

