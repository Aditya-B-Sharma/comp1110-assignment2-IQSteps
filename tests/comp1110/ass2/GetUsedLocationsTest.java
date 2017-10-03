package comp1110.ass2;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * Created by Stefan on 2/10/2017.
 */
public class GetUsedLocationsTest {
    /* Sample test cases and their solutions */
    String[] locations = {"A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y",
            "a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k", "l", "m", "n", "o", "p", "q", "r", "s", "t", "u", "v", "w", "x", "y"};
    String test = "BAM";
    String[] output = {"M"};
    String test2 = "AAL";
    String[] output2 = {"L"};

    String test3 = "BGSGGM";
    String[] output3 = {"S","M"};
    String test4 = "AALHFM";
    String[] output4 = {"L","M"};

    String test5 = "DFOGGQEDIBAk";
    String[] output5 = {"O","Q","I","k"};
    String test6 = "EFBCFlAFnGFSBFqFGhHANDDP";
    String[] output6 = {"B","l","n","S","q","h","N","P"};

    @Test
    public void testSingle() {
        assertTrue("Expected " + Arrays.toString(output) + " but got " + Arrays.toString(StepsGame.getUsedLocations(test).toArray()), StepsGame.getUsedLocations(test).toArray().equals(output));
        assertTrue("Expected " + Arrays.toString(output2) + " but got " + Arrays.toString(StepsGame.getUsedLocations(test2).toArray()), StepsGame.getUsedLocations(test2).toArray().equals(output2));
    }

    @Test
    public void testPairs() {
        assertTrue("Expected " + Arrays.toString(output3) + " but got " + Arrays.toString(StepsGame.getUsedLocations(test3).toArray()), StepsGame.getUsedLocations(test3).toArray().equals(output3));
        assertTrue("Expected " + Arrays.toString(output4) + " but got " + Arrays.toString(StepsGame.getUsedLocations(test4).toArray()), StepsGame.getUsedLocations(test4).toArray().equals(output4));
    }

    @Test
    public void testMultiplePlacements() {
        assertTrue("Expected " + Arrays.toString(output5) + " but got " + Arrays.toString(StepsGame.getUsedLocations(test5).toArray()), StepsGame.getUsedLocations(test5).toArray().equals(output5));
        assertTrue("Expected " + Arrays.toString(output6) + " but got " + Arrays.toString(StepsGame.getUsedLocations(test6).toArray()), StepsGame.getUsedLocations(test6).toArray().equals(output6));
    }
}
