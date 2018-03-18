/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tutai.spell.checker.analysis.model;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 *
 * @author Administrator
 * @param <K>
 * @param <V>
 */
public class StatisticResult<K, V> {

    public Map<K, V> mapResults;

    public StatisticResult() {
        this.mapResults = new HashMap<>();
    }

    public Set<K> getKeySet() {
        return mapResults.keySet();
    }

}
