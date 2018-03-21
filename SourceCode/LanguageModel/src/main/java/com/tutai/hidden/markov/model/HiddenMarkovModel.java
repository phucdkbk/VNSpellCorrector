/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tutai.hidden.markov.model;

import com.tutai.graph.model.Observation;
import com.tutai.graph.model.State;
import java.util.Map;
import java.util.Set;

/**
 *
 * @author Administrator
 */
public class HiddenMarkovModel {

    public Set<State> setStates;
    public double[][] transitionMaxtrix;
    public Set<Observation> vocabulary;
    public Map<State, Map<Observation, Double>> tramissionMatrix;

}
