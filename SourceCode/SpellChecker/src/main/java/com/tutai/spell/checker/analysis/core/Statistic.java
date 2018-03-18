/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tutai.spell.checker.analysis.core;

import com.tutai.spell.checker.analysis.model.StatisticResult;

/**
 *
 * @author Administrator
 */
public interface Statistic {

    public StatisticResult statistic(String... files) throws Exception;

}
