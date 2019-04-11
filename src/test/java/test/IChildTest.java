package test;

import com.google.common.collect.Lists;
import org.junit.Assert;
import org.junit.Test;

public class IChildTest extends BaseHierarchicalTest {


    @Test
    public void setChilds() {
        h1.setChilds(Lists.newArrayList(h11, h12));
        Assert.assertNotNull(h1.getChilds());
    }

    @Test
    public void setChilds1() {
        h1.setChilds(h11, h12);
        Assert.assertNotNull(h1.getChilds());
    }

    @Test
    public void isCanHaveChilds() {
        Assert.assertTrue(h1.isCanHaveChilds());
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
    public void getAllChilds() {
        Assert.assertFalse(h1.getAllChilds().isEmpty());
        Assert.assertTrue(h1.getAllChilds().size()==2);
    }


    @Test
    public void getAllChildsAndThis() {
        Assert.assertFalse(h1.getAllChilds().isEmpty());
        Assert.assertTrue(h1.getAllChildsAndThis().size()==3);
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
