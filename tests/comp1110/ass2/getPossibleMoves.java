package comp1110.ass2;

import org.junit.Test;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by Stefan on 26/09/2017.
 */

// Reference: Lecture Material
public class getPossibleMoves {

    /* Sample test cases and their solutions */
    String[] testSimple = {"AA"};
    String[] testSimple2 = {"DD"};
    String[] testSimple3 = {"GG"};
    String[] locations = {"A","B","C","D","E","F","G","H","I","J","K","L","M","N","O","P","Q","R","S","T","U","V","W","X","Y",
            "a","b","c","d","e","f","g","h","i","j","k","l","m","n","o","p","q","r","s","t","u","v","w","x","y"};
    String[] testSimpleMoves = {"AAL","AAN","AAP","AAR","AAW","AAY","AAb","AAd","AAg","AAi","AAk","AAm"};
    String[] testSimpleMoves2 = {"DDL","DDN","DDP","DDR","DDW","DDY","DDb","DDd","DDg","DDi","DDk","DDm"};
    String[] testSimpleMoves3 = {"GGM","GGO","GGQ","GGS","GGV","GGX","GGa","GGc","GGh","GGj","GGl","GGn"};

    String[] testMultiple = {"BA","GG","HF"};
    String[] testMultipleMoves = {"BAL","BAN","BAP","BAR","BAU","BAW","BAY","BAb","BAd","BAg","BAi","BAk","BAm","GGM","GGO","GGQ","GGS","GGV","GGX","GGa","GGc","GGh","GGj","GGl","GGn",
                                  "HFM","HFO","HFQ","HFS","HFV","HFX","HFa","HFc","HFh","HFj","HFl","HFn"};
    String[] testMultiple2 = {"AA","DD","GG"};
    String[] testMultipleMoves2 = {"AAL","AAN","AAP","AAR","AAW","AAY","AAb","AAd","AAg","AAi","AAk","AAm","DDL","DDN","DDP","DDR","DDW","DDY","DDb","DDd","DDg","DDi","DDk","DDm",
                                      "GGM","GGO","GGQ","GGS","GGV","GGX","GGa","GGc","GGh","GGj","GGl","GGn"};

    String[] invalidMovesArray = {"AAL","HFQ","AAM", "GGG", "BBB"};

    /* Checks if each individual piece placement in the array argument is a valid placement */
    public boolean isPlacementsValid(String[] possibleMoves) {
        for (String p : possibleMoves) {
            if (!StepsGame.isPlacementSequenceValid(p)) {
                return false;
            }
        }
        return true;
    }

    /* Tests to see if the method in StepsGame actually returns an appropriate list, with all piece placements being valid */
    @Test
    public void allValidPlacements() {
        assertFalse("List of possible placements for each shape contains an invalid piece placement.", isPlacementsValid(invalidMovesArray));
        assertTrue("List of possible placements for each shape contains an invalie piece placement.", isPlacementsValid(testMultipleMoves));
    }

    /* Tests to see if the simple cases gives the list containing all the possible LEGAL piece placements */
    @Test
    public void testSimple() {
        assertTrue("Expected " + Arrays.toString(testSimpleMoves) + " but got" + Arrays.toString(StepsGame.getPossibleMoves(testSimple,locations).toArray()), Arrays.equals((StepsGame.getPossibleMoves(testSimple,locations).toArray()),testSimpleMoves));
        assertTrue("Expected " + Arrays.toString(testSimpleMoves2) + " but got" + Arrays.toString(StepsGame.getPossibleMoves(testSimple2,locations).toArray()), Arrays.equals((StepsGame.getPossibleMoves(testSimple2,locations).toArray()),testSimpleMoves2));
        assertTrue("Expected " + Arrays.toString(testSimpleMoves3) + " but got" + Arrays.toString(StepsGame.getPossibleMoves(testSimple3,locations).toArray()), Arrays.equals((StepsGame.getPossibleMoves(testSimple3,locations).toArray()),testSimpleMoves3));
    }

    /* Tests to see if the cases with multiple shapes gives the list containing all the possible LEGAL piece placements */
    @Test
    public void testMultiple() {
        assertTrue("Expected " + Arrays.toString(testMultipleMoves) + " but got" + Arrays.toString(StepsGame.getPossibleMoves(testMultiple,locations).toArray()), Arrays.equals((StepsGame.getPossibleMoves(testMultiple,locations).toArray()),testMultipleMoves));
        assertTrue("Expected " + Arrays.toString(testMultipleMoves2) + " but got" + Arrays.toString(StepsGame.getPossibleMoves(testMultiple2,locations).toArray()), Arrays.equals((StepsGame.getPossibleMoves(testMultiple2,locations).toArray()),testMultipleMoves2));
    }
}
