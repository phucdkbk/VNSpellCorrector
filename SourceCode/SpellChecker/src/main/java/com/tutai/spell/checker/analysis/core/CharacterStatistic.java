/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tutai.spell.checker.analysis.core;

import com.tutai.product.text.core.utils.TextFileUtils;
import com.tutai.spell.checker.analysis.model.StatisticResult;
import com.tutai.spell.checker.analysis.utils.Constants;
import java.io.IOException;
import java.util.Collection;

/**
 *
 * @author Administrator
 */
public class CharacterStatistic implements Statistic {

    @Override
    public StatisticResult statistic(String... files) throws IOException {
        StatisticResult<Character, Integer> statisticResult = new StatisticResult();
        for (String fileName : files) {
            Collection<String> listSentences = TextFileUtils.getSentencesFromFile(fileName);
            for (String sentence : listSentences) {
                for (char character : sentence.toCharArray()) {
                    if (statisticResult.mapResults.containsKey(character)) {
                        statisticResult.mapResults.put(character, statisticResult.mapResults.get(character) + 1);
                    } else {
                        statisticResult.mapResults.put(character, 1);
                    }
                }
            }
        }
        return statisticResult;
    }
}
