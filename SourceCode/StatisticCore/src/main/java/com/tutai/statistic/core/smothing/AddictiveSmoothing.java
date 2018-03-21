/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tutai.statistic.core.smothing;

/**
 *
 * @author Administrator
 */
public class AddictiveSmoothing {

    public static final float DELTA = 1;

    public static float getProb(int count, int total, int vocabularySize) {
        return (count + DELTA) / (DELTA * vocabularySize + total);
    }

}
