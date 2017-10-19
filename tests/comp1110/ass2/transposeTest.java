package comp1110.ass2;


import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.Timeout;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;

/**
 * Test objective:
 *
 * A piece takes up 9 possible letter positions on the board.
 * Determine whether a piece and its given array, i.e AAl -> [1, 2, 0,
 * when transposed, ensures the piece and its peg locations   2, 1, 2,
 * are validly spun around,                                   1, 0, 0]
 *
 * where "1" defines a part of the piece placed on a lower peg, and
 * where "2" defines a part of the piece placed on an upper location, and
 * where "0" defines a board letter that the piece does not take up.
 *
 * We test to ensure:
 * - the piece rotates properly.
 * - the resulting array is also of length 9.
 * - the piece can repeatedly be spun using the same function.
 * - the piece values stay the same.
 */


// Reference: Lecture Material
public class transposeTest {

    int[] simple = {1,2,0,2,1,2,1,0,0};
    int[] simpleRotatedOnce = {1,2,1,0,1,2,0,2,0};
    int[] simpleRotatedTwice = {0,0,1,2,1,2,0,2,1};
    int[] simpleRotatedThrice = {0,2,0,2,1,0,1,2,1};
    int[] simpleRotatedFour = simple;

    int[] rotOnce = StepsGame.transpose(simple);
    int[] rotTwice = StepsGame.transpose(rotOnce);
    int[] rotThrice = StepsGame.transpose(rotTwice);
    int[] rotFour = StepsGame.transpose(rotThrice);

    String s = Arrays.toString(simple);
    String s1 = Arrays.toString(simpleRotatedOnce);
    String s2 = Arrays.toString(simpleRotatedTwice);
    String s3 = Arrays.toString(simpleRotatedThrice);

    String r1 = Arrays.toString(rotOnce);
    String r2 = Arrays.toString(rotTwice);
    String r3 = Arrays.toString(rotThrice);
    String r4 = Arrays.toString(rotFour);


    @Rule
    public Timeout globalTimeout = Timeout.millis(2000);


    @Test
    public void testSimple() {
        assertTrue(s + " transposed 90 degrees should be " + s1 + " but was " + r1, Arrays.equals(rotOnce, simpleRotatedOnce));
    }

    @Test
    public void TestMultipleRotations() {
        assertTrue(s + " transposed 180 degrees should be " + s2 + " but was " + r2, Arrays.equals(rotTwice, simpleRotatedTwice));
        assertTrue(s + " transposed 270 degrees should be " + s3 + " but was " + r3, Arrays.equals(rotThrice, simpleRotatedThrice));
        assertTrue(s + " transposed 360 degrees should be " + s + " but was " + r4, Arrays.equals(rotFour, simple));
    }

    @Test
    public void TestLength() {
        assertFalse("Length of resulting transposed piece array should be 9, but was " + rotOnce.length, rotOnce.length != 9);
    }

    @Test
    public void TestVals() {
        assertTrue(s1 + " should have the same values as " + r1 + ", but doesn't.",s1.equals(r1));
        assertFalse(s1 + "should not have the same values as " + r2 + ", but does.",s1.equals(r2));
        assertTrue(s3 + " should have the same values as " + r3 + ", but doesn't.",s3.equals(r3));
        assertTrue(s + " should have the same values as " + r4 + ", but doesn't.",s.equals(r4));
    }
}
