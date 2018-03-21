/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tutai.statistic.core.process;

import com.tutai.statistic.core.model.StatisticParam;
import java.util.Collection;

/**
 *
 * @author Administrator
 */
public class StatisticUtils {

    public static StatisticParam getStatisticInfo(Collection<Double> setNumbers) {
        StatisticParam statisticInfo = new StatisticParam();
        double expectation = 0;
        double standardDeviation = 0;
        for (Double number : setNumbers) {
            expectation += number;
        }
        expectation = expectation / setNumbers.size();
        statisticInfo.expectation = expectation;
        for (Double number : setNumbers) {
            standardDeviation += Math.pow(number - expectation, 2);
        }
        standardDeviation = Math.pow(standardDeviation / setNumbers.size(), 0.5);
        statisticInfo.standardDeviation = standardDeviation;
        return statisticInfo;
    }

}
