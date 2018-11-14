package com.bochkov.hierarchical;

import org.springframework.data.jpa.repository.Query;

import java.io.Serializable;
import java.util.List;

public interface HierarchicalRepository<T extends IHierarchical<ID, T>, ID extends Serializable> {

    @Query("select x from #{#entityName} x WHERE x.childs IS EMPTY ORDER BY x.name")
    List<T> findByChildsIsEmpty();

    @Query("select x from #{#entityName} x WHERE x.parents IS EMPTY ORDER BY x.name")
    List<T> findByParentsIsEmpty();

    @Query("select x from #{#entityName} x WHERE x.childs IS NOT EMPTY ORDER BY x.name")
    List<T> findByChildsIsNotEmpty();

    @Query("select x from #{#entityName} x WHERE x.parents IS NOT EMPTY ORDER BY x.name")
    List<T> findByParentsIsNotEmpty();
}