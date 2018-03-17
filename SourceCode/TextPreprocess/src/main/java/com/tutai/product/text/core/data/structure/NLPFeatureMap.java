/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tutai.product.text.core.data.structure;

import com.tutai.product.text.core.utils.FeatureMapUtils;
import java.io.IOException;

/**
 *
 * @author Administrator
 */
public class NLPFeatureMap extends FeatureMapText {

    public NLPFeatureMap(String featureIndexFile) throws IOException {
        this.mapFeatureIndex = FeatureMapUtils.getMapFeatureIndex(featureIndexFile);
        this.mapIndexFeature = FeatureMapUtils.getInverseMap(this.mapFeatureIndex);
    }

}
