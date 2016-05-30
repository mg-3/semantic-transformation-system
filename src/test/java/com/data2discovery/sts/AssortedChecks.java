package com.data2discovery.sts;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Created by mgarcia on 1/31/16.
 */
public class AssortedChecks {

    private int lookup(int n) {
        return (n % 10) * 10 + (n / 10);
    }

    @Test
    public void assocJoinColumns() {
        int n = 11;
        int v = lookup(n);
        assertEquals(n,v);
        n = 15;
        v = lookup(n);
        assertEquals(51, v);
        n = 31;
        v = lookup(n);
        assertEquals(13, v);
    }

}
