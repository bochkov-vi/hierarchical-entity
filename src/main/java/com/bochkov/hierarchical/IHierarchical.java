/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bochkov.hierarchical;

import java.io.Serializable;

/**
 * @param <T>
 * @author bochkov
 */
public interface IHierarchical<ID extends Serializable, T extends IHierarchical<ID, T>> extends IParent<T, ID>, IChild<T, ID> {

    default boolean isRealativeOf(Iterable<T> candidates) {
        return isParentOf(candidates) || isChildOf(candidates);
    }

    default boolean isRealativeOf(T... candidates) {
        return isParentOf(candidates) || isChildOf(candidates);
    }



}
