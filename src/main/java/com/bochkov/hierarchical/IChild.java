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
public interface IChild<T extends IChild<T, ID>, ID extends Serializable> extends Persistable<ID> {

    List<T> getParents();

    default T setParents(List<T> parents) {
        throw new UnsupportedOperationException(getClass().getName() + " method setParents not supported");
    }
    default T setParents(T... parents) {
        return setParents(Lists.newArrayList(parents));
    }
    default boolean isCanHaveParents() {
        return true;
    }

    default boolean isChildOf(Iterable<T> childs) {
        return Hierarchicals.isChildOf((T) this, stream(childs));
    }

    default boolean isChildOf(Stream<T> childs) {
        return Hierarchicals.isChildOf((T) this, childs);
    }


    default boolean isChildOf(T... childs) {
        return Hierarchicals.isChildOf((T) this, Stream.of(childs));
    }

    default List<T> getAllParents() {
        return streamAllParents().collect(Collectors.toList());
    }

    default Stream<T> streamAllParents() {
        return Hierarchicals.streamAllParents((T) this);
    }

    default List<T> getAllParentsAndThis() {
        return streamAllParentsAndThis().collect(Collectors.toList());
    }

    default Stream<T> streamAllParentsAndThis() {
        return Hierarchicals.streamAllParents(true, (T) this);
    }

    default boolean isChildOfId(Iterable<ID> childIds) {
        return Hierarchicals.isChildOfId((T) this, childIds);
    }

    default boolean isChildOfId(Stream<ID> childIds) {
        return Hierarchicals.isChildOfId((T) this, childIds);
    }

    default boolean isChildOfId(ID... childIds) {
        return Hierarchicals.isChildOfId((T) this, childIds);
    }

    default T getFirstParent() {
        return stream(getParents()).findFirst().orElse((T) this);
    }
}
