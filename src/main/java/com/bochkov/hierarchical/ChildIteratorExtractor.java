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
public class ChildIteratorExtractor<T extends IParent> implements Function<T, Iterator<T>> {
    @Override
    public Iterator<T> apply(T e) {
        if (e != null && e.getChilds() != null && !e.getChilds().isEmpty() && e.isCanHaveChilds()) {
            return e.getChilds().iterator();
        } else {
            return null;
        }
    }
}
