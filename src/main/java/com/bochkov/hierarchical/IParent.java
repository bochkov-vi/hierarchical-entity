package com.bochkov.hierarchical;

import com.google.common.collect.Lists;
import org.springframework.data.domain.Persistable;

import java.io.Serializable;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static com.bochkov.hierarchical.StreamSupport.stream;

/**
 * Created by home on 21.03.17.
 */
public interface IParent<T extends IParent<T, ID>, ID extends Serializable> extends Persistable<ID> {

    List<T> getChilds();

    default T setChilds(List<T> childs) {
        throw new UnsupportedOperationException(getClass().getName() + " method setChilds not supported");
    }

    default T setChilds(T... childs) {
        return setChilds(Lists.newArrayList(childs));
    }

    default boolean isCanHaveChilds() {
        return true;
    }

    default boolean isParentOf(Iterable<T> childs) {
        return Hierarchicals.isParentOf((T) this, stream(childs));
    }

    default boolean isParentOf(Stream<T> childs) {
        return Hierarchicals.isParentOf((T) this, childs);
    }


    default boolean isParentOf(T... childs) {
        return Hierarchicals.isParentOf((T) this, Stream.of(childs));
    }

    default List<T> getAllChilds() {
        return streamAllChilds().collect(Collectors.toList());
    }

    default Stream<T> streamAllChilds() {
        return Hierarchicals.streamAllChilds((T) this);
    }

    default List<T> getAllChildsAndThis() {
        return streamAllChildsAndThis().collect(Collectors.toList());
    }

    default Stream<T> streamAllChildsAndThis() {
        return Hierarchicals.streamAllChilds(true, (T) this);
    }

    default boolean isParentOfId(Iterable<ID> childIds) {
        return Hierarchicals.isParentOfId((T) this, childIds);
    }

    default boolean isParentOfId(Stream<ID> childIds) {
        return Hierarchicals.isParentOfId((T) this, childIds);
    }

    default boolean isParentOfId(ID... childIds) {
        return Hierarchicals.isParentOfId((T) this, childIds);
    }


}
