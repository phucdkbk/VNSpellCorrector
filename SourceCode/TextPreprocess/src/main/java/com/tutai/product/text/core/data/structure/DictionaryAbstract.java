/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tutai.product.text.core.data.structure;

import com.tutai.product.text.core.utils.TextFileUtils;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

/**
 *
 * @author Administrator
 */
public abstract class DictionaryAbstract implements Dictionary<String> {

    protected Set words;

    protected void init(String dictFile) throws IOException {
        this.words = new HashSet(TextFileUtils.getSentencesFromFile(dictFile));
    }
    
    @Override
    public boolean contain(String word){
        return words.contains(word);
    }

}
