/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bochkov.hierarchical;


import java.util.Iterator;
import java.util.function.Function;

/**
 * @param <T>
 * @author bochkov
 */
public class ParentIteratorExtractor<T extends IChild<T,?>> implements Function<T, Iterator<T>> {


    public Iterator<T> apply(T e) {
        if (e != null && e.getParents() != null && !e.getParents().isEmpty() && e.isCanHaveParents()) {
            return e.getParents().iterator();
        } else {
            return null;
        }
    }

}
