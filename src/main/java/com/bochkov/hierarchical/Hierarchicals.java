/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bochkov.hierarchical;

import org.springframework.data.domain.Persistable;

import java.io.Serializable;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static com.bochkov.hierarchical.StreamSupport.stream;

/**
 * @author bochkov
 */
public class Hierarchicals {

    public static <P extends IChild<P, ID>, ID extends Serializable> boolean isChildOfId(final P child, Stream<ID> parentIds) {
        List<ID> list = stream(parentIds).collect(Collectors.toList());
        return streamAllParentIds(child).anyMatch(childId -> list.contains(childId));
    }

    static <P extends IChild<P, ID>, ID extends Serializable> boolean isChildOfId(final P child, Iterable<ID> parentIds) {
        return isChildOfId(child, stream(parentIds));
    }

    public static <P extends IChild<P, ID>, ID extends Serializable> boolean isChildOfId(final P child, ID... parentIds) {
        return isChildOfId(child, stream(parentIds));
    }

    public static <P extends IChild<P, ID>, ID extends Serializable> boolean isChildOf(final P child, final Iterable<P> parents) {
        return isChildOf(child, stream(parents));

    }

    public static <P extends IChild<P, ID>, ID extends Serializable> boolean isChildOf(final P child, final Stream<P> parents) {
        List<P> list = stream(parents).collect(Collectors.toList());
        return streamAllParents(child).anyMatch(p -> list.contains(p));

    }

    public static <P extends IChild<P, ID>, ID extends Serializable> boolean isChildOf(P child, P... parents) {
        return isChildOf(child, stream(parents));
    }

    public static <P extends IChild<P, ID>, ID extends Serializable> List<P> getAllParents(Iterable<P> childs) {
        return stream(childs).flatMap(p -> streamAllParents(p)).collect(Collectors.toList());
    }

    public static <P extends IChild<P, ID>, ID extends Serializable> List<P> getAllParents(Stream<P> childs) {
        return streamAllParents(childs).collect(Collectors.toList());
    }

    public static <P extends IChild> List<P> getAllParents(P... childs) {
        return getAllParents(stream(childs));

    }


    public static <P extends IChild<P, ID>, ID extends Serializable> List<ID> getAllParentIds(Stream<P> childs) {
        return childs.flatMap(p -> streamAllParents(p)).map(Persistable::getId).collect(Collectors.toList());
    }

    public static <P extends IChild<P, ID>, ID extends Serializable> List<ID> getAllParentIds(Iterable<P> childs) {
        return getAllParentIds(java.util.stream.StreamSupport.stream(childs.spliterator(), true));
    }

    public static <P extends IChild<P, ID>, ID extends Serializable> List<ID> getAllParentIds(P... childs) {
        return getAllParentIds(stream(childs));
    }


    public static <P extends IChild<P, ID>, ID extends Serializable> Stream<P> streamAllParents(boolean includeOriginal, Stream<P> childs) {
        if (includeOriginal) {
            return stream(childs).flatMap(child -> Stream.concat(stream(child), streamAllParents(child)));
        } else {
            return stream(childs).flatMap(child -> streamAllParents(true, child.getParents()));
        }
    }

    public static <P extends IChild<P, ID>, ID extends Serializable> Stream<P> streamAllParents(boolean includeOriginal, Iterable<P> childs) {
        return streamAllParents(includeOriginal, stream(childs));
    }

    public static <P extends IChild<P, ID>, ID extends Serializable> Stream<P> streamAllParents(boolean includeOriginal, P... childs) {
        return streamAllParents(includeOriginal, stream(childs));
    }

    public static <P extends IChild<P, ID>, ID extends Serializable> Stream<P> streamAllParents(Stream<P> childs) {
        return streamAllParents(false, stream(childs));
    }

    public static <P extends IChild<P, ID>, ID extends Serializable> Stream<P> streamAllParents(Iterable<P> childs) {
        return streamAllParents(false, stream(childs));
    }

    public static <P extends IChild<P, ID>, ID extends Serializable> Stream<P> streamAllParents(P... childs) {
        return streamAllParents(false, stream(childs));
    }


    public static <P extends IChild<P, ID>, ID extends Serializable> Stream<ID> streamAllParentIds(Stream<P> childs) {
        return streamAllParents(false, childs).map(Persistable::getId);
    }

    public static <P extends IChild<P, ID>, ID extends Serializable> Stream<ID> streamAllParentIds(Iterable<P> childs) {
        return streamAllParents(false, childs).map(Persistable::getId);
    }

    public static <P extends IChild<P, ID>, ID extends Serializable> Stream<ID> streamAllParentIds(P... childs) {
        return streamAllParents(false, childs).map(Persistable::getId);
    }


    public static <P extends IChild<P, ID>, ID extends Serializable> List<ID> getAllParentIds(
            boolean includeOriginal, Iterable<P> childs) {
        return streamAllParents(includeOriginal, childs).map(Persistable::getId).collect(Collectors.toList());
    }

    public static <P extends IChild<P, ID>, ID extends Serializable> List<ID> getAllParentIds(
            boolean includeOriginal, P... child) {
        return streamAllParents(includeOriginal, child).map(Persistable::getId).collect(Collectors.toList());
    }


    public static <P extends IChild<P, ID>, ID extends Serializable> List<P> getAllParents(
            boolean includeOriginal, Iterable<P> childs) {
        return streamAllParents(includeOriginal, childs).collect(Collectors.toList());
    }

    public static <P extends IChild<P, ID>, ID extends Serializable> List<P> getAllParents(
            boolean includeOriginal, P... childs) {
        return streamAllParents(includeOriginal, childs).collect(Collectors.toList());
    }

