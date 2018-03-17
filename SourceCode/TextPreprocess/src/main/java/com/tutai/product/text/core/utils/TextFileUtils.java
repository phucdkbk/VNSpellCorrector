/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tutai.product.text.core.utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 *
 * @author Administrator
 */
public class TextFileUtils {

    public static Collection<String> getSentencesFromFile(File file) throws FileNotFoundException, IOException {
        List<String> listSentences = new ArrayList<>();
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(new FileInputStream(file), StandardCharsets.UTF_8));
        String line;
        while ((line = bufferedReader.readLine()) != null) {
            listSentences.add(line);
        }
        return listSentences;
    }

    public static Collection<Long> getListLongFromFile(String fileName) throws FileNotFoundException, IOException {
        Collection<String> listLines = getSentencesFromFile(new File(fileName));
        List<Long> listLongs = new ArrayList<>();
        listLines.forEach((line) -> {
            listLongs.add(Long.parseLong(line));
        });
        return listLongs;
    }

    public static Collection<String> getSentencesFromFile(String fileName) throws FileNotFoundException, IOException {
        return getSentencesFromFile(new File(fileName));
    }

    public static <T extends Object> void writeListToFile(Collection<T> datas, String fileName) throws IOException {
        PrintWriter writer = new PrintWriter(new OutputStreamWriter(new FileOutputStream(fileName), StandardCharsets.UTF_8));
        if (datas != null) {
            for (T data : datas) {
                writer.write(String.valueOf(data));
                writer.write('\n');
            }
        }
        writer.close();
    }

    public static void writeListStringToFile(Collection<String> listSentence, String fileName) throws IOException {
        PrintWriter writer = new PrintWriter(new OutputStreamWriter(new FileOutputStream(fileName), StandardCharsets.UTF_8));
        for (String str : listSentence) {
            writer.write(str);
            writer.write('\n');
        }
        writer.close();
    }

    public static void mergeMultiTextFile(String outputFile, String... inputFiles) throws IOException {
        List<String> listSentences = new ArrayList<>();
        for (String fileName : inputFiles) {
            listSentences.addAll(getSentencesFromFile(new File(fileName)));
        }
        writeListStringToFile(listSentences, outputFile);
    }
}
