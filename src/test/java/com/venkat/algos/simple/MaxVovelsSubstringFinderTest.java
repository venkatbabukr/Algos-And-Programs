package com.venkat.algos.simple;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import com.venkat.algos.simple.strings.MaxVowelsSubstringFinder;

public class MaxVovelsSubstringFinderTest {
    
    MaxVowelsSubstringFinder testFinder;

    @Before
    public void init() {
        testFinder = new MaxVowelsSubstringFinder();
    }

    @Test
    public void testEdgeCases() {
        try {
            String substr = testFinder.maxVowelsSubstr(null, 0);
            fail("Finder returning result for null string! result = " + substr);
        } catch (IllegalArgumentException e) {
            
        }

        // Test for empty input string...
        assertNull(testFinder.maxVowelsSubstr("", 2));

        // Test for asking length greater than string length...
        assertNull(testFinder.maxVowelsSubstr("aaa", 4));
        
        // Test entire string containing vowels...
        assertEquals("Failed for string having all vowels!!! ", testFinder.maxVowelsSubstr("aaaaaaa", 3), "aaa");

        // Test entire string containing no vowels...
        assertNull("Failed for string having no vowels!!! ", testFinder.maxVowelsSubstr("bcbcb", 3));

    }
    
    @Test
    public void testExpectedCases() {
        assertEquals("Failed to get substring having 3 vowels in middle", testFinder.maxVowelsSubstr("azxdeiig", 5), "xdeii");
        assertEquals("Failed to get substring having 4 vowels at the end", testFinder.maxVowelsSubstr("azxdeiiu", 5), "deiiu");
        assertEquals("Failed to get substring having 1 vowel at the beginning", testFinder.maxVowelsSubstr("azxdexig", 2), "az");
        assertEquals("Failed to get substring having 1 vowel in middle", testFinder.maxVowelsSubstr("dzaxdexig", 2), "za");
    }

}