    //=======================================================================
    public static <P extends IParent<P, ID>, ID extends Serializable> boolean isParentOfId(final P parent, Stream<ID> childIds) {
        List<ID> list = stream(childIds).collect(Collectors.toList());
        return streamAllChildIds(parent).anyMatch(parentId -> list.contains(parentId));
    }

    public static <P extends IParent<P, ID>, ID extends Serializable> boolean isParentOfId(final P parent, Iterable<ID> childIds) {
        return isParentOfId(parent, stream(childIds));
    }

    public static <P extends IParent<P, ID>, ID extends Serializable> boolean isParentOfId(final P parent, ID... childIds) {
        return isParentOfId(parent, stream(childIds));
    }

    public static <P extends IParent<P, ID>, ID extends Serializable> boolean isParentOf(final P parent, final Iterable<P> childs) {
        return isParentOf(parent, stream(childs));

    }

    public static <P extends IParent<P, ID>, ID extends Serializable> boolean isParentOf(final P parent, final Stream<P> childs) {
        List<P> list = stream(childs).collect(Collectors.toList());
        return streamAllChilds(parent).anyMatch(p -> list.contains(p));

    }

    public static <P extends IParent<P, ID>, ID extends Serializable> boolean isParentOf(P parent, P... childs) {
        return isParentOf(parent, stream(childs));
    }

    public static <P extends IParent<P, ID>, ID extends Serializable> List<P> getAllChilds(Iterable<P> parents) {
        return stream(parents).flatMap(p -> streamAllChilds(p)).collect(Collectors.toList());
    }




    public static <P extends IParent<P, ID>, ID extends Serializable> List<P> getAllChilds(Stream<P> parents) {
        return streamAllChilds(parents).collect(Collectors.toList());
    }

    public static <P extends IParent> List<P> getAllChilds(P... parents) {
        return getAllChilds(stream(parents));

    }


    public static <P extends IParent<P, ID>, ID extends Serializable> List<ID> getAllChildIds(Stream<P> parents) {
        return parents.flatMap(p -> streamAllChilds(p)).map(Persistable::getId).collect(Collectors.toList());
    }

    public static <P extends IParent<P, ID>, ID extends Serializable> List<ID> getAllChildIds(Iterable<P> parents) {
        return getAllChildIds(java.util.stream.StreamSupport.stream(parents.spliterator(), true));
    }

    public static <P extends IParent<P, ID>, ID extends Serializable> List<ID> getAllChildIds(P... parents) {
        return getAllChildIds(stream(parents));
    }


    public static <P extends IParent<P, ID>, ID extends Serializable> Stream<P> streamAllChilds(boolean includeOriginal, Stream<P> parents) {
        if (includeOriginal) {
            return stream(parents).flatMap(parent -> Stream.concat(stream(parent), streamAllChilds(parent)));
        } else {
            return stream(parents).flatMap(parent -> streamAllChilds(true, parent.getChilds()));
        }
    }

    public static <P extends IParent<P, ID>, ID extends Serializable> Stream<P> streamAllChilds(boolean includeOriginal, Iterable<P> parents) {
        return streamAllChilds(includeOriginal, stream(parents));
    }

    public static <P extends IParent<P, ID>, ID extends Serializable> Stream<P> streamAllChilds(boolean includeOriginal, P... parents) {
        return streamAllChilds(includeOriginal, stream(parents));
    }

    public static <P extends IParent<P, ID>, ID extends Serializable> Stream<P> streamAllChilds(Stream<P> parents) {
        return streamAllChilds(false, stream(parents));
    }

    public static <P extends IParent<P, ID>, ID extends Serializable> Stream<P> streamAllChilds(Iterable<P> parents) {
        return streamAllChilds(false, stream(parents));
    }

    public static <P extends IParent<P, ID>, ID extends Serializable> Stream<P> streamAllChilds(P... parents) {
        return streamAllChilds(false, stream(parents));
    }


    public static <P extends IParent<P, ID>, ID extends Serializable> Stream<ID> streamAllChildIds(Stream<P> parents) {
        return streamAllChilds(false, parents).map(Persistable::getId);
    }

    public static <P extends IParent<P, ID>, ID extends Serializable> Stream<ID> streamAllChildIds(Iterable<P> parents) {
        return streamAllChilds(false, parents).map(Persistable::getId);
    }

    public static <P extends IParent<P, ID>, ID extends Serializable> Stream<ID> streamAllChildIds(P... parents) {
        return streamAllChilds(false, parents).map(Persistable::getId);
    }


    public static <P extends IParent<P, ID>, ID extends Serializable> List<ID> getAllChildIds(
            boolean includeOriginal, Iterable<P> parents) {
        return streamAllChilds(includeOriginal, parents).map(Persistable::getId).collect(Collectors.toList());
    }

    public static <P extends IParent<P, ID>, ID extends Serializable> List<ID> getAllChildIds(
            boolean includeOriginal, P... parent) {
        return streamAllChilds(includeOriginal, parent).map(Persistable::getId).collect(Collectors.toList());
    }


    public static <P extends IParent<P, ID>, ID extends Serializable> List<P> getAllChilds(
            boolean includeOriginal, Iterable<P> parents) {
        return streamAllChilds(includeOriginal, parents).collect(Collectors.toList());
    }

    public static <P extends IParent<P, ID>, ID extends Serializable> List<P> getAllChilds(
            boolean includeOriginal, P... parents) {
        return streamAllChilds(includeOriginal, parents).collect(Collectors.toList());
    }
}
