package test;

import com.bochkov.hierarchical.Hierarchicals;
import com.bochkov.hierarchical.IChild;
import com.bochkov.hierarchical.IHierarchical;
import com.bochkov.hierarchical.IParent;
import com.google.common.collect.Lists;
import org.junit.Assert;
import org.junit.Test;

public class IParentTest extends BaseHierarchicalTest {


    @Test
    public void setChilds() {
        h1.setParents(Lists.newArrayList(h11, h12));
        Assert.assertNotNull(h1.getParents());
    }

    @Test
    public void setParents1() {
        h1.setParents(h11, h12);
        Assert.assertNotNull(h1.getParents());
    }

    @Test
    public void isCanHaveParents() {
        Assert.assertTrue(h1.isCanHaveParents());
    }

    @Test
    public void isParentOf() {
        Assert.assertTrue(h1.isParentOf(h11));
        Assert.assertTrue(h1.isParentOf(h12));
        Assert.assertFalse(h1.isParentOf(h22));
        Assert.assertFalse(h1.isParentOf((Hierarchical) null));
    }

    @Test
    public void isParentOf1() {
        Assert.assertTrue(h1.isParentOf(Lists.newArrayList(h11,h22)));
        Assert.assertTrue(h1.isParentOf(Lists.newArrayList(h11,h22)));
        Assert.assertFalse(h1.isParentOf(Lists.newArrayList(h22)));
        Assert.assertFalse(h1.isParentOf((Iterable<Hierarchical>) null));
    }



    @Test
    public void getAllParents() {
        Assert.assertFalse(h1.getAllParents().isEmpty());
        Assert.assertTrue(h1.getAllParents().size()==2);
    }


    @Test
    public void getAllParentsAndThis() {
        Assert.assertFalse(h1.getAllParents().isEmpty());
        Assert.assertTrue(h1.getAllParentsAndThis().size()==3);
    }

    @Test
    public void isParentOfId() {
        Assert.assertTrue(h1.isParentOfId(Lists.newArrayList(11,12)));
        Assert.assertTrue(h1.isParentOfId(Lists.newArrayList(11,22)));
        Assert.assertFalse(h1.isParentOfId(Lists.newArrayList(22)));
        Assert.assertFalse(h1.isParentOfId((Integer) null));
    }

    @Test
    public void isParentOfId1() {
        Assert.assertTrue(h1.isParentOfId(11,12));
        Assert.assertTrue(h1.isParentOfId(11,22));
        Assert.assertFalse(h1.isParentOfId(22));
        Assert.assertFalse(h1.isParentOfId((Integer) null));
    }

}
