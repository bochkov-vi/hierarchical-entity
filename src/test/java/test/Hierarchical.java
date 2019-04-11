package test;

import com.bochkov.hierarchical.IHierarchical;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.util.List;


@Accessors(chain = true)
public class Hierarchical implements IHierarchical<Integer, Hierarchical> {

    @Getter
    @Setter
    Integer id;

    @Getter
    @Setter
    List<Hierarchical> childs;

    @Getter
    @Setter
    List<Hierarchical> parents;


    @Override
    public boolean isNew() {
        return false;
    }
}
