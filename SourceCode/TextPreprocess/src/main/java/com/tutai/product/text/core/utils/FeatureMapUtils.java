/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tutai.product.text.core.utils;

import java.io.IOException;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author Administrator
 */
public class FeatureMapUtils {

    public static Map<String, Integer> getMapFeatureIndex(String featureIndexFile) throws IOException {
        Map<String, Integer> vocabulary = new HashMap<>();
        Collection<String> listString = TextFileUtils.getSentencesFromFile(featureIndexFile);
        for (int count = 0; count < listString.size(); count++) {
//            String[] arrString = listString.get(count).split(Constants.VOCABULARY_SEPARATOR);
//            vocabulary.put(arrString[1], Integer.valueOf(arrString[0]));
        }
        return vocabulary;
    }

    public static Map<Integer, String> getInverseMap(Map<String, Integer> mapFeatureIndex) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
