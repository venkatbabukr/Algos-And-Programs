package com.venkat.algos.simple.strings;

import static org.junit.Assert.*;

import org.junit.Test;

import com.venkat.utils.ext.StringExt;

public class StringAlgosTest {

	static class TestContainSubStrSeqData {
    	String mainStr;
    	String subStr;

        public TestContainSubStrSeqData(String ms, String ss) {
        	this.mainStr = ms;
        	this.subStr = ss;
        }
    }

	@Test
    public void test_isPanagramAllCases() {
        assertFalse(StringAlgos.isPanagram(null));
        assertFalse(StringAlgos.isPanagram(StringExt.EMPTY_STR));
        assertFalse(StringAlgos.isPanagram("aabbbcc"));
        assertFalse(StringAlgos.isPanagram("Bawds jog, flick quart, vex nymph"));
        assertTrue(StringAlgos.isPanagram("AbCdEfGhIjKlMnOpQrStUvWxYz"));
        assertTrue(StringAlgos.isPanagram("Bawds jog, flick quartz, vex nymph"));

        assertTrue(StringAlgos.isPanagram("Bawds Bawds jog, flick quartz, vex nymph" +
                                          "Bawds jog, flick quartz, vex nymph"));
    }

	@Test
	public void test_containsSubStrSeqEdgeCases() {
        TestContainSubStrSeqData[] allPositiveTestCases = new TestContainSubStrSeqData[] {
            new TestContainSubStrSeqData("", ""),
            new TestContainSubStrSeqData("test", "")
        };

        for (TestContainSubStrSeqData testData : allPositiveTestCases) {
        	boolean mainContainsSubSeq = StringAlgos.containsSubStrSeq(testData.mainStr, testData.subStr);
        	System.out.format("For Mainstr=%s, Substr=%s, contains=%s%n",
                                  testData.mainStr, testData.subStr,
                                  mainContainsSubSeq);
            assertTrue(mainContainsSubSeq);
        }

        TestContainSubStrSeqData[] allNegativeTestCases = new TestContainSubStrSeqData[] {
            new TestContainSubStrSeqData("", "test1")
        };

        for (TestContainSubStrSeqData testData : allNegativeTestCases) {
        	boolean mainContainsSubSeq = StringAlgos.containsSubStrSeq(testData.mainStr, testData.subStr);
        	System.out.format("For Mainstr=%s, Substr=%s, contains=%s%n",
                                  testData.mainStr, testData.subStr,
                                  mainContainsSubSeq);
            assertFalse(mainContainsSubSeq);
        }
	}

	@Test
	public void test_containsSubStrSeqPositiveCases() {
        TestContainSubStrSeqData[] allPositiveTestCases = new TestContainSubStrSeqData[] {
            new TestContainSubStrSeqData("abpcplea", "apple")
        };

        for (TestContainSubStrSeqData testData : allPositiveTestCases) {
        	boolean mainContainsSubSeq = StringAlgos.containsSubStrSeq(testData.mainStr, testData.subStr);
        	System.out.format("For Mainstr=%s, Substr=%s, contains=%s%n",
                                  testData.mainStr, testData.subStr,
                                  mainContainsSubSeq);
            assertTrue(mainContainsSubSeq);
        }

	}

	@Test
	public void test_containsSubStrSeqNegativeCases() {
        TestContainSubStrSeqData[] allPositiveTestCases = new TestContainSubStrSeqData[] {
            new TestContainSubStrSeqData("bananas", "apple")
        };

        for (TestContainSubStrSeqData testData : allPositiveTestCases) {
        	boolean mainContainsSubSeq = StringAlgos.containsSubStrSeq(testData.mainStr, testData.subStr);
        	System.out.format("For Mainstr=%s, Substr=%s, contains=%s%n",
                                  testData.mainStr, testData.subStr,
                                  mainContainsSubSeq);
            assertFalse(mainContainsSubSeq);
        }

	}

}
