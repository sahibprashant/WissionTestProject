package com.wission.testproject.models;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class ItemsTest {

    private Items item;
    private final String key = "key", name = "name";

    @Before
    public void init(){
        item = new Items(null, key, name);
    }

    @Test
    public void checkGetters() {
        assertEquals(item.getItemName(), name);
        assertEquals(item.getItemKey(), key);
    }

}