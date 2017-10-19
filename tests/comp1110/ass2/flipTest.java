package comp1110.ass2;


import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.Timeout;

import java.util.Arrays;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * Test objective:
 *
 * A piece takes up 9 possible letter positions on the board.
 * Determine whether a piece and its given array,
 * when flipped (i.e for AA to become AE),
 * ensures the piece and its peg locations
 * are validly mirrored around to make the other type of piece,
 *
 * ie. AA             AE
 * [1, 2, 0          [0, 1, 2,
 *  2, 1, 2,    ->    1, 2, 1,
 *  1, 0, 0]          0, 0, 2]
 *
 * where "1" defines a part of the piece placed on a lower peg, and
 * where "2" defines a part of the piece placed on an upper location, and
 * where "0" defines a board letter that the piece does not take up.
 *
 * We test to ensure:
 * - the piece flips and mirrors properly to create the resulting needed piece.
 * - the resulting array is also of length 9.
 * - the piece can repeatedly be flipped using the same function.
 * - the piece values stay the same.
 */


// Reference: Lecture Material
public class flipTest {

    int[] simple = {1,2,0,2,1,2,1,0,0};
    int[] simpleFlippedOnce = {0, 1, 2, 1, 2, 1, 0, 0, 2};
    int[] simpleFlippedTwice = simple;

    int[] flipOnce = StepsGame.flip(simple);
    int[] flipTwice = StepsGame.flip(flipOnce);

    String s = Arrays.toString(simple);
    String s1 = Arrays.toString(simpleFlippedOnce);
    //String s2 = Arrays.toString(simpleRotatedTwice);
    //String s3 = Arrays.toString(simpleRotatedThrice);

    String f1 = Arrays.toString(flipOnce);
    String f2 = Arrays.toString(flipTwice);


    @Rule
    public Timeout globalTimeout = Timeout.millis(2000);


    @Test
    public void testSimple() {
        assertTrue(s + " mirrored should be " + s1 + " but was " + f1, Arrays.equals(flipOnce, simpleFlippedOnce));
    }

    @Test
    public void TestDoubleFlip() {
        assertTrue("The mirror of the mirror of " + s + " should be " + s + " but was " + f2, Arrays.equals(flipTwice, simple));
        assertTrue("The mirror of the mirror of " + s1 + " should be " + s1 + " but was " + f1, Arrays.equals(flipOnce, simpleFlippedOnce));
    }

    @Test
    public void TestLength() {
        assertFalse("Length of resulting flipped piece array should be 9, but was " + flipOnce.length, flipOnce.length != 9);
    }

    @Test
    public void TestVals() {
        assertTrue(s1 + " should have the same values as " + f1 + ", but doesn't.",s1.equals(f1));
        assertFalse(s1 + "should not have the same values as " + f2 + ", but does.",s1.equals(f2));
        assertTrue(s + " should have the same values as " + f2 + ", but doesn't.",s.equals(f2));
    }
}
