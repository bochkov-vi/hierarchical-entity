package test;

import com.google.common.collect.Lists;
import org.junit.Before;

public class BaseHierarchicalTest {

    Hierarchical h1, h2, h11, h12, h21, h22,h3,h4;

    @Before
    public void setup() {
        h1 = new Hierarchical().setId(1);
        h2 = new Hierarchical().setId(2);
        h11 = new Hierarchical().setId(11);
        h12 = new Hierarchical().setId(12);
        h21 = new Hierarchical().setId(21);
        h22 = new Hierarchical().setId(22);

        h3 = new Hierarchical().setId(3);
        h4 = new Hierarchical().setId(4);

        h1.setChilds(Lists.newArrayList(h12, h11));
        h12.setParents(h1);
        h11.setParents(h1);

        h2.setChilds(Lists.newArrayList(h22, h21));
        h22.setParents(h2);
        h21.setParents(h2);
    }



}
