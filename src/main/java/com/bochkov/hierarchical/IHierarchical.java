/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bochkov.hierarchical;

import java.io.Serializable;

import static com.bochkov.hierarchical.StreamSupport.stream;

/**
 * @param <T>
 * @author bochkov
 */
public interface IHierarchical<ID extends Serializable, T extends IHierarchical<ID, T>> extends IParent<T, ID>, IChild<T, ID> {

    default boolean isRealativeOf(Iterable<T> candidates) {
        return anyEq(candidates) || isParentOf(candidates) || isChildOf(candidates);
    }

    default boolean isRealativeOf(T... candidates) {
        return anyEq(candidates) || isParentOf(candidates) || isChildOf(candidates);
    }

    default boolean isRealativeOfId(Iterable<ID> candidates) {
        return anyEqId(candidates) || isParentOfId(candidates) || isChildOfId(candidates);
    }

    default boolean isRealativeOfId(ID... candidates) {
        return anyEqId(candidates) || isParentOfId(candidates) || isChildOfId(candidates);
    }

    default boolean anyEqId(Iterable<ID> candidates) {
        return stream(candidates).anyMatch(c -> java.util.Objects.equals(c, getId()));
    }

    default boolean anyEqId(ID... candidates) {
        return stream(candidates).anyMatch(c -> java.util.Objects.equals(c, getId()));
    }


    default boolean anyEq(Iterable<T> candidates) {
        return stream(candidates).anyMatch(c -> java.util.Objects.equals(c, this));
    }

    default boolean anyEq(T... candidates) {
        return stream(candidates).anyMatch(c -> java.util.Objects.equals(c, this));
    }
}
