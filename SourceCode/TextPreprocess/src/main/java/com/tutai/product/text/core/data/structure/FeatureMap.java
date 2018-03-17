/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tutai.product.text.core.data.structure;

/**
 *
 * @author Administrator
 * @param <I>
 * @param <F>
 */
public interface FeatureMap<I, F> {

    public F getFeature(I index);

    public I getIndex(F feature);
}
