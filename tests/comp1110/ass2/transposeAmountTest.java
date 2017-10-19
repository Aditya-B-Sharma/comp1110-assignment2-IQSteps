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
 * i.e AAl -> [1, 2, 0,
 *             2, 1, 2,
 *             1, 0, 0]
 * where "1" defines a part of the piece placed on a lower peg, and
 * where "2" defines a part of the piece placed on an upper location, and
 * where "0" defines a board letter that the piece does not take up.
 *
 * Determine that the transposeAmount function returns
 * the valid array representation of the given piece.
 *
 * We test to ensure:
 * - the resulting array is correct.
 * - the resulting array is also of length 9.
 * - pieces of different types (i.e AA and AE) return valid respective representations.
 */


// Reference: Lecture Material
public class transposeAmountTest {

    int[] AA = {1,2,0,2,1,2,1,0,0};
    int[] AE = {0, 1, 2, 1, 2, 1, 0, 0, 2};

    int[] checkAA = StepsGame.transposeAmount("AA");
    int[] checkAE = StepsGame.transposeAmount("AE");

    @Rule
    public Timeout globalTimeout = Timeout.millis(2000);


    @Test
    public void testAA() {
        assertTrue("The parameter 'AA' should return " + Arrays.toString(AA) + " but returned " + Arrays.toString(checkAA), Arrays.equals(checkAA, AA));
        assertTrue("The parameter 'AE' should return " + Arrays.toString(AE) + " but returned " + Arrays.toString(checkAE), Arrays.equals(checkAE, AE));
    }

    @Test
    public void TestLength() {
        assertFalse("Length of resulting piece array should be 9, but was " + checkAA.length, checkAA.length != 9);
    }
}
