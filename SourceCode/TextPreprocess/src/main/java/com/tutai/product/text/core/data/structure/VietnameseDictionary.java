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
public class VietnameseDictionary extends DictionaryAbstract {

    private static VietnameseDictionary vietnameseDictionary;

    private VietnameseDictionary() throws IOException {
        super.init(ResourceBundleUtils.getValueByKey("viet.dict", "dict_config"));
    }

    public static VietnameseDictionary getInstance() throws IOException {
        if (vietnameseDictionary == null) {
            vietnameseDictionary = new VietnameseDictionary();
        }
        return vietnameseDictionary;
    }
}
