/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tutai.spell.checker.analysis.utils;

import com.tutai.product.text.core.utils.TextFileUtils;
import com.tutai.spell.checker.analysis.model.StatisticResult;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 *
 * @author Administrator
 */
public class ExportUtils {

    public static void printStatisticResult(StatisticResult<String, Integer> statisticResult, String fileName) throws IOException {
        Set<String> setKeys = statisticResult.getKeySet();
        List<String> listStrResults = new ArrayList<>();
        listStrResults.addAll(setKeys);
        statisticResult.mapResults.entrySet().forEach((entry) -> {
            listStrResults.add(entry.getKey() + "\t\t\t" + entry.getValue());
        });
        TextFileUtils.writeListStringToFile(listStrResults, fileName);
    }

}
