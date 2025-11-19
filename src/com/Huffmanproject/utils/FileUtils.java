package com.Huffmanproject.utils;

import java.io.*;

public class FileUtils {
    public static String readFile(File file) throws Exception {
        BufferedReader br = new BufferedReader(new FileReader(file));
        StringBuilder sb = new StringBuilder();
        String line;

        while ((line = br.readLine()) != null)
            sb.append(line).append("\n");

        br.close();
        return sb.toString();
    }
}


