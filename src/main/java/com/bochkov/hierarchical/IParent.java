package com.bochkov.hierarchical;

import org.springframework.data.domain.Persistable;

import java.io.Serializable;
import java.util.List;

/**
 * Created by home on 21.03.17.
 */
public interface IParent<T extends IParent<T, ID>, ID extends Serializable> extends Persistable<ID> {
    List<T> getChilds();

    default void setChilds(List<T> parents) {
        throw new UnsupportedOperationException(getClass().getName() + " method setChilds not supported");
    }

    default boolean isCanHaveChilds() {
        return true;
    }

    default boolean isParentOf(Iterable<T> childs) {
        return Hierarchicals.isParentOf((T) this, childs);
    }

    default boolean isParentOf(T... childs) {
        return Hierarchicals.isParentOf((T) this, childs);
    }

    default List<T> getAllChilds() {
        return Hierarchicals.getAllChilds((T) this);
    }

    default List<T> getAllChildsAndThis() {
        return Hierarchicals.getAllChilds(true, (T) this);
    }

    default boolean isParentOfId(Iterable<ID> childIds) {
        return Hierarchicals.isParentOfId((IParent) this, childIds);
    }

    default boolean isParentOfId(ID... childIds) {
        return Hierarchicals.isParentOfId((IParent) this, childIds);
    }


}
