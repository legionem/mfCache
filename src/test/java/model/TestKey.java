package model;

import org.junit.Assert;
import org.junit.Test;

public class TestKey {
    @Test
    public void test() {
        String constant = "1111";
        KeyBased key = new Key(constant,0);
        Assert.assertTrue(key.isNotALive());
        KeyBased keyBased = new KeyBased(constant) {
            @Override
            public boolean isNotALive() {
                return true;
            }
        };

        Assert.assertTrue(keyBased.isNotALive());
        Assert.assertTrue(((String)key.getKey()).equals((String) keyBased.getKey()));
        Assert.assertTrue(((String) keyBased.getKey()).equals((String) key.getKey()));
        Assert.assertTrue(((String)key.getKey()).equals((String)key.getKey()));
        Assert.assertTrue(((String)keyBased.getKey()).equals((String)keyBased.getKey()));
        Assert.assertTrue(key.equals(key));
        Assert.assertTrue(keyBased.equals(keyBased));
    }

}
