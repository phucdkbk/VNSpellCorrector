/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tutai.product.text.core.data.structure;

import java.util.Map;

/**
 *
 * @author Administrator
 */
public abstract class FeatureMapText implements FeatureMap<Integer, String> {

    protected Map<Integer, String> mapIndexFeature;
    protected Map<String, Integer> mapFeatureIndex;

    @Override
    public Integer getIndex(String feature) {
        return mapFeatureIndex.get(feature);
    }

    @Override
    public String getFeature(Integer index) {
        return mapIndexFeature.get(index);
    }

}
