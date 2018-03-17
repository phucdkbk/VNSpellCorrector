/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tutai.product.text.core.data.structure;

import com.tutai.product.text.core.utils.ResourceBundleUtils;
import java.io.IOException;

/**
 *
 * @author Administrator
 */
public class VietnamSyllableDictionary extends DictionaryAbstract {

    private static VietnamSyllableDictionary vnSyllableDict;

    private VietnamSyllableDictionary() throws IOException {
        super.init(ResourceBundleUtils.getValueByKey("viet.syllable.dict", "dict_config"));
    }

    public static VietnamSyllableDictionary getInstance() throws IOException {
        if (vnSyllableDict == null) {
            vnSyllableDict = new VietnamSyllableDictionary();
        }
        return vnSyllableDict;
    }
}
