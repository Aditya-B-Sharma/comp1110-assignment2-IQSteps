package comp1110.ass2;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * Created by Stefan on 26/09/2017.
 */

// Reference: Lecture Material
public class updateRemainingMoves {

    /* Sample test cases and their solutions */
    String test = "BAM";
    String[] original = {"BAL","BAN","BAP","BAR","BAU","BAW","BAY","BAb","BAd","BAg","BAi","BAk","BAm","GGM","GGO","GGQ","GGS","GGV","GGX","GGa","GGc","GGh","GGj","GGl","GGn",
            "HFM","HFO","HFQ","HFS","HFV","HFX","HFa","HFc","HFh","HFj","HFl","HFn"};
    String[] remaining = {"GGM","GGO","GGQ","GGS","GGV","GGX","GGa","GGc","GGh","GGj","GGl","GGn",
            "HFM","HFO","HFQ","HFS","HFV","HFX","HFa","HFc","HFh","HFj","HFl","HFn"};
    String test2 = "AAL";
    String[] original2 = {"AAL","AAN","AAP","AAR","AAW","AAY","AAb","AAd","AAg","AAi","AAk","AAm","DDL","DDN","DDP","DDR","DDW","DDY","DDb","DDd","DDg","DDi","DDk","DDm",
            "GGM","GGO","GGQ","GGS","GGV","GGX","GGa","GGc","GGh","GGj","GGl","GGn"};
    String[] remaining2 = {"DDL","DDN","DDP","DDR","DDW","DDY","DDb","DDd","DDg","DDi","DDk","DDm",
            "GGM","GGO","GGQ","GGS","GGV","GGX","GGa","GGc","GGh","GGj","GGl","GGn"};

    String test3 = "BGSGGM";
    String[] original3 = {"BAL","BAN","BAP","BAR","BAU","BAW","BAY","BAb","BAd","BAg","BAi","BAk","BAm","GGM","GGO","GGQ","GGS","GGV","GGX","GGa","GGc","GGh","GGj","GGl","GGn",
            "HFM","HFO","HFQ","HFS","HFV","HFX","HFa","HFc","HFh","HFj","HFl","HFn"};
    String[] remaining3 = {"HFM","HFO","HFQ","HFS","HFV","HFX","HFa","HFc","HFh","HFj","HFl","HFn"};



    /* Tests if updateRemainingMoves returns a list of remaining moves available given that the shapes that are already placed are removed
     * from the list. */

    /* Test for one starting piece placement given. */
    @Test
    public void testOneStartingPlacement() {
        ArrayList<String> moves = new ArrayList<>(Arrays.asList(original));
        ArrayList<String> moves2 = new ArrayList<>(Arrays.asList(original2));
        assertTrue("Expected " + Arrays.toString(remaining) + " but got" + Arrays.toString(StepsGame.updateRemainingMoves(test,moves).toArray()), Arrays.equals(StepsGame.updateRemainingMoves(test,moves).toArray(),remaining));
        assertTrue("Expected " + Arrays.toString(remaining2) + " but got" + Arrays.toString(StepsGame.updateRemainingMoves(test2,moves2).toArray()), Arrays.equals(StepsGame.updateRemainingMoves(test2,moves2).toArray(),remaining2));
    }

    /* Test for multiple starting piece placements given. */
    @Test
    public void testMultipleStartingPlacements() {
        ArrayList<String> moves3 = new ArrayList<>(Arrays.asList(original3));
        assertTrue("Expected " + Arrays.toString(remaining3) + " but got" + Arrays.toString(StepsGame.updateRemainingMoves(test3,moves3).toArray()), Arrays.equals(StepsGame.updateRemainingMoves(test3,moves3).toArray(),remaining3));
    }
}
